ThisBuild / scalaVersion := "2.11.12"

import Dependencies._
import com.earldouglas.xwp.ContainerPlugin.autoImport.containerPort

lazy val demo = (project in file("."))
  .enablePlugins(TomcatPlugin)
  .settings(
    name := "demo",
    libraryDependencies ++= Seq(
      servletApi % "provided",
      scalaj,
    ),
    containerPort := 9090
  )
