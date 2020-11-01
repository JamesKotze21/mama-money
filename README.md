To Run:
--

Please allow maven to download all the dependencies specified on the POM file, and run mvn clean compile.  
Afterwhich, you can run the service and hit it locally.

I have provided 2 endpoints for the USSD problem.

/mama-money/ussd
-

- this stores the user data in a HashMap.
- The user session data will be stored in the application's memory for as long as it is running.
- Pro's
	- Very fast data retrieval.

- Con's
	- Allot of requests will cause allot of memory usage.


/mama-money/ussd-mongo
-

- stores the user data in a MongoDB data store (currently embedded - so also in the application's memory).
- Pro's
	- Point to a hosted mongoDB datastore - no longer in the application's memory

- Con's
	- Not as fast as a hashmap lookup


If you were to run just one small instance of this application, these solutions should work.


If this was to be taken to production, I would rather host a MongoDB datastore and have the implementation point to that DB instance.  
That way, you can host multiple instances behind a load balancer which handles requests.
All incomming requests can then share the datastore among instances - i.e. not instance dependent.

I did this with a locally hosted mongoDB database.  


__To do so:__  
Start a mongo DB server - locally / hosted.  
_The hosted "mama-money" database should just have 1 collection called "users"_

Add the following properties to the application.properties file (e.g. of my locally hosted mongoDB instance):
```
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=mama-money
```

Also, remove the comments from the "test" scope to the "de.flapdoodle.embed" dependency in the POM file.  

__Embedded__:
```
<groupId>de.flapdoodle.embed</groupId>
<artifactId>de.flapdoodle.embed.mongo</artifactId>
<!--<scope>test</scope>-->
```

__Hosted DB__:
```
<groupId>de.flapdoodle.embed</groupId>
<artifactId>de.flapdoodle.embed.mongo</artifactId>
<scope>test</scope>
```




Assumptions made:
--
- if a session ID does not match the previous one, the flow will restart
- minor validations are required for when the user input is required
- for invalid requests, clear the session data so that the flow can restart