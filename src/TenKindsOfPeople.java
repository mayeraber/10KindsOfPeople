import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class TenKindsOfPeople 
{
	private static class PeopleNode 
	{
			int number;
			int xCoordinate, yCoordinate;
			int onStack;
			
			public PeopleNode(int num, int xCoord, int yCoord)
			{
				number= num;
				xCoordinate= xCoord;
				yCoordinate= yCoord;
				onStack=0;
			}
	}
	
	static Kattio kattio= new Kattio(System.in,System.out);
	public static void main(String[] args) 
	{
		int rows= kattio.getInt();
		int cols= kattio.getInt();
		
		int xStart, yStart, xEnd, yEnd, startNum, endNum;
		PeopleNode[][] matrix= new PeopleNode[rows+2][cols+2];
		StringBuilder nextRow= new StringBuilder(1);
		for(int row=1; row<=rows;row++)
		{
			nextRow.append(kattio.nextToken());
			for(int col=1; col<=cols; col++)
			{
				matrix[row][col]= new PeopleNode(Character.getNumericValue(nextRow.charAt(col-1)),row,col);//matrix[row][col]= new PeopleNode(Character.getNumericValue(nextRow.charAt((cols*(row-1)+col-1))),row,col);
			}
			nextRow.delete(0, nextRow.length());
		}
		int numQueries= kattio.getInt();
		Stack<PeopleNode> st= new Stack<>();
		boolean cont;
		for(int y=1; y<=numQueries; y++)
		{	
			cont=true;
			xStart=kattio.getInt();
			yStart=kattio.getInt();
			xEnd= kattio.getInt();
			yEnd= kattio.getInt();
			startNum= (matrix[xStart][yStart]).number;
			endNum= (matrix[xEnd][yEnd].number);
			if(startNum!=endNum)
				kattio.println("neither");
			else
			{
				// made onStack into an int so that it's not necessary to go thru all elements each time to reset, so removed that section
				st.push(matrix[xStart][yStart]);
				matrix[xStart][yStart].onStack= y;
				while(cont)
					cont=tester(matrix,st,startNum,xEnd,yEnd,y);
			}
		}
		kattio.close();
	}

	public static boolean tester(PeopleNode[][] matrix,Stack<PeopleNode> st, int startNum,int xEnd,int yEnd, int stackNum)
	{		
		if(st.isEmpty())
		{
			kattio.println("neither");
			return false;
		}
		else
		{
			PeopleNode temp=st.pop();
			int xCoord=temp.xCoordinate;
			int yCoord= temp.yCoordinate;
			
			if((xCoord==xEnd)&&(yCoord==yEnd))
			{
				st.clear();
				if (startNum==0)
					kattio.println("binary");
				else
					kattio.println("decimal");
				return false;
			}			
			else
			{
				if((matrix[xCoord][yCoord-1]!=null)&&matrix[xCoord][yCoord-1].onStack!=stackNum&&matrix[xCoord][yCoord-1].number==startNum)
				{	st.push(matrix[xCoord][yCoord-1]);
					matrix[xCoord][yCoord-1].onStack=stackNum;
				}
				if(matrix[xCoord-1][yCoord]!=null&&matrix[xCoord-1][yCoord].onStack!=stackNum&&matrix[xCoord-1][yCoord].number==startNum)
				{	
					st.push(matrix[xCoord-1][yCoord]);
					matrix[xCoord-1][yCoord].onStack=stackNum;
				}
				if(matrix[xCoord+1][yCoord]!=null&&matrix[xCoord+1][yCoord].onStack!=stackNum&&matrix[xCoord+1][yCoord].number==startNum)
				{
					st.push(matrix[xCoord+1][yCoord]);
					matrix[xCoord+1][yCoord].onStack=stackNum;
				}
				if(matrix[xCoord][yCoord+1]!=null&&matrix[xCoord][yCoord+1].onStack!=stackNum&&matrix[xCoord][yCoord+1].number==startNum)
				{
					st.push(matrix[xCoord][yCoord+1]);
					matrix[xCoord][yCoord+1].onStack=stackNum;
				}
				return true;	
			}
		}
	}
	
	private static class Kattio extends PrintWriter {
	    public Kattio(InputStream i) {
	        super(new BufferedOutputStream(System.out));
	        r = new BufferedReader(new InputStreamReader(i));
	    }
	    public Kattio(InputStream i, OutputStream o) {
	        super(new BufferedOutputStream(o));
	        r = new BufferedReader(new InputStreamReader(i));
	    }

	    public boolean hasMoreTokens() {
	        return peekToken() != null;
	    }

	    public int getInt() {
	        return Integer.parseInt(nextToken());
	    }

	    private BufferedReader r;
	    private String line;
	    private StringTokenizer st;
	    private String token;

	    private String peekToken() {
	        if (token == null)
	            try {
	                while (st == null || !st.hasMoreTokens()) {
	                    line = r.readLine();
	                    if (line == null) return null;
	                    st = new StringTokenizer(line);
	                }
	                token = st.nextToken();
	            } catch (IOException e) { }
	        return token;
	    }

	    private String nextToken() {
	        String ans = peekToken();
	        token = null;
	        return ans;
	    }
	}
}