
import static com.sun.javafx.fxml.expression.Expression.add;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import javax.swing.*;

// Java implementation of search and insert operations
// on Trie
public class Trie extends JFrame {

    // Alphabet size (# of symbols)
    static final int ALPHABET_SIZE = 26;

    // trie node
    static class TrieNode {

        TrieNode[] children = new TrieNode[ALPHABET_SIZE];

        // isEndOfWord is true if the node represents
        // end of a word
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < ALPHABET_SIZE; i++) {
                children[i] = null;
            }
        }
    };

    static TrieNode root;

    // If not present, inserts key into trie
    // If the key is prefix of trie node,
    // just marks leaf node
    static void insert(String key) {
        int level;
        int length = key.length();
        int index;
        //System.out.println("passed3");
        TrieNode pCrawl = root;
        //System.out.println("passed4");
        for (level = 0; level < length; level++) {
            //  System.out.println("passed6:" + key + " " + level);
            index = key.charAt(level) - 'a';
            //System.out.println("passed7:" + key + " " + level + index);
            if (pCrawl.children[index] == null) {
                pCrawl.children[index] = new TrieNode();
            }
            //System.out.println("passed8:" + key + " " + level);
            pCrawl = pCrawl.children[index];
        }
        //System.out.println("passed5");
        // mark last node as leaf
        pCrawl.isEndOfWord = true;
    }
    public static ArrayList<String> words = new ArrayList<String>();
    public static ArrayList<String> words2 = new ArrayList<String>();

    // Returns true if key presents in trie, else false
    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
        String sentence = "";

        StringBuilder updateString = new StringBuilder(key);
        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';

            if (pCrawl.children[index] == null) {

//return false;
            } else {
                pCrawl = pCrawl.children[index];
                sentence = sentence + key.charAt(level);
                updateString.setCharAt(level, Character.toUpperCase(key.charAt(level)));

            }
        }
        //System.out.println(pCrawl.isEndOfWord);
        if (pCrawl.isEndOfWord) {
            //System.out.println("got>" + sentence);
            //System.out.println("got>" + updateString + "\n");

            words.add(sentence);
            words2.add(updateString.toString());
        } else {
            sentence = "";
        }
        return (pCrawl.isEndOfWord);
    }

    static void multisearch(String key) {
        for (int i = 0; i < key.length(); i++) {
            //System.out.println(key.substring(i, key.length()));
            search(key.substring(i, key.length()));
        }
    }
    // Driver

    public static JPanel all = new JPanel(new BorderLayout());
    public static JPanel top = new JPanel(new BorderLayout());
    //public static JPanel right = new JPanel(new BorderLayout());
    public static JTextArea TAoutput1 = new JTextArea();
    public static JTextArea TAoutput2 = new JTextArea();
    public static JTextField TA = new JTextField();

    public static void main(String args[]) throws MalformedURLException, IOException {

        Trie frame = new Trie();
        frame.setTitle("Acronym!");
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);

        TAoutput1.setEditable(false);
        TAoutput1.setBorder(null);
        TAoutput1.setPreferredSize(new Dimension(300, 400));
        TAoutput1.setOpaque(false);

        TAoutput2.setEditable(false);
        TAoutput2.setBorder(null);
        TAoutput2.setPreferredSize(new Dimension(500, 400));
        TAoutput2.setOpaque(false);

        TA.setPreferredSize(new Dimension(100, 24));
        TA.setText("National Aeronautics and Space Administration");

        JLabel Outputlabel = new JLabel();
        Outputlabel.setFont(new Font("Calibri", Font.PLAIN, 30));
        Outputlabel.setText("Type sentence and press enter for Acyonym!");

        JLabel Consolelable = new JLabel();
        Consolelable.setFont(new Font("Calibri", Font.PLAIN, 20));
        Consolelable.setText("Console: waiting for input");

        top.add(Outputlabel, BorderLayout.NORTH);
        top.add(TA, BorderLayout.SOUTH);

        all.add(top, BorderLayout.NORTH);
        all.add(TAoutput1, BorderLayout.WEST);
        all.add(TAoutput2, BorderLayout.CENTER);
        all.add(Consolelable, BorderLayout.SOUTH);
        frame.add(all);

        frame.revalidate();
        frame.repaint();
        Action enterkey = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Consolelable.setText("Console: loading!");
                    frame.revalidate();
                    frame.repaint();

                    run(TA.getText());

                    Consolelable.setText("Console: done!");
                    frame.revalidate();
                    frame.repaint();
                } catch (IOException ex) {
                    Logger.getLogger(Trie.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        TA.addActionListener(enterkey);

//        if (search("caol") == true) {
//            System.out.println(" --- " + output[1]);
//        } else {
//            System.out.println(" --- " + output[0]);
//        }
//
//        if (search("then") == true) {
//            System.out.println("then --- " + output[1]);
//        } else {
//            System.out.println("then --- " + output[0]);
//        }
//        if (search("these") == true) {
//            System.out.println("these --- " + output[1]);
//        } else {
//            System.out.println("these --- " + output[0]);
//        }
//
//        if (search("their") == true) {
//            System.out.println("their --- " + output[1]);
//        } else {
//            System.out.println("their --- " + output[0]);
//        }
//
//        if (search("thaw") == true) {
//            System.out.println("thaw --- " + output[1]);
//        } else {
//            System.out.println("thaw --- " + output[0]);
//        }
    }
    public static String TempA = "";
    public static String TempB = "";

    public static void run(String input) throws MalformedURLException, IOException {
        words.clear();
        words2.clear();
        // Input keys (use only 'a' through 'z' and lower case)
        ArrayList<String> ar = new ArrayList<String>();
//get content from dictionary
        URL url = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");
        //Retrieving the contents of the specified page
        Scanner sc = new Scanner(url.openStream());
        //Instantiating the StringBuffer class to hold the result
        StringBuffer sb = new StringBuffer();

        String temp = "";
        while (sc.hasNext()) {
            temp = sc.next();
            temp = temp.toLowerCase();
            temp = temp.replaceAll("[^A-Za-z]", "");
            if (temp.length() > 1) {
                ar.add(temp);
            }
        }
        String[] keys = new String[ar.size()];
        keys = ar.toArray(keys);
        //String keys[] = {"the", "cl", "al"};

        String output[] = {"Not present in trie", "Present in trie"};

        root = new TrieNode();

        // Construct trie
        //System.out.println("passed" + keys.length);
        for (int i = 0; i < keys.length; i++) {
            insert(keys[i]);

        }
        // Search for different keys
        //String input = "HierarCHical taxonomic classification for viral mEtagEnomic data via deep leaRning";
        //String input = "National Aeronautics and Space Administration";
        input = input.toLowerCase().replace(" ", "");
        System.out.println(input);
        multisearch(input);

        TempA = "";
        TempB = "";
        for (int i = 0; i < words2.size(); i++) {
            //System.out.print(words.get(i) + "    ");
            TempA = TempA + "\n" + words.get(i);
            TempB = TempB + "\n"
                    + input.substring(0, input.length() - words2.get(i).length()) + words2.get(i);
            //System.out.print(input.substring(0, input.length() - words2.get(i).length()));
            //System.out.println(words2.get(i));
        }
        TAoutput1.setText(TempA);
        TAoutput2.setText(TempB);
    }
}
// This code is contributed by Sumit Ghosh
