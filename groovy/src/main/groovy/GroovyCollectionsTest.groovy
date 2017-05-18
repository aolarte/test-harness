class GroovyCollectionsTest {

    static class Holder {
        List<Parent> parents
    }

    static class Parent {
        List<Child> childs
    }

    static class Child {
        Long id
    }



    static void main(String... args) {
        List<String> list = ["a","b","c"]
        list = list.collect(){it.toUpperCase()}
        println(list)

        list = ["a","b","c"]
        println("Sublist")
        println(list[0..0])
        List<String> list2 = []
        println(list2[0])
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

        Set<Long> longSet = [10, 20, 30]
        long[] longs = longSet.toArray()
        println(longs)


        //Spread accross collections
        Child child1 =new Child(id: 1)
        Child child2 =new Child(id: 2)
        Child child3 =new Child(id: 3)
        Parent  parent1 = new Parent(childs: [child1])
        Parent  parent2 = new Parent(childs: [child2, child3])
        Holder holder = new Holder(parents: [parent1, parent2])
        holder.parents.childs.forEach{System.out.println("Id: " + it.id)}
    }
}
