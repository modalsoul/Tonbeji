package jp.modal.soul.tonbeji

import java.io.File

/**
 * Created by imae on 2015/01/17.
 */
case class PostFile(file: File,
                    layout: Layout,
                    title: String,
                    tags: Seq[String],
                    categories: Seq[String],
                    others: Map[String, String]) extends MarkdownBinding {

  val body = PostFile.getBody(file)
  override val settings: Map[String, Object] = Map(
    PostFile.LAYOUT -> layout,
    PostFile.TITLE -> title,
    PostFile.TAGS -> tags,
    PostFile.CATEGORIES -> categories) ++ others

  /**
   * Returns posted date from post filename.
   * @return
   */
  def postDate: String = {
    """\d{4}-\d{2}-\d{2}""".r.findFirstIn(file.getName) match {
      case Some(yyyymmdd) => yyyymmdd.replace("-", "/")
      case _              => throw PostFile.PostFileNameException()
    }
  }

  /**
   * Returns title from post filename.
   * @return
   */
  def fileTitle: String = {
    file.getName.split("""\d{4}-\d{2}-\d{2}-""", 2) match {
      case split if split.length > 2 => split.last
      case _                         => throw PostFile.PostFileNameException()
    }
  }

  /**
   * Returns path that is part of post uri.
   * @return Path that is composed by category, posted date and title.
   */
  def path: String = {
    categories match {
      case Nil => "%s/%s".format(postDate, fileTitle)
      case _   => "%s/%s/%s".format(categories.head, postDate, fileTitle)
    }
  }
}

class PostFileFormatException() extends SettingFormatException

object PostFile extends FileParser {
  case class PostFileParseException() extends Exception
  case class PostFileNameException() extends Exception

  case class NoSuchLayoutException() extends Exception
  case class TitleNotFoundException() extends Exception
  case class TagsNotFoundException() extends Exception
  case class CategoriesNotFoundException() extends Exception

  val MARKDOWN_EXTENSION = ".md"
  val LAYOUT = "layout"
  val TITLE = "title"
  val TAGS = "tags"
  val CATEGORIES = "categories"

  override val settingKeys: Map[String, Boolean] = Map(
    LAYOUT -> false,
    TITLE -> false,
    TAGS -> true,
    CATEGORIES -> true)

  def getLayout(layouts: Map[String, Layout], settings: Map[String, Object]) = {
    try {
      layouts.get(settings.get(LAYOUT).get.asInstanceOf[String]).get
    } catch {
      case e: Exception => throw NoSuchLayoutException()
    }
  }

  def getTitle(settings: Map[String, Object]) = {
    try {
      settings.get(TITLE).get.asInstanceOf[String]
    } catch {
      case e: Exception => throw TitleNotFoundException()
    }
  }

  def getTags(settings: Map[String, Object]) = {
    try {
      settings.get(TAGS).get.asInstanceOf[Seq[String]]
    } catch {
      case e: Exception => throw TagsNotFoundException()
    }
  }

  def getCategories(settings: Map[String, Object]) = {
    try {
      settings.get(CATEGORIES).get.asInstanceOf[Seq[String]]
    } catch {
      case e: Exception => throw CategoriesNotFoundException()
    }
  }

  def apply(file: File, layouts: Map[String, Layout]): PostFile = {
    val settings = getSettings[PostFileFormatException](file)

    try {
      val layout = getLayout(layouts, settings)
      val title = getTitle(settings)
      val tags = getTags(settings)
      val categories = getCategories(settings)
      val others = settings.filterKeys(k => !settingKeys.keySet.contains(k)).map {
        item => item._1 -> item._2.toString
      }
      new PostFile(file, layout, title, tags, categories, others)
    } catch {
      case e: Exception => throw PostFileParseException()
    }
  }
}
