README
This README describes the jj-server app, a simple embedded Java REST web server, based on Jetty & Jersey.


What is jj-server?
jj-server is a starting point for a micro-service java web server. It is

based on Jetty & Jersey
should support static content, servlet, and REST APIs.
should be really easy to host an AngularJS app, a ReactJS app, or a RestAPI server.

How to run?
mvn clean package

java -jar target/jj-server-1.0-SNAPSHOT.jar

Point browser to:

http://localhost:6060/static should return static html/css/js

http://localhost:6060/test is handled by REST controllers
