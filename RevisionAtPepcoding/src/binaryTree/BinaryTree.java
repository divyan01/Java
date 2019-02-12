package binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class BinaryTree {
	Node root;
	int size;

	private class Node {

		int data;
		Node left;
		Node right;

	}

	public BinaryTree(int[] arr) {

		Stack<Node> st = new Stack<>();

		for (int val : arr) {

			if (val == -1) {

				st.pop();

			} else {

				Node node = new Node();
				size++;
				node.data = val;
				if (st.size() == 0) {
					root = node;
				} else {

					if (st.peek().left == null) {

						st.peek().left = node;

					} else {

						st.peek().right = node;

					}

				}
				st.push(node);

			}

		}

	}

	public void display() {

		display(root);

	}

	private void display(Node node) {
		if (node == null) {
			return;
		}

		String str = "";

		str += node.left != null ? node.left.data + "->" : ".->";

		str += node.right != null ? node.data + "<- " + node.right.data : node.data + "<- .";
		System.out.println(str);
		display(node.left);
		display(node.right);

	}

	public int size1() {
		return size1(root);

	}

	private int size1(Node node) {
		if (node == null) {
			return 0;
		}

		int ls = size1(node.left);
		int rs = size1(node.right);

		return ls + rs + 1;
	}

	public int max() {
		return max(root);

	}

	private int max(Node node) {
		if (node == null) {
			return 0;
		}

		int ls = max(node.left);
		int rs = max(node.right);
		int mx = Math.max(ls, rs);

		return Math.max(mx, node.data);
	}

	public int min() {
		return min(root);

	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}

		int ls = min(node.left);
		int rs = min(node.right);
		int mx = Math.min(ls, rs);

		return Math.min(mx, node.data);
	}

	public int height() {
		return height(root);

	}

	private int height(Node node) {
		if (node == null) {
			return 0;
		}

		int ls = height(node.left);
		int rs = height(node.right);
		int mx = Math.max(ls, rs);

		return mx + 1;
	}

	public boolean find(int data) {
		return find(root, data);

	}

	private boolean find(Node node, int data) {
		if (node == null) {
			return false;
		}

		if (node.data == data) {
			return true;
		}
		boolean found = find(node.left, data);
		if (found == true) {
			return true;
		}
		boolean found1 = find(node.right, data);
		if (found1) {

			return true;

		}

		return false;

		/*
		 * boolean found=false;
		 * 
		 * found=found||node.data==data; found=found||find(node.left,data);
		 * found=found||find(node.right,data);
		 * 
		 * return found
		 */

	}

	public void removeLeafs() {

		removeLeafs(root, root.left);
		removeLeafs(root, root.right);

	}

	private void removeLeafs(Node parent, Node child) {

		if (child == null) {
			return;
		}

		if (child.left == null && child.right == null) {

			if (parent.left == child) {
				parent.left = null;
			} else {
				parent.right = null;
			}
			return;
		}

		removeLeafs(child, child.left);

		removeLeafs(child, child.right);

	}

	public void printsinglechild() {

		printsinglechild(root, root.left);
		printsinglechild(root, root.right);

	}

	private void printsinglechild(Node parent, Node child) {

		if (child == null) {
			return;
		}

		if (parent.left == child && parent.right == null) {
			System.out.println(child.data);

		} else if (parent.right == child && parent.left == null) {
			System.out.println(child.data);

		}

		printsinglechild(child, child.left);

		printsinglechild(child, child.right);

	}

	public void printpath(int lo, int hi) {

		printpath(root, lo, hi, 0, " ");

	}

	private void printpath(Node node, int lo, int hi, int sum, String st) {

		if (node.left == null && node.right == null) {
			sum += node.data;
			st += node.data;

			if (sum < hi && sum > lo) {
				System.out.println(st);
			}

			return;
		}

		printpath(node.left, lo, hi, sum + node.data, st + node.data + " ");
		printpath(node.right, lo, hi, sum + node.data, st + node.data + " ");
	}

	public ArrayList<Node> NodeToRoot(int data) {

		return NodeToRoot(root, data);

	}

	private ArrayList<Node> NodeToRoot(Node node, int data) {

		if (node == null) {
			ArrayList<Node> br = new ArrayList<>();
			return br;
		}

		if (node.data == data) {

			ArrayList<Node> br = new ArrayList<>();
			br.add(node);
			return br;

		}

		ArrayList<Node> myres = NodeToRoot(node.left, data);
		if (myres.size() > 0) {

			myres.add(node);
			return myres;

		}

		ArrayList<Node> myres1 = NodeToRoot(node.right, data);
		if (myres1.size() > 0) {

			// my = myres1;
			myres1.add(node);
			return myres1;

		}

		return new ArrayList<Node>();

	}

	private void printkdown(Node node, int k) {

		if (node == null) {
			return;
		}

		if (k == 0) {
			System.out.println(node.data);
		}
		printkdown(node.left, k - 1);
		printkdown(node.right, k - 1);

	}

	public void printkaway(int data, int k) {

		ArrayList<Node> path = NodeToRoot(data);
		printkdown(path.get(0), k);

		for (int i = 1; i < path.size() && i < k; i++) {

			Node n = path.get(i);
			Node nm = path.get(i - 1);

			if (n.left == nm) {

				printkdown(n.right, k - i - 1);

			} else {
				printkdown(n.left, k - i - 1);

			}

		}

		// if (path.size() >= k) {
		System.out.println(path.get(k).data);

		// }

	}

	public BinaryTree(int[] preorder, int[] inorder) {
		root = helperPreIn(0, preorder.length - 1, 0, inorder.length - 1, preorder, inorder);
	}

	private Node helperPreIn(int preStart, int preEnd, int inStart, int inEnd, int[] pre, int[] in) {
		if (preStart > preEnd || inStart > inEnd) {
			return null;
		}
		Node node = new Node();
		node.data = pre[preStart];
		this.size++;
		int pos = -1;
		for (int i = inStart; i <= inEnd; i++) {
			if (in[i] == node.data) {
				pos = i;
				break;
			}
		}
		int lhs = pos - inStart;
		node.left = helperPreIn(preStart + 1, preStart + lhs, inStart, pos - 1, pre, in);
		node.right = helperPreIn(preStart + lhs + 1, preEnd, pos + 1, inEnd, pre, in);
		return node;
	}

	public BinaryTree(int[] postorder, int[] inorder, int x) {
		root = helperPostIn(0, postorder.length - 1, 0, inorder.length - 1, postorder, inorder);
	}

	private Node helperPostIn(int postStart, int postEnd, int inStart, int inEnd, int[] postorder, int[] inorder) {
		if (postStart > postEnd || inStart > inEnd) {
			return null;
		}
		Node node = new Node();
		node.data = postorder[postEnd];
		this.size++;
		int pos = -1;
		for (int i = inStart; i <= inEnd; i++) {
			if (inorder[i] == node.data) {
				pos = i;
				break;
			}
		}

		int lhs = pos - inStart;

		node.left = helperPostIn(postStart, postStart + lhs - 1, inStart, pos - 1, postorder, inorder);
		node.right = helperPostIn(postStart + lhs, postEnd - 1, pos + 1, inEnd, postorder, inorder);

		return node;
	}

	class Pair {
		Node node;
		int wc = 0;

		public Pair(Node node, int wc) {
			this.node = node;
			this.wc = wc;
		}

	}

	public void preIter() {
		Stack<Pair> stack = new Stack<>();
		Pair pair = new Pair(root, 0);
		stack.push(pair);
		while (stack.size() != 0) {
			Pair tpair = stack.peek();
			if (tpair.wc == 0) {
				System.out.println(tpair.node.data);
				tpair.wc++;
			} else if (tpair.wc == 1) {
				if (tpair.node.left != null) {
					Pair left = new Pair(tpair.node.left, 0);

					stack.push(left);
				}
				tpair.wc++;
			} else if (tpair.wc == 2) {
				if (tpair.node.right != null) {
					Pair right = new Pair(tpair.node.right, 0);

					stack.push(right);
				}
				tpair.wc++;
			} else {
				stack.pop();
			}
		}
	}

	public void postIter() {
		Stack<Pair> stack = new Stack<>();
		Pair pair = new Pair(root, 0);
		stack.push(pair);
		while (stack.size() != 0) {
			Pair tpair = stack.peek();
			if (tpair.wc == 0) {
				if (tpair.node.left != null) {
					Pair left = new Pair(tpair.node.left, 0);
					stack.push(left);
				}
				tpair.wc++;
			} else if (tpair.wc == 1) {
				if (tpair.node.right != null) {
					Pair right = new Pair(tpair.node.right, 0);
					stack.push(right);
				}
				tpair.wc++;
			} else if (tpair.wc == 2) {
				System.out.println(tpair.node.data);
				tpair.wc++;
			} else {
				stack.pop();
			}
		}
	}

	public void inIter() {
		Stack<Pair> stack = new Stack<>();
		Pair pair = new Pair(root, 0);
		stack.push(pair);
		while (stack.size() != 0) {
			Pair tpair = stack.peek();
			if (tpair.wc == 0) {
				if (tpair.node.left != null) {
					Pair left = new Pair(tpair.node.left, 0);
					stack.push(left);
				}
				tpair.wc++;
			} else if (tpair.wc == 1) {
				System.out.println(tpair.node.data);
				tpair.wc++;
			} else if (tpair.wc == 2) {

				if (tpair.node.right != null) {
					Pair right = new Pair(tpair.node.right, 0);
					stack.push(right);
				}
				tpair.wc++;
			} else {
				stack.pop();
			}
		}
	}

	public boolean isBalanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
		}
		boolean lcheck = isBalanced(node.left);
		boolean rcheck = isBalanced(node.right);

		if (lcheck == false || rcheck == false) {
			return false;
		}

		int lheight = height(node.left);
		int rheight = height(node.right);

		if (lheight - rheight >= -1 && lheight - rheight <= 1) {
			return true;
		}
		return false;
	}

	public boolean isBalancedImprove() {
		return isBalancedImprove(root).balance;
	}

	private pair1 isBalancedImprove(Node node) {
		if (node == null) {
			return new pair1(0, true);
		}
		pair1 leftp = isBalancedImprove(node.left);
		pair1 rightp = isBalancedImprove(node.right);

		if (leftp.balance == false || rightp.balance == false) {
			return new pair1(Math.max(leftp.height, rightp.height) + 1, false);
		}

		if (leftp.height - rightp.height >= -1 && leftp.height - rightp.height <= 1) {
			return new pair1(Math.max(leftp.height, rightp.height) + 1, true);
		}

		return new pair1(Math.max(leftp.height, rightp.height) + 1, false);
	}

	public class pair1 {
		int height;
		boolean balance;

		pair1(int h, boolean bal) {
			this.height = h;
			this.balance = bal;
		}
	}

	public class pair2 {
		int height;
		int maxdiameter;

		pair2(int h, int d) {
			this.height = h;
			this.maxdiameter = d;
		}
	}

	public int diameter() {
		return diameter(root).maxdiameter;
	}

	private pair2 diameter(Node node) {
		if (node == null) {
			return new pair2(0, 0);
		}
		pair2 left = diameter(node.left);
		pair2 right = diameter(node.right);
		int myDia = left.height + right.height + 1;
		int max = Integer.MIN_VALUE;
		max = Math.max(myDia, Math.max(left.maxdiameter, right.maxdiameter));
		return new pair2(Math.max(left.height, right.height) + 1, max);
	}

	private class pair4 {
		int max;
		int min;
		boolean Isbalanced;
	}

	public boolean isBstbad() {
		return isBstbad(root);
	}

	private boolean isBstbad(Node node) {
		if (node == null) {
			return true;
		}
		boolean lans = isBstbad(node.left);
		boolean rans = isBstbad(node.right);
		if (lans == false || rans == false) {
			return false;
		}

		int lmax = max(node.left);
		int rmin = min(node.right);
		if (node.data > lmax && node.data < rmin) {
			return true;
		}
		return false;
	}

	public boolean isBst() {
		return isBst(root).Isbalanced;
	}

	private pair4 isBst(Node node) {
		if (node == null) {
			pair4 p = new pair4();
			p.Isbalanced = true;
			p.max = Integer.MIN_VALUE;
			p.min = Integer.MAX_VALUE;
			return p;
		}

		pair4 lpair = isBst(node.left);
		pair4 rpair = isBst(node.right);

		if (lpair.Isbalanced == false || rpair.Isbalanced == false) {
			pair4 p = new pair4();
			p.Isbalanced = false;
			return p;
		}

		pair4 mpair = new pair4();
		mpair.max = Math.max(lpair.max, Math.max(rpair.max, node.data));
		mpair.min = Math.min(lpair.min, Math.min(rpair.min, node.data));
		if (node.data > lpair.max && node.data < rpair.min) {
			mpair.Isbalanced = true;
		}

		return mpair;
	}

	private class pair5 {
		int max;
		int min;
		boolean Isbalanced;
		int data;
		int size;
	}

	public int largestBstNode() {
		return largestBstNode(root).data;
	}

	private pair5 largestBstNode(Node node) {
		if (node == null) {
			pair5 p = new pair5();
			p.Isbalanced = true;
			p.max = Integer.MIN_VALUE;
			p.min = Integer.MAX_VALUE;
			p.data = -1;
			p.size = 0;
			return p;
		}

		pair5 lpair = largestBstNode(node.left);
		pair5 rpair = largestBstNode(node.right);

		if (lpair.Isbalanced == false || rpair.Isbalanced == false) {
			pair5 p = new pair5();
			p.Isbalanced = false;
			p.data = lpair.size > rpair.size ? lpair.data : rpair.data;
			p.size = Math.max(lpair.size, rpair.size);
			return p;
		}

		pair5 mpair = new pair5();
		mpair.max = Math.max(lpair.max, Math.max(rpair.max, node.data));
		mpair.min = Math.min(lpair.min, Math.min(rpair.min, node.data));
		if (node.data > lpair.max && node.data < rpair.min) {
			mpair.Isbalanced = true;
			mpair.data = node.data;
			mpair.size = lpair.size + rpair.size + 1;
		} else {
			mpair.data = lpair.size > rpair.size ? lpair.data : rpair.data;
			mpair.Isbalanced = false;
			mpair.size = Math.max(lpair.size, rpair.size);
		}

		return mpair;
	}

	public void transform() {
		transform(root);
	}

	private void transform(Node node) {
		if (node == null) {
			return;
		}
		transform(node.left);
		transform(node.right);
		Node temp = node.left;
		Node newNode = new Node();
		newNode.data = node.data;
		newNode.left = temp;
		node.left = newNode;

	}

	public void printSpiral() {
		printSpiral(root);
	}

	public void printSpiral(Node node) {
		// addLast removeFirst
		LinkedList<Node> curr = new LinkedList<>();
		LinkedList<Node> next = new LinkedList<>();
		int level=0;
		curr.addLast(node);
		while (curr.size()>0) {
			Node rp = curr.removeFirst();
			System.out.println(rp.data);
			if (rp.left != null) {
				next.addLast(rp.left);
			}
			if (rp.right != null) {
				next.addLast(rp.right);
			}

			if (curr.size()==0) {
				curr = next;
				next = new LinkedList<>();
				level++;
			}
		}
	}

	public void transform2() {
		transform2(root);
	}

	private void transform2(Node node) {
		if (node == null) {
			return;
		}
		if (node.left != null) {
			if (node.left.data == node.data) {
				node.left = node.left.left;
			}
		}
		transform2(node.left);
		transform2(node.right);

	}
//	public void preorderIteratively() {  //not that good method
//
//		LinkedList<Node> s = new LinkedList<>();
//		// q add = add first
//		// q remove = remove first
//
//		s.addFirst(root);
//		while (s.size() != 0) {
//			Node node = s.removeFirst();
//			System.out.println(node.data);
//			if (node.right != null)
//				s.addFirst(node.right);
//			if (node.left != null)
//				s.addFirst(node.left);
//		}
//	}

}