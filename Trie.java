import java.util.Vector;

public class Trie implements CS211CountingDictionaryInterface {

    TrieNode root = new TrieNode();
    
    public void insert(String key) {
	root.insert(key,"");
    }
    
    public boolean delete(String key) {
	if (root.find(key)==null)
	    return false;
	else root.delete(key);
	return true;
    }
    
    public int find(String key) {
	if (root == null) 
	    return -1;
	else {
	    Word w = root.find(key);
	    if (w==null)
		return -1;
	    else return w.getCount();
	}
    }
    
    public Vector<Word> prefixMatch(String start) {
	Vector v = new Vector<Word>();
	root.prefixMatch(v,start);
	return v;
    }
    
    public Vector<Word> spellCheck1(String start) {
	Vector v = new Vector<Word>();
	//		System.out.println("start: " + start);
	root.spellCheck1(v,start);
	return v;
    }
    
    @Override
	public Vector<Word> allKeyValue() {
	Vector v = new Vector<Word>();
	root.allKeyValue(v);
	return v;
    }
    
    public Vector<Word> spellCheck2(String key, int errs) {
	Vector ws = new Vector<Word>();
	// Should this be errs+1 or just errs?
	root.spellCheck2(ws,key,errs+1);
	return ws;
    }
    /**
    public Vector<Word> matchRegexChar(String key){
	Vector send = new Vector<Word>();
	root.matchRegexChar(send, key);
	return send;
	}*/
    
    public Word getRandom(){
	Vector wds = new Vector<Word>();
	return root.getRandom(wds);
    }

    public int wordCount(){
	int returner = root.wordCount();
	return returner;
    }
    
    /**    public int nodeCount(){
	int count = root.nodeCount(0);
	return count;
	}*/
    
    public void print() {
	root.print("");
    }
    public static void main(String[] args) {
	Trie t = new Trie();
	//	System.out.println("Inserting Words...");
	t.insert("hello");
	t.insert("why");;
	t.insert("hellor");
	t.insert("hello");
	t.insert("mezzo");
	t.insert("mezza");
	t.insert("a");
	t.insert("he");
	t.insert("him");
	t.insert("hallo");
	
	//	System.out.println("Calling Print Method");
	t.print();
	//		System.out.println("Finding Words...");
	System.out.println(t.find("hello"));
	System.out.println(t.find("hellor"));
	System.out.println(t.find("why"));
	
	System.out.println("All Key Value");
	Vector<Word> ws = t.allKeyValue();
	for (Word w: ws) {
	    System.out.println("WS "+w);
	}
	
	System.out.println("Prefix Match");
	ws = t.prefixMatch("hel");
	for (Word w: ws) {
	    System.out.println("SS "+w);
	}
	
	System.out.println("Spell Check 1");
	//		String checkMe = new String();
	//checkMe = "hazzo";
	//System.out.println("Checking: " + checkMe);
	//ws = t.spellCheck1(checkMe);
	ws = t.spellCheck1("hazzo");
	for (Word w: ws) {
	    System.out.println("ST "+w);
	}
	
	System.out.println("Spell Check 2");
	// Note: int parameter gets +1 in Trie.spellCheck2
	ws = t.spellCheck2("hezzo",1);
	for (Word w: ws) {
	    System.out.println("EM "+w);
	}
	
	System.out.println("WordCount");
	int qrs = 0;
	qrs = t.wordCount();
	System.out.println("WC: " + qrs);
	
	
	/**System.out.println("matchRegexChar");
	ws = t.matchRegexChar("?ello");
	for(Word w:ws){
	    System.out.println("MR: " + w);
	    }*/

	/**	System.out.println("NodeCount");
	int cou = t.nodeCount();
	System.out.println("nodeCount: " + cou);
	*/
	
	Word word = new Word("temp", 1);
	word = t.getRandom();
	System.out.println("getRandom: " + word);
	//System.out.println("Deleting why...");
	System.out.println(t.delete("why"));
	//System.out.println("Why deleted");
	t.print();
	
    }
    
    
}
