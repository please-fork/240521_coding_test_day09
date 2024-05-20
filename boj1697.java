// package boj1697;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈이의 현재 위치
        int K = Integer.parseInt(st.nextToken()); // 동생의 위치
        // System.out.println(N + " " + K);
        
        boolean[] visited = new boolean[100001]; // 방문 여부 배열
        Queue<Integer[]> queue = new LinkedList<>(); // BFS를 위한 큐
        
        Integer[] el = {N, 0}; // 시작 위치와 시간
        queue.offer(el); // 큐에 시작 위치 추가
        
        while (!queue.isEmpty()) {
            el = queue.poll();
            // System.out.println(el[0]);
            if (el[0] == K) { // 동생의 위치에 도달한 경우
                bw.write(el[1] + ""); // 걸린 시간 출력
                break;
            }
            if (el[0] < 0 || el[0] >= 100001 || visited[el[0]]) {
                continue; // 범위를 벗어나거나 이미 방문한 경우
            }
            visited[el[0]] = true; // 현재 위치 방문 처리
            
            Integer[] tmp = {el[0] - 1, el[1] + 1}; // 한 칸 뒤로 이동
            queue.offer(tmp);
            Integer[] tmp2 = {el[0] + 1, el[1] + 1}; // 한 칸 앞으로 이동
            queue.offer(tmp2);
            Integer[] tmp3 = {el[0] * 2, el[1] + 1}; // 두 배로 이동
            queue.offer(tmp3);
        }
        
        bw.flush();
        br.close();
        bw.close();
    }
}
