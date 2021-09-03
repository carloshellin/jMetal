package org.uma.jmetal.algorithm;

public interface Watcher<Result> {
    void updateProgress(Result result);
}
