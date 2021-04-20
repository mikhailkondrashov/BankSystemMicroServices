# BankSystemMicroServices

A prototype of a banking system based on a Java MVC micro-service application.

### Technologies
- Java 8
- Spring Boot
- PostgreSQL
- Hibernate
- Thymeleaf
- Swagger 2

### How to run
```sh
To launch classes Application.java in servecies customerService, accountService and commonService
```
Services commonService, accountService and customerService start on localhost ports 8080, 8081 and 8082 respectively.
Postgre database initialized with some sample user.

## Available Services

### People Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /v1/people | get all people | 
| GET | /v1/people/{id} | get person by id | 
| POST | /v1/people| create new person | 
| PUT | /v1/people/{id}| update person info | 
| DELETE | /v1/people/{id} | delete person by id | 

#### Sample JSON for People Service
##### Create a person :
```sh
{
    "birthdate": "2021-04-20",
    "email": "mail@mail.ru",
    "firstName": "Bill",
    "lastName": "Johnson",
    "phoneNumber": "+1 234 567 89 01"
} 
```
### Account Service
| HTTP METHOD | PATH | USAGE |
| -----------| ------ | ------ |
| GET | /v1/people/{id}/accounts | get all bills | 
| GET | /bill/get-by-customer-id/{customerID} | get bill by customer id | 
| POST | /bill/create/{customerID} | create bill for exact customer | 
| POST | bill/delete/{id} | delete bill by id | 
| PUT | "bill/adjustment/{id} | commit adjustment for bill by id | 
| PUT | "bill/payment/{id} | commit payment for bill by id | 

#### Sample JSON for Account Service
##### Create an account:
```sh
{
  "creationDate": "2021-04-20",
  "name": "Salary",
  "personId": "c5a6757f-fec4-41f3-a6a6-eb03d70c0d6f"
} 
```
##### Create a bill:
```sh
{
  "amount": 10,
  "currency": "USD",
  "isOverdraft": false,
  "account": {
    "creationDate": "2021-04-20",
    "id": "58612097-bc0d-4de3-b32b-2221c81ec8e1",
    "name": "Salary",
    "personId": "c5a6757f-fec4-41f3-a6a6-eb03d70c0d6f"
  }
} 
```
##### Commit a payment:
```sh
{
  "amount": 700,
  "currency": "RUB",
  "message": "Payment for a purchase in the OK store in the amount of 700 RUB",
  "time": "2021-04-20T12:27:59.873Z",
  "bill": {
      "account": {
          "id": "58612097-bc0d-4de3-b32b-2221c81ec8e1",
          "name": "Salary",
          "creationDate": "2021-04-20",
          "personId": "c5a6757f-fec4-41f3-a6a6-eb03d70c0d6f"
      },
    "amount": 10,
    "currency": "USD",
    "isOverdraft": false
  }
}
```
##### Commit an adjustment:
```sh
{
  "amount": 10,
  "currency": "USD",
  "message": "Adjustment of the account in the amount of 10 USD",
  "time": "2021-04-20T12:27:59.873Z",
  "bill": {
      "account": {
          "id": "58612097-bc0d-4de3-b32b-2221c81ec8e1",
          "name": "Salary",
          "creationDate": "2021-04-20",
          "personId": "c5a6757f-fec4-41f3-a6a6-eb03d70c0d6f"
      },
    "amount": 0,
    "currency": "USD",
    "isOverdraft": false
  }
}
```
##### Commit a transfer:
```sh
{
  "amount": 10,
  "time": "2021-04-20T12:27:59.880Z",
  "currency": "USD",
  "message": "Transfer of funds between accounts",
  "sourceBill": {
    "account": {
      "creationDate": "2021-04-20",
      "id": "58612097-bc0d-4de3-b32b-2221c81ec8e1",
      "name": "Salary",
      "personId": "c5a6757f-fec4-41f3-a6a6-eb03d70c0d6f"
    },
    "amount": 10,
    "currency": "USD",
    "id": "987ae068-7960-4119-bc51-914e2e0c71a0",
    "isOverdraft": false
  },
  "beneficiaryBill": {
    "account": {
      "creationDate": "2021-04-20",
      "id": "58612097-bc0d-4de3-b32b-2221c81ec8e1",
      "name": "Salary",
      "personId": "c5a6757f-fec4-41f3-a6a6-eb03d70c0d6f"
    },
    "amount": 0,
    "currency": "RUB",
    "id": "2b10fd68-f8fd-4cdb-b9ad-eae860828c5c",
    "isOverdraft": true
  }
}
```
