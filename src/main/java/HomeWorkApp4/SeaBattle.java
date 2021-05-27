package HomeWorkApp4;

import java.util.Scanner;

public class SeaBattle {

    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;
    private static final int SHIP = 2;
    private static final int EMPTY = 0;
    private static final int MISS = 1;
    private static final int SHIPS_DECK_ON_BOARD = 10;
    static String playerName1 = "Player#1";
    static String playerName2 = "Player#2";
    static Scanner scanner = new Scanner(System.in);
    static int[][] board1 = new int[WIDTH][HEIGHT];
    static int[][] board2 = new int[WIDTH][HEIGHT];
    static int[][] monitor1 = new int[WIDTH][HEIGHT];
    static int[][] monitor2 = new int[WIDTH][HEIGHT];

    public static void main(String[] args) {

        System.out.println("Player#1, Введите свое имя:");
        playerName1 = scanner.nextLine();
        System.out.println("Player#2, Введите свое имя:");
        playerName2 = scanner.nextLine();
        placeShips(playerName1, board1);
        placeShips(playerName2, board2);
        while (true) {
            makeTurn(playerName1, monitor1, board2);
            if (isWin()) {
                break;
            }
            makeTurn(playerName2, monitor2, board1);
            if (isWin()) {
                break;
            }
        }

    }

    public static void placeShips(String playerName, int[][] board) {
        int deck = 4;
        while (deck >= 1) {
            System.out.println();
            System.out.println(playerName + ", Разместите свои " + deck + "-палубные корабли на поле:");
            System.out.println();
            printBoard(board);
            System.out.println("Введите координаты по оси Х:");
            int x = scanner.nextInt();
            System.out.println("Введите координаты по оси Y:");
            int y = scanner.nextInt();
            System.out.println("ВЫберите направление:");
            System.out.println("1. Вертикальное.");
            System.out.println("2. Горизонтальное.");
            int direction = scanner.nextInt();
            if (!isAvailable(x, y, deck, direction, board)){
                System.out.println("Не правильные координаты!");
                continue;
            }
            for (int i = 0; i < deck; i++) {
                if (direction == 1) {
                    board[x][y + i] = SHIP;
                } else {
                    board[x + i][y] = SHIP;
                }
            }
            deck--;
        }
    }

    public static void printBoard(int[][] board) {
        System.out.print("   ");
        for (char i = 'A'; i < 'A' + WIDTH; i++) {
            System.out.print(" " + i);
        }
        for (int i = 0; i < HEIGHT; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < WIDTH; j++) {
                if (board[j][i] == EMPTY) {
                    System.out.print("[] ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }
    }

    public static void makeTurn(String playerName, int[][] monitor, int[][] board) {
        while (true) {
            System.out.println(playerName + ", Сделайте свой ход.");
            System.out.print("   ");
            for (char i = 'A'; i < 'A' + WIDTH; i++) {
                System.out.print(" " + i);
            }
            for (int i = 0; i < HEIGHT; i++) {
                System.out.print(i + " ");
                for (int j = 0; j < WIDTH; j++) {
                    if (monitor[j][i] == EMPTY) {
                        System.out.print("[] ");
                    } else if (monitor[j][i] == MISS) {
                        System.out.print(". ");
                    } else {
                        System.out.print("X ");
                    }
                }
                System.out.println();
            }
            System.out.println("Введите координаты по оси Х:");
            int x = scanner.nextInt();
            System.out.println("Введите координаты по оси Y:");
            int y = scanner.nextInt();
            if (board[x][y] == 1) {
                System.out.println("Попал! Делайте еще один ход!");
                monitor[x][y] = SHIP;
            } else {
                System.out.println("Miss! Your opponents turn!");
                monitor[x][y] = MISS;
                break;
            }
        }
    }

    public static boolean isWin() {
        int counter1 = 0;
        for (int[] ints : monitor1) {
            for (int anInt : ints) {
                if (anInt == SHIP) {
                    counter1++;
                }
            }
        }

        int counter2 = 0;
        for (int[] ints : monitor2) {
            for (int anInt : ints) {
                if (anInt == SHIP) {
                    counter2++;
                }
            }
        }

        if (counter1 >= SHIPS_DECK_ON_BOARD) {
            System.out.println(playerName1 + " Победа!!!");
            return true;
        }
        if (counter2 >= SHIPS_DECK_ON_BOARD) {
            System.out.println(playerName2 + " Победа!!!");
            return true;
        }
        return false;
    }

    public static boolean isAvailable(int x, int y, int deck, int direction, int[][] board) {

        if (direction == 1) {
            if (y + deck > WIDTH) {
                return false;
            }
        }
        if (direction == 2){
            if (x + deck > HEIGHT){
                return false;
            }
        }

        while (deck!=0){
            for (int i = 0; i < deck; i++) {
                int xi = 0;
                int yi = 0;
                if (direction == 1){
                    yi = i;
                } else{
                    xi = i;
                }

                if (x + 1 + xi < HEIGHT && x + 1 + xi >= 0){
                    if (board[x + 1 + xi][y + yi]!=0){
                        return false;
                    }
                }
                if (x - 1 + xi < HEIGHT && x - 1 + xi >= 0){
                    if (board[x - 1 + xi][y + yi]!=0){
                        return false;
                    }
                }
                if (y + 1 + yi <WIDTH && y + 1 + yi >= 0){
                    if (board[x + xi][y + 1 + yi]!=0){
                        return false;
                    }
                }
                if (y - 1 + yi < WIDTH && y - 1 + yi >= 0){
                    if (board[x + xi][y - 1 + yi]!=0){
                        return false;
                    }
                }
            }
            deck--;
        }
        return true;
    }
}
