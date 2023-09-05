import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ForkJoinPool;

public class Example1 {
    public static void main(String[] args) throws SecurityException{
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(new PrintDoubledNumbers(new int[] {1,2,3,4,5,6,7,8,9}));
    }
}


class PrintDoubledNumbers extends RecursiveAction {
    private final int[] numbers;

    PrintDoubledNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    protected void compute() {
        if (numbers.length < 2) {
            for (int number : numbers) System.out.println(number * 2);
        } else {
            SubArrays array = new SubArrays(numbers);
            PrintDoubledNumbers task1 = new PrintDoubledNumbers(array.subArray1);
            PrintDoubledNumbers task2 = new PrintDoubledNumbers(array.subArray2);
            invokeAll(task1, task2);
        }
    }
}
