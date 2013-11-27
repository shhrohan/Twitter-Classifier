import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class TweetFetcher 
{
	public static void main(String args[]) throws TwitterException, IOException, InterruptedException
	{
		BufferedReader all_hash_reader = new BufferedReader(new FileReader(new File("politics")));
		String hash_tag = null;
		
		BufferedWriter tweet_writer = null;
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setHttpProxyHost("proxy.iiit.ac.in");
		cb.setHttpProxyPort(8080);
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey("U6OkYOdJcY4iqjbAXw130Q")
		  .setOAuthConsumerSecret("fHsDazFL4LTiswbb5QrmebK3dre6fwMU2T5s8oEVSc")
		  .setOAuthAccessToken("1065022058-FsvQaq9PlvoiEgwNHwIcggwFuID3GF4qMiwiJ0E")
		  .setOAuthAccessTokenSecret("6thDoEMkPHF3TnM2HVOqpSusNEhkXELPefTHLZxmU");
		
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();
		
		while(true)
		{
			try 
			{
				hash_tag = all_hash_reader.readLine();
				
				if(hash_tag == null)
				{
					System.out.println("Hash Tag Processing done..");
					break;
				}
			    
				Query query = new Query(hash_tag);
			    
		        QueryResult result;
		        do 
		        {
		        	System.out.println("new hash tag = " + hash_tag);
		        	result = twitter.search(query);
		            List<Status> tweets = result.getTweets();
		            
		            for (Status tweet : tweets) 
		            {
		            	String t = tweet.getText(); 
		            	if(t.contains(hash_tag))
		            	{
		            		tweet_writer = new BufferedWriter(new FileWriter(new File("./Tweets/new_tweets/"+hash_tag),true));
		            		System.out.println(t);
		            		tweet_writer.write(t.replaceAll("\n"," ")+"\n");
		            		tweet_writer.close();
		            	}
		            }
		        }
		        while ((query = result.nextQuery()) != null);
			}
			catch (TwitterException te) 
			{
				if(tweet_writer != null)
					tweet_writer.close();
				
		        System.out.println("Failed to search tweets: " + te.getMessage());
				Map<String, RateLimitStatus> r = null;
				RateLimitStatus status = null;
				r = twitter.getRateLimitStatus();
				status = r.get("/users/show/:id");
				int time_to_wait = status.getSecondsUntilReset()*1000+2000;
				
				System.out.println("Limit Reached..");
				while(true)
				{
					Thread.sleep(1000);
					System.out.println((time_to_wait/1000) + " Seconds to Resume !!" );
					time_to_wait -=1000;
					
					if(time_to_wait <= 0)
					{
						break;
					}
				}
	        }
		}
		all_hash_reader.close();
	}
}


	