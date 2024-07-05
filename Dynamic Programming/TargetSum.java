class TargetSum {

    // O(2^n)
    // -1 -> no answer, 0 -> false, 1 = true
    public static int targetSumSubsetRec(int N, int target, int[] arr, int[][] dp){
        if(N==0){
            if(target == 0){
                return dp[N][target] = 1;
            } else {
                return dp[N][target] = 0;
            }
        }

        if(dp[N][target] != -1) return dp[N][target];

        int ans = 0;

        // yes call
        if(target - arr[N-1] >= 0){
            ans = targetSumSubsetRec(N-1, target - arr[N-1], arr, dp);
        }

        // no call
        if(ans == 0)
            ans =  targetSumSubsetRec(N-1, target, arr, dp);

        return dp[N][target] = ans;
    }

    public static Boolean isSubsetSum(int N, int arr[], int target){
        int[][] dp = new int[N+1][target+1];

        for(int[] d: dp){
            Arrays.fill(d, -1);
        }

        return targetSumSubsetRec(N,target,arr, dp) == 1;
    }


    // number of subsets with sum == target
    public static int totalSubsets_rec(int[] arr, int N, int tar){
        if(N==0){
            return tar == 0 ? 1 : 0;
        }

        int ways = 0;
        // yes call
        if(tar - arr[N-1] >= 0){
            ways += totalSubsets_rec(arr,N-1,tar-arr[N-1]);
        }

        // no call
        ways += totalSubsets_rec(arr,N-1, tar);

        return ways;
    }

    public static int totalSubsets_memo(int[] arr, int N, int tar, int[][] dp){
        if(N==0){
            return dp[N][tar] = (tar == 0) ? 1 : 0;
        }

        if(dp[N][tar] != -1) return dp[N][tar];

        int ways = 0;
        // yes call
        if(tar - arr[N-1] >= 0){
            ways += totalSubsets_rec(arr,N-1,tar-arr[N-1]);
        }

        // no call
        ways += totalSubsets_rec(arr,N-1, tar);

        return dp[N][tar] = ways;
    }

    public static int totalSubsets(int[] arr, int tar){
        int N = arr.length;
        
        int[][] dp = new int[N+1][tar+1]; // default value 0 filled
        for(int[] d: dp){
            Arrays.fill(d,-1);
        }
        return totalSubsets_memo(arr,N,tar, memo);
    }

    public static void main(String[] args){

    }
}