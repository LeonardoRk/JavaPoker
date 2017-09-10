import java.util.ArrayList;
import java.util.Scanner;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

public class Main {

	public static void main(String[] args) {
		System.out.println("\nIniciando o jogo\n");
		
		Jogo jogo = null;
		jogo = new Jogo();
		
		if(jogo != null) {
			jogo.start();
		}else {
			// nothing to do in here
		}
			
	}
}
