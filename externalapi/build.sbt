ThisBuild / scalaVersion := "2.11.12"

import Dependencies._
import com.earldouglas.xwp.ContainerPlugin.autoImport.containerPort

lazy val externalapi = (project in file("."))
  .enablePlugins(TomcatPlugin)
  .settings(
    name := "externalapi",
    libraryDependencies ++= Seq(
      servletApi % "provided",
    ),
    containerPort := 9091
  )
