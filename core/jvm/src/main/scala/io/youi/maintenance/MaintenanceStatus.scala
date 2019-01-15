package io.youi.maintenance

sealed trait MaintenanceStatus {
  override def toString: String = getClass.getSimpleName.replaceAllLiterally("$", "")
}

object MaintenanceStatus {
  case object Init extends MaintenanceStatus
  case object Scheduled extends MaintenanceStatus
  final case class Running(time: Long) extends MaintenanceStatus {
    override def toString: String = {
      val elapsed = System.currentTimeMillis() - time
      s"Running(${elapsed / 1000.0} seconds)"
    }
  }
  case object Finished extends MaintenanceStatus
  case object Failure extends MaintenanceStatus
}