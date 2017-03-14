import groovy.transform.TupleConstructor

class GroovyConstructorTest {

    @TupleConstructor(force = true)
    static class TestConstructorTest {
        final String name
        final int id



        String build() {
            return "${name} - ${id}"
        }
    }
    static void main(String... args) {
        TestConstructorTest test = new TestConstructorTest()
        println(test.build())
    }
}
