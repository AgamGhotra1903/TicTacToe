import java.util.Scanner;
import java.util.Random;

public class TicTacToe {

    private final String[][] gameBoard, placementBoard;
    private String player1, player2, otherPlayer,playerPositions,otherPlayerPositions,winner;
    private char XO;
    Scanner sc = new Scanner(System.in);
    Scanner scan = new Scanner(System.in);

    public TicTacToe(){
        gameBoard = new String[][]{{"  ", "|", " ", "|", "  "},
                {"--", "+", "-", "+", "--"},
                {"  ", "|", " ", "|", "  "},
                {"--", "+", "-", "+", "--"},
                {"  ", "|", " ", "|", "  "}};
        placementBoard = new String[][]{{"1 ", "|", "2", "|", " 3"},
                {"--", "+", "-", "+", "--"},
                {"4 ", "|", "5", "|", " 6"},
                {"--", "+", "-", "+", "--"},
                {"7 ", "|", "8", "|", " 9"}};
        player1 = null;
        player2 = null;
        otherPlayer = null;
        playerPositions = "";
        otherPlayerPositions = "";
        XO = ' ';
        winner = "";
    }

    public void gameLoop() {
        while(true){
            playerMoves(player1,"Player");
            if (!winner.equals(""))
                break;

            if (otherPlayerPositions.length() + playerPositions.length() == 9){
                winner = "Tie";
                break;
            }

            if (otherPlayer.equals("Computer")){
                Random rand = new Random();
                int compPosition = rand.nextInt(9) + 1;
                while(playerPositions.contains(Integer.toString(compPosition)) ||
                        otherPlayerPositions.contains(Integer.toString(compPosition)) ){
                    compPosition = rand.nextInt(9) + 1;
                }
                System.out.println("Waiting for the Computer.....");
                for (int i = Integer.MIN_VALUE; i < Integer.MAX_VALUE; i++){
                }//Time delay Loop
                System.out.println("The computer entered " + compPosition);
                System.out.println("The Game Board now looks like this: ");
                update(gameBoard, compPosition, "Computer");
                winner = checkWinner();
            }else
                playerMoves(player2,"Player2");
            if (!winner.equals(""))
                break;
        }
        showResult(winner);
    }

    public void playerMoves(String player,String user){
        int pos;
        System.out.println(player + ", please enter your placement(1-9):");
        pos = sc.nextInt();

        while(playerPositions.contains(Integer.toString(pos)) ||
                otherPlayerPositions.contains(Integer.toString(pos)) ||
                !(pos > 0 && pos < 10)){
            if(playerPositions.contains(Integer.toString(pos)) ||
                    otherPlayerPositions.contains(Integer.toString(pos)))
                System.out.println("The position has already been taken !");
            else
                System.out.println("Placement entered by you does not exist.");
            System.out.println("Please enter another placement.");
            pos = sc.nextInt();
        }

        System.out.println("The Game Board now looks like this: ");
        update(gameBoard, pos, user);
        winner = checkWinner();
    }

    public void showResult(String winner) {
        if(winner.equals("Tie")) {
            System.out.println("It's a TIE!");
            System.out.println("**************RESULT**************");
            System.out.println("Name   : " + player1);
            if (otherPlayer.equals("Player2"))
                System.out.println("Name   : " + player2);
            else
                System.out.println("Name   : Computer");
            System.out.println("RESULT : TIE");
        }
        else{
            if(winner.equals(player1)) {
                System.out.println("CONGRATULATIONS ! You won!!");
                System.out.println("**************RESULT**************");
                System.out.println("WINNER : " + player1);
            }
            else{
                System.out.println("SORRY! You lost");
                System.out.println("**************RESULT**************");
                if (otherPlayer.equals(player2))
                    System.out.println("WINNER : " + player2);
                else
                    System.out.println("WINNER : Computer");
            }
        }
    }

    public void update(String[][] gameBoard, int pos, String user) {
        char usedSymbol;
        if(user.equals("Player")){
            usedSymbol = XO;
            playerPositions += pos;
        }
        else{
            usedSymbol = (XO == 'X')? 'O':'X';
            otherPlayerPositions += pos;
        }

        switch(pos) {
            case 1:
                gameBoard[0][0] = usedSymbol + " ";
                printGameBoard(gameBoard);
                break;
            case 2:
                gameBoard[0][2] = Character.toString(usedSymbol);
                printGameBoard(gameBoard);
                break;
            case 3:
                gameBoard[0][4] = " " + usedSymbol;
                printGameBoard(gameBoard);
                break;
            case 4:
                gameBoard[2][0] = usedSymbol + " ";
                printGameBoard(gameBoard);
                break;
            case 5:
                gameBoard[2][2] = Character.toString(usedSymbol);
                printGameBoard(gameBoard);
                break;
            case 6:
                gameBoard[2][4] = " " + usedSymbol;
                printGameBoard(gameBoard);
                break;
            case 7:
                gameBoard[4][0] = usedSymbol + " ";
                printGameBoard(gameBoard);
                break;
            case 8:
                gameBoard[4][2] = Character.toString(usedSymbol);
                printGameBoard(gameBoard);
                break;
            case 9:
                gameBoard[4][4] = " " + usedSymbol;
                printGameBoard(gameBoard);
                break;
        }
        System.out.println();
    }

    public String checkWinner() {
        int[][] winningCombinations = { {1,2,3},{4,5,6},{7,8,9},
                                        {1,4,7},{2,5,8},{3,6,9},
                                        {1,5,9},{3,5,7}
                                        };
        for(int i = 0; i < winningCombinations.length; i++){
            if(playerPositions.contains(Integer.toString(winningCombinations[i][0])) &&
                    playerPositions.contains(Integer.toString(winningCombinations[i][1])) &&
                    playerPositions.contains(Integer.toString(winningCombinations[i][2]))){
                return player1;
            }
            if(otherPlayerPositions.contains(Integer.toString(winningCombinations[i][0])) &&
                    otherPlayerPositions.contains(Integer.toString(winningCombinations[i][1])) &&
                    otherPlayerPositions.contains(Integer.toString(winningCombinations[i][2]))){
                if (otherPlayer.equals("Player"))
                    return player2;
                else
                    return "Computer";
            }
        }
        return "";
    }

    public void XorO() {
        System.out.println("Choose between X and O :");
        XO = sc.next().charAt(0);
        if(XO == 'x'){
            XO = 'X';
        }else if(XO == 'o'){
            XO = 'O';
        }
    }

    public void compOrPlayer(){
        System.out.println("Whom do You want to Play against ?");
        System.out.println("1. Computer ");
        System.out.println("2. Player ");
        System.out.println("Enter the corresponding Number :");
        int n = sc.nextInt();
        if(n == 1){
            otherPlayer = "Computer";
        }else if(n == 2){
            System.out.println("Enter other Player's Name :");
            player2 = scan.nextLine();
            otherPlayer = "Player2";
        }
    }

    public static void printGameBoard(String[][] Board){
        for(int i = 0; i < Board.length; i++){
            for(int j = 0; j < Board[i].length; j++){
                System.out.print(Board[i][j]);
            }
            System.out.println();
        }
    }

    public void introduction(){
        System.out.println("\t\t\t\t\t\t\tTIC-TAC-TOE GAME");
        System.out.println("\t\t\t\t\t\t\t⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎⁎\n");
        System.out.println("Enter your Name :");
        player1 = sc.nextLine();
        System.out.println("Instructions:");
        System.out.println("•Choose either O\\X to play the game.");
        System.out.println("•Try and win by making a combination of O\\X diagonally, horizontally or vertically.");
        System.out.println("•Enter the placement of O\\X to place it on the Game Board.");
        System.out.println("•Placements of the Game Board :");
        printGameBoard(placementBoard);
    }

    public static void main(String[] args) {
        TicTacToe TTT = new TicTacToe();
        TTT.introduction();
        TTT.compOrPlayer();
        TTT.XorO();
        TTT.gameLoop();
    }

}
