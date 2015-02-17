package c_pegasos;

import org.ejml.simple.SimpleMatrix;

public class Sample {
	int label;
	SimpleMatrix feature;
	Sample(int label,SimpleMatrix feature){
		this.label=label;
		this.feature=feature;
	}
}
