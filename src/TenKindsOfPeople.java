import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;
public class TenKindsOfPeople 
{
	
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

	    public double getDouble() {
	        return Double.parseDouble(nextToken());
	    }

	    public long getLong() {
	        return Long.parseLong(nextToken());
	    }

	    public String getWord() {
	        return nextToken();
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
			
			public boolean isOnStack()
			{
				return onStack;
			}
	}
	
	public static void main(String[] args) 
	{
		Kattio kattio= new Kattio(System.in,System.out);
		//BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer strT= new StringTokenizer(br.readLine());
		//Scanner sc= new Scanner(System.in);
		int rows= kattio.getInt();
		int cols= kattio.getInt();
		//sc.nextLine();
		int xStart, yStart, xEnd, yEnd;
		PeopleNode[][] matrix= new PeopleNode[rows+2][cols+2];
		String nextRow="";
		String str;
		for(int row=1; row<=rows;row++)
		{
			if(kattio.hasMoreTokens())
			{
				nextRow=""+ kattio.nextToken();
				for(int col=1; col<=cols; col++)
				{
					str="";
					matrix[row][col]=  new PeopleNode(Integer.parseInt(str+=nextRow.charAt(col-1)),row,col);
				}
			}
		}
		
		int numQueries= kattio.getInt();
		Stack<PeopleNode> st;
		boolean cont=true;
		for(int y=0; y<numQueries; y++)
		{	
			xStart=kattio.getInt();
			yStart=kattio.getInt();
			xEnd= kattio.getInt();
			yEnd= kattio.getInt();
			int startNum= (matrix[xStart][yStart]).number;
			int endNum= (matrix[xEnd][yEnd].number);
			if(startNum!=endNum)
				kattio.println("neither");
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
				cont=true;
				st.push(matrix[xStart][yStart]);
				while(cont)
				{
					if(st.isEmpty()){
						kattio.println("neither");
						cont=false;}
					else{
						PeopleNode temp=st.pop();
						temp.onStack= true;
						int xCoord=temp.xCoordinate;
						int yCoord= temp.yCoordinate;
						
						if((xCoord==xEnd)&&(yCoord==yEnd))
						{
							st.clear();
							cont=false;
							if (startNum==0)
								kattio.println("binary");//System.out.println("binary");
							else
								kattio.println("decimal");//System.out.println("decimal");
						}			
						else
						{
							
							if((temp.number!=startNum)||(((matrix[xCoord+1][yCoord]==null||matrix[xCoord+1][yCoord].valid==false)&&(matrix[xCoord-1][yCoord]==null||matrix[xCoord-1][yCoord].valid==false)&&(matrix[xCoord][yCoord-1]==null||matrix[xCoord][yCoord-1].valid==false)&&(matrix[xCoord][yCoord+1]==null||matrix[xCoord][yCoord+1].valid==false))))
							{
								temp.valid=false;
							}
							
								if((matrix[xCoord][yCoord-1]!=null)&&matrix[xCoord][yCoord-1].valid&&matrix[xCoord][yCoord-1].isOnStack()==false&&matrix[xCoord][yCoord-1].number==startNum)
								{	st.push(matrix[xCoord][yCoord-1]);
									matrix[xCoord][yCoord-1].onStack=true;
								}
								if(matrix[xCoord-1][yCoord]!=null&&matrix[xCoord-1][yCoord].valid&&matrix[xCoord-1][yCoord].isOnStack()==false&&matrix[xCoord-1][yCoord].number==startNum)
								{	
									st.push(matrix[xCoord-1][yCoord]);
									matrix[xCoord-1][yCoord].onStack=true;
								}
								if(matrix[xCoord+1][yCoord]!=null&&matrix[xCoord+1][yCoord].valid&&matrix[xCoord+1][yCoord].isOnStack()==false&&matrix[xCoord+1][yCoord].number==startNum)
								{
									st.push(matrix[xCoord+1][yCoord]);
									matrix[xCoord+1][yCoord].onStack=true;
								}
								if(matrix[xCoord][yCoord+1]!=null&&matrix[xCoord][yCoord+1].valid&&matrix[xCoord][yCoord+1].isOnStack()==false&&matrix[xCoord][yCoord+1].number==startNum)
								{
									st.push(matrix[xCoord][yCoord+1]);
									matrix[xCoord][yCoord+1].onStack=true;
								}	
						}
					}
				}
			}
		}
		kattio.close();
	}
}