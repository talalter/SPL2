package bgu.spl.mics.application.objects;

import bgu.spl.mics.application.messages.TestModelEvent;
import bgu.spl.mics.application.messages.TrainModelEvent;

import java.util.Vector;

/**
 * Passive object representing a single GPU.
 * Add all the fields described in the assignment as private fields.
 * Add fields and methods to this class as you see fit (including public methods and constructors).
 */
public class GPU {

    public GPU() {

    }

    /**
     * Enum representing the type of the GPU.
     */

    enum Type {RTX3090, RTX2080, GTX1080}
    private int capacity;
    private int tickstoTrain;
    private int numofBatches;//number of batches we need to prossses for current model
    private int ticksforCurrentDataBatch;
    private boolean inprocces;
    private boolean isFinished;
    private Model model;
    private Cluster cluster;
    private Type type;
    private DataBatch inprogressdata;
    private Vector<DataBatch> unProcessedDataBatchVector;
    private Vector<DataBatch> processedDataBatchVector; //that data is waiting to be prossessed
    private Vector<DataBatch> TrainedDataVector;
    public GPU(String typeString, Cluster cluster) {
        this.cluster = cluster;
        switch (typeString) {
            case "RTX3090": {
                this.capacity = 32;
                this.tickstoTrain = 1;
                this.type = Type.RTX3090;
            }
            case "RTX2080": {
                this.capacity = 16;
                this.tickstoTrain = 2;
                this.type = Type.RTX2080;
            }
            case "GTX1080": {
                this.capacity = 8;
                this.tickstoTrain = 4;
                this.type = Type.GTX1080;
            }
        }
        this.model = null;
        this.isFinished = false;
        this.unProcessedDataBatchVector = new Vector<DataBatch>();
        this.processedDataBatchVector = new Vector<DataBatch>();
        this.TrainedDataVector = new Vector<DataBatch>();
        this.inprogressdata = null;
        this.inprocces = false;
        //this.numofBatches = this.model.getData().getSize() / 1000;
        for (int i = 0; i < numofBatches; i++) {
            DataBatch db = new DataBatch(this.model.getData(), i * 1000, this);
            this.unProcessedDataBatchVector.add(db);
        }
    }
    public void SendData(){
        /* TODO
         *   take data from model
         *  */
    }

    public void ReciveMessageToTrainModel(){
        int batches = this.model.getData().getSize()/1000;
        for(int i=0;i<batches;i++){
            DataBatch db=new DataBatch(this.model.getData(),i*1000,this);
            this.unProcessedDataBatchVector.add(db);
        }
        sendBatches(unProcessedDataBatchVector);
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
    public void startProcessingTrainEvent(TrainModelEvent event){

    }

    public void sendBatches(Vector v){

    }

    public void reciveBatches(Vector v){

    }

    public void changeModel(Model model){
        this.model = model;
    }







}