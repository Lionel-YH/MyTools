package coding.leetcode;

public class LongestPalindrome {
    //My Solution
    public String longestPalindrome(String s) {
        int len = s.length();
        String result = String.valueOf(s.charAt(0));
        for(int i=1;i<len-1;i++){
            int j = i-1,k=i+1;
            for(;j>=0&&k<len;j--,k++){
                if(s.charAt(j)!=s.charAt(k)) break;
            }
            if(result.length()<(k-j-1)) result = s.substring(j+1,k);
        }
        for(int i=0;i<len;i++){
            int j = i,k = i+1;
            for(;j>=0&&k<len;j--,k++){
                if(s.charAt(j)!=s.charAt(k)) break;
            }
            if(result.length()<(k-j-1)) result = s.substring(j+1,k);
        }
        return result;
    }
    public static void main(String[] args){
        LongestPalindrome longestPalindrome = new LongestPalindrome();
        String str = "cbbd";
        String result = longestPalindrome.longestPalindrome(str);
        System.out.print(result);
    }
}
