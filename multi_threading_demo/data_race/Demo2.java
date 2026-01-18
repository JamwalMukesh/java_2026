// Data Race Solution
// Synchronization of methods which modify shared variables
// Declaration of shared variables with the volatile keyword
class Demo2{
	public static void main(String[] args){

		SharedClass sharedClass = new SharedClass();

		Thread thread1 = new Thread(() -> {
			for(int i = 0; i < Integer.MAX_VALUE; i++){
				sharedClass.increment();
			}
		});

		Thread thread2 = new Thread(() -> {
			for(int i = 0; i < Integer.MAX_VALUE; i++){
				sharedClass.checkDataForRace();
			}
		});

		thread1.start();
		thread2.start();
	}
}

class DataRaceException extends RuntimeException{

	public DataRaceException(String message){
		super(message);
	}
}

class SharedClass{
	volatile int x = 0;
	volatile int y = 0;

	public void increment(){
		x++;
		y++;
	}

	public void checkDataForRace(){
		if(y > x){
			System.out.println("y > x - Data Race is detected.");
		}
	}
}