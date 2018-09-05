package coding.leetcode;

public class _66_PlusOne {

    public int[] plusOne(int[] digits) {
        return plusOneAtPos(digits, digits.length-1);
    }

    public int[] plusOneAtPos(int[] digits, int pos){
        if(pos < 0){
            int[] newDigits = new int[digits.length + 1];
            newDigits[0] = 1;
            return newDigits;
        }
        if(digits[pos] < 9){
            digits[pos]++;
            return digits;
        }else{
            digits[pos] = 0;
            return plusOneAtPos(digits, pos-1);
        }
    }


    //leetcode reference
    public int[] plusOne_1(int[] digits) {

        int n = digits.length;
        for(int i=n-1; i>=0; i--) {
            if(digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        int[] newNumber = new int [n+1];
        newNumber[0] = 1;

        return newNumber;
    }


    public static void main(String[] args){
        _66_PlusOne plusOne = new _66_PlusOne();
        int[] nums = { 9, 9};
        int[] result = plusOne.plusOne(nums);
        for(int i:result){
            System.out.print(i);
        }
    }
}
