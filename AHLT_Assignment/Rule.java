import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Rule {

    static HashMap<String, String> ruleList = new HashMap<>();

    public static HashMap<String, String> readRules() {
        try {
            File f = new File("./files/rules.txt");
            Scanner reader = new Scanner(f);

            // Read in rules and split each pos and add to hashmap
            while (reader.hasNextLine()) {
                String next = reader.nextLine();
                String[] rules = next.split("\\s+");

                // Example of rule: S NP VP
                // The key will be: NP VP
                // The Value will be: S
                ruleList.put(rules[1] + " " + rules[2], rules[0]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ruleList;
    }

}
