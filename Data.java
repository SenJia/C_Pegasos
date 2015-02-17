package c_pegasos;

import org.ejml.simple.SimpleMatrix;

public class Data {
	int label;
	SimpleMatrix feature;
	Data(int label,SimpleMatrix feature){
		this.label=label;
		this.feature=feature;
	}
}
