package org.hyperscala.service

import com.outr.net.http.request.HttpRequest
import com.outr.net.http.response.HttpResponse

/**
 * ServiceInvocation represents a single invocation of a ServiceEndpoint.
 *
 * @author Matt Hicks <matt@outr.com>
 */
case class ServiceInvocation(endpoint: ServiceEndpoint, request: HttpRequest, response: HttpResponse)