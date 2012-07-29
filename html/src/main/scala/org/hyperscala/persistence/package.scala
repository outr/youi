package org.hyperscala

import css.attributes._
import html.attributes._

package object persistence {
  implicit val alignmentPersistence = Alignment
  implicit val booleanPersistence = BooleanPersistence
  implicit val charPersistence = CharPersistence
  implicit val clearPersistence = Clear
  implicit val colorPersistence = ColorPersistence
  implicit val contentEditablePersistence = ContentEditable
  implicit val directionPersistence = Direction
  implicit val displayPersistence = Display
  implicit val draggablePersistence = Draggable
  implicit val dropZonePersistence = DropZone
  implicit val floatPersistence = Float
  implicit val inputTypePersistence = InputType
  implicit val intPersistence = IntPersistence
  implicit val languagePersistence = LanguagePersistence
  implicit val lengthPersistence = Length
  implicit val listStringPersistence = ListStringPersistence
  implicit val positionPersistence = Position
  implicit val relationshipPersistence = Relationship
  implicit val resourcePersistence = Resource
  implicit val stringPersistence = StringPersistence
  implicit val targetPersistence = Target
  implicit val whiteSpacePersistence = WhiteSpace
}