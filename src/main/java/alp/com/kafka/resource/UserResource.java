package alp.com.kafka.resource;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alp.com.model.User;

@RestController
@RequestMapping("kafka")
public class UserResource {

	@Autowired
	private KafkaTemplate<String, User> kafkaTemplate;

	private static final String TOPIC = "Kafka_Example";

	@GetMapping("/publish/{name}")
	public String post(@PathVariable("name") final String name) {

		kafkaTemplate.send(TOPIC, new User(name, "Technology"));
		getAllTopics();
		return "Published successfully";
	}

	@GetMapping("/topics")
	public String getAllTopicName() {
		Set<String> topicNames = getAllTopics();
		String result = topicNames.stream().collect(Collectors.joining(" , "));
		return result;
	}
	
	
	// Move all logic the Service level
	@Autowired
	private AdminClient adminClient;

	public Set<String> getAllTopics() {

		ListTopicsOptions options = new ListTopicsOptions();
		options.listInternal(true); // Set to false if you only want user-created topics
		ListTopicsResult topicsResult = adminClient.listTopics(options);
		Set<String> topicNames = null;
		try {
			topicNames = topicsResult.names().get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return topicNames;
	}

}
