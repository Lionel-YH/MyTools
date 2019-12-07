package coding.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _448_FindAllNumbersDisappeared {


    //Internet Solution
    //The basic idea is that we iterate through the input array and mark elements as negativeusing nums[nums[i] -1] = -nums[nums[i]-1].
    // In this way all the numbers that we have seen will be marked as negative. In the second iteration,
    // if a value is not marked as negative, it implies we have never seen that index before, so just add it to the return list.
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]) - 1;
            if(nums[val] > 0) {
                nums[val] = -nums[val];
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }


    public static void main(String[] args){
        _448_FindAllNumbersDisappeared findAllNumbersDisappeared = new _448_FindAllNumbersDisappeared();
        int[] nums = {1, 2, 7, 2, 4, 5, 5};
        List<Integer> result = findAllNumbersDisappeared.findDisappearedNumbers(nums);
        for (Integer i:result) {
            System.out.println(i);
        }
    }
}
