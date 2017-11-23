
package com.osmanco.springakka.cucumber.test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osmanco.springakka.dto.ResponseDTO;
import com.osmanco.springakka.test.controller.SpringConfiguration;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
/**
 *
 * @author ahmed.anwer
 */

public class AccountTest extends SpringBootIntalizerTest{
    @LocalServerPort
    private int port;
    private String url;
    private ClientResponse response;
    private ObjectMapper mapper = new ObjectMapper();
    private final static Logger log = LoggerFactory.getLogger(AccountTest.class);

    @Given("^I access resource accounturl url \"([^\"]*)\"$")
    public void i_access_resource_accounturl_url(String url) {
        // this.url ="http://" + System.getenv("HOSTNAME") + ":"+
        // System.getenv("LOGIN_CONTROLLER_PORT") +url; 
        log.info("port : "+port);
        this.url = "http://localhost:"+port + url;
    }

    @When("^i send the addAccountRequst json data$")
    public void i_send_the_addAccountRequst_json_data(String requestDTO) {
        try {
            
            log.info("Url : " + this.url);
            log.info("requestDTO  addAccountRequst :" + requestDTO);
            Client client = Client.create();
            WebResource webResource = client.resource(url);
            response = webResource.accept("application/json").type("application/json").post(ClientResponse.class,
                    requestDTO);

        } catch (Throwable th) {
           log.error("Exception in test addAccountRequst " + th);
        }
    }

    @Then("^the response code for send addAccountRequst service should be (\\d+) in response Body$")
    public void the_response_code_for_send_addAccountRequst_service_should_be_in_response_Body(String responseBody) throws Exception {

        String output = response.getEntity(String.class).toString();
        log.info("Test case addAccount responce" + output);
        ResponseDTO responseDTO = mapper.readValue(output, ResponseDTO.class);
        Assert.assertEquals(responseBody, responseDTO.getStatusCode());
        log.info("Test case success");

    }

}
