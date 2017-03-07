package io.youi.template

import io.youi.app.{CommunicationManager, YouIApplication}

trait TemplateApplication extends YouIApplication {
  val communication: CommunicationManager[TemplateCommunication] = createCommunication[TemplateCommunication](connectivity)
}