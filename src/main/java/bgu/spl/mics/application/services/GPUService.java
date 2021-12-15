package bgu.spl.mics.application.services;

import bgu.spl.mics.Event;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.TrainModelEvent;
import bgu.spl.mics.application.objects.DataBatch;
import bgu.spl.mics.application.objects.GPU;
import bgu.spl.mics.application.objects.Model;

import java.util.PriorityQueue;
import java.util.Vector;

/**
 * GPU service is responsible for handling the
 * {@link TrainModelEvent} and {@link TestModelEvent},
 * in addition to sending the {@link}.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class GPUService extends MicroService {
    PriorityQueue<Event<Model>> messegequeue;//queue for messeges i cant handle at the momoent
    int tick = 0;
    private GPU gpu;
    Event<Model> modelEvent;
    private boolean isprocess;
    public GPUService(String name,GPU gpu) {
        super(name);
        this.gpu = gpu;
    }
    public GPUService(String name){
        super(name);
    }

    @Override
    protected void initialize() {
        subscribeBroadcast(TickBroadcast.class , (TickBroadcast e) -> {setTick();});
        subscribeEvent(TrainModelEvent.class, (TrainModelEvent event)-> {gpu.startProcessingTrainEvent(event);});
        subscribeEvent(TestModelEvent.class, (TestModelEvent event)-> {gpu.startProcessingTestEvent(event);});

    }
    private void setEvent(Event<Model> ev){
        this.modelEvent=ev;
        this.isprocess=true;
    }
    /*
    change the status of the current event to trained
    make it complete
     */
    public void finish(Vector<DataBatch> vec){

    }
    /*
    recieving the event for the first time
    if the gpu is working it will move it to the queue
     */
    public void handleTrainEvent(Event<Model> ev){
        if(isprocess)
            messegequeue.add(ev);
    }

    private void setTick() {
        if(gpu.isInproccese()) {
            tick++;

        }
    }

}