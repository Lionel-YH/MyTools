package coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class _167_TwoSum2 {

    //My Solution
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        int start = 0,end = nums.length - 1;
        while (start < end){
            if((nums[start] + nums[end]) == target){
                result[0] = ++start;
                result[1] = ++end;
                return result;
            }
            if((nums[start] + nums[end]) < target) start++;
            else end--;
        }
        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args){
        _167_TwoSum2 twoSum = new _167_TwoSum2();
        int[] nums = {2, 7, 11, 15};
        int[] result = twoSum.twoSum(nums,9);
        System.out.print(result[0]);
        System.out.print(",");
        System.out.print(result[1]);
    }
}
