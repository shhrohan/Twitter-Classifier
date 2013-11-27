package TweetsRefine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TweetCollector 
{
	static public String filePath_str;
	static public String refinedTweetFile_str;
	static private BufferedReader tweetFile_BR=null;
	static private BufferedWriter tweetFile_BW=null;
	//String outPath="";

	public void initialize(String fileName,String outFileName) throws IOException
	{
		tweetFile_BR = new BufferedReader(new FileReader(new File(fileName)));
		tweetFile_BW = new BufferedWriter(new FileWriter(outFileName));
	}
	
	public void deInitialize() throws IOException
	{
		tweetFile_BR.close();
		tweetFile_BW.close();
	}
	
	
	public void extractTweet() throws IOException
	{
		String line_str = "";
		String tweet_str = "";
		
		while((line_str = tweetFile_BR.readLine()) != null)
		{
			tweet_str = line_str.replaceAll("[^/:.0-9a-zA-Z]"," ").replaceAll("[ ]+", " ").trim();
			
			int start = tweet_str.indexOf("http:");
			int end = tweet_str.indexOf(" ",start);
			StringBuilder tweet = new StringBuilder();
			
			if(start != -1)
			{
				tweet.append(tweet_str.substring(0,start));

				if(end != -1)
					tweet.append(tweet_str.substring(end));
			}
			
			
			if(!tweet_str.equals("") && start == -1)
			{
				tweet_str = tweet_str.replaceAll("[^a-zA-Z]"," ").replaceAll("[ ]+", " ").trim();
				if(!tweet_str.equals("") && (tweet_str.length() > 1))
					tweetFile_BW.write(tweet_str + "\n" );
			}
			
			else if(!tweet.toString().equals(""))
			{
				tweet_str = tweet.toString().replaceAll("[^a-zA-Z]"," ").replaceAll("[ ]+", " ").trim();
				if(!tweet_str.equals("") && (tweet_str.length() > 1))
					tweetFile_BW.write(tweet_str + "\n" );
			}
	   }
	}
}
