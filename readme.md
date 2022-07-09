- basickafkatemplate  
    application.properties
- streams  
    - @EnableKafkaStreams
    - @Component
    @Profile(value={"stream"})
    class Consumer {
    - run directly
- avro  
    application.properties
        spring.profiles.active=avro
