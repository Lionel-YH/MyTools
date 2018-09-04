package coding.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _119_PascalTriangle2 {

    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if(rowIndex < 0) return result;
        result.add(1);
        if(rowIndex == 0) return  result;
        result.add(1);
        if(rowIndex == 1) return result;
        List<Integer> tmpList = getRow(rowIndex -1);
        for(int i =1;i< rowIndex;i++){
            result.add(i,tmpList.get(i-1)+tmpList.get(i));
        }
        return result;

    }

    public static void main(String[] args){
        _119_PascalTriangle2 pascalTriangle2 = new _119_PascalTriangle2();
        int nums = 3;
        List<Integer> result = pascalTriangle2.getRow(nums);
        for(int i:result){
            System.out.print(i);
        }
    }
}
