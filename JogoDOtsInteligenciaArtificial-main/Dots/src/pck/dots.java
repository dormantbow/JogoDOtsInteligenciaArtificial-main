package pck;

import java.util.Scanner;

public class dots {
	int posicaoJodgada;

	int tamanho=5;

	int jogador=1;

	int pontosP1=0;

	int pontosP2=0;

	boolean quadrado1=false;

	boolean quadrado2=false;

	boolean quadrado3=false;

	boolean quadrado4=false;

	char matriz[][] = new char[5][5];



	public void iniciaMatriz() {

		for(int i=0;i<tamanho;i+=2) {

			for(int j=0;j<tamanho;j+=2) {

				matriz[i][j]='.';

			}

		}

		for(int i=0;i<tamanho;i+=2) {

			for(int j=1;j<tamanho;j+=2) {

				matriz[i][j]=' ';

			}

		}

		for(int i=1;i<tamanho;i+=2) {

			for(int j=0;j<tamanho;j+=2) {

				matriz[i][j]=' ';

			}

		}

		for(int i=1;i<tamanho;i+=2) {

			for(int j=1;j<tamanho;j+=2) {

				matriz[i][j]=' ';

			}

		}

		for(int i=0;i<tamanho;i++) {

			for(int j=0;j<tamanho;j++) {

				System.out.print(matriz[i][j]);

			}

			System.out.println(" ");

		}

	}int primeiraJogada;
	int segundaJogada;
	int vez=0;
	arvore arvo;
	Scanner entradaProf;	
	int profundidade;
	int nDeJogadas=0;
	public void jogo() {	
		System.out.println("Qual a profundidade desejada?");
		entradaProf = new Scanner(System.in);

		profundidade= entradaProf.nextInt();

		iniciaMatriz();
		System.out.println("Vez da IA");

		//nDeJogadas++;
	//	System.out.println(1);

		entradaDoUsuario(1);
		tabuleiro();
		//primeiraJogada=posicaoJodgada;
		System.out.println("Vez do jogador");
		//nDeJogadas++;
		entradaDoUsuario(46);
		tabuleiro();
		//segundaJogada=posicaoJodgada;
		arvo = new arvore(primeiraJogada,segundaJogada);
		//System.out.println("criar arvvore");
		arvo.criaNo();
		//System.out.println("criado");
		arvo.calcularPts();
		//System.out.println("Pontos calculados!");
		//arvo.minMax();
		jogador=2;
		while(true) {

			System.out.println("\nP1: "+pontosP1+" P2: "+pontosP2);
			if(jogador==1) {
				System.out.println("\nVez do jogador ");
			}else {
				System.out.println("\nVez da IA! ");
			}

			if(jogador==1) {
				while(entradaDoUsuario(46)==false) {

				}
				nDeJogadas++;
			}else {

				if(nDeJogadas<profundidade) {
					int aux=arvo.minMax();
					//System.out.println("AUX:   "+aux);
					entradaDoUsuario(aux);
					nDeJogadas++;
				}else {
					entradaDoUsuario(arvo.jogadaAleatoria());
				}
			}
			int quadrados=buscaQuadrados();

			if(quadrados!=0) {

				if(quadrados==1) {

					System.out.println("Jogador "+jogador+" marcou "+quadrados+" ponto \n");

				}else {

					System.out.println("Jogador "+jogador+" marcou "+quadrados+" pontos \n");

				}

			}

			if(jogador==1) {

				if(quadrados==0) {

					jogador=2;

				}else{

					pontosP1+=quadrados;

				}

			}else {

				if(quadrados==0) {

					jogador=1;

				}else{

					pontosP2+=quadrados;

				}

			}

			if(verificaSeAcabou()==true) {

				System.out.println("-------------------------------------------");

				if(pontosP1>pontosP2) {

					System.out.println("Jogador 1 ganhou de "+pontosP1+" a "+pontosP2);

				}else if(pontosP2>pontosP1) {

					System.out.println("Jogador 2 ganhou de "+pontosP2+" a "+pontosP1);

				}else {

					System.out.println("O jogo empatou");

				}

				break;

			}

		}

	}
	public void tabuleiro() {
		for(int i=0;i<tamanho;i++) {

			for(int j=0;j<tamanho;j++) {

				System.out.print(matriz[i][j]);

			}

			System.out.println(" ");

		}
	}

	public int[] posicaoPonto(int ponto) {

		int[][] pontos= {{},{0,0,2,0},{2,0,4,0},{0,0,0,2},{2,0,2,2},{4,0,4,2},{0,2,2,2},{2,2,4,2},{0,2,0,4},{2,2,2,4},{4,2,4,4},{0,4,2,4},{2,4,4,4}};

		return pontos[ponto];

	}

	public boolean entradaDoUsuario(int a) {

		boolean adicionado;
		Scanner entrada;
		int ponto1;
		if(a!=46) {
			ponto1=a;
			if(vez<2) {
				if(vez==0) {
					primeiraJogada=ponto1;
				}else {
					segundaJogada=ponto1;
				}
			}
			if(vez>=2) {

			}
			vez++;

			//System.out.println("Jogou aquiiii: "+a );
			//System.out.println("Num na funcao: "+ponto1);
		}else {
			entrada = new Scanner(System.in);
			ponto1= entrada.nextInt();
			while(ponto1<1||ponto1>12) {

				System.out.println("Número invalido, digite novamente!");

				ponto1 = entrada.nextInt();

			}

			if(vez<2) {
				if(vez==0) {
					primeiraJogada=ponto1;
				}else {
					segundaJogada=ponto1;
				}
			}
			if(vez>=2) {
				arvo.jogadaJogador(ponto1);
			}
			vez++;

		}






		posicaoJodgada=ponto1;

		int p1H,p1V,p2H,p2V;

		int pontos[]=posicaoPonto(ponto1);

		p1H=pontos[0];

		p1V=pontos[1];

		p2H= pontos[2];

		p2V= pontos[3];

		adicionado=adicionarPonto(p1V, p1H, p2V, p2H);

		return adicionado;

	}

	public boolean adicionarPonto(int ponto1V,int ponto1H,int ponto2V,int ponto2H) {

		if(ponto1V==ponto2V) {

			if(ponto1H==ponto2H-2) {

				if(matriz[ponto1V][ponto1H+1]==' ') {matriz[ponto1V][ponto1H+1]='-';return true;}

				else {System.out.println("Ponto ja esta marcado");return false;}

			}else if(ponto2H==ponto1H-2) {

				if(matriz[ponto1V][ponto2H+1]==' ') {matriz[ponto1V][ponto2H+1]='-';return true;}

				else {System.out.println("Ponto ja esta marcado");return false;}

			}else {

				System.out.println("erro ao adicionar");return false;

			}

		}else if(ponto1H==ponto2H){

			if(ponto1V==ponto2V-2) {

				if(matriz[ponto1V+1][ponto2H]==' ') {matriz[ponto1V+1][ponto2H]='|';return true;}

				else {System.out.println("Ponto ja esta marcado");return false;}

			}else if(ponto1V-2==ponto2H) {

				if(matriz[ponto2V+1][ponto2H]==' ') {matriz[ponto2V+1][ponto2H]='|';return true;}

				else {System.out.println("Ponto ja esta marcado");return false;}

			}else {

				System.out.println("erro ao adicionar");return false;

			}

		}else {

			System.out.println("Ponto invalido");return false;

		}

	}

	public int buscaQuadrados() {

		int quadradosFormados=0;

		if(quadrado1==false) {

			if(matriz[1][2]=='|') {

				if(matriz[1][0]=='|') {

					if(matriz[0][1]=='-') {

						if(matriz[2][1]=='-') {

							//System.out.println("Quadrado formado");

							matriz[1][1]='x';

							quadrado1=true;

							quadradosFormados++;

						}

					}

				}

			}

		}

		if(quadrado2==false) {

			if(matriz[1][2]=='|') {

				if(matriz[1][4]=='|') {

					if(matriz[0][3]=='-') {

						if(matriz[2][3]=='-') {

							//System.out.println("Quadrado formado");

							matriz[1][3]='x';

							quadrado2=true;

							quadradosFormados++;

						}

					}

				}

			}

		}

		if(quadrado3==false) {

			if(matriz[3][2]=='|') {

				if(matriz[3][0]=='|') {

					if(matriz[2][1]=='-') {

						if(matriz[4][1]=='-') {

							//System.out.println("Quadrado formado");

							matriz[3][1]='x';

							quadrado3=true;

							quadradosFormados++;

						}

					}

				}

			}

		}

		if(quadrado4==false) {

			if(matriz[3][2]=='|') {

				if(matriz[3][4]=='|') {

					if(matriz[2][3]=='-') {

						if(matriz[4][3]=='-') {

							//System.out.println("Quadrado formado");

							matriz[3][3]='x';

							quadrado4=true;

							quadradosFormados++;

						}

					}

				}

			}

		}

		for(int i=0;i<tamanho;i++) {

			for(int j=0;j<tamanho;j++) {

				System.out.print(matriz[i][j]);

			}

			System.out.println(" ");

		}

		return quadradosFormados;

	}

	public boolean verificaSeAcabou() {

		for(int i=0;i<5;i++) {

			for(int j=0;j<5;j++) {

				if(matriz[i][j]==' ') {

					return false;

				}

			}

		}return true;

	}

}