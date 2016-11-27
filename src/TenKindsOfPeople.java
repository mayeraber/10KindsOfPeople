import java.util.Scanner;
import java.util.Stack;

//import TenKindsOfPeople.PeopleNode;

public class TenKindsOfPeople 
{
	private static class PeopleNode 
	{
			int number;
			boolean valid=true;
			int xCoordinate, yCoordinate;
			boolean onStack=false;
			
			public PeopleNode(int num, int xCoord, int yCoord)
			{
				number= num;
				xCoordinate= xCoord;
				yCoordinate= yCoord;
				
			}
			
			
			public void reset()
			{
				valid=true;
				onStack=false;
			}
			public  int getNum()
			{
				return number;
			}
			/*public int getXCoord()
			{
				return xCoordinate;
			}
			public int getYCoord()
			{
				return yCoordinate;
			}*/
			
			public boolean isOnStack()
			{
				return onStack;
			}
	}
	
	public static void main(String[] args) 
	{
		Scanner sc= new Scanner(System.in);
		int rows= sc.nextInt();
		int cols= sc.nextInt();
		sc.nextLine();
		int xStart, yStart, xEnd, yEnd;
		PeopleNode[][] matrix= new PeopleNode[rows+2][cols+2];
		String nextRow="";
		String str;
		for(int row=1; row<=rows;row++)
		{
			if(sc.hasNextLine())
			{
				nextRow=""+ sc.nextLine();
				for(int col=1; col<=cols; col++)
				{
					str="";
					matrix[row][col]=  new PeopleNode(Integer.parseInt(str+=nextRow.charAt(col-1)),row,col);
				}
			}
		}
		
		int numQueries= sc.nextInt();
		Stack<PeopleNode> st;
		for(int y=0; y<numQueries; y++)
		{	
			xStart=sc.nextInt();
			yStart= sc.nextInt();
			xEnd= sc.nextInt();
			yEnd= sc.nextInt();
			int startNum= (matrix[xStart][yStart]).getNum();
			int endNum= (matrix[xEnd][yEnd].getNum());
			if(startNum!=endNum)
				System.out.println("neither");
			else
			{
				if(y>0)
				{
					for(int row=1; row<=rows;row++)
					{
						for(int col=1; col<=cols; col++)
						{
							matrix[row][col].reset();
						}
					}
				}
				st= new Stack<>();
				st.push(matrix[xStart][yStart]);
				
				tester(matrix,st,startNum,xEnd,yEnd);
			}
		}
		sc.close();
	}
	

	
	
	public static void tester(PeopleNode[][] matrix,Stack<PeopleNode> sta, int startNum,int xEnd,int yEnd)
	{
		
		if(sta.isEmpty())
			System.out.println("neither");
		else{
			PeopleNode temp=sta.pop();
			temp.onStack= true;
			int xCoord=temp.xCoordinate;
			int yCoord= temp.yCoordinate;
			
			if((xCoord==xEnd)&&(yCoord==yEnd))
			{
				sta.clear();
				if (startNum==0)
					System.out.println("binary");//System.out.println("binary");
				else
					System.out.println("decimal");//System.out.println("decimal");
				
				
			}			
			else
			{
				
				if((temp.getNum()!=startNum)||(((matrix[xCoord+1][yCoord]==null||matrix[xCoord+1][yCoord].valid==false)&&(matrix[xCoord-1][yCoord]==null||matrix[xCoord-1][yCoord].valid==false)&&(matrix[xCoord][yCoord-1]==null||matrix[xCoord][yCoord-1].valid==false)&&(matrix[xCoord][yCoord+1]==null||matrix[xCoord][yCoord+1].valid==false))))
				{
					temp.valid=false;
				}
				
					if((matrix[xCoord][yCoord-1]!=null)&&matrix[xCoord][yCoord-1].valid&&matrix[xCoord][yCoord-1].isOnStack()==false&&matrix[xCoord][yCoord-1].getNum()==startNum)
					{	sta.push(matrix[xCoord][yCoord-1]);
						matrix[xCoord][yCoord-1].onStack=true;
					}
					if(matrix[xCoord-1][yCoord]!=null&&matrix[xCoord-1][yCoord].valid&&matrix[xCoord-1][yCoord].isOnStack()==false&&matrix[xCoord-1][yCoord].getNum()==startNum)
					{	
						sta.push(matrix[xCoord-1][yCoord]);
						matrix[xCoord-1][yCoord].onStack=true;
					}
					if(matrix[xCoord+1][yCoord]!=null&&matrix[xCoord+1][yCoord].valid&&matrix[xCoord+1][yCoord].isOnStack()==false&&matrix[xCoord+1][yCoord].getNum()==startNum)
					{
						sta.push(matrix[xCoord+1][yCoord]);
						matrix[xCoord+1][yCoord].onStack=true;
					}
					if(matrix[xCoord][yCoord+1]!=null&&matrix[xCoord][yCoord+1].valid&&matrix[xCoord][yCoord+1].isOnStack()==false&&matrix[xCoord][yCoord+1].getNum()==startNum)
					{
						sta.push(matrix[xCoord][yCoord+1]);
						matrix[xCoord][yCoord+1].onStack=true;
					}	
				tester(matrix,sta, startNum, xEnd, yEnd);
			}
		}	
	}
}

