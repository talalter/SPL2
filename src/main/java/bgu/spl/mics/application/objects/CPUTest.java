package bgu.spl.mics.application.objects;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class CPUTest {
    static Thread t1;
    static CPU cpu;
    static GPU gpu;
    static Cluster cluster;
    static Data data;
    static Vector<DataBatch> dbVec;
    static Student student;
    static Model model;

    @Before
    public void setUp() throws Exception {
        cpu = new CPU(cluster);
        Model model = new Model ("check", data, student);

        gpu = new GPU (GPU.Type.RTX3090,model,cluster);
        Cluster cluster = Cluster.getInstance();
        cluster.addCpu(cpu);
        Data data = new Data(Data.Type.Images, 4000);
        Vector<DataBatch> dbVec = new Vector<DataBatch>();

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testProcessData() {
        Thread t1 = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {

                }
            }
        };
        dbVec.add(new DataBatch(data,0,gpu));
        dbVec.add(new DataBatch(data,0,gpu));
        dbVec.add(new DataBatch(data,0,gpu));
        dbVec.add(new DataBatch(data,0,gpu));
        t1.start();
        cpu.ProcessData(dbVec);
        assertTrue(dbVec.firstElement().isProcessed());
    }
}