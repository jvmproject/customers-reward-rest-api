# customers-reward-rest-api

## Add Customer
```
localhost:8088/customer

Body
----
{
    "name": "vishnu mistry"
}
Response
--------
{
    "cid": 3,
    "name": "vishnu mistry",
    "rewardPoints": 0,
    "totalAmount": 0.0
}
```

## Add Transaction with based on customer id
```
localhost:8088/transaction/customerid/2
Body
----
{
    "total": 178,
    "description": "Bill - 2"
}

Response
--------
{
    "points": 206,
    "tid": 5,
    "total": 178.0,
    "description": "Bill - 2",
    "dateTime": "2022-08-20T18:31:08.477"
}
```

## Display all cutomer data with Transaction
```
localhost:8088/customer/

Response
--------
[
    {
        "cid": 1,
        "name": "akanksha Sharma",
        "transactions": [
            {
                "points": 90,
                "tid": 1,
                "total": 120.0,
                "description": "Bill - 1",
                "dateTime": "2022-08-20T18:30:35.986"
            },
            {
                "points": 40,
                "tid": 2,
                "total": 90.0,
                "description": "Bill - 2",
                "dateTime": "2022-08-20T18:30:42.528"
            },
            {
                "points": 0,
                "tid": 3,
                "total": 43.0,
                "description": "Bill - 3",
                "dateTime": "2022-08-20T18:30:50.213"
            }
        ],
        "rewardPoints": 130,
        "totalAmount": 253.0
    },
    {
        "cid": 2,
        "name": "vishnu Sharma",
        "transactions": [
            {
                "points": 206,
                "tid": 5,
                "total": 178.0,
                "description": "Bill - 2",
                "dateTime": "2022-08-20T18:31:08.477"
            },
            {
                "points": 650,
                "tid": 4,
                "total": 400.0,
                "description": "Bill - 1",
                "dateTime": "2022-08-20T18:30:58.943"
            }
        ],
        "rewardPoints": 856,
        "totalAmount": 578.0
    },
    {
        "cid": 3,
        "name": "vishnu mistry",
        "transactions": [],
        "rewardPoints": 0,
        "totalAmount": 0.0
    }
]
```
## Filter Data based on last month

```
localhost:8088/customer/1/bymonth/3
Response
-------
{
    "cid": 1,
    "name": "akanksha Sharma",
    "transactions": [
        {
            "points": 90,
            "tid": 1,
            "total": 120.0,
            "description": "Bill - 1",
            "dateTime": "2022-08-20T18:30:35.986"
        },
        {
            "points": 40,
            "tid": 2,
            "total": 90.0,
            "description": "Bill - 2",
            "dateTime": "2022-08-20T18:30:42.528"
        },
        {
            "points": 0,
            "tid": 3,
            "total": 43.0,
            "description": "Bill - 3",
            "dateTime": "2022-08-20T18:30:50.213"
        }
    ],
    "rewardPoints": 130,
    "totalAmount": 253.0
}
```


