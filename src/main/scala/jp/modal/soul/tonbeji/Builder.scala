package jp.modal.soul.tonbeji

import java.io.File
import org.fusesource.scalate.TemplateEngine

import scala.xml.Node

/**
 * Created by imaemasatoshi on 2014/10/05.
 */
class Builder(directoryStructure: DirectoryStructure) {
  val templateEngine = new TemplateEngine()
  templateEngine.workingDirectory = new File("./tmp")

  def buildPosts = {
    for (post <- directoryStructure.posts) {
      buildPost(post, directoryStructure.destinationDir.getPath)
    }
    true
  }

  def buildPost(post: PostFile, destination: String) {
    val html: Node = post.contents
    FileOperator.write(new File("%s/%s/index.html".format(destination, post.path)))(html.toString())
  }
}
