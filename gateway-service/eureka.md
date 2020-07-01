# Eureka

Eureka is a service registry/discovery service which was developed by Netflix and
is adopted into the Spring Cloud project. When services startup their first task
upon successful startup is to register with Eureka.  They send a request to where
they expect Eureka to be to inform Eureka that they are now available.

Eureka then adds this service to its registry.  Eureka will then occasionally send 
a health check to each service - this is called the Eureka heartbeat. Eureka sends
a request to the registered services to determine if they are still alive and
responding. If services fail the health check then they are removed from registry or
temporarily disabled.

As the service registry, our Eureka server is one of the most important parts of our 
MSA.  The service registry is also a common SPOF (Single Point of Failure) in our
architecture and for creating a fault-tolerant application we should take special 
care in ensuring that we have fallback strategies for failure of the service 
registry.