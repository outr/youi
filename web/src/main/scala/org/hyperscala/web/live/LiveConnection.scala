package org.hyperscala.web.live

import org.hyperscala.Unique
import org.powerscala.concurrent.Time

/**
 * LiveConnections are created for each window representation of the LivePage. This allows LiveChange lists to be
 * trimmed and only include relevant changes for the specific window instance. Reloading a page will reset the
 * LiveConnection.
 */
class LiveConnection(val id: String = Unique(),
                     val created: Long = System.currentTimeMillis(),
                     var lastUpdated: Long = System.currentTimeMillis()) {
  private var changes = Vector.empty[LiveChange]

  def elapsed = Time.fromMillis(System.currentTimeMillis() - lastUpdated)

  /**
   * Retrieves all changes since lastId or Nil if no changes.
   */
  def apply(lastId: Int): Seq[LiveChange] = synchronized {
    if (changes.nonEmpty) {
      val head = changes.head
      if (head.id <= lastId) {
        // Client verified receipt of this id - remove and continue
        changes = changes.tail
        apply(lastId)
      } else {
        // Client hasn't receive this id, so send the rest of the list
        lastUpdated = System.currentTimeMillis()
        changes
      }
    } else {
      // No changes to send
      lastUpdated = System.currentTimeMillis()
      Nil
    }
  }

  def +=(change: LiveChange) = synchronized {
    if (change.key != null) {
      changes = changes.filterNot(c => c.key == change.key) // Remove duplicates
    }
    changes = changes :+ change // Append the change
  }
}
