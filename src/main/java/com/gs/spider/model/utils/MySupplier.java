package com.gs.spider.model.utils;

/**
 * MySupplier
 */
@FunctionalInterface
public interface MySupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws Exception;
}
