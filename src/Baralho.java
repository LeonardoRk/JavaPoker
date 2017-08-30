
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;


public class Baralho {
	
	// paus, copas,espada, ouro
	char[] naipes = {(char)'\u2663' , (char)'\u2764' , (char)'\u2660' , (char)'\u2666'};
	  
	// valores que as cartas podem assumir no baralho
	String[] valores = {"2","3","4","5","6","7","8","9","10","J","Q","K","A"};	
	
	ArrayList<Carta> cartas = null;

	public Baralho() {
		this.cartas = new ArrayList<Carta>();
		
		// adiciona todas as cartas existentes de um baralho em cartas
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 13; j++){
				Carta carta = new Carta( this.naipes[i] , this.valores[j]);
				this.cartas.add(carta);	
			}	
		}
	}
	
	public void embaralhar() {
		ArrayList<Carta> embaralhado = new ArrayList<Carta>();
		
		int quantidade_de_escolhas = 52;
		
		Random random = new Random();
		do {
			int posicaoSelecionada = random.nextInt(quantidade_de_escolhas);
			Carta provisoria = this.cartas.get(posicaoSelecionada);
			embaralhado.add(provisoria);
			this.cartas.remove(posicaoSelecionada);		
			quantidade_de_escolhas--;
		}
		while(quantidade_de_escolhas > 0);
		this.cartas = embaralhado;
	}

	public Carta daCarta(){
		Carta carta = this.cartas.get(0);
		this.cartas.remove(0);
		return carta;	
	}

}
