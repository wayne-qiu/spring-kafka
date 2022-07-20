https://developer.confluent.io/learn-kafka/spring/process-messages-with-kafka-streams/

https://github.com/confluentinc/demo-scene/tree/master/spring-kafka-ccloud-course/spring-kafka-ccloud

https://www.baeldung.com/spring-boot-kafka-streams

- basickafkatemplate  
    application.properties
- streams  
    - @EnableKafkaStreams
    - @Component
    @Profile(value={"stream"})
    class Consumer {
    - run directly
    - https://www.baeldung.com/spring-boot-kafka-streams
- avro
    - application.properties
        spring.profiles.active=avro
    - xxx.avsc
      - generated code `Hobbit`
      - ConsumerRecord<Integer, Hobbit>
    - https://www.confluent.io/blog/schema-registry-avro-in-spring-boot-application-tutorial/


