
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.*;

public class DisplayProgram extends JFrame implements ActionListener {

    // JFrame set up
    JButton checkSentence = new JButton("Check sentence");
    JLabel gramResult = new JLabel("");
    JLabel resReason = new JLabel("");
    JLabel bracketedStucture = new JLabel("");
    JTextArea textFieldTop = new JTextArea(3, 20);
    JLabel inputLabel = new JLabel("Check sentence for correctness: ");
    int count = 0;
    JButton s1 = new JButton("Random correct sentence");
    JButton s2 = new JButton("Random incorrect sentence");
    JTree tree;
    ParserTest parserTest = new ParserTest();

    DisplayProgram() {
        super("Parser for Regular Expressions");
        setSize(750, 600);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridBagLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new GridBagLayout());
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new GridBagLayout());
        JPanel bottomPanel = new JPanel();

        // Colours
        topPanel.setBackground(new Color(232, 220, 202));
        middlePanel.setBackground(new Color(232, 220, 202));
        bottomPanel.setBackground(new Color(232, 220, 202));

        // Action listeners for buttons
        checkSentence.addActionListener(this);
        s1.addActionListener(this);
        s2.addActionListener(this);

        // Labels
        gramResult.setFont(new Font("serif", Font.PLAIN, 20));
        resReason.setFont(new Font("serif", Font.PLAIN, 20));
        bracketedStucture.setFont(new Font("serif", Font.BOLD, 20));

        // Scroll bar for text area
        textFieldTop.setLineWrap(true);
        JScrollPane scrollBar = new JScrollPane(textFieldTop, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Top panel
        GridBagConstraints topCon = new GridBagConstraints();
        topCon.gridx = 0;
        topCon.gridy = 0;
        topCon.weighty = .1;
        topCon.fill = 2;
        topPanel.add(inputLabel, topCon);
        topCon.gridy++;
        topCon.weighty = .33;
        topPanel.add(scrollBar, topCon);
        topCon.gridx++;
        topPanel.add(checkSentence, topCon);
        topCon.gridy++;
        topCon.gridx = 0;
        topPanel.add(s1, topCon);
        topCon.gridx++;
        topPanel.add(s2, topCon);

        // Middle Panel
        GridBagConstraints middleCon = new GridBagConstraints();
        middleCon.gridx = 0;
        middleCon.gridy = 0;
        middleCon.weighty = .33;
        middlePanel.add(gramResult, middleCon);
        middleCon.gridy++;
        middlePanel.add(resReason, middleCon);
        middleCon.gridy++;
        middlePanel.add(bracketedStucture, middleCon);

        // Create tree for bottom panel
        tree = new JTree(new DefaultMutableTreeNode());
        tree.setVisible(false);

        // Bottom panel
        bottomPanel.add(tree);

        // Content Pane
        GridBagConstraints con = new GridBagConstraints();
        con.gridx = 0;
        con.gridx = 0;
        con.gridy = 0;
        con.weighty = .3;
        con.weightx = .2;
        con.fill = GridBagConstraints.BOTH;
        contentPane.add(topPanel, con);
        con.gridy++;
        con.weighty = .2;
        contentPane.add(middlePanel, con);
        con.gridy++;
        con.weighty = .5;
        contentPane.add(bottomPanel, con);

        // Make JFrame visible
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkSentence) {
            String sentence = textFieldTop.getText().trim();
            parserTest.run(sentence);
        }
        // Show correct sentences
        else if (e.getSource() == s1) {
            count++;
            if (count % 2 == 0) {
                textFieldTop.setText("The people like the green dog");
            } else {
                textFieldTop.setText("A person dislikes the green dog");
            }

        }
        // Show incorrect sentences
        else if (e.getSource() == s2) {
            count++;
            if (count % 2 == 0) {
                textFieldTop.setText("A people like the green dog");
            } else {
                textFieldTop.setText("A person dislike the green dog");
            }
        }
    }

    // Update result labels called from ParserTest
    public void setResults(String result, String reason, String bracketedStructure) {
        gramResult.setText(result);
        resReason.setText(reason);
        bracketedStucture.setText(bracketedStructure);
    }

    // Get user input called from ParserTest
    public String getInput() {
        return textFieldTop.getText().trim();
    }

    // Set DefaultMutableTreeNode created from Parser test as root
    public void setTreeRoot(DefaultMutableTreeNode dmtn) {
        // Replace curr tree with new tree
        tree.setModel(new DefaultTreeModel(dmtn));

        // Open folder structure on open
        for (int i = 0; i < tree.getRowCount(); i++) {
            tree.expandRow(i);
        }

        tree.setVisible(true);
    }

}
