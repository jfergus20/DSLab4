import java.util.Vector;

public class TrieNode {

	private Word wordHere;

	private TrieNode[] links;

	public TrieNode() {
		wordHere = null;
		links= new TrieNode[26];
	}

	private int let(char c) {
		return c - 'a';
	}

	private char firstChar(String key) {
		return key.charAt(0);
	}
	
	private String rest(String key) {
		return key.substring(1,key.length());
	}
	
	private TrieNode linkWordStart(String start) {
		return links[let(firstChar(start))];
	}
	
	public void insert(String key,String toHere) {
	}

	
	public Word find(String key) {
		if (key.length() == 0) {
			if (wordHere==null)
				return null;
			else return wordHere;
		}
		else {
			if (linkWordStart(key) == null)
				return null;
			else return linkWordStart(key).find(rest(key));
		}		
	}

	public void allKeyValue(Vector v) {

	}

	public void spellCheck1(Vector v, String start) {
		
	}

	public void prefixMatch(Vector v, String start) {
	}

	public void spellCheck2(Vector ws, String key, int errs) {

	}

	
	public void print(String string) {
		if (wordHere != null)
			System.out.println(string+" "+wordHere);
		else System.out.println(string+" empty");
		for (int i =0; i<26; i++) {
			if (links[i]!=null){
				links[i].print(string+"-");
			}
		}
	}

	public boolean delete(String key) {
		return false;
	}
}


