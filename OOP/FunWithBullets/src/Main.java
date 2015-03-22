import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		// TODO
		/*trebuie sa fac citirea din fisier , sa construiesc ecranul ;
		*/
/*		
		//  This execution entry point class handles the parsing, indexing and lookup of words in a text.
		 

				String screen_sizex , screen_sizey , word;

				FileParser textFile = new FileParser(args[0]);
				textFile.open();
				while ((word = textFile.getNextWord()) != null) {
					System.out.print(" " + word);
				}
				textFile.close();

				FileParser prefixFile = new FileParser(args[1]);
				prefixFile.open();
				System.out.print("\nPrefixes:");
				while ((word = prefixFile.getNextWord()) != null) {
					System.out.print(" " + word);
				}
				prefixFile.close();
			}
		
//******************************************************************************8

		
		// Opens a file for reading and provides a stream of words resulting from its parsing.
		
		public final class FileParser {

			String filePath;
			BufferedReader reader;
			Queue<String> wordBuffer;

			public FileParser(String filePath) {
				this.filePath = filePath;
				this.reader = null;
				this.wordBuffer = new LinkedList<String>();
			}

	
			public void open() {
				if (reader != null) {
					throw new IllegalStateException("File already opened for reading");
				}
				try {
					reader = new BufferedReader(new FileReader(filePath));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}

	
			public void close() {
				wordBuffer.clear();
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			}

	
			public String getNextWord() {

				if (!wordBuffer.isEmpty()) {
					return wordBuffer.poll();
				}

				// refill word buffer
				String[] nextWords = new String[0];
				while (nextWords.length == 0) {
					nextWords = parseNextLine();
					if (nextWords == null) {
						return null;
					}
				}
				wordBuffer.addAll(Arrays.asList(nextWords));
				return wordBuffer.poll();
			}

			private String[] parseNextLine() {
				String line;
				try {
					line = reader.readLine();
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				if (line == null) {
					return null;
				}
				return line.toLowerCase().split("[^a-zA-Z']+");
			}
		
*/
		 BufferedReader br = new BufferedReader(new FileReader("file.txt"));
		    try {
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append('\n');
		            line = br.readLine();
		        }
		        String everything = sb.toString();
		    } finally {
		        br.close();
		    }
	}
}

