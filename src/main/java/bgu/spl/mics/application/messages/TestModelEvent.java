package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.objects.Model;
import bgu.spl.mics.application.objects.Student;

public class TestModelEvent implements Event<Model> {
    Student.Degree studentDegree;
    Model model;
    public TestModelEvent(Student.Degree studentDegree,Model model) {
        this.studentDegree = studentDegree;
        this.model=model;
    }

    public Model getModel() {
        return model;
    }

    public Student.Degree getStudentDegree() {
        return studentDegree;
    }


}