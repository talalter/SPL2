package bgu.spl.mics.application.messages;

import bgu.spl.mics.Broadcast;

public class FinishBroadcast implements Broadcast {
    public FinishBroadcast(){
        System.out.println(Thread.currentThread().getName()+ "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~FinishBroadcast 7");
        Thread.currentThread().interrupt();
    }
}
