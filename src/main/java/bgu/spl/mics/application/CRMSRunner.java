package bgu.spl.mics.application;

import bgu.spl.mics.application.objects.*;
import bgu.spl.mics.application.services.*;


import java.util.Vector;

/** This is the Main class of Compute Resources Management System application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output a text file.
 */
public class CRMSRunner {
    private static Student.Degree PhD;
    private static Data.Type Images;

    public static void main(String[] args) {
        System.out.println(Thread.activeCount());
        Vector<Model> simbaModels = new Vector<Model>();
        Data YOLO10DATA = new Data(Images,200000);
        Model YOLO10 = new Model("YOLO10", YOLO10DATA);
        Data ResNet9000Data = new Data(Images,200000);
        Model ResNet9000 = new Model("ResNet9000", ResNet9000Data);
        Data LessEfficientNetDATA = new Data(Images,20000);
        Model LessEfficientNet = new Model("YOLO10", LessEfficientNetDATA);
        Data DensestNetData = new Data(Images,20000);
        Model DensestNet = new Model("YOLO10", DensestNetData);
        simbaModels.add(YOLO10);
        simbaModels.add(ResNet9000);
        simbaModels.add(LessEfficientNet);
        simbaModels.add(DensestNet);
        Student simba = new Student("Simba", "CS", Student.Degree.MSc, simbaModels);


        Vector<Model> zazuModels = new Vector<Model>();
        Data VITDATA = new Data(Images,100000000);
        Model VIT = new Model("VIT", VITDATA);
        zazuModels.add(VIT);
        Student zazu = new Student("Zazu", "EE", Student.Degree.PhD, zazuModels);


        Vector<Model> pumbaModels = new Vector<Model>();
        Data BertDATA = new Data(Data.Type.Text,1000000);
        Model Bert = new Model("Bert", BertDATA);
        Data GPT4Data = new Data(Data.Type.Text,1000000);
        Model GPT4 = new Model("GPT4", GPT4Data);
        Data GPT5DATA = new Data(Data.Type.Text,200000);
        Model GPT5 = new Model("GPT5", GPT5DATA);
        Data GPT10Data = new Data(Data.Type.Text,50000);
        Model GPT10 = new Model("GPT10", GPT10Data);
        pumbaModels.add(Bert);
        pumbaModels.add(GPT4);
        pumbaModels.add(GPT5);
        pumbaModels.add(GPT10);
        Student pumba = new Student("Pumba", "CS", Student.Degree.PhD, pumbaModels);

        Vector<Model> timonModels = new Vector<Model>();
        Data PercepetronDATA = new Data(Data.Type.Tabular,1000000);
        Model Percepetron = new Model("Percepetron", PercepetronDATA);
        Data GNNData = new Data(Data.Type.Tabular,1000000);
        Model GNN = new Model("GNN", GNNData);
        Data MoreStyleGANDATA = new Data(Data.Type.Images,100000);
        Model MoreStyleGAN = new Model("MoreStyleGAN", MoreStyleGANDATA);
        Data ConditionalGANData = new Data(Data.Type.Images,500000);
        Model ConditionalGAN = new Model("ConditionalGAN", ConditionalGANData);
        timonModels.add(Percepetron);
        timonModels.add(GNN);
        timonModels.add(MoreStyleGAN);
        timonModels.add(ConditionalGAN);
        Student timon = new Student("Timon", "SE", Student.Degree.MSc,timonModels);

        Cluster c = Cluster.getInstance();

        /*GPU g1 = new GPU ("RTX3090",c);
        GPU g2 = new GPU ("RTX3090",c);
        GPU g3 = new GPU ("RTX2080",c);
        GPU g4 = new GPU ("GTX1080",c);
*/
        CPU c1 = new CPU(32,c);
        CPU c2 = new CPU(32,c);
        CPU c3 = new CPU(32,c);
        CPU c4 = new CPU(16,c);
        CPU c5 = new CPU(16,c);
        CPU c6 = new CPU(16,c);
        CPU c7 = new CPU(16,c);

        Conference conf1 = new Conference("ICML",20000);
        Conference conf2 = new Conference("NeurIPS",25000);
        Conference conf3 = new Conference("CVPR",30000);
        Conference conf4 = new Conference("ECCV",40000);
        Conference conf5 = new Conference("AISTATS",50000);
        Thread threadTimeService = new Thread (new TimeService(1,3));

        Thread gpuThread1 = new Thread(new GPUService("RTX3090"));
        Thread gpuThread2 = new Thread(new GPUService("RTX3090"));
        Thread gpuThread3 = new Thread(new GPUService("RTX2080"));
        Thread gpuThread4 = new Thread(new GPUService("GTX1080"));

        Thread confThread1 = new Thread(new ConferenceService(conf1));
        Thread confThread2 = new Thread(new ConferenceService(conf2));
        Thread confThread3 = new Thread(new ConferenceService(conf3));
        Thread confThread4 = new Thread(new ConferenceService(conf4));
        Thread confThread5 = new Thread(new ConferenceService(conf5));

        Thread cpuThread1 = new Thread(new CPUService(c1));
        Thread cpuThread2 = new Thread(new CPUService(c2));
        Thread cpuThread3 = new Thread(new CPUService(c3));
        Thread cpuThread4 = new Thread(new CPUService(c4));
        Thread cpuThread5 = new Thread(new CPUService(c5));
        Thread cpuThread6 = new Thread(new CPUService(c6));
        Thread cpuThread7 = new Thread(new CPUService(c7));

        Thread studentThread1 = new Thread(new StudentService(simba));
        Thread studentThread2 = new Thread(new StudentService(zazu));
        Thread studentThread3 = new Thread(new StudentService(pumba));
        Thread studentThread4 = new Thread(new StudentService(timon));



        gpuThread1.start();  //1
        gpuThread2.start();  //2
        gpuThread3.start();  //3
        gpuThread4.start();  //4
        confThread1.start();  //5
        confThread2.start();  //6
        confThread3.start();  //7
        confThread4.start();  //8
        confThread5.start();  //9
        cpuThread1.start();  //10
        cpuThread2.start();  //11
        cpuThread3.start();  //12
        cpuThread4.start();  //13
        cpuThread5.start();  //14
        cpuThread6.start();  //15
        cpuThread7.start();  //16
        studentThread1.start();  //17
        studentThread2.start();  //18
        studentThread3.start();  //19
        studentThread4.start();  //20
        threadTimeService.start();  //21
        try {
            gpuThread1.join();
            gpuThread2.join();
            gpuThread3.join();
            gpuThread4.join();
            confThread1.join();
            confThread2.join();
            confThread3.join();
            confThread4.join();
            confThread5.join();
            cpuThread1.join();
            cpuThread2.join();
            cpuThread3.join();
            cpuThread4.join();
            cpuThread5.join();
            cpuThread6.join();
            cpuThread7.join();
            studentThread1.join();
            studentThread2.join();
            studentThread3.join();
            studentThread4.join();
            threadTimeService.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
