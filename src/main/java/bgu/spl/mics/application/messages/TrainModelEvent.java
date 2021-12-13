package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.Message;
import bgu.spl.mics.application.objects.Model;

public class TrainModelEvent implements Event<Model> {
    public TrainModelEvent(){
        System.out.println("TRAIN MODEL EVENT CONSTRUCTOR");
    }
}
