package bgu.spl.mics.application.services;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.*;
import bgu.spl.mics.application.objects.Model;
import bgu.spl.mics.application.objects.Student;

import java.util.Vector;

/**
 * Student is responsible for sending the {@link TrainModelEvent},
 * {@link TestModelEvent} and {@link PublishResultsEvent}.
 * In addition, it must sign up for the conference publication broadcasts.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 *     Callback<TrainModelEvent> cb = new Callback<TrainModelEvent>() {
 *         @Override
 *         public void call(TrainModelEvent c) {
 *             System.out.println("I wish");
 *         }
 *     };
 */
public class StudentService extends MicroService {


    int a = 0;
    Student student;

    Vector<Model> models = new Vector<Model>();
    Vector<Model> modelsToPublish = new Vector<Model>();
    private Model.Status Trained;

    public StudentService(Student student) {
        super("Student");
        this.student = student;
    }

    private void printModels(){
        for (Model model :modelsToPublish){
            System.out.println(model.getName());
        }
    }
    private void onTick(){
        if(student.getModels()!=null&&student.getModels().size()>0) {
            Model m = student.getModels().firstElement();
            if (m.getStatus() == Model.Status.Trained) {
                System.out.println("sssssssssssssssssssss                   49");
                TestModelEvent testModelEvent = new TestModelEvent(this.student.getStatus(), m);
                Future<Model.Result> futureTest = sendEvent(testModelEvent);
                if (testModelEvent.getModel().getResult() == Model.Result.Good) {
                    System.out.println("sssssssssssssssssssss                   53");
                    modelsToPublish.add(m);
                    this.student.upgradePublications();
                    sendEvent(new PublishResultsEvent(m));
                }
                student.getModels().remove(0);
                if(student.getModels().size()>0)
                     sendEvent(new TrainModelEvent(student.getModels().firstElement()));
                System.out.println("sssssssssssssssssssss                   60");

            }
        }
    }

    @Override
    protected synchronized void initialize() {
        Model m=student.getModels().firstElement();
        TrainModelEvent trainModelEvent = new TrainModelEvent(m);
        sendEvent(trainModelEvent);
        subscribeBroadcast(FinishBroadcast.class, a -> {
            Thread.currentThread().interrupt();
            terminate();
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!" + "Thread student finish" + "!!!!!!!!!!!!!!!!!!!");
            printModels();
        });
        subscribeBroadcast(PublishConferenceBroadcast.class, (PublishConferenceBroadcast pcb) -> {
            pcb.getConference().getModels().firstElement();
        });
        if (student.getModels() == null) {

            return;
        }
        subscribeBroadcast(TickBroadcast.class,a -> onTick());
    }
}