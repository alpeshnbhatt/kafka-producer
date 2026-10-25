package alp.com.kafka.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alp.com.kafka.feigncall.UserFeignCall;
import alp.com.kafka.feigncall.UserHttpInterfaceCall;
import alp.com.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

	@Autowired
	private  KafkaTemplate<String, User> kafkaTemplate;
	@Autowired
	private  UserFeignCall userFeignCall;
	@Autowired
	private  UserHttpInterfaceCall userHttpInterfaceCall;

//	public UserResource(UserFeignCall userFeignCall, UserHttpInterfaceCall userHttpInterfaceCall,KafkaTemplate<String, User> kafkaTemplate) {
//		this.userFeignCall = userFeignCall;
//		this.userHttpInterfaceCall = userHttpInterfaceCall;
//		this.kafkaTemplate = kafkaTemplate;
//	}

	private static final String TOPIC = "Kafka_Example";

	@GetMapping("/publish/{name}")
	public String post(@PathVariable("name") final String name) {

		kafkaTemplate.send(TOPIC, new User(name, "Technology"));
		return "Published successfully";
	}

	@GetMapping("/feign/topics")
	public String getAllTopicName() {
		String result = userFeignCall.getTopics();
		return result;
	}

	@GetMapping("/http/topics")
	public String getAllHttpTopicName() {
		String result = userFeignCall.getTopics();
		return result;
	}

}
