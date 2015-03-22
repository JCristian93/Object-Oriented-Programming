/**
 * This execution entry point class handles the parsing, indexing and lookup of words in a text.
 */
public class Index {

	/**
	 * Execution entry point.
	 *
	 * @param args two strings representing the name of the file that contains the text to index,
	 * and the name of the file containing words to lookup in the text.
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java -cp <classpath> Index <text file> <word file>");
			System.exit(1);
		}

		// TODO: replace with homework implementation:

		String word;

		FileParser textFile = new FileParser(args[0]);
		textFile.open();
		System.out.print("Text words:");
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
}
