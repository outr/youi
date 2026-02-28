package tabulator

import scala.scalajs.js

@js.native
trait RowComponent extends js.Object {
  def getData(): js.Object = js.native
  def getElement(): org.scalajs.dom.Element = js.native
  def getTable(): Tabulator = js.native
  def getNextRow(): js.UndefOr[RowComponent] = js.native
  def getPrevRow(): js.UndefOr[RowComponent] = js.native
  def getCells(): js.Array[CellComponent] = js.native
  def getCell(column: js.Any): CellComponent = js.native
  def getIndex(): js.Any = js.native
  def getPosition(): Int = js.native
  def getGroup(): GroupComponent = js.native
  def delete(): js.Promise[Unit] = js.native
  def scrollTo(position: js.UndefOr[String] = js.undefined, ifVisible: js.UndefOr[Boolean] = js.undefined): js.Promise[Unit] = js.native
  def pageTo(): js.Promise[Unit] = js.native
  def move(to: js.Any, after: js.UndefOr[Boolean] = js.undefined): Unit = js.native
  def update(data: js.Object): js.Promise[Unit] = js.native
  def select(): Unit = js.native
  def deselect(): Unit = js.native
  def toggleSelect(): Unit = js.native
  def isSelected(): Boolean = js.native
  def normalizeHeight(): Unit = js.native
  def reformat(): Unit = js.native
  def freeze(): Unit = js.native
  def unfreeze(): Unit = js.native
  def isFrozen(): Boolean = js.native
  def treeExpand(): Unit = js.native
  def treeCollapse(): Unit = js.native
  def treeToggle(): Unit = js.native
  def getTreeParent(): js.UndefOr[RowComponent] = js.native
  def getTreeChildren(): js.Array[RowComponent] = js.native
  def isTreeExpanded(): Boolean = js.native
  def validate(): js.Any = js.native
  def popup(contents: js.Any, position: js.UndefOr[String] = js.undefined): Unit = js.native
}

@js.native
trait CellComponent extends js.Object {
  def getValue(): js.Any = js.native
  def getOldValue(): js.Any = js.native
  def restoreOldValue(): Unit = js.native
  def getInitialValue(): js.Any = js.native
  def restoreInitialValue(): Unit = js.native
  def getElement(): org.scalajs.dom.Element = js.native
  def getTable(): Tabulator = js.native
  def getRow(): RowComponent = js.native
  def getColumn(): ColumnComponent = js.native
  def getData(): js.Object = js.native
  def getField(): String = js.native
  def getType(): String = js.native
  def setValue(value: js.Any, mutate: js.UndefOr[Boolean] = js.undefined): Unit = js.native
  def checkHeight(): Unit = js.native
  def edit(force: js.UndefOr[Boolean] = js.undefined): Unit = js.native
  def cancelEdit(): Unit = js.native
  def isEdited(): Boolean = js.native
  def clearEdited(): Unit = js.native
  def navigatePrev(): Unit = js.native
  def navigateNext(): Unit = js.native
  def navigateLeft(): Unit = js.native
  def navigateRight(): Unit = js.native
  def navigateUp(): Unit = js.native
  def navigateDown(): Unit = js.native
  def validate(): js.Any = js.native
  def isValid(): Boolean = js.native
  def clearValidation(): Unit = js.native
  def popup(contents: js.Any, position: js.UndefOr[String] = js.undefined): Unit = js.native
}

@js.native
trait GroupComponent extends js.Object {
  def getElement(): org.scalajs.dom.Element = js.native
  def getTable(): Tabulator = js.native
  def getKey(): js.Any = js.native
  def getField(): String = js.native
  def getRows(): js.Array[RowComponent] = js.native
  def getSubGroups(): js.Array[GroupComponent] = js.native
  def getParentGroup(): js.UndefOr[GroupComponent] = js.native
  def isVisible(): Boolean = js.native
  def show(): Unit = js.native
  def hide(): Unit = js.native
  def toggle(): Unit = js.native
  def scrollTo(position: js.UndefOr[String] = js.undefined, ifVisible: js.UndefOr[Boolean] = js.undefined): js.Promise[Unit] = js.native
  def popup(contents: js.Any, position: js.UndefOr[String] = js.undefined): Unit = js.native
}
