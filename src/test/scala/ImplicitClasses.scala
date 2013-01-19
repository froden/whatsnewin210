import org.scalatest.FunSuite

class ImplicitClasses extends FunSuite {

  test("Legg til metode på String") {
    //fixme
    implicit class SuperString(str: String) {
      def antallAv(bokstav: Char) = str.count(_ == bokstav)
    }

    assert("Hei på deg".antallAv('e') == 2)
  }
}
