package bgu.spl.mics.application;

import bgu.spl.mics.Future;
import bgu.spl.mics.application.objects.*;
import bgu.spl.mics.application.services.GPUService;
import bgu.spl.mics.application.services.StudentService;
import bgu.spl.mics.application.services.TimeService;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicReference;

/** This is the Main class of Compute Resources Management System application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output a text file.
 */
public class CRMSRunner {
    private static Student.Degree PhD;
    private static Data.Type Images;

    public static void main(String[] args) {
        Vector<Model> models = new Vector<Model>();

        Cluster c = Cluster.getInstance();
        GPU g1 = new GPU(c);
        GPU g2 = new GPU(c);
        c.addGpu(g1);
        c.addGpu(g2);
        CPU c1 = new CPU(c);
        CPU c2 = new CPU(c);
        c.addCpu(c1);
        c.addCpu(c2);
        Student student = new Student("Simba", "Computer Science",PhD,models);
        models.add(new Model(student));
        models.add(new Model(student));
        Data data = new Data(Images,200000);
        Future<Model> f = new Future();
        Model m = new Model("YOLO10",data,student);


        //Thread threadGpuService1 = new Thread (new GPUService("2",g2));
        //Thread threadGpuService2 = new Thread (new GPUService("1",g1));
        //Thread threadStudent = new Thread ( new StudentService("check",student));
        Thread threadTimeService = new Thread (new TimeService(1,2));


        //threadGpuService1.start();
        //threadGpuService2.start();
        //threadStudent.start();
        threadTimeService.start();

        try {
            //threadGpuService1.join();
            //threadGpuService2.join();
            //threadStudent.join();
            threadTimeService.join();
            System.out.println("CRMSRunner 50");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finito");



        /*Thread studentThread =  new Thread(studentService);
        studentThread.start();
        try {
            studentThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
