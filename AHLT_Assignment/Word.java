import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Word {

    private String word;
    private String pos;
    private String root;
    private int number;

    static List<Word> wordList = new ArrayList<>();

    public static List<Word> readWords() {
        try {
            File f = new File("./files/lexicon.txt");
            Scanner reader = new Scanner(f);

            // Skip first two lines
            for (int i = 0; i < 2; i++) {
                reader.nextLine();
            }

            while (reader.hasNextLine()) {

                String next = reader.nextLine();
                // Split word
                String[] nextSplit = next.split(",");

                // Create Word objects and store in list
                Word word = new Word(nextSplit[0].trim(), nextSplit[1].trim(), nextSplit[2].trim(),
                        Integer.parseInt(nextSplit[3].trim()));
                wordList.add(word);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return wordList;
    }

    public Word(String word, String pos, String root, int number) {
        this.word = word;
        this.pos = pos;
        this.root = root;
        this.number = number;
    }

    public String getWord() {
        return this.word;
    }

    public String getPos() {
        return this.pos;
    }

    public String getRoot() {
        return this.root;
    }

    public int getNumber() {
        return this.number;
    }

}