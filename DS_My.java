

/**
 * DS_My.java created by Weihang Guo on Mac Air in p1_project
 * 
 * Author:   Weihang Guo (wguo63@wisc.edu)
 * Date:     01/28/2020
 * 
 * Course:   CS400
 * Semester: Spring 2020
 * Lecture:  001
 */


/**
 * DS_My - a data structure that can store pairs
 * @author Weihang Guo
 * 
 */
public class DS_My implements DataStructureADT< String, String > {

    private int size;
    private Pair head;
    private Pair tail;
    
    /**
     * Pair - a linked node
     * @author Weihang Guo
     * 
     */
    private class Pair{
      private String value;
      private String key;
      private Pair next;
      private Pair previous;
      private Pair(String key, String value) {
        this.value = value;
        this.key = key;
        next = null;
        previous = null;
      }
      
      /**
       * set the next node
       * @param next - the next node
       */
      private void setNext(Pair next) {
        this.next = next;
      }
      
      /** 
       * set the previous node
       * @param previous the previous node
       */
      private void setPrevious(Pair previous) {
        this.previous = previous;
      }
      
      /**
       * get the next node
       * @return the next node
       */
      private Pair getNext() {
        return next;
      }
      
      /**
       * get the previous node
       * @return the previous node
       */
      private Pair getPrevious() {
        return previous;
      }
      
      /**
       *  get the value of a node
       * @return the value of a node
       */
      private String getValue() {
        return value;
      }
      
      /**
       * get the key of a node
       * @return the key of a node
       */
      private String getKey() {
        return key;
      }
    }
    
    /**
     * the constructor of DS_My
     */
    public DS_My() {
      size = 0;
      head = null;
      tail = null;
        
    }

    /**
     * Insert a new element to the list.
     * @param key - the key of the element
     * @param value -  the value of the element
     * @throw RuntimeException when there is a duplicate key or the key is null
     */
    public void insert(String key, String value) throws RuntimeException {
      
      //if the key is null, throw an IllegalArgumentException
      if (key == null) {
        throw new IllegalArgumentException("null key");
      }
      
      //if the key already exists, throw a RuntimeException
      if (contains(key) == true) {
        throw new RuntimeException("duplicate key");
      }
      
      Pair newPair = new Pair(key, value);
      if (head == null) {//the data structure is empty
        //set both head and tail to this new element
        head = newPair;
        tail = newPair;
      } else {//the data structure is not empty
      //assign this new element to the next node of tail
        tail.setNext(newPair); 
      //assign tail to the previous node of this element
        newPair.setPrevious(tail);
      //set tail to this element
        tail = newPair;
      }
      size ++;
      
    }

    /**
     * remove an element from the list
     * @param key - the key of the element being removed
     * @return true if the element has been removed, false if the removal fails
     */
    public boolean remove(String key) {
      // If key is null, throws IllegalArgumentException("null key") without decreasing size
      if (key == null) {
        throw new IllegalArgumentException("null key");
      }
      if (size == 0) {//when the list is empty, there's nothing can be removed
        return false;
      }
      // If key is found, Removes the key from the data structure and decreases size
      // If key is not found, returns false.
      Pair pair = head;
      for (int i = 0; i < size; i ++) {//traverse all the elements
        if (pair.getKey().equals(key)) {
          //if there is only one element
          if (size == 1) {
            head = null;
            tail = null;
          } else if (i == 0) {//if the key is found at the head
            head = head.getNext();
            head.setPrevious(null);
          } else if (i == size - 1) {//if the key is found at the tail
            tail = tail.getPrevious();
            tail.setNext(null);
          } else {//if the key is found elsewhere
          pair.getPrevious().setNext(pair.getNext());
          pair.getNext().setPrevious(pair.getPrevious());
          }
          size --;
          return true;
        }
        pair = pair.getNext();
      }
      return false;
    }

    /**
     * get the value of the element with the given key
     * @param key the given key of the element
     * @return the value associated with the specified key
     */
    public String get(String key) {
      // If key is null, throws IllegalArgumentException("null key")
      if (key == null) {
        throw new IllegalArgumentException("null key");
      }
      Pair pair = head;
      for (int i = 0; i < size; i ++) {//traverse all the elements
        if (pair.getKey().equals(key)) {
          return pair.getValue();
        }
        pair = pair.getNext();
      }
      return null;// return null if key is not null and is not found in data structure
    }

    /**
     * tells if the list contains the element with the given key
     * @param key - the key to be found
     * @return true if the list contains the element with the given key, 
     * false if there is no such key
     */
    public boolean contains(String key) {
      Pair pair = head;
      for (int i = 0; i < size; i ++) {//traverse all the elements
        if (pair.getKey().equals(key)) {
          return true;
        }
        pair = pair.getNext();
      }
      return false;
    }

    /**
     * return the size of the list
     * @return the size of the list
     */
    public int size() {
      return size;
    }
    
                                              

}                            
    
