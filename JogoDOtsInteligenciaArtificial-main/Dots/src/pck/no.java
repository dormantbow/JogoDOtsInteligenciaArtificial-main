package pck;

import java.util.ArrayList;

public class no {

	ArrayList<no> nosFilhos = new ArrayList<no>();

	ArrayList<Boolean> filhosDisponiveis= new ArrayList<Boolean>();

	int jogada;

	int pontosJogador1;
	int pontosJogador2;
	int jogadorAtualAK;

	int vezes=0;

	int minMax;

	public no(int jogada,ArrayList<Boolean> filhosDisponiveis) {

		this.jogada=jogada;

		this.filhosDisponiveis=filhosDisponiveis;

	}

	public void criaFilhos() {

		for(int i=0;i<filhosDisponiveis.size();i++) {

			if(filhosDisponiveis.get(i)==true) {

				filhosDisponiveis.set(i, false);

				nosFilhos.add(new no(i,filhosDisponiveis));

				nosFilhos.get(nosFilhos.size()-1).criaFilhos();

				vezes+=nosFilhos.get(nosFilhos.size()-1).nVezes();

				filhosDisponiveis.set(i, true);
				vezes++;

			}

		}
		

	}

	public int nVezes() {

		return vezes;

	}
	ArrayList<Boolean> jogadasAk= new ArrayList<Boolean>();
	public void calcularPontos(int pontosP1,int pontosP2,int jogadorAtual,ArrayList<Boolean> jogadas) {
		jogadasAk=jogadas;
		jogadasAk.set(jogada, false);
		this.jogadorAtualAK=jogadorAtual;

		int pontosMarcados=0;
		if(jogada==0||jogada==2||jogada==3||jogada==5) {
			if(!jogadasAk.get(0)) {
				if(!jogadasAk.get(2)) {
					if(!jogadasAk.get(3)) {
						if(!jogadasAk.get(5)) {
							pontosMarcados++;
							
						}
					}
				}
			}
		}

		if(jogada==1||jogada==3||jogada==4||jogada==6) {
			if(!jogadasAk.get(1)) {
				if(!jogadasAk.get(3)) {
					if(!jogadasAk.get(4)) {
						if(!jogadasAk.get(6)) {
							pontosMarcados++;
						}
					}
				}
			}
		}
		
		if(jogada==5||jogada==7||jogada==8||jogada==10) {
			if(!jogadasAk.get(5)) {
				if(!jogadasAk.get(7)) {
					if(!jogadasAk.get(8)) {
						if(!jogadasAk.get(10)) {
							pontosMarcados++;
						}
					}
				}
			}
		}

		if(jogada==6||jogada==8||jogada==9||jogada==11) {
			if(!jogadasAk.get(6)) {
				if(!jogadasAk.get(8)) {
					if(!jogadasAk.get(9)) {
						if(!jogadasAk.get(11)) {
							pontosMarcados++;
						}
					}
				}
			}
		}

		if(pontosMarcados>0) {
			if(jogadorAtual==1) {
				jogadorAtual=2;
				jogadorAtualAK=2;
			}else {
				jogadorAtual=1;
				jogadorAtualAK=1;
			}
			if(jogadorAtual==1) {
				pontosP1+=pontosMarcados;
				
			}else {
				pontosP2+=pontosMarcados;
				
			}
			this.pontosJogador1=pontosP1;
			this.pontosJogador2=pontosP2;
			int jogadorAtualAux;
			if(jogadorAtual==1) {
				jogadorAtualAux=2;
			}else {
				jogadorAtualAux=1;
			}
			if(nosFilhos.size()>0) {
				for(int i=0;i<nosFilhos.size();i++) {
					//System.out.println("filhos");
					nosFilhos.get(i).calcularPontos(pontosP1, pontosP2, jogadorAtualAux,jogadas);
				}
			}else {
				
				if(pontosP2>pontosP1) {
					minMax=1;
				}else if(pontosP2==pontosP1) {
					minMax=0;
				}else {
					minMax=-1;
				}
			}

		}else {
			this.pontosJogador1=pontosP1;
			this.pontosJogador2=pontosP2;
			int jogadorAtualAux;
			if(jogadorAtual==1) {
				jogadorAtualAux=2;
			}else {
				jogadorAtualAux=1;
			}
			if(nosFilhos.size()>0) {
				for(int i=0;i<nosFilhos.size();i++) {
					nosFilhos.get(i).calcularPontos(pontosP1, pontosP2, jogadorAtualAux,jogadas);
				}
			}else {
				if(pontosP2>pontosP1) {
					minMax=1;
				}else if(pontosP2==pontosP1) {
					minMax=0;
				}else {
					minMax=-1;
				}//System.out.println(minMax);
			}

		}
		jogadasAk.set(jogada, true);

	}
	ArrayList<Integer> posicaoFilhosDisponiveis;
	public ArrayList<Integer> miniMaxPrimeiroNo() {
		ArrayList<Integer> minimaxDosFilhos= new ArrayList<Integer>();
		posicaoFilhosDisponiveis= new ArrayList<Integer>();

		for(int i=0;i<nosFilhos.size();i++) {				
			minimaxDosFilhos.add(nosFilhos.get(i).miniMax()); 
		}			
		for(int i=0;i<minimaxDosFilhos.size();i++) {
		//	System.out.println("Nó"+i+": "+minimaxDosFilhos.get(i));
		}
		
		for(int i=0;i<minimaxDosFilhos.size();i++) {
			posicaoFilhosDisponiveis.add(nosFilhos.get(i).jogada);
		}
		System.out.println();

		return minimaxDosFilhos;
	}
	public ArrayList<Integer> retornaMiniMaxFilhos() {
		ArrayList<Integer> minimaxFilhos= new ArrayList<Integer>();
		for(int i=0;i<nosFilhos.size();i++) {
			minimaxFilhos.add(nosFilhos.get(i).minMax);
		}
		return minimaxFilhos;
	}
	public ArrayList<Integer> retornaFilhosDisponiveis() {
		ArrayList<Integer> posicaoFDisponiveis= new ArrayList<Integer>();
		for(int i=0;i<nosFilhos.size();i++) {
			posicaoFDisponiveis.add(nosFilhos.get(i).jogada);
		}
		return posicaoFDisponiveis;
	}


	public int miniMax() {
		if(jogadorAtualAK==2) {

			if(nosFilhos.size()>0) {
				for(int i=0;i<nosFilhos.size();i++) {					
					if(nosFilhos.get(i).miniMax()==1) {
						minMax=1;
						return 1;
					}
				}
				for(int i=0;i<nosFilhos.size();i++) {					
					if(nosFilhos.get(i).miniMax()==0) {
						minMax=0;
						return 0;
					}
				}
				minMax=-1;
				return -1;
			}else {
				return minMax;
			}
		}

		if(jogadorAtualAK==1) {

			if(nosFilhos.size()>0) {
				for(int i=0;i<nosFilhos.size();i++) {					
					if(nosFilhos.get(i).miniMax()==-1) {
						minMax=-1;
						return -1;
					}
				}
				for(int i=0;i<nosFilhos.size();i++) {					
					if(nosFilhos.get(i).miniMax()==0) {
						minMax=0;
						return 0;
					}
				}
				minMax=1;
				return 1;


			}else {
				return minMax;
			}
		}

		return 1;
	}

}