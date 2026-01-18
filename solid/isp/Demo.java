// Interface Segregation Principle (ISP)
class Document{

}
interface Machine{
	void print(Document d);
	void fax(Document d);
	void scan(Document d);
}

class MultiFunctionPrinter implements Machine{
	
	@Override
	public void print(Document d){

	}
	
	@Override
	public void fax(Document d){

	}

	@Override
	public void scan(Document d){

	}
}

class OldFashionedPrinter implements Machine{

	@Override
	public void print(Document d){

	}
	
	@Override
	public void fax(Document d){

	}

	@Override
	public void scan(Document d){

	}
}