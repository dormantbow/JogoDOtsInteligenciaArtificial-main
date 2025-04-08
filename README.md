Introdução
O objetivo deste trabalho foi implementar uma inteligência artificial que joga o jogo dos dots e nunca perde. Para isso, foram criadas três classes principais: Board, Árvore e IA.
•	Board
A classe Board controla todo o tabuleiro e desenvolvimento do jogo. Ela é responsável por manter o estado atual do tabuleiro, executar as jogadas dos jogadores, verificar se o jogo terminou e calcular os pontos de cada jogador. O tabuleiro é representado como uma matriz de pontos, em que cada ponto pode estar vazio, ocupado pelo jogador humano ou pela IA.
•	Árvore
A classe Árvore cria uma árvore enésima de possibilidades de jogadas a partir do estado atual do tabuleiro. Para isso, ela utiliza uma estrutura de dados do tipo árvore, em que cada nó representa um possível estado do jogo. A partir desta árvore, é possível executar o algoritmo minimax para atribuir valores a cada nó.
•	Inteligência Artificial
A classe IA é responsável por escolher a melhor jogada para a IA a partir da árvore criada pela classe Árvore. Ela utiliza o algoritmo minimax para avaliar cada possível jogada, escolhendo a que oferece a maior probabilidade de vitória. 
Criação da Árvore de Possibilidades
A criação da árvore de possibilidades de todas as jogadas foi um passo fundamental para o desenvolvimento do algoritmo de IA capaz de jogar o jogo Dots de forma estratégica. Essa árvore é criada a partir do estado atual do jogo, onde cada nó representa uma jogada possível e suas subárvores representam as jogadas possíveis em resposta a essa jogada. Dessa forma, é possível explorar todas as possibilidades de jogadas e escolher a melhor opção para cada situação. Para criar essa árvore, foi utilizada a técnica de busca em profundidade e cada nó da árvore contém informações sobre o estado do jogo após a jogada realizada e uma pontuação atribuída pelo algoritmo Minimax. 
Algoritmo Minimax
Foram implementados dois algoritmos de minimax: um que retorna +1, -1 ou 0, dependendo do resultado da IA, e outro algoritmo heurístico que contabiliza os pontos e retorna a diferença de pontos entre a IA e o jogador humano. O primeiro algoritmo é mais simples e direto, enquanto o segundo é mais sofisticado e leva em consideração a situação atual do jogo.
Conclusão
A implementação da IA no jogo dos dots mostra como as técnicas de inteligência artificial podem ser aplicadas com sucesso em jogos simples, como o dots, e permitir que a máquina jogue de forma estratégica e nunca perca ou empate.
A estratégia da IA baseia-se no algoritmo Minimax, que avalia as possíveis jogadas com antecedência e seleciona a melhor opção possível. O uso do algoritmo minimax em conjunto com a heurística para avaliar a pontuação dos movimentos garante que a IA faça escolhas ótimas e minimize as chances de perder ou empatar.
