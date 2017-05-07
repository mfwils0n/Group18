import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NameExplorer 
{
	private ArrayList<String> names = new ArrayList<String>();
	
	public static void main(String arg[])
	{
		NameExplorer n = new NameExplorer();
		n.setNames();
		Collections.sort(n.getNames());
		getScores(n.getNames());
		System.out.println("The sum of the name scores is " + getTotal(n.getNames()));
		String scn = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Do you want to check a name score? (enter 'YES' or 'NO')");
		scn = sc.nextLine();
		if(scn.equals("YES"))
		{
			findNameScore(n.getNames(),scn,1);
		}
	}
	
	public ArrayList<String> getNames()
	{
		return names;
	}
	
	public void setNames()
	{
		BufferedReader br = null;
		
		try 
		{
			
			br = new BufferedReader(new FileReader("names.txt"));
		} 
		catch (FileNotFoundException e)
		{
				e.printStackTrace();
		}
		
		try {
			String line = br.readLine();
			
			while(line != null){
				
				names.add(line);	
				line = br.readLine();
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Integer> getScores(ArrayList<String> names)
	{
		ArrayList<Integer> nameScores = new ArrayList<Integer>();
		int nameScore = 0;
		String temp;
		for(int i = 0; i < names.size(); i++)
		{
			temp = names.get(i);
			for(int j = 0; j < temp.length(); j++)
			{
				if(temp.charAt(j)-64 > 0 && temp.charAt(j)-64 < 27)
				{
					nameScore = nameScore + (temp.charAt(j)-64);
				}
			}
			nameScore = nameScore*(i+1);
			nameScores.add(nameScore);
			//System.out.println(nameScores);
			nameScore = 0;

		}
		return nameScores;
	}
	
	public static int getTotal(ArrayList<String> names)
	{
		int total = 0;
		for(int i = 0; i < names.size(); i++)
		{
			total = total + getScores(names).get(i);
		}
		return total;
	}
		
	public static int findNameScore(ArrayList<String> names, String scn, int y)
	{
		//System.out.println("you entered: " + scn);
		Scanner sc = new Scanner(System.in);
		String name = "";
		String bool = scn;
		
		if(y==1)
		{
			System.out.println("Enter a name (in all caps)");
			name = sc.nextLine();
		}
		if(y==2 && scn.equals("YES"))
		{
			System.out.println("Enter a name (in all caps)");
			name = sc.nextLine();
		}
		
		
		while(bool.equals("YES"))
		{
			for(int i = 0; i < names.size(); i++)
			{
				if(name.equals(names.get(i)))
				{
					System.out.print("Then name score of " + name + " is " + getScores(names).get(i) + ". Its position is " + (i+1) +  " out of " + names.size() + " names." + "\n");
					System.out.println("Do you want to check another name score? (enter 'YES' or 'NO')");
					bool = sc.nextLine();
					findNameScore(names,bool, 2);
				}
			}
			NameExplorer n = new NameExplorer();
			n.addNames(names, name);
		}
		sc.close();
		return -1;
	}	
	
	public int addNames(ArrayList<String> names, String name)
	{
		ArrayList<String> newNames = new ArrayList<String>();
		newNames = names;
		
		Scanner sc = new Scanner(System.in);    //I'm not sure why, but without this scanner, my program will crash if the user enters "NO"

		newNames.add(name);
		Collections.sort(newNames);
		for(int i = 0; i < newNames.size(); i++)
		{
			if(name.equals(newNames.get(i)))
			{
				return getScores(newNames).get(i);
			}
		}
		//sc.close();
		return 0;
	}
	
	
	
	
	
	
	
	
}
