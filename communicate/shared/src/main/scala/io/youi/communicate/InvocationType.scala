package io.youi.communicate

sealed abstract class InvocationType(val id: Int)

object InvocationType {
  case object CallRequest extends InvocationType(1)
  case object CallResponse extends InvocationType(2)
  case object MethodRequest extends InvocationType(3)
  case object MethodResponse extends InvocationType(4)

  def apply(id: Int): InvocationType = id match {
    case CallRequest.id => CallRequest
    case CallResponse.id => CallResponse
    case MethodRequest.id => MethodRequest
    case MethodResponse.id => MethodResponse
  }
}