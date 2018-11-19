
import java.util.List;

import com.aditya.Acme.Acme;
import com.aditya.Acme.Service.CertificateService;
import com.aditya.Acme.model.Certificate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Acme.class}, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)

public class AcmeTest {

    @Autowired
    CertificateService certificateService;

    private RestTemplate restTemplate = new RestTemplate();
    String baseUrl = "http://localhost:8080";

    //creating a new certificate, will return HTTP 204 if successful
    @Test
    public void createNewCertificate() throws InterruptedException {

        ResponseEntity<Void> response = restTemplate.getForEntity(baseUrl + "/cert/av", Void.class);
        Thread.sleep(15000);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    //Populate with 2 dummy certificates and then fetch the list of certificates
    public void getAllCertificates() throws InterruptedException {
        ResponseEntity<Void> response = restTemplate.getForEntity(baseUrl + "/cert/dummy1", Void.class);
        ResponseEntity<Void> response2 = restTemplate.getForEntity(baseUrl + "/cert/dummy2", Void.class);

        ResponseEntity<Void> responseEntity = restTemplate.getForEntity(baseUrl + "/certs",Void.class);
        Thread.sleep(12000);
        assertEquals(HttpStatus.OK,responseEntity.getStatusCode());
    }
}
