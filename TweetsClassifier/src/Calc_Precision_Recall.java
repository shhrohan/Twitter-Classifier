import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Calc_Precision_Recall {

	
	static String file_name[]={"0","1","2","3","4","5"};
	static int number_of_tweets[]={0,100,100,100,100,100,100};
	
	static String class_names[]={"Business","Entertainment","Health","Politics","Sports","Technology"};
	
	static double precision[] = new double[6];
	static double recall[] = new double[6];
	
	public static void main(String args[]) throws IOException
	{
	
		String s = "null";
		int i;
		int min,max;
		min=number_of_tweets[0];
		max=number_of_tweets[1]-1;
		
		double tp,fn;
		
		for(i=0;i<file_name.length;i++)
		{
			tp=0;
			fn=0;
			BufferedReader br1 = new BufferedReader(new FileReader(new File("./naive/"+file_name[i])));
			String array[];
			int a;
			
			while((s=br1.readLine())!=null)
			{
				array=s.split(" ");
				a=Integer.parseInt(array[0]);
				
				if(a>=min && a<=max)
					tp++;
				else
					fn++;
			}
			
			
			precision[i]=(tp/(tp+fn));
			recall[i]=(tp/number_of_tweets[i+1]);
			
			min=max+1;
			max=max+number_of_tweets[i+1];
			
			
			
			br1.close();
		}
	
		
		for(int j=0;j<precision.length;j++)
		{
			System.out.println(class_names[j]+"\nPrecision: "+precision[j]+"\nRecall: "+ recall[j]+"\n");
		}
		

	}
	
}
