https://developer.confluent.io/learn-kafka/spring/process-messages-with-kafka-streams/

https://github.com/confluentinc/demo-scene/tree/master/spring-kafka-ccloud-course/

https://confluent.cloud/login

https://www.baeldung.com/spring-boot-kafka-streams

- basickafkatemplate  
    application.properties
- streams  
    - @EnableKafkaStreams
    - application.properties
        - spring.profiles.active=stream
    - run directly
        - localhost:8080/count/dragon  
    - https://www.baeldung.com/spring-boot-kafka-streams
- avro
    - application.properties
        spring.profiles.active=avro
    - xxx.avsc
      - generated code `Hobbit`  
       ./gradlew build  
       springBoot {  
	        mainClass = "com.atome.springkafka.streams.SpringKafkaStreamApplication"  
       }
      - ConsumerRecord<Integer, Hobbit>
    - https://www.confluent.io/blog/schema-registry-avro-in-spring-boot-application-tutorial/


