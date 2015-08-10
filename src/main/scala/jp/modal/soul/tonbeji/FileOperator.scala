package jp.modal.soul.tonbeji

import java.io._

import jp.modal.soul.tonbeji.util.Loan

import scala.collection.mutable.ArrayBuffer

/**
 * Created by imae on 2015/01/17.
 */
object FileOperator {
  val CHARSET = "UTF-8"
  def readAndExecute[T](target: File)(f: BufferedReader => T) = {
    for {
      in <- Loan(new FileInputStream(target))
      reader <- Loan(new InputStreamReader(in, CHARSET))
      buffer <- Loan(new BufferedReader(reader))
    } {
      f(buffer)
    }
  }

  def write(target: File)(contents: String): Unit = {
    for {
      out <- Loan(new FileOutputStream(target))
      writer <- Loan(new OutputStreamWriter(out, CHARSET))
    } {
      writer.write(contents)
    }
  }
}
