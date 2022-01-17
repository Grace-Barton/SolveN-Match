package solveIt;

public class Problems {
	private String problem;
	private String answer;
	private Problems next;
	
	public Problems(int max, int min, char operator) {
		int op1=(int)(Math.random()*(max-min+1)+min);
		int op2=(int)(Math.random()*(max-min+1)+min);
		problem=(" "+op1+operator+op2).trim();
		answer=(op1+op2+" ").trim();
	}
	
	public String getProblem() {
		return problem;
	}
	public String getAnswer() {
			return answer;
	}
	
	public Problems getNext() {
		return next;
	}
	

	
	
}
