var Person = Java.type('com.andresolarte.harness.lang.java8.domain.Person');

var testWithReturn = function () {
    person = new Person();
    person.setFirstName("Little");
    person.setLastName("Nashorn");
    return person;
}

var testWithBeanReturn = function () {
    person = new Person();
    person.firstName = "Little";
    person.lastName = "Nashorn";
    return person;
}

var testWithListReturn = function () {
    var javaImporter = new JavaImporter(
        java.util
    );
    var ret;
    with (javaImporter) {
        ret = new ArrayList();
        ret.add(Person.createJohnDoe());
        ret.add(Person.createJohnDoe());
    }
    return ret;
}

var testWithTypedArrayReturn = function () {
    var PersonArray = Java.type("com.andresolarte.harness.lang.java8.domain.Person[]");
    var ret = new PersonArray(2);
    ret[0] = Person.createJohnDoe();
    ret[1] = Person.createJohnDoe();
    return ret;

}


var testWithJSArrayReturn = function () {

    return [Person.createJohnDoe(), Person.createJohnDoe()];
}