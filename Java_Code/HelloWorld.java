import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public static void main(String[] args) {

        System.out.println("hello World");
        int[] dp = new int[5 + 1];
        int aaa=2;
    }
    @SuppressWarnings("unchecked")
    public static List<String> generateParenthesis(int n) {
        List<String>[] dp = new List[n + 1];
        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp[0] = dp0;
        for (int i = 1; i <= n; i++) {
            List<String> cur = new ArrayList<>();
            for (int m = 0; m < i; m++) {
                int k = i - 1 - m;
                List<String> str1 = dp[m];
                List<String> str2 = dp[k];
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp[i] = cur;
        }
        return dp[n];
    }
    
}
//dp[i]="("+dp[m]+")"+dp[k]
//其中m+k=i-1

