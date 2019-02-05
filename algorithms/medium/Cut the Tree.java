/* https://www.hackerrank.com/challenges/cut-the-tree/problem */

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    static int result = -1;

    static int n;

    static List<Integer> staticData;
    static List<List<Integer>> tree = new ArrayList<List<Integer>>();
    static int[] sums;

    public static void calculateSums(int nodeIndex, int parentNodeIndex)
    {
        int sum = 0;

        for(Integer childNodeIndex : tree.get(nodeIndex))
        {
            if (childNodeIndex != parentNodeIndex)
            {
                if (sums[childNodeIndex] == 0)
                {
                    calculateSums(childNodeIndex, nodeIndex);
                }

                sum += sums[childNodeIndex];
            }
        }

        sum += staticData.get(nodeIndex);

        sums[nodeIndex] = sum;
    }

    public static void traverseEdges(int nodeIndex, int parentNodeIndex) {

        for (Integer childNodeIndex : tree.get(nodeIndex)) {
            if (childNodeIndex != parentNodeIndex) {
                traverseEdges(childNodeIndex, nodeIndex);
            }
        }

        if (parentNodeIndex != -1)
        {
            int childNodeSum = sums[nodeIndex];
            int theOtherSum = sums[0] - childNodeSum;
            int absoluteDifference = Math.abs(childNodeSum - theOtherSum);

            if (result == -1 || absoluteDifference < result)
            {
                result = absoluteDifference;
            }
        }

    }

    public static int cutTheTree(List<Integer> data, List<List<Integer>> edges) {

        staticData = data;

        n = data.size();

        sums = new int[n];

        for(int i=0; i<n; i++)
        {
            tree.add(new ArrayList<Integer>());
        }

        for(List<Integer> edge : edges)
        {
            tree.get(edge.get(0)-1).add(edge.get(1)-1);
            tree.get(edge.get(1)-1).add(edge.get(0)-1);
        }

        calculateSums(0, -1);
        traverseEdges(0, -1);

        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> data = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
            .map(Integer::parseInt)
            .collect(toList());

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
            try {
                edges.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int result = Result.cutTheTree(data, edges);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

