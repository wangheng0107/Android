package com.example.threadpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 研究线程池，使用场景和使用方式
 */
public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 排序算法
        paixu();
        // 牛客top101
        top101();
        // 剑指offer
        offer();
    }
    public void paixu() {
//        maopao();
//        xuanze();
    }

    // 各类排序算法
    //算法1——冒泡排序
    public void maopao() {// 9分54秒
        int nums[] = {1, 3, 4, 9, 8, 5, 3};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }

        for (int num : nums) {
            Log.e(TAG, "冒泡排序：" + num);
        }
    }

    //算法2_选择排序
    public void xuanze() {// 4分50
        int nums[] = {1, 3, 4, 9, 8, 5, 3};
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i; j < nums.length - 1; j++) {
                if (nums[i] < nums[j + 1]) {
                    int temp = nums[i];
                    nums[i] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        for (int num : nums) {
            Log.e(TAG, "选择排序：" + num);
        }
    }

    // 算法4：插入排序（打扑克类似）

    // 算法3：快速排序，平均时间复杂度为O(nlogn)，空间复杂度为O(logn)

    // 算法5：二分查找

    // 牛客top101
    public void top101() {
//        deleteRePeatNum();
//        xuanzhuan();
//        containsDuplicate();
//        containsDuplicate();
//        intersect();
        replaceSpace();
    }

    // 牛客top101 https://www.nowcoder.com/exam/oj?fromPut=ad_baidu_sem_tiku_oj&bd_vid=11420667720602341616
    //算法1_删除数组中重复元素，先排序
    public void deleteRePeatNum() {//思路没找到
        int nums[] = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i; j < nums.length - 1; j++) {
//                if (nums[i] > nums[j + 1]) {
//                    int temp = nums[i];
//                    nums[i] = nums[j + 1];
//                    nums[j + 1] = temp;
//                }
//            }
//        }

        //双指针方式解决1
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        Log.e(TAG, "总数是：" + (i + 1));
        for (int n = 0; n < i + 1; n++) {
            Log.e(TAG, "输出元素：" + nums[n]);
        }
//        // 双指针2
//        int count = 0;
//        for (int i = 0; i < nums.length - 1; i++) {
//            if (nums[i] != nums[i + 1]) {
//                count++;
//                nums[count] = nums[i + 1];
//            }
//        }
//        Log.e(TAG, "总数是：" + (count + 1));
//        for (int n = 0; n < count + 1; n++) {
//            Log.e(TAG, "输出元素：" + nums[n]);
//        }

//        int i = 0;
//        for (int j = 1; j <nums.length ; j++) {
//            if (nums[i] != nums[j]){
//                nums[++i] = nums[j];
//            }
//        }
//        Log.e(TAG, "总数是：" + (i+1));
//        for (int num : nums) {
//            Log.e(TAG, "输出元素：" + num);
//        }
    }

    //算法2_买卖股票，最优解问题，用贪心算法：局部考虑最优，最终达到整体的最优
    public int buyPrice(int[] prices) {//9分15
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int num = prices[i + 1] - prices[i];
            if (num > 0) {
                result += num;
            }
        }
        return result;
    }

    //算法3_旋转数组
    public void xuanzhuan(int[] nums) {//20分55
        int[] nums1 = Arrays.copyOf(nums, nums.length);
        int k = 3;
        for (int i = 0; i < nums.length; i++) {
            int index = (i + k) % nums.length;
            nums[index] = nums1[i];
        }
        for (int num : nums) {
            Log.e(TAG, "旋转数组: " + num);
        }
    }

    //算法4_存在重复元素
    public boolean containsDuplicate() {// 5分钟，时间复杂度O(nlogn),空间复杂度O(1)
        int nums[] = {1, 3, 4, 9, 8, 5, 2, 2};
//        Arrays.sort(nums);
//        for (int i = 0; i < nums.length-1; i++) {
//            if (nums[i] == nums[i + 1]) {
//                Log.e(TAG, "存在重复元素：" + true);
//                return true;
//            }
//        }
//        Log.e(TAG, "不存在重复元素：" + false);
//        return false;

        Set set = new HashSet();
        for (int num : nums) {
            if (set.contains(num)) {
                return true;
            }
            set.add(num);
        }
        return false;
    }

    //算法5_只出现一次的数字
    public int singleNumber() {
        int nums[] = {4, 1, 2, 1, 2};
//        Set<Integer> set = new HashSet<>();
//        for (int num:nums){
//            if (set.contains(num)){
//                set.remove(num);
//            }else {
//                set.add(num);
//            }
//        }
//        //获取set的第一个值
////        List list = new ArrayList(set);
////        list.get(0);
//        return set.iterator().next();

        // 位运算符，转成二进制，高位开始比较，相同为0，不同为1；
        int result = 0;
        for (int num : nums) {
            result ^= num;
        }
        return result;
    }

    //算法6: 两数组的交集intersect，双指针
    public int[] intersect() {
        int[] nums1 = {4, 1, 2, 1, 2};
        int[] nums2 = {0, 1, 1, 2, 7, 9};

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int length1 = nums1.length;
        int length2 = nums2.length;
        int[] intersect = new int[Math.min(length1, length2)];
        int i = 0;
        int j = 0;
        int index = 0;

        while (i < length1 && j < length2) {
            if (nums1[i] == nums2[j]) {
                intersect[index] = nums1[i];
                i++;
                j++;
                index++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        //必须copy去掉末尾的多余的0
        return Arrays.copyOf(intersect, index);
    }

    //算法7: 移动零
    public void moveZeroes() {
        int[] nums = {4, 1, 2, 0, 2};
        // 双指针，o(n)
        int index = 0;
        int right = 0;
        while (right < nums.length) {
            if (nums[right] == 0) {
                right++;
                continue;
            }
            nums[index] = nums[right];
            index++;
            right++;
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
        // 冒泡排序： o(n^2)耗时
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = 0; j < nums.length - 1 - i; j++) {
//                if (nums[j] == 0) {
//                    int temp = nums[j + 1];
//                    nums[j + 1] = nums[j];
//                    nums[j] = temp;
//                }
//            }
//        }
    }

    // 剑指offer
    public void offer() {

    }

    // 剑指offer  https://leetcode-cn.com/problem-list/xb9nqhhg/
    // 算法1：数组中的任意重复数字
    public int duplicate(int[] numbers) {
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < numbers.length; i++) {
            if (set.contains(numbers[i])) {
                return numbers[i];
            } else {
                set.add(numbers[i]);
            }
        }
        return -1;
    }

    // 算法2: 替换空格
    public String replaceSpace() {
        String s = "We Are Happy";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==' '){
                sb.append("%20");
            }else {
                sb.append(s.charAt(i));
            }
        }
        Log.e(TAG, "替换空格: " + sb.toString());
        return sb.toString();
    }

    // 算法3：最小的k个数 sort的时间复杂度O(nlogn)
    public int[] getLeastNumbers(int[] arr, int k) {
        Arrays.sort(arr);
        return   Arrays.copyOf(arr,k);
    }


}