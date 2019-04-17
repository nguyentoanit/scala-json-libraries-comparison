package example
import java.io.{FileInputStream, FileOutputStream}
import java.util.zip.{GZIPInputStream, GZIPOutputStream}

import com.github.plokhotnyuk.jsoniter_scala.macros._
import com.github.plokhotnyuk.jsoniter_scala.core._

object Jsoniter extends App {
  val startTime = System.currentTimeMillis

  case class OldReport(campaignId: String, campaignName: String)
  case class NewReport(reportDate: String, profileId: Long, report: Array[OldReport])

  implicit val codec: JsonValueCodec[Array[OldReport]] = JsonCodecMaker.make(CodecMakerConfig())
  implicit val codec2: JsonValueCodec[NewReport] = JsonCodecMaker.make(CodecMakerConfig())
  val fis: FileInputStream = new FileInputStream("old.json.gz")
  val gis: GZIPInputStream = new GZIPInputStream(fis)

  val report = readFromStream(gis)(codec)
  val dataToCompress = NewReport("20190101", 123, report)

  val fos: FileOutputStream = new FileOutputStream("new.json.gz")
  val gos: GZIPOutputStream = new GZIPOutputStream(fos)
  writeToStream(dataToCompress, gos)(codec2)
  gos.close()

  val stopTime = System.currentTimeMillis
  println(stopTime - startTime)
}
