package jp.modal.soul.tonbeji.build

import liqp.Template

/**
 * Created by imae on 2015/08/09.
 */
trait Liquid {
  def parse(src: String, map: java.util.HashMap[String, Object]) = {
    val template = Template.parse(src)
    template.render(map)
  }
}
