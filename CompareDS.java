
/**
 * CompareDS - compares the performance of DS_My against any of one of the TA implementations
 * @author Weihang Guo
 * 
 */
public class CompareDS {
  
  /**
   * Compare the performance of DS_My and DS_Brian, and print out the result.
   * @param testNumber the number of work to do
   */
  private void compare(int testNumber) {
    //create a DS_My and a DS_Brian
    DS_My my = new DS_My();
    DS_Brian tas = new DS_Brian();
    String key = "1";
    //compute the time it takes using DS_My
    long startTime1 = System.nanoTime();
    for (int i = 0; i < testNumber; i ++) {
      my.remove(key);
    }
    long endTime1 = System.nanoTime();
    long myTime = endTime1 - startTime1;
    //compute the time it takes using DS_Brian
    long startTime2 = System.nanoTime();
    for (int i = 0; i < testNumber; i ++) {
      tas.remove(key);
    }
    long endTime2 = System.nanoTime();
    long tasTime = endTime2 - startTime2;
    //display the compare result
    System.out.println("CompareDS.main Compares work time for: DS_My and DS_ta-name-here\n"
        + "Description: remove" + testNumber + 
            " items\n\nDS_My is doing work for " + testNumber + " values\nIt took " + myTime + 
                " ns to process " + testNumber + " items\nDS_TA is doing work for " + testNumber + 
                    " values\nIt took " + tasTime + " ns to process " + testNumber + " items");
    
  }
  
  /**
   * Main method of the program
   * 
   * @param args the string arguments from the command line
   */
  public static void main(String[] args) {
    CompareDS test = new CompareDS();
    test.compare(100);
    test.compare(1000);
    test.compare(10000);
    test.compare(100000);
    test.compare(1000000000);
  }

}
