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
                              layouts: Map[String, Layout],
                              workingDir: File) {
  val posts = postsDir.postFiles
}
object DirectoryStructure {
  //  val dotSlash = (name: String) => "./%s".format(name)
  def apply(postsDirPath: String,
            layoutsDirPath: String,
            includesDirPath: String,
            destinationDirPath: String,
            workingDirPath: String): DirectoryStructure = {
    val layoutsDir = new File(layoutsDirPath)
    val layouts = getLayouts(layoutsDir)
    DirectoryStructure(
      PostDir(postsDirPath, layouts),
      new File(includesDirPath),
      new File(destinationDirPath),
      layouts,
      new File(workingDirPath))
  }

  /**
   * Returns all of layouts.
   * @param dir layouts root dir.
   * @return The name of layout as key and layout as value.
   */
  def getLayouts(dir: File): Map[String, Layout] = {
    dir.listFiles().toSeq.filter(_.getName.endsWith(Layout.LAYOUT_FILE_EXTENSION)).map {
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
    println("dir.exists() = " + dir.exists())
    val files = dir.listFiles().filter(f => f.isFile && f.getName.endsWith(PostFile.MARKDOWN_EXTENSION))
    val posts = files.map(f => PostFile(f, layouts))
    PostDir(dir, posts)
  }
}

