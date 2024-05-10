
public class Questions {
    // leetcode 3036, 3034

    // you shiould solve 3036, can skip 345
    public int[] makeLPSArray(int[] pattern){
        int m = pattern.length;

        int j = 1;
        int len = 0;
        int[] lps = new int[m];

        lps[0] = 0;

        while(j<m){
            if(pattern[j] == pattern[len]){
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

    public int countMatchingSubarrays(int[] nums, int[] pattern) {
        int[] lps = makeLPSArray(pattern); /// copy of KMP
        int m = pattern.length;

        int res = 0;
        int i = 1;
        int j = 0;

        while(i < nums.length){
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

            if(j==m){ // matching pattern
                res++;
                j = lps[j-1];
            }
        }

        return res;
    }

    // leet 3031, 3029
    public static void main(String[] args) {
        
    }
}
