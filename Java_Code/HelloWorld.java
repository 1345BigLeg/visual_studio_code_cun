
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class HelloWorld {
  
    public static void main(String[] args) {
        System.out.println("hello World");
        int[] dp = new int[5 + 1];
        String a="aba";
        String b= new StringBuffer(a).reverse().toString();
        if (a.equals(b))
        System.out.println(a.length());
        String test="smiles".substring(1, 5) ;
        String str = "我爱Java 编程";
        String result = str.substring(3);
        int aaa=2;
        List<List<Integer>> resultw = new ArrayList<>();
        List<Integer> tt = new ArrayList<>();
        
          tt.add(2);
          resultw.add(tt);
          System.out.println(resultw);
          tt.add(8);
          System.out.println(resultw);
          tt.clear();
          System.out.println(resultw);
          int aa=2;

    }
    public class ListNode {
             int val;
             ListNode next;
             ListNode(int x) { val = x; }
         }
    public int[] reversePrint(ListNode head) 
    {
            if (head==null)
            return new int [0];
            Stack<Integer>res=new Stack<>();
            ListNode temp=head;
            while (temp!=null)
            {
               res.push(temp.val);
               temp=temp.next;
            }
            int[] result=new int[res.size()];
            int index=0;
            while(!res.empty())
            {
                int a=res.peek();
                result[index++]=a;
                res.pop();
            }
            return result;
    }
    public int[] intersection(int[] nums1, int[] nums2) //349  给定两个数组，编写一个函数来计算它们的交集
    {
    {
        Set<Integer>ss=new HashSet<>();
        Set<Integer>tt=new HashSet<>();
        for (int i=0;i<nums1.length;i++)
        {
            ss.add(nums1[i]);
        }
        for (int i=0;i<nums2.length;i++)
        {
            tt.add(nums2[i]);
        }
        Set<Integer>res=new HashSet<>();
        for (var a:ss)
        {
            if (tt.contains(a))
            res.add(a);
        }
        int[] result=new int [res.size()];
        int index=0;
         for (var b:res)
         {
             result[index++]=b;
         }
         return result;
    }
    public int lengthOfLongestSubstring(String s) 
    {
        if (s.length()==0)
         return 0;
         if (s==" ")
         return 1;
       Set<Character> win = new HashSet<>();
       int result = 0;
	int next = 0;
	for (int i = 0; i < s.length(); i++)
	{
		while (win.contains(s.charAt(i)))
		{
			win.remove(s.charAt(next));
			next++;
		}
		win.add(s.charAt(i));
		result = Math.max(result,i-next+1);
	}
	return result;
    }
    public int removeDuplicates(int[] nums) {
        if (nums == null)
            return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if (nums[i] == nums[j])
                continue;
            else
                nums[++i] = nums[j];
        }
        return i + 1;

    }

    int result=0;
    boolean huiwen(String s)
    {
        String tem=new StringBuffer(s).reverse().toString();
        if (s.equals(tem))
          return true;
        else 
          return false;

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


