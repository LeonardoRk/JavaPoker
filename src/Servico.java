import java.util.ArrayList;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public final class Servico {

	private ArrayList<Jogador> jogadores = null;
	private ArrayList<Carta> cartasDaMesaOrdenadas = null;
	
	
	public Servico(ArrayList<Jogador> _jogadores , ArrayList<Carta> _cartasDaMesa) {
		this.jogadores = _jogadores;
		this.cartasDaMesaOrdenadas = new ArrayList<Carta>();
		ordenaCartasDaMesa(_cartasDaMesa);
	}
	
	public ArrayList<Carta> juntaCartasJogadorMesa(Jogador jogador, 
														ArrayList<Carta> cartaDaMesa){
		ArrayList<Carta> cartasJuntas = new ArrayList<Carta>();
		for(int i = 0 ; i < cartaDaMesa.size() ; i++) {
			cartasJuntas.add(cartaDaMesa.get(i));
		}
		cartasJuntas.add(jogador.getCartaUm());
		cartasJuntas.add(jogador.getCartaDois());
		return cartasJuntas;
	}
		
	public char juntaCartasDaMesaParaStraightFlush(ArrayList<Integer>acumuladoDaMesa) {
		
		int qtdNipePaus = 0 , qtdNipeCopas = 0 , qtdNipeEspada = 0 , qtdNipeOuro = 0;
		// paus, copas,  espada, ouro
		// \u2663 \u2764 \u2660 \u2666 
		
		final char PAUS = (char) '\u2663' , COPAS =(char) '\u2764' ,  
					ESPADA = (char) '\u2660' , OURO = (char) '\u2666' ; 

		Carta carta = null;
		for(int i= 0 ; i < this.cartasDaMesaOrdenadas.size(); i++) {
			carta = this.cartasDaMesaOrdenadas.get(i);
			if(carta.getNaipe().equals(PAUS)){ // naipe de paus
				qtdNipePaus++;
			}else if(carta.getNaipe().equals(COPAS)) { // naipe de copas
				qtdNipeCopas++;
			}else if(carta.getNaipe().equals(ESPADA)) { // naipe de espada
				qtdNipeEspada++;
			}else {
				// naipe de ouro
				qtdNipeOuro++;
			}
		}
		
		final char EMPTY = ' ';
		final int MIN_PARA_STAIGHT_FLUSH = 3;
		
		char nipeMaisRepetido = EMPTY;
		
		if(qtdNipeCopas >= MIN_PARA_STAIGHT_FLUSH) {
			nipeMaisRepetido = COPAS;
		}else if(qtdNipeEspada >= MIN_PARA_STAIGHT_FLUSH ) {
			nipeMaisRepetido = ESPADA;
		}else if(qtdNipeOuro >= MIN_PARA_STAIGHT_FLUSH ) {
			nipeMaisRepetido = OURO;
		}else if(qtdNipePaus >= MIN_PARA_STAIGHT_FLUSH) {
			nipeMaisRepetido = PAUS;
		}else {
			// nothing to do
		}
			
		for(int i = 0 ; i < this.cartasDaMesaOrdenadas.size(); i++) {
			adicionaCartasComMaiorNaipeEmAcumulado(nipeMaisRepetido, acumuladoDaMesa, i);	
		}
		
		return nipeMaisRepetido;
	}
	
	public ArrayList<Integer> acumulaCartasJogadores(ArrayList<Integer> acumuladoMesaJogador ,
										Jogador jogador , char nipeMaisRepetido) {
		
		if(jogador.getCartaUm().getNaipe() == nipeMaisRepetido) {
			String valorString = jogador.getCartaUm().getValor();
			if(isNumeric(valorString)) {
				int convertido = converteValorCartaParaInteiro(valorString);
				acumuladoMesaJogador.add(convertido);
			}
		}
		
		if(jogador.getCartaDois().getNaipe() == nipeMaisRepetido) {
			String valorString = jogador.getCartaDois().getValor();
			if(isNumeric(valorString)) {
				int convertido = converteValorCartaParaInteiro(valorString);
				acumuladoMesaJogador.add(convertido);
			}
		}
		
		return acumuladoMesaJogador;	
	}
	
	private int converteValorCartaParaInteiro(String valorString) {
		int valorConvertido = 0;
		
		if(isNumeric(valorString)) {
			valorConvertido = Integer.parseInt(valorString);
		}else {
			if(valorString.equals("A")){
				valorConvertido = 14;
			}else if(valorString.equals("J")) {
				valorConvertido = 11;
			}else if(valorString.equals("Q")) {
				valorConvertido = 12;
			}else if(valorString.equals("K")){
				valorConvertido = 13;
			}else{
				
			}
		}
		return valorConvertido;
	}

	private void adicionaCartasComMaiorNaipeEmAcumulado(char nipeMaisRepetido, ArrayList<Integer> acumuladoMesa, int i) {
		if(this.cartasDaMesaOrdenadas.get(i).getNaipe() == nipeMaisRepetido) {
			
			int numberToAdd = 0;
			String valorDaCarta = this.cartasDaMesaOrdenadas.get(i).getValor();
			if(valorDaCarta.equals("A")) {
				numberToAdd = 14;
			}else if(valorDaCarta.equals("J")) {
				numberToAdd = 11;
			}else if(valorDaCarta.equals("Q")) {
				numberToAdd = 12;
			}else if(valorDaCarta.equals("K")) {
				numberToAdd = 13;
			}else {
				numberToAdd = Integer.parseInt(valorDaCarta);
			}
			
			acumuladoMesa.add(numberToAdd);
		}
	}
	

	private ArrayList<Jogador> procuraStraightFlushParaJogadores(){
		ArrayList<Jogador> jogadoresComStraightFlush = new ArrayList<Jogador>();
		
		Jogador jogador = null;
		for(int i = 0 ; i < this.jogadores.size() ; i++) {
			jogador = this.jogadores.get(i);
			
			
		}
		return jogadoresComStraightFlush;
	}
		
	
	
	private void ordenaCartasDaMesa(ArrayList<Carta> _cartasDaMesa) {
		ArrayList<Carta> cartasDaMesa = _cartasDaMesa;
		
		ArrayList<Carta> numericosTemporario = new ArrayList<Carta>();
		
		// pega numeros do baralho
		adiconaNumerosEmTemporario(cartasDaMesa, numericosTemporario);
					
		// ordena numeros em baralho temporario
		ordenaNumerosEmTemporario(numericosTemporario);
		
		// passa temporario para o ordenado definitivo
		insere_numeros_ordenados(numericosTemporario);
	
		// insere J em baralho ordenado
		insereLetrasEmOrdenado(cartasDaMesa, "J");
		
		// insere Q em baralho ordenado
		insereLetrasEmOrdenado(cartasDaMesa, "Q");
				
		// insere K em baralho ordenado
		insereLetrasEmOrdenado(cartasDaMesa, "K");
		
		// insere K em baralho ordenado
		insereLetrasEmOrdenado(cartasDaMesa, "A");
		
	}
	
	public void imprimeCartasOrdenadas() {
		for(int i = 0; i < this.cartasDaMesaOrdenadas.size() ; i++) {
			System.out.println(this.cartasDaMesaOrdenadas.get(i) + " ");
		}
	}

	private void insereLetrasEmOrdenado(ArrayList<Carta> cartasDaMesa, String carta) {
		for(int i = 0 ; i < cartasDaMesa.size() ; i++) {
			if(cartasDaMesa.get(i).getValor().equals(carta)) {
				this.cartasDaMesaOrdenadas.add(cartasDaMesa.get(i));
				cartasDaMesa.remove(i);
			}
		}
	}

	private void insere_numeros_ordenados(ArrayList<Carta> numericosTemporario) {
		for(int i = 0 ; i < numericosTemporario.size() ; i++) {
			this.cartasDaMesaOrdenadas.add(numericosTemporario.get(i));
		}
	}

	private void ordenaNumerosEmTemporario(ArrayList<Carta> numericosTemporario) {
		Carta aux;
		
		for(int i = 0 ; i < numericosTemporario.size() - 1 ; i++) {
			for(int j = i+1 ; j < numericosTemporario.size() ; j++) {
				if(Integer.parseInt(numericosTemporario.get(i).getValor()) > 
					Integer.parseInt(numericosTemporario.get(j).getValor())) {
					aux = numericosTemporario.get(i);
					
					numericosTemporario.add(i , numericosTemporario.get(j));
					numericosTemporario.remove(i+1);
					
					numericosTemporario.add(j , aux);
					numericosTemporario.remove(j+1);
				}
			}
		}
	}

	private void adiconaNumerosEmTemporario(ArrayList<Carta> cartasDaMesa, ArrayList<Carta> numericosTemporario) {
		
		for(int i = 0 ; i < cartasDaMesa.size() ; i++) {
			final boolean isNumeric = isNumeric(cartasDaMesa.get(i).getValor().toString());
			
			if(isNumeric) {
				numericosTemporario.add(cartasDaMesa.get(i));
			}
		}
	}
	
	
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}
