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


    public static int totalSubsets_tab(int[] arr, int N, int Tar, int[][] dp){
        for(int n=0; n<=N; n++){
            for(int tar=0; tar<=Tar; tar++){
                if(n==0){
                    dp[n][tar] = (tar == 0) ? 1 : 0;
                    continue;
                }

                int ways = 0;
                // yes call
                if(tar - arr[n-1] >= 0){
                    ways += dp[n-1][tar-arr[n-1]];  //totalSubsets_rec(arr,N-1,tar-arr[N-1]);
                }

                // no call
                ways += dp[n-1][tar]; //totalSubsets_rec(arr,N-1, tar);

                dp[n][tar] = ways;
            }
        }

        return dp[N][Tar];
    }

    public static int totalSubsets_tab(int[] arr, int N, int Tar, int[][] dp){
        for(int i=0; i<=N; i++){
            for(int j=0; j<=Tar; j++){
                if(i==0 && j==0){
                    dp[i][j] = 1;
                    continue;
                } else if(i==0){
                    dp[i][j] = 0;
                    continue;
                }

                int ways = 0;
                // yes call
                if(j - arr[i-1] >= 0){
                    ways += dp[i-1][j-arr[i-1]];  //totalSubsets_rec(arr,N-1,tar-arr[N-1]);
                }

                // no call
                ways += dp[i-1][j]; //totalSubsets_rec(arr,N-1, tar);

                dp[i][j] = ways;
            }
        }

        return dp[N][Tar];
    }


    public static int totalSubsets(int[] arr, int tar){
        int N = arr.length;
        
        int[][] dp = new int[N+1][tar+1]; // default value 0 filled
        for(int[] d: dp){
            Arrays.fill(d,-1);
        }
        return totalSubsets_memo(arr,N,tar, memo);
    }

    // Knapsack (https://www.geeksforgeeks.org/problems/0-1-knapsack-problem0945/1) ==================
    static int knapsack_rec(int idx, int[] val, int[] wt, int capacity){
        if(idx == val.length){
            return 0;
        }

        int profitAfterPicking = 0;
        if(capacity >= wt[idx]){
            profitAfterPicking = knapsack_rec(idx+1, val, wt, capacity - wt[idx]) + val[idx];
        }

        int profitAfterNotPicking = knapsack_rec(idx + 1, val, wt, capacity);

        return Math.max(profitAfterPicking, profitAfterNotPicking);
    }

    static int knapsack_mem(int idx, int[] val, int[] wt, int capacity, int[][] dp){
        if(idx == val.length){
            return 0;
        }

        if(dp[idx][capacity] != -1) return dp[idx][capacity];

        int profitAfterPicking = 0;
        if(capacity >= wt[idx]){
            profitAfterPicking = knapsack_mem(idx+1, val, wt, capacity - wt[idx], dp) + val[idx];
        }

        int profitAfterNotPicking = knapsack_mem(idx + 1, val, wt, capacity, dp);

        return dp[idx][capacity] = Math.max(profitAfterPicking, profitAfterNotPicking);
    }

    // moving from n to 0
    static int knapsack_mem2(int n, int[] val, int[] wt, int capacity, int[][] dp){
        if(n==0){
            return dp[n][capacity] = 0;
        }
        if(capacity == 0){
            return dp[n][capacity] = 0;
        }

        if(dp[n][capacity] != -1) return dp[n][capacity];

        int profitAfterPicking = 0;
        if(capacity >= wt[n-1]){
            profitAfterPicking = knapsack_mem2(n-1, val, wt, capacity - wt[n-1]) + val[n-1];
        }

        int profitAfterNotPicking = knapsack_mem2(n-1, val, wt, capacity);

        return dp[n][capacity] = Math.max(profitAfterPicking, profitAfterNotPicking);
    }

    static int knapsack_tab(int N, int[] val, int[] wt, int total_capacity, int[][] dp){
        for(int n=0; n<=N; n++){
            for(int capacity = 0; capacity <= total_capacity; capacity++){
                if(n==0){
                    dp[n][capacity] = 0;
                    continue;
                }
                if(capacity == 0){
                    dp[n][capacity] = 0;
                    continue;
                }

                int profitAfterPicking = 0;
                if(capacity >= wt[n-1]){
                    profitAfterPicking = dp[n-1][capacity-wt[n-1]]; + val[n-1]; //knapsack_mem2(n-1, val, wt, capacity - wt[n-1]) + val[n-1];
                }

                int profitAfterNotPicking = dp[n-1][capacity]; //knapsack_mem2(n-1, val, wt, capacity);

                dp[n][capacity] = Math.max(profitAfterPicking, profitAfterNotPicking);
            }
        }

        return dp[N][total_capacity];
    }




    static int knapsack(int W, int val[], int wt[]) {
        int n = val.length;
        int[][] dp = new int[n+1][W+1];

        for(int[] d:dp){
            Arrays.fill(d, -1);
        }

        // return knapsack_rec(0,val,wt,W);
        // return knapsack_mem(0,val,wt,W, dp);
        // return knapsack_mem2(n,val,wt,W,dp);
        return knapsack_tab(n,val,wt,W,dp);
    }

    // Homework problems
    // 1. Knapsack but you can pick same item again and again.
    // 2. https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
    // 3. https://www.geeksforgeeks.org/problems/friends-pairing-problem5425/1
    // 4. https://leetcode.com/problems/decode-ways/description/
    // 5. https://leetcode.com/problems/partition-equal-subset-sum/description/



























    public static void main(String[] args){

    }
}