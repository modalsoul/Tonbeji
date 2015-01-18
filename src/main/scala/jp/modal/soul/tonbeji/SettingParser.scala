package jp.modal.soul.tonbeji

import java.io.File

import scala.collection.mutable.{ Map => MutableMap }
import scala.reflect.ClassTag

/**
 * Created by imae on 2015/01/18.
 */
class SettingFormatException extends Exception

trait SettingParser {
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
}
