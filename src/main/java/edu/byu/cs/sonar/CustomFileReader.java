package edu.byu.cs.sonar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

/**
 * This is a class that will handle the file reading.
 */
class CustomFileReader {
    /**
     * The path to the file that will be read.
     */
    private final String path;

    /**
     * The sentence that will be constructed.
     */
    private String newSentence;

    /**
     * The number of words read in by the scanner.
     */
    private int count;

    /**
     * Constructor for our class.
     *
     * @param fileName the file to be read in
     */
    CustomFileReader(final String fileName) {
        path = fileName;
        newSentence = "";
        count = 0;
    }

    /**
     * Creates a scanner for the file using UTF 8.
     *
     * @return scanner for the file
     * @throws FileNotFoundException if the file cannot be found
     */
    private Scanner createScanner() throws FileNotFoundException {
        return new Scanner(
                new InputStreamReader(
                        new FileInputStream(path),
                        StandardCharsets.UTF_8
                )
        );
    }

    /**
     * This will find how many words are in the text file provided.
     *
     * @return how many words in the file
     * @throws FileNotFoundException if the file cannot be found
     */
    int howManyWordsInFile() throws FileNotFoundException {
        count = 0;
        try (Scanner scanner = createScanner()) {
            while (scanner.hasNext()) {
                scanner.next();
                count++;
            }
        }
        return count;
    }

    /**
     * This will return the word at the given index in the text file.
     *
     * @param index which number word should be taken back
     * @return correct word
     * @throws FileNotFoundException if the file cannot be found
     */
    String returnThatWord(final int index) throws FileNotFoundException {
        String returnWord = "";
        try (Scanner scanner = createScanner()) {
            for (int i = 0; i < index; i++) {
                returnWord = scanner.next();
            }
        }
        return returnWord;
    }

    /**
     * This will search for a word that contains the same letter as the
     * one provided in the argument and store that word.
     *
     * @param letter eventually will be the character we are looking for in the word
     * @throws FileNotFoundException if the file cannot be found
     */
    void findNewWord(final CharSequence letter) throws FileNotFoundException {
        try (Scanner scanner = createScanner()) {
            String word = scanner.next();

            while (!word.contains(letter)) {
                word = scanner.next();
            }

            newSentence = newSentence + word + " ";
        }
    }

    /**
     * This is a standard getter.
     *
     * @return the sentence created by the reader
     */
    String getNewSentence() {
        return newSentence;
    }

    /**
     * This is a standard setter.
     *
     * @param betterSentence the new sentence for the reader
     */
    void setNewSentence(final String betterSentence) {
        newSentence = betterSentence;
    }

    /**
     * Private getter for count.
     *
     * @return the number of words in the dictionary
     */
    private int getCount() {
        return count;
    }

    /**
     * Private getter for path.
     *
     * @return the path for this reader
     */
    private String getPath() {
        return path;
    }

    /**
     * Returns a hash number for this reader.
     *
     * @return hash code for this specific instance of a reader
     */
    @Override
    public int hashCode() {
        return Objects.hash(path, newSentence, count);
    }

    /**
     * Returns information about this reader.
     *
     * @return string for this specific instance of a reader
     */
    @Override
    public String toString() {
        return newSentence + " " + count;
    }

    /**
     * Checks if the object being processed is an
     * instance of this exact class.
     *
     * @param object data type sent to be processed
     * @return true or false based on the processed object
     */
    @Override
    public boolean equals(final Object object) {
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        final CustomFileReader comparedReader = (CustomFileReader) object;

        return comparedReader.getNewSentence().equals(newSentence)
                && comparedReader.getCount() == count
                && comparedReader.getPath().equals(path);
    }
}