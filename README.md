# C_Pegasos

C-Pegasos is an on-line linear classifier based on the Pegasos algorithm. The key modification of C-Pegasos is that when a
training point is predicted to have the wrong label with very large margin, it may be considered unreliable and 
therefore the magnitude of the update associated to it will be upperbounded, in a strategy equivalent to the
soft-margin SVM.

For more datails please search the paper "Learning to Classify Gender from Four Million Images".

##Usage:
CPegasos classifer has two main functions, train and test, which need an array list of "Sample" as argument. The "Sample" 
class simply consists of two varibles, an integer for label and a SimpleMatrix for feature vector. More details for the
use of feature vector, please visit the website of "Efficient Java Matrix Library" (EJML).

ArrayList<Sample> trainingSet = new ArrayList<Sample>();
SimpleMatrix feature = new SimpleMatrix(1000,1);      #genderate a sample point of length 1000
feature.set(0,0.5);                                   #set the first element to 0.5
trainingSet.add(new Sample(1,feature));

CPegasos cp = new CPegasos(1,0.1,1000,10000);   # Note that the step will not be clipped until 10,000 samples are trained
cp.train(trainingSet);

##License:
The EJML library is released under the Apache License 2.0 code license, which is compatible with GPLv3 license.
