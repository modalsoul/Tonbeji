package jp.modal.soul.tonbejitest

import java.io.File

import jp.modal.soul.tonbeji.FileOperator
import org.scalatest.{ Matchers, FlatSpec }

import scala.collection.mutable.ArrayBuffer

/**
 * Created by imae on 2015/01/17.
 */
class FileReaderSpec extends FlatSpec with Matchers {
  "readAndExecute" should "success" in {
    val f = new File("./src/test/resources/ok/posts/2014-01-16-test.md")
    val settingLines = FileOperator.readAndExecute(f) { buffer =>
      val lines = ArrayBuffer[String]()
      lines.append(buffer.readLine())
      var line = buffer.readLine()
      while (line != null) {
        lines.append(line)
        line = buffer.readLine()
      }
      lines
    }
    settingLines.length should be(12)
  }

}
