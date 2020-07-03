package com.ljnt.recursion;

/**
 * @ Program       :  com.ljnt.recursion.MiGong
 * @ Description   :  数据结构经典递归算法——迷宫问题
 * @ Author        :  lj
 * @ CreateDate    :  2020-4-21 9:09
 */
public class MiGong {
    public static void main(String[] args) {
        int [][] map=getMap();
        for (int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
        goMigong(map,1,1);
        for (int i=0;i<8;i++){
            for(int j=0;j<7;j++){
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }

    }

    private static int[][] getMap(){
        int[][] map=new int[8][7];
        for (int i=0;i<7;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        for (int i=0;i<8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        map[3][1]=1;map[3][2]=1;
        return map;
    }

    public static boolean goMigong(int[][] map,int a,int b) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[a][b] == 0) {
                map[a][b] = 2;
                if (goMigong(map, a + 1, b)) {
                    return true;
                } else if (goMigong(map, a, b + 1)) {
                    return true;
                } else if (goMigong(map, a - 1, b)) {
                    return true;
                } else if (goMigong(map, a, b - 1)) {
                    return true;
                } else {
                    map[a][b] = 3;
                    return false;
                }
            }
            return false;
        }
    }

}
