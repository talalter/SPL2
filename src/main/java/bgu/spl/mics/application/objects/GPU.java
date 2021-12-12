package bgu.spl.mics.application.objects;

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
    private Model model;

    public Vector<DataBatch> getUnProcessedDataBatchVector() {
        return unProcessedDataBatchVector;
    }

    public Vector<DataBatch> getProcessedDataBatchVector() {
        return processedDataBatchVector;
    }

    private Cluster cluster;
    private Type type;
    private int dataBatchSize;
    private Vector<DataBatch> unProcessedDataBatchVector;
    private Vector<DataBatch> processedDataBatchVector;
    GPU(Type type, Model model, Cluster cluster){
        this.type = type;
        this.model = model;
        this.cluster = cluster;
        switch (this.type){
            case RTX3090:
                this.dataBatchSize = 32;
            case RTX2080:
                this.dataBatchSize = 16;
            case GTX1080:
                this.dataBatchSize = 8;
        }
        this.unProcessedDataBatchVector =  new Vector<DataBatch>();
        this.processedDataBatchVector =  new Vector<DataBatch>();


    }
    public GPU(Cluster c){
        this.cluster = c;
    }

    public void SendData(){
        /* TODO
         *   take data from model
         *  */
    }

    public void ReciveMessageToTrainModel(Model m){
        int batches = m.getData().getSize()/1000;
        for(int i=0;i<batches;i++){
            DataBatch db=new DataBatch(m.getData(),i*1000,this);
            this.unProcessedDataBatchVector.add(db);
        }
        sendBatches(unProcessedDataBatchVector);
    }

    public void sendBatches(Vector v){

    }

    public void reciveBatches(Vector v){

    }







}
