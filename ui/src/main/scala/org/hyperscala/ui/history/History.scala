package org.hyperscala.ui.history

import org.hyperscala.module.Module
import org.powerscala.Version
import org.hyperscala.web.{Webpage, Website}
import org.powerscala.event.Listenable
import scala.collection.immutable.Queue
import org.powerscala.event.processor.UnitProcessor
import org.powerscala.concurrent.AtomicInt
import org.hyperscala.realtime.Realtime

import org.hyperscala.jquery.dsl._
import org.hyperscala.realtime.dsl._

import scala.language.postfixOps
import org.hyperscala.event.Key
import org.powerscala.enum.{Enumerated, EnumEntry}
import org.hyperscala.web.useragent.UserAgent
import com.outr.net.http.session.Session
import org.hyperscala.javascript.dsl.body

/**
 * History module provides history management functionality to a webpage.
 *
 * @author Matt Hicks <matt@outr.com>
 */
object History extends Module {
  /**
   * Creates a new HistoryInstance each time it is called (once per webpage instance).
   */
  val WebpageInstanceCreator = (webpage: Webpage[_ <: Session]) => new HistoryInstance(webpage)
  /**
   * Stores the HistoryInstance in the session so each webpage has a persistent instance.
   */
  val SessionInstanceCreator = (webpage: Webpage[_ <: Session]) => {
    webpage.website.session.getOrSet(s"${webpage.getClass.getName}.history_module", new HistoryInstance(webpage))
  }

  val name = "history"
  val version = Version(1)

  override def dependencies = List(Realtime)

  override def init[S <: Session](website: Website[S]) = {}

  override def load[S <: Session](webpage: Webpage[S]) = apply(webpage)

  /**
   * Creates new HistoryInstances. This can be overridden to pre-populate or share instances across multiple pages.
   *
   * By default a single instance is tied to a single webpage instance (WebpageInstanceCreator).
   */
  var creator: (Webpage[_ <: Session]) => HistoryInstance = WebpageInstanceCreator

  def apply[S <: Session](webpage: Webpage[S]) = webpage.store.getOrSet("history_module", creator(webpage))
}

class HistoryInstance(webpage: Webpage[_ <: Session]) extends Listenable {
  private var undos = Queue.empty[HistoryEntry]
  private var redos = Queue.empty[HistoryEntry]
  private val changing = new AtomicInt(0)

  val added = new UnitProcessor[HistoryEntry]("history_added")
  val stateChanged = new UnitProcessor[HistoryStateChange]("history_state_change")

  // Configure key bindings on page
  if (UserAgent(webpage).os.family.apple) {
    $(body).keyDown(onKey(webpage, Key.Z, shiftKey = Some(false), metaKey = Some(true), stopPropagation = true) {
      undo()
    }).send(webpage)
    $(body).keyDown(onKey(webpage, Key.Z, shiftKey = Some(true), metaKey = Some(true), stopPropagation = true) {
      redo()
    }).send(webpage)
    $(body).keyDown(onKey(webpage, Key.Y, ctrlKey = Some(true), stopPropagation = true) {
      redo()
    }).send(webpage)
  } else {
    $(body).keyDown(onKey(webpage, Key.Z, shiftKey = Some(false), ctrlKey = Some(true), stopPropagation = true) {
      undo()
    }).send(webpage)
    $(body).keyDown(onKey(webpage, Key.Z, shiftKey = Some(true), ctrlKey = Some(true), stopPropagation = true) {
      redo()
    }).send(webpage)
    $(body).keyDown(onKey(webpage, Key.Y, ctrlKey = Some(true), stopPropagation = true) {
      redo()
    }).send(webpage)
  }

  def undoList = undos.toList
  def redoList = redos.toList

  /**
   * Returns true if the history state is currently being modified (undoing or redoing).
   */
  def isChanging = changing.get() > 0

  /**
   * Adds an undo history entry. Properly cuts the redo history before adding and merges if supported.
   *
   * @param entry the history entry to add
   * @param callRedo if set to true the redo method will be invoked on the entry before it is added (defaults to false)
   */
  def add(entry: HistoryEntry, callRedo: Boolean = false) = synchronized {
    if (!isChanging) {
      cut()                                     // Clear out any redo history before add an entry
      val newEntry = previous() match {         // Allows merging with the previous history entry
        case Some(p) => entry.mergeWithPrevious(p) match {
          case Some(e) => {
            removePrevious()                    // Remove the last entry because it was merged with the current entry
            e
          }
          case None => entry
        }
        case None => entry
      }
      if (callRedo) {
        withoutHistory {
          entry.redo()
        }
      }
      undos = newEntry +: undos                 // Add the entry to the undos queue
      added.fire(newEntry)
    }
  }

  private def removePrevious() = {
    undos = undos.tail
  }

  /**
   * Goes backward one step in history if there is undo history available.
   *
   * @return true if history moved backward one step, false if there is no undo history
   */
  def undo() = synchronized {
    withoutHistory {
      previous() match {
        case Some(entry) => {
          entry.undo()            // Undo the entry
          undos = undos.tail      // Remove it from the undos queue
          redos = entry +: redos  // Add it to the redos queue
          stateChanged.fire(HistoryStateChange.Undo)
          true
        }
        case None => false
      }
    }
  }

  /**
   * Goes forward one step in history if there is redo history available.
   *
   * @return true if history moved forward one step, false if there is no redo history
   */
  def redo() = synchronized {
    withoutHistory {
      next() match {
        case Some(entry) => {
          entry.redo()            // Redo the entry
          redos = redos.tail      // Remove it from the redos queue
          undos = entry +: undos  // Add it to the undos queue
          stateChanged.fire(HistoryStateChange.Redo)
          true
        }
        case None => false
      }
    }
  }

  /**
   * Invokes the supplied function without recording any history during its invocation. This is primarily for undo and
   * redo manipulation internally.
   */
  def withoutHistory[T](f: => T): T = {
    stopHistory()
    try {
      f
    } finally {
      startHistory()
    }
  }

  /**
   * Used to temporarily stop recording history changes. Primarily used during undo or redo to avoid duplicate entries.
   */
  private def stopHistory() = changing++

  /**
   * Used to start recording history changes again after stopHistory() is called. Primarily used during undo or redo to
   * avoid duplicate entries.
   */
  private def startHistory() = changing.decrementIfGreaterThan(0)

  /**
   * Returns the next step backward in history (into the undos) or None if there are no history entries to go back to.
   *
   * Does not modify the history state.
   */
  def previous() = undos.headOption

  /**
   * Returns the next step forward in history (into the redos) or None if there are no history entries to go forward to.
   *
   * Does not modify the history state.
   */
  def next() = redos.headOption

  /**
   * Cuts off all the redo history. Called before adding to the history.
   */
  def cut() = synchronized {
    redos = Queue.empty
  }

  /**
   * Clears all undo and redo history.
   */
  def clear() = synchronized {
    undos = Queue.empty
    redos = Queue.empty
    stateChanged.fire(HistoryStateChange.Clear)
  }
}

class HistoryStateChange private() extends EnumEntry

object HistoryStateChange extends Enumerated[HistoryStateChange] {
  val Undo = new HistoryStateChange
  val Redo = new HistoryStateChange
  val Clear = new HistoryStateChange
}