import org.scalatest.FunSuite
import spray.json.DefaultJsonProtocol
import util.parsing.json.{JSON, JSONObject}

class StringInterpolationTest extends FunSuite {

  test("enkel bruk") {
    val navn = "Frode"
    val høyde = 187

    val str: String = ???

    val ref = "Jeg heter Frode og er 187 cm lang"
    assert(str === ref)
  }

  test("med formatering") {
    val navn = "Frode"
    val høyde = 187.34

    val str: String = ???

    val ref = "Jeg heter Frode og er 187.3 cm lang"
    assert(str === ref)
  }

  test("avansert") {

    case class Person(navn: String, alder: Int)

    object MyJsonProtocol extends DefaultJsonProtocol {
      implicit val personformat = jsonFormat2(Person)
    }

    import MyJsonProtocol._
    import spray.json._

    //hint: JsonParser(str).convertTo[Person]
    //FIXME

    val navn = "frode"
    val alder = 32

    val frode: Person = json"""{"navn": "${navn}", "alder": $alder}"""
    assert(frode === Person(navn, alder))
  }
}
