import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;
import java.util.Random;

class Demo3{

	private static final String OUTPUT_FILE = "matrices";
	private static final int N = 10;
	private static final int NUMBER_OF_MATRIX_PAIRS = 100000;

	public static void main(String[] args) throws IOException{
		File file = new File(OUTPUT_FILE);
		FileWriter fileWriter = new FileWriter(file);		
		createMatrices(fileWriter);
		fileWriter.flush();
		fileWriter.close();
	}

	static void createMatrices(FileWriter fileWriter) throws IOException{

	}

	static float[] createRow(Random random){
		float[] row = new float[N];
        for (int i = 0; i < N; i++) {
            row[i] = random.nextFloat() * random.nextInt(100);
        }
        return row;
	}

	static float[][] createMatrix(Random random){
		float matrix[][] = new float[N][N];
        for (int i = 0; i < N; i++) {
            matrix[i] = createRow(random);
        }
        return matrix;
	}

	static void saveMatrixToFile(FileWriter fileWriter,float[][] matrix) throws IOException{

	}
}