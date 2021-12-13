package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.PublishResultsEvent;
import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TrainModelEvent;
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
    TrainModelEvent t1 = new TrainModelEvent(new Model());
    Class c1 = t1.getClass();
    Class trainModelEventClass = TrainModelEvent.class;
    Class TestModelEventClass = TestModelEvent.class;
    private Model.Status Trained;
    public StudentService(String name, Student student) {
        super(name);
        this.student = student;
    }

    @Override
    protected void initialize() {

        subscribeEvent(PublishResultsEvent.class, a -> System.out.println("LALALA"));
        for (Model m : student.getModels()){
            sendEvent(new TrainModelEvent(m));
        }
        /*for (Model m: student.getModels()){
            if(m.getStatus()==Trained){
                sendEvent(new TestModelEvent(m))
            }
        }*/

    }
}

