package Algorithm;

public class AlgoPractice {

    // useful things because i dont wanna write python/javascript
    // Hello world
    /*
        public class main {
            public static void main(String[] args) {
                System.out.println("Hello World");
            }
        }
     */

    public static void main(String[] args) {
        int[][] array = new int[5][5];

        // init the array from 1 to 25 (inclusive)
        int counter = 1;
        for (int i =0; i < array.length; i++) {
            for (int j =0; j < array[i].length; j++) {
                array[i][j] = counter++;
            }
        }

        print1DArray(flatten(array));
    }

    // print entire 2d array
    public static void print2DArray(int[][] array) {
        for (int i =0; i < array.length; i++) {
            for (int j =0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    // print entire 1d array
    public static void print1DArray(int[] array) {
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    // array need to contain at least one element
    public static int[] flatten(int[][] array) {
        int[] result = new int[array.length*array[0].length];
        int counter = 0;
        for (int i =0; i < array.length; i++) {
            for (int j =0; j < array[i].length; j++) {
                result[counter++] = array[i][j];
            }
        }

        return result;
    }

    // print an arraylist
    // print a stack
    // print a queue
}
