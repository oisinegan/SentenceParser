import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Parser {

    // Create parse tree
    public TreeNode createTree(HashMap<String, String> ruleList, List<Word> input) {
        // List to store treenodes
        List<TreeNode> nodes = new ArrayList<>();

        // Create nodes for each word and nodes for each pos
        for (int i = 0; i < input.size(); i++) {
            // Crete Child node as word
            TreeNode child = new TreeNode(input.get(i).getWord());
            // Create Parent node as pos attach child to parent
            TreeNode newNode = new TreeNode(input.get(i).getPos(), child);
            // Add parent
            nodes.add(newNode);
        }

        // Run while List that stores nodes does not contain one node ("S") (S =
        // sentence)
        while (nodes.size() != 1 && !(nodes.get(0).getItem().equals("S"))) {
            // Take last two nodes from list
            TreeNode node1 = nodes.get(nodes.size() - 2);
            TreeNode node2 = nodes.get(nodes.size() - 1);

            // Handle jj
            if (node1.getItem().equals("JJ") && node2.getItem().equals("NN")) {
                // If nodes = JJ and NN, Then create node3 and set to POS left of JJ.
                TreeNode node3 = nodes.get(nodes.size() - 3);

                // Look for rule pos left of jj and NN. Add node to list with left child(pos
                // left of
                // jj), middle child(jj) and right child(NN)
                if (ruleList.containsKey(node3.getItem() + " " + node2.getItem())) {
                    // Remove three nodes
                    nodes.remove(nodes.size() - 1);
                    nodes.remove(nodes.size() - 1);
                    nodes.remove(nodes.size() - 1);
                    TreeNode newNode = new TreeNode(ruleList.get(node3.getItem() + " " + node2.getItem()), node3, node1,
                            node2);
                    nodes.add(newNode);
                }

            } else {
                // If the two nodes are not in the list, check the two nodes at the front
                if (!(ruleList.containsKey(node1.getItem() + " " + node2.getItem()))) {
                    node1 = nodes.get(0);
                    nodes.remove(0);
                    node2 = nodes.get(0);
                    nodes.remove(0);
                    // Check nodes againsts rules
                    if (checkRules(ruleList, nodes, node1, node2, 1) == false) {
                        return null;
                    }

                } else {
                    // Remove the nodes from list
                    nodes.remove(nodes.size() - 1);
                    nodes.remove(nodes.size() - 1);
                    // Check nodes againsts rules
                    if (checkRules(ruleList, nodes, node1, node2, 0) == false) {
                        return null;
                    }
                }
            }
        }
        // Return root node
        return nodes.get(0);

    }

    // Helper method for createTrr
    private boolean checkRules(HashMap<String, String> ruleList, List<TreeNode> nodes, TreeNode node1, TreeNode node2,
            int n) {
        // Check if two nodes are in the rule list
        if (ruleList.containsKey(node1.getItem() + " " + node2.getItem())) {
            // Make a new node as the rule outcome and assign the two nodes as children
            TreeNode newNode = new TreeNode(ruleList.get(node1.getItem() + " " + node2.getItem()), node1,
                    node2);
            // Place the new node back into the list
            // If 0 passed add new node to back of list
            if (n == 0) {
                nodes.add(newNode);
                return true;
            } else {// If 1 passed add new node to front of list
                nodes.add(0, newNode);
                return true;
            }
        } else {
            System.out.println("Sentence is not gramatically corrected!");
            System.out.println("RULES NOT MET!");
            return false;
        }
    }

    // Bracketed Phrase Structure
    public String bracketedPhraseStucture(TreeNode node) {
        if (node != null) {

            return "[" + node.getItem() +
                    bracketedPhraseStucture(node.getLeft()) +
                    bracketedPhraseStucture(node.getMiddle()) +
                    bracketedPhraseStucture(node.getRight()) +
                    "]";

        }

        return "";
    }

    // Create List of Word. Each word has pos,root and number (1=plural, 0=single)
    public List<Word> createWords(List<Word> wordList, String[] words) {

        List<Word> input = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            // For each word checl if in lexicon,
            for (int j = 0; j < wordList.size(); j++) {
                Word wordHolder = wordList.get(j);
                // If in lexicon assign pos,root and number & add to list
                if (words[i].toLowerCase().equals(wordHolder.getWord())) {
                    Word word = new Word(wordHolder.getWord(), wordHolder.getPos(), wordHolder.getRoot(),
                            wordHolder.getNumber());
                    input.add(word);
                    break;
                }
                // If at end of loop then inform user that word is not in lexicon
                if (j == wordList.size() - 1) {
                    System.out.println("WORD: " + words[i] + ", is not in lexicon! This will be ignored!");
                }
            }
        }

        return input;
    }

    // Check sentence is correct in placement of plural and singular words
    public boolean checkPluralSingle(List<Word> input) {
        boolean isCorrect = true;
        for (int i = 0; i < input.size(); i++) {
            // If word is Plural verb check that word before is plural too
            if (input.get(i).getPos().equals("VBP")) {
                if (input.get(i - 1).getNumber() != 1) {
                    isCorrect = false;
                    System.out.println(
                            "'" + input.get(i - 1).getWord() + "' can't be followed by '" + input.get(i).getWord()
                                    + "'");
                }
            }
            // If sentence start with 'a' check that next word is singular
            else if (input.get(i).getWord().equals("a") && input.get(i + 1).getNumber() != 0) {
                isCorrect = false;
                System.out.println(
                        "'" + input.get(i).getWord() + "' can't be followed by '" + input.get(i + 1).getWord()
                                + "'");
            }
            // If word is singular verb check that word before is singular too
            else if (input.get(i).getPos().equals("VBZ")) {
                if (input.get(i - 1).getNumber() != 0) {
                    isCorrect = false;
                    System.out.println(
                            "'" + input.get(i - 1).getWord() + "' can't be followed by '" + input.get(i).getWord()
                                    + "'");
                }
            }
        }
        return isCorrect;
    }
}
