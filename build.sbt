import Dependencies._

ThisBuild / scalaVersion     := "2.12.8"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "scala-json-libraries-comparison",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      "com.typesafe.play" %% "play-json" % "2.7.2",
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "0.45.2" % Compile,
      "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "0.45.2" % Provided, // required only in compile-time
      "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.8",
      "com.lihaoyi" %% "ujson" % "0.7.1",
      "io.spray" %%  "spray-json" % "1.3.5"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
