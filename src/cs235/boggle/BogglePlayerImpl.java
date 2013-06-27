package cs235.boggle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class BogglePlayerImpl implements BogglePlayer {

	private String[] dictionary;
	private String[][] board;
	private Map map = new HashMap();
	private SortedSet set = new TreeSet();
	private List pathList = new ArrayList<Integer>();
	private List pathListCopy = new ArrayList<Integer>();
	private List tempArray = new ArrayList<String>();

	/**
	 * Loads the dictionary into a data structure for later use.
	 * 
	 * @param fileName
	 *            A string containing the dictionary to be opened.
	 * @throws IllegalArgumentException
	 *             if fileName is null
	 * @throws IllegalArgumentException
	 *             if fileName cannot be opened.
	 */
	public void loadDictionary(String fileName) {
		if (fileName == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		File file = new File(fileName);
		Scanner in;
		try {
			in = new Scanner(file);
			List<String> tempArray = new ArrayList<String>();

			while (in.hasNext())
				tempArray.add(in.next());

			dictionary = new String[tempArray.size()];
			for (int i = 0; i < tempArray.size(); i++)
				dictionary[i] = tempArray.get(i);

		} catch (FileNotFoundException e) {
			System.out.println("Could not load dictionary");
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Determines if the given word is in the dictionary.
	 * 
	 * @param wordToCheck
	 *            The word to validate
	 * @return true if wordToCheck appears in dictionary, false otherwise.
	 * @throws IllegalArgumentException
	 *             if wordToCheck is null.
	 * @throws IllegalStateException
	 *             if loadDictionary has not been called.
	 */
	public boolean isValidWord(String wordToCheck) {
		if (wordToCheck == null)
			throw new IllegalArgumentException("WordToCheck cannot be null");
		if (dictionary == null)
			throw new IllegalStateException(
					"Must call loadDictionary before isValidWord");
		return binarySearch(dictionary, wordToCheck, 0, dictionary.length - 1);
	}

	/**
	 * Determines if there is at least one word in the dictionary with the given
	 * prefix.
	 * 
	 * @param prefixToCheck
	 *            The prefix to validate
	 * @return true if prefixToCheck appears in dictionary, false otherwise.
	 * @throws IllegalArgumentException
	 *             if prefixToCheck is null.
	 * @throws IllegalStateException
	 *             if loadDictionary has not been called.
	 */
	public boolean isValidPrefix(String prefixToCheck) {
		if (prefixToCheck == null)
			throw new IllegalArgumentException("prefixToCheck cannot be null");
		if (dictionary == null)
			throw new IllegalStateException(
					"Must call loadDictionary before isValidPrefix");
		return binarySearchPreFix(dictionary, prefixToCheck, 0,
				dictionary.length - 1);
	}

	/**
	 * Stores the incoming array of Strings in a fashion that will make it
	 * convenient to find words.
	 * 
	 * @param letterArray
	 *            Each string in this array corresponds to a die on the Boggle
	 *            board. The die are in order left to right, top to bottom. The
	 *            size of letterArray = Row X Col. Note that the Strings inside
	 *            may be longer than one character. Also note that the board
	 *            might not be 4x4.
	 * @throws IllegalArgumentException
	 *             if letterArray is null, or is not square (i.e. it's the
	 *             square-root of the length is not a whole number).
	 */
	public void setBoard(String[] letterArray) {
		if (letterArray == null)
			throw new IllegalArgumentException("letterArray cannot be null");
		if (letterArray.length % Math.sqrt(letterArray.length) != 0)
			throw new IllegalArgumentException("letterArray is not sqaure");

		int size = (int) Math.sqrt(letterArray.length);
		board = new String[size][size];

		for (int i = 0; i < size; i++)
			for (int j = 0; j < size; j++)
				board[i][j] = letterArray[(size * i) + j];
	}

	/**
	 * Determines if the given word is in on the Boggle board. If so, it returns
	 * the path that makes up the word.
	 * 
	 * @param wordToCheck
	 *            The word to validate
	 * @return java.util.List containing java.lang.Integer objects with the path
	 *         that makes up the word on the Boggle board. If word is not on the
	 *         boggle board, return null.
	 * @throws IllegalArgumentException
	 *             if wordToCheck is null.
	 * @throws IllegalStateException
	 *             if setBoard has not been called.
	 */
	public List isOnBoard(String wordToCheck) {
		if (wordToCheck == null)
			throw new IllegalArgumentException("word to check cannot be null");
		if (board == null)
			throw new IllegalStateException(
					"Must call setBoard before isOnBoard");

		tempArray.clear();
		int p = 0;
		for (; p < wordToCheck.length(); p++) {
			if (wordToCheck.substring(p, p + 1).equalsIgnoreCase("q")) {
				tempArray.add(wordToCheck.substring(p, p + 2));
				p++;
			} else {
				tempArray.add(wordToCheck.substring(p, p + 1));
			}
		}
		String[] wordBrokenUp = new String[tempArray.size()];
		for (int i = 0; i < wordBrokenUp.length; i++) 
			wordBrokenUp[i] = (String) tempArray.get(i);

		int count = 0;
		resetMap();
		pathList.clear();
		pathListCopy.clear();

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {

				if (board[i][j].equalsIgnoreCase(wordBrokenUp[count])) {
					findValidPaths(i, j, wordBrokenUp, count);

					if (returnList(wordToCheck))
					return pathList;					
				}
			}
		}

		return null;
	}

	
	
	
	private boolean returnList(String wordToCheck) {
		if (wordToCheck.contains("qu"))
			if (pathList.size() == wordToCheck.length()-1)
				return true;
		if (pathList.size() == wordToCheck.length()) {
			return true;
		}
		return false;
	}


	public void findValidPaths(int i, int j, String[] wordBrokenUp, int count) {

		if (i < 0 || i >= board.length || j < 0 || j >= board.length)
			return;
		if (map.get((i * board.length) + j) != null)
			return;
		if (count >= wordBrokenUp.length)
			return;
		if (!board[i][j].equalsIgnoreCase(wordBrokenUp[count]))
			return;

		pathListCopy.add(i * board.length + j);
		map.put(i * board.length + j, true);

		count++;

		if (pathListCopy.size() == wordBrokenUp.length)
			pathList = new ArrayList<Integer>(pathListCopy);

		findValidPaths(i - 1, j - 1, wordBrokenUp, count);
		findValidPaths(i - 1, j, wordBrokenUp, count);
		findValidPaths(i - 1, j + 1, wordBrokenUp, count);
		findValidPaths(i, j + 1, wordBrokenUp, count);
		findValidPaths(i + 1, j + 1, wordBrokenUp, count);
		findValidPaths(i + 1, j, wordBrokenUp, count);
		findValidPaths(i + 1, j - 1, wordBrokenUp, count);
		findValidPaths(i, j - 1, wordBrokenUp, count);
		pathListCopy.remove(pathListCopy.size() - 1);
		map.put(i * board.length + j, null);
	}

	/**
	 * Retrieves all the words in the Boggle board that appear in the
	 * dictionary.
	 * 
	 * @param minimumWordLength
	 *            The minimum allowed length for strings that will be stated as
	 *            being on the board.
	 * @return java.util.SortedSet which contains all the words found from the
	 *         boggle board that appear in the dictionary.
	 * @throws IllegalArgumentException
	 *             if minimumWordLength < 1
	 * @throws IllegalStateException
	 *             if loadDictionary has not been called.
	 */
	public SortedSet getAllValidWords(int minimumWordLength) {
		if (dictionary == null)
			throw new IllegalStateException(
					"Must call loadDictionary before getAllValidWords");
		if (minimumWordLength < 1)
			throw new IllegalArgumentException(
					"Word Length must be greater than 0");
		set.clear();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				resetMap();
				String curWord = "";
				validWordsForTile(i, j, curWord, minimumWordLength);
			}
		}
		return set;
	}

	/**
	 * An optional method that gives a user-defined boggle board to the GUI.
	 * 
	 * @return a String array in the same form as the input to setBoard().
	 */
	public String[] getCustomBoard() {
		String[] sArray = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
		
		return sArray;
	}

	/**
	 * Method that checks for valid words beginning with tile on board at
	 * position i, j
	 * 
	 * @return a SortedSet of words beginning with tile i, j
	 */
	public void validWordsForTile(int i, int j, String curWord,
			int minimumWordLength) {

		if (i < 0 || i >= board.length || j < 0 || j >= board.length)
			return;
		if (map.get((i * board.length) + j) != null)
			return;

		map.put(i * board.length + j, true);
		curWord += board[i][j];
		curWord = curWord.toLowerCase();

		if (isValidWord(curWord) && curWord.length() >= minimumWordLength)
			set.add(curWord);
		if (isValidPrefix(curWord))
			recursion(i, j, curWord, minimumWordLength);

		map.put(i * board.length + j, null);
	}

	/**
	 * Checks if neighboring tiles are on the board and that they haven't
	 * already been used to create a word
	 * 
	 * @param i
	 *            (row of board)
	 * @param j
	 *            (column of board)
	 * 
	 */
	private void recursion(int i, int j, String curWord, int minimumWordLength) {
		validWordsForTile(i - 1, j - 1, curWord, minimumWordLength);
		validWordsForTile(i - 1, j, curWord, minimumWordLength);
		validWordsForTile(i - 1, j + 1, curWord, minimumWordLength);
		validWordsForTile(i, j + 1, curWord, minimumWordLength);
		validWordsForTile(i + 1, j + 1, curWord, minimumWordLength);
		validWordsForTile(i + 1, j, curWord, minimumWordLength);
		validWordsForTile(i + 1, j - 1, curWord, minimumWordLength);
		validWordsForTile(i, j - 1, curWord, minimumWordLength);
	}

	// Binary Search for words in dictionary
	public boolean binarySearch(String[] array, String value, int left,
			int right) {
		if (left > right)
			return false;
		if (value.compareTo(array[0]) < 0
				|| value.compareTo(array[array.length - 1]) > 0)
			return false;
		int middle = (left + right) / 2;
		if (array[middle].equals(value))
			return true;
		else if (array[middle].compareTo(value) > 0)
			return binarySearch(array, value, left, middle - 1);
		else
			return binarySearch(array, value, middle + 1, right);
	}

	// Binary search for prefixes (in the dictionary)

	public boolean binarySearchPreFix(String[] array, String value, int left,
			int right) {

		if (left > right)
			return false;
		int middle = (left + right) / 2;

		// if (array[middle].length() < value.length())
		// return binarySearchPreFix(array, value, left, right + 1);

		if (array[middle].startsWith(value))
			return true;
		else if (array[middle].compareTo(value) > 0)
			return binarySearchPreFix(array, value, left, middle - 1);
		else
			return binarySearchPreFix(array, value, middle + 1, right);
	}

	private void resetMap() {
		for (int i = 0; i < board.length; i++)
			for (int j = 0; j < board.length; j++)
				map.put(i * board.length + j, null);
	}

}
