package com.thaleswell.bankapp.data;

import com.thaleswell.bankapp.ds.List;
/**
 * The base interface from which all other application DAO (data access objects)
 * derive.
 * 
 * @author michael
 * @param <T> the data type for the DAO.
 */
public interface DataAccessObject<T> {
    /**
     * Saves the specified object as a new object in 
     * the data source and returns the object. The returned 
     * object may be slightly different, e.g. having an ID assigned by 
     * the data source.
     * 
     * @param t the object to be added to the data source
     * @return the object that was added or null if the object was unable to be added
     */
    public T create(T t);
}
