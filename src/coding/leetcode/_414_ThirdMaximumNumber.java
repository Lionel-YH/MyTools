package coding.leetcode;

public class _414_ThirdMaximumNumber {

    // My Solution
    public int thirdMax1(int[] nums) {
        int[] maxs = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};
        boolean[] flag = {false, false, false};
        for (int i = 0;i<nums.length;i++){
            if (nums[i] >= maxs[0]){
                if (flag[0] == true){
                    if (nums[i]==maxs[0]) continue;
                    if (flag[1]==true){
                        maxs[2]=maxs[1];
                        flag[2]=true;
                    }
                    maxs[1]=maxs[0];
                    flag[1]=true;
                    maxs[0]=nums[i];
                }else {
                    maxs[0]=nums[i];
                    flag[0]=true;
                }
            }else if(nums[i]>=maxs[1]){
                if(flag[1]==true){
                    if (nums[i]==maxs[1]) continue;
                    maxs[2]=maxs[1];
                    flag[2]=true;
                    maxs[1]=nums[i];
                }else {
                    maxs[1]=nums[i];
                    flag[1]=true;
                }
            }else if (nums[i]>=maxs[2]){
                maxs[2]=nums[i];
                flag[2]=true;
            }
        }
        if (flag[2]==true) return maxs[2];
        return maxs[0];
    }

    // Optimal Solution
    public int thirdMax2(int[] nums) {
        Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
            if (max1 == null || n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
    }

    public static void main(String[] args){
        _414_ThirdMaximumNumber thirdMaximumNumber = new _414_ThirdMaximumNumber();
        int[] nums = {1,-2147483648,2};
        int result = thirdMaximumNumber.thirdMax1(nums);
        System.out.print(result);
    }
}
