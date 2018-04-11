package coding.leetcode;

import java.util.*;

public class LengthOfLongestSubstring {
    //My Solution
    public int lengthOfLongestSubstring1(String s) {
        List<Object> queue = new ArrayList<>();
        Set<Object> set = new HashSet<>();
        int result = 0;
        int len = s.length();
        for(int i=0;i<len;i++){
            char c = s.charAt(i);
            if(!set.contains(c)){
                set.add(c);
                queue.add(c);
            }else{
                if(queue.size()>result) result = queue.size();
                while((char)queue.get(0)!=c){
                    queue.remove(0);
                }
                queue.remove(0);
                queue.add(c);
                set = new HashSet<>(queue);
            }
        }
        return queue.size()>result?queue.size():result;
    }

    //Approach #2 Sliding Window
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    //Approach #3 Sliding Window Optimized
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static void main(String[] args){
        String str = "aab";
        LengthOfLongestSubstring lengthOfLongestSubstring = new LengthOfLongestSubstring();
        int result = lengthOfLongestSubstring.lengthOfLongestSubstring1(str);
        System.out.println(result);
    }
}
