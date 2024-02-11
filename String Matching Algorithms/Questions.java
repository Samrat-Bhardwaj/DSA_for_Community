
public class Questions {
    public static int[] makeLPSArray(int[] patt, int m){
        int[] lps = new int[m];

        int j = 1;
        lps[0] = 0;

        int len = 0;
        while(j<m){
            if(patt[j] == patt[len]){
                len++;
                lps[j] = len;
                j++;
            } else {
                if(len == 0){
                    j++;
                } else {
                    len = lps[len-1];
                }
            }
        }

        return lps;
    }

    // leetcode 3036, 3034
    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int m = pattern.length;

        int[] lps = makeLPSArray(pattern, m);

        int i=1;
        int j=0;
        int ans = 0;

        while(i<nums.length){
            int currChar = -2;
            if(nums[i] > nums[i-1]){
                currChar = 1;
            } else if(nums[i] == nums[i-1]){
                currChar = 0;
            } else {
                currChar = -1;
            }

            if(currChar == pattern[j]){
                i++;
                j++;
            } else {
                if(j==0){
                    i++;
                } else {
                    j = lps[j-1];
                }
            }

            if(j==m){
                ans++;
                j=lps[j-1];
            }
        }

        return ans;
    }

    // leet 3031, 3029
    public static void main(String[] args) {
        
    }
}
