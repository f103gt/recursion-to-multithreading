import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Example2 {
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        System.out.println(pool.invoke(new GetNumbersSum(new int[] {1,2,3,4,5,6,7,8})));
    }
}

class GetNumbersSum extends RecursiveTask<Integer> {
    private final int[] numbers;

    GetNumbersSum(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    protected Integer compute(){
        if(numbers.length <= 2){
            int sum = 0;
            for(int number : numbers) sum += number;
            return sum;
        }
        SubArrays array = new SubArrays(numbers);

        GetNumbersSum task1 = new GetNumbersSum(array.subArray1);
        GetNumbersSum task2 = new GetNumbersSum(array.subArray2);

        task2.fork();

        return task1.compute() + task2.join();
    }
}
