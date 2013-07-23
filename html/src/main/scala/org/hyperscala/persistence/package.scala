package org.hyperscala

import css.attributes._
import html.attributes._
import org.hyperscala.selector.Selector

/**
 * NOTE: This file has been generated. Do not modify directly!
 */
package object persistence {
  implicit val stringPersistence = StringPersistence
  implicit val alignmentPersistence = Alignment
  implicit val autoCompletePersistence = AutoComplete
  implicit val booleanPersistence = BooleanPersistence
  implicit val buttonTypePersistence = ButtonType
  implicit val charPersistence = CharPersistence
  implicit val clearPersistence = Clear
  implicit val colorPersistence = ColorPersistence
  implicit val contentEditablePersistence = ContentEditable
  implicit val directionPersistence = Direction
  implicit val displayPersistence = Display
  implicit val doublePersistence = DoublePersistence
  implicit val draggablePersistence = Draggable
  implicit val dropZonePersistence = DropZone
  implicit val floatPersistence = Float
  implicit val fontSizePersistence = FontSize
  implicit val javaScriptContentPersistence = JavaScriptContentPersistence
  implicit val inputTypePersistence = InputType
  implicit val intPersistence = IntPersistence
  implicit val languagePersistence = LanguagePersistence
  implicit val lengthPersistence = Length
  implicit val listStringPersistence = ListStringPersistence
  implicit val listIntPersistence = ListIntPersistence
  implicit val listDoublePersistence = ListDoublePersistence
  implicit val methodPersistence = Method
  implicit val opacityPersistence = Opacity
  implicit val overflowPersistence = Overflow
  implicit val positionPersistence = Position
  implicit val relationshipPersistence = Relationship
  implicit val resourcePersistence = Resource
//  implicit val styleSheetPersistence = TagStyleSheet
  implicit val selectorPersistence = Selector
  implicit val targetPersistence = Target
  implicit val textAreaWrapPersistence = TextAreaWrap
  implicit val whiteSpacePersistence = WhiteSpace
}