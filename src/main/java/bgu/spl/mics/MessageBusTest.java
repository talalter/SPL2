package bgu.spl.mics;

import bgu.spl.mics.application.services.GPUService;
import bgu.spl.mics.example.messages.ExampleBroadcast;
import bgu.spl.mics.example.messages.ExampleEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageBusTest {

    private static MessageBusImpl mb;
    private static ExampleEvent event;
    private static MicroService ms;
    private static ExampleBroadcast broadcast;
    @BeforeEach
    void setUp() {
        MessageBusImpl mb = MessageBusImpl.getInstance();
        ExampleEvent event = new ExampleEvent("exampleEvent");
        MicroService ms = new GPUService("test");
        ExampleBroadcast broadcast = new ExampleBroadcast("exampleBroadcast");
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testSubscribeEvent() {
        int size_before = mb.getServiceMessage().get(event).size();
        mb.subscribeEvent(event.getClass(),ms);
        int size_after = mb.getServiceMessage().get(event).size();
        assertTrue(size_before+1==size_after);
    }

    @Test
    void testSubscribeBroadcast() {
        int size_before = mb.getServiceMessage().get(broadcast).size();
        mb.subscribeBroadcast(broadcast.getClass(),ms);
        int size_after = mb.getServiceMessage().get(broadcast).size();
        assertTrue(size_before+1==size_after);
    }

    @Test
    void testComplete() {
        assertTrue(!mb.getMessageFuture().get(event).isDone());
        String result = "complete";
        mb.complete(event,result);
        assertTrue(mb.getMessageFuture().get(event).isDone());
    }

    @Test
    void testSendBroadcast() {

        int size_before=mb.getMessageService().get(broadcast.getClass()).size();
        mb.sendBroadcast(broadcast);
        int size_after = mb.getServiceMessage().get(broadcast).size();
        assertTrue(size_before+1==size_after);
    }


    @Test
    void testSendEvent() {
        int size_before=mb.getMessageService().get(event.getClass()).size();
        mb.sendEvent(event);
        int size_after = mb.getServiceMessage().get(event).size();
        assertTrue(size_before+1==size_after);
    }

    @Test
    void testRegister() {
        int size_before = mb.getServiceMessage().size();
        mb.register(ms);
        int size_after = mb.getServiceMessage().size();
        assertTrue(size_before+1==size_after);
    }

    @Test
    void testUnregister() {
        int size_before = mb.getServiceMessage().size();
        mb.unregister(ms);
        int size_after = mb.getServiceMessage().size();
        assertTrue(size_before-1==size_after);
    }

    @Test
    void testAwaitMessage() {
    }
}