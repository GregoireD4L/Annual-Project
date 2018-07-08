import * as admin from 'firebase-admin';

//var serviceAccount = require('./credentials/data-for-life-firebase-adminsdk-p2tqj-0396543d79');

admin.initializeApp({
    credential: admin.credential.cert({
        project_id: "data-for-life",
        client_email: "firebase-adminsdk-p2tqj@data-for-life.iam.gserviceaccount.com",
        private_key: "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDO3Juv0OI9Y8Hz\nRV2zwFazBRYXrkAVhM+2tgE7sDGKyBezmFtIYCrfuh+vpTinoIIYrvAEiMaQrP7O\nY0bqc+f4a0PSTcqeTCL80vNLKKujWRYI6jkJmeuZt/L9I3mSywJwcePWNQAEBjoy\nlkd8F33g9TO40H1cvr1gA2gl3I7/SoHB7YzcspR81gx4xVnxnZkJqQg3nj6Lxpi2\n9A8gFtCXitXIH4buVCfMJ2+1gsxtsFcv58DBiYrEqUYlnmtTN9YsMF2LalI7HsGt\nmMGdwT9c1iwmDxShkCr2BFCtpoPgjvFAjv+locmnOJOydunSjqsNXij8/jNC92fG\nhHzAF2nTAgMBAAECggEABa2ykSjc9kQ5DH4JUrlg0b8qMINhTJXsjaEGGd95Ijji\nT9UPqQr/Y4eLQw2VxPqwkWiCbQ9I5kjO+k1VmOksAOIbEwJpcF851PT24VF9m8WW\neOpod+JZwF5jrd3xZOnYvBpRLnVFxMyqgGTr7Eh6MHsfmRU5ABFXld/g1g8U9CNI\ntQZ5Yb5XSQfzYD3eLYOpS8k5vQDyatSYcV/mUak8U2DabduaZ/TWkVByOqafG6cb\ndtWMjE/WOL5buJiOFWtgufuqIitCPa5OPdbCHKxhBe47RGWjbTzfxTwJ7Ym+sWrK\nrPVYnzFx07i2h1IeRDRQ7cZQvHfxErN+934LMtSpPQKBgQDqLFLKNAXcxTjLCMNk\nMd1zl1jRbhRDqC+ZnA/axHoKfIlUINMP2qyflsGkAcs5ODXy55m+Z+geiCBXlH6N\nf3FcFXODmhFfma/OczO69MEfE1XNrUBb2TQzIgvMwYGwrOS5R8pCK35u7OOhu9xx\nWZ+vNgukA6tFn632K5p001DirQKBgQDiJJly+q2LokprHrWwSTNitTv13dx8ZrGm\nRBTxBS+ROx+XdI8uj9cuoGdo2H1WlWqq5IClUsMLUH//36Ooi7C3pENpG4bfqKax\nWzX4eRBNwbDXuuK5LvJBd+MdWOlnjVIJBunDu42Q8+LA6M0tMzIV9jiWNjliosui\nOqBEFIeOfwKBgAXVvuZc73x1yZl9iWjYuTzlG4h0Zrrc2PrJ4bdAyA201rMq8jdl\n6oE7f223wbmWRfncfX86FZWVLMEh6RlbgJabzjoMP7yTFsddJX/WE23TzIKIe58O\nRXe0tHiQpcuiMzzOpiKJ9OeMrYtdI/wLEKiL/zRCByV/+hS1N28LyOstAoGAKWL9\nWleaT0XCTNxv7/VVnCkC8jpKUdIRjRZEpt+GFxQ08ej5E1Jr1TKx+jQ0rBXoUaJE\nEP2WiImRA2kQcJYwH0vGsoPBKUmaSDcjJ3gwjap3DOQ8/hsjZuvj+sj83mC0tMY5\nHgNDI1YfF6MDedQ2xGItdQAzfGPC1MvZ7Nu8+00CgYEAxekXyreLYfr7syTt8od9\nJoXoKW7aMosSl+vqcF+QpmwwretL8qsSvsc0aexR82k4PZp/tH6uh+V5YbOM7e1n\n3Fk6wFiFoaoGC9cmT/OxkaPBbtI/9sn49S2forBAqOK7/FxJttXPjCszOc/IG6UE\n5LMSgOUTceExkUNUy1PCyUo=\n-----END PRIVATE KEY-----\n",
    }),
    databaseURL: 'https://data-for-life.firebaseio.com'
});

export default admin;