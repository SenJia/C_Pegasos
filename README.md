

[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
# C-Pegasos

C-Pegasos is an on-line linear classifier based on the Pegasos algorithm. The key modification of C-Pegasos is that when a
training point is predicted to have the wrong label with very large margin, it may be considered unreliable and 
therefore the magnitude of the update associated to it will be upperbounded, in a strategy equivalent to the
soft-margin SVM.

<!--For more datails please read my [blog](https://wilddata.wordpress.com/2017/06/03/first-blog-post/).--->

@article{Jia2015,  
author = "Sen Jia and Nello Cristianini",   
title = "Learning to classify gender from four million images",   
journal = "Pattern Recognition Letters",  
volume = "58",  
number = "0",  
pages = "35 - 41",  
year = "2015",  
issn = "0167-8655",  
doi = "http://dx.doi.org/10.1016/j.patrec.2015.02.006",  
}


@INPROCEEDINGS{Jia2016,  
author={S. {Jia} and T. {Lansdall-Welfare} and N. {Cristianini}},  
booktitle={2016 IEEE 16th International Conference on Data Mining Workshops (ICDMW)},  
title={Gender Classification by Deep Learning on Millions of Weakly Labelled Images},  
year={2016},  
pages={462-467},  
doi={10.1109/ICDMW.2016.0072},  
ISSN={2375-9259},  
month={Dec}  
}

There is an extension of this work using convolutional neural networks. Both works used a subset of LFW, 10,147 images, for model evalutaion, here is the [link](https://raw.githubusercontent.com/SenJia/C_Pegasos/master/LFW_sublist.txt). If our studies are useful to your research, we would appreciate if you could cite our papers.


## Usage:
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
