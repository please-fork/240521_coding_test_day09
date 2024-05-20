// package boj2644;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine()); // 사람의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken()); // 촌수를 계산할 첫 번째 사람
        int b = Integer.parseInt(st.nextToken()); // 촌수를 계산할 두 번째 사람
        
        adj = new boolean[n + 1][n + 1]; // 인접 행렬 초기화
        int m = Integer.parseInt(br.readLine()); // 관계의 수
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 관계의 시작점
            int y = Integer.parseInt(st.nextToken()); // 관계의 끝점
            adj[x][y] = true; // 양방향 관계 설정
            adj[y][x] = true; // 양방향 관계 설정
        }
        
        visited = new boolean[n + 1]; // 방문 여부 배열 초기화
        // int result = dfs1(a, b, 0); // 재귀를 이용한 DFS
        // int result = dfs2(a, b); // 스택을 이용한 DFS
        int result = bfs(a, b); // 큐를 이용한 BFS
        bw.write(result == -1 ? "-1" : result + ""); // 결과 출력
        
        br.close();
        bw.close();
    }
    
    public static boolean[] visited; // 방문 여부 배열
    public static boolean[][] adj; // 인접 행렬
    
    // 재귀를 이용한 DFS
    public static int dfs1(int from, int to, int count) {
        if (from == to) { // 목적지에 도달한 경우
            return count; // 현재까지의 촌수를 반환
        }
        visited[from] = true; // 현재 노드를 방문 처리
        for (int i = 1; i < visited.length; i++) {
            if (!visited[i] && adj[from][i]) { // 방문하지 않았고 연결된 경우
                int result = dfs1(i, to, count + 1); // 재귀 호출로 다음 노드 방문
                if (result != -1) { // 목적지에 도달한 경우 결과 반환
                    return result;
                }
            }
        }
        return -1; // 목적지에 도달하지 못한 경우
    }
    
    // 스택을 이용한 DFS
    public static int dfs2(int from, int to) {
        Stack<Integer[]> stack = new Stack<>();
        Integer[] el = {from, 0}; 
        stack.push(el); // 시작 노드를 스택에 추가
        while (!stack.isEmpty()) {
            el = stack.pop();
            if (el[0] == to) { // 목적지에 도달한 경우
                return el[1]; // 현재까지의 촌수를 반환
            }
            // System.out.println(Arrays.toString(el));
            visited[el[0]] = true; // 현재 노드를 방문 처리
            for (int i = 1; i < visited.length; i++) {
                if (!visited[i] && adj[el[0]][i]) { // 방문하지 않았고 연결된 경우
                    Integer[] val = {i, el[1] + 1};
                    stack.push(val); // 다음 노드를 스택에 추가
                }
            }
        }
        return -1; // 목적지에 도달하지 못한 경우
    }
    
    // 큐를 이용한 BFS
    public static int bfs(int from, int to) {
        Queue<Integer[]> queue = new LinkedList<>();
        Integer[] el = {from, 0}; 
        queue.add(el); // 시작 노드를 큐에 추가
        while (!queue.isEmpty()) {
            el = queue.poll();
            if (el[0] == to) { // 목적지에 도달한 경우
                return el[1]; // 현재까지의 촌수를 반환
            }
            // System.out.println(Arrays.toString(el));
            visited[el[0]] = true; // 현재 노드를 방문 처리
            for (int i = 1; i < visited.length; i++) {
                if (!visited[i] && adj[el[0]][i]) { // 방문하지 않았고 연결된 경우
                    Integer[] val = {i, el[1] + 1};
                    queue.add(val); // 다음 노드를 큐에 추가
                }
            }
        }
        return -1; // 목적지에 도달하지 못한 경우
    }
}
