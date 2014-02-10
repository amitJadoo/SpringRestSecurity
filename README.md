SpringRestSecurity
==================
This is a sample REST service using Spring.
A basic authentication has also been provided using basic Spring security.

More, a centralized exception handling also has been provided using ControllerAdvice.

The technologies used:
1. maven 3.0.5
2. Spring (CORE, MVC, SECURITY) 3.2.0
3. Tomcat 7

The project can be build using maven and the war file can be deployed in Tomcat.

The sample access URLs are: 

GET--http://localhost:8080/SpringRestSecurity/employee
GET--http://localhost:8080/SpringRestSecurity/employee/1
GET--http://localhost:8080/SpringRestSecurity/employee/-1
GET--http://localhost:8080/SpringRestSecurity/employee/1000

POST--http://localhost:8080/SpringRestSecurity/employee (with json/xml body)

PUT--http://localhost:8080/SpringRestSecurity/employee/1 (with json/xml body)
PUT--http://localhost:8080/SpringRestSecurity/employee/-1 (with json/xml body)
PUT--http://localhost:8080/SpringRestSecurity/employee/1000 (with json/xml body)

DELETE--http://localhost:8080/SpringRestSecurity/employee/1
DELETE--http://localhost:8080/SpringRestSecurity/employee/-1
DELETE--http://localhost:8080/SpringRestSecurity/employee/1000
