package jp.modal.soul.tonbeji

import com.tristanhunt.knockoff.DefaultDiscounter._

/**
 * Created by imae on 2015/01/19.
 */
trait MarkdownBinding {
  val settings: Map[String, Object]
  val body: String
  def markdownContents: String = toXHTML(knockoff(body)).toString()
  lazy val bindings = settings + ("contents" -> markdownContents)
}
