package rsw;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class RswreactiveNettyclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(RswreactiveNettyclientApplication.class, args);
	}

	@Bean
	public WebClient webClient() {
		return WebClient.create();
//	public WebClient webClient() { return getWebClient();
	}

//	private WebClient getWebClient() {
//		TcpClient tcpClient = TcpClient
//				.create()
//				.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60000)
//				.doOnConnected(connection -> {
//					connection.addHandlerLast(new ReadTimeoutHandler(60000, TimeUnit.MILLISECONDS));
//					connection.addHandlerLast(new WriteTimeoutHandler(60000, TimeUnit.MILLISECONDS));
//				});
//
//		return WebClient
//				.builder()
//				.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
//				//limit response to 16MB:
//				.codecs(configurer -> configurer
//						.defaultCodecs()
//						.maxInMemorySize(16 * 1024 * 1024))
//				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
//				.build();
//	}
}
