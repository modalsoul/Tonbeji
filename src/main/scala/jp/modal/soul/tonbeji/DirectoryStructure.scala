package jp.modal.soul.tonbeji

import java.io.File

import jp.modal.soul.Tonbeji

/**
 * Created by imaemasatoshi on 2014/11/20.
 */
/**
 * DirectoryStructure
 * @param postsDir
 * @param includesDir
 * @param destinationDir
 * @param layouts
 */
case class DirectoryStructure(postsDir: PostDir,
                              includesDir: File,
                              destinationDir: File,
                              layouts: Map[String, Layout]) {
  val posts = postsDir.postFiles
}
object DirectoryStructure {
  val dotSlash = (name: String) => "./%s".format(name)
  def apply(postsDirName: String, layoutsDirName: String, includesDirName: String, destinationDirName: String): DirectoryStructure = {
    val layoutsDir = new File(dotSlash(layoutsDirName))
    val layouts = getLayouts(layoutsDir)
    DirectoryStructure(
      PostDir(dotSlash(postsDirName), layouts),
      new File(dotSlash(includesDirName)),
      new File(dotSlash(destinationDirName)),
      layouts)
  }

  /**
   * Returns all of layouts.
   * @param dir layouts root dir.
   * @return The name of layout as key and layout as value.
   */
  def getLayouts(dir: File) = {
    dir.listFiles().toSeq.filter(_.getName.endsWith(".html")).map {
      file => file.getName.substring(0, file.getName.lastIndexOf(".")) -> Layout(file)
    }.toMap
  }
}

/**
 * Directory that is placed markdown files.
 * @param dir
 * @param postFiles
 */
case class PostDir(dir: File, postFiles: Seq[PostFile])

object PostDir {
  def apply(dirPath: String, layouts: Map[String, Layout]): PostDir = {
    val dir = new File(dirPath)
    val files = dir.listFiles().filter(f => f.isFile && f.getName.endsWith(Tonbeji.MARKDOWN_EXTENSION))
    val posts = files.map(f => PostFile(f, layouts))
    PostDir(dir, posts)
  }
}

/**
 * Template that wrap posts.
 * @param file
 */
case class Layout(file: File) {

}

