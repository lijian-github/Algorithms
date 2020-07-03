package com.ljnt.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {

	public static void main(String[] args) {
		String Expression="1+((2+3)*4)-5";
//		String suffixExpression = "4 5 * 8 - 60 + 8 2 / +";
//		List<String> suffixlist=new ArrayList<String>();
//		String[] sua=suffixExpression.split(" ");
//		for (int i=0;i<sua.length;i++) {
//			suffixlist.add(sua[i]);
//		}
		System.out.println(getPoland(Expression));
		System.out.println(calculate(getPoland(Expression)));

	}
	public static List<String> getPoland(String expression){
		List<String> suffixlist=new ArrayList<String>();
		Stack<String> optStack=new Stack<>();
		String tnums="";
		for (int i=0;i<expression.length();i++) {
			String s=expression.substring(i, i+1);
			if (s.matches("\\d")) {
				tnums+=s;
				if (i==expression.length()-1||!expression.substring(i+1, i+2).matches("\\d")) {
					suffixlist.add(tnums);
					tnums="";
				}
			}else {
				if(optStack.size()==0||optStack.peek().equals("(")||s.equals("(")) {
					optStack.push(s);
				}else {
					if (s.equals(")")) {
						while(true) {
							if (optStack.peek().equals("(")){
								optStack.pop();
								break;
							}
							suffixlist.add(optStack.pop());
						}
					}else {
						if (compareOpt(optStack.peek().charAt(0), s.charAt(0))) {
							suffixlist.add(optStack.pop());
						}
						optStack.push(s);
					}
				}
			}
		}
		while(true) {
			if (optStack.size()==0) {
				break;
			}
			suffixlist.add(optStack.pop());
		}
		return suffixlist;
		
	}
	public static boolean compareOpt(int a,int b) {
		return (a=='*'||a=='/')||((a=='+'||a=='-')&&(b=='+'||b=='-'));
	}
	
	public static int calculate(List<String> sl) {
		Stack<Integer> numStack=new Stack<>();
		for (String s : sl) {
			if (s.matches("\\d+")) {
				numStack.push(Integer.parseInt(s));
			}else {
				int a=numStack.pop();
				int b=numStack.pop();
				int res=operation(a, b, s.charAt(0));
				numStack.push(res);
			}
		}
		return numStack.pop();
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
