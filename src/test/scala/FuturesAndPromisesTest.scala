import org.scalatest.FunSuite
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FuturesAndPromisesTest extends FunSuite {

  test("skal kjøre i parallell og samle opp feil og resultat") {

    val start = System.currentTimeMillis()

    //Tar 1 sek å kjøre
    def skjørOgTreg(i: Int) = {
      Thread.sleep(1000)
      if (i < 8) i * 2 else throw new Exception(i + " feilet")
    }

    val futures = for (i <- 1 to 10) yield skjørOgTreg(i) //FIXME



    //Total kjøretid må være under 3 sek (potensielt > 10 sek)
    assert(System.currentTimeMillis() - 3000 < start)
    assert(result._1 === Seq("8 feilet", "9 feilet", "10 feilet"))
    assert(result._2 === Seq(2, 4, 6, 8, 10, 12, 14))
  }
}
