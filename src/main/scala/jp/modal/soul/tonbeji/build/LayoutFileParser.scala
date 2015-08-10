package jp.modal.soul.tonbeji.build

class LayoutFileParser(override val fileBody:String) extends Parser with FrontMatter with Liquid {
  def parsed = {
    val frontMatter = frontMatterMap(frontMatterPart)
    parse(contentsPart, frontMatter)
  }
}