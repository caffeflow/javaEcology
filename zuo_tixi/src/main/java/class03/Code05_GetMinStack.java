package class03;
/**
 * 题目：
 * 栈和队列的常见面试题 实现一个特殊的栈，在基本功能的基础上，再实现返回栈中最小元素的功能
 * 1）pop、push、getMin操作的时间复杂度都是 O(1)。
 * 2）设计的栈类型可以使用现成的栈结构。
 */

/**
 * 笔记1：
 * 既然语言都有这些结构和api, 为什么还需要手撸练习？
 * 1) 算法问题无关语言
 * 2）语言提供的api是有限的，当有新的功能是api不提供的，就需要改写
 * 3）任何软件工具的底层都是最基本的算法和数据结构，这是绕不过去的
 *
 * 笔记2：
 * 栈和队列的常见面试题
 * 怎么用数组实现不超过固定大小的队列和栈？
 * 栈：正常使用
 * 队列：环形数组
 *
 *
 * 笔记3：
 * 含最小元素方法的栈，解题思路有两种
 * 1) 同步push和pop的过程，只是stackMin在push时需要判断。
 * 			stackData.push(value);
 * 			stackMin.push(stackMin.isEmpty() ? value : Math.min(value,stackMin.peek()));
 *
 * 2) 对stackMin维护非严格单调递减(非增)，在push和pop时都需要判断。好处是stackMin的占用稍微小。
 * 		public void push(int value){
 * 			stackData.push(value);
 * 			if (stackMin.isEmpty() || value <= stackMin.peek())  // 注意null
 * 				stackMin.push(value);
 *                }
 * 		public Integer pop(){
 * 			if (stackData.isEmpty())
 * 				return null;
 * 			int ans = stackData.pop();
 * 			if (stackMin.peek().equals(ans))
 * 				stackMin.pop();
 * 			return ans;
 */

import java.util.Stack;



public class Code05_GetMinStack {

	public static class MyStack1 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack1() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}

		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum <= this.getmin()) {
				this.stackMin.push(newNum);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			int value = this.stackData.pop();
			if (value == this.getmin()) {
				this.stackMin.pop();
			}
			return value;
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static class MyStack2 {
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public MyStack2() {
			this.stackData = new Stack<Integer>();
			this.stackMin = new Stack<Integer>();
		}


//		public void push(int value){
//			stackData.push(value);
//			stackMin.push(stackMin.isEmpty() ? value : Math.min(value,stackMin.peek()));
//		}
		public void push(int newNum) {
			if (this.stackMin.isEmpty()) {
				this.stackMin.push(newNum);
			} else if (newNum < this.getmin()) {
				this.stackMin.push(newNum);
			} else {
				int newMin = this.stackMin.peek();
				this.stackMin.push(newMin);
			}
			this.stackData.push(newNum);
		}

		public int pop() {
			if (this.stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			this.stackMin.pop();
			return this.stackData.pop();
		}

		public int getmin() {
			if (this.stackMin.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			return this.stackMin.peek();
		}
	}

	public static void main(String[] args) {
		MyStack1 stack1 = new MyStack1();
		stack1.push(3);
		System.out.println(stack1.getmin());
		stack1.push(4);
		System.out.println(stack1.getmin());
		stack1.push(1);
		System.out.println(stack1.getmin());
		System.out.println(stack1.pop());
		System.out.println(stack1.getmin());

		System.out.println("=============");

		MyStack1 stack2 = new MyStack1();
		stack2.push(3);
		System.out.println(stack2.getmin());
		stack2.push(4);
		System.out.println(stack2.getmin());
		stack2.push(1);
		System.out.println(stack2.getmin());
		System.out.println(stack2.pop());
		System.out.println(stack2.getmin());
	}

}
