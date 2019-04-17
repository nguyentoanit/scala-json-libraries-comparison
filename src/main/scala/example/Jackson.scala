package example
import java.io.{FileInputStream, FileOutputStream}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

object Jackson extends App {
  val startTime = System.currentTimeMillis

  val mapper = new ObjectMapper()
  mapper.registerModule(DefaultScalaModule)
  case class OldReport(campaignId: String, campaignName: String)
  case class NewReport(reportDate: String, profileId: Long, report: Array[OldReport])

  val fis: FileInputStream = new FileInputStream("old.json.gz")
  val gis: GZIPInputStream = new GZIPInputStream(fis)
  val report = mapper.readValue(gis, classOf[Array[OldReport]])

  val dataToCompress = NewReport("20190101", 123, report)

  val fos: FileOutputStream = new FileOutputStream("new.json.gz")
  val gos: GZIPOutputStream = new GZIPOutputStream(fos)

  mapper.writeValue(gos, dataToCompress)
  gos.close()

  val stopTime = System.currentTimeMillis
  println(stopTime - startTime)
}
