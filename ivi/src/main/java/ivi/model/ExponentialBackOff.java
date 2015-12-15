package ivi.model;

public class ExponentialBackOff {
	private long interval;
	private long initialInterval;
	private long maxInterval;
	private long timeElapsed=0;
	private long maxElapsedTime;
	private double multiplier;
	private long nextInterval;
	public void setInitialIntervalMillis(long millis){
		initialInterval=millis;
		interval=initialInterval;
	}
	public void setMaxElapsedTimeMillis(long millis){
		maxElapsedTime=millis;
	}
	public void setMaxIntervalMillis(long millis){
		maxInterval=millis;
	}
	public void setMultiplier(double mult){
		multiplier=mult;
	}
	public long nextBackOffMillis(){
		long interv=interval;
		if(timeElapsed<maxElapsedTime){
			nextInterval=(int) (interval*multiplier);
			if(nextInterval<maxInterval)
				interval=nextInterval;
			timeElapsed+=interval;
			return interv;
		}
		else
			return -1;
	}
	public void reset(){
		timeElapsed=0;
		interval=initialInterval;
	}
}
