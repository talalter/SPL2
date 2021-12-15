package bgu.spl.mics.application.objects;
import java.util.*;

/**
 * Passive object representing the cluster.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * Add all the fields described in the assignment as private fields.
 * Add fields and methoֺds to this class as you see fit (including public methods and constructors).
 */
public class Cluster {
	Set<GPU> gpuSet;
	//Set<CPU> cpuSet;
	PriorityQueue<CPU> CpusQueue;
	int numofprosseseddata;


	private static class ClusterHolder {
		private static Cluster instance = new Cluster();
	}
	private Cluster(){
		this.gpuSet = new HashSet<GPU>();
		this.numofprosseseddata=0;
		this.CpusQueue = new PriorityQueue<CPU>(new Comparator<CPU>() {
			@Override
			public int compare(CPU o1, CPU o2) {
				return (o1.getCores() - o2.getCores());
			}
		});
	}
	public synchronized void recieveDBfromgpu(Vector<DataBatch> vec) {//TODO
	}
	public synchronized Vector<DataBatch> withdrawDB(int maxCapacity){
		return null;
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Cluster getInstance() {
		return ClusterHolder.instance;
	}

	public void addGpu(GPU gpu){
		this.gpuSet.add(gpu);
	}
	public void addCpu(CPU cpu){
		this.CpusQueue.add(cpu);
	}


}