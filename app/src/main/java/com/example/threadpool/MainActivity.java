package com.example.threadpool;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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

    // 算法3：插入排序（打扑克类似）还有优化插入排序，如二分插入排序，链表插入排序，希尔排序
    // 原理：每次将数组最后一个元素作为插入元素，与它前面有序（已排好序）的数组元素进行比较后，插入正确的位置，排序完成。
    private static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {// i: 代表即将插入的元素角标,作为每一组比较数据的最后一个元素
            for (int j = i; j > 0; j--) {   //j:代表数组角标
                if (arr[j] < arr[j - 1]) {//符合条件，插入元素（交换位置）
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
            }
        }
        return arr;
    }

    // 算法4：快速排序，平均时间复杂度为O(nlogn)，空间复杂度为O(logn)
    // 递归算法，有点复杂
    // 算法思想：左侧初始值为基准元素，分别找出右侧小的值和左侧大的值进行交换，直到找到剩一个值和基准元素交换，然后分别递归下去，直到排序完成
    // https://blog.csdn.net/wthfeng/article/details/78037228
    public void quickSort(int[] arr, int low, int high) {
        // low,high 为每次处理数组时的首、尾元素索引

        //当low==high是表示该序列只有一个元素，不必排序了
        if (low >= high) {
            return;
        }
        // 选出哨兵元素和基准元素。这里左边的哨兵元素也是基准元素
        int i = low, j = high, base = arr[low];
        while (i < j) {
            //右边哨兵从后向前找
            while (arr[j] >= base && i < j) {
                j--;
            }
            //左边哨兵从前向后找
            while (arr[i] <= base && i < j) {
                i++;
            }
            swap(arr, i, j);  //交换元素
        }
        swap(arr, low, j);  //基准元素与右哨兵交换

        //递归调用，排序左子集合和右子集合
        quickSort(arr, low, j - 1);
        quickSort(arr, j + 1, high);

    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    // 算法5_1：二分查找法 (前提是有序) 非递归
    public static int binarySearchNoRecur(int[] arr, int findVal) {

        int left = 0;
        int right = arr.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == findVal) {
                return mid;
            } else if (arr[mid] > findVal) {
                // 向左边查找
                right = mid - 1;
            } else {
                // 向右边查找
                left = mid + 1;
            }
        }
        return -1;
    }

    // // 算法5_2：二分查找法 (前提是有序) 递归方法
    public static int binarySearch(int[] arr, int left, int right, int findVal) {

        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;

        if (arr[mid] == findVal) {
            return mid;
        } else if (arr[mid] > findVal) {
            // 向左边查找
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            // 向右边查找
            return binarySearch(arr, mid + 1, right, findVal);
        }
    }

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
            if (s.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(s.charAt(i));
            }
        }
        Log.e(TAG, "替换空格: " + sb.toString());
        return sb.toString();
    }

    // 算法3：最小的k个数 sort的时间复杂度O(nlogn)
    public int[] getLeastNumbers(int[] arr, int k) {
        Handler handler = new Handler();
        Message message = Message.obtain();
        message.what = 1;
        handler.sendMessage(message);
        Arrays.sort(arr);
        return Arrays.copyOf(arr, k);
    }

    // 链表
    /**
     * 给定一个单链表的头结点pHead(该头节点是有值的，比如在下图，它的val是1)，长度为n，
     * 反转该链表后，返回新链表的表头。
     */
    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
    public ListNode ReverseList(ListNode head) {
        ListNode newHead = null;
        ListNode cur = head;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = newHead;
            newHead = cur;
            cur = temp;
        }
        return newHead;
    }

    /**
     * 输入一个链表的头节点，按链表从尾到头的顺序返回每个节点的值（用数组返回）。
     * 如输入{1,2,3}
     * 返回一个数组为[3,2,1]
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode newhead = null;
        ListNode cur = listNode;
        while(cur != null){
            ListNode temp = cur.next;
            cur.next = newhead;
            newhead = cur;
            cur = temp;
        }
        ArrayList<Integer> list = new ArrayList();
        while(newhead != null){
            list.add(newhead.val);
            newhead = newhead.next;
        }
        return list;
    }
    // 使用栈的方式，同时返回int数组
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack=new Stack<>();
        while(head != null){
            stack.push(head.val);
            head=head.next;
        }
        int[] num = new int[stack.size()];
        int k = 0;
        while(!stack.isEmpty()){
            num[k] = stack.pop();
            k++;
        }
        return num;
    }

    /**
     * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的。
     * 注：返回整个链表的时候，需要用到辅助头结点
     * 输入：{1,3,5},{2,4,6}
     * 返回值：{1,2,3,4,5,6}
     */
    public ListNode Merge(ListNode list1,ListNode list2) {
        if(list1 == null){
            return list2;
        }
        if(list2 == null){
            return list1;
        }
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while(list1!=null&&list2!=null){
            if(list1.val<=list2.val){
                // 插入小的
                cur.next = list1;
                // 当前插完向后移动
                list1 = list1.next;
            }else{
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        if(list1!=null){
            cur.next = list1;
        }
        if(list2!=null){
            cur.next = list2;
        }
        return head.next;
    }

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        // fast走的快
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow){
                return true;
            }
        }
        return false;
    }

    /**
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     */
    // 快慢指针
    public ListNode detectCycle1(ListNode head) {
        // 快慢指针
        ListNode slow = head;
        ListNode fast = head;
        // fast走的快
        while(fast!=null&&fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow){
                fast = head;
                while (fast!=slow){
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
    // set集合
    public ListNode detectCycle2(ListNode head) {
        ListNode node = head;
        Set<ListNode> set = new HashSet<ListNode>();
        while (node != null) {
            if (set.contains(node)) {
                return node;
            } else {
                set.add(node);
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 输入两个无环的单向链表，找出它们的第一个公共结点，如果没有公共节点则返回空。
     * 使用的set方式
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        Set<ListNode> set = new HashSet<>();
        ListNode head = pHead1;
        while(head!=null){
            set.add(head);
            head = head.next;
        }
        ListNode head2 = pHead2;
        while(head2!=null){
            if(set.contains(head2)){
                return head2;
            }else{
                head2 = head2.next;
            }
        }
        return null;
    }

    /**
     * 输入一个长度为 n 的链表，设链表中的元素的值为 ai ，返回该链表中倒数第k个节点。
     *
     *
     * @param pHead ListNode类
     * @param k int整型
     * @return ListNode类
     */
    public ListNode FindKthToTail (ListNode pHead, int k) {
        ListNode p1 = pHead;
        // 保留p1，并计算k是否超过链表长度
        while(p1 != null && k > 0) {
            p1 = p1.next;
            k--;
        }
        if(k > 0) {
            return null;
        }
        ListNode p2 = pHead;
        while(p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点。
     * 输入：{2,5,1,9},5
     * 返回值：{2,1,9}
     */
    public ListNode deleteNode (ListNode head, int val) {
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode pre = res;
        ListNode cur = head;
        while(cur!=null){
            if(cur.val == val){
                pre.next = cur.next;
                break;
            }
            pre = cur;
            cur = cur.next;
        }
        return res.next;
    }

    /**
     * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
     * 例如，链表 1->2->3->3->4->4->5  处理后为 1->2->5
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead) {
        //空链表
        if(pHead == null)
            return null;
        ListNode res = new ListNode(0);
        //在链表前加一个表头
        res.next = pHead;
        ListNode cur = res;
        while(cur.next != null && cur.next.next != null){
            //遇到相邻两个节点值相同
            if(cur.next.val == cur.next.next.val){
                int temp = cur.next.val;
                //将所有相同的都跳过
                while (cur.next != null && cur.next.val == temp)
                    cur.next = cur.next.next;
            }
            else
                cur = cur.next;
        }
        //返回时去掉表头
        return res.next;
    }



}