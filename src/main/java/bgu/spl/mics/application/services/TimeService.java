package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TickBroadcast;
import bgu.spl.mics.application.messages.FinishBroadcast;
import org.w3c.dom.ls.LSOutput;

import java.util.Timer;
import java.util.TimerTask;

/**
 * TimeService is the global system timer There is only one instance of this micro-service.
 * It keeps track of the amount of ticks passed since initialization and notifies
 * all other micro-services about the current time tick using {@link TickBroadcast}.
 * This class may not hold references for objects which it is not responsible for.
 *
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class TimeService extends MicroService{
	Timer timer;
	TimerTask task;
	long tickTime;
	long duration;
	public TimeService(int tickTime, long duration) {
		super("Timer");
		this.duration = duration;
		this.tickTime = tickTime;


	}
	@Override
	protected void initialize() {
		subscribeBroadcast(FinishBroadcast.class, a -> {
			Thread.currentThread().interrupt();
			terminate();
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!"+"Thread time finish"+"!!!!!!!!!!!!!!!!!!!!");
		});
		timer = new Timer();
		System.out.println(Thread.currentThread().getName());
		final long[] duration = {this.duration};
		task = new TimerTask() {
			@Override
			public void run() {
				if(duration[0] > 0){
					if(!Thread.currentThread().isInterrupted()) {
						sendBroadcast(new TickBroadcast());
						System.out.println(Thread.currentThread().getName());
						System.out.println("sendBroadcast");
						duration[0]--;
					}
				}
				else{
					System.out.println("Finish");
					task.cancel();
					timer.cancel();
					timer.purge();
					terminate();
					sendBroadcast(new FinishBroadcast());
					Thread.currentThread().interrupt();

				}

			}
		};
		System.out.println(Thread.currentThread().getName());
		timer.scheduleAtFixedRate(task,0, this.tickTime*1000);
	}


}
