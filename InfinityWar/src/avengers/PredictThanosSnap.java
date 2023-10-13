package avengers;

/**
 * Given an adjacency matrix, use a random() function to remove half of the
 * nodes.
 * Then, write into the output file a boolean (true or false) indicating if
 * the graph is still connected.
 *
 * Steps to implement this class main method:
 *
 * Step 1:
 * PredictThanosSnapInputFile name is passed through the command line as args[0]
 * Read from PredictThanosSnapInputFile with the format:
 * 1. seed (long): a seed for the random number generator
 * 2. p (int): number of people (vertices in the graph)
 * 2. p lines, each with p edges: 1 means there is a direct edge between two
 * vertices, 0 no edge
 *
 * Note: the last p lines of the PredictThanosSnapInputFile is an ajacency
 * matrix for
 * an undirected graph.
 *
 * The matrix below has two edges 0-1, 0-2 (each edge appear twice in the
 * matrix, 0-1, 1-0, 0-2, 2-0).
 *
 * 0 1 1 0
 * 1 0 0 0
 * 1 0 0 0
 * 0 0 0 0
 *
 * Step 2:
 * Delete random vertices from the graph. You can use the following pseudocode.
 * StdRandom.setSeed(seed);
 * for (all vertices, go from vertex 0 to the final vertex) {
 * if (StdRandom.uniform() <= 0.5) {
 * delete vertex;
 * }
 * }
 * Answer the following question: is the graph (after deleting random vertices)
 * connected?
 * Output true (connected graph), false (unconnected graph) to the output file.
 *
 * Note 1: a connected graph is a graph where there is a path between EVERY
 * vertex on the graph.
 *
 * Note 2: use the StdIn/StdOut libraries to read/write from/to file.
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 *
 * To write to a file use StdOut (here, isConnected is true if the graph is
 * connected,
 * false otherwise):
 * StdOut.setFile(outputfilename);
 * StdOut.print(isConnected);
 *
 * @author Yashas Ravi
 *         Compiling and executing:
 *         1. Make sure you are in the ../InfinityWar directory
 *         2. javac -d bin src/avengers/*.java
 *         3. java -cp bin avengers/PredictThanosSnap predictthanossnap.in
 *         predictthanossnap.out
 */

public class PredictThanosSnap {

    private boolean[] visited;

    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println("Execute: java PredictThanosSnap <INput file> <OUTput file>");
            return;
        }

        String predictThanosSnapInputFile = args[0];
        String predictThanosSnapOutputFile = args[1];

        // set the input file
        StdIn.setFile(predictThanosSnapInputFile);

        // read the seed
        long seed = StdIn.readLong();

        // read the number of people
        int p = StdIn.readInt();

        // read the adjacency matrix
        int[][] adjMatrix = new int[p][p];
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                adjMatrix[i][j] = StdIn.readInt();
            }
        }

        // set the output file
        StdOut.setFile(predictThanosSnapOutputFile);

        // randomly delete half of the vertices
        StdRandom.setSeed(seed);

        int[] deletedVertices = new int[p];

        for (int i = 0; i < p; i++) {
            if (StdRandom.uniform() <= 0.5) {
                deletedVertices[i] = 1;
            }
        }

        // remove the deleted vertices from the adjacency matrix
        for (int i = 0; i < p; i++) {
            for (int j = 0; j < p; j++) {
                if (deletedVertices[i] == 1 || deletedVertices[j] == 1) {
                    adjMatrix[i][j] = 0;
                }
            }
        }

        // create a new PredictThanosSnap object
        PredictThanosSnap predictThanosSnap = new PredictThanosSnap();

        // call function to check if the graph is connected
        boolean isConnected = predictThanosSnap.dfs(adjMatrix, 0, p);

        // output the result
        StdOut.print(isConnected);
    }

    public boolean dfs(int[][] adjMatrix, int vertex, int p) {
        if (this.visited == null) {
            this.visited = new boolean[p];
        }
        // mark the current vertex as visited
        this.visited[vertex] = true;

        // recursively visit all the vertices that are connected to the current
        for (int i = 0; i < adjMatrix.length; i++) {
            // if there is an edge between the current vertex and the vertex
            if (adjMatrix[vertex][i] == 1 && !this.visited[i]) {
                // recursively visit the vertex
                dfs(adjMatrix, i, p);
            }
        }

        // check if all the vertices have been visited
        for (int i = 0; i < this.visited.length; i++) {

            // if there is a vertex that has not been visited, return false
            if (!this.visited[i]) {
                return false;
            }
        }

        // if all the vertices have been visited, return true
        return true;
    }

}
