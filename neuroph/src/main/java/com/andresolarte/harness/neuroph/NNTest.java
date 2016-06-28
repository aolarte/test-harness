package com.andresolarte.harness.neuroph;


import org.neuroph.core.NeuralNetwork;
import org.neuroph.core.data.DataSet;
import org.neuroph.core.data.DataSetRow;
import org.neuroph.nnet.Perceptron;

import java.util.Arrays;

public class NNTest {
    public static void main(String... args) {
        new NNTest().addition();



    }

    private  void addition() {
        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
        // create training set
        DataSet trainingSet =
                new DataSet(2, 1);
        // add training data to training set (addition)
        trainingSet. addRow (new DataSetRow(new double[]{0, 0},
                new double[]{0}));
        trainingSet. addRow (new DataSetRow (new double[]{0, 1},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{1, 0},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{1, 0},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{0.5, 0.5},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{0, 0.6},
                new double[]{1}));

        trainingSet. addRow (new DataSetRow (new double[]{0.3, 0.3},
                new double[]{1}));

        trainingSet. addRow (new DataSetRow (new double[]{0.2, 0.2},
                new double[]{0}));


        // learn the training set
        neuralNetwork.learn(trainingSet);
        System.out.println("Learned");
        // save the trained network into file
//        neuralNetwork.save("or_perceptron.nnet");


        // set network input
        neuralNetwork.setInput(0.25, 0.25);
// calculate network
        neuralNetwork.calculate();
// get network output
        double[] networkOutput = neuralNetwork.getOutput();
        Arrays.stream(networkOutput).forEach(d-> System.out.println("Result: " + d));
    }

    private  void logicalOr() {
        NeuralNetwork neuralNetwork = new Perceptron(2, 1);
        // create training set
        DataSet trainingSet =
                new DataSet(2, 1);
        // add training data to training set (logical OR function)
        trainingSet. addRow (new DataSetRow(new double[]{0, 0},
                new double[]{0}));
        trainingSet. addRow (new DataSetRow (new double[]{0, 1},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{1, 0},
                new double[]{1}));
        trainingSet. addRow (new DataSetRow (new double[]{1, 1},
                new double[]{1}));
        // learn the training set
        neuralNetwork.learn(trainingSet);
        // save the trained network into file
//        neuralNetwork.save("or_perceptron.nnet");


        // set network input
        neuralNetwork.setInput(1, 1);
// calculate network
        neuralNetwork.calculate();
// get network output
        double[] networkOutput = neuralNetwork.getOutput();
        Arrays.stream(networkOutput).forEach(d-> System.out.println("Result: " + d));
    }

}
