package TweetsRefine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class IndexBuildTrain 
{
	
	String fileName="";
	int categoryNumber=0;
	FrequencyCount frequencyCount=null;
	TreeMap<String, Integer> listOfWord;
	String inPath="./FilterTweets/";
	
	public IndexBuildTrain() throws IOException
	{
		frequencyCount=new FrequencyCount();
		listOfWord=new TreeMap<String, Integer>();
	}
	
	public void fileInitializer(String fileName,int categoryNumber)
	{
		this.fileName=fileName;
		this.categoryNumber=categoryNumber;
	}
	
	public void tokenize() throws IOException
	{
		String str="";
		BufferedReader br=new BufferedReader(new FileReader(new File(inPath+fileName)));
		while((str=br.readLine()) != null)
		{
			String line[]=str.toLowerCase().split(" ");
			for(int i=0;i<line.length;i++)
			{
					frequencyCount.add_to_tree_map(line[i], categoryNumber);
					listOfWord.put(line[i], 0);
			}
		}
	}
	
	public void fixedArffAttributeOfNaive() throws IOException
	{
		BufferedWriter listOfUniqWord=new BufferedWriter(new FileWriter(new File("uniqueWord")));
		BufferedWriter bw=new BufferedWriter(new FileWriter(new File("./naive/"+"naive.arff")));
		int count=0;
		String str="";
		bw.write("\n@RELATION iris\n");
		Set<String> setOfWord=listOfWord.keySet();
		
			Iterator<String> iterator=setOfWord.iterator();
		while(iterator.hasNext())
		{
			str=iterator.next();
			bw.write("@ATTRIBUTE "+str+"	REAL\n");
			listOfUniqWord.write(str+"\n");
			listOfWord.put(str, count++);
		}
		
		bw.write("@ATTRIBUTE class 	{business,entertainment,health,politics,sports,technology}\n@DATA\n");
		listOfUniqWord.close();
		bw.close();
	}
	
	public void createNiveArff(String fileName) throws IOException
	{

		BufferedWriter bw_index=new BufferedWriter(new FileWriter(new File("./naive/"+"naive.arff"),true));
		BufferedReader br_index=new BufferedReader(new FileReader(new File(inPath+fileName)));
		String str="";
		int treeIndex;
		int listIndex;
		
		while((str=br_index.readLine()) != null)
		{
			List<Integer> list=new ArrayList<Integer>(Collections.nCopies(listOfWord.size(),0));
			
			String split[]=str.split(" ");
			for(int i=0;i<split.length;i++)
			{
				if(listOfWord.containsKey(split[i].toLowerCase()))
				{
					treeIndex=listOfWord.get(split[i].toLowerCase());
					listIndex=list.remove(treeIndex);
					list.add(treeIndex,++listIndex);
				}
			}
			bw_index.write("{");
			for(int i=0;i<list.size();i++)
			{
				if(list.get(i) != 0)  
				{
					bw_index.write(i+" "+list.get(i)+",");
				}
			}
			bw_index.write(listOfWord.size()+" "+fileName+"}"+"\n");
			list.clear();
		}
		br_index.close();
		bw_index.close();
	}
	
	public void generateIndexSVM(String fileName1,int val1,String fileName2,int val2) throws IOException
	{
		String fileName=fileName1;
		String className="";
		BufferedWriter bw_index=new BufferedWriter(new FileWriter(new File("./svm/index/"+"index"+val1+val2)));
		className="+1";
		for(int j=0;j<2;j++)
		{
			BufferedReader br_index=new BufferedReader(new FileReader(new File(inPath+fileName)));
			String str="";
			int treeIndex;
			int listIndex;
			
			while((str=br_index.readLine()) != null)
			{
				
				boolean flag=true;
				List<Integer> list=new ArrayList<Integer>(Collections.nCopies(listOfWord.size(),0));
				String split[]=str.split(" ");
				for(int i=0;i<split.length;i++)
				{
					if(listOfWord.containsKey(split[i]))
					{
						treeIndex=listOfWord.get(split[i]);
						listIndex=list.remove(treeIndex);
						list.add(treeIndex,++listIndex);
					}
				}
				for(int i=0;i<list.size();i++)
				{
					if(list.get(i) != 0)  ///  modified for svm in testing 2
					{
						if(flag)
						{
							flag=false;
							bw_index.write(className);
						}
						bw_index.write(" "+i+":"+list.get(i));
					}
				}
				if(!flag)
					bw_index.write("\n");
				list.clear();
			}
			br_index.close();
			fileName=fileName2;
			className="-1";
		}
		bw_index.close();
	}
}
