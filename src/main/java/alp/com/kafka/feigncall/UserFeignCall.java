package alp.com.kafka.feigncall;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "kafka-consumer", url = "http://localhost:8081")
public interface UserFeignCall {

	@GetMapping("/topics")
	String getTopics();

}
