public class SubarraySum {

    /**
     * Time Complexity: O(n) - it processes each element at most twice
     * Space Complexity: O(1) - it only uses a constant amount of extra space
     */
    public static int[] sumTarget(int[] A, int K) {
        if (A == null || A.length == 0) {
            return new int[]{-1, -1};
        }

        int left = 0;
        int right = 0;
        int currentSum = 0;

        while (right < A.length) {
            currentSum += A[right];

            while (currentSum > K && left <= right) {
                currentSum -= A[left];
                left++;
            }

            if (currentSum == K) {
                return new int[]{left, right};
            }

            right++;
        }

        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[][] testArrays = {
                {1, 2, 3, 7, 5},
                {1, 2, 3, 7, 5},
                {1, 2, 3, 7, 5},
                {1, 2, 3, 7, 5},
                {1, 1, 1, 1, 1},
                {2, 4, 6, 8, 10},
                {}
        };

        int[] testTargets = {12, 5, 7, 11, 3, 10, 5};

        for (int i = 0; i < testArrays.length; i++) {
            int[] result = sumTarget(testArrays[i], testTargets[i]);

            System.out.println("Array: " + arrayToString(testArrays[i]));
            System.out.println("Target: " + testTargets[i]);

            if (result[0] == -1) {
                System.out.println("Result: No subarray found");
            } else {
                System.out.println("Result: Subarray from index " + result[0] + " to " + result[1]);

                int sum = 0;
                for (int j = result[0]; j <= result[1]; j++) {
                    sum += testArrays[i][j];
                }
                System.out.println("Sum of subarray: " + sum);
            }
            System.out.println();
        }
    }

    private static String arrayToString(int[] arr) {
        if (arr.length == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}