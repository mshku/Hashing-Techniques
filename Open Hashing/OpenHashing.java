// Implementation of Open Hashing also known as
// hashing by chaining.  Each chain is implemented
// using Java's LinkedList.

import java.util.LinkedList;

public class OpenHashing <E> implements DictionaryADT <E>{

	private Object [] dataChain;
    final private int TABLESIZE = 300151;

    @SuppressWarnings("unchecked")
    public OpenHashing() {
          dataChain = new Object [TABLESIZE];
          for (int i = 0; i < TABLESIZE; i++)
             dataChain[i] = new LinkedList<E>();
    }

    /**
     * @param item to be inserted into the hash table
     * @return the number of collisions for this insert
     */
    @SuppressWarnings("unchecked")
    public int insert(E item) {
    	
    	int hashIndex = item.hashCode();
    	LinkedList<E> hashElement = (LinkedList<E>)dataChain[hashIndex];
    	try {
    		E headValue = hashElement.getFirst();
        	
            hashElement.addLast(item);
            dataChain[hashIndex] = hashElement;
            return 1;
		}
		catch(Exception e) {
			hashElement.addLast(item);
			dataChain[hashIndex] = hashElement;
			return 0;
		}
    	
    }  // int insert(...)

    /**
     * @param item element to be searched in the hash table
     * @param count the number of element-to-element comparisons
     * made for this search
     * @return the object if found; null if the search is unsuccessful
     */
    @SuppressWarnings("unchecked")
    public E search(E item, IntObject count) {
    	int hashIndex = item.hashCode();
    	LinkedList<E> hashElement = (LinkedList<E>)dataChain[hashIndex];
    	count.setData(0);
    	try {
    		E headValue = hashElement.getFirst();
        	
            while(headValue!=null)
            {
            	count.setData(count.getData()+1);
            	if(headValue.equals(item))
            	{
            		return headValue;
            	}
            	else {
            		hashElement.removeFirst();
            		headValue = hashElement.getFirst();
            	}
            }
            return null;
		}
		catch(Exception e) {
			return null;
		}
    }  // search(...)

    /**
	 * @param item element to be deleted from the dictionary
	 * @param count number of element-to-element comparisons made for this deletion
	 * @return the object if found and deleted; null otherwise
	 */
    public E delete(E item, IntObject count) {
    	int hashIndex = item.hashCode();
    	LinkedList<E> hashElement = (LinkedList<E>)dataChain[hashIndex];
    	count.setData(0);
    	int cnt = 0;
    	try {
    		E headValue = hashElement.getFirst();
        	
            while(headValue!=null)
            {
            	cnt =cnt + 1;
            	count.setData(count.getData()+1);
            	if(headValue.equals(item))
            	{
            		hashElement.remove(item);
            		dataChain[hashIndex] = hashElement;
            		return headValue;
            	}
            	else {
            		try {
            		headValue = hashElement.get(cnt);
	            	}
	        		catch(Exception e) {
	        			return null;
	        		}
            	}
            }
            return null;
		}
		catch(Exception e) {
			return null;
		}
    }
}