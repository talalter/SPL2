package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TrainModelEvent;
import bgu.spl.mics.application.objects.Student;

/**
 * Student is responsible for sending the {@link TrainModelEvent},
 * {@link TestModelEvent} and {@link PublishResultsEvent}.
 * In addition, it must sign up for the conference publication broadcasts.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class StudentService extends MicroService {

    Student student;
    Class c1 = TrainModelEvent.class;
    Class c2 = TestModelEvent.class;
    public StudentService(String name, Student student) {
        super("Change_This_Name");
        this.student=student;
        System.out.println("StudentService Constructor");
    }

    @Override
    protected void initialize() {
        mb.register(this);
        mb.subscribeEvent(c1, this);
        mb.subscribeEvent(c2,this);
    }
}
