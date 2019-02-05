/* https://www.hackerrank.com/challenges/3d-surface-area/problem */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static boolean isOccupied(int[][] A, int x, int y, int z)
    {
        if (x < 0 || x >= A.length)
            return false;
        
        if (y < 0 || y >= A[x].length)
            return false;

        if (A[x][y] > z && z >= 0)
            return true;
        else
            return false;
    }

    // Complete the surfaceArea function below.
    static int surfaceArea(int[][] A) {
        int result = 0;

        for(int x=0; x<A.length; x++)
            for(int y=0; y<A[x].length; y++)
                for(int z=0; z<A[x][y]; z++)
                {
                    if(!isOccupied(A, x - 1, y, z))
                        result++;
                    if (!isOccupied(A, x + 1, y, z))
                        result++;
                    if (!isOccupied(A, x, y - 1, z))
                        result++;
                    if (!isOccupied(A, x, y + 1, z))
                        result++;
                    if (!isOccupied(A, x, y, z - 1))
                        result++;
                    if (!isOccupied(A, x, y, z + 1))
                        result++;
                }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] HW = scanner.nextLine().split(" ");

        int H = Integer.parseInt(HW[0]);

        int W = Integer.parseInt(HW[1]);

        int[][] A = new int[H][W];

        for (int i = 0; i < H; i++) {
            String[] ARowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < W; j++) {
                int AItem = Integer.parseInt(ARowItems[j]);
                A[i][j] = AItem;
            }
        }

        int result = surfaceArea(A);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}

