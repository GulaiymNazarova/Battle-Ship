import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

import static java.lang.System.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        Random random = new Random();
        out.println("Welcome to Battle Ship! \nPlease enter your name:");
        String userName = sc.nextLine();
        // The field
        String[][] field = new String[9][9];
        String[][] fields = new String[9][9];
        grid(field);
        gridForUser(fields);
        placeShips(random, field);
        displayField(field);
        displayField(fields);
    }
    public static void grid(String[][] field) {
        field[0][0] = " ";
        field[0][1] = "A";
        field[0][2] = "B";
        field[0][3] = "C";
        field[0][4] = "D";
        field[0][5] = "E";
        field[0][6] = "F";
        field[0][7] = "G";

        for (int j = 1; j < 8; j++) {
            field[j][0] = String.valueOf(j);
        }
        for (int j = 1; j < 8; j++) {
            for (int i = 1; i < 8; i++) {
                field[j][i] = "~";
            }
        }
        for(int k = 0; k <= 8; k++)
        {
            field[k][8] = " ";
        }
        for(int k = 0; k <= 7; k++)
        {
            field[8][k] = " ";
        }
    }
    public static void gridForUser (String[][] fields) {
        fields[0][0] = " ";
        fields[1][0] = "A";
        fields[2][0] = "B";
        fields[3][0] = "C";
        fields[4][0] = "D";
        fields[5][0] = "E";
        fields[6][0] = "F";
        fields[7][0] = "G";

        for (int j = 1; j < 8; j++) {
            fields[0][j] = String.valueOf(j);
        }
        for (int j = 1; j < 8; j++) {
            for (int i = 1; i < 8; i++) {
                fields[j][i] = "~";
            }
        }
        for(int k = 0; k <= 8; k++)
        {
            fields[k][8] = " ";
        }
        for(int k = 0; k <= 7; k++)
        {
            fields[8][k] = " ";
        }
    }
    public static void displayField(String[][] name) {
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                out.print(name[j][i] + " ");
            }
            out.println();
        }
    }

    public static String[][] placeShips(Random random, String[][] field) {

        boolean placed = false;
        ArrayList<String> availableCells = new ArrayList<>();
        for (int row = 0; row <= 8; row++) {
            for (int col = 0; col <= 8; col++) {
                availableCells.add(row + "," + col);
            }
        }
        while (!placed) {
            int direction3 = directions(random);
            if (direction3 == 0) {
                int firstCellRow3 = random.nextInt(7) + 1;
                int firstCellColumn3 = random.nextInt(5) + 1;
                for (int hor3 = firstCellColumn3; hor3 <= firstCellColumn3 + 2; hor3++) {
                    field[firstCellRow3][hor3] = "#";
                }
                for (int i = firstCellRow3 - 1; i <= firstCellRow3 + 1; i++) {
                    for (int j = firstCellColumn3 - 1; j <= firstCellColumn3 + 3; j++) {
                        availableCells.remove(i + "," + j);
                    }
                }
                placed = true;

            } else if (direction3 == 1) {
                int firstCellRow3 = random.nextInt(5) + 1;
                int firstCellColumn3 = random.nextInt(7) + 1;
                for (int ver3 = firstCellRow3; ver3 <= firstCellRow3 + 2; ver3++) {
                    field[ver3][firstCellColumn3] = "#";
                }
                for (int i = firstCellRow3 - 1; i <= firstCellRow3 + 3; i++) {
                    for (int j = firstCellColumn3 - 1; j <= firstCellColumn3 + 1; j++) {
                        availableCells.remove(i + "," + j);
                    }
                }
                placed = true;
            }
        }
        for (int l = 0; l < 2; l++) {
            boolean placed2 = false;
            while (!placed2) {
                int direction2 = directions(random);
                String selectedCell = availableCells.get(random.nextInt(availableCells.size()));
                String[] parts = selectedCell.split(",");
                int firstCellRow2 = Integer.parseInt(parts[0]);
                int firstCellColumn2 = Integer.parseInt(parts[1]);

                if (direction2 == 0) {
                    if (firstCellColumn2 + 1 < 8 && firstCellColumn2 > 0 && firstCellRow2 > 0 && firstCellRow2 < 8 &&
                            availableCells.contains((firstCellRow2) + "," + (firstCellColumn2 + 1))) {
                        field[firstCellRow2][firstCellColumn2] = "*";
                        field[firstCellRow2][firstCellColumn2 + 1] = "*";

                        for (int i = firstCellRow2 - 1; i <= firstCellRow2 + 1; i++) {
                            for (int j = firstCellColumn2 - 1; j <= firstCellColumn2 + 2; j++) {
                                availableCells.remove(i + "," + j);
                            }

                            placed2 = true;
                        }
                    }
                }
                    else if (direction2 == 1) {
                        if (firstCellRow2 + 1 < 8 && firstCellRow2 > 0 && firstCellColumn2 > 0 && firstCellColumn2 < 8 &&
                                availableCells.contains((firstCellRow2 + 1) + "," + firstCellColumn2)) {

                            field[firstCellRow2][firstCellColumn2] = "*";
                            field[firstCellRow2 + 1][firstCellColumn2] = "*";

                            for (int i = firstCellRow2 - 1; i <= firstCellRow2 + 2; i++) {
                                for (int j = firstCellColumn2 - 1; j <= firstCellColumn2 + 1; j++) {
                                    availableCells.remove(i + "," + j);
                                }
                            }
                            placed2 = true;
                        }
                    }
                }
            }

            for (int o = 0; o < 4; o++) {
                boolean placed3 = false;
                while (!placed3) {
                    String selectedCell = availableCells.get(random.nextInt(availableCells.size()));
                    String[] parts = selectedCell.split(",");
                    int firstCellRow2 = Integer.parseInt(parts[0]);
                    int firstCellColumn2 = Integer.parseInt(parts[1]);

                    if (firstCellColumn2 < 8 && firstCellColumn2 > 0 && firstCellRow2 > 0 && firstCellRow2 < 8) {
                        field[firstCellRow2][firstCellColumn2] = "$";

                        for (int i = firstCellRow2 - 1; i <= firstCellRow2 + 1; i++) {
                            for (int j = firstCellColumn2 - 1; j <= firstCellColumn2 + 1; j++) {
                                availableCells.remove(i + "," + j);
                            }
                        }
                        placed3 = true;
                    }
                }
            }

            return field;

    }
    public static int directions (Random random){
        return random.nextInt(2);
    }


}