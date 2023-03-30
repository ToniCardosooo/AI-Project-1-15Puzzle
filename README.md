# Inteligência Artificial - 2022/2023

## Projeto 1 - Jogo dos Quinze

### Vista Geral

Este projeto foi desenvolvido no âmbito da Unidade Curricular *Inteligência Artificial*, durante o ano letivo 2022/2023, na Faculdade de Ciências da Universidade do Porto, pelos alunos António Cardoso, Bárbara Santos e Isabel Brito.

O seu principal objetivo é a implementação completa do [Jogo dos 15](https://pt.wikipedia.org/wiki/O_jogo_do_15 "Descrição do Jogo dos 15 - Wikipédia") e de diferentes algoritmos de pesquisa que procurem a solução, mediante o input dado. Para tal, foi escolhida a linguagem Java, devido à sua relação com Programação Orientada a Objetos, o facto de ser *strongly-typed*, o seu "*Garbage Collector*", entre outros motivos.

O programa foi compilado tanto em *Ubuntu 20.04 LTS* com *javac 11.0.17* como *macOS Monterey version 12.2.1* com *javac 18.0.2.1*.

### Instruções de Compilação e Execução

Para poder executar cada problema é primeiro necessário compilar todos os ficheiros java. Para tal, utilize a seguinte instrução:

`javac *.java`

Após a compilação de todos os ficheiros, é possível, finalmente, executá-los. Foram desenvolvidos os seguintes algoritmos:
- BFS
- DFS
- IDFS
- Greedy-misplaced
- Greedy-Manhattan
- A*-misplaced
- A*-Manhattan

Para a execução de cada um, utilizar o seguinte formato de entrada:
`java Game strategy < input.txt`, onde "strategy" deve ser substituido pelo nome do algoritmo desejado e "input.txt" pelo nome do ficheiro de texto onde se encontram as configurações inicial e final. É também possível, no entanto, não colocar esta última parte e inserir o input pelo terminal.

O programa irá aguardar o input. O mesmo deve ser fornecido pela ordem tabuleiro inicial, tabuleiro final.

Exemplo:

1 2 3 4 5 7 8 0 9 13 12 6 14 11 10 15

1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 0

É esperado que, após a execução do problema, seja impresso o número máximo de estados guardados em memória, o nível da solução encontrada e o tempo que o algoritmo demorou a encontrar a mesma, para além dos passos necessários para chegar ao tabuleiro final.
