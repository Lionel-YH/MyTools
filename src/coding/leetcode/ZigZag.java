package coding.leetcode;

public class ZigZag {
    /**My Solution
     * Z字型的理解，Z斜边的行数是(numRows-2)，要注意边界，for循环j的判断边界
     * @param s
     * @param numRows
     * @return
     */
    public String convert1(String s, int numRows) {
        if(numRows==1) return s;
        int size = s.length();
        String result = "";
        StringBuilder[] sb = new StringBuilder[numRows];
        for(int l=0;l<numRows;l++) sb[l]=new StringBuilder();
        for(int i=0;i<size;i+=2*numRows-2){
            for(int j=i;j<i+numRows&&j<size;j++){
                sb[j-i].append(String.valueOf(s.charAt(j)));
            }
            for(int j=i+numRows;j<i+2*numRows-2&&j<size;j++){
                sb[i+2*numRows-j-2].append(String.valueOf(s.charAt(j)));
            }
        }
        for(int k=0;k<numRows;k++){
            result += sb[k].toString();
        }
        return result;
    }

    //网络版，比自己的好在循环逻辑上。
    public String convert2(String s, int nRows) {
        char[] c = s.toCharArray();
        int len = c.length;
        StringBuffer[] sb = new StringBuffer[nRows];
        for (int i = 0; i < sb.length; i++) sb[i] = new StringBuffer();

        int i = 0;
        while (i < len) {
            for (int idx = 0; idx < nRows && i < len; idx++) // vertically down
                sb[idx].append(c[i++]);
            for (int idx = nRows-2; idx >= 1 && i < len; idx--) // obliquely up
                sb[idx].append(c[i++]);
        }
        for (int idx = 1; idx < sb.length; idx++)
            sb[0].append(sb[idx]);
        return sb[0].toString();
    }

    public static void main(String[] args){
        ZigZag zigZag = new ZigZag();
        String str = "ABCDEF";
        String result = zigZag.convert1(str,4);
        System.out.print(result);
    }
}
