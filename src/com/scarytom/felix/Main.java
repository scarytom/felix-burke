package com.scarytom.felix;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) {
        int[][] distances = new int[][] {
                {0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {7 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {8 , 9 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {5 , 11, 7 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {7 , 3 , 9 , 12, 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {10, 6 , 7 , 11, 5 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {2 , 8 , 5 , 4 , 7 , 6 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {8 , 12, 5 , 5 , 11, 13, 3 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {7 , 13, 12, 3 , 13, 10, 5 , 8 , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {15, 8 , 10, 20, 6 , 7 , 10, 12, 16, 0 , 0 , 0 , 0 , 0 , 0 , 0},
                {7 , 4 , 8 , 11, 1 , 4 , 8 , 10, 13, 5 , 0 , 0 , 0 , 0 , 0 , 0},
                {12, 6 , 10, 16, 3 , 7 , 10, 12, 16, 3 , 4 , 0 , 0 , 0 , 0 , 0},
                {8 , 2 , 9 , 13, 2 , 12, 9 , 10, 12, 3 , 3 , 4 , 0 , 0 , 0 , 0},
                {5 , 18, 7 , 6 , 14, 17, 6 , 5 , 4 , 20, 13, 20, 19, 0 , 0 , 0},
                {5 , 10, 6 , 3 , 11, 13, 3 , 5 , 4 , 15, 10, 15, 11, 4 , 0 , 0},
                {8 , 6 , 4 , 8 , 5 , 5 , 8 , 8 , 12, 6 , 4 , 6 , 6 , 10, 9 , 0},
        };

        for (int start = 0; start < distances.length; start++) {
            for (int dest = 0; dest < distances[start].length; dest++) {
                distances[start][dest] = distances[dest][start];
            }
        }

        final int startIdx = 2;
        final int finishIdx = 0;

        //3 11 5 8 15 7 6 10 13 2 16 1
        //3 8 15 7 16 6 5 12 2 10 11 1
        //3 8 15 7 2 6 12 5 16 10 11 1
        //3 15 16 10 11 5 2 6 12 8 7 1
        //3 15 8 7 10 12 16 6 13 5 11 1
        //3 7 15 8 12 5 13 16 6 10 11 1 ; this

        combinations(new int[] {1,4,5,6,7,9,10,11,12,14,15}, 10, 0, new int[10], (int[] route) -> {
            int distance = distances[startIdx][route[0]] + distances[route[9]][finishIdx] + calcDist(route, distances);
            if (distance == 60
//                    && (route[0] == 9 || route[0] == 14)
//                    && (route[5] == 2 || route[6] == 2 || route[7] == 2)
            ) {
                printRoute(startIdx, finishIdx, route);
            }
        });

    }

    private static int calcDist(int[] route, int[][] distances) {
        int distance = 0;
        for (int i = 0; i < route.length - 1; i++) {
            distance += distances[route[i]][route[i+1]];
        }
        return distance;
    }

    static void combinations(int[] arr, int len, int startPosition, int[] result, Consumer<int[]> c){
        if (len == 0){
//
            permutations(result.clone(), result.length, result.length, c);
            return;
        }
        for (int i = startPosition; i <= arr.length-len; i++){
            result[result.length - len] = arr[i];
            combinations(arr, len-1, i+1, result, c);
        }
    }

    static void printRoute(int startIdx, int endIdx, int[] route)
    {
        System.out.print((startIdx + 1) + " ");
        for (int location : route)  {
            System.out.print((location + 1) + " ");
        }
        System.out.print(endIdx + 1);
        System.out.println();
    }

    static void permutations(int[] a, int size, int n, Consumer<int[]> c) {
        if (size == 1)
            c.accept(a);

        for (int i=0; i<size; i++) {
            permutations(a, size-1, n, c);
            if (size % 2 == 1) {
                int temp = a[0];
                a[0] = a[size-1];
                a[size-1] = temp;
            } else {
                int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;
            }
        }
    }

}