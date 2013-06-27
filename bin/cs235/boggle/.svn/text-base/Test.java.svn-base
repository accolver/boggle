package cs235.boggle;

import java.util.SortedSet;

public class Test {

	private static final int MAXLENGTH = 4;

	/**
	 * Run tests on an object that implements the BogglePlayer interface
	 */
	public static void main(String[] args) {

		
		
		BogglePlayer boggle = BoggleFactory.createBogglePlayer();

	
		testGetAllValidWordsBeforeDictionary(boggle);
		testDictionary(boggle);
		testBinarySearches(boggle);
		
		boggle.loadDictionary("dictionary.txt");
		if (boggle.isValidPrefix("aa"))
			System.out.println ("YAYAYAYAYAY");

		testGetAllValidWordsTooLow(boggle);
		testSetBoard(boggle);
		testIsOnBoard(boggle);

		
		//String[] s3 = { "H", "E", "B", "E", "Z", "K", "T", "S", "T" };
		//boggle.setBoard(s3);
		//System.out.println(boggle.getAllValidWords(MAXLENGTH));
		//System.out.println(boggle.isOnBoard("skeet"));

		String[] letterArray = { "U", "G", "I", "A", "O", "H", "S", "S", "T",
				"U", "E", "T", "Y", "N", "T", "W" };
		boggle.setBoard(letterArray);
	//	System.out.println ("PATH: " + boggle.isOnBoard("thus"));
		SortedSet l = boggle.getAllValidWords(MAXLENGTH);
		System.out.println(l);
		System.out.println(l.size());
		System.out.println(boggle.isOnBoard("ashen"));

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	private static void testIsOnBoard(BogglePlayer boggle) {
		try {
			boggle.isOnBoard(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {
			boggle.isOnBoard("a");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	private static void testBinarySearches(BogglePlayer boggle) {
		try {
			boggle.isValidWord(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {
			boggle.isValidWord("anything");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		try {
			boggle.isValidPrefix(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		try {
			boggle.isValidPrefix("a");
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

	}

	private static void testGetAllValidWords(BogglePlayer boggle) {
		testGetAllValidWordsTooLow(boggle);
		testGetAllValidWordsBeforeDictionary(boggle);
	}

	private static void testGetAllValidWordsBeforeDictionary(BogglePlayer boggle) {
		try {
			boggle.getAllValidWords(MAXLENGTH);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	}

	private static void testGetAllValidWordsTooLow(BogglePlayer boggle) {
		try {
			boggle.getAllValidWords(-1);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private static void testDictionary(BogglePlayer boggle) {
		testDictionaryNull(boggle);
		testDictionaryBadName(boggle);
	}

	private static void testDictionaryBadName(BogglePlayer boggle) {
		try {
			boggle.loadDictionary("weirdFileName.txt");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private static void testDictionaryNull(BogglePlayer boggle) {
		try {
			boggle.loadDictionary(null);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	private static void testSetBoard(BogglePlayer boggle) {
		setBoardTestNull(boggle);
		setBoardTestNotSquare(boggle);
	}

	private static void setBoardTestNotSquare(BogglePlayer boggle) {
		String[] s1 = null;
		try {
			boggle.setBoard(s1);
		} catch (IllegalArgumentException e) {
			System.out.println("letterArray for setBoard cannot be null");
		}
	}

	private static void setBoardTestNull(BogglePlayer boggle) {
		String[] s2 = { "A", "B", "C", "D" };
		try {
			boggle.setBoard(s2);
		} catch (IllegalArgumentException e) {
			System.out.println("setBoard must receive a square grid");
		}
	}

}
