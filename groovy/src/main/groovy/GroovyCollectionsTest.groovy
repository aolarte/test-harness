class GroovyCollectionsTest {
    static void main(String... args) {
        List<String> list = ["a","b","c"]
        list = list.collect(){it.toUpperCase()}
        println(list)
    }
}
