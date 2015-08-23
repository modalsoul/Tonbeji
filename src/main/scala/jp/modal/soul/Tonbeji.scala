package jp.modal.soul

import jp.modal.soul.tonbeji.setting.GlobalSetting
import sbt._
import sbt.Keys._

object Tonbeji extends Plugin with GlobalSetting {

  final val postsDirPath = "./src/test/resources/posts" // TODO build.sbt config
  final val layoutsDirPath = "./src/test/resources/layouts" // TODO build.sbt config
  final val includesDirPath = "./src/test/resources/includes" // TODO build.sbt config
  final val destinationDirPath = "./src/test/resources/public" // TODO build.sbt config
  final val workingDirPath = "./src/test/resources/working" // TODO build.sbt config

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

  //  def getSetting[T](key: SettingKey[T])(implicit state: State): Option[T] = {
  //    val extracted = Project.extract(state)
  //    key in extracted.currentRef get (extracted.structure.data)
  //  }

  def log(command: String, options: Seq[String] = Nil): Unit = {
    println("[Info]Command: %s %s".format(command, options.mkString(" ")))
  }

  // build
  lazy val build = Command.command("build") { implicit state =>
    log("build")

    val gs = this.asInstanceOf[GlobalSetting].get()
    println(gs)
    state
  }

  // serve
  lazy val serve = Command.command("serve") { state =>
    log("serve")
    state
  }

}

