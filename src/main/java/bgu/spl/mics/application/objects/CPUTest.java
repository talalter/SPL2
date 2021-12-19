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
    static DataBatch db;

    @Before
    public void setUp() throws Exception {
        cpu = new CPU(16);
        Model model = new Model ("check", data);

        gpu = new GPU ("RTX3090",model);
        Cluster cluster = Cluster.getInstance();
            cluster.addCPU(cpu);
        Data data = new Data("Images", 4000);
        db = new DataBatch(data,0,gpu);

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
        t1.start();
        cpu.ProcessData(new DataBatch(data,0,gpu));
        assertTrue(db.isProcessed());
    }
}