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