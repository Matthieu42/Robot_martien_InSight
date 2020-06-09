package org.minesales.robotmartieninsight.service;

public class Promise<T> {

    OnResponse<T> promiseThen;
    OnError promiseCatch;

    public Promise<T> then(OnResponse<T> promiseThen) {
        this.promiseThen = promiseThen;
        return this;
    }

    public Promise<T> catchError(OnError promiseCatch) {
        this.promiseCatch = promiseCatch;
        return this;
    }


}
