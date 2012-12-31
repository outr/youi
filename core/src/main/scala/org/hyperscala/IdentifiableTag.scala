package org.hyperscala

/**
 * @author Matt Hicks <mhicks@outr.com>
 */
trait IdentifiableTag extends Tag {
  val id = PropertyAttribute[String]("id", null)

  /**
   * Gets the id value and sets it to a unique value if it's null.
   */
  def identity = id() match {
    case null => {
      val uuid = Unique()
      id := uuid
      uuid
    }
    case value => value
  }
}
