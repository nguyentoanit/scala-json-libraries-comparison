package example

import java.io.{FileInputStream, FileOutputStream}
import java.nio.charset.Charset
import java.util.zip.{GZIPInputStream, GZIPOutputStream}
import scala.io.Source

import spray.json._

object Spray extends App {
  val startTime = System.currentTimeMillis

  val fis: FileInputStream = new FileInputStream("old.json.gz")
  val gis: GZIPInputStream = new GZIPInputStream(fis)

  val report: JsValue = Source.fromInputStream(gis).mkString.parseJson
  val dataToCompress = JsObject(
    "reportDate" -> JsString("20190101"),
    "profileId" ->JsNumber(123),
    "data" -> report
  )

  val fos: FileOutputStream = new FileOutputStream("new.json.gz")
  val gos: GZIPOutputStream = new GZIPOutputStream(fos)

  gos.write(dataToCompress.toString.getBytes(Charset.forName("UTF-8")))
  gos.close()

  val stopTime = System.currentTimeMillis
  println(stopTime - startTime)
}
