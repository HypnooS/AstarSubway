
LEIAME
-----------------------------------------------------------------------------------
A classe MainProblem é classe aonde se controla as matrizes e os tipos de busca.
Para realizar uma busca manualmente, é necessário:

0) Retire o bloco Switch de interação.</br>
1) Iniciar a matriz.</br>
2) Iniciar as estações para cada problema respectivo.</br>
3) Criar a busca.</br>
4) Criar um Thread para busca.</br>
5) Inicar o Thread.</br>
Assim como o exemplo abaixo:</br>
(0)</br>
/*switch(choose){</br>
            case 1:</br>
                matrixStation = createMatrixForProblem1();</br>
                System.out.println("Choose the problem to resolve [1 to 4AM or 2 to 6PM]");</br>
                choose = scanner.nextInt();</br>
                switch(choose){</br>
....</br>
if(se.equals("ASTAR")){</br>
            Astar search = new Astar(stations, matrixStation, startStation, endStation);</br>
            Thread searchThread = new Thread(search);</br> 
            searchThread.setPriority(5);</br>
            searchThread.start();</br>
        }</br>
        else{</br>
            System.err.println("No search type detect!!!");</br>
        }</br>
}
*/
(1)
matrixStation = createMatrixForProblem1(); (Opções: createMatrixForProblem1(),createMatrixForProblemBackTracking(), createMatrixForProblem2())

(2)
createSubwayProblem1At4AM(stations); (Opções: createSubwayProblem1At4AM(stations), createSubwayProblem1At6PM(stations), createSubwayProblem2At4AM(stations), createSubwayProblem1At6PM(stations))
OBS: Para a matrix ProblemBackTracking, o conjunto de estações é createSubwayProblem1At4AM(stations).

(3)
GreedyBestFirst search = new GreedyBestFirst(stations, matrixStation, startStation,endStation); (Opções:Astar e GreedyBestFirst)

(4)(5)
Thread searchThread = new Thread(search);      
searchThread.setPriority(5);
searchThread.start();

ATENÇÃO:
A classe BFS é uma classe antiga (@Deprecated), ela somente esta presente para demonstrar a evolução do código GreedyBestFirst.

