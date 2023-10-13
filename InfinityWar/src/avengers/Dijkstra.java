package avengers;

// this clas takes adjecency matrix and finds the shortest path from source to destination

public class Dijkstra {

  public static int[] dijkstra(int[][] graph, int source, int destination) {
    int[] dist = new int[graph.length];
    int[] prev = new int[graph.length];
    boolean[] visited = new boolean[graph.length];

    for (int i = 0; i < graph.length; i++) {
      dist[i] = Integer.MAX_VALUE;
      prev[i] = -1;
      visited[i] = false;
    }

    dist[source] = 0;

    for (int i = 0; i < graph.length; i++) {
      int u = minDistance(dist, visited);
      visited[u] = true;

      for (int v = 0; v < graph.length; v++) {
        if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
            && dist[u] + graph[u][v] < dist[v]) {
          dist[v] = dist[u] + graph[u][v];
          prev[v] = u;
        }
      }
    }

    int[] path = new int[graph.length];
    int i = 0;
    int j = destination;
    while (j != -1) {
      path[i] = j;
      j = prev[j];
      i++;
    }

    int[] path2 = new int[i];
    for (int k = 0; k < i; k++) {
      path2[k] = path[i - k - 1];
    }

    return path2;
  }

  private static int minDistance(int[] dist, boolean[] visited) {
    int min = Integer.MAX_VALUE;
    int minIndex = -1;

    for (int i = 0; i < dist.length; i++) {
      if (visited[i] == false && dist[i] <= min) {
        min = dist[i];
        minIndex = i;
      }
    }

    return minIndex;
  }

}
