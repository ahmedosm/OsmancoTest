/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.osmanco.springakka.test.controller;

import akka.actor.UntypedActor;

class Ponger extends UntypedActor {
 static final class PongMessage {}

 public void onReceive(Object message) throws Exception {
    if (message instanceof Pinger.PingMessage) {
        System.out.println(" onReceive Ponger ");                
        getSender().tell(new PongMessage(), getSelf());
    }
    else unhandled(message);
 }
}
