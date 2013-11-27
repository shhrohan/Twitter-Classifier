package NAIVE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestTweetsReader 
{
	public List<String> storeTweets;
	public TestTweetsReader()
	{
		storeTweets=new ArrayList<String>();
	}
	
	public void readTweets(String FileName) throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File(FileName)));
		String str="";
		while((str=br.readLine()) != null)
		{
			storeTweets.add(str);
		}
	}
}
