package TweetsRefine;
import java.io.IOException;

public class IndexCreatorTrain
{
	public static String fileArray[];
	static String outPath="./FilterTweets/";
	public static String inPath="./Tweets/";
	
	public IndexCreatorTrain()
	{
		fileArray=new String[6];
		fileArray[0]="business";
		fileArray[1]="entertainment";
		fileArray[2]="health";
		fileArray[3]="politics";
		fileArray[4]="sports";
		fileArray[5]="technology";
	}
	
	public static void main(String[] args) throws IOException
	{
		IndexCreatorTrain indexcreatorObj=new IndexCreatorTrain();
		IndexBuildTrain indexBuildObj=null;
		for(int i=0;i<=5;i++)
		{
			TweetCollector tweetCollectorObj=new TweetCollector();
			tweetCollectorObj.initialize(inPath+fileArray[i],outPath+fileArray[i]);
			tweetCollectorObj.extractTweet();
			tweetCollectorObj.deInitialize();
		}
		indexBuildObj=new IndexBuildTrain();
		for(int i=0;i<=5;i++)
		{
			indexBuildObj.fileInitializer(fileArray[i], i);
			indexBuildObj.tokenize();
		}
		
		
		// write file containing list of unique words
		indexBuildObj.fixedArffAttributeOfNaive();
		
		/* create naive baise */
		for(int i=0;i<=5;i++)
		{
			indexBuildObj.createNiveArff(fileArray[i]);
		}
		/* end here*/
		/* create SVM */
		for(int i=0;i<=5;i++)
		{
			for(int j=i+1;j<=5;j++)
			{
				indexBuildObj.generateIndexSVM(fileArray[i],i,fileArray[j],j);
			}
		}
		/*end here */
		
	}

}