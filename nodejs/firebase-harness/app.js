var firebase = require('firebase')
require('firebase/firestore')

var config = {
  apiKey: 'XXXX',
  authDomain: 'XXXX',
  databaseURL: 'XXXX',
  storageBucket: 'XXXX',
  projectId: 'XXXX'
}
firebase.initializeApp(config)

// Set explode to true
var explode = true
new Promise(function (resolve, reject) {
  if (explode) {
    reject(new Error('Boom'))
  } else {
    resolve()
  }
}).then(function () {
  console.log('Thing is done. Do followup task.')
  return 'my argument'
}).then(function (arg1) {
  console.log('Thing is done. Do followup task 2. argument: ' + arg1)
}).catch(function () {
  console.log('Thing failed')
}).then(function () {
  console.log('Always do this')
})

//

// var userEmail = 'olarte.andres@gmail.com'
// var userPassword = 'andres'

var userEmail = 'andresolarte@google.com'
var userPassword = 'xxx1234'

firebase.auth().fetchProvidersForEmail(userEmail)
  .then(function (list) {
    if (list === undefined || list.length === 0) {
      // array empty or does not exist
      console.log('Create user')
      firebase.auth().createUserWithEmailAndPassword(userEmail, userPassword)
        .then(function () {
          console.log('User created')
        })
        .catch(function (error) {
          var errorCode = error.code
          var errorMessage = error.message
          console.error('Error creating user: ' + errorCode + '' + errorMessage)
        })
    } else {
      // We got some providers
      for (let s of list) {
        console.log('Provider: ' + s)
      }
    }
  })
  .catch(function (error) {
    var errorCode = error.code
    var errorMessage = error.message
    console.error('Error fetching providers: ' + errorCode + '' + errorMessage)
  })

firebase.auth().signInWithEmailAndPassword(userEmail, userPassword)
  .then(function (user) {
    var uid = user.uid.toString()
    console.log('Signed In with uid:' + uid)
    var db = firebase.firestore()
    db.collection('users').doc(uid).set({
      email: userEmail
    })
      .then(function () {
        console.log('User Document successfully written! ') // + docRef.id + ' ' + docRef.path)
        var today = new Date()
        return db.collection('users').doc(uid).collection('requests').add({
          requestDate: today.toDateString()
        })
      })
      .then(function () {
        // Find all requests

        return db.collection('users').doc('3nUYJJLGVMYX9xKlzzbm1xGU31K2').collection('requests').get()
        //return db.collection('users').doc(uid).collection('requests').get()
      }).then(function (querySnapshot) {
        querySnapshot.forEach(function (doc) {
        // doc.data() is never undefined for query doc snapshots
          console.log(doc.id, ' => ', doc.data())
        })
      })
      .catch(function (error) {
        console.error('Error writing document: ', error)
      }).then(function () {
        firebase.auth().signOut().then(function () {
          console.log('Signed out')
        }).catch(function (error) {
          console.error('Error signing out: ', error)
        })
      })
  })
  .catch(function (error) {
    // Handle Errors here.
    var errorCode = error.code
    var errorMessage = error.message
    console.log('Error ' + errorCode + ' ' + errorMessage)
  })
