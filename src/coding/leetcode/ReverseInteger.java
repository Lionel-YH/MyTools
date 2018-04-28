package coding.leetcode;

public class ReverseInteger {
    /**
     * 来自讨论区的答案，注意负数取模运算的结果！
     * @param x
     * @return
     */
    public int reverse(int x)
    {
        int result = 0;
        while (x != 0)
        {
            int tail = x % 10;
            int newResult = result * 10 + tail;
            if ((newResult - tail) / 10 != result)
            { return 0; }
            result = newResult;
            x = x / 10;
        }
        return result;
    }

    public static void main(String[] args){
        ReverseInteger reverseInteger = new ReverseInteger();
        int num = -12232;
        int result = reverseInteger.reverse(num);
        System.out.print(result);
    }
}
