import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;

public class ParserTest {

    private static List<Word> wordList = new ArrayList<>();
    private static HashMap<String, String> ruleList = new HashMap<>();
    private static List<Word> input = new ArrayList<>();
    static JFrame display;

    public static void main(String[] args) {
        // Read in rules and words
        wordList = Word.readWords();
        ruleList = Rule.readRules();
        // Display Java Swing gui
        display = new DisplayProgram();
    }

    // Method gets called when check sentence button is pressed on the GUI
    public void run(String userInput) {

        String[] words = userInput.split("\\s+");
        Parser parser = new Parser();
        input = parser.createWords(wordList, words);

        if (input.size() == 0) {
            // If no input output error msg
            ((DisplayProgram) display).setResults("", "Enter words that are present in lexicon", "");
        } else {
            if (parser.checkPluralSingle(input)) {
                // If sentence is correct create tree and bracketed structure
                TreeNode root = parser.createTree(ruleList, input);
                String bps = parser.bracketedPhraseStucture(root);

                if (root != null) {
                    // Display bracketed phrase structure
                    ((DisplayProgram) display).setResults("Sentence is gramatically correct!", "", bps);
                    // Display Tree (First creates a tree of Default Mutable Treenodes)
                    ((DisplayProgram) display).setTreeRoot(createTreeVis(root));
                } else {
                    ((DisplayProgram) display).setResults("Sentence is not gramatically correct! (Rules not met!)", "",
                            "");
                }
            } else {
                // If not grammatically correct output error
                ((DisplayProgram) display).setResults("Sentence is not gramatically correct!", "", "");
            }

        }
    }

    // Create tree structure from root tree node using DefaultMutableTreeNode
    // (Files)
    public DefaultMutableTreeNode createTreeVis(TreeNode node) {
        DefaultMutableTreeNode curr = new DefaultMutableTreeNode(node.getItem());

        // Keep adding left child untill null
        if (node.getLeft() != null) {
            curr.add(createTreeVis(node.getLeft()));
        }
        // Keep adding middle child untill null
        if (node.getMiddle() != null) {
            curr.add(createTreeVis(node.getMiddle()));
        }
        // Keep adding right child untill null
        if (node.getRight() != null) {
            curr.add(createTreeVis(node.getRight()));
        }
        // Return root as DefaultMutableTreeNode
        return curr;
    }

}
