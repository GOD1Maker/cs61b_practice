import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieSet implements TrieSet61B {

    private Node root;

    private class Node{
        private boolean isKey;
        private char nodeChar;
        private Map<Character,Node> children;

        private Node(char nodeChar, boolean isKey){
            this.nodeChar = nodeChar;
            this.isKey = isKey;
            children = new HashMap<Character, Node>();
        }
    }
    public MyTrieSet(){
        root = new Node('\0',false);
    }

    @Override
    public void clear(){
        root = null;
    }

    @Override
    public boolean contains(String key){
        if(key == null || key.length() == 0 || root == null){
            return false;
        }

        Node currNode = root;
        Node nextNode = null;

        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            nextNode = currNode.children.get(c);
            if(nextNode == null){
                return false;
            }
            currNode = nextNode;
        }
        return currNode.isKey;
    }

    @Override
    public void add(String key){
        if(key == null || key.length() == 0 || root == null){
            return;
        }

        Node currNode = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(!currNode.children.containsKey(c)){
                currNode.children.put(c, new Node(c, false));
            }
            currNode = currNode.children.get(c);
        }
        currNode.isKey = true;
    }

    public List<String> keysWithPrefix(String prefix){
        if(prefix == null || prefix.length() == 0 || root == null){
            throw new IllegalArgumentException();
        }
        List<String> prefixKey = new ArrayList<String>();
        Node currNode = root;

        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            currNode = currNode.children.get(c);
        }

        // If prefix itself is a key, add it to the result.
        if(currNode.isKey){
            prefixKey.add(prefix);
        }

        for(Node node : currNode.children.values()){
            if(node!=null){
                keysWithPrefix(prefixKey, prefix, node);
            }
        }
        return prefixKey;

    }

    private List<String> keysWithPrefix(List<String> ans, String s, Node node){
        if(node.isKey){
            ans.add(s + node.nodeChar);
        }
        for(Node nextNode : node.children.values()){
            if(nextNode != null){
                keysWithPrefix(ans, s + node.nodeChar, nextNode);
            }
        }
        return ans;
    }

    @Override
    public String longestPrefixOf(String key){
        StringBuilder str = new StringBuilder();
        Node currNode = root;
        for(int i = 0; i < key.length(); i++){
            char c = key.charAt(i);
            if(!currNode.children.containsKey(c)){
                return str.toString();
            }else {
                str.append(c);
                currNode = currNode.children.get(c);
            }
        }
        return str.toString();
    }
}
