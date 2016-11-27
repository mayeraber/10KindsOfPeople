/*import java.util.Scanner;
import java.util.Stack;

public class PeopleNode 
{
		int number;
		boolean valid=true;
		int xCoordinate, yCoordinate;
		PeopleNode top, bottom, left, right;
		boolean onStack=false;
		
		public PeopleNode(int num, int xCoord, int yCoord)
		{
			number= num;
			xCoordinate= xCoord;
			yCoordinate= yCoord;
			
		}
		
		public void setNeighbors(PeopleNode top,PeopleNode bottom, PeopleNode left, PeopleNode right)
		{
			this.top= top;
			this.bottom= bottom;
			this.left= left;
			this.right= right;
		}
		
		
		public  int getNum()
		{
			return number;
		}
		public int getXCoord()
		{
			return xCoordinate;
		}
		public int getYCoord()
		{
			return yCoordinate;
		}
		public boolean noValidSides()
		{
			if((top==null||top.valid==false)&&(bottom==null||!isBottomValid())&&(left==null||!isLeftValid())&&(right==null||!isRightValid()))
			return true;
			return false;
		}
		public boolean isTopValid()
		{
			return top.valid;
		}
		public boolean isBottomValid()
		{
			return bottom.valid;
		}
		public boolean isLeftValid()
		{
			return left.valid;
		}
		public boolean isRightValid()
		{
			return right.valid;
		}
		public boolean isOnStack()
		{
			return onStack;
		}
	
		
}*/
/*import java.util.Scanner;
import java.util.Stack;

public class TenKindsOfPeople 
{
	private static class PeopleNode 
	{
			int number;
			boolean valid=true;
			int xCoordinate, yCoordinate;
			PeopleNode top, bottom, left, right;
			boolean onStack=false;
			
			public PeopleNode(int num, int xCoord, int yCoord)
			{
				number= num;
				xCoordinate= xCoord;
				yCoordinate= yCoord;
				
			}
			
			public void setNeighbors(PeopleNode top,PeopleNode bottom, PeopleNode left, PeopleNode right)
			{
				this.top= top;
				this.bottom= bottom;
				this.left= left;
				this.right= right;
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
			public int getXCoord()
			{
				return xCoordinate;
			}
			public int getYCoord()
			{
				return yCoordinate;
			}
			public boolean noValidSides()
			{
				if((top==null||top.valid==false)&&(bottom==null||!isBottomValid())&&(left==null||!isLeftValid())&&(right==null||!isRightValid()))
				return true;
				return false;
			}
			public boolean isTopValid()
			{
				return top.valid;
			}
			public boolean isBottomValid()
			{
				return bottom.valid;
			}
			public boolean isLeftValid()
			{
				return left.valid;
			}
			public boolean isRightValid()
			{
				return right.valid;
			}
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
					//if(col==1)
					//	new PeopleNode(Integer.parseInt(str+=nextRow.charAt(col-1)), xCoord, yCoord)
					str="";
					matrix[row][col]=  new PeopleNode(Integer.parseInt(str+=nextRow.charAt(col-1)),row,col);
				}
			}
		}
		
		for(int row=1; row<=rows;row++)
		{
			for(int col=1; col<=cols; col++)
			{
				matrix[row][col].setNeighbors(matrix[row-1][col],matrix[row+1][col], matrix[row][col-1], matrix[row][col+1]);
			}
		}
		PeopleNode head= matrix[1][1];
		PeopleNode tail= matrix[rows+1][cols+1];
		int numQueries= sc.nextInt();
		Stack<PeopleNode> st;
		PeopleNode starter,ender;
		for(int y=0; y<numQueries; y++)
		{	
			xStart=sc.nextInt();
			yStart= sc.nextInt();
			xEnd= sc.nextInt();
			yEnd= sc.nextInt();
			PeopleNode temp1;
			PeopleNode temp=head;
			for(int i=1;i<xStart;i++)
				temp= temp.bottom;
			for(int i=1;i<yStart;i++)
				temp= temp.right;
			starter=temp;
			int startNum= temp.getNum();
			temp=head;
			for(int i=1;i<xEnd;i++)
				temp= temp.bottom;
			for(int i=1;i<yEnd;i++)
				temp= temp.right;
			ender= temp;
			int endNum= temp.getNum();
			//int startNum= (matrix[xStart][yStart]).getNum();
			//int endNum= (matrix[xEnd][yEnd].getNum());
			if(startNum!=endNum)
				System.out.println("neither");
			else
			{
				if(y>0)
				{
					temp=head;
					temp1=head;
					for(int row=1; row<=rows;row++)
					{
						for(int col=1; col<=cols&&temp!=null; col++)
						{
							temp.reset();
							temp=temp.right;
						}
						temp=temp1.bottom;
						temp1=temp;
					}
				}
				st= new Stack<>();
				st.push(starter);
				tester(st,startNum,xEnd,yEnd,false);
			}
		}
		sc.close();
	}
	
	public static void tester(Stack<PeopleNode> sta, int startNum,int xEnd,int yEnd,boolean status)
	{
		boolean test= status;
		if(sta.isEmpty())
			System.out.println("neither");
		else{
		
			PeopleNode temp=sta.pop();
			temp.onStack= true;
			
			
			if((temp.getXCoord()==xEnd)&&(temp.getYCoord()==yEnd))
			{
				if (startNum==0)
					System.out.println("binary");
				else
					System.out.println("decimal");
				test= true;
			}			
			if(!test)
			{
				if((temp.getNum()!=startNum)||((temp.noValidSides())))
				{
					temp.valid=false;
				}
					if(temp.left!=null&&temp.isLeftValid()&&temp.left.isOnStack()==false&&temp.left.getNum()==startNum)
					{	sta.push(temp.left);
						temp.left.onStack=true;
					}
					if(temp.top!=null&&temp.isTopValid()&&temp.top.isOnStack()==false&&temp.top.getNum()==startNum)
					{	
						sta.push(temp.top);
						temp.top.onStack=true;
					}
					if(temp.bottom!=null&&temp.isBottomValid()&&temp.bottom.isOnStack()==false&&temp.bottom.getNum()==startNum)
					{
						sta.push(temp.bottom);
						temp.bottom.onStack=true;
					}
					if(temp.right!=null&&temp.isRightValid()&&temp.right.isOnStack()==false&&temp.right.getNum()==startNum)
					{
						sta.push(temp.right);
						temp.right.onStack=true;
					}	
				tester(sta, startNum, xEnd, yEnd,test);
			}
		}	
	}
}*/


