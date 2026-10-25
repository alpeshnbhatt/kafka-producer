package alp.com.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringBootKafkaProduceApp {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootKafkaProduceApp.class, args);
	}
}
