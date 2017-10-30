package gj.forza4.player;

public class RandomPlayer implements Player {


	private int nr = 0;
	private int nc = 0;

	// 0 io, 1 avversario
	private static byte[][] board;

	@Override
	public void start(int nr, int nc) {
		long startTime = System.nanoTime();

		setNr(nr);
		setNc(nc);

		board = new byte[nr][nc];
		long endTime = System.nanoTime();

		long duration = (endTime - startTime);
		//System.out.println(duration);
	}

	@Override
	public int move() {
		boolean possible = false;
		int possibleMove = 0;
		
		while (!possible) {
			possibleMove = (int) (Math.random() * nc);
			possible = insert(possibleMove,1);
		}
		//print();
		return possibleMove;
	}

	private boolean insert(int c, int p) {
		int i = nr-1;
		boolean possible = false;
		while (i >=0 && !possible) {
			if (board[i][c] == 0) {
				board[i][c] = (byte)p;
				possible = true;
			}
			i--;
		}
		return possible;

	}
	
	private void print(){
		
		for(int i=0; i<nr; i++){
			for(int j=0; j<nc; j++){
				System.out.print(board[i][j] + ",");
			}
			System.out.println("");
		}
		
	}

	private void minimax() {

	}

	private void evaluation() {

	}

	@Override
	public void tellMove(int c) {
		insert(c,-1);
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public void setNc(int nc) {
		this.nc = nc;
	}

}
