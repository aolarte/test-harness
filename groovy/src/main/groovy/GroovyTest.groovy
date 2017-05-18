/**
 * Created by aolarte on 12/19/2016.
 */
class GroovyTest {
    static class MyModel {
        int i
    }

    static void main(String... args) {
        List list
        println list?.size()>0
        list = new ArrayList()
        println list?.size()>0
        list.add("test")
        println list?.size()>0

        def lock = "f"
        if (lock) {
            println 'ok'
        } else {
            println 'null'
        }

        String[] items=["item1", "item2", "item3"]
        List<String> upperCaseItems = items.collect{
            return it.toUpperCase()
        }
        println(upperCaseItems)

        MyModel model = new MyModel(i:10)
        println("Model: " + (model?.i>5))

    }
}
