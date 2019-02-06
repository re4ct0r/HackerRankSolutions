import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static boolean isReachable(int fromX, int fromY, int toX, int toY)
    {
        if (Math.abs(toY-fromY) % 2 == 1)
            return false;

        int verticalDistance = Math.abs(toY-fromY)/2;

        if (fromX % 2 == 0)
        {
            if (verticalDistance % 2 == 0)
            {
                if (toX % 2 == 1)
                    return false;
            }
            else
            {
                if (toX % 2 == 0)
                    return false;
            }
        } else
        {
            if (verticalDistance % 2 == 0)
            {
                if (toX % 2 == 0)
                    return false;
            } else
            {
                if (toX % 2 == 1)
                    return false;
            }
        }

        return true;
    }

    static int distance(int fromX, int fromY, int toX, int toY)
    {
        int verticalDistance = Math.abs(toY-fromY)/2;
        
        if (verticalDistance >= Math.abs(toX - fromX))
            return verticalDistance;

        return verticalDistance + (Math.abs(toX - fromX) - verticalDistance)/2;
    }

    // Complete the printShortestPath function below.
    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        // Print the distance along with the sequence of moves.

        if (!isReachable(j_start, i_start, j_end, i_end))
        {
            System.out.print("Impossible");
            return;
        }

        System.out.println(distance(j_start, i_start, j_end, i_end));

        int xPos = j_start;
        int yPos = i_start;
        int currentDistance = distance(xPos, yPos, j_end, i_end);

        while(currentDistance >= 0)
        {
            if (distance(xPos-1, yPos-2, j_end, i_end) + 1 == currentDistance)
            {
                System.out.print("UL");
                xPos = xPos-1;
                yPos = yPos-2;
            }
            else if (distance(xPos+1, yPos-2, j_end, i_end) + 1 == currentDistance)
            {
                System.out.print("UR");
                xPos = xPos+1;
                yPos = yPos-2;
            }
            else if (distance(xPos + 2, yPos, j_end, i_end) + 1 == currentDistance)
            {
                System.out.print("R");
                xPos = xPos+2;
            }
            else if (distance(xPos + 1, yPos+2, j_end, i_end) + 1 == currentDistance)
            {
                System.out.print("LR");
                xPos = xPos+1;
                yPos = yPos+2;
            }
            else if (distance(xPos - 1, yPos + 2, j_end, i_end) + 1 == currentDistance)
            {
                System.out.print("LL");
                xPos = xPos-1;
                yPos = yPos+2;
            }
            else if (distance(xPos - 2, yPos, j_end, i_end) + 1 == currentDistance)
            {
                System.out.print("L");
                xPos = xPos-2;
            }

            currentDistance--;
            if (currentDistance > 0)
                System.out.print(" ");
        }
        

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] i_startJ_start = scanner.nextLine().split(" ");

        int i_start = Integer.parseInt(i_startJ_start[0]);

        int j_start = Integer.parseInt(i_startJ_start[1]);

        int i_end = Integer.parseInt(i_startJ_start[2]);

        int j_end = Integer.parseInt(i_startJ_start[3]);

        printShortestPath(n, i_start, j_start, i_end, j_end);

        scanner.close();
    }
}

