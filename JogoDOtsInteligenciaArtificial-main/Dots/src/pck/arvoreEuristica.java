package pck;

import java.util.ArrayList;
import java.util.Random;

/*Arvore de busca heuristica */

public class arvoreEuristica {

	int primeiraJogada;
	int segundaJogada;

	noEuristico noInicial;

	noEuristico noAtual;
	noEuristico inicialSave;


	ArrayList<Boolean> filhosDisponiveis= new ArrayList<Boolean>();
	/*representação da arvore heuristica */
	public arvoreEuristica(int primeiraJogada, int segundaJogada) {
		this.primeiraJogada=primeiraJogada-1;
		this.segundaJogada=segundaJogada-1;
	}

	/*Criando nó inicial da arvore, uma lista booleanacom filhos disponiveis */
	public void criaNo() {
		for(int i=0;i<12;i++) {
			if(i!=primeiraJogada&&i!=segundaJogada) {
				filhosDisponiveis.add(true);
				//System.out.println(i);
			}
			else {
				filhosDisponiveis.add(false);
			}
			//System.out.println(i+"  "+filhosDisponiveis.get(i));

		}

		noInicial=new noEuristico(1,filhosDisponiveis);

		noInicial.criaFilhos();

	//	System.out.println(noInicial.vezes);

	}
	/*calculando os pontos dos nós na arvore
	 * iniciando a lista de jogadas possiveis
	 */
	public void calcularPts() {
		ArrayList<Boolean> jogadas= new ArrayList<Boolean>();
		for(int j=0;j<12;j++) {
			if(j!=primeiraJogada&&j!=segundaJogada) {
				jogadas.add(true);
				//System.out.println(i);
			}
			else {
				jogadas.add(false);
			}
		}
		noInicial.calcularPontos(0, 0, 2,jogadas);
		noAtual=noInicial;
	}
	/*
	 * implementação do algoritmo MinMax
	 * realização da busca na arvore
	 */
	int f=0;
	public int minMax() {
		if(f==0) {
			ArrayList<Integer>  miniMaxFilhos = new ArrayList<Integer>();
			ArrayList<Integer>  jogadaReferenteAoFilho = new ArrayList<Integer>();
			noAtual.miniMaxPrimeiroNo();
			miniMaxFilhos=noAtual.retornaMiniMaxFilhos();
			jogadaReferenteAoFilho=noAtual.retornaFilhosDisponiveis();
			ArrayList<Integer>  posicoesComUm = new ArrayList<Integer>();
			int a=0;
			int auxMaiorValor=0;
			int posMaiorValor=0;
			for(int i=0;i<miniMaxFilhos.size();i++) {
				if(miniMaxFilhos.get(i)>=1) {
					if(miniMaxFilhos.get(i)>auxMaiorValor) {
						auxMaiorValor=miniMaxFilhos.get(i);
						posMaiorValor=i;
					}
					posicoesComUm.add(i);
					a++;
				}
			}
			if(a==0) {
				for(int i=0;i<miniMaxFilhos.size();i++) {
					if(miniMaxFilhos.get(i)==0) {
						if(miniMaxFilhos.get(i)>auxMaiorValor) {
							auxMaiorValor=miniMaxFilhos.get(i);
							posMaiorValor=i;
						}
						posicoesComUm.add(i);
						a++;
					}
				}
			}
			if(a==0) {
				auxMaiorValor=-1000;
				for(int i=0;i<miniMaxFilhos.size();i++) {
					if(miniMaxFilhos.get(i)==0) {
						if(miniMaxFilhos.get(i)>auxMaiorValor) {
							auxMaiorValor=miniMaxFilhos.get(i);
							posMaiorValor=i;
						}
						posicoesComUm.add(i);
						a++;
					}
				}
			}
			printaJogadorAtual();
			//System.out.println("tamanho: "+posicoesComUm.size()+"  num: "+numero);
			//System.out.println(jogadaReferenteAoFilho.get(posicoesComUm.get(numero))+";;;;;;;;;;;;;;;;;;;;;;;");
			noAtual=noAtual.nosFilhos.get(posMaiorValor);
			printaJogadorAtual();
			f++;
			return jogadaReferenteAoFilho.get(posMaiorValor)+1;
		}else {
			//System.out.println("NO ELSE                        ===");
			ArrayList<Integer>  miniMaxFilhos = new ArrayList<Integer>();
			ArrayList<Integer>  jogadaReferenteAoFilho = new ArrayList<Integer>();
			miniMaxFilhos=noAtual.retornaMiniMaxFilhos();
			jogadaReferenteAoFilho=noAtual.retornaFilhosDisponiveis();
			ArrayList<Integer>  auxDisponiveis = new ArrayList<Integer>();
			int a=0;
			int auxMaiorValor=0;
			int posMaiorValor=0;
			for(int i=0;i<miniMaxFilhos.size();i++) {
				if(miniMaxFilhos.get(i)>=1) {
					if(miniMaxFilhos.get(i)>auxMaiorValor) {
						auxMaiorValor=miniMaxFilhos.get(i);
						posMaiorValor=i;
					}
					
					a++;
				}
			}
			if(a==0) {
				for(int i=0;i<miniMaxFilhos.size();i++) {
					if(miniMaxFilhos.get(i)==0) {
						if(miniMaxFilhos.get(i)>auxMaiorValor) {
							auxMaiorValor=miniMaxFilhos.get(i);
							posMaiorValor=i;
						}
						
						a++;
					}
				}
			}
			if(a==0) {
				auxMaiorValor=-1000;
				for(int i=0;i<miniMaxFilhos.size();i++) {
					if(miniMaxFilhos.get(i)==0) {
						if(miniMaxFilhos.get(i)>auxMaiorValor) {
							auxMaiorValor=miniMaxFilhos.get(i);
							posMaiorValor=i;
						}
					
						a++;
					}
				}
			}
			printaJogadorAtual();
			//System.out.println("tamanho: "+auxDisponiveis.size()+"  num: "+numero);
			//System.out.println(filhosDisponiveis.get(auxDisponiveis.get(numero))+";;;;;;;;;;;;;;;;;;;;;;;");
			noAtual=noAtual.nosFilhos.get(posMaiorValor);
			printaJogadorAtual();
			//System.out.println( auxDisponiveis.get(numero));
			f++;
			return jogadaReferenteAoFilho.get(posMaiorValor)+1;
		}
	}

	public void printaJogadorAtual() {
		//		System.out.println(noAtual.jogadorAtualAK);
		//		System.out.println("POntos p1: "+noAtual.pontosJogador1);
		//		System.out.println("Pontos p2: "+noAtual.pontosJogador2);
	}

	/*Selecionamos uma jogada aleatoria dentre as jogadas disponiveis */
	public int jogadaAleatoria() {
		int a=0;
		for(int i=0;i<noAtual.nosFilhos.size();i++) {
			a++;
		}
		Random random = new Random();
		int numero = random. nextInt(a);
		noAtual=noAtual.nosFilhos.get(numero);
		return noAtual.jogada+1;
	}


	public void jogadaJogador(int posicaoJogada) {
		int a=0;
		for(int i=0;i<noAtual.nosFilhos.size();i++) {
			if(noAtual.nosFilhos.get(i).jogada==posicaoJogada-1) {
				a++;
				noAtual=noAtual.nosFilhos.get(i);
			}
		}
		if(a==0) {
			System.out.println("Erro ao adicionar ");
		}else {
			System.out.println("Jogada com sucesso");
		}

	}


} 