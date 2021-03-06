package jp.modal.soul

import jp.modal.soul.tonbeji.{SiteBuild, DirectoryStructure}
import sbt._
import Keys._

object Tonbeji extends Plugin {

  val productionUrl = settingKey[String]("production url")
  val title = settingKey[String]("title")
  val author = settingKey[Author]("author")
  val googleAnalytics = settingKey[String]("googleAnalytics")
  val mixPanel = settingKey[String]("mixPanel")
  val siteMenu = settingKey[Menu]("menu")

  val postsDirName = "posts"  // TODO build.sbt config
  val layoutsDirName = "layouts" // TODO build.sbt config
  val includesDirName = "includes" // TODO build.sbt config
  val destinationDirName = "public" // TODO build.sbt config
  val directoryStructure = DirectoryStructure(postsDirName, layoutsDirName, includesDirName, destinationDirName)

  override lazy val settings = Seq(
    commands ++= Seq(
      build,
      serve
    )
  )

  def log(command:String, options:Seq[String] = Nil): Unit = {
    println("[Info]Command: %s %s".format(command, options.mkString(" ")))
  }

  // build
  lazy val build = Command.command("build") { state =>
    log("build")
    new SiteBuild(directoryStructure)
    state
  }

  // serve
  lazy val serve = Command.command("serve") { state =>
    log("serve")
    state
  }

  case class Author(authorInfo:Pair[Symbol, String]*)

  case class Menu(items:Pair[String, String]*)
}


