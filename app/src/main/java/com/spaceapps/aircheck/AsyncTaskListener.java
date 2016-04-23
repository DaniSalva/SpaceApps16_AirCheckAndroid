package com.spaceapps.aircheck;

/**
 * Created by dsalvador on 30/10/2015.
 */

public interface AsyncTaskListener<T>{
    void onInit();
    void onProgress(Integer progress);
    void onCancel();
    void onFinish(T result);
}
