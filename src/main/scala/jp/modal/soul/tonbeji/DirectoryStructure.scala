package jp.modal.soul.tonbeji

import java.io.File

/**
 * Created by imaemasatoshi on 2014/11/20.
 */
/**
 * DirectoryStructure
 * @param postsDir
 * @param layoutsDir
 * @param includesDir
 * @param destinationDir
 */
case class DirectoryStructure(postsDir:PostDir, layoutsDir:File, includesDir:File, destinationDir:File)
object DirectoryStructure {
  val dotSlash = (name:String) => "./%s".format(name)
  def apply(postsDirName:String, layoutsDirName:String, includesDirName:String, destinationDirName:String):DirectoryStructure = {
    DirectoryStructure(
      PostDir(dotSlash(postsDirName)),
      new File(dotSlash(layoutsDirName)),
      new File(dotSlash(includesDirName)),
      new File(dotSlash(destinationDirName))
    )
  }
}

case class PostDir(dir:File) {
  private val files = dir.listFiles().filter(f => f.isFile && f.getName.endsWith(".md"))
  val posts = files.map(PostFile(_))

}
object PostDir {
  def apply(dirPath:String):PostDir = PostDir(new File(dirPath))
}

case class PostFile(file:File,
                    layout:Layout,
                    title:String,
                    tags:Array[String],
                    categories:Array[String],
                    others:Map[String,String]) {

}
object PostFile {
  def apply(file:File):PostFile = {
    PostFile(file, Layout(), "", Array(""), Array(""), Map())
  }
}

case class Layout()