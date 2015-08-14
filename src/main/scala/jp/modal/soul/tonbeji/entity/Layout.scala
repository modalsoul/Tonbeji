package jp.modal.soul.tonbeji.entity

import jp.modal.soul.tonbeji.build.Liquid

/**
 * Created by imae on 2015/08/11.
 */
case class Layout(name: String, layout: Option[String], body: String, settings: java.util.HashMap[String, Object]) extends Liquid {
  def bind(otherSettings: java.util.HashMap[String, Object]) = {
    otherSettings.putAll(settings)
    parse(body, otherSettings)
  }
}

object Layout {
  def apply(name: String, body: String, map: java.util.HashMap[String, Object]): Layout = {
    Layout(
      name = name,
      layout = Option(map.getOrDefault("layout", null)).map { case o: Object => o.toString },
      body = body,
      settings = map
    )
  }
}
