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

		String word = new String ();
		RadixTree tema = new RadixTree();
		tema.set_contor();

		FileParser textFile = new FileParser(args[0]);
		textFile.open();

		while ((word = textFile.getNextWord()) != null) {
			//cuvintele de introdus
			tema.add(word);
		}
		textFile.close();
		FileParser prefixFile = new FileParser(args[1]);
		prefixFile.open();

		while ((word = prefixFile.getNextWord()) != null) {
			//cuvintele din prefix
			tema.find(word);
		}
//		tema.Debug (tema);
		prefixFile.close();

	}
}
