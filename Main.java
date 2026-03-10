import java.util.Arrays;
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
}