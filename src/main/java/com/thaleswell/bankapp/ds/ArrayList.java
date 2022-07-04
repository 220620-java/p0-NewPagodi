package com.thaleswell.bankapp.ds;

public class ArrayList<T> implements List<T> {
    // Private data
    private T[] objectArray;
    private int totalUsed;
    
    public ArrayList() {
        this.objectArray = (T[]) new Object[4];
    }
    
    public ArrayList(int initialSize) {
        this.objectArray = (T[]) new Object[initialSize];
    }
    
    @Override
    public void add(T t) {
        // If the current array is full create a new array 50% larger
        // and copy the existing array to it.
        if (totalUsed == objectArray.length - 1) {
            int newLength = objectArray.length + objectArray.length / 2;

            T[] newObjectArray = (T[]) new Object[newLength];
            System.arraycopy(objectArray, 0, newObjectArray, 0, objectArray.length);

            objectArray = newObjectArray;
        }

        // Add the new item to the array and update the totalUsed count.
        objectArray[totalUsed] = t;
        totalUsed++;
    }
    
    
    @Override
    public T get(int index) {
        if (index>=0 && index < this.totalUsed) {
            return this.objectArray[index];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    
    @Override
    public T remove(int index) {
        // if the index is invalid, do nothing.
        if (index < 0 || index >= totalUsed) {
            return null;
        }

        // if the index is valid, starting at index, move all items
        // down 1, set the last item to null, and decrement totalUsed.
        T returnedObject = objectArray[index];
        for (int i = index; i < totalUsed; ++i) {
            objectArray[i] = objectArray[i + 1];
        }

        objectArray[totalUsed - 1] = null;
        --totalUsed;

        return returnedObject;
    }
    
    @Override
    public int size() {
        return totalUsed;
    }
}
