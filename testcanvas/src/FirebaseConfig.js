// Config file
import * as firebase from "firebase";

const config = {
    apiKey: "AIzaSyAanpOteKt6sJER051WlLtlf3oYHduwpTM",
    authDomain: "data-for-life.firebaseapp.com",
    databaseURL: "https://data-for-life.firebaseio.com",
    projectId: "data-for-life",
    storageBucket: "data-for-life.appspot.com",
    messagingSenderId: "1065890119840"
};
var app ;
if(!firebase.apps.length){
app=firebase.initializeApp(config);
app.auth().setPersistence('local');

}
else{
app= firebase.app();
}
export default app;