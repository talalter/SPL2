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
	Set<CPU> cpuSet;
	private static class ClusterHolder {
		private static Cluster instance = new Cluster();
	}
	private Cluster(){
		this.gpuSet = new HashSet<GPU>();
		this.cpuSet = new HashSet<CPU>();
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
		this.cpuSet.add(cpu);
	}

}
