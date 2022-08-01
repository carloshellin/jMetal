package org.uma.jmetal.algorithm.singleobjective.particleswarmoptimization;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.AlgorithmBuilder;
import org.uma.jmetal.problem.Problem;
import org.uma.jmetal.problem.doubleproblem.DoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;

/**
 * Created by ajnebro on 10/12/14.
 */
public class StandardPSOBuilder implements AlgorithmBuilder<Algorithm<DoubleSolution>> {
  /**
   * Builder class
   */
  private DoubleProblem problem;
  private int objectiveId;
  private int swarmSize;
  private int maxIterations;
  private int numberOfParticlesToInform;  
  private SolutionListEvaluator<DoubleSolution> evaluator;

  /**
   * Builder constructor
   */
  
  public StandardPSOBuilder(DoubleProblem problem, int numberOfParticlesToInform) {
    this.problem = problem;
    this.objectiveId = 0;
    swarmSize = 100;
    maxIterations = 25000;
    this.numberOfParticlesToInform = numberOfParticlesToInform;
    
    evaluator = new SequentialSolutionListEvaluator<DoubleSolution>();
  }

  public StandardPSOBuilder setObjectiveId(int objectiveId) {
    this.objectiveId = objectiveId;
    
    return this;
  }

  public StandardPSOBuilder setMaxIterations(int maxIterations) {
    this.maxIterations = maxIterations;

    return this;
  }

  public StandardPSOBuilder setSwarmSize(int swarmSize) {
    this.swarmSize = swarmSize;

    return this;
  }

  public StandardPSOBuilder setSolutionListEvaluator(SolutionListEvaluator<DoubleSolution> evaluator) {
    this.evaluator = evaluator;

    return this;
  }

  public StandardPSO2011 build() {
      return new StandardPSO2011(problem, objectiveId, swarmSize, maxIterations,
        numberOfParticlesToInform, evaluator);
  }

  /*
   * Getters
   */
  public Problem<DoubleSolution> getProblem() {
    return problem;
  }

  public int getMaxIterations() {
    return maxIterations;
  }

  public int getSwarmSize() {
    return swarmSize;
  }

  public SolutionListEvaluator<DoubleSolution> getEvaluator() {
    return evaluator;
  }
}
