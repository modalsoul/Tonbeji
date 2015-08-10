package jp.modal.soul.tonbeji

import org.fusesource.scalate.support.ScalaCompiler
import org.fusesource.scalate.{ TemplateSource, TemplateEngine }

import scala.collection._

/**
 * Created by imaemasatoshi on 2014/10/05.
 */
class SiteBuilder(directoryStructure: DirectoryStructure) {
  def buildPosts = {
    for (post <- directoryStructure.posts) {
      buildPost(post, directoryStructure.destinationDir.getPath)
    }
    true
  }

  def buildPost(post: PostFile, destination: String) {
    //    FileOperator.write(new File("%s/%s/index.html".format(destination, post.path)))(html.toString())
  }

  def buildLayout(layout: Layout, contents: String): String = {
    val templateEngine = new TemplateEngine()
    templateEngine.workingDirectory = directoryStructure.workingDir
    try {
      layout.hasParentLayout match {
        // no parent
        case false =>
          val t = TemplateSource.fromText("tmp.mustache", layout.body)
          val fuga = layout.settings + ("contents" -> contents)
          templateEngine.layout(t, fuga)
        case true =>
          // has parent
          println("contents = " + contents)
          val t = TemplateSource.fromText("tmp.mustache", layout.body)
          val fuga = layout.settings + ("contents" -> contents)

          buildLayout(layout.parentLayout, templateEngine.layout(t, fuga))
      }
    } finally {
      templateEngine.compiler.shutdown()
    }
  }
}
