package io.youi.template

import io.youi.app.{ApplicationCommunication, CommunicationManager, YouIApplication}

trait TemplateApplication extends YouIApplication {
  object comm extends ApplicationCommunication(this) {
    val template: CommunicationManager[TemplateCommunication] = communication[TemplateCommunication]
  }
}