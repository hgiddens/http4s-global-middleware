package com.github.hgiddens.http4s.middleware.global

import java.net.InetSocketAddress
import java.util.concurrent.ExecutorService
import org.http4s.server.{HttpMiddleware, HttpService, ServerBuilder}

object Syntax {
  implicit class ServerBuilderExtensions(val builder: ServerBuilder) extends AnyVal {
    /**
      * Adds middleware to all subsequent mountService calls.
      *
      * a.globalMiddleware(b).globalMiddleware(c).mountService(d)
      *
      * is equivalent to
      *
      * a.globalMiddleware(b compose c).mountService(d)
      *
      * is equivalent to
      *
      * a.mountService(b(c(d)))
      *
      * @param middleware the middleware to apply globally
      * @return a builder that adds middleware to all mounted services
      */
    def globalMiddleware(middleware: HttpMiddleware): ServerBuilder =
      GlobalMiddlewareServerBuilder(builder, middleware)
  }
}

private[global] case class GlobalMiddlewareServerBuilder(
    underlying: ServerBuilder,
    middleware: HttpMiddleware
) extends ServerBuilder {
  type Self = GlobalMiddlewareServerBuilder

  def bindSocketAddress(socketAddress: InetSocketAddress) =
    copy(underlying = underlying.bindSocketAddress(socketAddress))

  def withServiceExecutor(executor: ExecutorService) =
    copy(underlying = underlying.withServiceExecutor(executor))

  def mountService(service: HttpService, prefix: String = "") =
    copy(underlying = underlying.mountService(middleware(service), prefix))

  def start =
    underlying.start
}
