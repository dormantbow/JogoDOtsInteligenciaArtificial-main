package pck;

import java.util.ArrayList;
import java.util.Random;

public class arvore {

    int primeiraJogada;
    int segundaJogada;

    no noInicial;

    no noAtual;
    no inicialSave;

    ArrayList<Boolean> filhosDisponiveis= new ArrayList<Boolean>();

    // Construtor da classe
    public arvore(int primeiraJogada, int segundaJogada) {
        this.primeiraJogada=primeiraJogada-1;
        this.segundaJogada=segundaJogada-1;
    }

    // Método para criar o nó inicial da árvore
    public void criaNo() {
        for(int i=0;i<12;i++) {
            if(i!=primeiraJogada&&i!=segundaJogada) {
                filhosDisponiveis.add(true);
            }
            else {
                filhosDisponiveis.add(false);
            }
        }

        noInicial=new no(1,filhosDisponiveis);

        noInicial.criaFilhos();
    }

    // Método para calcular os pontos dos nós da árvore
    public void calcularPts() {
        ArrayList<Boolean> jogadas= new ArrayList<Boolean>();
        for(int j=0;j<12;j++) {
            if(j!=primeiraJogada&&j!=segundaJogada) {
                jogadas.add(true);
            }
            else {
                jogadas.add(false);
            }
        }
        noInicial.calcularPontos(0, 0, 2,jogadas);
        noAtual=noInicial;
    }

    int f=0;

    // Método para implementar o algoritmo Minimax
    public int minMax() {
        if(f==0) {
            ArrayList<Integer>  miniMaxFilhos = new ArrayList<Integer>();
            ArrayList<Integer>  jogadaReferenteAoFilho = new ArrayList<Integer>();
            noAtual.miniMaxPrimeiroNo();
            miniMaxFilhos=noAtual.retornaMiniMaxFilhos();
            jogadaReferenteAoFilho=noAtual.retornaFilhosDisponiveis();
            ArrayList<Integer>  posicoesComUm = new ArrayList<Integer>();
            int a=0;
            for(int i=0;i<miniMaxFilhos.size();i++) {
                if(miniMaxFilhos.get(i)==1) {
                    posicoesComUm.add(i);
                    a++;
                }
            }
            if(a==0) {
                for(int i=0;i<miniMaxFilhos.size();i++) {
                    if(miniMaxFilhos.get(i)==0) {
                        posicoesComUm.add(i);
                        a++;
                    }
                }
            }
            if(a==0) {
                for(int i=0;i<miniMaxFilhos.size();i++) {
                    if(miniMaxFilhos.get(i)==0) {
                        posicoesComUm.add(i);
                        a++;
                    }
                }
            }
            Random random = new Random();
            int numero = random. nextInt(posicoesComUm.size());
            printaJogadorAtual();
            noAtual=noAtual.nosFilhos.get(posicoesComUm.get(numero));
            printaJogadorAtual();
            f++;
            return jogadaReferenteAoFilho.get(posicoesComUm.get(numero))+1;
        } else {
            ArrayList<Integer>  aux = new ArrayList<Integer>();
            ArrayList<Integer>  filhosDisponiveis = new ArrayList<Integer>();
            aux=noAtual.retornaMiniMaxFilhos();
            filhosDisponiveis=noAtual.retornaFilhosDisponiveis();
            ArrayList<Integer>  auxDisponiveis = new ArrayList<Integer>();
            int a=0;
            for(int i=0;i<aux.size();i++) {
                if(aux.get(i)==1) {
                    auxDisponiveis.add(i);
                    a++;
                }
            }
            if(a==0) {
                for(int i=0;i<aux.size();i++) {
                    if(aux.get(i)==0) {
                        auxDisponiveis.add(i);
                        a++;
                    }
                }
            }
            if(a==0) {
                for(int i=0;i<aux.size();i++) {
                    if(aux.get(i)==0) {
                        auxDisponiveis.add(i);
                        a++;
                    }
                }
            }
            printaJogadorAtual();
            Random random = new Random();
            int numero = random. nextInt(auxDisponiveis.size());
            noAtual=noAtual.nosFilhos.get(auxDisponiveis.get(numero));
            printaJogadorAtual();
            f++;
            return filhosDisponiveis.get(auxDisponiveis.get(numero))+1;
        }
    }

    // Método para imprimir informações sobre o jogador atual
    public void printaJogadorAtual() {
        //System.out.println(noAtual.jogadorAtualAK);
        //System.out.println("Pontos p1: "+noAtual.pontosJogador1);
        //System.out.println("Pontos p2: "+noAtual.pontosJogador2);
    }
    
    // Método para realizar uma jogada aleatória
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
    
    // Método para realizar uma jogada do jogador
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
        } else {
            System.out.println("Jogada com sucesso");
        }
    }
}
