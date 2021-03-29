import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * DataStructureADTTest - test the implementation of a data structure
 * @author Weihang Guo
 * 
 * @param <T> the type of the data structure
 */
abstract class DataStructureADTTest<T extends DataStructureADT<String,String>> {
    
    private T ds;
    
    protected abstract T createInstance();

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    @BeforeEach
    void setUp() throws Exception {
        ds = createInstance();
    }

    @AfterEach
    void tearDown() throws Exception {
        ds = null;
    }

    
    @Test
    void test00_empty_ds_size() {
        if (ds.size() != 0)
        fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    

    @Test
    void test01_insert_one() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.size()==1);
    }
    
    @Test
    void test02_insert_remove_one_size_0() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (ds.remove(key)); // remove the key
        if (ds.size() != 0)
            fail("data structure should be empty, with size=0, but size="+ds.size());
    }
    
    @Test
    void test03_duplicate_exception_thrown() {
        String key = "1";
        String value = "one";
        ds.insert("1", "one");
        ds.insert("2", "two");
        try { 
            ds.insert(key, value); 
            fail("duplicate exception not thrown");
        }
        catch (RuntimeException re) { }
        assert (ds.size()==2);
    }
            
    
    @Test
    void test04_remove_returns_false_when_key_not_present() {
        String key = "1";
        String value = "one";
        ds.insert(key, value);
        assert (!ds.remove("2")); // remove non-existent key is false
        assert (ds.remove(key));  // remove existing key is true
        if (ds.get(key)!=null)
            fail("get("+key+ ") returned " + ds.get(key) + " which should have been removed");
    }

    
    /**
     * inserts one item and fails if unable to remove it
     */
    @Test
    void test05_insert_remove_one() {
      String key = "1";
      String value = "one";
      ds.insert(key, value);
      assert(ds.remove(key));//remove the key
      if (ds.contains(key))
        fail("fail to remove the key");
    }
    
    /**
     * inserts many items and fails if size is not correct 
     */
    @Test
    void test06_insert_many_size() {
      String key1 = "1";
      String value1 = "one";
      String key2 = "2";
      String value2 = "two";
      String key3 = "3";
      String value3 = "three";
      ds.insert(key1, value1);
      ds.insert(key2, value2);
      ds.insert(key3, value3);
      if (ds.size() != 3)
        fail("there should be three elements in the data structure, but size="+ds.size());
    }
    
    /**
     * inserts two pairs with different keys, but same values; fails if second doesn't insert
     */
    @Test
    void test07_duplicate_values() {
      String key1 = "1";
      String value = "one";
      String key2 = "2";
      ds.insert(key1, value);
      assert(ds.size() == 1);
      ds.insert(key2, value);
      if (ds.size() == 1) 
        fail("the second doesn't insert");
    }
    
    
    /**
     * get the value of an existing item with the key; fails if the value returned is not correct
     */
    @Test
    void test08_get_value() {
      String key = "1";
      String value = "one";
      ds.insert(key, value);
      if (!ds.get(key).equals(value)) 
        fail("fail to get the right value");
    }
    
    /**
     * insert an item and fails if the list doesn't contain it
     */
    @Test
    void test09_contains_true() {
      String key = "1";
      String value = "one";
      ds.insert(key, value);
      assert(ds.contains(key)); 
      
    }
    
    /**
     * fails if the list contains a non-existing item
     */
    @Test
    void test10_contains_false() {
      String key = "1";
      assert(!ds.contains(key));
    }

    /**
     * insert an item, remove it, and insert it again; fails if the insertion cannot be made
     */
    @Test
    void test11_insert_again_after_remove() {
      String key = "1";
      String value = "one";
      ds.insert(key, value);
      assert(ds.remove(key));
      ds.insert(key,value);
      if (!ds.contains(key)) {
        fail("fail to insert the same key after remove it");
      }
    }
    
    /**
     * insert 1000 items; fails if the size is not 1000
     */
    @Test
    void test12_insert_1000_items() {
      for (int i = 0; i < 1000; i ++) {
        ds.insert(i + "", i + "");
      }
      assert(ds.size() == 1000);
    }
    
    /**
     * insert 1000 items and remove all of them; fails if the size after removal is not 0
     */
    @Test
    void test13_remove_1000_items() {
      for (int i = 0; i < 1000; i ++) {
        ds.insert(i + "", i + "");
      }
      for (int i = 0; i < 1000; i ++) {
        ds.remove(i + "");
      }
      assert(ds.size() == 0);
    }
    
    

}
