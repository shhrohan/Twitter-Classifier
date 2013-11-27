package SVM;

import SVM.utils.DataFileReader;
import SVM.ca.uwo.csd.ai.nlp.kernel.KernelManager;
import SVM.ca.uwo.csd.ai.nlp.kernel.LinearKernel;
import SVM.ca.uwo.csd.ai.nlp.libsvm.svm_model;
import SVM.ca.uwo.csd.ai.nlp.libsvm.svm_parameter;
import SVM.ca.uwo.csd.ai.nlp.libsvm.ex.Instance;
import SVM.ca.uwo.csd.ai.nlp.libsvm.ex.SVMTrainer;


public class CreateModel 
{
	public static void createModel() throws Exception
	{
		for(int i=0;i<=5;i++)
		{
			for(int j=i+1;j<=5;j++)
			{
				Instance[] trainingInstances = DataFileReader.readDataFile("./svm/index/index"+i+j);
				
				svm_parameter param = new svm_parameter();
				
				KernelManager.setCustomKernel(new LinearKernel());
		        
		        //Train the model
		        System.out.println("Training started...");
		        svm_model model = SVMTrainer.train(trainingInstances, param);
		        System.out.println("Training completed.");
		                
		        //Save the trained model
		        SVMTrainer.saveModel(model, "./svm/model/"+i+j+".model"); ////////////////////// change here a1a.model
			}
		}
	}
	public static void main(String arr[]) throws Exception
	{
		createModel();
	}
}
