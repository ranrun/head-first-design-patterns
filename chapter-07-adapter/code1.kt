/**
 * Adapter pattern
 *
 * The Adapater Pattern converts the interface of a class into another
 * interface the clients expect. Adapter lets classes work together that
 * couldn't otherwise becasue of incompatible interfaces.
 *
 * The Facade Pattern provides a unified interface to a set of interfaces in a
 * subsystem. Facade defines a higer-level interface that makes the
 * subsystem easier to use.
 *
 * Design Principle
 *
 * - Principle of Least Knowledge: talk only to your immediate friends.
 *
 */

interface Duck {
    fun quack()
    fun fly()
}

class MallardDuck : Duck {
    override fun quack() {
        println("Quack")
    }
    override fun fly() {
        println("I'm flying")
    }
}

interface Turkey {
    fun gobble()
    fun fly()
}

class WildTurkey : Turkey {
    override fun gobble() {
        println("Gobble gobble")
    }
    override fun fly() {
        println("I'm flying a short distance")
    }
}

class TurkeyAdapter(
    val turkey: Turkey
) : Duck {
    override fun quack() {
        turkey.gobble()
    }
    override fun fly() {
        List(5) {
            turkey.fly()
        }
    }
}

fun testDuck(duck: Duck) {
    duck.quack()
    duck.fly()
}

// $ kotlinc code1.kt -include-runtime -d code1.jar
// $ java -jar code1.jar DuckTestDrive
object DuckTestDrive {
    @JvmStatic
    fun main(args: Array<String>) {
        val duck = MallardDuck()

        val turkey = WildTurkey()
        val turkeyAdapter = TurkeyAdapter(turkey)

        println("The Turkey says...")
        turkey.gobble()
        turkey.fly()

        println("The Duck says...")
        testDuck(duck)

        println("The TurkeyAdapter says...")
        testDuck(turkeyAdapter)
    }
}