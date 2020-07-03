package com.ljnt.stack;

import java.util.Scanner;

public class Calculator {

	public static void main(String[] args) {


//		ArrayStack stack = new ArrayStack(4);
//		String key = "";
//		boolean loop = true; // 控制是否退出菜单
//		Scanner scanner = new Scanner(System.in);
//		while (loop) {
//			System.out.println("show: 表示显示栈");
//			System.out.println("exit: 退出程序");
//			System.out.println("push: 表示添加数据到栈(入栈)");
//			System.out.println("pop: 表示从栈取出数据(出栈)");
//			System.out.println("请输入你的选择");
//			key = scanner.next();
//			switch (key) {
//			case "show":
//				stack.list();
//				break;
//			case "push":
//				System.out.println("请输入一个数");
//				int value = scanner.nextInt();
//				stack.push(value);
//				break;
//			case "pop":
//				try {
//					int res = stack.pop();
//					System.out.printf("出栈的数据是 %d\n", res);
//				} catch (Exception e) {
//					System.out.println(e.getMessage());
//				}
//				break;
//			case "exit":
//				scanner.close();
//				loop = false;
//				break;
//			default:
//				break;
//			}
//		}
//		System.out.println("程序退出~~~");

		String expression = "7*2*2-5+1-15+3*4";
		ArrayStack asnum=new ArrayStack(10);
		ArrayStack asopt=new ArrayStack(10);
		String tnum="";
		for (int i=0;i<expression.length();i++) {
			char ch=expression.charAt(i);
			if (!isOpt(ch)) {
				tnum+=ch;
				if (i+1==expression.length()||isOpt(expression.charAt(i+1))) {
					int num=Integer.parseInt(tnum);
					System.out.println(num);
					asnum.push(num);
					tnum="";
				}
			}else {
				if (!asopt.isEmpty()) {
					if (compareOpt(asopt.getTop(),ch)) {
						int a=asnum.pop();
						int b=asnum.pop();
						int opt=asopt.pop();
						int s=operation(a, b, opt);
						asnum.push(s);
						asopt.push(ch);
						System.out.println("运算"+s);
					}else {
						asopt.push(ch);
					}
				}else {
					asopt.push(ch);
				}
				System.out.println(ch);
			}

		}

		while (!asopt.isEmpty()) {
			int a=asnum.pop();
			int b=asnum.pop();
			int opt=asopt.pop();
			int s=operation(a, b, opt);
			asnum.push(s);
			System.out.println("运算"+s);
		}
		System.out.println("结果："+asnum.pop());



	}

	public static boolean isOpt(int c) {
		return c=='*'||c=='/'||c=='+'||c=='-';
	}

	public static boolean compareOpt(int a,int b) {
		return (a=='*'||a=='/')||((a=='+'||a=='-')&&(b=='+'||b=='-'));
	}

	public static int operation(int a,int b,int opt) {
		int s=0;
		switch (opt) {
			case '+':
				s=a+b;
				break;
			case '-':
				s=b-a;
				break;
			case '*':
				s=a*b;
				break;
			case '/':
				s=b/a;
				break;
			default:
				break;
		}
		return s;
	}


}

class ArrayStack{
	int top=-1;
	public int lenght=0;
	Integer[] stack;

	public ArrayStack(int lenght) {
		this.lenght=lenght;
		stack=new Integer[lenght];
	}

	public boolean isFull() {
		return top==lenght-1;
	}

	public boolean isEmpty() {
		return top==-1;
	}

	public int getTop() {
		return stack[top];
	}

	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		stack[++top]=value;
	}

	public Integer pop() {
		if (isEmpty()) {
			System.out.println("栈空");
			return null;
		}
		return stack[top--];
	}

	public void list(){
		if (isEmpty()) {
			System.out.println("栈空");
			return ;
		}
		for (int i=0;i<=top;i++) {
			System.out.print(stack[i]+" ");
		}
		System.out.println();
	}


}

