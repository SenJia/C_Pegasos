[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
# C-Pegasos
![scrutinizer](https://scrutinizer-ci.com/g/SenJia/C_Pegasos/badges/quality-score.png?b=master)
C-Pegasos is an on-line linear classifier based on the Pegasos algorithm. The key modification of C-Pegasos is that when a
training point is predicted to have the wrong label with very large margin, it may be considered unreliable and 
therefore the magnitude of the update associated to it will be upperbounded, in a strategy equivalent to the
soft-margin SVM.

For more datails please search the paper ["Learning to Classify Gender from Four Million Images"].

@article{Jia201535,
author = "Sen Jia and Nello Cristianini", 

title = "Learning to classify gender from four million images ", 

journal = "Pattern Recognition Letters ",

volume = "58", 

number = "0",

pages = "35 - 41", 

year = "2015", 

issn = "0167-8655", 

doi = "http://dx.doi.org/10.1016/j.patrec.2015.02.006", 

}

##Usage:
CPegasos classifer has two main functions, train and test, which need an array list of "Sample" as argument. The "Sample" 
class simply consists of two varibles, an integer for label and a SimpleMatrix for feature vector. For  more details about the
use of feature vector, please visit the website of ["Efficient Java Matrix Library" (EJML)](https://code.google.com/p/efficient-java-matrix-library/).
```java
ArrayList<Sample> trainingSet = new ArrayList<Sample>();
SimpleMatrix feature = new SimpleMatrix(1000,1);      #genderate a sample point of length 1000
feature.set(0,0.5);                                   #set the first element to 0.5
trainingSet.add(new Sample(1,feature));

CPegasos cp = new CPegasos(1,0.1,1000,10000);   # Note that the step will not be clipped until 10,000 samples are trained
cp.train(trainingSet);
```
##License:
This code is released under GPLv3 license.
