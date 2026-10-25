package alp.com.kafka.feigncall;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

@HttpExchange(url = "http://localhost:8081")
public interface UserHttpInterfaceCall {
	@GetExchange("/topics")
	public String getTopics();
}
