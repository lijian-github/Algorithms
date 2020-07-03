package com.ljnt.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ Program       :  com.ljnt.graph.Graph
 * @ Description   :  图
 * @ Author        :  lj
 * @ CreateDate    :  2020-6-3 20:51
 */
public class GraphDemo {
    public static void main(String[] args) {
        String[] sarr = {"1", "2", "3", "4", "5", "6", "7", "8"};
        Graph graph = new Graph(sarr);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 3, 1);
        graph.addEdge(1, 4, 1);
        graph.addEdge(3, 7, 1);
        graph.addEdge(4, 7, 1);
        graph.addEdge(2, 5, 1);
        graph.addEdge(2, 6, 1);
        graph.addEdge(5, 6, 1);
        graph.showGraph();
        graph.depthFirst();
        graph.breadthFirst();
    }

}


class Graph {
    String[] graphVertexarr;
    int vertexCount;
    int[][] adjacencyMatrix;
    boolean[] visited;

    public Graph(String[] graphVertexarr) {
        this.graphVertexarr = graphVertexarr;
        vertexCount = graphVertexarr.length;
        adjacencyMatrix = new int[vertexCount][vertexCount];
    }

    public void addEdge(int v1, int v2, int weight) {
        adjacencyMatrix[v1][v2] = weight;
        adjacencyMatrix[v2][v1] = weight;
    }

    public void showGraph() {
        for (int i = 0; i < vertexCount; i++) {
            for (int j = 0; j < vertexCount; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void depthFirst(int v,boolean[] visited){
        System.out.print(graphVertexarr[v]+"=>");
        visited[v]=true;
        for (int i=0;i<vertexCount;i++){
            if (adjacencyMatrix[v][i]>0&&!visited[i]){
                depthFirst(i,visited);
            }
        }

    }

    public void depthFirst(){
        //非连通图情况
        visited = new boolean[vertexCount];
        for (int i=0;i<vertexCount;i++){
            if (!visited[i]){
                depthFirst(i,visited);
            }
        }
        System.out.println();
    }

    public void breadthFirst(int v){
        LinkedList<Integer> queue=new LinkedList<>();
//        visited=new boolean[vertexCount];
        System.out.print(graphVertexarr[v]+"=>");
        queue.addLast(v);
        visited[v]=true;
        while (queue.size()>0){
            v=queue.pollFirst();
            for (int i=0;i<vertexCount;i++){
                if (adjacencyMatrix[v][i]>0&&!visited[i]){
                    System.out.print(graphVertexarr[i]+"=>");
                    queue.addLast(i);
                    visited[i]=true;
                }
            }
        }
    }

    public void breadthFirst(){
        //非连通图情况
        visited = new boolean[vertexCount];
        for (int i=0;i<vertexCount;i++){
            if (!visited[i]){
                breadthFirst(i);
            }
        }
        System.out.println();
    }

}
