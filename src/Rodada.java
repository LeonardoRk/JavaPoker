import java.util.ArrayList;
import java.util.Scanner;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Rodada {
	
	private Integer qntsVaoJogar = 0;
	private Baralho baralho = null;
	private ArrayList<Carta> cartasNaMesa = null;
	

	public Rodada() {
		this.qntsVaoJogar = 0;
		this.cartasNaMesa = new ArrayList<Carta>();
		
		
		// cria o baralho, embaralha e retorna o baralho de jogo 
		// pronto para distribuir cartas e começar a rodada
		this.baralho = new Baralho();
		baralho.embaralhar();	
	}
	
	
	public void start(ArrayList<Jogador> jogadores) {
		System.out.println("INICIANDO A RODADA\n\n");
		
		distribui2Cartas(jogadores);	
		apresentaCartasDosJogadores(jogadores);
		
		decideSeSaiOuContinua(jogadores);
		
		if(this.qntsVaoJogar >= 2) {
			
			desceDuasCartasNaMesa();
			
			do {
				this.qntsVaoJogar = 0;
				
				decideSeSaiOuContinua(jogadores);
				
				if(this.qntsVaoJogar >= 2) {
					desceUmaCarta();
				}
			}while(this.qntsVaoJogar >=2 && this.cartasNaMesa.size() <= 4);	
					
		}else {
			System.out.println("A RODADA TERMINOU");
		}
		
		if(this.qntsVaoJogar == 1) {
			System.out.println("O vencedor é:" + imprimeNomeDoVencedor(jogadores));
		}else {
			
			Servico servico = new Servico(jogadores , this.cartasNaMesa);
			ArrayList<Integer> acumuladoMesaJogador = new ArrayList<Integer>();
			char nipeMaisRepetido = servico.juntaCartasDaMesaParaStraightFlush(acumuladoMesaJogador);
			
			boolean temStraight = false;
			for(int i = 0 ; i < jogadores.size() ; i++) {
				
				Jogador jogador = jogadores.get(i);
				acumuladoMesaJogador = servico.acumulaCartasJogadores(acumuladoMesaJogador ,jogador , nipeMaisRepetido);
			
				StraightFlush straightFlush = new StraightFlush(acumuladoMesaJogador);
				
				temStraight = straightFlush.temStraight();
				
				if(temStraight){
					System.out.println("FEZ STRAIGHT FLUSH:  " + jogador.getNome());
				}else {
					System.out.println("NÃO FEZ STRAIGHT FLUSH: " + jogador.getNome());
				}
				
				
				excluiCartasJogadorAtual(acumuladoMesaJogador);
			}
			
			
			
			//ArrayList<Jogador> vencedores = servico.procuraVencedor();
			
		}
	}


	private void excluiCartasJogadorAtual(ArrayList<Integer> acumuladoMesaJogador) {
		int qtdCartas = acumuladoMesaJogador.size();
		acumuladoMesaJogador.remove(qtdCartas - 1);
		qtdCartas--;
		acumuladoMesaJogador.remove(qtdCartas - 1);
	}


	private void desceUmaCarta() {
		this.cartasNaMesa.add(this.baralho.daCarta());
		apresentaCartasNaMesa();
	}


	private void desceDuasCartasNaMesa() {
		this.cartasNaMesa.add(this.baralho.daCarta());
		desceUmaCarta();
	}
	
	private void distribui2Cartas(ArrayList<Jogador> jogadores ) {
		for(int i = 0 ; i < jogadores.size() ; i++) {
					
			Carta carta1, carta2;
			
			carta1 = this.baralho.daCarta();
			carta2 = this.baralho.daCarta();
			
			jogadores.get(i).setCartaUm(carta1);
			jogadores.get(i).setCartaDois(carta2);
		}
	}
	
	private void apresentaCartasDosJogadores(ArrayList<Jogador> jogadores) {
		for(int i= 0 ; i < jogadores.size() ; i++) {
			System.out.println("---------------");
			System.out.println("Nome:" + jogadores.get(i).getNome());
			System.out.println("Carta1:" + jogadores.get(i).getCartaUm());
			System.out.println("Carta2:" + jogadores.get(i).getCartaDois());
			System.out.println("---------------");
		}
	}
	
	private void decideSeSaiOuContinua( ArrayList<Jogador> jogadores ) {
		Scanner input = null;
		input = new Scanner(System.in);
		
		for(int i = 0 ; i < jogadores.size() ; i++) {
			Jogador jogadorAtual = jogadores.get(i);
			
			System.out.println("Continua no jogo " + jogadorAtual.getNome()+ " ?");
			System.out.println("1 - MESA\n2 - CORRE");
			
			int resposta = input.nextInt();
			boolean decisao = false;
			if (resposta == 1) {
				// o jogador decidiu continuar jogando
				decisao = true;
				this.qntsVaoJogar++;
			}else {
				// não vai jogar
				decisao = false;
			}
			jogadorAtual.setDecisao(decisao);
			System.out.println(jogadorAtual.getNome() + " respondeu " + jogadorAtual.getDecisao() + "\n\n");
			System.out.println("Jogadores que continuam na rodada: " + this.qntsVaoJogar + "\n\n");
		}
	}
	
	private void apresentaCartasNaMesa() {
		System.out.println("CARTAS DA MESA\n");
		for(int i = 0 ; i < this.cartasNaMesa.size() ; i++) {
			System.out.println(this.cartasNaMesa.get(i).toString());
		}
		System.out.println("\n");
	}
	
	private String imprimeNomeDoVencedor(ArrayList<Jogador> jogadores) {
		String nomeDoVencedor = null;
		
		for(int i = 0 ; i < jogadores.size() ; i++) {
			if(jogadores.get(i).getDecisao() == true) {
				nomeDoVencedor = jogadores.get(i).getNome();
			}
		}
		return nomeDoVencedor;
	}

}
