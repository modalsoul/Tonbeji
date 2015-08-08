package jp.modal.soul.tonbeji

import java.io.File

/**
 * Template that wrap posts.
 * @param file
 */
case class Layout(file: File, settings: Map[String, Object], hasParentLayout: Boolean) {
  val body: String = Layout.getBody(file)

  def parentLayout: Layout = {
    val layoutName = settings.get(Layout.LAYOUT).get.asInstanceOf[String]
    Layout(new File("%s/%s%s".format(file.getParent, layoutName, Layout.LAYOUT_FILE_EXTENSION)))
  }

  def contents(f: String => String): String = f(body)

  //  override val settings: Map[String, Object] = layoutSettings + ("contents" -> markdownContents)
}

case class LayoutFormatException() extends SettingFormatException

object Layout extends FileParser {
  val LAYOUT_FILE_EXTENSION = ".html"
  val LAYOUT = "layout"
  override val settingKeys: Map[String, Boolean] = Map(LAYOUT -> false)

  def apply(file: File): Layout = {
    val settings = getSettings[LayoutFormatException](file)
    Layout(file, settings, settings.keys.exists(_ == LAYOUT))
  }
}

