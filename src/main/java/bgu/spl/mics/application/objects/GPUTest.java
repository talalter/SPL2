package bgu.spl.mics.application.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class GPUTest {

    static GPU gpu;
    static Cluster cluster;
    static Model model;
    static Data data;
    static Student student;
    static Vector<DataBatch>  dbVec;
    @Before
    public void setUp() throws Exception {
        GPU gpu = new GPU (GPU.Type.RTX3090,model,cluster);
        Cluster cluster = Cluster.getInstance();
        cluster.addGpu(gpu);
        Model model = new Model ("check", data, student);
        Data data = new Data (Data.Type.Images,4000);
        Vector<DataBatch>  dbVec = new Vector<DataBatch>();
        //Student student = new Student("name","departmant", Student.Degree.PhD);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void sendData() {
    }

    @Test
    public void testReciveMessageToTrainModel() {

        int size = gpu.getUnProcessedDataBatchVector().size();
        gpu.ReciveMessageToTrainModel();
        int size_after=gpu.getUnProcessedDataBatchVector().size();
        assertTrue(size+4==size_after);
    }

    @Test
    public void testReciveBatches() {
        int size = gpu.getProcessedDataBatchVector().size();
        dbVec.add(new DataBatch(data,0,gpu));
        dbVec.add(new DataBatch(data,0,gpu));
        dbVec.add(new DataBatch(data,0,gpu));
        dbVec.add(new DataBatch(data,0,gpu));
        gpu.reciveBatches(dbVec);
        int size_after=gpu.getProcessedDataBatchVector().size();
        assertTrue(size+4==size_after);
    }
}