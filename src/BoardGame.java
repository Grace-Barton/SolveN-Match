package solveIt;
import java.util.*;

public class BoardGame {
	
	public static String arr[];
	public static LinkedList<Problems> p=new LinkedList<Problems>();
	
	public BoardGame(int x, int y) {
		createProblems(x,y);
		shuffle();
	}
	
	public void createProblems(int x, int y) {
		int val=(x*y)/2;
		arr=new String[x*y];
		
		for(int i=0;i<val;i++) {
			Problems p1= new Problems(100,0,'+');
			p.add(p1);
			arr[i*2]=p1.getProblem();
			arr[i*2+1]=p1.getAnswer();
		}
	}
	
	public void shuffle() {
		int curIndex=arr.length, randIndex=0;
		while(curIndex!=0) {
			randIndex=(int)Math.floor(Math.random()*curIndex);
			curIndex--;
			String tmp= arr[curIndex];
			arr[curIndex]=arr[randIndex];
			arr[randIndex]=tmp;
		}
		
	}
	public Problems getP(int i) {
		return p.get(i);
	}
	
	public String getArr(int i) {
		return arr[i];
	}
	
	
	
	
}
