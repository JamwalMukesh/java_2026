import java.util.Random;

class Metrics{
	private long count = 0;
	private volatile double average = 0.0;

	public synchronized void addSample(long sample){
		double currentSum = average * count;
		count++;
		average = (currentSum + sample) / count;
	}

	public double getAverage(){
		return average;
	}
}

class BusinessLogic extends Thread{

	private Metrics metrics;
	private Random random = new Random();

	public BusinessLogic(Metrics metrics){
		this.metrics = metrics;
	}

	@Override
	public void run(){
		while(true){
			long start = System.currentTimeMillis();

			try{
				Thread.sleep(random.nextInt(10));
			}catch(InterruptedException ex){

			}

			long end = System.currentTimeMillis();

			metrics.addSample(end - start);	
		}
	}
}

class MetricPrinter extends Thread{

	private Metrics metrics;

	public MetricPrinter(Metrics metrics){
		this.metrics = metrics;
	}

	@Override
	public void run(){
		while(true){
			long start = System.currentTimeMillis();

			try{
				Thread.sleep(100);
			}catch(InterruptedException ex){

			}

			double currentAverage = metrics.getAverage();

			System.out.println("Current Average is " + currentAverage);
		}
	}
}

class Demo1{
	public static void main(String[] args){
		Metrics metrics = new Metrics();

		BusinessLogic businessLogicThread1 = new BusinessLogic(metrics);
		BusinessLogic businessLogicThread2 = new BusinessLogic(metrics);

		MetricPrinter metricsPrinterThread = new MetricPrinter(metrics);

		businessLogicThread1.start();
		businessLogicThread2.start();
		metricsPrinterThread.start();
	}
}