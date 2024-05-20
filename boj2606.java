// package boj2606;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 컴퓨터의 수
        int M = Integer.parseInt(br.readLine()); // 네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수
        // System.out.println(N + " " + M);

        adj = new boolean[N+1][N+1]; // 인접 행렬 초기화
        
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); // 시작 정점
            int e = Integer.parseInt(st.nextToken()); // 끝 정점
            adj[s][e] = true; // 간선 정보 저장
            adj[e][s] = true; // 무향 그래프이므로 반대 방향도 저장
        }
        // System.out.println(Arrays.deepToString(adj));
        
        visited = new boolean[N+1]; // 방문 여부 확인 배열 초기화
        dfs1(1); // 1번 컴퓨터부터 DFS 시작
        // dfs2(1); // 스택을 이용한 DFS
        // bfs(1); // BFS
        
        int count = 0;
        for (boolean v : visited) {
            if (v) {
                count++; // 방문한 컴퓨터 수를 카운트
            }
        }
        bw.write(count - 1 + ""); // 1번 컴퓨터를 제외한 수 출력
        bw.flush();
        br.close();
        bw.close();
    }
    
    public static boolean adj[][]; // 인접 행렬
    public static boolean visited[]; // 방문 여부 배열

    // 재귀를 이용한 DFS
    public static void dfs1(int node) {
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
