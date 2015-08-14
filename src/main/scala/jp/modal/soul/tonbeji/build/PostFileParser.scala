package jp.modal.soul.tonbeji.build

import jp.modal.soul.tonbeji.entity.Post

/**
 * Created by imae on 2015/08/08.
 */
class PostFileParser(fileName: String, override val fileBody: String) extends Parser with FrontMatter with Markdown {
  def parsed = {
    val frontMatter = frontMatterMap(frontMatterPart)
    val contents = parsedMarkdown(contentsPart)
    frontMatter.put("contents", contents)
    Post(fileName, frontMatter)
  }
}
