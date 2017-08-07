package com.andresolarte.harness.joptimizer;

import com.joptimizer.optimizers.LPOptimizationRequest;
import com.joptimizer.optimizers.LPPrimalDualMethod;
import org.apache.log4j.BasicConfigurator;

import java.util.Arrays;

public class LPRunner {

    public static void main(String... args) throws Exception {
        BasicConfigurator.configure();

        //Objective function
        //Minimize:
        double[] c = new double[] { -10, -5 };

        //Inequalities constraints
        double[][] G = new double[][] {{1, 1}};
        double[] h = new double[] {8};

        //Bounds on variables
        double[] lb = new double[] {0 , 0};
        double[] ub = new double[] {100, 100};

        //optimization problem
        LPOptimizationRequest or = new LPOptimizationRequest();
        or.setC(c);
        or.setG(G);
        or.setH(h);
        or.setLb(lb);
        or.setUb(ub);
        or.setDumpProblem(true);

        //optimization
        LPPrimalDualMethod opt = new LPPrimalDualMethod();

        opt.setLPOptimizationRequest(or);
        int returnCode = opt.optimize();

        double[] sol = opt.getOptimizationResponse().getSolution();
        System.out.println("Sol: " + Arrays.toString(sol));
        System.out.println(or);
    }
}
