package com.atome.springkafka.basickafkatemplate;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaApplication.class, args);
	}

	@Bean
	NewTopic hobbit() {
		return TopicBuilder.name("hobbit")
				.partitions(2)
				.replicas(3)
//				.config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
				.build();
	}
}

@RequiredArgsConstructor
@Component
class Producer {

	private final KafkaTemplate<Integer, String> template;

	Faker faker;

	@EventListener(ApplicationStartedEvent.class)
	public void generate() {

		faker = Faker.instance();
		final Flux<Long> interval = Flux.interval(Duration.ofMillis(1_000));

		final Flux<String> quotes = Flux.fromStream(Stream.generate(() -> faker.hobbit().quote()));

		Flux.zip(interval, quotes)
				.map(it -> template.send("hobbit", faker.random().nextInt(42), it.getT2()))
				.blockLast();
	}

}

@Component
@Profile(value={"stream"})
class Consumer {
	@KafkaListener(topics= {"hobbit"}, groupId="spring-boot-kafka")
	public void consume(String quote) {
		System.out.println("received= " + quote);
	}
}

//@Component
//class Consumer {
//
//	@KafkaListener(topics = {"streams-wordcount-output"}, groupId = "spring-boot-kafka")
//	public void consume(ConsumerRecord<String, Long> record) {
//		System.out.println("received = " + record.value() + " with key " + record.key());
//	}
//}