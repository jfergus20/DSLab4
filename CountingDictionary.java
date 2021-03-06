// Imports
import java.util.Vector;

public class CountingDictionary implements CS211CountingDictionaryInterface{
    
    private Word[] theTable;
    private int length = 10000;
    private double capacity = length * 0.8;
    private Word[] newTable;
    /**        
    public CountingDictionary(int num){
	theTable = new Word[num];
    }
    
    public CountingDictionary(){
	// dbl check 1003 is prime
	theTable = new Word[1003];
	}*/
    
    public void init(){
	theTable = new Word[length];
    }

    public void insert(String key){

	String keyTo = key.toLowerCase();
	int mapTo = hashIt(keyTo);

	int findIt = find(keyTo);
	//	System.out.println("keyTo: " + keyTo);


	if(findIt == -1){
	    if(theTable[mapTo] == null){
		theTable[mapTo] = new Word(keyTo, 1);
	    }else{
		int m = 0;
		while(m < (length - mapTo))
		    if(theTable[mapTo + m] == null){
			theTable[mapTo + m] = new Word(keyTo, 1);
			break;
		    }else{
			m++;
		    }
		}
	}else{
	    theTable[findIt].incrCount();
	}

        int ent = 0;
	for(int n = 0; n < length; n++){
	    if(theTable[n] != null){
		ent += 1;
	    }
	}
	
	if(ent >= getCapacity()){

	    System.out.println("resizing");
	    //	    theTable = resize();
	    resize();
	}
    }
    public boolean delete(String key){

	int check = find(key);
	if(check == -1){
	    return false;
	}else{
	    theTable[check] = null;	    
	    return true;
	}
    }
    public int find(String key){
	/* return the value associated with the given key,  If the key is NOT in the dictionary,                                                                                                                                            
	 * return -1                                                                                                                                                                                                                        
	 */
	int spotCheck = hashIt(key);
	for(int b = 0; b <= theTable.length; b++){
	    /**  System.out.println("b + spotCheck: " + (b + spotCheck));
	    System.out.println("(b + spotCheck) % theTable.length: " + ((b + spotCheck) % theTable.length));
	    System.out.println("theTable.length: " + theTable.length);
	    */
	    if(theTable[(b + spotCheck) % theTable.length] != null){
		if(theTable[(b + spotCheck) % theTable.length].getKey().toLowerCase().equals(key)){
		    theTable[(b + spotCheck) % theTable.length].incrCount();
		    return (b + spotCheck) % theTable.length;
		}
	    }

	}
	return -1;
		
    }
    
    public Vector<Word> allKeyValue(){
	/* return a list of all the key-value Words in the dictionary.  If your D/S is                                                                                                                    
	 * a BST or an ordered array, they should be in order.                                                                                                                      
	 */

	Vector<Word> words = new Vector<Word>();
	for(int w = 0; w < theTable.length; w++){
	    // System.out.println("w: " + w);
	    // System.out.println("theTable[w]: " + theTable[w]);
	    if(theTable[w] != null){
		words.addElement(theTable[w]);
	    }
	}
	return words;
    }

    public int hashIt(String key){

	int sumLetters = 0;
	int size = key.length();
	int toSender = 0;
	for (int c = 0; c < size; c++){

	    sumLetters += (int)key.charAt(c);
	}

	toSender = sumLetters/(size);

	if(toSender < length && toSender >= 0){
	    /**  System.out.println("ugh");
	    System.out.println("length: " + getLength());
	    System.out.println("theTable.length: " + theTable.length);
	    System.out.println("toSender: " + toSender);
	    */
	    return toSender;
	}else if(toSender >= getLength()){
	    // System.out.println("Here");
	    return toSender % getLength();
	}else{
	    // System.out.println("There");
	    return Math.abs(toSender);
	}
    }


    public void resize(){	
	System.out.println("---");
	setLength(2 * length);

	setCapacity((double)getLength() * 0.8);
 
	Vector<Word> oldTable = allKeyValue();
	
	Word[] copyMe = new Word[oldTable.size()];
	
	theTable = new Word[getLength()];
	
	//	System.arraycopy(copyMe, 0, theTable, 0, getLength());

	for(int k = 0; k < oldTable.size(); k++){
	    if(copyMe[k] != null && !copyMe[k].goner()){
	
		//	System.out.println("copyMe[k].getKey(): " + copyMe[k].getKey());
		//insert(copyMe[k].getKey());
		theTable[k] = copyMe[k];
		//	System.out.println("god damnit");
	    }
	}
	/**	theTable = new Word[2*theTable.length];
	return newTable;
	*/

    }
    
    private int getLength(){
	return length;
    }
    private void setLength(int newL){
	length = newL;
    }
    private double getCapacity(){
	return capacity;
    }
    private void setCapacity(double newC){
	capacity = newC;
    }

    


}


