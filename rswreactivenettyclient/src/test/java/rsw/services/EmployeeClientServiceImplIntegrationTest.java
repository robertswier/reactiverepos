package rsw.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import rsw.RswreactiveNettyclientApplication;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import rsw.model.Employee;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = RswreactiveNettyclientApplication.class)
public class EmployeeClientServiceImplIntegrationTest {

    @Autowired
    EmployeeClientServiceImpl testable;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void happyFlow() throws Exception {
        MockWebServer mockWebServer = new MockWebServer();
        mockWebServer.start();

        String id = "1";
        mapper.registerModule(new Jdk8Module()); //to handle the mapping the Optional
        String resp = mapper.writeValueAsString(Arrays.asList(new Employee("1", "robert"), new Employee("2", "peter")));
        MockResponse mockResponse = new MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .setBody(resp)
                .throttleBody(128, 1, TimeUnit.SECONDS);

        mockWebServer.enqueue(mockResponse);

        URI uri = URI.create(mockWebServer.url("").toString() + "getemp/2");
        List<Employee> result = testable.getEmployeesBlocking(uri);

        assertEquals(2, result.size());

        RecordedRequest request = mockWebServer.takeRequest();
        assertEquals("/getemp/2", request.getPath());
        mockWebServer.shutdown();
    }

}
