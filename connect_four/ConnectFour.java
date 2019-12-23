import java.util.*;

public class ConnectFour {
    public static void main(String [] args) {
        //initialise scanner and grid
        Scanner sc = new Scanner(System.in);
        ArrayList<String> grid = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            grid.add("-");
          }
        boolean hasGameEnded = false;
        int player = 0;

        // get the column for the next token and update the grid till a winner is found
        while (hasGameEnded == false){
            //ensure that the column chosen is a legitimate move
            int column = sc.nextInt();
            grid = updateGrid(grid, player, column, sc);
            printCurrentState(grid);
            hasGameEnded = checkEndConditions(grid);
            player++;
        }
        sc.close();
        if (player % 2 == 0){
            System.out.println("Player 2 is the winner!");
        } else {
            System.out.println("Player 1 is the winner!");
        }
    }

    private static ArrayList<String> updateGrid(ArrayList<String> currentGrid, int player, int column, Scanner sc) {
        ArrayList<String> grid = currentGrid;
        while (grid.get(column) != "-"){
            System.out.println("Space in not available. Please choose another column");
            column = sc.nextInt();
        }
        if (player % 2 == 0){
            System.out.println(player + " is even");
            grid.set(column,"x");
        } else {
            System.out.println(player + " is odd");
            grid.set(column,"o");
        }
        return grid;
    }

    private static void printCurrentState(ArrayList<String> currentGrid){
        ArrayList<String> grid = currentGrid;
        System.out.println("");
        System.out.print("|");
        for (String element : grid){
            System.out.print(" " + element + " |");
        }
    }

    private static boolean checkEndConditions(ArrayList<String> currentGrid){
        ArrayList<String> grid = currentGrid;
        StringBuilder gridContents = new StringBuilder();
        for (String i: grid){
            gridContents.append(i);
        }
        boolean hasGameEnded = gridContents.toString().matches("(.*)x{3,}(.*)|(.*)o{3,}(.*)");
        return hasGameEnded;
    }
}