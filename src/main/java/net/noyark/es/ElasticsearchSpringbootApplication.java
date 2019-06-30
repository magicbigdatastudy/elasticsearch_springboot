package net.noyark.es;

import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@Controller
public class ElasticsearchSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticsearchSpringbootApplication.class, args);
    }

    @Autowired //注入
    private TransportClient client;

}
