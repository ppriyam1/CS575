import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class TSP {
    Stack<Integer> stack;

    public TSP() {
        stack = new Stack<Integer>();
    }

    int[] TSPNearestNeighbour(int distance_matrix[][]) {
        //Initalization
        int min, nodes_visited = 0, node, min_node = 0, no_of_nodes, r_node = 0,element; 
        no_of_nodes= (distance_matrix[1].length - 1);
        int Visted[] = new int[distance_matrix[1].length + 1];
        int result[] = new int[no_of_nodes];
        //we always visit the first node as its our start point
        stack.push(1);
        //element keeps track of the latest element pushed into the stack
        element=0;
        //result matrix stores the result or seies to visit node sequencially 
        result[r_node] = 1;
        r_node++;
        //Visited matrix keeps track of the nodes that are visited
        Visted[1] = 1;
        nodes_visited++;
        
        for(int i =0;i<=no_of_nodes;i++) {
            //we would inialize min as the highest Integer possible as every integer would be smaller than the max integer and thus would make easy in comparision
            min = Integer.MAX_VALUE;
            //flag is set so that to determine is there any node mwith min value
            boolean flag = false;
            for (int j = 0; j < no_of_nodes; j++) {
                //initalize node to the distance from the recentely visited node to other nodes
                node = distance_matrix[element][j];
                //it would find minimum if and only if node is not zero and node is not visited
                if (node != 0 && Visted[j+1] != 1) {
                    if (min > node) {
                        min = node;
                        flag = true;
                        min_node = j+1;
                        
                        
                    }
                }
            }

            if (flag) {
                //if flag is set to true that indicates they found minimum distance to the next node
                if (min_node != 0) {
                    stack.push(min_node);
                    element=min_node-1;
                    Visted[min_node] = 1;
                    nodes_visited++;
                    flag = false;
                    result[r_node] = min_node;
                    r_node++;
                    
                }
            }
        }
        //return the sequencial pattern of the nodes sorted by distance between them
        return result;
    }

    public static void main(String args[]) {
        int no_of_nodes;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes...");
        no_of_nodes = sc.nextInt();
        int distance_matrix[][] = new int[no_of_nodes + 1][no_of_nodes + 1];
        int result[] = new int[no_of_nodes];

        System.out.println("Enter the Distances between nodes");
        for (int i = 0; i < no_of_nodes; i++) {
            for (int j = 0; j < no_of_nodes; j++) {
                if (i == j) {
                    System.out.println("The distance value between nodes " + i + " and " + j + "  is 0 ");
                    distance_matrix[i][j] = 0;
                } else {
                    System.out.print("Enter the Distance value between nodes " + i + " and " + j + " ");
                    distance_matrix[i][j] = sc.nextInt();
                    // System.out.println();
                }
            }
            System.out.println();
        }

        System.out.println("Your Matrix is");
        for (int i = 0; i < no_of_nodes; i++) {
            for (int j = 0; j < no_of_nodes; j++) {
                System.out.print(distance_matrix[j][i] + "\t");
            }
            System.out.println();
        }
        TSP t = new TSP();
        result = t.TSPNearestNeighbour(distance_matrix);
        System.out.println("The order in which nodes should be visited are...");
        for (int i = 0; i < no_of_nodes; i++)
            System.out.println(result[i]);

    }
}