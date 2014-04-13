mars-calendar
=============

https://2014.spaceappschallenge.org


Please write all code and comments in English, according to challenge rules!

Database configuration: in mysql to run db_copy.rtfm. MySQL configuration in application.properties

Application start (from Intellij Idea):
Simple launch of embedded Tomcat server: app-local in Idea, or
mvn spring-boot:run

Launch on the local Jetty: jetty-local in Idea
or mvn org.apache.maven.plugins:maven-war-plugin:2.4:war
& then something like jetty-runner (не настраивал)

In order to write html code, one may open index.html via "open in browser" - there won't be any need to restart the server (backbone might go nuts though)