package io.youi.template

import io.youi.app.{CommunicationManager, YouIApplication}

trait TemplateApplication extends YouIApplication {
  val comm: CommunicationManager[TemplateCommunication] = communication[TemplateCommunication]
}