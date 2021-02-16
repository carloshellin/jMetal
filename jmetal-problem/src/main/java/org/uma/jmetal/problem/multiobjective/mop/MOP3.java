package org.uma.jmetal.problem.multiobjective.mop;

import org.uma.jmetal.problem.doubleproblem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.doublesolution.DoubleSolution;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem MOP3. Defined in
 * H. L. Liu, F. Gu and Q. Zhang, "Decomposition of a Multiobjective 
 * Optimization Problem Into a Number of Simple Multiobjective Subproblems,"
 * in IEEE Transactions on Evolutionary Computation, vol. 18, no. 3, pp. 
 * 450-455, June 2014.
 *
 * @author Mastermay <javismay@gmail.com> 	
 */
@SuppressWarnings("serial")
public class MOP3 extends AbstractDoubleProblem {

  /** Constructor. Creates default instance of problem MOP3 (10 decision variables) */
  public MOP3() {
    this(10);
  }

  /**
   * Creates a new instance of problem MOP3.
   *
   * @param numberOfVariables Number of variables.
   */
  public MOP3(Integer numberOfVariables) {
    setNumberOfVariables(numberOfVariables);
    setNumberOfObjectives(2);
    setName("MOP3");

    List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables()) ;
    List<Double> upperLimit = new ArrayList<>(getNumberOfVariables()) ;

    for (int i = 0; i < getNumberOfVariables(); i++) {
      lowerLimit.add(0.0);
      upperLimit.add(1.0);
    }

    setVariableBounds(lowerLimit, upperLimit);
  }

  /** Evaluate() method */
  public DoubleSolution evaluate(DoubleSolution solution) {
    double[] f = new double[solution.objectives().length];

    double g = this.evalG(solution);
    f[0] = (1 + g) * Math.cos(solution.getVariable(0) * Math.PI * 0.5);
    f[1] = (1 + g) * Math.sin(solution.getVariable(0) * Math.PI * 0.5);

    solution.setObjective(0, f[0]);
    solution.setObjective(1, f[1]);
    return solution ;
  }

  /**
   * Returns the value of the MOP3 function G.
   *
   * @param solution Solution
   */
  private double evalG(DoubleSolution solution) {
    double g = 0.0;
    for (int i = 1; i < solution.getNumberOfVariables(); i++) {
      double t = solution.getVariable(i) - Math.sin(0.5 * Math.PI * solution.getVariable(0));
      g += Math.abs(t) / (1 + Math.exp(5 * Math.abs(t)));
    }
    g = 10 * Math.sin(0.5 * Math.PI * solution.getVariable(0)) * g;
    return g;
  }

}
