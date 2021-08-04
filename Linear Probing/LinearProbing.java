import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

// Implementation of closing hashing with linear probing.
// The probe sequence is defined by h(k), h(k) + 1, h(k) + 2, ... all
// modulus table size.

enum Status {EMPTY, OCCUPIED, DELETED};
public class LinearProbing <E> implements DictionaryADT<E> {

	private E[] dataTable;
    private Status[] statusTable;
    final private int TABLESIZE = 300151;

    @SuppressWarnings("unchecked")
    public LinearProbing() {
          dataTable = (E[])new Object[TABLESIZE];
          statusTable = new Status[TABLESIZE];
          for (int i = 0; i < TABLESIZE; i++)
             statusTable[i] = Status.EMPTY;
    }

    /**
     * @param item element to be inserted into the dictionary
     * @return number of collisions encountered during this insert
    */
    public int insert(E item) {
    	int hashIndex = item.hashCode();
    	int index=0;
    	int cnt = 0;
    	if(statusTable[hashIndex] != Status.OCCUPIED)
    	{
    		dataTable[hashIndex] = item;
    		statusTable[hashIndex] = Status.OCCUPIED ;
    		return 0;
    	}
    	else {
    		index = hashIndex;
    		cnt = 0;
    		while(dataTable[index]!= null)
    		{
    			statusTable[index] = Status.OCCUPIED ;
    			index++;
    			cnt ++;
    		}
    		dataTable[index] = item;
    		statusTable[index] = Status.OCCUPIED ;
    		return cnt;
    	}
      }  // int insert(...)

    /**
	 * @param item element to be searched in the dictionary
	 * @param count number of element-to-element comparisons made for this search
	 * @return the object if found; null otherwise
	 */
    public E search(E item, IntObject count) {
    	int hashIndex = item.hashCode();
    	int index = hashIndex;
    	while(dataTable[index]!= null)
		{
    		count.setData(count.getData() + 1);
			if(dataTable[index].equals(item))
			{
				return dataTable[index];
			}
			index++;
			
		}
        return null;  // to keep the crazy compiler happy
    }  // search(...)

    /**
	 * @param item element to be deleted from the dictionary
	 * @param count number of element-to-element comparisons made for this deletion
	 * @return the object if found and deleted; null otherwise
	 */
    public E delete(E item, IntObject count) {
    	int hashIndex = item.hashCode();
    	int index = hashIndex;
    	
    	while(statusTable[index]!= Status.EMPTY)
		{
    		
    		if(statusTable[index] != Status.DELETED)
    		{
    			count.setData(count.getData() + 1);
				if(dataTable[index].equals(item))
				{
					dataTable[index] = null;
					statusTable[index] = Status.DELETED;
					return item;
				}
    		}
			index++;
			
		}
        return null;  // to keep the crazy compiler happy
    }  // delete(...)

}