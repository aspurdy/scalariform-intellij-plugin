
//indentPackageBlocks
package foo {
  package bar {
    class Baz
  }
}

//alignParameters: Align class/function parameters (modifiers and name, type, and defaults) in three columns.
case class Person(
  name:             String,
  age:              Int,
  astrologicalSign: String         = "",
  shoeSize:         Int            = 0,
  favoriteColor:    java.awt.Color = java.awt.Color.green
)

class Test {
  // AlignArguments
  Person(
    name = "Name",
    age = 10,
    shoeSize = 5
  )

  //preserveDanglingCloseParenthesis
  val p1 = Person(
    name = "Name",
    age = 10,
    shoeSize = 5
  )
  val p2 = Person(name = "Name", age = 10, shoeSize = 5)

  //alignSingleLineCaseStatements
  val a1 = "2" match {
    case "1234" => 1
    case "3"    => 2
    case "two"  => 3
  }

  //alignSingleLineCaseStatements.maxArrowIndent
  val a2 = Some((1, 2)) match {
    case Some((wibble, wobble)) if wibble + wibble > wobble * wibble ⇒ 1
    case None => 2
  }

  //compactControlReadability
  val a3 = if (1 == 2) {
    println()
  } else if (2 == 3) {
    add(1, 2)
  } else {
    int2Integer(3)
  }

  try {
    add(1, 3)
  } catch {
    case _ ⇒ add(1, 1)
  } finally {
    println()
  }

  //compactStringConcatenation
  val name = "test"
  val next = "Hello " + name + "!"

  //doubleIndentClassDeclaration
  trait Entity
  trait Logging
  trait Identifiable
  class Person(
    name:             String,
    age:              Int,
    astrologicalSign: String,
    shoeSize:         Int,
    favoriteColor:    java.awt.Color
  )
      extends Entity
      with Logging
      with Identifiable
      with Serializable {
    def firstMethod: Int = {
      1
    }
  }

  //formatXml
  <hei>
    <der id="0"/>
    <level>
      <next/>
    </level>
  </hei>

  //indentLocalDefs
  //multilineScaladocCommentsStartOnFirstLine
  //placeScaladocAsterisksBeneathSecondAsterisk
  class A {
    /**
      * This method applies f to each
      * element of the given list.
      */
    def find(something: String): Unit = {
      val x = "..."
      def find0(some: String): Unit = {
        val b = 3
      }
      find0("...")
    }
  }

  //preserveSpaceBeforeArguments
  stack.pop() should equal(2)

  //rewriteArrowSymbols
  for (n ← 1 to 10) n % 2 match {
    case 0 => println("even")
    case 1 => println("odd")
  }

  //spaceBeforeColon
  def add(a: Int, b: Int): Int = a + b

  //spaceInsideBrackets
  val a = Seq[String]

  //spaceInsideParentheses
  def some(args: Array[String]): Unit = ???

  //spacesWithinPatternBinders
  "nei" match {
    case list @ List(_, elem, _) => println("matching:" + list)
    case _                       =>
  }
}
