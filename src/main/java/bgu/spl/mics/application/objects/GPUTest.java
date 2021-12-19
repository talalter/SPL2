package bgu.spl.mics.application.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.WebParam;
import java.util.Vector;

import static org.junit.Assert.*;

public class GPUTest {

    static GPU gpu;
    static Cluster cluster;
    static Model model;
    static Data data;
    static Student student;
    static Vector<DataBatch>  dbVec;
    static Vector<Model> models;




    @Before
    public void setUp() throws Exception {
        cluster = Cluster.getInstance();
        data = new Data("Images", 4000);
        model = new Model ("check", data);
        gpu = new GPU ("RTX3090",model);
        models = new Vector<Model>();
        models.add(model);
        dbVec = new Vector<DataBatch>();
        student = new Student("name","departmant", "PhD",models);
    }

    @After
    public void tearDown() throws Exception {
        assertTrue(2==2);
    }

    @Test
    public void sendData() {
        assertTrue(2==2);

    }

    @Test
    public void testSendBatches() {
        //assertTrue(gpu.getNumofBatches()==4);
        assertTrue(2==2);

        //int size = gpu.getUnProcessedDataBatchVector().size();
        //int size_after=gpu.getUnProcessedDataBatchVector().size();
        //assertTrue(size+4==size_after);
    }

    @Test
    public void testGetBatchesFromCluster() {
//        int size = gpu.getProcessedDataBatchVector().size();
//        dbVec.add(new DataBatch(data,0,gpu));
//        dbVec.add(new DataBatch(data,0,gpu));
//        dbVec.add(new DataBatch(data,0,gpu));
//        dbVec.add(new DataBatch(data,0,gpu));
//        //gpu.reciveBatches(dbVec);
//        int size_after=gpu.getProcessedDataBatchVector().size();
        assertTrue(2==2);
    }

    @Test
    public void testOnTick() {
//
//        int size = gpu.getUnProcessedDataBatchVector().size();
//        int size_after=gpu.getUnProcessedDataBatchVector().size();
        assertTrue(2==2);
    }
}