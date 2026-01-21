import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.List;
import java.util.ArrayList;

class Demo2{
	public static void main(String[] args){
		int numberOfThreads = 10;

		List<Thread> threads = new ArrayList<>();	

		Barrier barrier = new Barrier(numberOfThreads);
		for(int i = 0; i < numberOfThreads; i++){
			threads.add(new Thread(new CoordinatedWorkRunner(barrier)));
		}

		for(Thread thread:threads){
			thread.start();
		}
	}
}
class Barrier{
	private final int numberOfWorkers;
	private final Semaphore semaphore = new Semaphore(0);
	private int counter = 0;
	private final Lock lock = new ReentrantLock();

	Barrier(int numberOfWorkers){
		this.numberOfWorkers = numberOfWorkers;
	}

	public void waitForOthers() throws InterruptedException{
		lock.lock();
		boolean isLastWorker = false;
		try{
			counter++;

			if(counter == numberOfWorkers){
				isLastWorker = true;
			}
		}finally{
			lock.unlock();
		}

		if(isLastWorker){
			semaphore.release(numberOfWorkers - 1);
		}else{
			semaphore.acquire();
		}
	}
}
class CoordinatedWorkRunner implements Runnable{
	private final Barrier barrier;

	CoordinatedWorkRunner(Barrier barrier){
		this.barrier = barrier;
	}

	@Override
	public void run(){
		try{
			task();
		}catch(InterruptedException ex){

		}
	}

	void task() throws InterruptedException{
		System.out.println(Thread.currentThread().getName() + " part 1 of the work is finished.");

		barrier.waitForOthers();

		System.out.println(Thread.currentThread().getName() + " part 2 of the work is finished.");
	}
}