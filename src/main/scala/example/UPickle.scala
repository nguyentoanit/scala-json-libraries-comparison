package example

import java.io.{FileInputStream, FileOutputStream}
import java.nio.charset.Charset
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import scala.io.Source

object UPickle extends App {
  val startTime = System.currentTimeMillis

  val fis: FileInputStream = new FileInputStream("old.json.gz")
  val gis: GZIPInputStream = new GZIPInputStream(fis)

  val reportString: String = Source.fromInputStream(gis).mkString

  val dataToCompress: ujson.Value = ujson.Obj("reportDate" -> "20190101", "profileId" -> 123, "data" -> ujson.read(reportString))
  val fos: FileOutputStream = new FileOutputStream("new.json.gz")
  val gos: GZIPOutputStream = new GZIPOutputStream(fos)

  gos.write(dataToCompress.toString.getBytes(Charset.forName("UTF-8")))
  gos.close()

  val stopTime = System.currentTimeMillis
  println(stopTime - startTime)
}
