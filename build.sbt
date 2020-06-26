lazy val root = (project in file("."))
  .settings(
    scalaVersion := "2.13.2",
    name := "tapir-openapi-repro",
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-core" % "0.16.1",
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % "0.16.1",
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % "0.16.1",
      "com.softwaremill.sttp.tapir" %% "tapir-openapi-docs" % "0.16.1",
      "com.softwaremill.sttp.tapir" %% "tapir-openapi-circe-yaml" % "0.16.1",
      "org.http4s" %% "http4s-blaze-server" % "0.21.5"
    )
  )

// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
