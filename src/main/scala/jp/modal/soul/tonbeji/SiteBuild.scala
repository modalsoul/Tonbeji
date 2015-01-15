package jp.modal.soul.tonbeji

import java.io.File
import org.fusesource.scalate.TemplateEngine

/**
 * Created by imaemasatoshi on 2014/10/05.
 */
class SiteBuild(directoryStructure:DirectoryStructure) {
  val templateEngine = new TemplateEngine()
  templateEngine.workingDirectory = new File("./tmp")

  def buildPosts = {
//    for(postFile <- directoryStructure.postsDir.posts) {
//
//    }


    true
  }
}
