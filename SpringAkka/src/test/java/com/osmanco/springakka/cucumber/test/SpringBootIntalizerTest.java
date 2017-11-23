
package com.osmanco.springakka.cucumber.test;

import com.osmanco.springakka.test.controller.SpringConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
@SpringBootTest(classes = SpringConfiguration.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public class SpringBootIntalizerTest {
    
}
