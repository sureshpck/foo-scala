name := """foo-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  filters,
  jdbc,
  cache,
  ws,
  evolutions,
  "mysql" % "mysql-connector-java" % "5.1.28",
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.play" %% "anorm" % "2.3.6",
  "org.specs2" %% "specs2-core" % "3.8.5" % "test"
)

