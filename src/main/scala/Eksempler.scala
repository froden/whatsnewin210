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

  //Temp$.MODULE$.extension$toHexString(3)


}
