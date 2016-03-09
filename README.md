## http4s-global-middleware

[![Build Status](https://api.travis-ci.org/hgiddens/http4s-global-middleware.svg)](https://travis-ci.org/hgiddens/http4s-global-middleware)

A simple syntax extension to add middleware to all subsequently mounted routes.

### Usage

Add the following to your `build.sbt`:

    resolvers += Resolver.bintrayRepo("hgiddens", "maven")
    libraryDependencies += "com.github.hgiddens" %% "http4s-global-middleware" % <version>

### Syntax

    import com.github.hgiddens.http4s.middleware.global.Syntax._
    import com.http4s.server.ServerBuilder
    def serverBuilder: ServerBuilder = ???

    serverBuilder.
      globalMiddleware(a).
      globalMiddleware(b).
      mountService(c)
    // Is equivalent to
    serverBuilder.mountService(a(b(c)))
