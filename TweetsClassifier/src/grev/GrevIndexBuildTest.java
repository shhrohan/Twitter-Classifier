package grev;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GrevIndexBuildTest 
{
	
	String fileName="";
	public List<String> listOfUniqWord=new ArrayList<String>();
	
	public void readUniqueWord() throws IOException
	{
		BufferedReader br=new BufferedReader(new FileReader(new File("./grev/UniqueWord")));
		String str="";
		while((str=br.readLine()) != null)
		{
			listOfUniqWord.add(str);
		}
	}
	
	public void createIndexSVM(String fileName) throws IOException
	{
		BufferedWriter bw_index=new BufferedWriter(new FileWriter(new File("./grev/"+"test.txt")));
		BufferedReader br_index=new BufferedReader(new FileReader(new File(fileName)));
		String str="";
		int treeIndex;
		int listIndex;
		boolean flag;
		String className="-1";
		while((str=br_index.readLine()) != null)
		{
			flag=true;
			List<Integer> list=new ArrayList<Integer>(Collections.nCopies(listOfUniqWord.size(),0));
			String split[]=str.split(" ");
			for(int i=0;i<split.length;i++)
			{
				if(listOfUniqWord.contains(split[i]))
				{
					treeIndex=listOfUniqWord.indexOf(split[i]);
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
		bw_index.close();
		br_index.close();
	}
	
	
}
