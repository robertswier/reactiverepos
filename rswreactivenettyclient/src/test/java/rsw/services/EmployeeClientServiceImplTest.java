package rsw.services;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.WebClient;
import rsw.model.Employee;

import java.net.URI;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
class EmployeeClientServiceImplTest {

    private WireMockServer wireMockServer;

    private EmployeeClientServiceImpl testable;

    private WebClient webClient;

    private String uri;

    @AfterEach
    void stop() {
        wireMockServer.stop();
    }

    @Test
    void getEmployees() {
        String amount = "5";
        webClient = WebClient.create(uri);
        wireMockServer = new WireMockServer(wireMockConfig().dynamicPort());
        wireMockServer.start();
        uri = "http://localhost:" + wireMockServer.port() + "/getemp/" + amount;
        configureFor("localhost", wireMockServer.port());
        testable = new EmployeeClientServiceImpl(webClient);

        List<Employee> result = testable.getEmployees(URI.create(uri));
        System.out.println("result=" + result.size());

    }
}