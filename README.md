# SquadioTechAssessment
Demo API for tech assessment

I. Starting The Demo:

Make sure you have Java and Maven installed in your system.

1. Download the source code zip or use git clone <<repourl>> to get the repository.
2. Unzip the source code zip file.
3. Navigate to the root directory of the unzipped sources.
4. Run mvn install from the root directory. This will install the dependencies, build the fat jar and run the tests.
5. Navigate to the target folder via cd target
5. Execute java -jar SquadioTechAssessment-0.0.1-SNAPSHOT.jar
  
  II. Consuming the API.
  
  You'll need a tool like Postman or Insomia installed in your system.
  
  Note that upon first run, the Demo will create the default users as directed in the requirements doc.
  
  Also note that the BASE_URL will always be '/api/v1/'
  
  1. Navigate to http://localhost:5050/api/v1/auth/login . Note that if the 5050 port is unavailable, edit the application.properties and set server.port=<<your free port>>
    
  2. Enter the appropriate credentials e.g. 'Admin' as username and 'admin' as password.
  3. Once you click 'send' or execute the command to send the login request, you'll receive a JWT (as well as the username and user id).
  4. Place the JWT token in the 'Authorization' header as 'Bearer <<JWTtoken>>' note that there exists a SINGLE SPACE betweeen 'Bearer' and the start of the JWT token.
     Note that without a valid JWT, you will be unable to consume the rest of the API.
  5. Navigate to /api/v1/accounts/genStats and do a GET request. This is done to auto-generate a number of statements that will be randomly attached to preset
     accounts which are in turn pre-attached to specific preset users. Since the data is stored in a H2 in-memory database, you'll need to hit this
     endpoint everytime you start the app. You can't have to do that for users and accounts since those are specified in the DML that comes with the source and
     and consequently in the far jar. There is no need to install a web server or database since for the purposes of this demo, we use embedded types of
     both.
  6. Send requests to the appropriate endpoints by appending either 'users' or 'accounts' after the BASE_URL as clarified below under 'Endpoints'.
  7. The generated JWT token will have an expiry of 5 minutes.
  8. API to add or delete or edit the entries is not included since it's not a part of the API as specified in the Requirements document.
  9. Logout by sending GET to /api/v1/logout
  
  

  A Note On Logging
  
  The demo uses slf4j to do the logging which is fine for testing and for small applications. Production grade APIs would mostly involve
  an implementation that writes the formatted logs to a Queue within a message broker like ActiveMQ or RabbitMQ, and then a log aggregator(e.g. LogStash)
  can be connected to that queue and channel the log messages to the right persistence method(html or text files or ElasticSearch). So,we
  get to separate the log generation and log persistence.

  
   A Note On DTOs (You don't have to read this).
  
  This is done for the sake of this implementation bearing in mind that when giving out the JSON or XML response, we don't always want to include all the fields
  within an entity(or table row for that matter) or that we need to get data from various sources and amalgamate it to generate a certain response or the fact
  that it may not be practical to use a DTO or JPA (e.g. try mapping ISO8583 messages into DTOs!, in the case of ISO8583, all you really need is a HashMap or a JSONObject)
