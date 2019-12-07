package coding.leetcode;

public class FindMedianSortedArrays {
    //My Solution
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        int len = len1 + len2;
        int left = len % 2 == 0 ? len / 2 - 1 : len / 2;
        int right = len / 2;
        int median = 0;
        int flag = 0, i = 0, j = 0;
        if (len1 == 0) return (nums2[left] + nums2[right]) / 2.0;
        if (len2 == 0) return (nums1[left] + nums1[right]) / 2.0;
        while (flag < left&&i<len1&&j<len2) {
            while (flag < left&&i<len1&&j<len2&&nums1[i] <= nums2[j]) {
                i++;
                flag++;
            }
            while (flag < left&&i<len1&&j<len2&&nums2[j] < nums1[i]) {
                j++;
                flag++;
            }
        }
        if(len%2==0){
            if(i>=len1) return (nums2[left-len1] +nums2[left-len1+1])/2.0;
            if(j>=len2) return (nums1[left-len2] +nums1[left-len2+1])/2.0;
            if (nums1[i] <= nums2[j]) {
                median += nums1[i];
                if (i + 1 < len1 && nums1[i + 1] < nums2[j]) {
                    median += nums1[i + 1];
                } else {
                    median += nums2[j];
                }
            } else {
                median += nums2[j];
                if (j + 1 < len2 && nums2[j + 1] < nums1[i]) {
                    median += nums2[j + 1];
                } else {
                    median += nums1[i];
                }
            }
        }else{
            if(i>=len1) return (2*nums2[left-len1])/2.0;
            if(j>=len2) return (2*nums1[left-len2])/2.0;
            if (nums1[i] <= nums2[j]) {
                median += 2*nums1[i];
            } else {
                median += 2*nums2[j];
            }
        }
        return  median/2.0;
    }

    //Approach #1 Recursive Approach
    public double findMedianSortedArrays2(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }

    public static void main(String[] args){
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] nums1 = {1,};
        int[] nums2 = {2,3,4,5};
        double result  = findMedianSortedArrays.findMedianSortedArrays2(nums1,nums2);
        System.out.print(result);
    }
}
