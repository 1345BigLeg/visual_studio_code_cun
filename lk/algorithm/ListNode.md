>1 题目描述：判断给定的链表中是否有环 https://www.nowcoder.com/practice/650474f313294468a4ded3ce0f7898b9?tpId=188&&tqId=35436&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking  
* **解题思路**：使用快慢指针法，即设置两个指针，一个为slow，一个为fast，从头开始对链表进行扫描。指针slow每次走一步，指针fast每次走两步。如果存在环，则这两个指针一定会相遇。如果不存在环，则fast指针会最先到达NULL而退出。
``` C++  20200921
class Solution {
public:
    bool hasCycle(ListNode *head) {
        
        ListNode *fast=head;
        ListNode *slow=head;
        while (fast->next!=nullptr&&fast!=nullptr)
        {
            slow=slow->next;
            fast=fast->next->next;
            if (fast==slow)
            {
                return true;
            }
        }
            
            return false;
    }
};
```
  
* **如何求单链表的环长**：
设从第一次相遇到第二次相遇，设slow走了len步，则fast走了2 * len步，相遇时多走了一圈：环长 = 2*len - len   

* **如何求有环链表中的环连接点的位置** ：
第一次碰撞点Pos到连接点Join的距离 = 头结点到连接点Join的距离。因此，分别从第一次碰撞点Pos、头指针head开始走，相遇的那个点就是连接点。在环上相遇后，记录第一次相遇点为Pos，连接点为Join，假设头结点到连接点的长度为LenA，连接点到第一次相遇点的长度为x，环长为R。第一次相遇时，slow走的长度S = LenA + x;第一次相遇时，fast走的长度2S = LenA + n * R + x；因此，LenA + x = n * R;　　故LenA = n * R -x;
>2 **反转链表**：输入一个链表，反转链表后，输出新链表的表头   源代码：20200921
https://www.nowcoder.com/practice/75e878df47f24fdc9dc3e400ec6058ca?tpId=188&&tqId=35464&rp=1&ru=/activity/oj&qru=/ta/job-code-high-week/question-ranking
>>* 解法一：栈
```C++
ListNode* ReverseList(ListNode* pHead)
{
	    //栈
	if (pHead == nullptr)
		return pHead;
	stack<ListNode*>st;
	while (pHead)
	{
		st.push(pHead);
		pHead = pHead->next;
	}
	ListNode *top = st.top();
	ListNode *nod = st.top();
	st.pop();
	while (!st.empty())
	{
		top->next=st.top();
		st.pop();
		top = top->next;
		
	}
	top->next = nullptr;
	return nod;
}

```
>>* 解法2 递归
``` C++
ListNode* ReverseList(ListNode* pHead)
{
	if (pHead == NULL || pHead->next == NULL) //找到当前链表的尾结点
	{
		return pHead;
	}
	ListNode* ret = ReverseList(pHead->next);
	pHead->next->next = pHead;
	pHead->next = NULL;
	return ret;

}
```
>>* 解法3 双指针
``` C++
ListNode* ReverseList(ListNode* pHead)
{
    ListNode *cur = pHead;
	ListNode *pre = nullptr;
	if (cur ==nullptr)
	{
		return pre;
	}
	while (cur)
	{
		ListNode *temp = cur->next;
		cur->next = pre;
		pre = cur;
		cur = temp;
	}
	return cur;
}
```
>3 **合并两个有序链表** 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的 源代码：20200925  
https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
>> * 解法一 迭代
``` C++
ListNode* mergeTwoLists(ListNode* l1, ListNode* l2)
{
	ListNode dummy = ListNode(-1);    //解法一 迭代 哑结点
	ListNode *pre = &dummy;
	while (l1 != nullptr&&l2 != nullptr)
	{
		if (l1 == nullptr)
		{
			pre->next = l2;
		}
		else if (l2 == nullptr)
		{
			pre->next = l1;
		}
		else if (l1->val < l2->val)
		{
			pre->next = l1;
			l1 = l1->next;

		}
		else
		{
			pre->next = l2;
			l2 = l2->next;

		}
		pre = pre->next;
	}
	pre->next = l1 == nullptr ? l2 : l1;
	return dummy.next;
}
```
>>* 解法2 递归 (不是特别理解)    
``` C++  
//子问题和原问题具有相同结构
 if (l1 == nullptr) {  
            return l2;
        } else if (l2 == nullptr) {
            return l1;
        } else if (l1->val < l2->val) {
            l1->next = mergeTwoLists(l1->next, l2);
            return l1;
        } else {
            l2->next = mergeTwoLists(l1, l2->next);
            return l2;
        }
    }

```
>4 **例题4** 相交链表
* 问题描述：编写一个程序，找到两个单链表相交的起始节点，这是一个相交的链表
>>**学习点1**：使用unordered_set
``` C++ 20201027
ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
	if (headA == nullptr || headB == nullptr)
		return nullptr;
	ListNode *temp = headA;
	unordered_set<ListNode *>ms;
	while (temp!=nullptr)
	{
		ms.insert(temp);
		temp = temp->next;
	}
	while (headB)
	{
		if (ms.count(headB) == 1)
			return headB;
		headB = headB->next;
	}
	return {};
}
```
>## <center>例题5   排序链表</center>
* 问题描述：给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表  https://leetcode-cn.com/problems/sort-list/solution/ 
>>**学习点1**：归并排序链表
``` C++ 20201116
               /*方法一*/
ListNode* sortList(ListNode* head)  
{
	if (head == nullptr)
		return nullptr;
	vector<int>tem;
	ListNode* tt = head;
	while (tt)
	{
		tem.push_back(tt->val);
		tt = tt->next;
	}
	sort(tem.begin(),tem.end());
	ListNode *nn=head;
	for (int i = 0; i < tem.size(); i++)
	{
		nn->val = tem[i];
		nn = nn->next;
	}
	return head;
}
           /*方法二 归并排序*/
		   ListNode* mer(ListNode * fir, ListNode* sec)
{
	ListNode* temp=new ListNode(-1);
	ListNode*tem = temp;
	while (fir != nullptr && sec != nullptr)
	{
		if (fir->val < sec->val)
		{
			tem->next = fir;
			fir = fir->next;

		}
		else
		{
			tem->next = sec;
			sec = sec->next;
		}
		tem = tem->next;
	}
	if (fir ==nullptr)    tem->next = sec;
	if (sec == nullptr)    tem->next = fir;
	return temp->next;
}
ListNode* sortList(ListNode* head)      //方法二
{
	if (head == nullptr||head->next==nullptr)
		return head;
	ListNode* slow = head;
	ListNode* fast = head->next;
	while (fast&&fast->next)
	{
		slow = slow->next;
		fast = fast->next->next;
	}
	ListNode* rightHead = slow->next;
	slow->next = nullptr;
	ListNode *B = sortList(rightHead);
	ListNode *A = sortList(head);
	
	return mer(A,B);
}
```
>## <center>例题6   删除链表的节点</center>
* 问题描述：给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。返回删除后的链表的头节点https://leetcode-cn.com/problems/shan-chu-lian-biao-de-jie-dian-lcof/
>>**学习点1**： 
``` C++ 20201117
ListNode* deleteNode(ListNode* head, int val)  //删除链表指点元素的节点
{

	if (head == nullptr)
		return nullptr;
	if (head->val == val)
		return head->next;
	ListNode* tem = head;
	ListNode *tt;
	while (tem)
	{

		if (tem->val == val)
		{
			tt->next = tem->next;
			return head;
		}
		tt = tem;
		tem = tem->next;

	}
	return {};
}
```
>## <center>例题7   奇偶链表</center>
* 题目描述：给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性 https://leetcode-cn.com/problems/odd-even-linked-list/  
>>**学习点1**：双指针 思路搞清
``` Java 日期20201124    C++ 
public ListNode oddEvenList(ListNode head) {
         if (head==null||head.next==null)
            return head;
        ListNode ji=head;
        ListNode ou=head.next;
        ListNode tmpou=ou;
        while (tmpou!=null&&tmpou.next!=null)
        {
            ji.next=tmpou.next;
            ji=ji.next;
            tmpou.next=ji.next;
            tmpou=tmpou.next;
        }
         ji.next=ou;

         return head;
    }
```

