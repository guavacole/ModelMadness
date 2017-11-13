package henry.enrichment

import scala.reflect.runtime._
import scala.reflect.runtime.universe._


case class Test(lol: Int, lmao: Option[Int])

object Enrichment {
  implicit class EnrichedModel[A](value: A) {

    def isEnriched: Boolean = {
      val mirror = currentMirror
      val accessors = mirror.classSymbol(this.getClass).toType.members.collect {
        case field: MethodSymbol if field.isVal && field.isPublic => field
      }
      accessors.foreach(println(_))
      true
    }
  }
}


object app extends App {
  import Enrichment._
  override def main (args: Array[String] ): Unit = {
    val yo = Test(1, Some(2))
    yo isEnriched
  }

}

