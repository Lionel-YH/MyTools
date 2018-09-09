package coding.leetcode;

public class _283_MoveZeroes {

    // My Solution
    public void moveZeroes1(int[] nums) {
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]==0){
                int j = i;
                while(j<nums.length-1 && nums[j+1] != 0){
                    nums[j] += nums[j+1];
                    nums[j+1] = nums[j] - nums[j+1];
                    nums[j] -= nums[j+1];
                    j ++;
                }
            }
        }
        for(int i:nums) {
            System.out.print(i + ",");
        }
    }

    // Optimal Solution
    public void moveZeroes2(int[] nums) {
        int lastNonZero = 0;
        for(int i=0;i< nums.length;i++){
            if(nums[i]!=0) {
                int tmp = nums[lastNonZero];
                nums[lastNonZero]=nums[i];
                nums[i]=tmp;
                lastNonZero++;
            }
        }
        for(int i:nums) {
            System.out.print(i + ",");
        }
    }


    public static void main(String[] args){
        _283_MoveZeroes moveZeroes = new _283_MoveZeroes();
        int[] nums = {1};
        moveZeroes.moveZeroes2(nums);
    }
}
