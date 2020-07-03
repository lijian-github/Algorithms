package com.ljnt.recursion;

/**
 * @ Program       :  com.ljnt.recursion.Queue8
 * @ Description   :  数据结构经典递归算法——八皇后问题
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-21 10:42
 */
public class Queue8 {
    static int count=0;
    static int[] q8;//每一位代表第n个皇后在第n排的第q8[n]列
    public static void main(String[] args) {
        q8=new int[8];
        doQueue(0);
        System.out.println(count);


    }

    public static void doQueue(int n){
        if (n==8){
            count++;
            for (int i=0;i<8;i++){
                System.out.print(q8[i]+" ");
            }
            System.out.println();
            return;
        }else {
            for (int i=0;i<8;i++){
                q8[n]=i;
                if (doJudge(n)){
                    doQueue(n+1);
                }
            }
        }


    }

    /**
     * @param : [n]
     * @return : boolean
     * @throws :
     * @Description ：判断第n个皇后是否和前面n-1个皇后是否互相攻击
     * @author : lj
     * @date : 2020-4-21 11:02
     */
    public static boolean doJudge(int n) {
        for (int i = 0; i < n; i++) {
            if (q8[n]==q8[i]){//是否在同一列
                return false;
            }else if(Math.abs(n-i)==Math.abs(q8[n]-q8[i])){//是否在同一对角线上
                return false;
            }
        }
        return true;
    }

}
