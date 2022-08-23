package org.uma.jmetal.algorithm;

import java.util.List;

public interface Watcher<Result> {
    void initProgress(Result result);
    void updateProgress(Result result);
    void updateEvaluations(List population);
}
