/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.springakka;

import akka.actor.UntypedActor;

public class Pinger extends UntypedActor {
 static final class PingMessage {
 private final String pMessage;
 public PingMessage(String pMessage){
     this.pMessage=pMessage;
 }

        public String getpMessage() {
            return pMessage;
        }

 }

 public void onReceive(Object message) throws Exception {
    if (message instanceof Pinger.PingMessage){ 
        PingMessage pm=(PingMessage)message;
         System.out.println(" onReceive Pinger "); 
        getSender().tell(new TellMessage(pm.getpMessage()+"lol"), getSelf());
    }
    else unhandled(message);
}
}
