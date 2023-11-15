//Определение интерфейса
interface TestRunner {
    fun < T : TestSteps> runTest(steps: T, test: () -> Unit)
}

interface TestSteps {
    fun beforeTest() {
        println("Running beforeTest")
    }

    fun afterTest() {
        println("Running afterTest")
    }
}

// Реализация класса
class SimpleTestRunner : TestRunner {
    override fun <T : TestSteps> runTest(steps: T, test: () -> Unit) {
        try {
            steps.beforeTest()
            test()
        } finally {
            steps.afterTest()
        }
    }
}

// Запуск тестов
fun main() {
    val testRunner = SimpleTestRunner()

    val testSteps = object : TestSteps {
        override fun beforeTest() {
            println("Custom beforeTest")
        }

        override fun afterTest() {
            println("Custom afterTest")
        }
    }

    testRunner.runTest(testSteps) {
        println("Running actual test")
    }
}