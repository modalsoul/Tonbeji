package jp.modal.soul.tonbeji.entity

import collection.JavaConversions._
import java.util.ArrayList

case class Post(
    name: String,
    layout: Option[String],
    title: String,
    tags: List[String],
    categories: List[String],
    settings: java.util.HashMap[String, Object]) {

  private final val fileNameReg = """([0-9]+)-([0-9]+)-([0-9]+)-(.*)\.md""".r

  def permalink = {
    val fileNameReg(year, month, day, title) = name
    s"/${Seq(categories.mkString("/"), year, month, day, title).mkString("/")}/"
  }
}

object Post {
  def apply(name: String, map: java.util.HashMap[String, Object]): Post = {
    Post(
      name = name,
      layout = Option(map.getOrDefault("layout", null)).map { case o: Object => o.toString },
      title = map.getOrDefault("title", "").toString,
      tags = map.getOrDefault("tags", new ArrayList[String]()).asInstanceOf[ArrayList[String]].toList,
      categories = map.getOrDefault("categories", new ArrayList[String]()).asInstanceOf[ArrayList[String]].toList,
      settings = map
    )
  }
}