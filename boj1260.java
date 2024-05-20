// package boj1260;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점 번호
        // System.out.println(N + " " + M + " " + V);
        
        adj = new boolean[N+1][N+1]; // 인접 행렬 초기화
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 시작 정점
            int e = Integer.parseInt(st.nextToken()); // 끝 정점
            adj[s][e] = true; // 간선 정보 저장
            adj[e][s] = true; // 무향 그래프이므로 반대 방향도 저장
        }
        // System.out.println(Arrays.deepToString(adj));
        visited = new boolean[N+1]; // 방문 여부 확인 배열 초기화
        // dfs1(V);
        dfs2(V); // DFS(스택 이용)
        System.out.println();
        visited = new boolean[N+1]; // 방문 여부 배열 초기화
        bfs(V); // BFS(큐 이용)
        br.close();
    }
    
    public static boolean adj[][]; // 인접 행렬
    public static boolean visited[]; // 방문 여부 배열

    // 재귀를 이용한 DFS
    public static void dfs1(int node) {
        System.out.print(node + " ");
        visited[node] = true; // 현재 노드 방문 처리
        for (int i = 1; i < adj.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (adj[node][i]) {
                dfs1(i); // 재귀 호출로 다음 노드 방문
            }
        }
    }
    
    // 스택을 이용한 DFS
    public static void dfs2(int start) {
        Stack<Integer> stack = new Stack<>();
        stack.push(start); // 시작 노드를 스택에 추가
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (visited[node]) {
                continue;
            }
            System.out.print(node + " ");
            visited[node] = true; // 현재 노드 방문 처리
            for (int i = adj.length - 1; i >= 0; i--) {
                if (visited[i]) {
                    continue;
                }
                if (adj[node][i]) {
                    stack.push(i); // 다음 노드를 스택에 추가
                }
            }
        }
    }
    
    // 큐를 이용한 BFS
    public static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start); // 시작 노드를 큐에 추가
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (visited[node]) {
                continue;
            }
            System.out.print(node + " ");
            visited[node] = true; // 현재 노드 방문 처리
            for (int i = 1; i < adj.length; i++) {
                if (visited[i]) {
                    continue;
                }
                if (adj[node][i]) {
                    queue.add(i); // 다음 노드를 큐에 추가
                }
            }
        }
    }
}
