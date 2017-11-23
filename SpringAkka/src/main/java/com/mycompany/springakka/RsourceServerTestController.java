package com.mycompany.springakka;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

import static akka.pattern.Patterns.ask;
import akka.util.Timeout;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.concurrent.CompletionStage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import scala.compat.java8.FutureConverters;
import scala.concurrent.Await;

import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

@RestController
public class RsourceServerTestController {

    @Autowired
    private PingPong pingPong;
private ObjectMapper mapper = new ObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> logout(@RequestBody String lol, UriComponentsBuilder ucBuilder) {
        pingPong.testActor();
        return new ResponseEntity<String>("lol", HttpStatus.OK);
    }

    private ActorRef pinger;
    private ActorRef ponger;

    @PostConstruct
    public void initialize() {
        final ActorSystem system = ActorSystem.create("kamon-spring-boot-actor-system");

        pinger = system.actorOf(Props.create(Pinger.class), "pinger");
        ponger = system.actorOf(Props.create(Ponger.class), "ponger");

    }

    @RequestMapping("/hello")
    @ResponseBody
    @Async
    public String sendMail() throws InterruptedException, Exception {
        Timeout timeout = new Timeout(Duration.create(5, "seconds"));
        Future<Object> future = ask(pinger, new Pinger.PingMessage("Ahmed Osman"), 3000);
        String result = (String) Await.result(future, timeout.duration());
        //return ask(pinger, new Pinger.PingMessage("Ahmed Osman"), 3000);
        return result;
    }

    @RequestMapping("/hello2")
    @ResponseBody
    @Async
    public CompletionStage sendMail2() throws InterruptedException, Exception {
        System.out.println(" before call akka ");        
      return FutureConverters.toJava(ask(pinger, new Pinger.PingMessage("Ahmed Osman"), 3000))
                .thenApply(response -> transformMessage("generateVerificationKey   Response :",response))
                .exceptionally(th -> transformMessage("Exception Controller generateVerificationKey  : " + th,null));

    }
    

    @SuppressWarnings("empty-statement")
    private String transformMessage(String message,Object responce){        
       System.out.println(" message : "+responce);
        try {
            return mapper.writeValueAsString(responce);
        } catch (JsonProcessingException ex) {
           ex.printStackTrace();
         return "Error";
        }
    }
}
