import java.util.ArrayList;
import java.util.List;

public class MyTrieSet implements TrieSet61B {
    Node root;
    public class Node{
        char val;
        boolean isChar;
        ArrayList<Node> letterCollection;

        public Node(char l, boolean iC) {
            val = l;
            isChar = iC;
            letterCollection = new ArrayList<Node>();
        }
    }

    public MyTrieSet() {
        root = new Node(' ', false);
    }

    @Override
    public void clear() {
        root = new Node(' ', false);
    }

    @Override
    public boolean contains(String key) {

        return contains(key, root, 0);
    }


    @Override
    public void add(String key) {
        add(key, root, 0);
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        return keysWithPrefix(prefix, root, 0);
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }


    /** Helper function.
       no need to read. */

    /** need modify */
    private void add(String key, Node tempRoot, int i) {
        boolean found = false;
        for (Node node : tempRoot.letterCollection) {
            if(node.val == key.charAt(i) && isLast(key, i)) { // isLast = true found = true
                node.isChar = true;
                found = true;
            } else if (node.val == key.charAt(i) && !isLast(key, i)) { // isLast = false found = true
                found = true;
                add(key, node, i + 1);
            }
        }
        if (!found && isLast(key, i)) { // found = false isLast = true;
            Node temp = new Node(key.charAt(i),true);
            tempRoot.letterCollection.add(temp);
        } else if(!found && !isLast(key, i)){ //found = false && isLast = false;
            Node temp = new Node(key.charAt(i), false);
            tempRoot.letterCollection.add(temp);
            add(key, temp, i + 1);
        }
    }



    private List<String> keysWithPrefix(String prefix, Node tempRoot, int i) {
        ArrayList<String> wordList = new ArrayList<>();
        for (Node node: tempRoot.letterCollection) {
            if (node.val == prefix.charAt(i) && isLast(prefix, i)) {
                wordList = addWordToList(tempRoot, prefix);
            } else if (node.val == prefix.charAt(i)) {
                keysWithPrefix(prefix, node ,i + 1);
            }
        }
        return wordList;
    }

    private boolean contains(String key, Node root, int i) {

        for (Node node: root.letterCollection) {
            if (node.val == key.charAt(i) && isLast(key, i) && node.isChar) {
                return true;
            } else if (node.val == key.charAt(i) && !isLast(key, i)) {
                return contains(key, node, i + 1);
            }
        }
        return false;
    }

    /** Return true if the index is equal to key's length minus 1. */
    private boolean isLast(String key, int i) {
        return key.length()- 1 == i;
    }

    /** Add all words to the list start from the node.*/
    private ArrayList<String> addWordToList(Node start,  String prefix) {
        ArrayList<String> list = new ArrayList<>();
        for (Node node : start.letterCollection) {
            if (node.isChar) {
                list.add(prefix+node.val);
            }
            addWordToList(node, prefix+ node.val);
        }
        return list;
    }

}
