# Sample Project for customer transactions

### Design consideration
* Two restful endpoints in a single springboot application.
* Transaction API implemented using Paging to cater for many transactions.
* Can easily be splitted up into different microservices, which integrates with API gateway and Authentication server. 
* Tests written in JUnit5 for Controllers, Services and Repositories with "test" profile.
* Using Gradle 5.x.x
* To run test, gradle test