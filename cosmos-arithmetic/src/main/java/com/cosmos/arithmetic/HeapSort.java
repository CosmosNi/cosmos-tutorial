package com.cosmos.arithmetic;

/**
 * 堆排序
 * 1.将序列构建成大顶堆。
 * 2.将根节点与最后一个节点交换，然后断开最后一个节点。
 * 3.重复第一、二步，直到所有节点断开。
 *
 * 大顶堆：arr[i] >= arr[2i+1] && arr[i] >= arr[2i+2]
 *
 * 小顶堆：arr[i] <= arr[2i+1] && arr[i] <= arr[2i+2]
 */
public class HeapSort {

    public static void heapSort(int[] a) {
        System.out.println("开始排序");
        int arrayLength = a.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆

            buildMaxHeap(a, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap(a, 0, arrayLength - 1 - i);
            //System.out.println(Arrays.toString(a));
        }
    }

    private static void swap(int[] data, int i, int j) {
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    //对data数组从0到lastIndex建大顶堆
    private static void buildMaxHeap(int[] data, int lastIndex) {
        //从lastIndex处节点（最后一个节点）的父节点开始
        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {
                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }
                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    public static void printArray(int arr[]) {
        for (int k = 0; k < arr.length; k++) {
            System.out.print(arr[k] + "\t");
        }
    }

    public static void main(String[] args) {
        int[] data = {543, 23, 45, 65, 76, 1, 456, 7, 77, 88, 3, 9};
//        int[] data = {7, 6, 4, 8, 9};
        System.out.print("数组排序前：");
        printArray(data);
        System.out.print("\n");
        heapSort(data);
        System.out.print("排序后：");
        printArray(data);
    }

}