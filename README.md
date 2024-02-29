# order-microservices

## how to run
1. go to order-approval-service and execute `gradlew build`

3. go to order-config-server and execute `gradlew build`

5. go to order-taking-service and execute `gradlew build`

7. `execute docker-compose build`
8. `execute docker-compose up`

## how to use
1. to add new order event curl:
   
`   curl --location 'http://localhost:8080/api/v1/order' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic ZXJwOmVycA==' \
--data '{
    "clientName": "James",
    "clientSurname": "Dean",
    "installationAddress": "some address",
    "preferredInstallationDate": "2024-10-18T08:04:30Z",
    "timeSlotDetails": "1hr",
    "productToInstall": [
        {
            "productType": "MOBILE",
            "details": "some details to product"
        }
    ]
}'`

3. to approve order you need to take uuid from previous response and curl:
   
`curl --location --request POST 'http://localhost:8090/api/v1/order/d5f8e5d5-e538-41d0-83cd-be2537a32045/approve' \
--header 'Authorization: Basic YWdlbnQ6YWdlbnQ='`

## Stack
- Java 17
- Spring(Boot, JPA, Cloud Config, Security)
- JUnit5
- RabbitMQ
- Docker
- Mock Server
- TestContainers
- H2

## Arch
- use hexagonal pattern combined with DDD
