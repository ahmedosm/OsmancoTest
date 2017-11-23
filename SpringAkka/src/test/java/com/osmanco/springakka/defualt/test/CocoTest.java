
package com.osmanco.springakka.defualt.test;

import com.osmanco.springakka.dto.ResponseDTO;
import com.osmanco.springakka.test.controller.SpringConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
 
import java.util.HashMap;
import org.springframework.boot.context.embedded.LocalServerPort;
/**
 *
 * @author ahmed.anwer
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SpringConfiguration.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class CocoTest {
    @LocalServerPort
    private int port;
   @Autowired
    private TestRestTemplate restTemplate;
 
    @Test
    public void test() {
        System.out.println("in lol test"+port);
        ResponseEntity<ResponseDTO> response = this.restTemplate.getForEntity("/", ResponseDTO.class, new HashMap<>());
        Assert.assertEquals("200", response.getBody().getStatusCode());
        response.getBody().geteDescription().equals("lol");
    }
    
}
