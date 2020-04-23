import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;

public class TSP {
    Stack<Integer> stack;

    public TSP() {
        stack = new Stack<Integer>();
    }
    int findLowest(int distance_matrix[][], int current_element, int Visited[]){
        int no_of_nodes= (distance_matrix[1].length - 1), node_value,min_node=-1;
        //we would inialize min as the highest Integer possible as every integer would be smaller than the max integer and thus would make easy in comparision
        int min = Integer.MAX_VALUE;
        //flag is set so that to determine is there any node mwith min value
        boolean flag = false;

        for (int j = 0; j < no_of_nodes; j++) {
            //initalize node to the distance from the recentely visited node to other nodes
            node_value = distance_matrix[current_element][j];
            //it would find minimum if and only if node is not zero and node is not visited
            if (node_value != 0 && Visited[j] != 1) {
                if (min > node_value) {
                    min=node_value;
                    flag = true;
                    min_node = j;  
                }
            }
        }
        if(flag == true){
            return min_node;
        }
        return -1;
    }

    int[] TSPNearestNeighbour(int distance_matrix[][], int start_node) {
        //Initalization
        int  min_node = 0, no_of_nodes,current_element,cost=0; 
        no_of_nodes= (distance_matrix[1].length - 1);
        int Visited[] = new int[distance_matrix[1].length];
        int result[] = new int[no_of_nodes+3];
        
        //current_element keeps track of the latest current_element
        current_element=start_node;

        //result matrix stores the result or seies to visit node sequencially 
        result[0] = start_node;
    
        //Visited matrix keeps track of the nodes that are visited
        Visited[start_node] = 1;
        
        for(int i =1;i<no_of_nodes;i++) 
        {
            min_node = findLowest(distance_matrix, current_element, Visited);
            if (min_node != -1) 
            {
                cost +=distance_matrix[current_element][min_node];
                current_element=min_node;
                Visited[min_node] = 1;
                result[i] = min_node;
                 
            }
        }

        cost += distance_matrix[current_element][start_node];
        //return the sequencial pattern of the nodes sorted by distance between them
        result[no_of_nodes] = start_node;
        result[no_of_nodes+1] = cost;
        return result;
    }

    public static void main(String args[]) {
        int no_of_nodes;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes...");
        no_of_nodes = sc.nextInt();
        int distance_matrix[][] = new int[no_of_nodes + 1][no_of_nodes + 1];
        int result[] = new int[no_of_nodes];
        //------------------------------Taking inputs for distance matrix ------------------------------
        System.out.println("Enter the Distances between nodes");
        for (int i = 0; i < no_of_nodes; i++) {
            for (int j = i; j < no_of_nodes; j++) {
                if (i == j) {
                    System.out.println("The distance value between nodes " + i + " and " + j + "  is 0 ");
                    distance_matrix[i][j] = 0;
                } else {
                    System.out.print("Enter the Distance value between nodes " + i + " and " + j + " ");
                    distance_matrix[i][j] = sc.nextInt();
                    distance_matrix[j][i] = distance_matrix[i][j];
                    // System.out.println();
                }
            }
            System.out.println();
        }
        // -----------------------------------Printing the matrix------------------------------
        System.out.println("Your Matrix is");
        for (int i = 0; i < no_of_nodes; i++) {
            for (int j = 0; j < no_of_nodes; j++) {
                System.out.print(distance_matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("Enter the start node");
        int start_node = sc.nextInt();
        
        TSP t = new TSP();
        result = t.TSPNearestNeighbour(distance_matrix,start_node);
        System.out.println("The order in which nodes should be visited are...");
        
        for (int i = 0; i <= no_of_nodes; i++)
            System.out.println(result[i]);

        System.out.println("The optimal cost of the path is " + result[no_of_nodes+1]);

    }
}
