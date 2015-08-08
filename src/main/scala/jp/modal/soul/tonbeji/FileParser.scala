package jp.modal.soul.tonbeji

import java.io.File

import scala.collection.mutable
import scala.collection.mutable.{ Map => MutableMap }
import scala.reflect.ClassTag

/**
 * Created by imae on 2015/01/18.
 */
class SettingFormatException extends Exception

trait FileParser {
  val SEPARATOR = "---"

  val settingKeys: Map[String, Boolean]

  /**
   * Returns setting.
   * @param key
   * @param value
   * @return The name of setting item name as key and value of setting item as value.
   */
  def parseSetting(key: String, value: String) = {
    val toCollection = (s: String) => s.split("""\s+""").toSeq
    settingKeys.get(key) match {
      case Some(true) => key -> toCollection(value)
      case _          => key -> value
    }
  }

  /**
   * Returns settings.
   * @param file
   * @return The name of setting item name as key and value of setting item as value.
   */
  def getSettings[T <: SettingFormatException: ClassTag](file: File) = {
    val settingKeyValues = FileOperator.readAndExecute(file) { buffer =>
      var line = buffer.readLine()
      import scala.util.control.Breaks.{ break, breakable }
      breakable {
        while (line != null) {
          if (line.trim != SEPARATOR) line = buffer.readLine()
          else break()
        }
      }
      if (line == null) {
        throw implicitly[ClassTag[T]].runtimeClass.newInstance().asInstanceOf[T]
      }
      line = buffer.readLine()
      val keyValues = MutableMap[String, String]()
      while (line.trim != SEPARATOR && line != null) {
        if (line.matches(""".*:.*""")) {
          val split = line.split(":", 2)
          keyValues.put(split.head.trim, split.last.trim)
        }
        line = buffer.readLine()
      }
      keyValues.toMap
    }

    for ((key, value) <- settingKeyValues) yield {
      parseSetting(key, value)
    }
  }

  /**
   * Returns body part.
   * @return Markdown body string.
   */
  def getBody(file: File): String = {
    FileOperator.readAndExecute(file) { buffer =>
      var line = buffer.readLine()
      val bodyBuilder = new mutable.StringBuilder()
      var separatorCount = 0
      while (line != null) {
        if (separatorCount > 1) bodyBuilder.append("%s\n".format(line))
        if (line.trim == PostFile.SEPARATOR) separatorCount = separatorCount + 1
        line = buffer.readLine()
      }
      bodyBuilder.result()
    }
  }
}
