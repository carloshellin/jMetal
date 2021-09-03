package org.uma.jmetal.algorithm.impl;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.Watcher;
import org.uma.jmetal.problem.Problem;

import java.util.List;

/**
 * Abstract class representing an evolutionary algorithm
 * @param <S> Solution
 * @param <R> Result
 *
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
@SuppressWarnings("serial")
public abstract class AbstractEvolutionaryAlgorithm<S, R>  implements Algorithm<R>{
  protected List<S> population;
  protected Problem<S> problem ;
  protected Watcher<R> watcher;

  public List<S> getPopulation() {
    return population;
  }
  public void setPopulation(List<S> population) {
    this.population = population;
  }

  public void setProblem(Problem<S> problem) {
    this.problem = problem ;
  }
  public Problem<S> getProblem() {
    return problem ;
  }

  @Override
  public void setWatcher(Watcher<R> watcher) {
    this.watcher = watcher;
  }

  public abstract void initProgress();

  public abstract void updateProgress();

  protected abstract boolean isStoppingConditionReached();

  protected abstract  List<S> createInitialPopulation() ;

  protected abstract List<S> evaluatePopulation(List<S> population);

  protected abstract List<S> selection(List<S> population);

  protected abstract List<S> reproduction(List<S> population);

  protected abstract List<S> replacement(List<S> population, List<S> offspringPopulation);

  @Override public abstract R getResult();

  @Override public void run() {
    List<S> offspringPopulation;
    List<S> matingPopulation;

    population = createInitialPopulation();
    population = evaluatePopulation(population);
    initProgress();
    while (!isStoppingConditionReached()) {
      matingPopulation = selection(population);
      offspringPopulation = reproduction(matingPopulation);
      offspringPopulation = evaluatePopulation(offspringPopulation);
      population = replacement(population, offspringPopulation);
      updateProgress();
      watcher.updateProgress(getResult());
    }
  }
}
