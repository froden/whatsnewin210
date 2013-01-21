import org.scalatest.FunSuite
import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FuturesAndPromisesTest extends FunSuite {

  test("skal kjøre i parallell og samle opp feil og resultat") {

    val start = System.currentTimeMillis()

    def skjørOgTreg(i: Int) = {
      Thread.sleep(1000)
      if (i < 8) i * 2 else throw new Exception(i + " feilet")
    }

    val futures = for (i <- 1 to 10) yield future{skjørOgTreg(i)}

    val failures = for (f <- futures) yield {
      f.failed.map(t => Some(t.getMessage)).recover { case _ => None }
    }
    val successes = for (f <- futures) yield {
      f.map(Some(_)).recover { case _ => None }
    }

    val errorMsgsF = Future.sequence(failures).map(_.flatten)
    val resultsF = Future.sequence(successes).map(_.flatten)
    val combined = for {
      e <- errorMsgsF
      r <- resultsF
    } yield (e, r)

    val result = Await.result(combined, Duration.Inf)

    assert(System.currentTimeMillis() - 3000 < start)
    assert(result._1 === Seq("8 feilet", "9 feilet", "10 feilet"))
    assert(result._2 === Seq(2, 4, 6, 8, 10, 12, 14))
  }
}
