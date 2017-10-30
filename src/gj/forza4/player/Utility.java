package gj.forza4.player;

/**
 * Classe che contiene metodi della funzione di valutazione. E' una classe che
 * contiene solo metodi statici creata per organizzare meglio il codice.
 * 
 * @author Federico Ciardi
 * @version 1.0
 */

public class Utility {

	/**
	 * Metodo controlla se esistono colonne con 2 dischetti di fila nella
	 * configurazione corrente della griglia e somma ogni 2 dischetti di fila +3
	 * se sono i dischetti del mio giocatore e -4 se sono i dischetti
	 * dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di due dischetti in colonna della
	 *         configurazione della griglia.
	 */
	public static int column2(int[][] board, int nr, int nc) {
		int sum = 0;

		// Controllo tutte le righe e colonne tranne ultima riga
		for (int row = nr - 1; row >= 3; row--) {
			for (int col = 0; col < nc; col++) {
				if (board[row][col] == board[row - 1][col]
						&& board[row - 2][col] == 0 && board[row - 3][col] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono colonne con 3 dischetti di fila nella
	 * configurazione corrente della griglia e somma ogni 3 dischetti di fila
	 * +10 se sono i dischetti del mio giocatore e -11 se sono i dischetti
	 * dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di tre dischetti in colonna della
	 *         configurazione della griglia.
	 */
	public static int column3(int[][] board, int nr, int nc) {
		// Trova colonne con 3 stessi dischetti
		int sum = 0;

		// Controllo dalla riga 3 alla riga 1
		for (int row = nr - 1; row >= 3; row--) {
			for (int col = 0; col < nc; col++) {
				if (board[row][col] == board[row - 1][col]
						&& board[row][col] == board[row - 2][col]
						&& board[row - 3][col] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono colonne con 4 dischetti di fila nella
	 * configurazione corrente della griglia.
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore massimo di un intero in caso di quattro
	 *         dischetti miei in fila, il valore minimo di un intero in caso di
	 *         quattro dischetti avversari in fila o 0 se non vi sono 4
	 *         dischetti di fila.
	 */
	public static int columnWin(int[][] board, int nr, int nc) {
		// Trova colonne con 4 stessi dischetti
		for (int row = nr - 1; row >= 3; row--) {
			for (int col = 0; col < nc; col++) {
				if (board[row][col] == board[row - 1][col]
						&& board[row][col] == board[row - 2][col]
						&& board[row][col] == board[row - 3][col]) {
					if (board[row][col] == 1) {
						return Integer.MAX_VALUE;
					} else if (board[row][col] == -1) {
						return Integer.MIN_VALUE;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Metodo controlla se esistono righe con 2 dischetti di fila nella
	 * configurazione corrente della griglia e somma ogni 2 dischetti di fila +3
	 * se sono i dischetti del mio giocatore e -4 se sono i dischetti
	 * dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di due dischetti in riga della
	 *         configurazione della griglia.
	 */
	public static int row2(int[][] board, int nr, int nc) {
		int sum = 0;

		for (int row = nr - 1; row >= 0; row--) {
			for (int col = 0; col <= nc - 4; col++) {
				// XXOO
				if (board[row][col] == board[row][col + 1]
						&& board[row][col + 2] == 0 && board[row][col + 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
				// OOXX
				if (board[row][col + 2] == board[row][col + 3]
						&& board[row][col] == 0 && board[row][col + 1] == 0) {
					if (board[row][col + 2] == 1) {
						sum = sum + 3;
					} else if (board[row][col + 2] == -1) {
						sum = sum - 4;
					}
				}
				// OXXO
				if (board[row][col + 1] == board[row][col + 2]
						&& board[row][col] == 0 && board[row][col + 3] == 0) {
					if (board[row][col + 1] == 1) {
						sum = sum + 3;
					} else if (board[row][col + 1] == -1) {
						sum = sum - 4;
					}
				}
				// OXOX
				if (board[row][col + 1] == board[row][col + 3]
						&& board[row][col] == 0 && board[row][col + 2] == 0) {
					if (board[row][col + 1] == 1) {
						sum = sum + 3;
					} else if (board[row][col + 1] == -1) {
						sum = sum - 4;
					}
				}
				// XOXO
				if (board[row][col] == board[row][col + 2]
						&& board[row][col + 1] == 0 && board[row][col + 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
			}
		}

		return sum;
	}

	/**
	 * Metodo controlla se esistono righe con 3 dischetti di fila nella
	 * configurazione corrente della griglia e somma ogni 3 dischetti di fila
	 * +10 se sono i dischetti del mio giocatore e -11 se sono i dischetti
	 * dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di tre dischetti in riga della
	 *         configurazione della griglia.
	 */
	public static int row3(int[][] board, int nr, int nc) {
		int sum = 0;

		for (int row = nr - 1; row >= 0; row--) {
			for (int col = 0; col < nc - 3; col++) {
				// XXXO
				if (board[row][col] == board[row][col + 1]
						&& board[row][col] == board[row][col + 2]
						&& board[row][col + 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
				// OXXX
				if (board[row][col + 1] == board[row][col + 2]
						&& board[row][col + 2] == board[row][col + 3]
						&& board[row][col] == 0) {
					if (board[row][col + 1] == 1) {
						sum = sum + 10;
					} else if (board[row][col + 1] == -1) {
						sum = sum - 11;
					}
				}
				// XOXX
				if (board[row][col] == board[row][col + 2]
						&& board[row][col] == board[row][col + 3]
						&& board[row][col + 1] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
				// XXOX
				if (board[row][col] == board[row][col + 1]
						&& board[row][col] == board[row][col + 3]
						&& board[row][col + 2] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono righe con 4 dischetti di fila nella
	 * configurazione corrente della griglia.
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore massimo di un intero in caso di quattro
	 *         dischetti miei in fila, il valore minimo di un intero in caso di
	 *         quattro dischetti avversari in fila o 0 se non vi sono 4
	 *         dischetti di fila.
	 */
	public static int rowWin(int[][] board, int nr, int nc) {
		for (int row = nr - 1; row >= 0; row--) {
			for (int col = 0; col <= nc - 4; col++) {
				if (board[row][col] == board[row][col + 1]
						&& board[row][col] == board[row][col + 2]
						&& board[row][col] == board[row][col + 3]) {
					if (board[row][col] == 1) {
						return Integer.MAX_VALUE;
					} else if (board[row][col] == -1) {
						return Integer.MIN_VALUE;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Metodo controlla se esistono diagonali verso destra con 2 dischetti di
	 * fila nella configurazione corrente della griglia e somma ogni 2 dischetti
	 * di fila +3 se sono i dischetti del mio giocatore e -4 se sono i dischetti
	 * dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di due dischetti in diagonale verso
	 *         destra della configurazione della griglia.
	 */
	public static int diagRight2(int[][] board, int nr, int nc) {
		int sum = 0;

		for (int row = nr - 1; row >= 3; row--) {
			for (int col = 0; col <= nc - 4; col++) {
				// XXOO
				if (board[row][col] == board[row - 1][col + 1]
						&& board[row - 2][col + 2] == 0
						&& board[row - 3][col + 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
				// OOXX
				if (board[row][col] == 0 && board[row - 1][col + 1] == 0
						&& board[row - 2][col + 2] == board[row - 3][col + 3]) {
					if (board[row - 2][col + 2] == 1) {
						sum = sum + 3;
					} else if (board[row - 2][col + 2] == -1) {
						sum = sum - 4;
					}
				}
				// OXXO
				if (board[row][col] == 0
						&& board[row - 1][col + 1] == board[row - 2][col + 2]
						&& board[row - 3][col + 3] == 0) {
					if (board[row - 1][col + 1] == 1) {
						sum = sum + 3;
					} else if (board[row - 1][col + 1] == -1) {
						sum = sum - 4;
					}
				}
				// XOXO
				if (board[row][col] == board[row - 2][col + 2]
						&& board[row - 1][col + 1] == 0
						&& board[row - 3][col + 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
				// OXOX
				if (board[row - 1][col + 1] == board[row - 3][col + 3]
						&& board[row][col] == 0 && board[row - 2][col + 2] == 0) {
					if (board[row - 1][col + 1] == 1) {
						sum = sum + 3;
					} else if (board[row - 1][col + 1] == -1) {
						sum = sum - 4;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono diagonali verso destra con 3 dischetti di
	 * fila nella configurazione corrente della griglia e somma ogni 3 dischetti
	 * di fila +10 se sono i dischetti del mio giocatore e -11 se sono i
	 * dischetti dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di tre dischetti in diagonale verso
	 *         destra della configurazione della griglia.
	 */
	public static int diagRight3(int[][] board, int nr, int nc) {
		int sum = 0;

		for (int row = nr - 1; row >= 3; row--) {
			for (int col = 0; col <= nc - 4; col++) {
				// XXXO
				if (board[row][col] == board[row - 1][col + 1]
						&& board[row][col] == board[row - 2][col + 2]
						&& board[row - 3][col + 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
				// OXXX
				if (board[row - 1][col + 1] == board[row - 3][col + 3]
						&& board[row - 1][col + 1] == board[row - 2][col + 2]
						&& board[row][col] == 0) {
					if (board[row - 1][col + 1] == 1) {
						sum = sum + 10;
					} else if (board[row - 1][col + 1] == -1) {
						sum = sum - 11;
					}
				}
				// XOXX
				if (board[row][col] == board[row - 2][col + 2]
						&& board[row][col] == board[row - 3][col + 3]
						&& board[row - 1][col + 1] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
				// XXOX
				if (board[row][col] == board[row - 1][col + 1]
						&& board[row][col] == board[row - 3][col + 3]
						&& board[row - 2][col + 2] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono diagonali verso destra con 4 dischetti di
	 * fila nella configurazione corrente della griglia.
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore massimo di un intero in caso di quattro
	 *         dischetti miei in fila, il valore minimo di un intero in caso di
	 *         quattro dischetti avversari in fila o 0 se non vi sono 4
	 *         dischetti di fila.
	 */
	public static int diagRightWin(int[][] board, int nr, int nc) {
		for (int row = nr - 1; row > 2; row--) {
			for (int col = 0; col < nc - 3; col++) {
				if (board[row][col] == board[row - 1][col + 1]
						&& board[row][col] == board[row - 2][col + 2]
						&& board[row][col] == board[row - 3][col + 3]) {
					if (board[row][col] == 1) {
						return Integer.MAX_VALUE;
					} else if (board[row][col] == -1) {
						return Integer.MIN_VALUE;
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Metodo controlla se esistono diagonali verso sinistra con 2 dischetti di
	 * fila nella configurazione corrente della griglia e somma ogni 2 dischetti
	 * di fila +3 se sono i dischetti del mio giocatore e -4 se sono i dischetti
	 * dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di due dischetti in diagonale verso
	 *         sinistra della configurazione della griglia.
	 */
	public static int diagLeft2(int[][] board, int nr, int nc) {
		int sum = 0;

		for (int row = nr - 1; row >= 3; row--) {
			for (int col = nc - 1; col >= 3; col--) {
				// XXOO
				if (board[row][col] == board[row - 1][col - 1]
						&& board[row - 2][col - 2] == 0
						&& board[row - 3][col - 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
				// OOXX
				if (board[row - 2][col - 2] == board[row - 3][col - 3]
						&& board[row][col] == 0 && board[row - 1][col - 1] == 0) {
					if (board[row - 2][col - 2] == 1) {
						sum = sum + 3;
					} else if (board[row - 2][col - 2] == -1) {
						sum = sum - 4;
					}
				}
				// OXXO
				if (board[row - 1][col - 1] == board[row - 2][col - 2]
						&& board[row][col] == 0 && board[row - 3][col - 3] == 0) {
					if (board[row - 1][col - 1] == 1) {
						sum = sum + 3;
					} else if (board[row - 1][col - 1] == -1) {
						sum = sum - 4;
					}
				}
				// XOXO
				if (board[row][col] == board[row - 2][col - 2]
						&& board[row - 1][col - 1] == 0
						&& board[row - 3][col - 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 3;
					} else if (board[row][col] == -1) {
						sum = sum - 4;
					}
				}
				// OXOX
				if (board[row - 1][col - 1] == board[row - 3][col - 3]
						&& board[row][col] == 0 && board[row - 2][col - 2] == 0) {
					if (board[row - 1][col - 1] == 1) {
						sum = sum + 3;
					} else if (board[row - 1][col - 1] == -1) {
						sum = sum - 4;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono diagonali verso sinistra con 3 dischetti di
	 * fila nella configurazione corrente della griglia e somma ogni 3 dischetti
	 * di fila +10 se sono i dischetti del mio giocatore e -11 se sono i
	 * dischetti dell'avversario.
	 * 
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore totale di tre dischetti in diagonale verso
	 *         sinistra della configurazione della griglia.
	 */
	public static int diagLeft3(int[][] board, int nr, int nc) {
		int sum = 0;

		for (int row = nr - 1; row >= 3; row--) {
			for (int col = nc - 1; col >= 3; col--) {
				// XXXO
				if (board[row][col] == board[row - 1][col - 1]
						&& board[row][col] == board[row - 2][col - 2]
						&& board[row - 3][col - 3] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
				// OXXX
				if (board[row][col] == 0
						&& board[row - 1][col - 1] == board[row - 2][col - 2]
						&& board[row - 1][col - 1] == board[row - 3][col - 3]) {
					if (board[row - 1][col - 1] == 1) {
						sum = sum + 10;
					} else if (board[row - 1][col - 1] == -1) {
						sum = sum - 11;
					}
				}
				// XOXX
				if (board[row][col] == board[row - 2][col - 2]
						&& board[row][col] == board[row - 3][col - 3]
						&& board[row - 1][col - 1] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
				// XXOX
				if (board[row][col] == board[row - 1][col - 1]
						&& board[row][col] == board[row - 3][col - 3]
						&& board[row - 2][col - 2] == 0) {
					if (board[row][col] == 1) {
						sum = sum + 10;
					} else if (board[row][col] == -1) {
						sum = sum - 11;
					}
				}
			}
		}
		return sum;
	}

	/**
	 * Metodo controlla se esistono diagonali verso sinistra con 4 dischetti di
	 * fila nella configurazione corrente della griglia.
	 * 
	 * @param board
	 *            Array bidimensionale che rappresenta la griglia.
	 * @param nr
	 *            Intero che indica il numero di righe della griglia.
	 * @param nc
	 *            Intero che indica il numero di colonna della griglia.
	 * @return Ritorna il valore massimo di un intero in caso di quattro
	 *         dischetti miei in fila, il valore minimo di un intero in caso di
	 *         quattro dischetti avversari in fila o 0 se non vi sono 4
	 *         dischetti di fila.
	 */
	public static int diagLeftWin(int[][] board, int nr, int nc) {
		for (int row = nr - 1; row >= 3; row--) {
			for (int col = nc - 1; col >= 3; col--) {
				if (board[row][col] == board[row - 1][col - 1]
						&& board[row][col] == board[row - 2][col - 2]
						&& board[row][col] == board[row - 3][col - 3]) {
					if (board[row][col] == 1) {
						return Integer.MAX_VALUE;
					} else if (board[row][col] == -1) {
						return Integer.MIN_VALUE;
					}
				}
			}
		}
		return 0;
	}

}
