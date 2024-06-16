# Spring Boot and Camel 3

## Documentation

```
https://access.redhat.com/documentation/id-id/red_hat_build_of_apache_camel/4.4
```

## Concept
A Camel middleware in front of a legacy system (SOAP UI). Serving a REST API, firing a SOAP request to application behind it, and convert to JSON. 

```
+-------------+           +--------------+            +--------------+
| Rest API    | --GET---> |    Camel     | --SOAP-->  |    SOAP UI   |
|             | <--JSON-- |              | <--SOAP--  |              |
+-------------+           +--------------+            +--------------+
```

## Request
```
$  curl -kv http://localhost:8080/api/employee/3
*   Trying [::1]:8080...
* Connected to localhost (::1) port 8080
> GET /api/employee/3 HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/8.4.0
> Accept: */*
>
< HTTP/1.1 200
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Sun, 16 Jun 2024 16:41:44 GMT
<
* Connection #0 to host localhost left intact
{"employee":{"address":[{"fromdate":1032800400000,"todate":1348419600000,"address":"Jakarta"}],"id":1,"firstname":"whatever","lastname":"something","birthdate":243882000000,"gender":"M"}}  
```