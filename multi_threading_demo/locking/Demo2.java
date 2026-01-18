import java.util.Random;

// Deadlock using Train Crossing Analogy
// Conditions for Deadlock
// Mutual Exclusion - Only one thread can have exclusive access to a resource
// Hold and Wait - At least one thread is holding a resource and is waiting for another resource
// Non-preemptive allocation - A resource is released only after the thread is done using it.
// Circular wait - A chain of at least two thread each one is holding one resource and waiting
//					for another resource

// Solution
// Avoid Circular wait - Enforce a strict order in lock acquisition
// Change the order of lock on the resource and map them in an order 
// Other techniques
// Deadlock detection - Watchdog
// Thread interruption (not possible with synchronized)
// tryLock operations (not possible with synchronized)

class Demo2{
	public static void main(String[] args){
		Intersection intersection = new Intersection();

		Thread trainAThread = new Thread(new TrainA(intersection));
		Thread trainBThread = new Thread(new TrainB(intersection));

		trainAThread.start();
		trainBThread.start();
	}
}

class Intersection{
	private Object roadA = new Object();
	private Object roadB = new Object();

	public void takeRoadA(){
		synchronized(roadA){
			System.out.println("Road A is locked by thread " + Thread.currentThread().getName());

			synchronized(roadB){
				System.out.println("Train is passing through road A");
				try{
					Thread.sleep(1);	
				}catch(InterruptedException e){

				}
			}
		}
	}

	public void takeRoadB(){
		synchronized(roadA){
			System.out.println("Road A is locked by thread " + Thread.currentThread().getName());

			synchronized(roadB){
				System.out.println("Train is passing through road B");
				try{
					Thread.sleep(1);	
				}catch(InterruptedException e){
					
				}
			}
		}
	}
}

class TrainA implements Runnable{

	private Intersection intersection;
	private Random random = new Random();

	public TrainA(Intersection intersection){
		this.intersection = intersection;
	}

	@Override
	public void run(){
		while(true){
			long sleepingTime = random.nextInt(50);
			
			try{
				Thread.sleep(sleepingTime);	
			}catch(InterruptedException e){
				
			}

			intersection.takeRoadA();
		}
	}
}

class TrainB implements Runnable{
	
	private Intersection intersection;
	private Random random = new Random();

	public TrainB(Intersection intersection){
		this.intersection = intersection;
	}

	@Override
	public void run(){
		while(true){
			long sleepingTime = random.nextInt(50);
			
			try{
				Thread.sleep(sleepingTime);	
			}catch(InterruptedException e){
				
			}

			intersection.takeRoadB();
		}
	}
}