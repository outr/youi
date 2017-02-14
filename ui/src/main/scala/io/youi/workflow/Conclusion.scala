package io.youi.workflow

sealed trait Conclusion

object Conclusion {
  case object Continue extends Conclusion
  case object Finished extends Conclusion
}