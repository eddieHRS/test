import java.util.*;

/**
 * @title: Solution
 * @Author eddie
 * @Date: 2021/3/4 18:50
 * @Version 1.0
 */

public class Solution {
    public static void main(String[] args){
        Solution solution = new Solution();
        //int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        //int [] nums = new int[]{1556913,-259675,-7667451,-4380629,-4643857,-1436369,7695949,-4357992,-842512,-118463};
        //int []nums = new int[]{9210,-5402,8022,-4660,-1719,-9686,3899,8543,-8813,2070,3791,3177,-775,-9400,-7036,-7050,-9843,2563,-1190,5216,-1089,2210,5775,1027,2729,4947,-6183,5850,1616,-5259,3605,-5142};
        int[]nums = new int[]{1};
        System.out.println(solution.defangIPaddr("127.0.0.1"));
    }
    public int minAbsDifference(int[] nums, int goal) {
        //首先将nums 一分为2 然后在每一个里面算得所有的可能的值
        // 将这两个结果数组意义一一比对
        if(nums.length == 1){
            return Math.min(Math.abs(goal),Math.abs(nums[0] - goal));
        }
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for(int i = 0; i < nums.length/2;i++){
            left.add(nums[i]);
        }
        for(int i = nums.length/2; i < nums.length;i++){
            right.add(nums[i]);
        }
        List<Integer> leftList = generateList(left);
        Collections.sort(leftList);
        System.out.println(left);
        System.out.println(leftList);
        List<Integer> rightList = generateList(right);
        Collections.sort(rightList);
        System.out.println(right);
        System.out.println(rightList);
        int leftPointer = 0;
        int rightPointer = rightList.size()-1;
        int min = Integer.MAX_VALUE;
        while(leftPointer < leftList.size() && rightPointer >= 0){
            int sum = leftList.get(leftPointer) + rightList.get(rightPointer);
            if(sum == goal)
                return 0;
            if(sum > goal) {
                rightPointer--;
                min = Math.min(sum - goal, min);
            }
            if(sum < goal){
                leftPointer++;
                min = Math.min(goal-sum,min);
            }

        }
        return min;
    }

    private List<Integer> generateList(List<Integer> list){
        Map<Integer,Boolean> map = new HashMap<>();
        for(int i = 0; i < 1 << list.size(); i++){
            int num = 0;
            int bitMap = i;
            for(Integer n:list){
                num += n * (bitMap & 1);
                bitMap = bitMap >> 1;
            }
            if(!map.containsKey(num))
                map.put(num,true);
        }
        return new ArrayList<>(map.keySet());

    }
    public String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        char[] chars = address.toCharArray();
        for(char c:chars){
            if(c == '.')
                sb.append("[.]");
            else
                sb.append(c);
        }
        int[] n = new int[]{1,2,3};
        Arrays.sort(n);
        List<Boolean> list = new ArrayList<>();
        return sb.toString();


    }

}
