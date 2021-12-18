package bgu.spl.mics.application;

import bgu.spl.mics.application.objects.*;
import bgu.spl.mics.application.services.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Vector;

/** This is the Main class of Compute Resources Management System application. You should parse the input file,
 * create the different instances of the objects, and run the system.
 * In the end, you should output a text file.
 */
public class CRMSRunner {

    public static void main(String[] args) {
//        Cluster cluster = Cluster.getInstance();
//        Vector<Thread> threads = new Vector<Thread>();
//        File input = new File("C:/Users/talal/Downloads/input.json");
//        try {
//            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
//            JsonObject fileObject = fileElement.getAsJsonObject();
//            JsonArray students = (JsonArray) fileObject.get("Students");
//            for (JsonElement student : students) {
//                Vector<Model> vectorModels = new Vector<Model>();
//                JsonObject studentObject = student.getAsJsonObject();
//                String nameStudent = studentObject.get("name").getAsString();
//                String department = studentObject.get("department").getAsString();
//                String status = studentObject.get("status").getAsString();
//                JsonArray models = (JsonArray) student.getAsJsonObject().get("models");
//                for (JsonElement model : models) {
//                    JsonObject modelObject = model.getAsJsonObject();
//                    String nameModel = modelObject.get("name").getAsString();
//                    String type = modelObject.get("type").getAsString();
//                    int size = modelObject.get("size").getAsInt();
//                    Data data = new Data(type, size);
//                    Model m = new Model(nameModel, data);
//                    vectorModels.add(m);
//                }
//                threads.add(new Thread(new StudentService(new Student(nameStudent, department, status, vectorModels))));
//            }
//            JsonArray GPUS = (JsonArray) fileObject.get("GPUS");
//            for (JsonElement gpu : GPUS) {
//                threads.add(new Thread(new GPUService(gpu.getAsString())));
//            }
//            JsonArray CPUS = (JsonArray) fileObject.get("CPUS");
//            for (JsonElement cpu : CPUS) {
//                CPU cpu_ = new CPU(cpu.getAsInt());
//                threads.add(new Thread(new CPUService(cpu_)));
//                cluster.addCPU(cpu_);
//            }
//            JsonArray conferences = (JsonArray) fileObject.get("Conferences");
//            for (JsonElement conference : conferences) {
//                JsonObject conferenceObject = conference.getAsJsonObject();
//                String nameConferences = conferenceObject.get("name").getAsString();
//                int date = conferenceObject.get("date").getAsInt();
//                threads.add(new Thread(new ConferenceService(new Conference(nameConferences, date))));
//            }
//            int tickTime = fileObject.get("TickTime").getAsInt();
//            long duration = fileObject.get("Duration").getAsLong();
//            threads.add(new Thread(new TimeService(1, 7)));
//            for (Thread thread : threads){
//                thread.start();
//            }
//            for (Thread thread : threads){
//                thread.join();
//            }
//        } catch (FileNotFoundException | InterruptedException e) {
//            e.printStackTrace();
//        }

        Vector<Model> simbaModels = new Vector<Model>();
        Data YOLO10DATA = new Data("Images", 2000);
        Model YOLO10 = new Model("YOLO10", YOLO10DATA);
        Data ResNet9000Data = new Data("Images", 2000);
        Model ResNet9000 = new Model("ResNet9000", ResNet9000Data);
        Data LessEfficientNetDATA = new Data("Images", 2000);
        Model LessEfficientNet = new Model("YOLO10", LessEfficientNetDATA);
        Data DensestNetData = new Data("Images", 2000);
        Model DensestNet = new Model("YOLO10", DensestNetData);
        simbaModels.add(YOLO10);
        simbaModels.add(ResNet9000);
        simbaModels.add(LessEfficientNet);
        simbaModels.add(DensestNet);
        Student simba = new Student("Simba", "CS", "Msc", simbaModels);


        Vector<Model> zazuModels = new Vector<Model>();
        Data VITDATA = new Data("Images", 10000);
        Model VIT = new Model("VIT", VITDATA);
        zazuModels.add(VIT);
        Student zazu = new Student("Zazu", "EE", "PhD", zazuModels);


        Vector<Model> pumbaModels = new Vector<Model>();
        Data BertDATA = new Data("Text", 10000);
        Model Bert = new Model("Bert", BertDATA);
        Data GPT4Data = new Data("Text", 10000);
        Model GPT4 = new Model("GPT4", GPT4Data);
        Data GPT5DATA = new Data("Text", 2000);
        Model GPT5 = new Model("GPT5", GPT5DATA);
        Data GPT10Data = new Data("Text", 5000);
        Model GPT10 = new Model("GPT10", GPT10Data);
        pumbaModels.add(Bert);
        pumbaModels.add(GPT4);
        pumbaModels.add(GPT5);
        pumbaModels.add(GPT10);
        Student pumba = new Student("Pumba", "CS", "Phd", pumbaModels);

        Vector<Model> timonModels = new Vector<Model>();
        Data PercepetronDATA = new Data("Tabular", 1000);
        Model Percepetron = new Model("Percepetron", PercepetronDATA);
        Data GNNData = new Data("Tabular", 1000);
        Model GNN = new Model("GNN", GNNData);
        Data MoreStyleGANDATA = new Data("Images", 1000);
        Model MoreStyleGAN = new Model("MoreStyleGAN", MoreStyleGANDATA);
        Data ConditionalGANData = new Data("Images", 5000);
        Model ConditionalGAN = new Model("ConditionalGAN", ConditionalGANData);
        timonModels.add(Percepetron);
        timonModels.add(GNN);
        timonModels.add(MoreStyleGAN);
        timonModels.add(ConditionalGAN);
        Student timon = new Student("Timon", "SE","Msc", timonModels);

        CPU c1 = new CPU(32);
        CPU c2 = new CPU(32);
        CPU c3 = new CPU(32);
        CPU c4 = new CPU(16);
        CPU c5 = new CPU(16);
        CPU c6 = new CPU(16);
        CPU c7 = new CPU(16);

        Cluster cluster = Cluster.getInstance();
        cluster.addCPU(c1);
        cluster.addCPU(c2);
        cluster.addCPU(c3);
        cluster.addCPU(c4);
        cluster.addCPU(c5);
        cluster.addCPU(c6);
        cluster.addCPU(c7);

        Conference conf1 = new Conference("ICML", 20000);
        Conference conf2 = new Conference("NeurIPS", 25000);
        Conference conf3 = new Conference("CVPR", 30000);
        Conference conf4 = new Conference("ECCV", 40000);
        Conference conf5 = new Conference("AISTATS", 50000);
        Thread threadTimeService = new Thread(new TimeService(1, 15));

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

