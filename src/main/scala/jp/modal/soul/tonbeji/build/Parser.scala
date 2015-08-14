package jp.modal.soul.tonbeji.build

/**
 * Created by imaemasatoshi on 2015/08/11.
 */
trait Parser {
  val fileBody: String
  val lines = fileBody.split("\n")
  protected final val TRIPLE_DASHED_LINE = "---"
  protected final val start = lines.indexOf(TRIPLE_DASHED_LINE) + 1
  protected final val end = lines.drop(start).indexOf(TRIPLE_DASHED_LINE) + start
  protected val frontMatterPart = lines.drop(start).take(end - start)
  protected val contentsPart = lines.drop(end + 1).mkString("\n")
}
