name := "http4s-global-middleware"
organization in ThisBuild := "com.github.hgiddens"

scalaVersion in ThisBuild := "2.11.8"
scalacOptions in ThisBuild ++= Seq(
  "-deprecation",
  "-unchecked",
  "-feature",
  "-language:experimental.macros",
  "-language:higherKinds",
  "-Xlint",
  "-Xlog-free-terms",
  "-Ywarn-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Ywarn-value-discard"
)
scalacOptions in Test in ThisBuild += "-Yrangepos"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-server" % "0.14.5a"
)

autoAPIMappings in ThisBuild := true
homepage in ThisBuild := Some(url("https://github.com/hgiddens/htt4ps-session"))
licenses in ThisBuild := Seq("Apache-2.0" -> url("http://opensource.org/licenses/Apache-2.0"))

import scalariform.formatter.preferences._
scalariformPreferences in ThisBuild := scalariformPreferences.value.
  setPreference(DoubleIndentClassDeclaration, true).
  setPreference(PlaceScaladocAsterisksBeneathSecondAsterisk, true).
  setPreference(SpacesAroundMultiImports, false)
