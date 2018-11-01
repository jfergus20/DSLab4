public class Word implements Comparable<Word> {
    
    private String theWord;
    private int theCount;
    private boolean deleted = false;
    
    public String toString() {
	return theWord+"  "+theCount;
    }
    
    public Word(String word, Integer count) {
	this.theWord = word;
	theCount = count;
    }
    
    /* compare to should return a positive number if this is greater
     * than other, 0 if they are equal and a negative number if this is less.
     * 
     * this is greater (less than) if its count is greater (less than) that of 
     * other.  If the counts are equal, you should determine which theWord is
     * larger as a String.  compareTo is implemented in Java for Strings, you
     * should use it
     */
    public int compareTo(Word other) {
	
	if(theCount > other.getCount()){
	    return 1;
	}else if(theCount < other.getCount()){
	    return -1;
	}else{
	    if(theWord.compareTo(other.getKey()) > 0){
		return 1;
	    }else if(theWord.compareTo(other.getKey()) < 0){
		return -1;
	    }else{
		return 0;
	    }
	}	
    }
    
    public int getCount(){
	return theCount;
    }

    public int getKeyLength(){
	return theWord.length();
    }
    
    public void setCount(int newCount){
	theCount = newCount;
    }
    
    public String getKey(){
	return theWord;
    }
    
    public void setKey(String newKey){
	theWord = newKey;
    }
    
    public void incrCount(){
	theCount += 1;
    }

    public void bye(){
        deleted = true;
    }

    public boolean goner(){
        return deleted;
    }
    

}
