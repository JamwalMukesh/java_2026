import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

class Demo1{
	public static void main(String[] args){
		SharedBuffer sharedBuffer = new SharedBuffer();

		Thread producer = new Thread(new Producer(sharedBuffer));
		Thread consumer = new Thread(new Consumer(sharedBuffer));

		producer.start();
		consumer.start();
	}
}
class SharedBuffer{
	static final int BUFFER_SIZE = 5;

	Queue<Integer> buffer = new LinkedList<>();

	Semaphore empty = new Semaphore(BUFFER_SIZE);
	Semaphore full = new Semaphore(0);
	Semaphore mutex = new Semaphore(1);
}
class Producer implements Runnable{
	SharedBuffer sharedBuffer;

	Producer(SharedBuffer sharedBuffer){
		this.sharedBuffer = sharedBuffer;
	}

	@Override
	public void run(){
		int item = 0;
		try{
			while(true){
				sharedBuffer.empty.acquire(); // wait for empty slot
				sharedBuffer.mutex.acquire(); // enter critical section

				System.out.println("Produced: " + item);
				sharedBuffer.buffer.add(item++);

				sharedBuffer.mutex.release(); // exit critical section
				sharedBuffer.full.release(); // signal item available

				Thread.sleep(500);
			}
		}catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
	}
}
class Consumer implements Runnable{
	SharedBuffer sharedBuffer;

	Consumer(SharedBuffer sharedBuffer){
		this.sharedBuffer = sharedBuffer;
	}

	@Override
	public void run(){
		try{
			while(true){
				sharedBuffer.full.acquire(); // wait for item
				sharedBuffer.mutex.acquire(); // enter critical section

				int item = sharedBuffer.buffer.poll();
				System.out.println("Consumed: " + item);

				sharedBuffer.mutex.release(); // exit critical section
				sharedBuffer.empty.release(); // signal empty slot

				Thread.sleep(800);
			}
		}catch(InterruptedException ex){
			Thread.currentThread().interrupt();
		}
	}
}