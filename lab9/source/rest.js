var request = require('request');
request('https://api.foursquare.com/v2/venues/search%20?client_id=RPCLY5C5TUHTG3T2AKEPZKEEHKNAZE2J32E0NXX33XBNKSWD&client_secret=BHNJJEU1UC5V2ZA5SA2Q4GFO5EFSYPF4YMR1VXXHI10RC2FL%20&v=20160215' "&near=" + placeEntered +"&query=" + searchQuery);, function (error, response, body) {
    //Check for error
    if(error){
        return console.log('Error:', error);
    }

    //Check for right status code
    if(response.statusCode !== 200){
        return console.log('Invalid Status Code Returned:', response.statusCode);
    }
//	console.log(body);
    //All is good. Print the body
    body = JSON.parse(body);
    var ven = body.response.venues;
    var i;
    for(i=0;i<ven.length;i++)
    {
        console.log(ven[i].name);
    }

});