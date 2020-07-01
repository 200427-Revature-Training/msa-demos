# Hystrix

Hystrix is a defensive programming tool designed by Netflix to create fault-tolerant applications by leveraging a circuit breaker design pattern.

Hystrix employs a circuit pattern with the goal of allowing services which struggling to have a chance to catch up.  It accomplishes this by providing a fallback method that can be invoked in the case that the primary method is not available or unable to respond effectively.  By temporarily rerouting traffic, the struggling service has a better chance to recover.  After some period of time, Hystrix will retry the original handler to see if it works. Once it starts positive responses, Hystrix will return to normal functionality.

Hystrix also builds in a monitoring tool for this, which allows us to track service outages and problem endpoints.


Note: To Enable Hystrix, you must add the @EnableCircuitBreaker annotation to your configuration.