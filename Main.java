public class Main {
    public static boolean splitArraySameAverage(int[] A) {
        int n = A.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
        }

        // dp[j][k] represents whether it is possible to form a sum of j using k elements from A
        boolean[][] dp = new boolean[sum+1][n+1];
        dp[0][0] = true; //  It is possible to form a sum of 0 using 0 elements from A

        // Compute dp for all values
        for (int i = 0; i < n; i++) {
            for (int j = sum; j >= A[i]; j--) {
                for (int k = 1; k <= n; k++) {
                    dp[j][k] = dp[j][k] || dp[j-A[i]][k-1];
                    // dp[j][k] can be true if either dp[j][k] is already true (we don't include A[i]) or dp[j-A[i]][k-1] is true (we include A[i] as the k element)
                }
            }
        }

        // Check whether it is possible to split A into two non-empty lists B and C with the same average
        for (int k = 1; k < n; k++) {
            if (sum*k % n == 0) { // The sum of elements in B must be divisible by n
                int target = sum*k/n;
                if (dp[target][k]) { // If it is possible to form a sum of target using k elements from A
                    return true;
                }
            }
        }
        return false;
    }



    public static void main(String[] args) {
        int[] A = {1,2,3,4,5,6,7,8};
        System.out.println(splitArraySameAverage(A) + " ");
    }
}