# Spring Boot MicroServices Top Shop backend
This repository is code base for top shop eccomerce microservice application. the application uses spring boot, apache kafaka, eureka service discovery, jpa, postgresql and many oher more technologies
. The development team used best programing practiceses, design patterns and architectures.
# Table of Content
* [Contributors](#contributors)
* [Application Architecture](#application-architecture)
* [Using the application](#using-application)

    * [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/78f423782d32dd945c02)
    * [Running on local m/c](#run_local_mc)
    * [Running using docker - NOT WORKING](#run_docker) 
* [Microservices Overview](#microservices-overview)
* [Spring Cloud Config Overview](#spring-cloud-config-overview)
* [Spring Cloud Netflix Overview](#spring-cloud-netflix-overview)


## <a name="contributors"></a>Contributors

* [yome mengistu](https://www.linkedin.com/yome-mengistu)
* [Ali ]()

## <a name="application-architecture"></a>Application Architecture

The application consists of 7 different services 

* [config server](config-server/README.md) - setup external configuration
* [webservice-registry](webservice-registry/README.md) - Eureka server
* [user-webservice](user-webservice/README.md) - User micro-service
* [vendor-webservice](vendor-webservice/README.md) - vendor micro-service
* [product-webservice](product-webservice/README.md) - product for task micro-service
* [api-gateway](api-gateway/README.md) - API gateway that proxies all the micro-services

### Target Architecture
![Target Architecture](/images/Target_Architecture.jpg)

### Application Components
![Components](/images/Application_Components.jpg)

## <a name="using-application"></a>Using the application

### <a name="run_local_mc"></a>Running on local m/c

* You can build all the projects by running the `./build-all-projects.sh` on Mac/Linux systems and then going to each individual folder and running the jars using the `java -jar build/libs/sam<application_name>.jar` command.
* Please refer to the individual readme files on instructions of how to run the services. For demo, you can run the applications in the same order listed above.
        
### <a name="run_docker"></a>Running using docker (**NOTE: NOT WORKING with latest docker 1.8x since the gradle docker task is NOT compatible; also bug in Spring Boot 1.2.x**)
