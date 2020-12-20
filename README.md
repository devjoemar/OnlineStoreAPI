# OnlineStoreAPI
Basic Online Store API

Getting Started

  1. Clone this project or download.
  2. Run the main method in OnlineStoreApiApplication.java
  
  Cloning and importing github project using Intellij IDEA https://youtu.be/aBVOAnygcZw
  
  
The following are the pre loaded data once you run the application
### User
  
| ID        	| Name          |
| ----------- |:-------------:|
| 1      		  | John Doe		  |
| 2     		  | Alexander     |


### Product

|ID | 	DESCRIPTION  		      | NAME  	|	PRICE    |
|-- |:-----------------------:|:-------:|:---------:
|1	| iPhone 12 Pro MAX 512GB |	iPhone	|  1200.00 |
|2	| Samsun Galaxy Note	    | Samsung	|  900.00  |

#### List product
### GET http://localhost:8080/api/v1/products

#### Response 200 OK
----

```json
{
    "_embedded": {
        "productEntityList": [
            {
                "id": 1,
                "name": "iPhone",
                "price": 1200.00,
                "description": "iPhone 12 Pro MAX 512GB",
                "tags": [
                    "mobile",
                    "5G"
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/v1/products/1"
                    },
                    "products": {
                        "href": "http://localhost:8080/api/v1/products"
                    }
                }
            },
            {
                "id": 2,
                "name": "Samsung",
                "price": 900.00,
                "description": "Samsun Galaxy Note",
                "tags": [
                    "mobile",
                    "5G"
                ],
                "_links": {
                    "self": {
                        "href": "http://localhost:8080/api/v1/products/2"
                    },
                    "products": {
                        "href": "http://localhost:8080/api/v1/products"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/products"
        }
    }
}
```

#### Add product
### POST http://localhost:8080/api/v1/products

#### Headers
Content-Type: application/json

#### Request
``` json
{

	"name" : "Oppo",
	"price": 78.00,
	"description" : "Oppo 2020 ",
	"tags": [ "mobile", "5G"]
}

```
#### Response 201 Created
``` json
{
    "id": 3,
    "name": "Oppo",
    "description": "Oppo 2020 ",
    "tags": [
        "mobile",
        "5G"
    ],
    "price": 78.00
}
```
#### Show Pre loaded orders of User 2
### GET http://localhost:8080/api/v1/orders/user/2

#### Response 200 OK
``` json
{
    "_embedded": {
        "orderItemEntityList": [
            {
                "userId": 2,
                "name": "Alexander",
                "products": [
                    {
                        "id": 1,
                        "name": "iPhone",
                        "price": 1200.00,
                        "description": "iPhone 12 Pro MAX 512GB",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    },
                    {
                        "id": 1,
                        "name": "iPhone",
                        "price": 1200.00,
                        "description": "iPhone 12 Pro MAX 512GB",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    },
                    {
                        "id": 2,
                        "name": "Samsung",
                        "price": 900.00,
                        "description": "Samsun Galaxy Note",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    }
                ],
                "localDate": "2020-12-20",
                "_links": {
                    "related": {
                        "href": "http://localhost:8080/api/v1/orders/user/2"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/orders/user/2"
        }
    }
}

```
#### Place order
User 2 placing an order with product 3 and product 1 with localdate "2020-12-18"
### POST http://localhost:8080/api/v1/orders

#### Headers
Content-Type: application/json

#### Request
``` json
{
	"userId" : 2,
	"products" : [
		{ 
			"id" : 3,
			"price" : 78.00
		},
		{ 
			"id" : 1,
			"price" : 1200.00
		}	
	],
	"localDate": "2020-12-18"
}
```
#### Response 201 Created
``` json
{
    "userId": 2,
    "name": "Alexander",
    "products": [
        {
            "id": 3,
            "name": "Oppo",
            "price": 78.00,
            "description": "Oppo 2020 ",
            "tags": [
                "mobile",
                "5G"
            ]
        },
        {
            "id": 1,
            "name": "iPhone",
            "price": 1200.00,
            "description": "iPhone 12 Pro MAX 512GB",
            "tags": [
                "mobile",
                "5G"
            ]
        }
    ],
    "localDate": "2020-12-18",
    "_links": {
        "related": {
            "href": "http://localhost:8080/api/v1/orders/user/2"
        }
    }
}
```
#### Show orders of user 2 with range fromDate 2020-12-02 and toDate 2020-12-20
### GET http://localhost:8080/api/v1/orders?id=2&fromDate=2020-12-02&toDate=2020-12-20

#### Response 200 OK
``` json
{
    "_embedded": {
        "orderItemEntityList": [
            {
                "userId": 2,
                "name": "Alexander",
                "products": [
                    {
                        "id": 1,
                        "name": "iPhone",
                        "price": 1200.00,
                        "description": "iPhone 12 Pro MAX 512GB",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    },
                    {
                        "id": 1,
                        "name": "iPhone",
                        "price": 1200.00,
                        "description": "iPhone 12 Pro MAX 512GB",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    },
                    {
                        "id": 2,
                        "name": "Samsung",
                        "price": 900.00,
                        "description": "Samsun Galaxy Note",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    }
                ],
                "localDate": "2020-12-20",
                "_links": {
                    "related": {
                        "href": "http://localhost:8080/api/v1/orders/user/2"
                    }
                }
            },
            {
                "userId": 2,
                "name": "Alexander",
                "products": [
                    {
                        "id": 3,
                        "name": "Oppo",
                        "price": 78.00,
                        "description": "Oppo 2020 ",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    },
                    {
                        "id": 1,
                        "name": "iPhone",
                        "price": 1200.00,
                        "description": "iPhone 12 Pro MAX 512GB",
                        "tags": [
                            "mobile",
                            "5G"
                        ]
                    }
                ],
                "localDate": "2020-12-18",
                "_links": {
                    "related": {
                        "href": "http://localhost:8080/api/v1/orders/user/2"
                    }
                }
            }
        ]
    },
    "_links": {
        "self": {
            "href": "http://localhost:8080/api/v1/orders/user/2"
        }
    }
}
```
