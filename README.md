mars-calendar
=============

https://2014.spaceappschallenge.org/project/planet-calendar/


http://162.243.255.36:8080/#!/week/mars/earth/1014/23/1 (still eventual availablity)


Please write all code and comments in English, according to challenge rules!

Database configuration: run db_copy.rtfm in mysql. db configuration in application.properties

Application start (from Intellij Idea):
Simple launch of embedded Jetty server: app-local in Idea, or
mvn spring-boot:run

Launch on the local Jetty: jetty-local in Idea
or mvn org.apache.maven.plugins:maven-war-plugin:2.4:war & then something like jetty-runner

In order to write html code, one may open index.html via "open in browser" - there won't be any need to restart the server
