# Persistency Challenges in MSA

In a monolithic application with a single database we can rely on the database to do a lot of work for us. For instance, defined foreign keys will automatically ensure that all references are valid maintaining referential integrity at the database level.  We could also be sure that operations that require multiple database actions could happen in an atomic fashion at the database level using transactions, making our database operations ACID compliant.  This will not be possible in MSA.

In Microservices Applications the responsibility of managing referential integrity and data validity in transactional form is left to the developer at the application level. This leads to a number of technical challenges and approaches.

# Synchronous Interservice Communication
When we simply need to validate data in order to complete a task, we can synchronously communicate with the other service to retrieve data that can
be used to validate internal service data.  For instance, if we need to validate that an author ID correctly references an author for the creation of a book in the book-service, we could send a request to the author-service for the author with the given ID. If we get back a valid record, then we can create the Book.  This happens synchronously with the original HTTP request to create the book.

# Asynchronous Interservice Communication
Asynchronous communication occurs when an application action requires subsequent action in other services.  Often this action is mandatory and must be handled even if the service is offline. Because a service outage is a possibility these actions tend to be done asynchronously via messaging. The general pattern is to create a messaging queue which sits on messages until a service is ready to process them, then provides the message to the service. This might happen in a scenario where we delete an author from our service and subsequently want to cascade delete all books by that author.

# Transition from ACID to BASE 
(Basic Availibility, Soft State, and Eventual Consistency)
