package jp.modal.soul.tonbeji.setting

import java.util

import sbt._

/**
 * Created by imae on 2015/08/15.
 */

trait GlobalSetting extends Setting {
  final val productionUrl = settingKey[String]("production url")
  final val title = settingKey[String]("title")
  final val author = settingKey[Seq[(String, String)]]("author")
  final val googleAnalytics = settingKey[String]("googleAnalytics")
  final val mixPanel = settingKey[String]("mixPanel")
  final val siteMenu = settingKey[Seq[(String, String)]]("menu")

  def get()(implicit state: State): java.util.HashMap[String, Object] = {
    val map = new util.HashMap[String, Object]()
    val authorMap = new util.HashMap[String, String]()
    getSetting(author).foreach(_.foreach { case (key, value) => authorMap.put(key, value) })
    map.put("author", authorMap)
    map
  }
}

trait Setting {
  private[setting] def getSetting[T](key: SettingKey[T])(implicit state: State): Option[T] = {
    val extracted = Project.extract(state)
    key in extracted.currentRef get (extracted.structure.data)
  }

  def get()(implicit state: State): java.util.HashMap[String, Object]
}