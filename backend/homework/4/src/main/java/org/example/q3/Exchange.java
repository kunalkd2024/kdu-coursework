package org.example.q3;

public class Exchange {
    public <T> void swap(T[] arr, int firstIndex, int secondIndex){
        T temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
