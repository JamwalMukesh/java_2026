import java.util.List;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;
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

}
// Move the Separation of concern and handling all operation related to persistence
class Persistence{
	
	public void saveToFile(Journal journal,String filename,boolean overwrite) throws FileNotFoundException{
		if(overwrite || new File(filename).exists()){
			try(PrintStream out = new PrintStream(filename)){
				out.println(journal.toString());
			}
		}
	}

	public void load(String filename){

	}

	public void load(URL url){

	}
}
class Demo1{
	public static void main(String[] args) throws Exception{
		Journal j = new Journal();
		j.addEntry("I cried today");
		j.addEntry("I ate a bug");

		System.out.println(j);

		Persistence persistence = new Persistence();
		persistence.saveToFile(j,"data1.txt",true);
	}
}