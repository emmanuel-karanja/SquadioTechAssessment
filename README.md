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
  
  1. Navigate to http://localhost:8080/api/v1/auth/login
  2. Enter the appropriate credentials e.g. 'Admin' as username and 'admin' as password.
  3. Once you click 'send' or execute the command to send the login request, you'll receive a JWT (as well as the username and user id).
  4. Place the JWT token in the 'Authorization' header as 'Bearer <<JWTtoken>>' note that there exists a SINGLE SPACE betweeen 'Bearer' and the start of the JWT token.
  5. Post requests to the appropriate endpoints 
  
  
