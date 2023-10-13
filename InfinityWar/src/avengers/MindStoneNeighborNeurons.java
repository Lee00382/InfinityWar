package avengers;

/**
 * Given a Set of Edges representing Vision's Neural Network, identify all of
 * the
 * vertices that connect to the Mind Stone.
 * List the names of these neurons in the output file.
 *
 *
 * Steps to implement this class main method:
 *
 * Step 1:
 * MindStoneNeighborNeuronsInputFile name is passed through the command line as
 * args[0]
 * Read from the MindStoneNeighborNeuronsInputFile with the format:
 * 1. v (int): number of neurons (vertices in the graph)
 * 2. v lines, each with a String referring to a neuron's name (vertex name)
 * 3. e (int): number of synapses (edges in the graph)
 * 4. e lines, each line refers to an edge, each line has 2 (two) Strings: from
 * to
 *
 * Step 2:
 * MindStoneNeighborNeuronsOutputFile name is passed through the command line as
 * args[1]
 * Identify the vertices that connect to the Mind Stone vertex.
 * Output these vertices, one per line, to the output file.
 *
 * Note 1: The Mind Stone vertex has out degree 0 (zero), meaning that there are
 * no edges leaving the vertex.
 *
 * Note 2: If a vertex v connects to the Mind Stone vertex m then the graph has
 * an edge v -> m
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
 * //Call StdOut.print() for EVERY neuron (vertex) neighboring the Mind Stone
 * neuron (vertex)
 *
 * Compiling and executing:
 * 1. Make sure you are in the ../InfinityWar directory
 * 2. javac -d bin src/avengers/*.java
 * 3. java -cp bin avengers/MindStoneNeighborNeurons mindstoneneighborneurons.in
 * mindstoneneighborneurons.out
 *
 * @author Yashas Ravi
 *
 */

public class MindStoneNeighborNeurons {

    public static void main(String[] args) {

        if (args.length < 2) {
            StdOut.println("Execute: java MindStoneNeighborNeurons <INput file> <OUTput file>");
            return;
        }

        // WRITE YOUR CODE HERE
        // read file names from command line
        String mindStoneNeighborNeuronsInputFile = args[0];
        String mindStoneNeighborNeuronsOutputFile = args[1];

        // Set the input file.
        StdIn.setFile(mindStoneNeighborNeuronsInputFile);

        // Set the output file.
        StdOut.setFile(mindStoneNeighborNeuronsOutputFile);

        // Read the number of vertices(neurons) in the graph.
        int v = StdIn.readInt();
        StdIn.readLine();

        // next v lines are the names of the vertices(neurons)
        String[] vertexNames = new String[v];
        for (int i = 0; i < v; i++) {
            vertexNames[i] = StdIn.readString();
            StdIn.readLine();
        }

        // Read the number of edges(synapses) in the graph.
        int e = StdIn.readInt();
        StdIn.readLine();

        // next e lines are the edges(synapses) in the graph
        String[][] edgeNames = new String[e][2];
        for (int i = 0; i < e; i++) {
            edgeNames[i][0] = StdIn.readString();
            edgeNames[i][1] = StdIn.readString();
            StdIn.readLine();
        }

        // find the vertex with out degree 0 (zero)
        // for all edges check if they have an out vertex
        // if they do not have an out vertex then they are the Mind Stone
        String mindStone = "";
        for (int i = 0; i < v; i++) {
            boolean isMindStone = true;
            for (int j = 0; j < e; j++) {
                if (edgeNames[j][0].equals(vertexNames[i])) {
                    isMindStone = false;
                    break;
                }
            }
            if (isMindStone) {
                mindStone = vertexNames[i];
                break;
            }
        }

        // find all the vertices that connect to the Mind Stone
        for (int i = 0; i < e; i++) {
            if (edgeNames[i][1].equals(mindStone)) {
                StdOut.println(edgeNames[i][0]);
            }
        }

    }
}
