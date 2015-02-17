package c_pegasos;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.ejml.simple.SimpleMatrix;

public class CPegasos {
	private SimpleMatrix weights;
	private double bias=1;
	
	private int number=0;  
	private double c;
	private double omega;
	private int startPoint;
	private double c_coef;
	private double mean_alpha=0;
	
	private double FP=0;
	private double FN=0;
	private double TP=0;
	private double TN=0;
	
	public CPegasos(double c_coef,double omega,String path,int featureLength,int startPoint) throws IOException{
		this.c_coef=c_coef;
		this.omega=omega;
		this.startPoint=startPoint;
		this.weights=new SimpleMatrix(featureLength,1);
	}
	
	public CPegasos(String path) throws IOException{
		load(path);
		this.c=(this.mean_alpha/this.number)*this.c_coef;
	}
	
	public void reset(){
		this.FN=0;
		this.FP=0;
		this.TN=0;
		this.TP=0;
	}
	
	public double getAccuracy(){
		return (TN+TP)/(TP+TN+FP+FN);
	}
	
	public double getPosAccuracy(){
		return TP/(TP+FN);
	}
	
	public double getNegAccuracy(){
		return TN/(TN+FP);
	}
	
	public double predict(SimpleMatrix feature){
		return weights.dot(feature)+bias;
	}
		
	public void train(ArrayList<Data> trainingSet){
		for(Data a:trainingSet){
			double prediction=predict(a.feature);
			number++;
			if(prediction>0){
				if(a.label>0){
					TP++;
				}else{
					FP++;
				}
			}else{
				if(a.label>0){
					FN++;
				}else{
					TN++;
				}
			}
			update(a.feature,prediction,a.label);
		}
		System.out.println("Training set size: "+trainingSet.size());
		System.out.println("TP: "+TP+"\t"+"TN: "+TN+"\t"+"FP: "+FP+"\t"+"FN: "+FN);
		System.out.println("Training accuracy: "+getAccuracy());
	}	

	public void test(ArrayList<Data> testSet){
		reset();
		for(Data a:testSet){
			double prediction=predict(a.feature);
			if(prediction>0){
				if(a.label>0)
					TP++;
				else
					FP++;
			}else{
				if(a.label>0)
					FN++;
				else
					TN++;
			}
		}
		System.out.println("Test size: "+testSet.size());
		System.out.println("TP: "+TP+"\t"+"TN: "+TN+"\t"+"FP: "+FP+"\t"+"FN: "+FN);
		System.out.println("Accuracy: "+getAccuracy());
	}
	
	private void update(SimpleMatrix feature,double prediction,int label){
		double alpha=omega/(feature.dot(feature))*(1-label*prediction);
		
		number++;
		if(alpha<=0){
			alpha=0;
		}else{
			mean_alpha=mean_alpha+alpha;   // update the mean value of alpha
			if(number>startPoint || alpha>c){
				alpha=c;
			}
		}
		if(number%startPoint==0){
			c=(mean_alpha/number)*c_coef;   //update c value
		}
		this.weights= this.weights.plus(feature.scale(alpha*label));
		bias=bias+alpha*label;
	}
	
	public void save(String path) throws IOException{
		if(!path.endsWith("/"))
			path=path+"/";
		File dir=new File(path);
		if(!dir.isDirectory())
			dir.mkdirs();
		weights.saveToFileCSV(path+"model.weights");
		DataOutputStream a=new DataOutputStream(new FileOutputStream(path+"model.bias"));
		a.writeDouble(bias);
		a.close();
		DataOutputStream b=new DataOutputStream(new FileOutputStream(path+"model.number"));
		b.writeInt(number);
		b.close();
		DataOutputStream c=new DataOutputStream(new FileOutputStream(path+"model.meanAlpha"));
		c.writeDouble(mean_alpha);
		c.close();
		DataOutputStream d=new DataOutputStream(new FileOutputStream(path+"model.omega"));
		d.writeDouble(omega);
		d.close();
		DataOutputStream e=new DataOutputStream(new FileOutputStream(path+"model.coef"));
		e.writeDouble(c_coef);
		e.close();
		DataOutputStream f=new DataOutputStream(new FileOutputStream(path+"model.startPoint"));
		f.writeInt(startPoint);
		f.close();
	}

	void load(String path) throws IOException{
		if(!path.endsWith("/"))
			path=path+"/";
		weights=weights.loadCSV(path+"model.weights");
		DataInputStream a=new DataInputStream(new FileInputStream(path+"model.bias"));
		bias=a.readDouble();
		a.close();
		DataInputStream b=new DataInputStream(new FileInputStream(path+"model.number"));
		number=b.readInt();
		b.close();
		DataInputStream c=new DataInputStream(new FileInputStream(path+"model.meanAlpha"));
		mean_alpha = c.readDouble();
		c.close();
		DataInputStream d=new DataInputStream(new FileInputStream(path+"model.omega"));
		omega = d.readDouble();
		d.close();
		DataInputStream e=new DataInputStream(new FileInputStream(path+"model.coef"));
		c_coef = e.readDouble();
		e.close();
		DataInputStream f=new DataInputStream(new FileInputStream(path+"model.startPoint"));
		startPoint = f.readInt();
		f.close();
	}

}
