# PaperlessREST

## OpenAPI generated server

Spring Boot Server

### Overview
This server was generated by the [OpenAPI Generator](https://openapi-generator.tech) project.
By using the [OpenAPI-Spec](https://openapis.org), you can easily generate a server stub.
This is an example of building a OpenAPI-enabled server in Java using the SpringBoot framework.


The underlying library integrating OpenAPI to Spring Boot is [springdoc](https://springdoc.org).
Springdoc will generate an OpenAPI v3 specification based on the generated Controller and Model classes.
The specification is available to download using the following url:
http://localhost:8080/v3/api-docs/

Start your server as a simple java application

You can view the api documentation in swagger-ui by pointing to
http://localhost:8080/swagger-ui.html

Change default port value in application.properties

## HOWTO

### Steps to reproduce:

1. Start just with openapi-gen.sh and swagger.json
2. Run ```./openapi-gen.sh```
3. The generated soltiuon will be in ```out/``` - open with IntelliJ and try out if source was generated as expected.
4. move all files from the ```out/``` directory to the current working directory, e.g.
```mv -rf out/* .```
5. Start IntelliJ again and open the project in the current working directory
6. Run the server with ```mvn spring-boot:run```
7. Open the browser in http://localhost:8080/

### Next steps:

* create fake implementations in ApiApiController to try out the REST-Server