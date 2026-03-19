package basics;

public class Ex_ar1 {
    public static void main(String[] args) {
        int[] nums = new int[4];

        for (int i = 0; i < nums.length; i++) {
            nums[i]= ((i+1)*2);
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
        
    }
}
