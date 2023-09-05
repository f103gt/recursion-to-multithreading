public class SubArrays {
    public int[] subArray1;
    public int[] subArray2;

    SubArrays(int[] parentArray){
        int mid = parentArray.length / 2;
        this.subArray1 = new int[mid];
        this.subArray2 = new int[parentArray.length - mid];

        System.arraycopy(parentArray, 0, subArray1, 0, mid);
        System.arraycopy(parentArray, mid, subArray2, 0,parentArray.length - mid);
    }
}
