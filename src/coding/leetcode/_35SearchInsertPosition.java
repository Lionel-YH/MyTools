package coding.leetcode;

public class _35SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int start = 0, end = nums.length - 1;
        if(start == end) return target<= nums[start]?start:start + 1;
        while (start < end){
            int mid = (start + end)/2;
            if(target < nums[mid]) end = mid - 1;
            else if(target > nums[mid]) start = mid + 1;
            else return mid;
        }
        return target<= nums[start]?start:start + 1;
    }

    public static void main(String[] args){
        int[] nums = {1, 4, 6, 7, 8};
        int target = 9;
        _35SearchInsertPosition searchInsertPosition = new _35SearchInsertPosition();
        int result = searchInsertPosition.searchInsert(nums, target);
        System.out.println(result);
    }
}
