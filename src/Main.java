import java.util.Scanner;
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to Battle Ship! \nPlease enter your name:");
        String userName = sc.nextLine();
        // The field
        String[][] field = new String[8][8];
        field[0][0] = " ";
        field[0][1] = "A";
        field[0][2] = "B";
        field[0][3] = "C";
        field[0][4] = "D";
        field[0][5] = "E";
        field[0][6] = "F";
        field[0][7] = "G";

        for(int j = 1; j < 8; j++)
        {
            field[j][0] = String.valueOf(j);
        }
        for(int j = 1; j < 8; j++)
        {
            for(int i = 1; i < 8; i++)
            {
                field[j][i] = "~";
            }
        }

        for(int j = 0; j < 8; j++)
        {
            for(int i = 0; i < 8; i++)
            {
                System.out.print(field[j][i] + " ");
            }
            System.out.println();
        }

    }
}