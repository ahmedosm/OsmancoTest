/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springakka;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import static akka.pattern.Patterns.ask;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import scala.concurrent.Future;

@Service
public class PingPong {
    private  ActorRef pinger;
    private  ActorRef ponger;
    @PostConstruct
    public void initialize() {
        final ActorSystem system = ActorSystem.create("kamon-spring-boot-actor-system");

         pinger = system.actorOf(Props.create(Pinger.class), "pinger");
         ponger = system.actorOf(Props.create(Ponger.class), "ponger");    
    
    }
    
    public void testActor(){
        System.out.println("actor : "+pinger);
        //  pinger.tell(new Ponger.PongMessage(), ponger);
        Future<Object> ask = ask(pinger, new Pinger.PingMessage("Ahmed Osman"), 3000);
        System.out.println("Message : "+ask.toString());
        
    }
    
}