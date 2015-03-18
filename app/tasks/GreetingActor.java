package tasks;

import akka.actor.UntypedActor;
import akka.dispatch.BoundedMessageQueueSemantics;
import akka.dispatch.RequiresMessageQueue;
import play.*;
import play.mvc.*;

public class GreetingActor extends UntypedActor implements RequiresMessageQueue<BoundedMessageQueueSemantics> {

    public static final String MESSAGE_GREETING = "message_greeting";

    @Override
    public void onReceive(Object message) throws Exception {

        if (message.equals(MESSAGE_GREETING)) {
            Logger.trace("trace");
            Logger.debug("debug");
            Logger.info("info");
            Logger.warn("warn");
            Logger.error("error");
        }
    }
}
