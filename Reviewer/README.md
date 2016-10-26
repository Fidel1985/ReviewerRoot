# Reviewer
This module calculates native and syndicated reviews, based on information obtained from SwitchBoard, Oracle and ElasticSearch services.

* HTTP Request: [http://reviewer-host/product/{clientID}/{productID}](http://reviewer-host/product/{clientID}/{productID})
* Response:
```
{
    "client": "{clientID}",
    "externalId": "{productID}",
    "native": 23,
    "syndicated": 175
}
```
* Response codes:
  * 200 - OK
  * 404 - Not Found
  * 503 - Service Unavailable

* native - count of reviews for target product
* syndicated - count of reviews for similar products that belongs to friendly clients