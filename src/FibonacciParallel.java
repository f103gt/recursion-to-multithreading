import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class FibonacciParallel {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new Fibonacci(5)));
    }
}

class Fibonacci extends RecursiveTask<Integer> {
    int number;

    Fibonacci(int number) {
        this.number = number;
    }

    @Override
    protected Integer compute() {
        if (number < 2) return 1;
        Fibonacci task1 = new Fibonacci(number - 1);
        Fibonacci task2 = new Fibonacci(number - 2);
        task2.fork();
        return task1.compute() + task2.join();
    }

}
