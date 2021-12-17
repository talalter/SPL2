package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.FinishBroadcast;
import bgu.spl.mics.application.objects.CPU;

/**
 * CPU service is responsible for handling the {@link DataPreProcessEvent}.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class CPUService extends MicroService {
    CPU cpu;
    int tick = 0;
    public CPUService(CPU cpu) {
        super("CPU");
        this.cpu = cpu;
    }

    @Override
    protected void initialize() {
        // TODO Implement this
        subscribeBroadcast(FinishBroadcast.class, a -> {
            Thread.currentThread().interrupt();
            terminate();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+Thread.currentThread().getName()+"!!!!!!!!!!!!!!!!!!!!");
        });

        subscribeBroadcast(TickBroadcast.class , tb -> {cpu.setTime();});

    }
}
