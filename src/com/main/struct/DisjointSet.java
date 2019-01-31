
package com.main.struct;

public class DisjointSet {
    
    private int size;
    private int[] parent, rank;
    
    public DisjointSet(int size){
        this.size = size;
        this.parent = new int[size];
        this.rank = new int[size];
        for(int i = 0; i < size; i++){
            parent[i] = i;
        }
    }
    
    public int find(int i){
        int last;
        while(parent[i] != i){
            last = i;
            i = parent[i];
            parent[last] = parent[i];
        }
        return i;
    }
    
    public boolean union(int i, int j){
        int pi = find(i);
        int pj = find(j);
        if(pi == pj){ return false; }
        if(rank[pj] > rank[pi]){
            int temp = pi;
            pi = pj;
            pj = temp;
        }
        parent[pj] = pi;
        if(rank[pi] == rank[pj]){ rank[pi]++; }
        return true;
    }
}
