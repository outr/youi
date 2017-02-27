package io.youi.template

import io.youi.app.{ApplicationCommunication, CommunicationManager, YouIApplication}

trait TemplateApplication extends YouIApplication {
  val comm = new TemplateComms(this)
}

class TemplateComms(application: TemplateApplication) extends ApplicationCommunication(application) {
  val template: CommunicationManager[TemplateCommunication] = communication[TemplateCommunication]
}