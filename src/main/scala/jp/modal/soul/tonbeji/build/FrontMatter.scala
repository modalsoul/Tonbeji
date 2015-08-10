package jp.modal.soul.tonbeji.build

import java.util

/**
 * Created by imae on 2015/08/08.
 */
trait FrontMatter {
  private final val TRIPLE_DASHED_LINE = "---"

  def frontMatterMap(lines: Seq[String]): java.util.HashMap[String, Object] = {
    val map = new util.HashMap[String, Object]()
    lines.map(parseLine).foreach { case (key, value) => map.put(key, value) }
    map
  }

  private final val keyValueReg = """[\s]*(.*):[\s]*(.*)""".r
  private final val arrayReg = """\[(.*)\]""".r
  private final val commaReg = """,[\s]*"""

  private def parseLine(line: String): (String, Object) = {
    val keyValueReg(k, v) = line
    (k.trim, arrayReg.findFirstIn(v).fold[Object](v.trim) {
      case arrayReg(array) =>
        val list = new util.ArrayList[String]()
        array.split(commaReg).foreach(v => list.add(v.trim))
        list
    })
  }
}
