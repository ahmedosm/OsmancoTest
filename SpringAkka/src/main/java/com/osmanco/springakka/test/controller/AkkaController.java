package com.osmanco.springakka.test.controller;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import static akka.pattern.Patterns.ask;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osmanco.springakka.dto.TestBodyDTO;
import com.osmanco.springakka.test.controller.Pinger;
import java.util.concurrent.CompletionStage;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import scala.compat.java8.FutureConverters;

@RestController
public class AkkaController {
    private ActorRef pinger;
    private ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
   
    @PostConstruct
    public void initialize() {
        final ActorSystem system = ActorSystem.create("kamon-spring-boot-actor-system");
        pinger = system.actorOf(Props.create(Pinger.class), "pinger");    
    }
    
    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    public CompletionStage sendMail2(@RequestBody TestBodyDTO testBodyDTO, UriComponentsBuilder ucBuilder) throws InterruptedException, Exception {
        System.out.println(" before call akka ");
        return FutureConverters.toJava(ask(pinger, new Pinger.PingMessage(testBodyDTO.getName()), 3000))
                .thenApply(response -> transformMessage("generateVerificationKey   Response :", response))
                .exceptionally(th -> transformMessage("Exception Controller generateVerificationKey  : " + th, null));

    }
    @SuppressWarnings("empty-statement")
    private String transformMessage(String message, Object responce) {
        System.out.println(" message : " + responce);
        try {
            return mapper.writeValueAsString(responce);
        } catch (JsonProcessingException ex) {
            ex.printStackTrace();
            return "Error";
        }
    }
}
