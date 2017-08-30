import java.util.ArrayList;
import java.util.Scanner;

public class Jogo {
	
	private ArrayList<Jogador> jogadores = null;
	
	public Jogo() {	
	}
	
	public void start() {
		//cria os players para o jogo poder começar
		criaJogadores();
		
		Rodada rodada = new Rodada();
		if(rodada != null) {
			rodada.start(this.jogadores);
		}
		
	}
	
	private void criaJogadores(){
		Scanner input = null;
		input = new Scanner(System.in);
		
		System.out.println("Digite o número de jogadores e tecle enter:");
		int qtdDePlayers = Integer.parseInt(input.nextLine());
		
		this.jogadores = new ArrayList<Jogador>();
		
		for(int i = 1 ; i <= qtdDePlayers ; i++) {
			System.out.println("Digite o nome do jogador:" + i);
			String nome = input.nextLine();
			Jogador jogador = new Jogador(nome);
			this.jogadores.add(jogador);
		}
		
		System.out.println("Qtd de players: " + this.jogadores.size());
		System.out.println("JOGADORES CRIADOS COM SUCESSO\n\n");
	}
}
