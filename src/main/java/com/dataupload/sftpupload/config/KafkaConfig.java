package com.dataupload.sftpupload.config;

import com.dataupload.sftpupload.event.CustomerCsvGeneratedEvent;
import com.dataupload.sftpupload.event.CustomerRequestCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    // =========================================================
    // PRODUCER 1
    // CustomerRequestCreatedEvent
    // =========================================================

    @Bean
    public ProducerFactory<String, CustomerRequestCreatedEvent>
    producerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JacksonJsonSerializer.class
        );

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, CustomerRequestCreatedEvent>
    kafkaTemplate() {

        return new KafkaTemplate<>(producerFactory());
    }


    // =========================================================
    // PRODUCER 2
    // CustomerCsvGeneratedEvent
    // =========================================================

    @Bean
    public ProducerFactory<String, CustomerCsvGeneratedEvent>
    customerCsvGeneratedProducerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        config.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class
        );

        config.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JacksonJsonSerializer.class
        );

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, CustomerCsvGeneratedEvent>
    customerCsvGeneratedKafkaTemplate() {

        return new KafkaTemplate<>(
                customerCsvGeneratedProducerFactory()
        );
    }


    // =========================================================
    // CONSUMER
    // CustomerRequestCreatedEvent
    // =========================================================

    @Bean
    public ConsumerFactory<String, CustomerRequestCreatedEvent>
    consumerFactory() {

        Map<String, Object> config = new HashMap<>();

        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9092"
        );

        config.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                "transformation-service"
        );

        config.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class
        );

        config.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JacksonJsonDeserializer.class
        );

        // Allow Kafka to deserialize our event class
        config.put(
                JacksonJsonDeserializer.TRUSTED_PACKAGES,
                "com.dataupload.sftpupload.event"
        );

        return new DefaultKafkaConsumerFactory<>(config);
    }


    // =========================================================
    // KAFKA LISTENER CONTAINER
    // =========================================================

    @Bean(name = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<
            String,
            CustomerRequestCreatedEvent>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<
                String,
                CustomerRequestCreatedEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}