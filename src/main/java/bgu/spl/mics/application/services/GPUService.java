package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.TrainModelEvent;
import bgu.spl.mics.application.objects.GPU;

/**
 * GPU service is responsible for handling the
 * {@link TrainModelEvent} and {@link TestModelEvent},
 * in addition to sending the {@link DataPreProcessEvent}.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class GPUService extends MicroService {

    int tick = 0;
    private GPU gpu;
    Class c1 = TrainModelEvent.class;
    Class c2 = TestModelEvent.class;

    public GPUService(String name,GPU gpu) {
        super(name);
        this.gpu = gpu;
    }
    public GPUService(String name){
        super("Change_This_Name");
        mb.register(this);
        System.out.println("StudentService Constructor");
    }
    @Override
    protected void initialize() {
        // TODO Implement this
        subscribeEvent(TrainModelEvent.class, (TrainModelEvent g)->System.out.println("123"));
        subscribeBroadcast(TickBroadcast.class , (TickBroadcast e) -> {tick++;
            System.out.println("GPUCALLBACK");});
    }
    //jhhj
}
