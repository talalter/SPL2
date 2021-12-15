package bgu.spl.mics.application.services;

import bgu.spl.mics.Event;
import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.TrainModelEvent;
import bgu.spl.mics.application.objects.*;

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
    Vector<Event<Model>> messegequeue;//queue for messeges i cant handle at the momoent
    int tick = 0;
    private GPU gpu;
    TrainModelEvent modelEvent;
    private boolean isprocess;
    private String typestr;
    public GPUService(String name, String type) {
        super(name);
        this.typestr=type;
    }

    @Override
    protected void initialize() {
        subscribeBroadcast(TickBroadcast.class , (TickBroadcast e) -> {setTick();});
        subscribeEvent(TrainModelEvent.class, (TrainModelEvent event)-> {handleEvent(event);});
        subscribeEvent(TestModelEvent.class, (TestModelEvent event)-> {handleEvent(event);});

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
    public void handleEvent(TrainModelEvent ev){
        if(gpu.isInprocces())
            messegequeue.add(ev);
        else {
            gpu = new GPU(typestr, ev.getModel(), cluster);
            modelEvent =ev;
        }
    }
    public void handleEvent(TestModelEvent ev){
        if(gpu.isInprocces())
            messegequeue.add(ev);
        else {
            gpu = new GPU();
            gpu.startProcessingTestEvent(ev);
            complete(ev,ev.getModel());
        }
    }

    private void setTick() {
        if(gpu.isFinished()){
            complete(modelEvent,modelEvent.getModel());
            if(!messegequeue.isEmpty()){
                if(messegequeue.get(0).getClass()==TrainModelEvent.class)
                    handleEvent((TrainModelEvent)messegequeue.remove(0));
                else
                    handleEvent((TestModelEvent)messegequeue.remove(0));
            }
        if(gpu.isInprocces()) {
            tick++;
            gpu.onTick();
            }
        }
    }

}