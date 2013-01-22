import org.scalatest.FunSuite

class ImplicitClassesTest extends FunSuite {

  test("Legg til metode på String") {
    //fixme

    assert("Hei på deg".antallAv('e') == 2)
  }
}
