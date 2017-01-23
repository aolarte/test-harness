/**
 * Created by aolarte on 12/19/2016.
 */
class GroovyTest {
    static void main(String... args) {
        List list
        println list?.size()>0
        list = new ArrayList()
        println list?.size()>0
        list.add("test")
        println list?.size()>0
    }
}
