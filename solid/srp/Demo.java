import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.net.URL;
// Single Responsibility Priniciple
// Anti Pattern is GOD class which have lots of reponsibility
// Separation of Concern
class Journal{
	private final List<String> entries = new ArrayList<>();
	private static int count = 0;

	public void addEntry(String text){
		entries.add("" + (++count) + ": " + text);
	}

	public void removeEntry(int index){
		entries.remove(index);
	}

	@Override
	public String toString(){
		return String.join(System.lineSeparator(),entries);
	}

	// break the SRP
	public void save(String filename) throws FileNotFoundException{
		try(PrintStream out = new PrintStream(filename)){
			out.println(toString());
		}
	}

	public void load(String filename){

	}

	public void load(URL url){

	}
}
class Demo{
	public static void main(String[] args) throws Exception{
		Journal j = new Journal();
		j.addEntry("I cried today");
		j.addEntry("I ate a bug");

		System.out.println(j);

		j.save("data.txt");
	}
}