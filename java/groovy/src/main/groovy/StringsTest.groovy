class StringsTest {
    static final String MY_ID = "ID43"
    static void main(String... args) {
        println '''Multi 
line'''
        println '''\
Multi 
line'''
        println "My id: ${MY_ID}" //Interpolated
        println 'My id: ${MY_ID}' //Non-Interpolated java.lang.String

        String s = "My Test String"
        println s.drop(3) //Test String
        println s.take(7) //My Test

        int  x =999
        println sprintf('%02d', x)

        println String.format('Y%s X%sY', '1', '2')
        println String.format('Y%s X%sY', ['1', '2'] as Object[])
    }
}
