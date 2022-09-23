package class03;
/**
 * 两个队列配合实现栈。
 * 		private Queue<T> queue;
 * 		private Queue<T> help;
 * 		private Queue<T> tmp;
 *
 * 		public void transForPop(){
 * 			// 将栈的pop对象转移到help中。(queue不为空)
 * 			while (queue.size() > 1){
 * 				help.add(queue.poll());}
 * 			    tmp = queue;
 * 			    queue = help;
 * 			    help = tmp;* 		}
 *
 *      以上为关键代码，
 *      1) pop:  pop之前调用transForPop,则弹出help.poll()即可。
 *      2) peek:  peek之前调用transForPop,先记录help.peek()，在返回值之前queue.add(help.peek())即可。
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code07_TwoQueueImplementStack {

	public static class TwoQueueStack_xj<T> {
		private Queue<T> queue;
		private Queue<T> help;
		private Queue<T> tmp;
		public TwoQueueStack_xj(){
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}
		public void push(T value){
			queue.add(value);
		}
		public T pop(){
			if (queue.isEmpty())
				throw new RuntimeException("栈为空，无法pop");
			transForPop();
			return help.poll();
		}
		public T peek(){
			if(queue.isEmpty())
				throw new RuntimeException("栈为空，无法peek");
			transForPop();
			T ans = help.peek();
			queue.add(help.poll());
			return ans;
		}
		public void transForPop(){
			// 将栈的pop对象转移到help中。(queue不为空)
			while (queue.size() > 1){
				help.add(queue.poll());
			}
			tmp = queue;
			queue = help;
			help = tmp;
		}

		public boolean isEmpty(){
			return queue.isEmpty();
		}
	}

	public static class TwoQueueStack<T> {
		public Queue<T> queue;
		public Queue<T> help;

		public TwoQueueStack() {
			queue = new LinkedList<>();
			help = new LinkedList<>();
		}

		public void push(T value) {
			queue.offer(value);
		}

		public T pop() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			T ans = queue.poll();
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public T peek() {
			while (queue.size() > 1) {
				help.offer(queue.poll());
			}
			T ans = queue.poll();
			help.offer(ans);
			Queue<T> tmp = queue;
			queue = help;
			help = tmp;
			return ans;
		}

		public boolean isEmpty() {
			return queue.isEmpty();
		}

	}

	public static void main(String[] args) {
		System.out.println("test begin");
		TwoQueueStack<Integer> myStack = new TwoQueueStack<>();
//		TwoQueueStack_xj<Integer> myStack = new TwoQueueStack_xj<>();
		Stack<Integer> test = new Stack<>();
		int testTime = 1000000;
		int max = 1000000;
		for (int i = 0; i < testTime; i++) {
			if (myStack.isEmpty()) {
				if (!test.isEmpty()) {
					System.out.println("Oops");
				}
				int num = (int) (Math.random() * max);
				myStack.push(num);
				test.push(num);
			} else {
				if (Math.random() < 0.25) {
					int num = (int) (Math.random() * max);
					myStack.push(num);
					test.push(num);
				} else if (Math.random() < 0.5) {
					if (!myStack.peek().equals(test.peek())) {
						System.out.println("Oops");
					}
				} else if (Math.random() < 0.75) {
					if (!myStack.pop().equals(test.pop())) {
						System.out.println("Oops");
					}
				} else {
					if (myStack.isEmpty() != test.isEmpty()) {
						System.out.println("Oops");
					}
				}
			}
		}

		System.out.println("test finish!");

	}

}
