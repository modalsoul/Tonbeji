package jp.modal.soul.tonbeji.build

import com.tristanhunt.knockoff.DefaultDiscounter._

/**
 * Created by imae on 2015/08/09.
 */
trait Markdown {
  def parsedMarkdown(str: String) = toXHTML(knockoff(str)).toString()
}
