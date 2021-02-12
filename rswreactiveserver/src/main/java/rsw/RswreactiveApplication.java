package rsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
import java.util.List;




/**
 * This is de server application.
 * There are 2 client applications:
 * D:\logius\eigenprojecten\reactive\tutorials\rswreactivenettyclient
 * D:\logius\eigenprojecten\reactive\rswreactiveserver
 */

//@EnableDiscoveryClient
//This starts Netty in stead of Tomcat as webserver (dependent on pom)
@SpringBootApplication
public class RswreactiveApplication {

	public static void main(String[] args) {
		SpringApplication.run(RswreactiveApplication.class, args);
	}

}
