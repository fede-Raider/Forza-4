package gj.forza4.player;

/**
 * Classe che definisce un giocatore di "Forza 4". La classe implementa
 * l'algoritmo di Minimax con alcuni miglioramenti, come ad esempio la potatura
 * alpha beta.
 * 
 * @author Federico Ciardi
 * @version 1.0
 */

public class Ciardi implements Player {

	/**
	 * Numero di righe della griglia di gioco.
	 */
	private int nr = 0;
	/**
	 * Numero di colonne della griglia di gioco.
	 */
	private int nc = 0;
	/**
	 * Profondita' massima dell'albero di ricerca del Minimax.
	 */
	private int maxDepth = 8;
	/**
	 * Griglia che contine i dischetti. 1 = Dischetto giocatore. 0 = Dischetto
	 * vuoto. -1 = Dischetto avversario.
	 */
	private int[][] board;
	/**
	 * Numero di dischetti realmente inseriti da me nella griglia.
	 */
	private int numDisk = 0;

	/**
	 * Metodo invocato dal GameManager che fa partire la partita. Il metodo si
	 * limita a creare la griglia di gioco e settare i vari parametri.
	 * 
	 * @param nr
	 *            Intero che indica il numero delle righe nella griglia.
	 * @param nc
	 *            Intero che indica il numero delle colonne nella griglia.
	 */
	@Override
	public void start(int nr, int nc) {
		this.nr = nr;
		this.nc = nc;

		maxDepth = 8;
		numDisk = 0;

		board = new int[nr][nc];
	}

	/**
	 * Metodo che comunica al GameManager la mossa da noi effettuata. Il metodo
	 * richiama il metodo chooseMove per la scelta della colonna dove inserire
	 * il dischetto. Una volta scelta la colonna, si inserisce il dischetto
	 * nella colonna, si aumenta il numero dei dischetti del mio giocatore di 1
	 * e si fa un controllo sui dischetti inseriti per aumentare l'altezza
	 * massima dell'albero. Infine si restituisce al Game Manager la colonna
	 * accuratamente scelta.
	 * 
	 * @return Un valore intero che indica la colonna in cui inserire il proprio
	 *         dischetto.
	 */
	@Override
	public int move() {
		int c = chooseMove();

		insert(c, 1);
		numDisk++;

		if (numDisk % 3 == 0) {
			maxDepth++;
		}

		return c;
	}

	/**
	 * Metodo che restituisce la colonna accuratamente scelta in cui si vuole
	 * inserire il dischetto. Il metodo setta come miglior valore di una mossa
	 * il valore intero più piccolo possibile e come miglior colonna -1. Se il
	 * metodo viene richiamato con la griglia vuota, allora restituisce la
	 * colonna centrale della griglia, poichè è sempre la colonna migliore in
	 * cui inserire il dischetto se la griglia è vuota. Se il metodo viene
	 * richiamato con la griglia contenente almeno un dischetto, allora il per
	 * ogni colonna viene inserito un dischetto se la colonna non è piena e
	 * viene richiamato il metodo di Minimax (con altezza corrente 1, alpha
	 * intero più piccolo possibile e beta intero più grande possibile),
	 * successivamente viene rimosso quel dischetto inserito e se il valore
	 * della mossa in cui è stato inserito il dischetto è maggiore del vecchio
	 * valore, allora il miglior valore e la miglior colonna vengono aggiornati.
	 * In più se troviamo un valore di vittoria un una mossa, si esce dal ciclo.
	 * Una volta usciti dal ciclo si restituisce la colonna che ha il miglior
	 * valore di mossa.
	 * 
	 * @return Un valore intero che indica la colonna in cui inserire il proprio
	 *         dischetto.
	 */
	private int chooseMove() {
		int max = Integer.MIN_VALUE;
		int best = -1;
		if (boardEmpty()) {
			// Prima volta c'e' piu' probabilita' nel centro della scacchiera
			best = nc / 2;
		} else {
			for (int i = 0; i < nc; i++) {
				// Controllo che la colonna abbia almeno uno spazio libero
				if (board[0][i] == 0) {
					insert(i, 1);
					int val = miniMax(1, max, Integer.MAX_VALUE);
					remove(i);
					if (val > max) {
						// Aggiorno valore del massimo
						best = i;
						max = val;
						if (val == Integer.MAX_VALUE) {
							// Uscita in caso di vittoria
							i = nc;
						}
					}
				}
			}
		}
		return best;
	}

	/**
	 * Metodo che crea l'albero di ricerca con tutte le possibili sequenze di
	 * inserimenti fino ad un'altezza massima, attraverso l'impementazione
	 * dell'algoritmo di Minimax con alcuni miglioramenti. Inizialmente
	 * controlla, atraverso il metodo winOrLose(), che la configurazione della
	 * tabella in cui siamo non sia già una situazione di vittoria o di
	 * sconfitta, se così fosse restituisce il valore massimo per la vittora e
	 * un valore abbastanza piccolo (-100000) per la sconfitta. Se la
	 * configurazione non è ne di vittoria ne di sconfitta, allora controlla se
	 * l'altezza dell'albero è uguale all'altezza massima o se la griglia non è
	 * già piena, in caso si verificasse una delle due condizioni allora chiama
	 * il metodo eval() sulla configurazione corrente e restituisce il valore
	 * ottenuto da quella configurazione. A questo punto se non si è verificata
	 * nessuna delle condizioni precedenti controlla se l'altezza corrente è un
	 * multiplo di 2, infatti un' altezza pari indica una mossa effettuata dal
	 * mio giocatore, mentre un'altezza dispari indica una mossa effettuata dal
	 * giocatore avversario. A questo punto per ogni colonna si inserisce un
	 * dischetto e si richiama ricorsivamente il miniMax, scegliendo il valore
	 * più grande tra il valore della chiamata e il valore che già avevamo se il
	 * turno e' quello del mio giocatore, o il valore più picolo se il turno è
	 * quello dell' avversario (la mia miglior mossa o la mossa migliore per
	 * l'avversario), fino a quando appunto o l'altezza è massima, o la griglia
	 * è piena o c'è una situazione di vincita/perdita. Quindi viene restituito
	 * il valore di quella sequenza di mosse, e ogni volta che si ritorna alla
	 * chiamata precedente si cancella il dischetto precedentemente inserito. La
	 * ricerca traverso questo metodo e' stata implementata anche con l'utilizzo
	 * della potatura alpha beta in cui i valori di alpha (valore della miglior
	 * mossa possibile attualmente calcolata per il mio giocatore) e beta
	 * (valore della miglior mossa possibile attualmente calcolata per
	 * l'avversario) per ogni nodo vengono aggiornati man mano che la ricerca si
	 * approfondisce. Se durante la ricerca, per un dato nodo alpha diventa
	 * maggiore di beta, la ricerca al di sotto di quel nodo finisce, poichè si
	 * incontra una mossa peggiore rispetto a quelle valutate in precedenza.
	 * Quindi non si giocherà una sequenza di mosse al di sotto di quel nodo.
	 * 
	 * @param currDepth
	 *            Intero che indica il numero dell'altezza dell'albero di
	 *            ricerca corrente.
	 * @param alpha
	 *            Intero che indica il valore più grande trovato in un percorso.
	 * @param beta
	 *            Intero che indica il valore più piccolo trovato in un
	 *            percorso.
	 * @return Ritorna il valore della colonna in cui è stato inserito il primo
	 *         dischetto dal chooseMove().
	 */
	private int miniMax(int currDepth, int alpha, int beta) {

		int winlose = winOrLose();

		if (winlose == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		if (winlose == Integer.MIN_VALUE) {
			return -100000;
		}

		if (currDepth == maxDepth || boardFull()) {
			// Calcolo il valore della mossa, non può succedere ne di vincere ne
			// di perdere
			return eval();
		}

		if (currDepth % 2 == 0) {
			// Turno Ciardi Player
			int val = Integer.MIN_VALUE;
			for (int i = 0; i < nc; i++) {
				// Controllo che la colonna abbia almeno uno spazio libero
				if (board[0][i] == 0) {
					alpha = Math.max(alpha, val);
					if (beta > alpha) {
						// Inserisco nella colonna
						insert(i, 1);
						val = Math
								.max(val, miniMax(currDepth + 1, alpha, beta));
						// Rimuovo dalla colonna
						remove(i);
					} else {
						i = nc;
					}
				}
			}
			return val;
		} else {
			// Turno Avversario
			int val = Integer.MAX_VALUE;
			for (int i = 0; i < nc; i++) {
				// Controllo che la colonna abbia almeno uno spazio libero
				if (board[0][i] == 0) {
					beta = Math.min(beta, val);
					if (beta > alpha) {
						// Inserisco nella colonna
						insert(i, -1);
						val = Math
								.min(val, miniMax(currDepth + 1, alpha, beta));
						// Rimuovo dalla colonna
						remove(i);
					} else {
						i = nc;
					}
				}
			}
			return val;
		}

	}

	/**
	 * Metodo che controlla (attraverso altri metodi della classe Utility) se la
	 * configurazione della griglia contiene una situazione di vittoria o di
	 * perdita.
	 * 
	 * @return Ritorna un valore intero massimo in caso di vittoria, un valore
	 *         intero minimo in caso di perdita, mentre un valore 0 se non è una
	 *         situazione ne di vittoria ne di perdita.
	 */
	private int winOrLose() {
		int column = Utility.columnWin(board, nr, nc);
		int row = Utility.rowWin(board, nr, nc);
		int diagR = Utility.diagRightWin(board, nr, nc);
		int diagL = Utility.diagLeftWin(board, nr, nc);

		if (column == Integer.MAX_VALUE || row == Integer.MAX_VALUE
				|| diagR == Integer.MAX_VALUE || diagL == Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		if (column == Integer.MIN_VALUE || row == Integer.MIN_VALUE
				|| diagR == Integer.MIN_VALUE || diagL == Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		}

		return 0;

	}

	/**
	 * Metodo che esegue una valutazione (attraverso altri metodi della classe
	 * Utility) della configurazione corrente della griglia.
	 * 
	 * @return Ritorna un valore intero che indica il punteggio della
	 *         configurazione corrente della griglia.
	 */
	private int eval() {
		// Creare funzione evaluation
		int value = Utility.column2(board, nr, nc)
				+ Utility.column3(board, nr, nc) + Utility.row2(board, nr, nc)
				+ Utility.row3(board, nr, nc)
				+ Utility.diagRight2(board, nr, nc)
				+ Utility.diagRight3(board, nr, nc)
				+ Utility.diagLeft2(board, nr, nc)
				+ Utility.diagLeft3(board, nr, nc);

		return value;

	}

	/**
	 * Metodo che inserisce un dischetto (1 o -1) nella griglia. Il metodo si
	 * limita a cercare la prima posizione vuota della colonna partendo dal
	 * basso e vi inserisce il dischetto.
	 * 
	 * @param c
	 *            Intero che indica la colonna in cui inserire il dischetto.
	 * @param p
	 *            Intero che indica se il dischetto e' del mio giocatore "1" o
	 *            se e' del giocatore avversario "-1".
	 */
	private void insert(int c, int p) {
		for (int i = nr - 1; i >= 0; i--) {
			if (board[i][c] == 0) {
				board[i][c] = p;
				i = -1;
			}
		}

	}

	/**
	 * Metodo che rimuove un dischetto (1 o -1) dalla griglia. Il metodo si
	 * limita a cercare la prima posizione non vuota della colonna partendo
	 * dall'alto e vi rimuove il dischetto.
	 * 
	 * @param c
	 *            Intero che indica la colonna in cui rimuovere il dischetto.
	 */
	private void remove(int c) {
		for (int i = 0; i <= nr - 1; i++) {
			if (board[i][c] != 0) {
				board[i][c] = 0;
				i = nr;
			}
		}
	}

	/**
	 * Metodo che controlla se la griglia è piena. Il metodo si limita a
	 * controllare che la posizione più alta di ogni colonna sia piena.
	 * 
	 * @return Ritorna un valore booleano, true se la griglia e' piena o false
	 *         se la griglia ha almeno un elemento vuoto.
	 */
	private boolean boardFull() {
		for (int i = 0; i < nc; i++) {
			if (board[0][i] == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metodo che controlla se la griglia è vuota. Il metodo si limita a
	 * controllare che la posizione più bassa di ogni colonna sia vuota.
	 * 
	 * @return Ritorna un valore booleano, true se la griglia e' vuota o false
	 *         se la griglia contiene almeno un elemento.
	 */
	private boolean boardEmpty() {
		for (int i = 0; i < nc; i++) {
			if (board[nr - 1][i] != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Metodo col quale il GameManager ci comunica la mossa dell'avversario. Il
	 * metodo inserisce il dischetto nella colonna.
	 * 
	 * @param c
	 *            Intero che indica la colonna in cui inserire il dischetto.
	 */
	@Override
	public void tellMove(int c) {
		insert(c, -1);
	}

}
