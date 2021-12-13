package bgu.spl.mics.application.services;

import bgu.spl.mics.Broadcast;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.TickBroadcast;

import java.sql.Time;
import java.time.Clock;
import java.util.Timer;

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
	Clock clock;
	int timeTicks;
	long duration;
	public TimeService(int timeTicks) {
		super("Timer");
		this.timeTicks = timeTicks;
		timer = new Timer();
		System.out.println("Time Service Constructor");
		this.duration = 500000000;
	}
	public Timer getTimer(){
		return this.timer;
	}
	@Override
	protected void initialize() {

		clock = Clock.systemDefaultZone();
		long milliSeconds = clock.millis();
		System.out.println(milliSeconds+ " " + clock.millis());
		while(clock.millis()<duration+milliSeconds) {
			System.out.println(clock.millis());
			sendBroadcast(new TickBroadcast());
			try {

				Thread.currentThread().sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		terminate();

	}

}
