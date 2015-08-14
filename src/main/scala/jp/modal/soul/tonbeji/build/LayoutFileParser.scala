package jp.modal.soul.tonbeji.build

import jp.modal.soul.tonbeji.entity.Layout

class LayoutFileParser(fileName: String, override val fileBody: String) extends Parser with FrontMatter {
  def parsed = {
    val frontMatter = frontMatterMap(frontMatterPart)
    Layout(fileName, contentsPart, frontMatter)
  }
}