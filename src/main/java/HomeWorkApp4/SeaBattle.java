package HomeWorkApp4;

import java.util.Random;

public class SeaBattle {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SHIPS_COUNT = 10;
    private static final int SHIP = 1000;
    private static final int EMPTY = 0;
    private static final int BATTLESHIP = 1; // 4-хпалубный линкор
    private static final int CRUISER = 2; // 3-хпалубный крейсер
    private static final int DESTROYER = 3; // 2-хпалубный эсминец
    private static final int TORPEDO_BOAT = 4; // 1-апалубный торпедный катер

    public static void main(String[] args) {

        boolean win = play();
        if (win) {
            System.out.println("Поздравляю, вы выиграли!");
        } else {
            System.out.println("Вы проиграли");
        }

    }

    private static boolean play() {
        int[][] board = generateBoard();
 //       int[] ships = generateShips();
        printBoard(board);
        return true;
    }

    private static void printBoard(int[][] board) {
        System.out.print("   ");
        for (char i = 'A'; i < 'A' + WIDTH; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 0; i < HEIGHT; i++) {
            System.out.printf("%3d", i);
            for (int j = 0; j < WIDTH; j++) {
                System.out.print(getColorCode(board[i][j]));
                switch (board[i][j]) {
                    case EMPTY:
                        System.out.print(" .");
                        break;
                    case SHIP:
                        System.out.print(" x");
                        break;
                    default:
                        System.out.printf("%2d", board[i][j]);
                }
                System.out.print(ANSI_RESET);
            }
            System.out.println();
        }
    }

    private static String getColorCode(int n) {
        switch (n) {
            case EMPTY:
                return ANSI_WHITE;
            case BATTLESHIP:
                return ANSI_RED;
            case CRUISER:
                return ANSI_BLUE;
            case DESTROYER:
                return ANSI_CYAN;
            case TORPEDO_BOAT:
                return ANSI_GREEN;
            default:
                return ANSI_PURPLE;
        }
    }

    //   private static int[] generateShips() {
 //       int[] fourDeckShip = new int[4];
 //       int[] threeDeckShip = new int[3];
 //       int[] doubleDeckShip = new int[2];
 //       int[] singleDeckShip = new int[1];
 //   }

    private static int[][] generateBoard() {
        int[][] board = placeShips();
        return calculateShipsAround(board);
    }

    private static int[][] placeShips() {
        int[][] board = new int[HEIGHT][WIDTH];
        Random random = new Random();
        int ships = SHIPS_COUNT;
        while (ships > 0) {
            int x, y;
            do {
                x = random.nextInt(WIDTH);
                y = random.nextInt(HEIGHT);
            } while (board[x][y] == SHIP);
            board[x][y] = SHIP;
            ships--;
        }
        return board;
    }

    private static int[][] calculateShipsAround(int[][] board) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                if (board[i][j] == SHIP) {
                    continue;
                }
                board[i][j] = calculateShipAroundCell(board, i, j);
            }
        }
        return board;
    }

    private static int calculateShipAroundCell(int[][] board, int i, int j) {
        int sCount = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (k < 0 || k >= HEIGHT || l < 0 || l >= WIDTH) {
                    continue;
                }
                if (board[k][l] == SHIP) {
                    sCount++;
                }
            }
        }
        return sCount;
    }


}
