package jp.modal.soul.tonbeji.build

import java.util

/**
 * Created by imae on 2015/08/08.
 */
class PostFileParser(fileBody: String) extends FrontMatter with Markdown with Liquid {
  private final val TRIPLE_DASHED_LINE = "---"

  def parsed = {
    val lines = fileBody.split("\n")
    val start = lines.indexOf(TRIPLE_DASHED_LINE) + 1
    val end = lines.drop(start).indexOf(TRIPLE_DASHED_LINE) + start

    val frontMatter = frontMatterMap(lines.drop(start).take(end - start))
    val contents = parsedMarkdown(lines.drop(end + 1).mkString("\n"))
    frontMatter.put("contents", contents)
    frontMatter
  }

}
