var Person = Java.type('com.andresolarte.harness.lang.java8.domain.Person');

var personBuilder = function () {
    //This function returns a JS object that will create person objects.
    return {
        build: function () {
            return Person.createJohnDoe();
        },
        buildWithParameters: function (personTemplate) {
            //This function creates a new Person, using parameters
            ret = new Person();
            ret.givenName = personTemplate.givenName;
            ret.surName = personTemplate.surName;
            ret.phone = 'N/A';
            ret.email = 'N/A';
            return ret;
        }
    };
}

var createJavaScriptPersonObject = function () {
    return {
        firstName: 'John',
        lastName: 'Doe',
        address: {
            street1: '412 Fake St.',
            street2: 'Apt 303',
            zip: '33132',
            city: 'Miami',
            state: 'FL'
        }
    }
}