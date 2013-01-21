import io.Source
import language.implicitConversions

object Implicit1 extends App {

  class Folkelig(tall: Int) {
    def pluss(i: Int) = tall + i
  }

  implicit def intToFolkelig(tall: Int): Folkelig = new Folkelig(tall)

  println(2 pluss 2)
}

object Implicit2 extends App {

  implicit class Folkelig(tall: Int) {
    def pluss(i: Int) = tall + i
  }

  println(2 pluss 2)
}

object StringInt extends App {
  val temp = -10.2
  val værmelding1 = s"Det er $temp grader i dag"
  val værmelding2 = f"Det er $temp%.2f grader i dag"
}

object ValueClass extends App {

  class Temp(val t : Int) extends AnyVal {
    def +(other: Temp): Temp = new Temp(t + other.t)
  }

  val t1 = new Temp(23)
  val t2 = new Temp(13)
  val t3 = t1 + t2

  //Temp$.MODULE$.extension$plus(23, 13)
}

object FutureEx extends App {
  import scala.concurrent._
  import scala.concurrent.duration._
  import ExecutionContext.Implicits.global

  def load(filename: String) = future {
    Source.fromInputStream(getClass.getResourceAsStream("/" + filename)).getLines()
  }

  val data = for {
    keys <- load("keys.txt")
    values <- load("values.txt")
  } yield keys.zip(values).toMap

  data.onSuccess {
    case map => println(map)
  }

  Await.result(data, Duration.Inf)
}

object DynamicEks extends App {
  import language.dynamics

  object Props extends Dynamic {
    val data = scala.collection.mutable.Map[String, Any]()

    def updateDynamic(key: String)(value: Any) = data(key) = value
    def selectDynamic(key: String) = data.get(key)
  }

  Props.navn = "frode"
  Props.alder = 32
  println(Props.navn)
  println(Props.alder)
}
