name := "whatsnewin210"

version := "1.0"

scalaVersion := "2.10.0"

resolvers += "spray repo" at "http://repo.spray.io/"

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.0",
  "com.typesafe.akka" %% "akka-actor" % "2.1.0",
  "net.databinder.dispatch" %% "dispatch-core" % "0.9.5",
  "io.spray" %%  "spray-json" % "1.2.3",
  "ch.qos.logback" % "logback-classic" % "1.0.6",
  "org.scalatest" %% "scalatest" % "1.9.1" % "test"
)
