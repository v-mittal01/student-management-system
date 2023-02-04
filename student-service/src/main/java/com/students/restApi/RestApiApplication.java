package com.students.restApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiApplication.class, args);
    }

    /*@Bean
    public RestTemplate restTemplate() {      //Not used currently. Instead, KafkaTemplate is being used.
        return new RestTemplate();
    }*/

}
