package questions;

import java.util.Arrays;

public class RemoveDuplicatesFromSortedArray {

    public static void main (String [] args )
    {
        Integer[] arr = {1,1,1,2,2,3,4,4};
        Integer[] res = removeDuplicates1(arr);
        System.out.println(Arrays.toString(res));
    }

    private static Integer[] removeDuplicates1(Integer[] arr) {
      if (arr.length ==0 ) return arr;

      int slow = 1;

      for (int fast =1 ; fast < arr.length ; fast++){
          if (!arr[fast].equals((arr[fast-1]))){
              arr[slow] = arr[fast];
              slow++;
          }
      }

      for (int i= slow ; i< arr.length ; i++){

          arr[i] = null;
      }
    return arr;

    }


}
