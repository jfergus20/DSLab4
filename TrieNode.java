import java.util.Vector;
import java.util.Random;
public class TrieNode {
    
    private Word wordHere;
    private TrieNode[] links;
    
    public TrieNode() {
	wordHere = null;
	links = new TrieNode[26];
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
    
    public void insert(String key, String toHere) {
	if(key.length() != 0) {
	    if(linkWordStart(key) == null){
                this.links[let(firstChar(key))] = new TrieNode();
            }
            toHere = toHere + firstChar(key);
            links[let(firstChar(key))].insert(rest(key), toHere);
	    // wordHere = new Word(toHere, 1);                                                                                                                                                                          
        }else if(wordHere != null){
	    wordHere.incrCount();
	}else{
	    /**	    if(linkWordStart(key) == null){
		this.links[let(firstChar(key))] = new TrieNode();
	    }
	    toHere = toHere + firstChar(key);
	    links[let(firstChar(key))].insert(rest(key), toHere);
	    */
	    wordHere = new Word(toHere, 1);
        }
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
    
    public Vector<Word> allKeyValue(Vector v){
	if (wordHere != null){
             v.add(wordHere);        
	}
	for (int i =0; i<26; i++) {
            if (links[i]!=null){
                links[i].allKeyValue(v);
            }
        }
	return v;
    }
    
    public Vector<Word> spellCheck1(Vector v, String start) {
	for(int y = 0; y < start.length(); y++){
	    if(links[let(firstChar(start))] == null){
		v = this.allKeyValue(v);
		return v;
	    }else{
		v = links[let(firstChar(start))].spellCheck1(v, rest(start));
		return v;
	    }	    
	    //links[let(start.charAt(y))]
	    //links[y].allKeyValue(v)
	    //prefixMatch(v, chipOff(start, y))q
	}
	return null;
    }
	
    public Vector<Word> prefixMatch(Vector v, String start) {
	if(start.length() == 0){
	    if(anyKids()){
		for(int n = 0; n < 26; n++){
		    if(links[n] != null){
			// fill vector
			v = links[n].allKeyValue(v);
		    }
		}
		return v;
	    }else{
		if(wordHere != null){
		    v.add(wordHere);
		    return v;
		}else{
		    System.out.println("No words in dictionary with that prefix...");
		    return null;
		}
	    }
	}else{
	    //Recursion here
	    //System.out.println("Recursion here");
	    //System.out.println("rest(start): " + rest(start));
	    v = links[let(firstChar(start))].prefixMatch(v, rest(start));
	    return v;
	}    
    }

    public Vector<Word> spellCheck2(Vector ws, String key, int errs) {
	//System.out.println("ws: " + ws);
	Vector<Word> rectov = allKeyValue(ws);
	//System.out.println("rectov: " + rectov);
	Vector<Word> forlater = new Vector<Word>();
	for(int o = 0; o < rectov.size(); o++){
	    if(rectov.elementAt(o).getKeyLength() == key.length()){
		forlater.add(rectov.elementAt(o));
	    }
	}
	//System.out.println("forlater: " + forlater);	
	//System.out.println("errs: " + errs);
	//System.out.println("ws: " + ws);
	ws.clear();
	for(int p = 0; p < forlater.size(); p++){
	    //System.out.println("~~~~~~~~~~~~~~");
	    //System.out.println("findDist(): " + findDist(key, forlater.elementAt(p).getKey()));
	    if(findDist(key, forlater.elementAt(p).getKey()) <= errs){
		// change this to = instead of .add
		//System.out.println("ws: " + ws);
		ws.addElement(forlater.elementAt(p));
		//System.out.println("ws: " + ws);
		//System.out.println("errs: " + errs);
		//System.out.println("forlater.elementAt(p): " + forlater.elementAt(p) + " has been added to ws");
		//System.out.println();
	    }
	}
	//System.out.println("ws: " + ws);
	return ws;
    }
    
    private int findDist(String key, String word){
	int count = 0;
	char[] keyLets = key.toCharArray();
	char[] wordLets = word.toCharArray();
	for(int z = 0; z < key.length(); z++){
	    if(keyLets[z] != wordLets[z]){
		count++;
	    }
	}
	//System.out.println("key: " + key);
	//System.out.println("word: " + word);
	//System.out.println("count: " + count);
	//System.out.println();
	return count;
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
	if(find(key) != null){
	    return deleter(key);
	}else{
	    return false;
	}
    }
    // Delete Helper
    private boolean deleter(String key){
	//System.out.println("deleter(" + key + ")");
	if(key.length() == 1){
	    if(links[let(firstChar(key))].wordHere == null){
		//System.out.println("links[firstChar] set to null");
     		return false;
	    }
	    if(links[let(firstChar(key))].anyKids()){
		links[let(firstChar(key))].wordHere = null;
		//System.out.println("links[firstChar] set to null + has kids");
		return true;
	    }else{
		links[let(firstChar(key))] = null;
		//System.out.println("links[firstChar] set to null + no kids");
		return true;
	    }
	}else{
	    if(!anyKids()){
		//System.out.println("No Kids");
		return false;
	    }else if(links[let(firstChar(key))].deleter(rest(key))){
		if(links[let(firstChar(key))].anyKids()){
		    return true;
		}else{
		    links[let(firstChar(key))] = null;
		    //System.out.println("links[firstChar] set to null");
		    return true;
		}
       	    }else{
		return false;
	    }
	}
    }

    private boolean anyKids(){
	for(int i = 0; i < 26; i ++){
	    if(this.links[i] != null){
		//System.out.println("Child is not null");
		return true;
	    }
	}
	//System.out.println("Child is null");
	return false;
    }

    //~~~~~~~~~~~~~
    // New Methods
    //~~~~~~~~~~~~~
    public int wordCount(){
        // returns word count                                                                                                                                                                                                                
        // beamMeUp = variable to return, nummber of words in the dictionary                                                                                                                                                           
        int beamMeUp = 0;
        // counter = number of links that are not null                                                                                                                                                                                      
        int counter = 0;
        for(int ok = 0; ok < 26; ok++){
            if(links[ok] != null){
                if(links[ok].wordHere != null){
                    beamMeUp += 1;
                }
		// RECURSIVE CALL
                beamMeUp += links[ok].wordCount();
                //System.out.println("beamMeUp: " + beamMeUp);                                                                                                                                                                               
                counter++;
            }
        }
        // If dictionary is empty                                                                                                                                                                                                            
        if(counter == 0){
            return 0;
        }
        return beamMeUp;
        // NOTE: This can also be done with (return allKeyVaue().size)                                                                                                                                                                
	// However, this method uses recursion
    }
    
    
    public Word getRandom(Vector v){
        // returns a random word in the dictionary                                                                                                                                                                                           
        Random r = new Random();
        int rand = r.nextInt(wordCount());
        return allKeyValue(v).elementAt(rand);
    }
    
    /**    
    public Vector<Word> matchRegex(Vector vs, String key){
	if(firstChar(key) == '?'){
	    // ? is one or zero characters
	    for(int k = 0; k < 26; k++){
		if(links[k] != null){
		    if(matchRegex(vs, rest(key)) != null){
		    }
		}
	    }
	}else if(firstChar(key) == '*'){
	    // * can be a string of characters
	    if(links[let(firstChar(rest(key)))] != null){
		vs = links[let(firstChar(rest(key)))].matchRegex(vs, rest(key));
	    }else{
		for(int in = 0; in < 26; in++){
		    vs = links[in].matchRegex(vs, key);
		}
	    }
	}else{
	    // recursion here
	    if(key.length() != 1){
		if(this.links[let(firstChar(key))] != null){
		    vs = this.links[let(firstChar(key))].matchRegex(vs, rest(key));
		}else{
		    System.out.println("Something went wrong...");
		}
		return vs;
	    }
	}
	return vs;
	}*/
    
}


