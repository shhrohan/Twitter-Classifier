package grev;

import java.io.IOException;

import TweetsRefine.TweetCollector;
 

public class GrevIndexCreatorTest
{
	static String outPath="./grev/";
	
	public static void createIndex(String fileName,String modelName) throws IOException
	{
		GrevIndexBuildTest indexBuildObj=null;
		
		TweetCollector tweetCollectorObj=new TweetCollector();
		tweetCollectorObj.initialize(fileName,outPath+"grev.txt");
		tweetCollectorObj.extractTweet();
		tweetCollectorObj.deInitialize();
		
		indexBuildObj=new GrevIndexBuildTest();
		indexBuildObj.readUniqueWord();
		indexBuildObj.createIndexSVM(outPath+"grev.txt");
		
	}

}