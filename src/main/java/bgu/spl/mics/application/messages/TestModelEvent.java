package bgu.spl.mics.application.messages;

import bgu.spl.mics.Message;
import bgu.spl.mics.application.objects.Model;

public class TestModelEvent implements Message {
    private Model model;
    public TestModelEvent(Model model){
        this.model = model;
        System.out.println("TEST MODEL EVENT CONSTRUCTOR");
    }

}
