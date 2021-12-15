package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.Message;
import bgu.spl.mics.application.objects.Model;
import bgu.spl.mics.application.objects.Student;

public class TestModelEvent implements Event<Model> {
    Student.Degree studentdegree;
    Model model;
    public TestModelEvent(Student.Degree studentdegree,Model model) {
        this.studentdegree = studentdegree;
        this.model=model;
    }

    public Model getModel() {
        return model;
    }

    public Student.Degree getStudentdegree() {
        return studentdegree;
    }


}