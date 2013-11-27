package grev;
import java.io.IOException;

import TweetsRefine.TweetCollector;

public class GrevIndexCreatorTrain
{
	public static String fileArray[];
	static String outPath="./grev/FilterTweets/";
	public static String inPath="./grev/Tweets/";
	
	public GrevIndexCreatorTrain()
	{
		fileArray=new String[2];
		fileArray[0]="greavence";
		fileArray[1]="nonGreavence";
	}
	
	public static void main(String[] args) throws IOException
	{
		GrevIndexCreatorTrain indexcreatorObj=new GrevIndexCreatorTrain();
		GrevIndexBuildTrain indexBuildObj=null;
		for(int i=0;i<=1;i++)
		{
			TweetCollector tweetCollectorObj=new TweetCollector();
			tweetCollectorObj.initialize(inPath+fileArray[i],outPath+fileArray[i]);
			tweetCollectorObj.extractTweet();
			tweetCollectorObj.deInitialize();
		}
		indexBuildObj=new GrevIndexBuildTrain();
		
		for(int i=0;i<=1;i++)
		{
			indexBuildObj.fileInitializer(fileArray[i], i);
			indexBuildObj.tokenize();
		}
		indexBuildObj.writeUniqueWord("./grev/UniqueWord");
		indexBuildObj.generateIndexGrev(fileArray[0],0,fileArray[1],1);
		
	}

}