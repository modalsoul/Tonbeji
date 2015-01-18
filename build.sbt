sbtPlugin := true

name := "tonbeji"

organization := "jp.modal.soul"

scalaVersion := "2.10.3"

val testDependencies = Seq(
  "org.scalatest" %% "scalatest" % "2.2.3" % "test"
)

libraryDependencies ++= Seq(
  "org.fusesource.scalate" %% "scalate-core" % "1.6.1",
  "com.tristanhunt"        %% "knockoff"     % "0.8.3"
) ++ testDependencies

