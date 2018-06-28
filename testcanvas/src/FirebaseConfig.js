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

export default !firebase.apps.length ? firebase.initializeApp(config) : firebase.app();