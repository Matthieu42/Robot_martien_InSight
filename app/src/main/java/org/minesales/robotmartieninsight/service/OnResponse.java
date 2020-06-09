package org.minesales.robotmartieninsight.service;

public interface OnResponse<T> {

    void execute(T t);
}
