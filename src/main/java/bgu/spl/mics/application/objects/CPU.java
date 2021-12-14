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

    public int getCores() {
        return cores;
    }
    public int sizeOfCollections(){
        return this.dataCurrentlyProccesing.size();
    }

    Collection<DataBatch> dataCurrentlyProccesing;
    Cluster cluster;
    int time;

    CPU (int cores, Cluster cluster){
        this.cores = cores;
        this.cluster = cluster;
        this.dataCurrentlyProccesing = new Vector<DataBatch>();
    }
    public CPU(Cluster c){
        this.cluster = c;
    }

    public void ProcessData(Vector<DataBatch> dataToProcess){
        switch (dataToProcess.firstElement().getData().getType()) {
            case Images:
                this.time = (32 / this.cores) * 4;
            case Text:
                this.time = (32 / this.cores) * 2;
                ;
            case Tabular:
                this.time = (32 / this.cores);
        }
        for (DataBatch db : dataToProcess){
            db.setProcessed(true);
        }
    }
}
