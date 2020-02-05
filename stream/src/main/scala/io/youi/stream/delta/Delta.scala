package io.youi.stream.delta

import io.youi.stream.{HTMLStream, Selector, Tag}

trait Delta {
  def selector: Selector
  def apply(streamer: HTMLStream, tag: Tag.Open): Unit
}

object Delta {
  def Replace(selector: Selector, content: => String): Replace = new Replace(selector, () => content)
  def Process(selector: Selector,
              replace: Boolean,
              onlyOpenTag: Boolean,
              processor: (Tag.Open, String) => String,
              closeTagProcessor: Option[(Tag.Open, Tag.Close, String) => String] = None): Processor = new Processor(selector, replace, onlyOpenTag, processor, closeTagProcessor)
  def InsertBefore(selector: Selector, content: => String): InsertBefore = new InsertBefore(selector, () => content)
  def InsertFirstChild(selector: Selector, content: => String): InsertFirstChild = new InsertFirstChild(selector, () => content)
  def ReplaceContent(selector: Selector, content: => String): ReplaceContent = new ReplaceContent(selector, () => content)
  def ReplaceAttribute(selector: Selector, attributeName: String, content: => String): ReplaceAttribute = new ReplaceAttribute(selector, attributeName, () => content)
  def InsertLastChild(selector: Selector, content: => String): InsertLastChild = new InsertLastChild(selector, () => content)
  def InsertAfter(selector: Selector, content: => String): InsertAfter = new InsertAfter(selector, () => content)
  def Repeat[Data](selector: Selector, data: List[Data], changes: Data => List[Delta]): Repeat[Data] = new Repeat(selector, data, changes)
  def Template(selector: Selector, deltas: List[Delta]): Template = new Template(selector, deltas)
  def Grouped(selector: Selector, deltas: Delta*): Grouped = new Grouped(selector, deltas.toList)
}