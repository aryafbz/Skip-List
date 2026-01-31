import java.util.Random;

public class Main {
    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        Random random = new Random();

        int n = 1_000_000;
        int[] testValues = new int[n];


        for (int i = 0; i < n; i++) {
            testValues[i] = random.nextInt(Integer.MAX_VALUE);
        }

        long startInsert = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            skipList.insert(testValues[i]);
        }
        long endInsert = System.currentTimeMillis();
        System.out.println("Insert time for " + n + " elements: " + (endInsert - startInsert) + " ms");


        long startSearch = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            skipList.search(testValues[random.nextInt(n)]);
        }
        long endSearch = System.currentTimeMillis();
        System.out.println("Search time for " + n + " elements: " + (endSearch - startSearch) + " ms");


        long startDelete = System.currentTimeMillis();
        for (int i = 0; i < n; i++) {
            skipList.delete(testValues[i]);
            long endDelete = System.currentTimeMillis();
            System.out.println("Delete time for " + n + " elements: " + (endDelete - startDelete) + " ms");
        }
    }
}