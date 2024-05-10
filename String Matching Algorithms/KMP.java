public class KMP {
    public static int[] makeLPSArray(String patt, int m){
        int[] lps = new int[m];

        int j = 1;
        lps[0] = 0;

        int len = 0;
        while(j<m){
            if(patt.charAt(j) == patt.charAt(len)){
                len++;
                lps[j] = len;
                j++;
            } else {
                if(len == 0){
                    j++;
                } else {
                    len = lps[len-1]; // this is actually saving our time
                }
            }
        }

        return lps;
    }

    public static void findPattern(String str, String patt){
        int n = str.length();
        int m = patt.length();

        int[] lps = makeLPSArray(patt,m);

        int i = 0;
        int j = 0;

        while(i<n){
            if(str.charAt(i) == patt.charAt(j)){
                i++;
                j++;
            } else {
                if( j == 0){
                    i++;
                } else { // i is waiting
                    j = lps[j-1];
                }
            }

            if(j==m){
                System.out.println("Pattern Matched at index " + (i-m));
                j = lps[j-1]; // to find another match
            }
        }
    }
    public static void main(String[] args) {
        String str = "acababdabababcaba";
        String patt = "ababcaba";

        findPattern(str,patt);
    }
}
