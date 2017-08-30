

public class Carta {

	private Character naipe;
	private String valor;
	
	public Carta() {
		
	}
	
	public Carta(Character naipe , String valor) {
		this.naipe = naipe;
		this.valor = valor;
	}

	public Character getNaipe() {
		return naipe;
	}

	public void setNaipe(Character naipe) {
		this.naipe = naipe;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		String carta = "|" + this.valor + " " +this.naipe + "|";
		return carta ;
	}
	
}
