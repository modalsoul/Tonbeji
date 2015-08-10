package jp.modal.soul

import jp.modal.soul.tonbeji.{ SiteBuilder, DirectoryStructure }
import sbt._
import Keys._

object Tonbeji extends Plugin {

  final val productionUrl = settingKey[String]("production url")
  final val title = settingKey[String]("title")
  final val author = settingKey[Author]("author")
  final val googleAnalytics = settingKey[String]("googleAnalytics")
  final val mixPanel = settingKey[String]("mixPanel")
  final val siteMenu = settingKey[Menu]("menu")

  final val postsDirPath = "./src/test/resources/posts" // TODO build.sbt config
  final val layoutsDirPath = "./src/test/resources/layouts" // TODO build.sbt config
  final val includesDirPath = "./src/test/resources/includes" // TODO build.sbt config
  final val destinationDirPath = "./src/test/resources/public" // TODO build.sbt config
  final val workingDirPath = "./src/test/resources/working" // TODO build.sbt config
  final val directoryStructure = DirectoryStructure(postsDirPath, layoutsDirPath, includesDirPath, destinationDirPath, workingDirPath)

  // TODO configuration check exist? file? dir?

  //  unmanagedResources ++= (file("./_posts") ** "*.md").get
  //  unmanagedSources ++= (file("_posts") ** "*.md").get

  final val MARKDOWN_EXTENSION = ".md"
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

