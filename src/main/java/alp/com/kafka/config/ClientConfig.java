package alp.com.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import alp.com.kafka.feigncall.UserFeignCall;
import alp.com.kafka.feigncall.UserHttpInterfaceCall;

@Configuration
public class ClientConfig {

	@Bean
	public UserHttpInterfaceCall userFeignCall() {
		RestClient restClient = RestClient.builder().baseUrl("http://localhost:8081").build();

		RestClientAdapter adapter = RestClientAdapter.create(restClient);
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

		return factory.createClient(UserHttpInterfaceCall.class);
	}
}