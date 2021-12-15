package bgu.spl.mics.application.services;

import bgu.spl.mics.Broadcast;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TickBroadcast;

import java.sql.Time;
import java.time.Clock;
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
	int tickTime;
	long duration;
	public TimeService(int tickTime, long duration) {
		super("Timer");
		this.duration = duration;
		this.tickTime = tickTime;
		timer = new Timer();
		TimerTask task = null;

	}
	@Override
	protected void initialize() {
		System.out.println(Thread.currentThread().getName());
		System.out.println("microService line 37");
		final long[] duration = {this.duration};
		task = new TimerTask() {

			@Override
			public void run() {
				if(duration[0] > 0){
					sendBroadcast(new TickBroadcast());
					System.out.println(Thread.currentThread().getName());
					System.out.println("lalala");
					duration[0]--;
				}
				else{
					timer.cancel();
					System.out.println("now start terminate");
					terminate();
					Thread.currentThread().interrupt();
				}
			}

		};
		timer.scheduleAtFixedRate(task,0, this.tickTime*1000);
	}


}
