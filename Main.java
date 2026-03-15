import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

public class Main {
    public int[] twoSum(int[] nums, int target) {
        // if index of nums is not less than target, jump to next num

        int[] result = new int[2];

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int sum = nums[j] + nums[i];
                if (sum == target) {
                    result = new int[] { i, j };
                    return new int[] { i, j };
                }
            }
        }

        return result;
    }

    public boolean containsDuplicate(int[] nums) {
        HashSet<Integer> hashNums = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashNums.contains(nums[i])) {
                return true;
            }
            hashNums.add(nums[i]);
        }
        return false;
    }

    /**
     * Stream is slower than the for loop since it requires more overhead,
     * and it needs to go through the entire dataset before arriving at a judgment,
     * whereas a loop can exit the moment it reaches its target.
     * However, what is lost in speed is gained in readability and flexibility,
     * splitting work with `parallel()`
     */
    public boolean containsDuplicateStreamed(int[] nums) {
        return Arrays.stream(nums)
                .distinct()
                .count() < nums.length;
    }

    /**
     * Order matters in this exercise, so instead of trying to manhandle the
     * issue with tons of if conditions, we simply use a stack to track "Last-In,
     * First-Out" (LIFO).
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty())
                    return false;

                char top = stack.pop();

                if (c == ')' && top != '(')
                    return false;
                if (c == ']' && top != '[')
                    return false;
                if (c == '}' && top != '{')
                    return false;
            }
        }

        return stack.isEmpty();
    }

    public boolean isAnagramUnoptimized(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> sLetters = new HashMap<>();
        HashMap<Character, Integer> tLetters = new HashMap<>();

        String sLower = s.toLowerCase();
        String tLower = t.toLowerCase();

        for (char c : sLower.toCharArray()) {
            sLetters.put(c, sLetters.getOrDefault(c, 0) + 1);
        }

        for (char c : tLower.toCharArray()) {
            tLetters.put(c, tLetters.getOrDefault(c, 0) + 1);
        }

        return sLetters.equals(tLetters);
    }

    public void reverseString(char[] s) {

        for (int i = 0; i < s.length / 2; i++) {
            int currentLast = s.length - i - 1;
            char temp = s[currentLast];

            s[currentLast] = s[i];
            s[i] = temp;
        }

    }

    public void reverseStringWithWhile(char[] s) {
        int left = 0;
        int right = s.length - 1;

        while (left < right) {
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;

            left++;
            right--;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode currentNode = list1;
        ListNode currentOtherNode = list2;

        ListNode anchor = new ListNode(0);
        ListNode tail = anchor;

        while (currentNode != null && currentOtherNode != null) {
            if (currentNode.val < currentOtherNode.val) {
                tail.next = currentNode;
                currentNode = currentNode.next;
            } else {
                tail.next = currentOtherNode;
                currentOtherNode = currentOtherNode.next;
            }
            tail = tail.next;
        }

        if (currentNode != null) {
            tail.next = currentNode;
        } else {
            tail.next = currentOtherNode;
        }

        return anchor.next;
    }

    /**
     * Slower because uses memory overhead.
     */
    public boolean hasCycleWithHash(ListNode head) {
        ListNode currentNode = head;

        HashSet<ListNode> s = new HashSet<>();

        while (currentNode != null) {
            if (s.contains(currentNode)) {
                return true;
            }
            s.add(currentNode);
            currentNode = currentNode.next;

        }

        return false;
    }

    /**
     * Using tortoise and hare strategy.
     * 
     * If one is slow and other fast, and there is a loop,
     * then eventually one will "catch up" to the other one,
     * causing an identical match (in memory).
     */
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == null) {
                return false;
            }
            if (slow == fast) {
                return true;
            }

        }

        return false;
    }
}