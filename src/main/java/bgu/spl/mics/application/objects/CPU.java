package bgu.spl.mics.application.objects;

import java.sql.Time;
import java.util.Collection;
import java.util.Comparator;
import java.util.Vector;

/**
 * Passive object representing a single CPU.
 * Add all the fields described in the assignment as private fields.
 * Add fields and methods to this class as you see fit (including public methods and constructors).
 */
public class CPU{
    int cores;
    int time;
    public int getCores() {
        return cores;
    }
    public int sizeOfCollections(){
        return 0;
        // return this.dataCurrentlyProccesing.size();
    }

    Collection<DataBatch> dataCurrentlyProccesing;
    Cluster cluster;

    public CPU (int cores, Cluster cluster){
        this.cores = cores;
        this.cluster = cluster;
        this.dataCurrentlyProccesing = new Vector<DataBatch>();
        this.time = 0;
    }
    public CPU(Cluster c){
        this.cluster = c;
    }

    public void ProcessData(Vector<DataBatch> dataToProcess){
        int timeToProcess;
        for(DataBatch dataBatch : dataToProcess) {
            switch (dataBatch.getData().getType()) {
                case Images:
                    timeToProcess = (32 / this.cores) * 4;
                    if (time > timeToProcess) {
                        time -= timeToProcess;
                    }
                case Text:
                    timeToProcess = (32 / this.cores) * 4;
                    if (time > timeToProcess) {
                        time -= timeToProcess;
                    }
                case Tabular:
                    timeToProcess = (32 / this.cores) * 4;
                    if (time > timeToProcess) {
                        time -= timeToProcess;
                    }
            }
        }
    }

    public void setTime(){
        this.time++;
    }
}