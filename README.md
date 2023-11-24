# xsis-miniproject

## POST REQUEST
http://localhost:9090/movie
### Body Req
<code>
{
    "title": "",
    "description": "",
    "rating": 0.0,
    "image": ""
}
</code>
### Response
<code>
  {
    "id": 2,
    "title": "Janji Joni",
    "description": "Film tentang film",
    "rating": 8.5,
    "image": "",
    "createdAt": "2023-11-24T03:00:43.589220800Z",
    "updatedAt": "2023-11-24T03:00:43.589220800Z"
}
</code>

## GET REQUEST
http://localhost:9090/movie
### Response
<code>
  [
    {
        "id": 2,
        "title": "Pengabdi mantan",
        "description": "Pengabdi mantan film yang di buat joko anwar",
        "rating": 9.0,
        "image": "upload/voucher_print_back_1_1700641174458.jpeg",
        "createdAt": null,
        "updatedAt": "2023-11-24T03:09:20Z"
    }
]
</code>

## GET REQUEST BY ID
http://localhost:9090/movie/2
### Response
<code>
  {
    "id": 2,
    "title": "Pengabdi mantan",
    "description": "Pengabdi mantan film yang di buat joko anwar",
    "rating": 9.0,
    "image": "upload/voucher_print_back_1_1700641174458.jpeg",
    "createdAt": null,
    "updatedAt": "2023-11-24T03:09:20Z"
}
</code>

## PUT REQUEST
http://localhost:9090/movie/2
### BODY REQUEST
<code>
  {
    "title": "Pengabdi mantan",
    "description": "Pengabdi mantan film yang di buat joko anwar",
    "rating": 9.0,
    "image": "upload/voucher_print_back_1_1700641174458.jpeg"
}
</code>
### RESPONSE
<code>
  {
    "id": 2,
    "title": "Pengabdi mantan",
    "description": "Pengabdi mantan film yang di buat joko anwar",
    "rating": 9.0,
    "image": "upload/voucher_print_back_1_1700641174458.jpeg",
    "createdAt": null,
    "updatedAt": "2023-11-24T03:09:20.342252800Z"
}
</code>
## Delete Request
http://localhost:9090/movie/1
## Response
<code>
  Success Delete
</code>
## Upload Image
http://localhost:9090/movie/upload
