import * as admin from 'firebase-admin';

var serviceAccount = require('./credentials/data-for-life-firebase-adminsdk-p2tqj-0396543d79');

admin.initializeApp({
    credential: admin.credential.cert(serviceAccount),
    databaseURL: 'https://data-for-life.firebaseio.com'
});

export default admin;