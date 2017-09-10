import java.util.ArrayList;import com.sun.javafx.util.TempState;

public class StraightFlush {

	private ArrayList<Integer> acumulado = null;
	final int MIN_TO_STRAIGHT = 5;
	
	public StraightFlush(ArrayList<Integer> _cartas) {
		this.acumulado = _cartas;
	}
	
	public boolean temStraight() {
		boolean temSequencia = false;
		if(this.acumulado.size() < MIN_TO_STRAIGHT) {
			temSequencia = false;
		}else {
			temSequencia = procuraSeTemSequencia();
		}
		return temSequencia;
		
	}
	
	public boolean procuraSeTemSequencia() {
		int menor = achaMenor();
		int cartaAtual = menor;
		boolean temSequencia = false;
		final int REST_OF_NUMBERS_TO_FLUSH = 5;
		
		for(int i = 0 ; i < REST_OF_NUMBERS_TO_FLUSH ; i++) {
			if(this.acumulado.contains(cartaAtual+1)) {
				cartaAtual = menor++;
				temSequencia = true;
			}else {
				temSequencia = false;
			}
		}
		return temSequencia;
	}

	private int achaMenor() {
		int menorCarta = 1000;
		for(int i = 0 ; i < this.acumulado.size(); i++) {
			if(this.acumulado.get(i) < menorCarta) {
				menorCarta = this.acumulado.get(i);
			}
		}
		return menorCarta;
	}
	
}
