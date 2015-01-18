package jp.modal.soul

import jp.modal.soul.tonbeji.{ Builder, DirectoryStructure }
import sbt._
import Keys._

object Tonbeji extends Plugin {

  val productionUrl = settingKey[String]("production url")
  val title = settingKey[String]("title")
  val author = settingKey[Author]("author")
  val googleAnalytics = settingKey[String]("googleAnalytics")
  val mixPanel = settingKey[String]("mixPanel")
  val siteMenu = settingKey[Menu]("menu")

  val postsDirName = "posts" // TODO build.sbt config
  val layoutsDirName = "layouts" // TODO build.sbt config
  val includesDirName = "includes" // TODO build.sbt config
  val destinationDirName = "public" // TODO build.sbt config
  val directoryStructure = DirectoryStructure(postsDirName, layoutsDirName, includesDirName, destinationDirName)

  // TODO configuration check exist? file? dir?

  //  unmanagedResources ++= (file("./_posts") ** "*.md").get
  //  unmanagedSources ++= (file("_posts") ** "*.md").get

  val MARKDOWN_EXTENSION = ".md"
  watchSources <++= baseDirectory map { path => ((path / "_posts") ** MARKDOWN_EXTENSION).get }

  override lazy val settings = Seq(
    commands ++= Seq(
      build,
      serve
    )
  )

  def log(command: String, options: Seq[String] = Nil): Unit = {
    println("[Info]Command: %s %s".format(command, options.mkString(" ")))
  }

  // build
  lazy val build = Command.command("build") { state =>
    log("build")
    println(state)

    //    new SiteBuild(directoryStructure)
    state
  }

  // serve
  lazy val serve = Command.command("serve") { state =>
    log("serve")
    state
  }

  case class Author(authorInfo: Pair[Symbol, String]*)

  case class Menu(items: Pair[String, String]*)
}

