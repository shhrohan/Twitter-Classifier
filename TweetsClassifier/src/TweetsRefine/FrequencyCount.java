package TweetsRefine;

import java.util.TreeMap;

public class FrequencyCount {

	TreeMap<String, Frequency> treeMapObj = new TreeMap<String,Frequency>();
	public void add_to_tree_map(String word,int c)
	{
		
		boolean contain=treeMapObj.containsKey(word);
		if(!contain)
		{
			Frequency frequencyObj=new Frequency();
			switch (c)
			{
				case 0:
					frequencyObj.statusUpdate++;
					break;
				case 1:
					frequencyObj.event++;
					break;
				case 2:
					frequencyObj.informationSharing++;
					break;
				case 3:
					frequencyObj.news++;
					break;
				case 4:
					frequencyObj.opinion++;
					break;
				case 5:
					frequencyObj.personalMessage++;
					break;
			}
			treeMapObj.put(word, frequencyObj);
		}
		else
		{
			
			Frequency frequencyObj=null;
			frequencyObj=treeMapObj.get(word);
				switch (c)
				{
					case 0:
						frequencyObj.statusUpdate++;
						break;
					case 1:
						frequencyObj.event++;
						break;
					case 2:
						frequencyObj.informationSharing++;
						break;
					case 3:
						frequencyObj.news++;
						break;
					case 4:
						frequencyObj.opinion++;
						break;
					case 5:
						frequencyObj.personalMessage++;
						break;
				}
				treeMapObj.put(word, frequencyObj);
		}
		
	}

}
