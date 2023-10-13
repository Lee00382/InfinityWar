package avengers;

/**
 * Given a starting event and an Adjacency Matrix representing a graph of all
 * possible
 * events once Thanos arrives on Titan, determine the total possible number of
 * timelines
 * that could occur AND the number of timelines with a total Expected Utility
 * (EU) at
 * least the threshold value.
 *
 *
 * Steps to implement this class main method:
 *
 * Step 1:
 * UseTimeStoneInputFile name is passed through the command line as args[0]
 * Read from UseTimeStoneInputFile with the format:
 * 1. t (int): expected utility (EU) threshold
 * 2. v (int): number of events (vertices in the graph)
 * 3. v lines, each with 2 values: (int) event number and (int) EU value
 * 4. v lines, each with v (int) edges: 1 means there is a direct edge between
 * two vertices, 0 no edge
 *
 * Note 1: the last v lines of the UseTimeStoneInputFile is an ajacency matrix
 * for a directed
 * graph.
 * The rows represent the "from" vertex and the columns represent the "to"
 * vertex.
 *
 * The matrix below has only two edges: (1) from vertex 1 to vertex 3 and, (2)
 * from vertex 2 to vertex 0
 * 0 0 0 0
 * 0 0 0 1
 * 1 0 0 0
 * 0 0 0 0
 *
 * Step 2:
 * UseTimeStoneOutputFile name is passed through the command line as args[1]
 * Assume the starting event is vertex 0 (zero)
 * Compute all the possible timelines, output this number to the output file.
 * Compute all the posssible timelines with Expected Utility higher than the EU
 * threshold,
 * output this number to the output file.
 *
 * Note 2: output these number the in above order, one per line.
 *
 * Note 3: use the StdIn/StdOut libraries to read/write from/to file.
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 *
 * To write to a file use StdOut:
 * StdOut.setFile(outputfilename);
 * //Call StdOut.print() for total number of timelines
 * //Call StdOut.print() for number of timelines with EU >= threshold EU
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/*.java
 * 3. java -cp bin avengers/UseTimeStone usetimestone.in usetimestone.out
 *
 * @author Yashas Ravi
 *
 */

public class UseTimeStone {

    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println("Execute: java UseTimeStone <INput file> <OUTput file>");
            return;
        }

        String useTimeStoneInputFile = args[0];
        String useTimeStoneOutputFile = args[1];

        // set the input file
        StdIn.setFile(useTimeStoneInputFile);

        // read the EU threshold
        int threshold = StdIn.readInt();
        StdIn.readLine();

        // read the number of events
        int numEvents = StdIn.readInt();
        StdIn.readLine();

        // read the EU values for each event
        int[][] euValues = new int[numEvents][2];

        for (int i = 0; i < numEvents; i++) {
            euValues[i][0] = StdIn.readInt();
            euValues[i][1] = StdIn.readInt();
            StdIn.readLine();
        }

        // read the adjacency matrix
        int[][] adjMatrix = new int[numEvents][numEvents];

        for (int i = 0; i < numEvents; i++) {
            for (int j = 0; j < numEvents; j++) {
                adjMatrix[i][j] = StdIn.readInt();
            }
            StdIn.readLine();
        }

        // set the output file
        StdOut.setFile("temp");

        // find all possible paths from vertex 0 to all other vertices of the graph
        // using DFS
        for (int i = 0; i < numEvents; i++) {
            boolean[] visited = new boolean[numEvents];
            int[] path = new int[numEvents];
            int pathIndex = 0;
            findAllPaths(adjMatrix, 0, i, visited, path, pathIndex);
        }

        // set input file to output file
        StdIn.setFile("temp");
        // get all the paths
        String[] paths = StdIn.readAllLines();

        // compute the total number of timelines
        int totalTimelines = paths.length;

        // set output file
        StdOut.setFile(useTimeStoneOutputFile);

        StdOut.println(totalTimelines);

        // compute the number of timelines with EU >= threshold EU
        int numTimelinesWithEU = 0;
        for (int i = 0; i < paths.length; i++) {
            String[] path = paths[i].split(" ");
            int eu = 0;
            for (int j = 0; j < path.length; j++) {
                eu += euValues[Integer.parseInt(path[j])][1];
            }
            if (eu >= threshold) {
                numTimelinesWithEU++;
            }
        }
        StdOut.println(numTimelinesWithEU);
    }

    // a function to find all paths from in a directed graph
    // using DFS and additively compute the EU for each path
    private static void findAllPaths(int[][] adjMatrix, int u, int d, boolean[] visited, int[] path, int pathIndex) {

        // mark the current node as visited and store it in path[]
        visited[u] = true;
        path[pathIndex] = u;
        pathIndex++;

        // if current vertex is same as destination, then print current path[]
        if (u == d) {
            for (int i = 0; i < pathIndex; i++) {
                StdOut.print(path[i] + " ");
            }
            StdOut.println();

        } else {
            // if current vertex is not destination
            // recur for all the vertices adjacent to this vertex
            for (int i = 0; i < adjMatrix.length; i++) {
                if (adjMatrix[u][i] == 1 && !visited[i]) {
                    findAllPaths(adjMatrix, i, d, visited, path, pathIndex);
                }
            }
        }

        // remove current vertex from path[] and mark it as unvisited
        pathIndex--;
        visited[u] = false;
    }
}
