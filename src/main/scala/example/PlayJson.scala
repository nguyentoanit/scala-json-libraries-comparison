package example

import java.io.{FileInputStream, FileOutputStream}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import play.api.libs.json._

object PlayJson extends App {
  val startTime = System.currentTimeMillis

  val fis: FileInputStream = new FileInputStream("old.json.gz")
  val gis: GZIPInputStream = new GZIPInputStream(fis)

  val report: JsValue = Json.parse(gis)
  val newReport: JsValue = Json.obj(
    "reportDate" -> "20190101",
    "profileId" -> 123,
    "data" -> report
  )

  val dataToCompress = Json.toBytes(newReport)

  val fos: FileOutputStream = new FileOutputStream("new.json.gz")
  val gos: GZIPOutputStream = new GZIPOutputStream(fos)

  gos.write(dataToCompress)
  gos.close()

  val stopTime = System.currentTimeMillis
  println(stopTime - startTime)
}
