package coding.leetcode;

public class _22_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int i = 0;
        int n = nums.length;
        while(i < n){
            if(nums[i] == val){
                nums[i] = nums[n-1];
                n--;
            }else {
                i++;
            }
        }
        return n;
    }

    public static void main(String[] args){
        _22_RemoveElement removeElement = new _22_RemoveElement();
        int[] nums = {2};
        int result = removeElement.removeElement(nums,2);
        System.out.print(result);
    }
}
