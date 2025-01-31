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
        shots(field, fields, sc);
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
    public static void shots (String[][] field, String[][] fields, Scanner sc) {
        // hit +
        // mis -
        // sunk @
        boolean allShipsSunks = false;
        int count = 0;
        while (!allShipsSunks) {
            int numrow = 0;
            int numcol = 0;
            out.println("Input coordinates of ships. Choose from A-G and 1-7. Ex: A5 ");
            String usersInput = sc.nextLine().trim();
            clearConsole();
            out.println("You have chosed " + usersInput );
            if (usersInput.matches("[A-G][1-7]")) {
                char letter = usersInput.charAt(0);
                int row = change(letter, numrow);
                char number = usersInput.charAt(1);
                int column = change(number, numcol);
                switch (field[row][column]){
                    case "$":
                        out.println("ship sank");
                        fields[row][column] = "@";
                        //field[row][column] = ":";
                        count++;
                        break;
                    case "~":
                        out.println("You missed");
                        fields[row][column] = "-";
                        //field[row][column] = ":";
                        break;
                    case "*":
                        if(field[row+1][column].equals("*") || field[row - 1][column].equals("*") || field[row][column - 1].equals("*") ||
                                field[row][column + 1].equals("*")){
                            out.println("You hit");
                            fields[row][column] = "+";
                            field[row][column] = "+";
                        }
                        else if (field[row + 1][column].equals("+")) {
                            field[row][column] = "@";
                            fields[row][column] = "@";
                            fields[row + 1][column] = "@";
                            field[row + 1][column] = "@";
                            out.println("ship sank");
                            count++;
                        }
                        else if (field[row - 1][column].equals("+")) {
                            field[row][column] = "@";
                            fields[row][column] = "@";
                            fields[row - 1][column] = "@";
                            field[row - 1][column] = "@";
                            out.println("ship sank");
                            count++;
                        }
                        else if (field[row][column + 1].equals("+")) {
                            field[row][column] = "@";
                            field[row][column + 1] = "@";
                            fields[row][column] = "@";
                            fields[row][column + 1] = "@";
                            out.println("ship sank");
                            count++;
                        }
                        else if (field[row][column - 1].equals("+")) {
                            field[row][column] = "@";
                            field[row][column - 1] = "@";
                            fields[row][column] = "@";
                            fields[row][column - 1] = "@";
                            out.println("ship sank");
                            count++;
                        }
                    case "#":
                        if((field[row + 1][column].equals("#") && field[row - 1][column].equals("#")) || (field[row + 1][column].equals("#") && field[row - 1][column].equals("+")) || (field[row + 1][column].equals("+") && field[row - 1][column].equals("#"))){
                            fields[row][column] = "+";
                            field[row][column] = "+";
                            out.println("You hit");
                        }
                        else if(field[row + 1][column].equals("+") && field[row - 1][column].equals("+")){
                            fields[row][column] = "@";
                            fields[row + 1][column] = "@";
                            fields[row - 1][column] = "@";
                            field[row][column] = "@";
                            field[row + 1][column] = "@";
                            field[row - 1][column] = "@";
                            out.println("Ship sank");
                            count++;
                        }
                        else if((field[row][column + 1].equals("#") && field[row][column - 1].equals("#")) || (field[row][column + 1].equals("+") && field[row][column - 1].equals("#")) || (field[row][column + 1].equals("#") && field[row][column - 1].equals("+"))){
                            fields[row][column] = "+";
                            field[row][column] = "+";
                            out.println("You hit");
                        }
                        else if(field[row][column + 1].equals("+") && field[row][column - 1].equals("+")){
                            fields[row][column] = "@";
                            fields[row][column + 1] = "@";
                            fields[row][column - 1] = "@";
                            field[row][column] = "@";
                            field[row][column + 1] = "@";
                            field[row][column - 1] = "@";
                            out.println("Ship sank");
                            count++;
                        }

                        else if(field[row + 1][column].equals("#")) {
                            if((field[row + 2][column].equals("#")) || (field[row + 2][column].equals("+"))){
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }

                        }
                        else if(field[row + 1][column].equals("+")) {
                            if((field[row + 2][column].equals("#"))){
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                            else if(field[row + 2][column].equals("+")){
                                fields[row][column] = "@";
                                fields[row + 1][column] = "@";
                                fields[row + 2][column] = "@";
                                field[row][column] = "@";
                                field[row + 1][column] = "@";
                                field[row + 2][column] = "@";
                                out.println("Ship sank");
                                count++;
                            }

                        }
                        else if(field[row - 1][column].equals("#")) {
                            if((field[row -  2][column].equals("#")) || (field[row + 2][column].equals("+"))){
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                        }
                        else if(field[row - 1][column].equals("+")) {
                            if((field[row - 2][column].equals("#"))){
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                            else if(field[row - 2][column].equals("+")){
                                fields[row][column] = "@";
                                fields[row - 1][column] = "@";
                                fields[row - 2][column] = "@";
                                field[row][column] = "@";
                                field[row - 1][column] = "@";
                                field[row - 2][column] = "@";
                                out.println("Ship sank");
                                count++;
                            }
                        }
                        else if(field[row][column + 1].equals("#")) {
                            if ((field[row][column + 2].equals("#")) || (field[row][column + 2].equals("+"))) {
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                        }
                        else if(field[row][column + 1].equals("+")) {
                            if ((field[row][column + 2].equals("#"))) {
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                            else if(field[row][column + 2].equals("+")) {
                                fields[row][column] = "@";
                                fields[row][column + 1] = "@";
                                fields[row][column + 2] = "@";
                                field[row][column] = "@";
                                field[row][column + 1] = "@";
                                field[row][column + 2] = "@";
                                out.println("Ship sank");
                                count++;
                            }
                        }
                        else if(field[row][column - 1].equals("#")) {
                            if ((field[row][column - 2].equals("#")) || (field[row][column - 2].equals("+"))) {
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                        }
                        else if(field[row][column - 1].equals("+")) {
                            if ((field[row][column - 2].equals("#"))) {
                                fields[row][column] = "+";
                                field[row][column] = "+";
                                out.println("You hit");
                            }
                            else if(field[row][column - 2].equals("+")) {
                                fields[row][column] = "@";
                                fields[row][column - 1] = "@";
                                fields[row][column - 2] = "@";
                                field[row][column] = "@";
                                field[row][column - 1] = "@";
                                field[row][column - 2] = "@";
                                out.println("Ship sank");
                                count++;
                            }
                        }



                }


            } else {
                System.out.println("Try again. One letter A-G and one number 1-7.");
            }
            displayField(fields);
            if(count == 7){
                allShipsSunks = true;
            }
        }
    }


    public static int change (char name, int num) {
        if (name == 'A' || name == '1') {
            num = 1;
        } else if (name == 'B' || name == '2') {
            num = 2;
        } else if (name == 'C' || name == '3') {
            num = 3;
        } else if (name == 'D' || name == '4') {
            num = 4;
        } else if (name == 'E' || name == '5') {
            num = 5;
        } else if (name == 'F' || name == '6') {
            num = 6;
        } else if (name == 'G' || name == '7') {
            num = 7;
        }
        return num;
    }
    public static void clearConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }


}