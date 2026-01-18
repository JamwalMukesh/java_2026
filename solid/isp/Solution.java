// Interface Segregation Principle (ISP)
class Document{

}

interface Printer{
	void print(Document d);
}

interface Scanner{
	void scan(Document d);
}

// YAGNI = You Ain't Going to Need It

class JustAPrinter implements Printer{

	@Override
	public void print(Document d){

	}
}

class Photocopier implements Printer, Scanner{

	@Override
	public void print(Document d){

	}

	@Override
	public void scan(Document d){

	}

}

interface MultiFunctionDevice extends Printer, Scanner { }

class MultiFunctionMachine implements MultiFunctionDevice{

	private Printer printer; //Decorator pattern
	private Scanner scanner;

	public MultiFunctionMachine(Printer printer, Scanner scanner){
		this.printer = printer;
		this.scanner = scanner;
	}

	@Override
	public void print(Document d){
		this.printer.print(d);
	}

	@Override
	public void scan(Document d){
		this.scanner.scan(d);
	}
}