package class03;

/**
 * ppt:
 * 栈和队列的常见面试题
 * 1) 如何用栈结构实现队列结构
 * 2）如何用队列结构实现栈结构（
 * 这两种结构的应用实在是太多了，在刷题时我们会大量见到
 *
 */

/**
 * 笔记1：
 * 1)图的宽度优先 -- 队列实现。
 * 2)图的深度优先 -- 栈实现。
 * 3)队列和栈可以相互实现。
 *
 * 笔记2：
 * 栈实现队列
 * 1)定义push栈和pop栈
 * 2)push栈一次性倒数据到pop栈
 * 3）只有pop栈空才能倒数据。
 * 上述两个条件可以设计到一个方法中：
 * 		private void trans(){
 * 			if (stackPop.isEmpty()){ // 条件1：stackPop非空
 * 				while (!stackPush.isEmpty()){ // 条件2：push一次性转移到pop
 * 					stackPop.push(stackPush.pop());}
 * 					}
 * 		}
 *
 *
 * 笔记3：
 * 队列实现栈
 * 两个队列来回倒。
 * 1)A: -4321-> B:         依次加入1,2,3,4
 * 2)A: -4->    B: -321->  弹出4
 * 3)A:         B: -65321->   加入5，6
 * 4）A:-5321-> B: -6->      弹出6
 */

import com.sun.xml.internal.fastinfoset.tools.TransformInputOutput;

import java.util.Stack;

public class Code06_TwoStacksImplementQueue {


	public static class TwoStacksQueue {
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStacksQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		// push栈向pop栈倒入数据
		private void pushToPop() {
			if (stackPop.empty()) {
				while (!stackPush.empty()) {
					stackPop.push(stackPush.pop());
				}
			}
		}

		public void add(int pushInt) {
			stackPush.push(pushInt);
			pushToPop();
		}

		public int poll() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.pop();
		}

		public int peek() {
			if (stackPop.empty() && stackPush.empty()) {
				throw new RuntimeException("Queue is empty!");
			}
			pushToPop();
			return stackPop.peek();
		}
	}

	public static void main(String[] args) {
		TwoStacksQueue test = new TwoStacksQueue();
		test.add(1);
		test.add(2);
		test.add(3);
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
		System.out.println(test.peek());
		System.out.println(test.poll());
	}

}
