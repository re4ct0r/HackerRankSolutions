import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the absolutePermutation function below.
    static int[] absolutePermutation(int n, int k) {
        int[] minusOneArray = new int[1];
        int[] result = new int[n];
        boolean[] used = new boolean[n];

        minusOneArray[0] = -1;

        for(int i=0; i<result.length; i++)
        {
            int elem1 = (i+1) - k;
            int elem2 = (i+1) + k;
            int elem = -1;

            if (elem1 < 1 && elem2 > n)
                return minusOneArray;
            else if (elem1 >= 1 && !used[elem1-1])
            {
                elem = elem1;
            } else if (elem2 <= n && !used[elem2-1])
            {
                elem = elem2;
            }
            else
                return minusOneArray;

            result[i] = elem;
            used[elem-1] = true;
        }

        return result;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String[] nk = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nk[0]);

            int k = Integer.parseInt(nk[1]);

            int[] result = absolutePermutation(n, k);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

