
public class Jogador {

	private String nome;
	private Carta cartaUm;
	private Carta cartaDois;
	private Boolean decisao;
	
	public Jogador(String nome) {
		this.nome = nome;
	}
	
	
	public Carta getCartaUm() {
		return cartaUm;
	}
	public void setCartaUm(Carta cartaUm) {
		this.cartaUm = cartaUm;
	}
	public Carta getCartaDois() {
		return cartaDois;
	}
	public void setCartaDois(Carta cartaDois) {
		this.cartaDois = cartaDois;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public Boolean getDecisao() {
		return decisao;
	}


	public void setDecisao(Boolean decisao) {
		this.decisao = decisao;
	}
	
	
	
	
}
