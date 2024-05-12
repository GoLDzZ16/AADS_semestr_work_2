import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Generating array for 10000 numbers
        int[] array = new int[10000];
        Random randomizer = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = randomizer.nextInt(10000);
        }

        // Creating SplayTree, inserting to structure
        long elapsedTime_inserting = 0;
        int iterations_inserting = 0;
        SplayTree splayTree = new SplayTree();
        for (int i = 0; i < array.length; i++) {
            long startTime_inserting = System.nanoTime();
            splayTree.insert(array[i]);
            long endTime_inserting = System.nanoTime();
            iterations_inserting += splayTree.getIterations();
            elapsedTime_inserting += (endTime_inserting - startTime_inserting);
        }
        System.out.println("Average time for inserting: " + (elapsedTime_inserting/10000 ) + ". Average count of iterations for inserting: " + (iterations_inserting/10000));


        // Searching 100 elements in SplayTree
        long elapsedTime_searching = 0;
        int iterations_searching = 0;
        for (int i = 0; i < 100; i++) {
            int number = array[randomizer.nextInt(10000 - 1)];
            long startTime_searching = System.nanoTime();
            splayTree.search(number);
            long endTime_searching = System.nanoTime();
            iterations_searching += splayTree.getIterations();
            elapsedTime_searching += (endTime_searching - startTime_searching);
        }
        System.out.println("Average time for searching: " + (elapsedTime_searching/100) + ". Average count of iterations for searching: " + (iterations_searching/100));


        // Deleting 1000 elements in SplayTree
        long elapsedTime_deleting = 0;
        int iterations_deleting = 0;
        for (int i = 0; i < 1000; i++) {
            int number = array[randomizer.nextInt(10000 - 1)];
            long startTime_deleting = System.nanoTime();
            splayTree.delete(number);
            long endTime_deleting = System.nanoTime();
            iterations_deleting += splayTree.getIterations();
            elapsedTime_deleting += (endTime_deleting - startTime_deleting);
        }
        System.out.println("Average time for deleting: " + (elapsedTime_deleting/1000) + ". Average count of iterations for deleting: " + (iterations_deleting/1000));
    }
}



