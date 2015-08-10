sbtPlugin := true

name := "tonbeji"

organization := "jp.modal.soul"

scalaVersion := "2.10.4"

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "2.2.3" % "test"
)

libraryDependencies ++= Seq(
  "org.scalatra.scalate" %% "scalate-core" % "1.7.0",
  "com.tristanhunt"        %% "knockoff"     % "0.8.3"
) ++ testDependencies

