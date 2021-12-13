package bgu.spl.mics.application.services;

import bgu.spl.mics.Event;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.TrainModelEvent;
import bgu.spl.mics.application.objects.GPU;
import bgu.spl.mics.application.objects.Model;
import bgu.spl.mics.application.objects.Student;

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

    int tick = 0;
    private GPU gpu;

    public GPUService(String name,GPU gpu) {
        super(name);
        this.gpu = gpu;
    }
    public GPUService(String name){
        super(name);
    }
    public void startProcessingTestEvent(TestModelEvent event){
        double prob=Math.random();
        if(event.getStudentdegree()== Student.Degree.MSc & prob>0.4)
            event.getModel().setResult(Model.Result.Good);
        else if(event.getStudentdegree()== Student.Degree.PhD & prob>0.2)
            event.getModel().setResult(Model.Result.Good);
        else
            event.getModel().setResult(Model.Result.Bad);
    }
    public void startProcessingTrainEvent(TrainModelEvent event){}
    @Override
    protected void initialize() {
        subscribeBroadcast(TickBroadcast.class , (TickBroadcast e) -> {tick++;});
        subscribeEvent(TrainModelEvent.class, (TrainModelEvent event)-> startProcessingTrainEvent(event));
        subscribeEvent(TestModelEvent.class, (TestModelEvent event)-> startProcessingTestEvent(event));

    }

}
