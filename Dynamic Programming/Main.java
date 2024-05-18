public class Main {

    public static int fib(int n){
        if(n <= 1){
            return n;
        }

        int lastTerm = fib(n-1);
        int secondLastTerm = fib(n-2);

        int nthTerm = lastTerm + secondLastTerm;
        return nthTerm;
    }

    public static int fib_memo(int n, int[] memo){
        if(n <= 1){
		    return memo[n] = n;
	    }

        if(memo[n] != -1){
            return memo[n];
        }

        int lastTerm = fib_memo(n-1, memo);
        int secondLastTerm = fib_memo(n-2, memo);

        int nthTerm = lastTerm + secondLastTerm;

        // memo[n] = nthTerm;
        return memo[n] = nthTerm;
    }

    public static int fib_Tab(int N, int[] memo){
        for(int n=0; n<=N; n++){
            if(n <= 1){
                memo[n] = n;
                continue;
            }

            int lastTerm = memo[n-1]; //fib_memo(n-1, memo);
            int secondLastTerm = memo[n-2]; //fib_memo(n-2, memo);

            int nthTerm = lastTerm + secondLastTerm;

            memo[n] = nthTerm;
        }

        return memo[N];
    }

    public int rec(int[] nums, int target){
        if(target == 0){
            return 1;
        }

        int totalWays = 0;
        for(int i=0; i<nums.length; i++){
            if(target - nums[i] >= 0){
                totalWays += rec(nums, target - nums[i]);
            }
        }

        return totalWays;
    }

    public int rec_memo(int[] nums, int target, int[] dp){
        if(target == 0){
            return dp[target] = 1;
        }

        if(dp[target] != -1){
            return dp[target];
        }

        int totalWays = 0;
        for(int i=0; i<nums.length; i++){
            if(target - nums[i] >= 0){
                totalWays += rec_memo(nums, target - nums[i], dp);
            }
        }

        return dp[target] = totalWays;
    }


    public int rec_tab(int[] nums, int Target, int[] dp){
       for(int target=0; target<=Target; target++){
            if(target == 0){
                dp[target] = 1;
                continue;
            }

            int totalWays = 0;
            for(int i=0; i<nums.length; i++){
                int curr_coin = nums[i];
                if(target - curr_coin >= 0){
                    totalWays += dp[target-curr_coin]; //rec_memo(nums, target - nums[i], dp);
                }
            }

            dp[target] = totalWays; 
       }

       return dp[Target];
    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target+1];
        Arrays.fill(dp,-1);

        return rec_tab(nums,target,dp);    
    }

    public static void main(String[] args) {
        int n = 6;
        int[] memo = new int[n+1];
        Arrays.fill(memo, -1);

        System.out.println(fib_memo(n, memo));
    }
}
