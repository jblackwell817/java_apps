import java.util.*;
import javafx.util.Pair; 

public class ConnectFour {
    public static void main(String [] args) {
        //initialise scanner and 8x8 grid
        Scanner sc = new Scanner(System.in);
        //ArrayList<String> grid = new ArrayList<>();
        List<List<String>> grid = new ArrayList<List<String>>();
        for (int i = 0; i < 8; i++) {
            ArrayList<String> newRow = new ArrayList<>();
            for (int j = 0; j < 8; j++){
                newRow.add("-");
            }
            grid.add(newRow);
          }
        boolean hasGameEnded = false;
        int player = 0; //keep track of whose turn it is

        //keep track of how many times a column has been used
        ArrayList<Integer> columnCounts = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            columnCounts.add(0);
          }
        // get the column for the next token and update the grid till a winner is found
        printCurrentState(grid);
        while (hasGameEnded == false){
            int column = getNextColumn(sc);
            Pair<List<List<String>>, Integer> p = updateGrid(grid, player, column, columnCounts, sc);
            grid = p.getKey();
            Integer chosenColumn = p.getValue();
            columnCounts.set(chosenColumn,columnCounts.get(chosenColumn) + 1);
            printCurrentState(grid);
            hasGameEnded = checkEndConditions(grid, player);
            player++;
        }
        sc.close();
        if (player % 2 == 0){
            System.out.println("Player 2 is the winner!");
        } else {
            System.out.println("Player 1 is the winner!");
        }
    }

    private static Pair<List<List<String>>, Integer> updateGrid(List<List<String>> currentGrid, int player, int column, ArrayList<Integer> columnCounts, Scanner sc) {
        List<List<String>> grid = currentGrid;
        //check that the space chosen is available
        //while (grid.get(7 - columnCounts.get(column)).get(column) != "-"){
        while (columnCounts.get(column) > 7){
            System.out.println("Space is not available. Please choose another column");
            column = getNextColumn(sc);
        }
        //update space with x or o depending on whose turn it is
        if (player % 2 == 0){
            grid.get(7 - columnCounts.get(column)).set(column,"x");
        } else {
            grid.get(7 - columnCounts.get(column)).set(column,"o");
        }
        return new Pair<List<List<String>>, Integer>(grid, column);
    }

    private static void printCurrentState(List<List<String>> currentGrid){
        for (int i = 0; i < 8; i++){
            System.out.println("");
            System.out.print("|");
            List<String> thisLine = currentGrid.get(i);
            for (int j = 0; j < 8; j++){
                System.out.print(" " + thisLine.get(j) + " |");
            }
            System.out.println("");
            System.out.print("---------------------------------");
        }
    }

    private static boolean checkEndConditions(List<List<String>> currentGrid, int player){
        String token; //determine whether to look for x's or o's
        if (player % 2 == 0){
            token = "x";
        } else {
            token = "o";
        }
        // horizontalCheck 
        for (int j = 0; j<5 ; j++ ){
            for (int i = 0; i<8; i++){
                if (currentGrid.get(i).get(j) == token && currentGrid.get(i).get(j+1) == token && currentGrid.get(i).get(j+2) == token && currentGrid.get(i).get(j+3) == token){
                    return true;
                }           
            }
        }
        // verticalCheck
        for (int i = 0; i<5 ; i++ ){
            for (int j = 0; j<8; j++){
                if (currentGrid.get(i).get(j) == token && currentGrid.get(i+1).get(j) == token && currentGrid.get(i+2).get(j) == token && currentGrid.get(i+3).get(j) == token){
                    return true;
                }           
            }
        }
        // ascendingDiagonalCheck 
        for (int i=3; i<8; i++){
            for (int j=0; j<5; j++){
                //System.out.println("i = " + i + " and j = " + j + " and their values are " + currentGrid.get(i).get(j) + currentGrid.get(i-1).get(j+1) + currentGrid.get(i-2).get(j+2) + " and " + currentGrid.get(i-3).get(j+3));
                if (currentGrid.get(i).get(j) == token && currentGrid.get(i-1).get(j+1) == token && currentGrid.get(i-2).get(j+2) == token && currentGrid.get(i-3).get(j+3) == token)
                    return true;
            }
        }
        // descendingDiagonalCheck
        for (int i=3; i<8; i++){
            for (int j=3; j<8; j++){
                if (currentGrid.get(i).get(j) == token && currentGrid.get(i-1).get(j-1) == token && currentGrid.get(i-2).get(j-2) == token && currentGrid.get(i-3).get(j-3) == token)
                    return true;
            }
        }
        return false;
    }

    private static int getNextColumn(Scanner sc){
        int column;
        do {
            System.out.println("Please choose a column between 0 and 7 to place your token in.");
            while (!sc.hasNextInt()) {
                System.out.println("That's not a number.");
                sc.next(); 
            }
            column = sc.nextInt();
        } while (column < 0 | column > 7);
        return column;
    }
}