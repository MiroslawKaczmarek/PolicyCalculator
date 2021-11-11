## What is PolicyCalculator ?
PolicyCalculator - it is a test project - API for calculate insurrance cost. 

## How to start server
* Before you start you need to have on your machine - maven, java (at least 8)
* Checkout the project 
* On your machine open command line
* Go to directory with project (the directory where is pom.xml)
* Call this for start the server:
  **mvn spring-boot:run**
* For call jUnits you can call command:
  **mvn test** 

## How to use - operating on API 
On initial after you start the server open http://localhost:8080/swagger-ui.html <br>
In next step you should open group **policy-controller**. 
Inside are available 2 methods :
*  /policy/{policyId}/calculate - returns calculations for choosen policy
*  /policy/{policyId} - returns details about choosen policy

###which {policyId} we can use in API?
Database is not added, but in repository classes is artificially created data structure. 
So, has been created Policy records with ID: 1,2,3,4,5:
* Calculations for IDs 1,2,3 contains different 'successfull' scenarions which returns different calculation results.<br>
-Id:2 contains scenario from your specification pdf (returns 2.28)<br>
-Id:1 contains scenario from your specification pdf (returns 17.13)
* In case calculations for IDs 4,5 - will be return error for demonstrate exceptional scenarios.
* Also can be used another not existed ID - in that case will be returned error.

## Tech stack
* java 8
* spring boot
* maven

## Additional thoughts about algorithm
Used strategy design pattern. However all logic is moved to the 'database'. Objects PolicyStrategy defines logic.
This approach not requires additional potential deploys in case when we would like to add new PolicyStrategy - for exaple new type: TORNADO.
