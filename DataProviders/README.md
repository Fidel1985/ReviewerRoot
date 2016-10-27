# Data Providers
This module provides test data, until the real services are disabled.

## Switchboard service
  Description: provides information about syndicated clients.
  * HTTP Request: [http://switchboard-host/edges/to/{destinationClientID}](http://switchboard-host/edges/to/{destinationClientID})
  * Response:
```
{
    "data": [
        {
            "destinationClientId": "{destinationClientID}",
            "sourceClientId": "{sourceClient1}"
        },
        {
            "destinationClientId": "{destinationClientID}",
            "sourceClientId": "{sourceClient2}"
        },
        {
            "destinationClientId": "{destinationClientID}",
            "sourceClientId": "{sourceClient3}"
        }
    ]
}
```
  * Response codes:
    * 200 - OK
    * 404 - Not Found
    * 503 - Service Unavailable

## Oracle
  Description: provides information about product matches.
  * HTTP Request: [http://oracle-host/product/{clientID}/{productID}/sources](http://oracle-host/product/{clientID}/{productID}/sources)
  * Response:
```
{
    "products": [
        {
            "client": "{sourceClient10}",
            "externalId": "1003695"
        },
        {
            "client": "{sourceClient1}",
            "externalId": "positively_radiant_daily_moisturizer_with_broad_spectrum_spf_15"
        },
        {
            "client": "{sourceClient3}",
            "externalId": "spf_15_4513"
        }
    ]
}
```
  * Response codes:
    * 200 - OK
    * 404 - Not Found
    * 503 - Service Unavailable

## ElasticSearch
  Description: provides reviews for products.
  * HTTP Request: [http://elasticseatch-host/explore?type=review&client={destinationClientID}&subjectProduct.externalId={productID}](http://elasticseatch-host/explore?type=review&client={destinationClientID}&subjectProduct.externalId={productID})
  * Response:
```
{
    "hits": [
        {
            "client": "{destinationClientID}",
            "subjectProduct": {
                "externalId": "{productID}"
            },
            "submissionTime": "2016-07-18T17:00:43.000Z",
            "text": "Quisque varius tortor vitae augue laoreet euismod aliquet metus vulputate. Duis tempor tristique
            dolor, ut venenatis enim fringilla feugiat.Praesent porttitor laoreet augue sed iaculis. Sed adipiscing
            convallis imperdiet. Fusce commodo dictum malesuada. Phasellus non nisl et elit euismod fermentum vel
            vitae lorem. Vestibulum nunc nisi, molestie vitae aliquet vel, pulvinar non nulla. Curabitur sit amet
            arcu turpis.",
            "title": "Sed nibh metus",
            "type": "review"
        },
        {
            "client": "{destinationClientID}",
            "subjectProduct": {
                "externalId": "{productID}",
            },
            "submissionTime": "2016-10-26T17:00:43.000Z",
            "text": "Proin eget malesuada ipsum. Maecenas purus arcu, imperdiet nec venenatis in, aliquet quis mauris.
            Donec in nulla erat, eget vehicula purus.Nulla varius, lacus id eleifend placerat, magna tortor accumsan
            quam, sit amet viverra quam tortor et urna. Integer nisl libero, euismod at commodo ut, sodales nec magna.
            Maecenas facilisis turpis id enim sollicitudin at dictum mauris commodo. Phasellus ut lectus eget turpis
            molestie vulputate.Praesent vel fringilla est.Ut tristique mollis magna, et pretium lacus aliquam quis.",
            "title": "Pellentesque habitant morbi tristique",
            "type": "review"
        }
    ]
}
```
  * Response codes:
    * 200 - OK
    * 404 - Not Found
    * 503 - Service Unavailable
