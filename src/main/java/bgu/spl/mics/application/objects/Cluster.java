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
	//CPUSDBforcomperator IS just for compartor TO KNOW HOW MANY ITEMS EACH CPU IS HANDLING AT THE MOMENT
	private HashMap<CPU, Vector<DataBatch>> CPUSDBforcomperator;
	private HashMap<CPU, Vector<DataBatch>> CPUSDB;
	PriorityQueue<CPU> CpusQueue;
	int numofprosseseddata;
	Vector<DataBatch> unProcessDB;
	HashMap<GPU, Vector<DataBatch>> ProcessDB;
	Object lockCPUSDB = new Object();
	private int howManyProcessed;

	private static class ClusterHolder {
		private static Cluster instance = new Cluster();
	}

	private Cluster() {
		this.numofprosseseddata = 0;
		this.unProcessDB = new Vector<DataBatch>();
		this.ProcessDB = new HashMap<GPU, Vector<DataBatch>>();
		this.CPUSDB = new HashMap<CPU, Vector<DataBatch>>();
		this.CPUSDBforcomperator = new HashMap<CPU, Vector<DataBatch>>();
		this.CpusQueue = new PriorityQueue<CPU>(new Comparator<CPU>() {
			@Override
			// 				return (o1.getCores() / Math.max(1, CPUSDBforcomperator.get(o1).size()) - o2.getCores() / Math.max(1, CPUSDBforcomperator.get(o2).size()));
			public int compare(CPU o1, CPU o2) {
				return (o1.getCores() - o2.getCores());
			}
		});
	}

	public void recieveDBfromgpu(Vector<DataBatch> vec) {
		synchronized (lockCPUSDB) {
			if(vec!=null) {
				for (DataBatch db : vec) {
					CPU cpu = CpusQueue.poll();
					if (cpu != null) {
						CPUSDB.get(cpu).add(db);
						CPUSDBforcomperator.get(cpu).add(db);
						CpusQueue.add(cpu);
					}
				}
			}
		}
	}

	public synchronized Vector<DataBatch> withdrawDB(int maxCapacity, GPU gpu) {
		Vector temp = new Vector<DataBatch>();
		for (int i = 0; i < maxCapacity; i++) {
			if (ProcessDB.get(gpu) != null && ProcessDB.get(gpu).size() != 0)
				temp.add(ProcessDB.get(gpu).remove(0));
		}
		return temp;
	}

	public Vector<DataBatch> withdrawDB(CPU cpu) {
		synchronized (lockCPUSDB) {
			Vector<DataBatch> temp = CPUSDB.get(cpu);
			CPUSDB.remove(cpu);
			CPUSDB.put(cpu, new Vector<DataBatch>());
			return temp;
		}
	}

	public void addCPU(CPU cpu) {
		synchronized (lockCPUSDB) {
			CPUSDBforcomperator.put(cpu, new Vector<DataBatch>());
			CPUSDB.put(cpu, new Vector<DataBatch>());
			CpusQueue.add(cpu);
		}
	}

	public void recieveDBfromcpu(DataBatch db, CPU cpu) {
		if(ProcessDB.containsKey(db.getGpu()))
			ProcessDB.get(db.getGpu()).add(db);
		else {
			ProcessDB.put(db.getGpu(), new Vector<DataBatch>());
			ProcessDB.get(db.getGpu()).add(db);
		}
		if (CPUSDBforcomperator.get(cpu).size() != 0)
			CPUSDBforcomperator.get(cpu).remove(0);
	}
	
	public static Cluster getInstance() {
		return ClusterHolder.instance;
	}


}