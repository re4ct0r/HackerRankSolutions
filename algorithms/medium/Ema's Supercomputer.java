import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static char[][] staticGrid;
    static int height, width;
    static int largestArea = 0;

    static char getCell(int x, int y)
    {
        if (x >= 0 && x < width && y >= 0 && y < height)
        {
            return staticGrid[x][y];
        }

        return 'B';
    }

    static void setCell(int x, int y, char value) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            staticGrid[x][y] = value;
        }
    }

    static int drawSecondCross(int x, int y)
    {
        int biggest = -1;
        for (int i = 0; i < 8; i++) {
            if (getCell(x - i, y) == 'G' &&
                getCell(x + i, y) == 'G' &&
                getCell(x, y - i) == 'G' &&
                getCell(x, y + i) == 'G')
            {
                biggest = i;
            }
            else
            {
                break;
            }
        }

        if (biggest == -1)
        {
            return 0;
        }
        else
        {
            return 4 * biggest + 1;
        }
    }

    static void handlePairOfPoints(int x, int y, int x2, int y2)
    {
        for(int i=0; i<8; i++)
        {
            if (getCell(x-i, y) == 'G' &&
                getCell(x+i, y) == 'G' &&
                getCell(x, y-i) == 'G' &&
                getCell(x, y+i) == 'G')
            {
                setCell(x+i, y, 'C');
                setCell(x-i, y, 'C');
                setCell(x, y-i, 'C');
                setCell(x, y+i, 'C');

                int firstArea = 4 * i + 1;
                int secondArea = drawSecondCross(x2, y2);

                if (firstArea * secondArea > largestArea) {
                    largestArea = firstArea * secondArea;
                }
            }
            else
            {
                break;
            }
        }

        for (int i = 0; i < 8; i++) {
            if (getCell(x - i, y) == 'C' &&
                getCell(x + i, y) == 'C' &&
                getCell(x, y - i) == 'C' &&
                getCell(x, y + i) == 'C') {

                setCell(x + i, y, 'G');
                setCell(x - i, y, 'G');
                setCell(x, y - i, 'G');
                setCell(x, y + i, 'G');
            }
        }
    }

    // Complete the twoPluses function below.
    static int twoPluses(String[] grid) {

        height = grid.length;
        width = grid[0].length();

        staticGrid = new char[width][height];

        for(int y=0; y<height; y++)
            for (int x = 0; x < width; x++)
                staticGrid[x][y] = grid[y].charAt(x);

        for (int y=0; y<height; y++)
            for (int x=0; x<width; x++)
                if (getCell(x, y) == 'G')
                    for (int y2=y; y2<height; y2++)
                        for (int x2=0; x2<width; x2++)
                            if ((y2 > y || x2 > x) && getCell(x2, y2) == 'G')
                                handlePairOfPoints(x, y, x2, y2);

        return largestArea;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        String[] grid = new String[n];

        for (int i = 0; i < n; i++) {
            String gridItem = scanner.nextLine();
            grid[i] = gridItem;
        }

        int result = twoPluses(grid);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

