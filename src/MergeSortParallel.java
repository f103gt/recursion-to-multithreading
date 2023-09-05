import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class MergeSortParallel {
}

class MergeSort extends RecursiveAction {
    private final int[] numbers;

    MergeSort(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    protected void compute() {
        if(numbers.length > 1){
            SubArrays array = new SubArrays(numbers);

            MergeSort task1 = new MergeSort(array.subArray1);
            MergeSort task2 = new MergeSort(array.subArray2);

            invokeAll(task1,task2);

            this.numbers = merge(array.subArray1,array.subArray2,this.numbers);
        }
    }

    private void merge(int[] first, int[] second) {
        int size = first.length + second.length;
        int j = 0, k = 0;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            if (j < first.length && k < second.length) {
                if (first[j] < second[k]) {
                    result[i] = first[j];
                    j++;
                } else {
                    result[i] = second[k];
                    k++;
                }
            } else {
                if (j < first.length) {
                    result[i] = first[j];
                    j++;
                } else {
                    result[i] = second[k];
                    k++;
                }
            }
        }
        return result;
    }
}
