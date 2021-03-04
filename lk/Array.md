 >1  **例题1   数组中重复的数字**  

* 题目描述：在一个长度为 n 的数组里的所有数字都在 0 到 n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字是重复的，也不知道每个数字重复几次。请找出数组中任意一个重复的数字。
 **解题思路**：把0,1,2……n-1依次放入s[0],s[1]……s[n-1]中，如果s[i]!=i,且 s[s[i]]=s[i],则为重复项  

 * 题目链接 ：https://cyc2018.github.io/CS-Notes/#/notes/%E5%89%91%E6%8C%87%20Offer%20%E9%A2%98%E8%A7%A3%20-%203~9?id=_3-%e6%95%b0%e7%bb%84%e4%b8%ad%e9%87%8d%e5%a4%8d%e7%9a%84%e6%95%b0%e5%ad%97
* 代码示例： 20200904
```C++
          /*方法1*/
   int Find_In_Array(vector<int> s)
{
	int length = s.size();
	int duplicate;
	int temp;s
	if (length == 0)
	{
		return -1;
	}
	for (int i = 0; i < length; i++)
	{
		if (s[i] < 0 || s[i] >= length)
			return -1;
	}
	for (int i = 0; i < length; i++)
	{
		while (s[i]!=i)
		{
			if (s[i] == s[s[i]])
			{
				duplicate = s[i];
				return duplicate;
			}
			temp = s[s[i]];
			s[s[i]] = s[i];
			s[i] = temp;
			

		}
	}
	return  -1;
}

                  /*方法2 参考了例题8*/
				  int findRepeatNumber(vector<int>& nums)
{
	int size = nums.size();
	if (size == 0)
		return -1;
	for (int i = 0; i < size; i++)
	{
		if (nums[i] < 0 || nums[i] >= size)
			return -1;
	}
	for (int i=0;i<size;i++)
	{   
		nums[i] += 1;
	}
	for (int i = 0; i < size; i++)
	{
		int newIndex = abs(nums[i])-1;
		if (nums[newIndex] < 0)
			return abs(nums[i])-1;
		nums[newIndex] *= -1;
	}
	return -1;


	          /* 方法三 set集合或unordered_map*/
			  unordered_map<int, int>pp;
	for (int i = 0; i < nums.size(); i++)
	{
		if (++pp[nums[i]] > 1)
			return nums[i];
	}
	return -1;
}
```
>2  **例题2   只出现一次的数字**
* 题目描述：给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素，不使用额外空间来实现。https://leetcode-cn.com/p
lems/single-number/
* 解题思路：  
  任何数和 0 做异或运算，结果仍然是原来的数，即 a⊕0=a。  

  任何数和其自身做异或运算，结果是 0，即 a⊕a=0。  

  异或运算满足交换律和结合律，即a⊕b⊕a=b⊕a⊕a=b⊕(a⊕a)=b⊕0=b
* 代码示例：
``` C++
class Solution {
public:
    int singleNumber(vector<int>& nums) {

       int result=0;
       for (auto num:nums)
    {   
        result^=num;
  
    }
     return result;
    }
};
```

>3 **例题3   例题2的扩展**
* 题目描述：一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
* 解题思路：把所有数字分成两组，使得两个只出现一次的数字在不同的组中；相同的数字会被分到相同的组中。对两个组分别进行异或操作。
* 代码示例：
```C++
class Solution {
public:
    vector<int> singleNumbers(vector<int>& nums) {
        int ret = 0;
        for (int n : nums)
            ret ^= n;
        int div = 1;
        while ((div & ret) == 0)
            div <<= 1;
        int a = 0, b = 0;
        for (int n : nums)
            if (div & n)
                a ^= n;
            else
                b ^= n;
        return vector<int>{a, b};
    }
};
```
>4 **例题4**  两数之和
* 题目描述：给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍 https://leetcode-cn.com/problems/two-sum/
* **学习点1**：哈希表  (也可以用暴力解法)
``` C++ 20201013
 vector<int> twoSum(vector<int>& nums, int target) {
          
        unordered_map<int,int>hs;
        for (int i=0;i<nums.size();i++)
        {
              auto it=hs.find(target-nums[i]);
              if (it!=hs.end())
              {
                 return {it->second,i};
                 break;
              }
              hs[nums[i]]=i;

        }
        return {};
    }
```
>5 **例题5** 有效的括号
* 题目描述：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
有效字符串需满足：左括号必须用相同类型的右括号闭合；左括号必须以正确的顺序闭合。https://leetcode-cn.com/problems/valid-parentheses/  

>>**学习点1**：使用栈 先进后出
```C++ 20201016
if (s.empty())
		return true;
	stack<char>path;
	char temp;
	char top;
	if (s.size() % 2 != 0)
		return false;
	for (int i = 0; i < s.size(); i++)
	{
		temp = s[i];
		if (temp == '{' || temp == '[' || temp == '(')
		{
			path.push(s[i]);
			continue;
		}
		if (path.size() == 0)
			return false;
		if (path.top() == '('&&temp == ')' || path.top() == '['&&temp == ']' || path.top() == '{'&&temp == '}')
		{
			path.pop();
		}
		else
			return false;

	}
	if (path.size() == 0)
		return true;
	else
		return false;
```
>6 **例题6** 最大子序和
* 题目描述：给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。https://leetcode-cn.com/problems/maximum-subarray/  

>>**学习点1**：动态规划法
```C++ 20201015
    /*动态规划算法*/
	vector<int>dp(nums.size());
	int result = nums[0];
	dp[0] = nums[0];
	for (int i = 1; i < nums.size(); i++)
	{
		dp[i] = (dp[i - 1] + nums[i])>nums[i]? (dp[i - 1] + nums[i]) :nums[i];
		result = result > dp[i] ? result : dp[i];
	}
	return result;

    /*答案的暴力解法*/
	int max = INT_MIN;
	int numsSize = int(nums.size());
	for (int i = 0; i < numsSize; i++)
	{
		int sum = 0;
		for (int j = i; j < numsSize; j++)
		{
			sum += nums[j];
			if (sum > max)
			{
				max = sum;
			}
		}
	}
	return max;
```
>6 **例题7** 多数元素
* 问题描述：给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。你可以假设数组是非空的，并且给定的数组总是存在多数元素。https://leetcode-cn.com/problems/majority-element/ 
>>**学习点1**：我自己写的方法超时了
``` c++ 20201021 
/*方法1 排序法*/
int majorityElement(vector<int>& nums) 
{
         sort(nums.begin(), nums.end());
        return nums[nums.size() / 2];  
    }

	/*方法2：哈希法*/

	int majorityElement(vector<int>& nums) 
	{
       unordered_map<int, int> record;//元素->频率
	for (int i = 0; i < nums.size(); ++i)
	{
		record[nums[i]]++;
		if (record[nums[i]] > nums.size() / 2)
			return nums[i];
	}
	return -1;
    }
```
>7 **例题8** 移动0
* 问题描述：给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序 https://leetcode-cn.com/problems/move-zeroes/
>>**学习点1**：双指针法
``` C++ 20201023
			/*方法一*/
	int t=0;
	for (int i = 0; i < nums.size(); i++)
	{
		if (nums[i] != 0)
			nums[t++] = nums[i];
	}
	for (int i = t; i < nums.size(); i++)
	{
		nums[t] = 0;
	}

			/*方法二 双指针法*/
	for (int i = 0, lastnonzeroAT = 0; i < nums.size(); i++)
	{
		if (nums[i != 0])
			swap(nums[lastnonzeroAT++],nums[i]);
	}
```
>8 **例题8** 找到所有数组中消失的数字 https://leetcode-cn.com/problems/find-all-numbers-disappeared-in-an-array/
* 问题描述：给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的 整型数组，数组中的元素一些出现了两次，另一些只出现一次。找到所有在 [1, n] 范围之间没有出现在数组中的数字
>>**学习点1**：使用 set
>>**学习点2**：原地修改数组，把nums[i]当做新的索引
``` C++ 20201023
         /*方法1 使用集合set*/
vector<int>result;
	set<int>a;
	for (int i = 0; i < nums.size(); i++)
	{
		a.insert(nums[i]);
	}
	for (int i = 1; i <= nums.size(); i++)
	{
		if (a.count(i) == 0)
			result.emplace_back(i);
	}
	return result;


	/*方法二 原地修改*/
	vector<int>result2;
	for (int i = 0; i < nums.size(); i++)
	{
		 // nums[abs(nums[i]) - 1] = abs(nums[  abs(nums[i]) - 1    ]) * -1; 也可

		 // Treat the value as the new index
		int newIndex = abs(nums[i]) - 1;
		if (nums[newIndex] > 0) 
		{
			nums[newIndex] *= -1;
		}

	}
	for (int i = 0; i < nums.size(); i++)
	{
		if (nums[i] > 0)
			result2.emplace_back(i+1);
	}
	return result2;
```
>9 **例题9** 寻找重复数
* 问题描述：给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数 https://leetcode-cn.com/problems/find-the-duplicate-number/
>>**学习点1**：使用 set
>>**学习点2**：快慢指针，相当于寻找环形链表的交点
``` C++ 20201027
 		/*方法1 使用unordered_set*/
        int findDuplicate(vector<int>& nums) 
	{
     unordered_set<int>re;
     for (int i=0;i<nums.size();i++)
     {
         if (re.count(nums[i])==1)
         return nums[i];
         re.insert(nums[i]);
     }
     return 0;
    }

		 /*方法2 快慢指针*/
		 int findDuplicate(vector<int>& nums)
{
	
	int fast = 0, slow = 0;
	while (true) {
		fast = nums[nums[fast]];
		slow = nums[slow];
		if (fast == slow)
			break;
	}
	int finder = 0;
	while (true) {
		finder = nums[finder];
		slow = nums[slow];
		if (slow == finder)
			break;
	}
	return slow;
}
```
>10 **例题十** 最小路径和
* 问题描述：给定一个包含非负整数的 m x n 网格，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。每次只能向下或者向右移动一步。  
https://leetcode-cn.com/problems/minimum-path-sum/
>>**学习点1**：动态规划
``` C++ 20201029
int minPathSum(vector<vector<int>>& grid)
{
	int row = grid.size();
	int col = grid[0].size();
	vector<vector<int>>dp(row,vector<int>(col,0));
	dp[0][0] = grid[0][0];
	for (int i = 1; i < col; i++)
	{
		dp[0][i] = dp[0][i-1]+grid[0][i];
	}
	for (int i = 1; i < row; i++)
	{
		dp[i][0] = dp[i-1][0]+grid[i][0];
	}
	for (int i = 1; i < row; i++)
	{
		for (int j = 1; j < col; j++)
		{
			dp[i][j] = min(dp[i][j - 1] + grid[i][j], dp[i - 1][j] + grid[i][j]);
		}
	}
	return dp[row-1][col-1];
}
```
>11 **例题十一**除自身以外数组的乘机
* 问题描述：给你一个长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
https://leetcode-cn.com/problems/product-of-array-except-self/
>>**学习点1**：左右累乘列表，遍历两遍数组
 ``` C++ 20201030 
vector<int> productExceptSelf(vector<int>& nums)
{
	vector<int>result(nums.size());
	int L = 1;
	for (int i = 0; i < nums.size(); i++)
	{
		result[i] = L;
		L *= nums[i];
	}
	int R = 1;
	for (int i = nums.size() - 1; i >= 0; i--)
	{
		result[i] *= R;
		R *= nums[i];
	}
	return result;
}
```
>12 **例题12**  前 K 个高频元素
* 问题描述：给定一个非空的整数数组，返回其中出现频率前 k 高的元素。 https://leetcode-cn.com/problems/top-k-frequent-elements/
>>**学习点1**：把哈希放到vector就可以用sort（）自定义排序
>>**学习点2**：优先列表 priority_queue
``` C++ 20201031
                   /*方法1*/
vector<int> topKFrequent(vector<int>& nums, int k)
{
	        
	unordered_map<int, int>temp;
	for (int i = 0; i < nums.size(); i++)
	{
		temp[nums[i]]++;
	}
	
	vector<int>result;
	vector<pair<int, int>>aa(temp.begin(),temp.end());
	sort(aa.begin(), aa.end(), [](pair<int, int>c, pair<int, int>d) {return c.second > d.second; });
	for (int i = 0; i < k; i++) 
	{
		result.emplace_back(aa[i].first);
	}
	return result;
}
                  /*方法2*/
vector<int> topKFrequent(vector<int>& nums, int k)
{
	vector<int> ret;
	unordered_map<int, int> mp;
	priority_queue<pair<int, int>> pq;
	for (auto i : nums) mp[i]++;
	for (auto p : mp) {
		pq.push(pair<int, int>(-p.second, p.first));
		if (pq.size() > k) pq.pop();
	}
	while (k--) {
		ret.push_back(pq.top().second);
		pq.pop();
	}
	return ret;
	
}
```
>13 **例题13** 打家劫舍
* 问题描述：https://leetcode-cn.com/problems/house-robber/
>>**学习点1**：动态规划
``` C++ 20201103
int rob(vector<int>& nums)      //打家劫舍--动态规划
{
	//int length = nums.size();
	//if (length == 0)
	//	return 0;
	//else if (length == 1)
	//	return nums[0];
	//else
	//{
	//	vector<int>dp(nums.size());
	//	dp[0] = nums[0];
	//	dp[1] = max(nums[0], nums[1]);
	//	for (int i = 2; i < nums.size(); i++)
	//	{
	//		dp[i] = max(dp[i - 1], dp[i - 2] + nums[i]);
	//	}
	//	return dp[nums.size() - 1];
	//}

	int length = nums.size(); //动态规划 --空间优化
	if (length == 0)
		return 0;
	if (length == 1)
		return nums[0];
	int first, second;
	vector<int>dp(nums.size());
	first = nums[0];
	second = max(nums[0], nums[1]);
	for (int i = 2; i < nums.size(); i++)
	{
		int temp = second;
		second = max(second, first + nums[i]);
		first = temp;
	}
	return second;
}
```
>14 **例题14** 二叉树的层序遍历
* 问题描述：给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。  
https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
>>**学习点1**：广度优先BFS，BFS经典使用场景：层序遍历、最短路路径问题
>>**学习点2**：BFS使用**队列**数据结构
``` C++20201107
vector<int>temp;
vector<vector<int>>result;
vector<vector<int>> levelOrder(TreeNode* root)
{
	if (root == nullptr)
		return {};
	queue<TreeNode*>qu;
	qu.push(root);
	while (!qu.empty())
	{
		int size = qu.size();
		for (int i = 1; i <= size; i++)
		{
			auto it = qu.front();
			temp.push_back(it->val);
			qu.pop();
			if (it->left)
				qu.push(it->left);
			if (it->right)
				qu.push(it->right);
		}

		result.push_back(temp);
		temp.clear();
	}
	return result;
}
```
>15 **例题15** 调整数组顺序使奇数位于偶数前面
* 问题描述：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
https://leetcode-cn.com/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/
 >>**学习点1**：双指针
 ``` C++ 20201110
vector<int> exchange(vector<int>& nums)
{
	 /*方法一  碰撞指针*/
	if (nums.empty())
		return {};
	int i = 0;
	int j = nums.size() - 1;
	while (i<j)
	{
		while (i < j&&nums[i] % 2 != 0)
		{
			i++;
		}
		while (i < j&&nums[j] % 2 == 0)
		{
			j--;
		}
		if (i < j)
			swap(nums[i],nums[j]);
	}
	return nums;
	/*方法二 快慢指针*/
	if (nums.empty())
		return {};
	int slow=0, fast = 0;
	for (int i = 0;i < nums.size(); i++)
	{
		if (nums[fast] % 2 != 0)
		{
			swap(nums[slow],nums[fast]);
			slow++;
		}
		fast++;
	}
	return nums;
}
```
>16 **例题16**买卖股票的最佳时机
* 问题描述：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
注意：你不能在买入股票前卖出股票 https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
>>**学习点1**：动态规划 （这道题暴力解法超时）
``` C++ 20201111
int maxProfit(vector<int>& prices)
{
if (prices.empty())         //动态规划法
		return {};
	vector<int>d(prices.size(), 0);
	int mins = prices[0];                    //用mins存nums[i]之前的最小值，我是用循环了 又超时
	for (int i = 1; i < prices.size(); i++)           
	{
		d[i] = d[i - 1] > prices[i] - mins ? d[i - 1] : prices[i] - mins;
		mins = (prices[i] > mins) ? mins : prices[i];
	}
	return d[d.size() - 1];
}
```
>17 **例题17**第一个只出现一次的字符
* 问题描述：在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/
>>**学习点1**：unordered_map
``` C++ 20201110
char firstUniqChar(string s) 
{

	if (s.empty())
		return ' ';
	unordered_map<char, int>res;
	for (int i = 0; i < s.size(); i++)
	{
		res[s[i]]++;
	}

	for (int i = 0; i < s.size(); i++)
	{
		if (res[s[i]]== 1)
			return s[i];
	}
	return ' ';
}
```
>18 **例题18**每日温度
*问题描述：请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。  
https://leetcode-cn.com/problems/daily-temperatures/
>>**学习点1**：单调栈（这道题是单调递减） 暴力解法超出时间限制
``` C++20201112
vector<int> dailyTemperatures(vector<int>& T) //每日温度
{

	if (T.size() == 0)
		return {};
	vector<int>res(T.size(), 0);
	stack<int>temp;
	for (int i = 0; i < T.size(); i++)
	{
		while (!temp.empty() && T[i] > T[temp.top()])
		{
			res[temp.top()] = i - temp.top();
			temp.pop();
		}
		temp.push(i);
	}
	return res;
}
```
>## <center>例题19   回文子串</center>
* 题目描述：给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串  https://leetcode-cn.com/problems/palindromic-substrings/
>>**学习点1**：String 访问char通过方法 charAt(int),索引不对  
>>**学习点2**：中心扩展法
``` Java 日期20201122
  public int countSubstrings(String s) {
        int result = 0;
        for (int center = 0; center < s.length() * 2 - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right))
            {
                left--;
                right++;
                result++;
            }

        }
return result;
   }
```
>## <center>例题20    删除排序数组中的重复项</center>
* 题目描述：给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/
>>**学习点1**：双指针访问数组
``` Java 20201128     C++ 20201126
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
```
## <center>例题21    无重复字符的最长子串</center>
* 题目描述：给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
>>**学习点1**：滑动窗口 Set集合
``` Java  20201202 C++ 20201202
       /*滑动窗口*/
 public int lengthOfLongestSubstring(String s) {
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
	   /*解法2 暴力解法*/
 int lengthOfLongestSubstring(string s) 
{

	int max = 1;
	int next = 0;
	for (int i = 0; i < s.size(); i++)
	{
		int temp = 1;
		for (int j = next; j < i; j++)
		{

			if (s[i] == s[j])
			{
				next = j + 1;
				break;
			}
			else
				temp++;

		}
		max = max > temp ? max : temp;
	}
	return max;
}
```
## <center>例题22    560 和为k的子数组</center>
* 题目描述：给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数 https://leetcode-cn.com/problems/subarray-sum-equals-k/
>>**学习点1**：前缀和  **前缀和的定义：nums的第 0 项到当前项的和**
``` Java   C++ 20201205
 public int subarraySum(int[] nums, int k) {
  int len = nums.length;
        // 计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < len; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                if (preSum[right + 1] - preSum[left] == k) {
                    count++;
                }
            }
        }
        return count;

    }
```
## <center>例题22    300 最长上升子序列</center>
* 题目描述：给定一个无序的整数数组，找到其中最长上升子序列的长度 https://leetcode-cn.com/problems/longest-increasing-subsequence/
>>**学习点1**：动态规划 在计算dp[i] 之前，我们已经计算出 dp[0…i−1] 的值
``` Java 20201207 C++ 20201207
public int lengthOfLIS(int[] nums) 
    {
       if (nums.length==0)
        return 0;
        int result=1;
        int[] dp=new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i=1;i<nums.length;i++)
        {
            for (int j=0;j<i;j++)
            {
                if (nums[j]<nums[i])
                {
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }
            }
            result=Math.max(result,dp[i]);
        }
        return result
```
## <center>例题23  15 三数之和 </center>
* 题目描述：给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复的三元组  https://leetcode-cn.com/problems/3sum/
>>**学习点1**：排序 + 双指针 **当我们需要枚举数组中的两个元素时，如果我们发现随着第一个元素的递增，第二个元素是递减的，例如此题 b+c=0;那么就可以使用双指针的方法，将枚举的时间复杂度从 O(N^2)减少至 O(N)**

``` Java C++ 20201209
public List<List<Integer>> threeSum(int[] nums) {
      
        List<List<Integer>>res=new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        for (int first=0;first<nums.length;first++)
        {
          
            if (first>0&&nums[first]==nums[first-1])
                continue;
        int third=nums.length-1;
        for (int second=first+1;second<nums.length;second++)
        {
          
            if (second>first+1&&nums[second]==nums[second-1])
                 continue;
           while (second<third&&nums[second]+nums[third]>-nums[first])
           {
               third--;
           }
           if (second==third)
             break;
             if (nums[second]+nums[third]==-nums[first])
            {
                List<Integer>temp=new ArrayList<>();
                temp.add(nums[first]);
                temp.add(nums[second]);
                temp.add(nums[third]);
                res.add(temp);
                
            }
        }
        }
        return res;
    }
```
## <center>例题24 152 乘机最大子数组</center>
* 题目描述：给你一个整数数组 nums ，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积 https://leetcode-cn.com/problems/maximum-product-subarray/
>>**学习点1**：动态规划，这道题根据正负性分类讨论
``` Java C++ 20201211
public int maxProduct(int[] nums) 
{
      
        int[] big=new int[nums.length];
        int[] small=new int[nums.length];
        int result=nums[0];
        big[0]=nums[0];
        small[0]=nums[0];
        for (int i=1;i<nums.length;i++)
        {
            big[i]=Math.max(big[i-1]*nums[i],Math.max(nums[i],small[i-1]*nums[i]));
            small[i]=Math.min(big[i-1]*nums[i],Math.min(nums[i],small[i-1]*nums[i]));
            result=Math.max(big[i],result);
        }
        return result;
    }
```
## <center>例题25 从尾到头打印链表</center>
* 题目描述：输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）  https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/
>>**学习点1**：栈  Java: push()   pop() peek()       C++   push()  pop() front()
``` Java C++ 20201224
 public int[] reversePrint(ListNode head) {
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
                //int a=res.peek();
                result[index++]=res.pop();
               // res.pop();
            }
            return result;
    }
```
## <center>例题26 和为s的连续正数序列</center>
* 题目描述：输入一个正整数 target ，输出所有和为 target 的**连续**正整数序列（至少含有两个数）。序列内的数字由小到大排列，不同序列按照首个数字从小到大排列 https://leetcode-cn.com/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/
>>**学习点1**: 回溯算法 （此题超时），滑动窗口算法
``` C++ 20201228
           /*方法一 回溯算法*/
vector<vector<int>>result;
vector<int>temp;
void backtracking(int target, int sum,int index)
{
	if (sum == target)
	{
		result.push_back(temp);
		return;
	}
	if (sum > target)
		return;
	for (int i = index; i < target; i++)
	{
		sum += i;
		if (temp.size() == 0 || (temp.size() > 0 && i - temp.back() == 1))
		{
			temp.push_back(i);
			backtracking(target, sum, i + 1);
			temp.pop_back();
			sum -= i;
		}
	}
}
vector<vector<int>> findContinuousSequence(int target) //此题回溯超时
{
	backtracking(target, 0,1);
	return result;
}
            /*方法二 滑动窗口*/
vector<vector<int>> findContinuousSequence2(int target) //滑动窗口通过
{
	int L = 1;
	int R = 1;
	int sum = 1;
	vector<vector<int>>result;
	while (L <= R && R <= target / 2 + 1)
	{
		if (sum < target)
		{
			R++;
			sum += R;
		}
		else if (sum > target)
		{
			sum -= L;
			L++;
		}
		else
		{
			vector<int>temp;
			for (int i = L; i <= R; i++)
			{
				temp.push_back(i);
			}
			result.push_back(temp);
			sum -= L;
			L++;
		}

	}
	return result;
}
```
## <center>例题27 两数相加</center>
* 题目描述：给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。请你将两个数相加，并以相同形式返回一个表示和的链表。https://leetcode-cn.com/problems/add-two-numbers/
>>**学习点1**: 维护一个进位变量int wei;
```C++
ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) 
    {
        int wei=0;
        ListNode* t=new ListNode(-1);
        ListNode*p=t;
        while (l1!=nullptr||l2!=nullptr||wei!=0)
        {
          int l1val=l1!=nullptr?l1->val:0;
          int l2val=l2!=nullptr?l2->val:0;
          int temsum=l1val+l2val+wei;
          wei=temsum/10;
          t->next=new ListNode(temsum%10);
          t=t->next;
          if (l1)
          l1=l1->next;
          if(l2)
          l2=l2->next;
        }
        return p->next;
    }
```
## <center>例题28 使用最小花费爬楼梯</center>
* 题目描述：数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]（下标从 0 开始）
每当你爬上一个阶梯你都要花费对应的体力值，一旦支付了相应的体力值，你就可以选择向上爬一个阶梯或者爬两个阶梯。请你找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯 https://leetcode-cn.com/problems/min-cost-climbing-stairs
>>**学习点1**: 动态规划 读懂题意，确定dp[i]的意义是什么
``` C++
int minCostClimbingStairs(vector<int>& cost)   // 使用最小花费爬楼梯
{
	if (cost.empty())            
		return 0;
	vector<int>dp(cost.size()); 
	dp[0] = cost[0];
	if (cost.size() >= 1)
		dp[1] = cost[1];
	for (int i = 2; i < cost.size(); i++)
	{
		dp[i] = min(dp[i-1]+cost[i],dp[i-2]+cost[i]);
	}
	return min(dp[cost.size() -1],dp[cost.size()-2]);
}
```
## <center>例题29 按摩师</center>
* 题目描述：一个有名的按摩师会收到源源不断的预约请求，每个预约都可以选择接或不接。在每次预约服务之间要有休息时间，因此她不能接受相邻的预约。给定一个预约请求序列，替按摩师找到最优的预约集合（总预约时间最长），返回总的分钟数。https://leetcode-cn.com/problems/the-masseuse-lcci/
>>**学习点1**:动态规划
``` C++
int massage(vector<int>& nums)
{
	if (nums.empty())
		return 0;
	int size = nums.size();;
	int *dp = new int[size];
	dp[0] = nums[0];
	if (size >= 2)
	{
		dp[1] = max(dp[0], nums[1]);
	}
	for (int i = 2; i < size; i++)
	{
		dp[i] = max(dp[i-1],nums[i]+dp[i-2]);
	}
		return dp[size-1];
}
```  
## <center>例题30 买卖股票的最佳时机2</center>
* 题目描述：给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票） https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/ 
>>**学习点1**:
``` C++ 
int maxProfit2(vector<int>& prices) 
{
	int ans = 0;
	for (int i = 1; i <= prices.size() - 1; i++) //扫描一遍 只要后一天比前一天大 就把这两天的差值加一下,因为可以多次买卖一支股票
	{
		if (prices[i] > prices[i - 1])
		{
			ans += prices[i] - prices[i - 1];
		}
	}
	return ans;
}
```
## <center>例题31 最长公共前缀</center>
* 题目描述：编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 "" https://leetcode-cn.com/problems/longest-common-prefix/
>>**学习点1**:
``` C++
string longestCommonPrefix(vector<string>& strs)  //最长公共前缀
{
	if (strs.empty())
		return "";
	string res = "";
	for (int i = 0; i < strs[0].size(); i++)
	{
		for (int j = 1; j < strs.size(); j++)
		{
			if (strs[0][i] != strs[j][i])
			{
				return res;
			}
		}
		res += strs[0][i];
	}
	return res;
}
```
## <center>例题32 移除元素</center>
* 题目描述：给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组 https://leetcode-cn.com/problems/remove-element/  
>>**学习点1**:
``` C++
int removeElement(vector<int>& nums, int val)  //移除元素
{
	if (nums.empty())
		return 0;
	int res = 0;
	for (int i = 0; i < nums.size(); i++)
	{
		if (nums[i] != val)
		{
			nums[res++] = nums[i];
		}

	}
	return res;
}
```
## <center>例题33 回文数</center>
* 题目描述：给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。例如，121 是回文，而 123 不是https://leetcode-cn.com/problems/palindrome-number
>>**学习点1**:
``` C++
bool isPalindrome(int x)  //回文数
{
	/* 方法一：if (x < 0)
		return false;

	string b = to_string(x);
	reverse(b.begin(), b.end());
	int c = atoi(b.c_str());
	if (c == x)
		return true;
	else
		return false;*/
	 /* 方法二*/
	if (x < 0)
		return false;
	int re = 0;
	double y = 0;   // y是int 执行不通过
	int copyx = x;
	while (copyx)
	{
		re = copyx % 10;
		y = y * 10 + re;
		copyx = copyx / 10;
	}
	return y == x;
}
```
## <center>例题34 下一个排列</center>
* 题目描述：实现获取 下一个排列 的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。必须原地修改，只允许使用额外常数空间。 https://leetcode-cn.com/problems/next-permutation/
>>**学习点1**: 算法思路 :从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序,在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。
将 A[i] 与 A[k] 交换,可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序.如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
``` C++
void nextPermutation(vector<int>& nums) //下一个排列
{
	int i = nums.size()-2;
	while (i >= 0)              
	{
		if (nums[i] < nums[i + 1])
			break;
		else
			i--;
	}
	int j = nums.size() - 1;
	if (i >= 0)
	{
		while (j > i)
		{
			if (nums[j] > nums[i])
				break;
			else
				j--;
		}
		swap(nums[i],nums[j]);
	}
	reverse(nums.begin()+i+1,nums.end());
}
```
## <center>例题35 跳跃游戏</center>
* 题目描述：给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。判断你是否能够到达最后一个下标https://leetcode-cn.com/problems/jump-game/
>>**学习点1**: 方法一 思路：如果所有元素都不为0， 那么一定可以跳到最后；从后往前遍历，如果遇到nums[i] = 0，就找i前面的元素j，使得nums[j] > i - j。如果找不到，则不可能跳跃到num[i+1]，返回false
>>**学习点2**: 方法二 参考答案，如果一个位置能够到达，那么这个位置左侧所有位置都能到达
``` C++  
 /*方法一*/
 bool canJump(vector<int>& nums) 
    {
      if (nums.empty()||nums.size()==1)
		return true;
bool res = true;	
	for (int i = nums.size() - 1; i >= 0; i--)
	{
		bool temp = false;
		if (nums[i] == 0)
		{
			for (int j = i - 1; j >= 0; j--)
			{
				if (i<nums.size()-1&&nums[j] > i - j)
				{
					temp = true;
					break;
				}
                if (i==nums.size()-1&&nums[j] >= i - j)  //最后一个元素为0 判断条件改变了
				{
					temp = true;
					break;
				}
			}
			if (temp == false)
			{
				res = false;
				break;
			}
			
		}
	}
	return res;
    }

	/*方法二*/
	bool canJump(vector<int>& nums) 
{
	int k = 0;
	for (int i = 0; i < nums.size(); i++)
	{
		if (i > k) return false;
		k = max(k, i + nums[i]);
	}
	return true;
}
```
## <center>例题36 合并区间</center>
* 题目描述：以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 https://leetcode-cn.com/problems/merge-intervals/
>>**学习点1**: 首先，我们将列表中的区间按照左端点升序排序。然后我们将第一个区间加入 merged 数组中，并按顺序依次考虑之后的每个区间：
如果当前区间的左端点在数组 merged 中最后一个区间的右端点之后，那么它们不会重合，我们可以直接将这个区间加入数组 merged 的末尾；
否则，它们重合，我们需要用当前区间的右端点更新数组 merged 中最后一个区间的右端点，将其置为二者的较大值
``` C++
vector<vector<int>> merge(vector<vector<int>>& intervals)
{
	if (intervals.empty())
		return {};
	vector<vector<int>>res; 
	sort(intervals.begin(), intervals.end(), [](vector<int>&a,vector<int>&b) 
	{
		
			return a.front() < b.front();
	});
	res.push_back(intervals[0]);
	for (int i = 1; i < intervals.size(); i++)
	{
		if (intervals[i].front() <= res.back().back())
		{
			res.back().back() = max(res.back().back(),intervals[i].back());
		}
		else
			res.push_back(intervals[i]);
	}
	return res;

}
```
## <center>例题37 罗马数字转整数</center>
* 题目描述：https://leetcode-cn.com/problems/roman-to-integer/ 
>>**学习点1**:首先建立一个HashMap来映射符号和值，然后对字符串从左到右来，如果当前字符代表的值不小于其右边，就加上该值；否则就减去该值。以此类推到最左边的数，最终得到的结果即是答案
``` C++
int romanToInt(string s) //罗马数字转整数
{
	int res=0;
	unordered_map<char,int>roman;
	roman['I'] = 1;
	roman['V'] = 5;
	roman['X'] = 10;
	roman['L'] = 50;
	roman['C'] = 100;
	roman['D'] = 500;
	roman['M'] = 1000;
	for (int i=0;i<s.size();i++)
	{
		if (roman[s[i]] < roman[s[i + 1]])
		{
			res = res - roman[s[i]]+roman[s[i + 1]];
			i++;
		}
		else
		{
			res=res + roman[s[i]] ;
		}
	}
	return res;
}
```
## <center>例题38 加一</center>
* 题目描述：给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。你可以假设除了整数 0 之外，这个整数不会以零开头 https://leetcode-cn.com/problems/plus-one/
>>**学习点1**:
``` C++
vector<int> plusOne(vector<int>& digits)  // 加一
{
	vector<int>res(digits.size() + 1, 0);
	int size = digits.size();
	bool ind=false;	
	for (int i = size - 1; i >= 0; i--)
	{
		if (digits[i] != 9)
		{
			digits[i] += 1;
			ind = true;
			return digits;
		}
		if (digits[i] == 9)
		{
			digits[i] = 0;
		}
		
	}
	if (ind == false)
	{
		res.front() = 1;
	}
	return res;
}
```
## <center>例题39 剪绳子</center>
* 题目描述：给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少 https://leetcode-cn.com/problems/jian-sheng-zi-lcof/ 
>>**学习点1**:动态规划
``` C++
int cuttingRope(int n) //剪绳子
{
	vector<int>dp(n + 1); // dp[i] 表示长度为i的剪短后的最大乘积 （dp[i]默认都为0）
	dp[1] = 0;
	dp[2] = 1;
	for (int i = 3; i <= n; i++)
	{
		for (int j = 1; j < i; j++)
	{
		dp[i] = max(dp[i], max((i - j)*j, j*dp[i - j])); // 长度为i的绳子，减掉j,比较 (i-j)*j 和 j*dp[i-j] 和 上次求出的dp[i],取最大值
	}
	}


	return dp[n];
}
```
## <center>例题40 盛最多水的容器</center>
* 题目描述：给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水 https://leetcode-cn.com/problems/container-with-most-water/
>>**学习点1**: 双指针 见链接的题解 (在移动的过程中不断消去不可能成为最大值的状态)
``` C++
 int maxArea(vector<int>& height)
    {
    int res = 0;
	int size = height.size();
    int first=0;
    int last=size-1;
	while (first<last)
    {
        res=max(res,min(height[first],height[last])*(last-first));
        if (height[first]<height[last]) 
        {
            first++;
        }
        else
        {
            last--;
        }
    }
    return res;
```
## <center>例题41 顺时针打印矩阵</center>
* 题目描述：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字 https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
>>**学习点1**:  
``` C++
vector<int> spiralOrder(vector<vector<int>>& matrix) //顺时针打印矩阵
{
	if (matrix.empty())
		return {};	
	int rows = matrix.size();
	int cols = matrix[0].size();
	vector<int>res(rows*cols);
	int top = 0;
	int left = 0;
	int right = cols - 1;
	int bottom = rows - 1;
	int index = 0;
	while (true)
	{
		for (int i = left; i <=right; i++)  // 从左到右
		{
			res[index++] = matrix[top][i];
		}
		if (++top > bottom)
		{
			break;
		}
		for (int i = top; i <= bottom; i++) //从上到下
		{
			res[index++] = matrix[i][right];
		}
		if (--right < left)
		{
			break;
		}
		for (int i = right; i >= left; i--) //从右到左
		{
			res[index++] = matrix[bottom][i];
		}
		if (--bottom < top)
		{
			break;
		}
		for (int i = bottom; i >=top; i--) //从下到上
		{
			res[index++] = matrix[i][left];
		}
		if (++left > right)
		{
			break;
		}
	}
	return res;
}
```
## <center>例题42 接雨水</center>
*  题目描述：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水https://leetcode-cn.com/problems/trapping-rain-water/
>>**学习点1**:  对于数组中的每个元素，我们找出下雨后水能达到的最高位置，等于两边最大高度的较小值减去当前高度的值，方法二是对方法一的优化，在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。但是我们可以提前存储这个值
``` C++ 方法一
int trap(vector<int>& height)  //时间复杂度 O(n^2)
{
	if (height.empty())
		return 0;
	int res = 0;
	int size = height.size();
	for (int i = 1; i < size - 1; i++)
	{
		int left_max = 0;
		int right_max = 0;
		for (int j = 0; j <= i; j++)
		{
			left_max = max(left_max,height[j]);
		}
		for (int k = i; k < size; k++)
		{
			right_max = max(right_max,height[k]);
		}
		res += min(left_max, right_max) - height[i];
	}
	return res;
}
```
``` C++ 方法二
int trap(vector<int>& height) //在暴力方法中，我们仅仅为了找到最大值每次都要向左和向右扫描一次。但是我们可以提前存储这个值，时间复杂度 O(n)
{
        if (height.empty())
        return 0;
        int res=0;
        int size=height.size();
        vector<int>left_max(size,0);
        vector<int>right_max(size,0);
        left_max[0]=height[0];
        right_max[size-1]=height[size-1];
        for (int i=1;i<size;i++)
        {
            left_max[i]=max(height[i],left_max[i-1]);
        }
        for (int i=size-2;i>=0;i--)
        {
            right_max[i]=max(right_max[i+1],height[i]);
        }
        for (int i=1;i<size-1;i++)
        {
            res+=min(right_max[i],left_max[i])-height[i];
        }
        return res;
    }
```
## <center>例题43 岛屿数量</center>
* 题目描述：给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。此外，你可以假设该网格的四条边均被水包围 https://leetcode-cn.com/problems/number-of-islands/
>>**学习点1**: 在深度优先搜索的过程中，每个搜索到的 1 都会被重新标记为 0,最终岛屿的数量就是我们进行深度优先搜索的次数
``` C++
void dfs(vector<vector<char>>& grid,int n,int m)
    {
        grid[n][m]='0';
        int rows=grid.size();
        int cols=grid[0].size();
        if (n>=1&&grid[n-1][m]=='1') dfs(grid,n-1,m);
        if (n<rows-1&&grid[n+1][m]=='1') dfs(grid,n+1,m);
        if (m>=1&&grid[n][m-1]=='1') dfs(grid,n,m-1);
        if (m<cols-1&&grid[n][m+1]=='1') dfs(grid,n,m+1);

    }
    int numIslands(vector<vector<char>>& grid) 
    {
          int res=0;
          int rows=grid.size();
          int cols=grid[0].size();
          for (int i=0;i<rows;i++)
          {
              for (int j=0;j<cols;j++)
              {
                  if (grid[i][j]=='1')
                  {
                   res++;
                   dfs(grid,i,j);
                  }
              }
          }
          return res;
    }
``` 
## <center>例题44 零钱兑换</center>
* 题目描述：给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。你可以认为每种硬币的数量是无限的。 https://leetcode-cn.com/problems/coin-change/
>>**学习点1**: 动态规划
``` C++
int coinChange(vector<int>& coins, int amount)  //零钱兑换
{
	vector<int>dp(amount + 1, amount + 1);
	dp[0] = 0;
	for (int i = 1; i < dp.size(); i++)
	{
		for (int j = 0; j < coins.size(); j++)
		{
			if (i >= coins[j])
			dp[i] = min(dp[i], 1 + dp[i - coins[j]]);

		}		
	}
	if (dp[amount] > amount)
		return -1;
	return dp[amount];
}
```
## <center>例题45 旋转图像</center>
* 题目描述：给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。https://leetcode-cn.com/problems/rotate-image/
>>**学习点1**: 先以对角线翻转，再以中心左右翻转
>>**学习点1**: vector<int>a;vector<int>b; a=b 值拷贝
``` C++
void rotate(vector<vector<int>>& matrix) // 原地旋转图像，先以对角线翻转，再以中心左右翻转
{
	int rows = matrix.size();
	int cols = rows;
	int temp;
	for (int i = 0; i < rows; i++)  // 对角线翻转
	{
		for (int j = i; j < cols; j++)
		{
			temp = matrix[i][j];
			matrix[i][j] = matrix[j][i];
			matrix[j][i] = temp;
		}		
	}
	for (int i = 0; i < rows; i++)
	{
		for (int j=0;j<cols/2;j++)
		{
			swap(matrix[i][j],matrix[i][cols-j-1]);
		}
	}

}
```
## <center>例题46 圆圈中最后剩下的数字</center>
* 题目描述：0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字 https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/
>>**学习点1**: 方法一 暴力解法(超时)
>>**学习点1**: 逆推，找出最后一个数字在最开始数组的索引
``` C++ 约瑟夫环问题
int lastRemaining(int n, int m) // 暴力解法
{
	vector<int>res(n);
	for (int i = 0; i < n; i++)
	{
		res[i]=i;
	}
	int index = 0;
	while (res.size() != 1)
	{
		index = index + m - 1;
		/*while (index >= res.size())
		{
			index -= res.size();
		}*/
		index=index%res.size();
		res.erase(res.begin()+index);
	}
	return res.front();
}
```
``` C++ 方法二 数学方法
int lastRemaining(int n, int m) 
{
        int x = 0;
        for (int i = 2; i <= n; i++) 
		{
            x = (x + m) % i;
        }
        return x;
}
```
## <center>例题47 扑克牌中的顺子</center>
* 题目描述：从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof/
>>**学习点1**: 这道题思路需满足两点 1 除大小王外，所有牌无重复；2 最大的牌为max ，最小的牌为min ，需满足：max - min < 5
``` C++
bool isStraight(vector<int>& nums) //扑克牌中的顺子
{
	unordered_set<int>sz;
	int maxv= -1;
	int minv = 14;
	for (int i = 0; i < nums.size(); i++)
	{
		if (nums[i] == 0) continue;
		if (sz.find(nums[i]) == sz.end())
		{
			sz.insert(nums[i]);
			maxv = max(maxv,nums[i]);
			minv = min(minv,nums[i]);
		}
		else
			return false;
	}
	if (maxv - minv < 5) return true;
	else
		return false;
}
```
## <center>例题48 栈的压入、弹出序列</center>
* 题目描述：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列 https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/
>>**学习点1**: 判断合不合法，用个栈试一试: 把压栈的元素按顺序压入，当栈顶元素和出栈的第一个元素相同，则将该元素弹出，出栈列表指针后移并继续判断。最后判断出栈列表指针是否指向出栈列表的末尾即可
``` C++
bool validateStackSequences(vector<int>& pushed, vector<int>& popped) // 栈的压入、弹出序列
{
	stack<int>simu;
	int index = 0;
	for (int i=0;i<pushed.size();i++)
	{
		simu.push(pushed[i]);
		while (!simu.empty()&&simu.top()==popped[index])
		{
			simu.pop();
			index++;
		}

	}
	return index == popped.size();  // return simu.empty();
	
}
```
## <center>例题49 求1+2+3+……+n</center>
* 题目描述：求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C） https://leetcode-cn.com/problems/qiu-12n-lcof/
>>**学习点1**: 
``` C++
 int sumNums(int n)   // 方法一
    {
        char arr[n][n+1];           
        return sizeof(arr)>>1;
    }
 int sumNums(int n)  // 方法二
   {
        int res = n;
        n && (res += sumNums(n-1));
        return res;
    }
```
## <center>例题50 矩阵中的路径</center>
* 题目描述：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof/
>>**学习点1**:本问题是典型的矩阵搜索问题，可使用 深度优先搜索（DFS）+ 剪枝解决
``` C++
bool dfs(vector<vector<char>>& board, string word, int rows, int cols, int index)
{
	if (rows >= board.size() || rows < 0 || cols < 0 || cols >= board[0].size()) return false;
	if (board[rows][cols] != word[index])
		return false;
	if (index == word.size() - 1)
		return true;	
	board[rows][cols] = '\0';
	bool res = dfs(board, word, rows + 1, cols, index + 1) || dfs(board, word, rows - 1, cols, index + 1)
		|| dfs(board, word, rows, cols - 1, index + 1) || dfs(board, word, rows, cols + 1, index + 1);
	board[rows][cols] = word[index];
	return res;
}
bool exist(vector<vector<char>>& board, string word) // 矩阵中的路径
{
	int rows = board.size();
	int cols = board[0].size();
	for (int i = 0; i < rows; i++)
	{
		for (int j = 0; j < cols; j++)
		{
			if (dfs(board, word, i, j, 0))
				return true;
		}
	}
	return false;
}
```
## <center>例题51 把数字翻译成字符串</center>
* 题目描述：给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法 https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/
>>**学习点1**: 见题解评论
>>**学习点2**: 边界条件要考虑满足通式
``` C++
int translateNum(int num) // 把数字翻译成字符串
{
	if (num == 0)
		return 0;
	string s = to_string(num);
	int size = s.size();
	vector<int>dp(size + 1, 1);
	dp[0] = 1;         // 为了满足通式
	dp[1] = 1;
	for (int i = 2; i < dp.size(); i++)
	{
		int temp = atoi(s.substr(i - 2, 2).c_str());
		if (temp >= 10 && temp <= 25)
		{
			dp[i] = dp[i - 1] + dp[i-2];
		}
		else
			dp[i] = dp[i - 1];
	}
	return dp[size];
}
```
## <center>例题52 机器人的运动范围</center>
* 题目描述：地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/
>>**学习点1**:深度优先遍历(dfs)
``` C++
int dfs(int m, int n, int k, int rows, int cols, vector<vector<bool>>& help)
{
	if (rows >= m || rows < 0 || cols >= n || cols<0 || rows / 10 + rows % 10 + cols / 10 + cols % 10 > k ||
		help[rows][cols] == false) return 0;
	help[rows][cols] = false;
	int a = dfs(m, n, k, rows + 1, cols, help);
	int b = dfs(m, n, k, rows, cols + 1, help);
	int c = dfs(m, n, k, rows - 1, cols, help);
	int d = dfs(m, n, k, rows, cols - 1, help);
	return a + b + c + d + 1;

}
int movingCount(int m, int n, int k)  // 机器人的运动范围
{
	vector<vector<bool>>help(m, vector<bool>(n, true));
	return dfs(m, n, k, 0, 0, help);
}
```
## <center>例题52 把数组排成最小的数</center>
* 题目描述：输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个 https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/
>>**学习点1**:有两个字符串str1和str2，其中str1"小于"str2的表示方法是str1 + str2 < str2 + str1
>>**学习点2**:把对应整数的字符串放到容器vector中，自定义比较规则，这道题是把最小字符串放到高位(前面)，所以用学习点1的比较规则自定义排序
``` C++
string minNumber(vector<int>& nums) // 把数组排成最小的数
{
	string res = "";
	vector<string>temp;
	for (int i = 0; i < nums.size(); i++)
	{
		temp.push_back(to_string(nums[i]));
	}
	sort(temp.begin(), temp.end(), [](string &a, string &b)
	{
		
		return a + b < b + a;
	}
	);
	for (int i = 0; i < temp.size(); i++)
	{
		res += temp[i];
	}
	return res;
}
```
## <center>例题53 n个骰子的点数</center>
* 题目描述：把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率 https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof/
>>**学习点1**:动态规划 1 表示状态 2 找出状态转移方程 3 边界处理
>>**学习点2**:
``` C++
vector<double> dicesProbability(int n)  // n个骰子的点数
{
	vector<double>res;
	vector<vector<int>>dp(n+1, vector<int>(6 * n + 1, 0)); //dp[i][j]表示：当有i个骰子时，点数和为j出现的个数
	for (int i = 1; i <=6; i++)
	{
		dp[1][i] = 1;
	}
	for (int i = 2; i <=n; i++)
	{
		for (int j = i;j <=6*n; j++)
		{
			for (int k = 1; k <= 6; k++)
			{
				if (j-k>0)
				{
					dp[i][j] += dp[i - 1][j - k];  // 状态转移方程
				}
				else
					break;
			}
		}
	}
	for (int i = n; i <=6*n; i++)
	{
		res.push_back(dp[n][i] * 1.0 / pow(6, n));
	}
	return res;
}
```
## <center>例题54 翻转单词顺序</center>
* 题目描述：输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I" https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/
>>**学习点1**:stringstream 的使用
``` C++
string reverseWords(string s)  //翻转单词顺序
{
	stringstream ss(s);
	stack<string>dd;   // 使用栈来反过来
	string temp;
	string res;
	while (ss >> temp)
	{
		dd.push(temp);
		dd.push(" ");
	}
	if (!dd.empty())
	{
		dd.pop();
	}
	while (!dd.empty())
	{
		res += dd.top();
		dd.pop();
	}
	return res;
}
``` 
## <center>例题55 数字序列中某一位的数字</center>
* 题目描述：数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。请写一个函数，求任意第n位对应的数字 https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/ 
>>**学习点1**:这是一道找规律题 第一步找到这个数字在序列中对应的是几位数 ；第二步：找到这个目标数；第三步：确定目标数字在这个目标数的第几位
``` C++
int findNthDigit(int n) //数字序列中某一位的数字
{
	int temn = n;
	int digits = 1;   // 这个数字在序列中对应的是几位数
	
	long base = 9;
	while (n - base * digits > 0)
	{
		n = n - base * digits;
		digits++;
		base = base * 10;
	}
	long number = 1;
	for (int i = 1; i < digits; i++)
	{
		number *= 10;
	}
	number += (n%digits == 0) ? n / digits - 1 : n / digits;    //找到目标数 number

	// 从目标数中找到想要的那个数字
	if (n%digits == 0) return number % 10;
	int index = n % digits;  // index代表目标数的第几个数字
	for (int i = 0; i < digits-index; i++)
	{
		number /= 10;
	}
	return number%10;

}
```
## <center>例题56 数值的整数次方</center>
* 题目描述：实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题 https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/
>>**学习点1**:当n为偶数，即求两个指数为n/2的快速幂的积；当n为奇数，即求两个指数为n/2的快速幂的积再乘1个底数
``` C++
double myPow(double x, int n) //数值的整数次方
{
	if (n == 0) return 1;
	if (n == 1) return x;
	if (n == -1) return 1 / x;
	double half = myPow(x, n / 2);
	double mod = myPow(x, n % 2);
	return half * half*mod;
}
```
## <center>例题57 丑数</center>
* 题目描述：我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数 https://leetcode-cn.com/problems/chou-shu-lcof/ 
>>**学习点1**: 三指针 不是动态规划，详见评论第一条
``` C++
int nthUglyNumber(int n) // 丑数
{
	if (n <= 0)
		return 0;
	vector<int>dd(n);
	dd[0] = 1;
	int i = 0, j = 0, k = 0;
	for (int o = 1; o < n; o++)
	{
		int temp = min(dd[i]*2,min(dd[j]*3,dd[k]*5));
		if (temp == dd[i] * 2) i++;
		if (temp == dd[j] * 3) j++;
		if (temp == dd[k] * 5) k++;
		dd[o] = temp;
	}
	return dd[n-1];
}
```
## <center>例题58 把字符串转换成整数</center>
* 题目描述：见链接 https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof/
>>**学习点1**: 自己写的
``` C++
int fir(string str)    // 找到第一个数字所在的索引
{
	for (int i = 0; i < str.size(); i++)
	{
		char a = str[i];
		if (a != ' ')
		{
			if (a == '+' || a == '-' || (a <= '9'&&a >= '0'))
			{
				return i;

			}
			else
				return -1;
		}		
	}
	return -1;
}
int la(string str)   找到最后一位数字所在索引
{
	for (int i = str.size()-1; i >=0; i--)
	{
		char a = str[i];
		if (a <= '9'&&a >= '0')
		{
			return i;
		}
	}
	return -1;
}
int strToInt(string str) // 把字符串转换成整数
{
	if (str.empty())
		return 0;
	int first = fir(str);
	int last = la(str);
	if (first == -1 || last == -1)
	{
		return 0;
	}
	string s = str.substr(first, last - first + 1); // 找到代表数字的子字符串
	stringstream ss;   //转换成int
	ss << s;
	int res;
	ss >> res;
	return res;
}
```
## <center>例题59 汉明距离</center>
* 题目描述：两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。给出两个整数 x 和 y，计算它们之间的汉明距离 https://leetcode-cn.com/problems/hamming-distance/
>>**学习点1**: 位运算
``` C++
int hammingDistance(int x, int y) // 汉明距离
{
	int a = x ^ y;
	int res = 0;
	while (a!=0)
	{
		if (a & 1 != 0)
		{
			res++;			
		}
		a=a >> 1;
	}
	return res;
}
```
## <center>例题60 打家劫舍二</center>
* 题目描述：你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，能够偷窃到的最高金额。https://leetcode-cn.com/problems/house-robber-ii/
 
>>**学习点1**:环状排列意味着第一个房子和最后一个房子中只能选择一个偷窃，所以可以把环状排列房间问题 分解 为两个单排排列房间子问题(打家劫舍一)，即 一个是从0到n-1，另一个是从1到n，转换为单排排列房间(打家劫舍一)，求的是这两个单排排列房间的最大值
``` C++
int rob1(vector<int>& nums)  // 打家劫舍一
{
	if (nums.empty())
	{
		return 0;
	}
	int size = nums.size();
	if (size == 1)
		return nums.front();
	vector<int>dp(size);
	dp[0] = nums[0];
	dp[1] = max(nums[0],nums[1]);
	for (int i = 2; i < size; i++)
	{
		dp[i] = max(dp[i-2]+nums[i],dp[i-1]);
	}
	return dp[size-1];
}
int rob2(vector<int>& nums) //打家劫舍二
{
	if (nums.size() == 1)    // 考虑只有一个值的情况
		return nums.front();
	vector<int>a(nums.begin(),nums.end()-1);
	vector<int>b(nums.begin()+1,nums.end());
	return max(rob1(a),rob1(b));
}
```
## <center>例题61 打家劫舍三</center>
* 题目描述:在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。计算在不触动警报的情况下，小偷一晚能够盗取的最高金额 https://leetcode-cn.com/problems/house-robber-iii/
>>**学习点1**: 1. 对于一个以 node 为根节点的二叉树而言，如果尝试偷取 node 节点，那么势必不能偷取其左右子节点，然后继续尝试偷取其左右子节点的左右子节点; 2. 如果不偷取该节点，那么只能尝试偷取其左右子节点; 3. 比较两种方式的结果，谁大取谁。
``` C++
unordered_map<TreeNode*, int>s;
int retrot(TreeNode* root)  //尝试对以 node 为根节点的二叉树进行偷取，返回符合题意偷取的最大值
{
	if (root == nullptr)
	{
		return 0;
	}
	if (s.count(root) != 0)   // 避免不必要的重复计算，否则会有节点被重复计算
	{
		return s[root];
	}
	int f = 0;
	if (root->left != nullptr)
	{
		f = f+retrot(root->left->left)+ retrot(root->left->right);
	}
	if (root->right != nullptr)
	{
		f = f+retrot(root->right->left) + retrot(root->right->right);
	}
	f += root->val;
	int l = retrot(root->left)+retrot(root->right);
	s[root] = max(f,l);
	return max(f,l);
}
int rob3(TreeNode* root) // 打家劫舍三
{
	return retrot(root);
}
```
