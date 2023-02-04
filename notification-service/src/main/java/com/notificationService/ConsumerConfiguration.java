package com.notificationService;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ConsumerConfiguration {

    @Autowired
    Environment env;

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
        config.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.kafka.topics"));
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    // if Object type of data is to be consumed, use following Consumerfactory.
    /*
     * @Bean public ConsumerFactory<String, Object> consumerFactory(){ Map<String,
     * Object> config = new HashMap<>();
     *
     * config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("spring.kafka.bootstrap-servers"));
     * config.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("spring.kafka.topics"));
     * config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
     * StringDeserializer.class);
     * config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
     * StringDeserializer.class);
     *
     * return new DefaultKafkaConsumerFactory<>(config); }
     *
     * @Bean public ConcurrentKafkaListenerContainerFactory<String, Object>
     * kafkaListenerContainerFactory(){
     * ConcurrentKafkaListenerContainerFactory<String, Object> factory = new
     * ConcurrentKafkaListenerContainerFactory<>();
     * factory.setConsumerFactory(consumerFactory()); return factory; }
     */
}
