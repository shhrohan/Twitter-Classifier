package TweetsRefine;
import java.io.IOException;

public class IndexCreatorTest
{
	static String outPath="./FilterTweets/";
	
	public static void createIndex(String fileName,String modelName) throws IOException
	{
		IndexBuildTest indexBuildObj=null;
		
		TweetCollector tweetCollectorObj=new TweetCollector();
		tweetCollectorObj.initialize(fileName,outPath+modelName+".txt");
		tweetCollectorObj.extractTweet();
		tweetCollectorObj.deInitialize();
		
		indexBuildObj=new IndexBuildTest();
		indexBuildObj.readUniqueWord();
		
		if(modelName.equals("svm"))
		{
			indexBuildObj.createIndexSVM(outPath+modelName+".txt");
		}
		else if(modelName.equals("naive"))
		{
			System.out.println("naive");
			indexBuildObj.fixedArffAttributeOfNaive();
			indexBuildObj.createIndexNaive(outPath+modelName+".txt"); // provide here file name
		}
		else if(modelName.equals("grievence"))
		{
			System.out.println("in  gre");
		}
	}

}