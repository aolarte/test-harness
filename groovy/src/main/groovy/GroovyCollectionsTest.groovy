class GroovyCollectionsTest {
    static void main(String... args) {
        List<String> list = ["a","b","c"]
        list = list.collect(){it.toUpperCase()}
        println(list)

        list = ["a","b","c"]
        println(list)
        list = list.drop(1)
        println(list) // "b","c"

        list = []
        println(list)
        list = list.drop(1)
        println(list)

        Map<String,String> map= ["1" : "a" , "2" : "b"]
        //Collect into a map
        Map<Long,String> longMap = map.collectEntries {
            return [new Long(it.key), it.value]
        }
        println (longMap)


        //Flat Map
        String [] array1 = ["a", "b", "c"]
        String [] array2 = ["d", "e", "f"]
        List listOfArrays = [array1, array2]
        println(listOfArrays)
        List flatList = listOfArrays.collectMany {
            it.collect{ entry -> entry.toUpperCase()}
        }
        println(flatList)

    }
}
