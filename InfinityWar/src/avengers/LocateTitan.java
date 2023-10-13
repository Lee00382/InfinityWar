package avengers;

/**
 *
 * Using the Adjacency Matrix of n vertices and starting from Earth (vertex 0),
 * modify the edge weights using the functionality values of the vertices that
 * each edge
 * connects, and then determine the minimum cost to reach Titan (vertex n-1)
 * from Earth (vertex 0).
 *
 * Steps to implement this class main method:
 *
 * Step 1:
 * LocateTitanInputFile name is passed through the command line as args[0]
 * Read from LocateTitanInputFile with the format:
 * 1. g (int): number of generators (vertices in the graph)
 * 2. g lines, each with 2 values, (int) generator number, (double) funcionality
 * value
 * 3. g lines, each with g (int) edge values, referring to the energy cost to
 * travel from
 * one generator to another
 * Create an adjacency matrix for g generators.
 *
 * Populate the adjacency matrix with edge values (the energy cost to travel
 * from one
 * generator to another).
 *
 * Step 2:
 * Update the adjacency matrix to change EVERY edge weight (energy cost) by
 * DIVIDING it
 * by the functionality of BOTH vertices (generators) that the edge points to.
 * Then,
 * typecast this number to an integer (this is done to avoid precision errors).
 * The result
 * is an adjacency matrix representing the TOTAL COSTS to travel from one
 * generator to another.
 *
 * Step 3:
 * LocateTitanOutputFile name is passed through the command line as args[1]
 * Use Dijkstra’s Algorithm to find the path of minimum cost between Earth and
 * Titan.
 * Output this number into your output file!
 *
 * Note: use the StdIn/StdOut libraries to read/write from/to file.
 *
 * To read from a file use StdIn:
 * StdIn.setFile(inputfilename);
 * StdIn.readInt();
 * StdIn.readDouble();
 *
 * To write to a file use StdOut (here, minCost represents the minimum cost to
 * travel from Earth to Titan):
 * StdOut.setFile(outputfilename);
 * StdOut.print(minCost);
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/*.java
 * 3. java -cp bin avengers/LocateTitan locatetitan.in locatetitan.out
 *
 * @author Yashas Ravi
 *
 */

public class LocateTitan {

    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println("Execute: java LocateTitan <INput file> <OUTput file>");
            return;
        }

        // WRITE YOUR CODE HERE
        // read filename from command line
        String locateTitanInputFile = args[0];
        String locateTitanOutputFile = args[1];

        // set input file
        StdIn.setFile(locateTitanInputFile);
        // set output file
        StdOut.setFile(locateTitanOutputFile);

        // get number of generators
        int generators = StdIn.readInt(); // number of gens
        StdIn.readLine(); // read the newline character

        // create 2d array of size generators x 2
        double[][] genInfo = new double[generators][2];

        // populate genInfo array with generator number and functionality value
        for (int i = 0; i < generators; i++) {
            genInfo[i][0] = StdIn.readInt();
            genInfo[i][1] = StdIn.readDouble();
            StdIn.readLine(); // read the newline character
        }

        // create 2d array of size generators x generators
        int[][] adjMatrix = new int[generators][generators];

        // populate adjMatrix array with edge values
        for (int i = 0; i < generators; i++) {
            for (int j = 0; j < generators; j++) {
                adjMatrix[i][j] = StdIn.readInt();
            }
            StdIn.readLine(); // read the newline character
        }

        // update adjMatrix array with edge values divided by functionality
        for (int i = 0; i < generators; i++) {
            for (int j = 0; j < generators; j++) {
                adjMatrix[i][j] = (int) (adjMatrix[i][j] / (genInfo[i][1] * genInfo[j][1]));
            }
        }

        // Use Dijkstra's Algorithm to find the path of minimum cost between Earth and
        // Titan
        int[] minCost = Dijkstra.dijkstra(adjMatrix, 0, generators - 1);

        // Output the array of minimum cost to travel from Earth to Titan
        // for (int i = 0; i < minCost.length; i++) {
        // StdOut.print(minCost[i] + " ");
        // }
        // StdOut.println();

        // calculate the total cost to travel from Earth to Titan
        int totalCost = 0;
        // iterate through the array of minimum cost find weight of each edge from
        // adjMatrix
        for (int i = 0; i < minCost.length - 1; i++) {
            totalCost += adjMatrix[minCost[i]][minCost[i + 1]];
        }

        // output the total cost to travel from Earth to Titan
        // StdOut.print("Total Cost:");
        StdOut.println(totalCost);

    }
}
