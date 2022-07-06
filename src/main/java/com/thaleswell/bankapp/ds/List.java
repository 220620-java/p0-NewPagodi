package com.thaleswell.bankapp.ds;

/**
 * The generic interface used for all list objects in this application.
 *
 * @param <T> The datatype of the objects stored in the list.
 */
public interface List<T> {
    /**
     * Adds an element to the end of the list.
     * 
     * @param t the object to be added into the list
     */
    public void add(T t);
    
    /**
     * Retrieves an element from a specified index.
     * 
     * @param index the index of the desired element
     * @return the element at the specified index
     */
    public T get(int index);
    
    /**
     * Removes the object at the specified index from the list and 
     * returns the object.
     * 
     * @param index the index of the object to be removed
     * @return the object that was removed
     */
    public T remove(int index);
    
    /**
     * Returns the number of elements currently in the list.
     * 
     * @return the number of elements in the list
     */
    public int size();
}
