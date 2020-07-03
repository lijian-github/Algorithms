package com.ljnt.search;


public class BinaryTreeDemo {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Node root = new Node(1, "宋江");
		Node node2 = new Node(2, "吴用");
		Node node3 = new Node(3, "卢俊义");
		Node node4 = new Node(4, "林冲");
		Node node5 = new Node(5, "关胜");
		root.setLeft(node2);
		root.setRight(node3);
		node3.setRight(node4);
		node3.setLeft(node5);
		BinaryTree binaryTree = new BinaryTree(root);
		binaryTree.preOrder();
		System.out.println();
		binaryTree.infixOrder();
		System.out.println();
		binaryTree.postOrder();

		System.out.println();
		Node finded=binaryTree.preOrderSearch(5);
		System.out.println(finded);

		Node finded1=binaryTree.infixOrderSearch(4);
		System.out.println(finded1);

		Node finded2=binaryTree.postOrderSearch(1);
		System.out.println(finded2);

	}

}

class BinaryTree {
	private Node root = null;

	public BinaryTree(Node root) {
		this.root = root;
	}

	public void preOrder() {
		if (root != null) {
			preOrder(root);
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	public void preOrder(Node poin) {
		System.out.print(poin + " ");
		if (poin.getLeft() != null) {
			preOrder(poin.getLeft());
		}
		if (poin.getRight() != null) {
			preOrder(poin.getRight());
		}
	}

	public Node preOrderSearch(int key) {
		if (root != null) {
			return preOrderSearch(root,key);
		} else {
			System.out.println("二叉树为空，无法遍历");
			return null;
		}
	}

	public Node preOrderSearch(Node poin,int key) {
		if (poin.getIndex()==key) {
			return poin;
		}
		if (poin.getLeft() != null) {
			Node finded=preOrderSearch(poin.getLeft(),key);
			if (finded != null) {
				return finded;
			}
		}
		if (poin.getRight() != null) {
			Node finded=preOrderSearch(poin.getRight(),key);
			if (finded != null) {
				return finded;
			}
		}
		return null;
	}

	public void infixOrder() {
		if (root != null) {
			infixOrder(root);
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	public void infixOrder(Node poin) {
		if (poin.getLeft() != null) {
			infixOrder(poin.getLeft());
		}
		System.out.print(poin + " ");
		if (poin.getRight() != null) {
			infixOrder(poin.getRight());
		}
	}

	public Node infixOrderSearch(int key) {
		if (root != null) {
			return infixOrderSearch(root,key);
		} else {
			System.out.println("二叉树为空，无法遍历");
			return null;
		}
	}

	public Node infixOrderSearch(Node poin,int key) {
		if (poin.getLeft() != null) {
			Node finded=infixOrderSearch(poin.getLeft(),key);
			if (finded != null) {
				return finded;
			}
		}

		if (poin.getIndex()==key) {
			return poin;
		}
		if (poin.getRight() != null) {
			Node finded=infixOrderSearch(poin.getRight(),key);
			if (finded != null) {
				return finded;
			}
		}
		return null;
	}

	public void postOrder() {
		if (root != null) {
			postOrder(root);
		} else {
			System.out.println("二叉树为空，无法遍历");
		}
	}

	public void postOrder(Node poin) {
		if (poin.getLeft() != null) {
			postOrder(poin.getLeft());
		}
		if (poin.getRight() != null) {
			postOrder(poin.getRight());
		}
		System.out.print(poin + " ");
	}

	public Node postOrderSearch(int key) {
		if (root != null) {
			return postOrderSearch(root,key);
		} else {
			System.out.println("二叉树为空，无法遍历");
			return null;
		}
	}

	public Node postOrderSearch(Node poin,int key) {
		if (poin.getLeft() != null) {
			Node finded=postOrderSearch(poin.getLeft(),key);
			if (finded != null) {
				return finded;
			}
		}
		if (poin.getRight() != null) {
			Node finded=postOrderSearch(poin.getRight(),key);
			if (finded != null) {
				return finded;
			}
		}

		if (poin.getIndex()==key) {
			return poin;
		}
		return null;
	}
}

class Node {
	private Node left;
	private Node right;
	private int index;
	private String data;

	public Node(int index, String data) {
		this.index = index;
		this.data = data;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "Node [index=" + index + ", data=" + data + "]";
	}

}
