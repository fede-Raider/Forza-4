# Forza-4
Sviluppo di un’ intelligenza artificiale che cerca di vincere le partite del gioco Forza 4 mediante l’algoritmo di minimax, con alcuni accorgimenti per le performance come l’implementazione dell’algoritmo di alpha-beta pruning.

## Interfaccia giocatore
I vari giocatori implementano l’interfaccia Player, il cui codice è il seguente.
```java
public interface Player {
  public void start(int nr, int nc);
  public int move ();
  public void tellMove(int c);
}
```
Il metodo start viene invocato all’inizio della partita per comunicare al giocatore il numero di righe e il numero di colonne della griglia.
Il metodo move restituisce un valore intero non negativo e inferiore al numero di colonne, che specifica la colonna in cui il giocatore desidera inserire il proprio disco.
Il metodo tellMove serve a comunicare a un giocatore la mossa fatta dall’avversario: pertanto, il valore dell’argomento sarà un numero intero non negativo e inferiore al numero di colonne.
