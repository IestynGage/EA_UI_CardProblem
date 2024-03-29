package Evolution_Algorithm;

import java.util.ArrayList;
import java.util.Random;

public class Population {

    ///////////////////////
    //  Variables
    ///////////////////////


    private String fitnessFunction;
    private Double populationSize;
    private Double reSelection;
    private int generation = 0;

    ///////////////////////
    //  ArrayLists
    ///////////////////////

    private ArrayList<Chromosome> population = new ArrayList<Chromosome>();

    ///////////////////////
    //  Constructors
    ///////////////////////

    public Population(Double thePopulationSize, Double theReSelection,String theFitnessFunction){
        populationSize = thePopulationSize;
        reSelection = theReSelection;
        fitnessFunction = theFitnessFunction;
        initializePopulation();
    }

    ///////////////////////
    //  Methods
    ///////////////////////

    public Boolean hasConverged(){
        if(getBestScore()==0 || generation >= 5000){
            return true;
        } else{
            return false;
        }
    }

    public int getAverageScore(){
        int averageScore = 0;
        for (Chromosome chromosome : population) {
            averageScore += chromosome.fitnessFunctionEqual();
        }
        averageScore = averageScore / population.size();

        return averageScore;
    }

    public int getBestScore(){
        Chromosome bestChromosome = getBestChromosome();
        int bestScore = bestChromosome.fitnessFunctionEqual();
        return bestScore;
    }

    public Chromosome getBestChromosome(){
        Chromosome bestSolution = null;
        if(fitnessFunction=="Equal"){
            for (Chromosome possibleBest : population) {
                if(bestSolution==null || possibleBest.fitnessFunctionEqual() < bestSolution.fitnessFunctionEqual()){
                    bestSolution = possibleBest;
                }
            }
        } else if(fitnessFunction=="Pure") {
            for (Chromosome possibleBest : population) {
                if (bestSolution == null || possibleBest.fitnessFunctionPure() < bestSolution.fitnessFunctionPure()) {
                    bestSolution = possibleBest;
                }
            }

        }
        return bestSolution;
    }


    public int getGeneration(){
        return generation;
    }

    public Chromosome getRandomSolution(){
        Random random = new Random();
        return population.get(random.nextInt(population.size()));
    }

    public void nextGeneration(){
        rePopulate();
        termination();
        generation++;
    }

    private void initializePopulation(){
        for (int i = 0; i<populationSize; i++){
            Chromosome toBeAdded = new Chromosome();
            population.add(toBeAdded);
        }
    }

    private void rePopulate(){
        Random random = new Random();
        double amountRepopulation = (populationSize/100) * reSelection;
        for(int i = 0; i<amountRepopulation; i++){
            int size = population.size();
            Chromosome parent1 = population.get(random.nextInt(size));
            Chromosome parent2 = population.get(random.nextInt(size));
            Chromosome child = new Chromosome(parent1,parent2);
            population.add(child);

        }

    }

    private void termination(){
        ArrayList<Chromosome> startOfNextPopulation = new ArrayList<Chromosome>();
        double amountNeededToBeAdded = (populationSize) /2;
        for(int i=0;i<amountNeededToBeAdded; i++){
            Chromosome toBeAdded = getBestChromosome();
            startOfNextPopulation.add(toBeAdded);
            population.remove(toBeAdded);
        }
        for(int i=0;i<amountNeededToBeAdded; i++){
            Chromosome toBeAdded = getRandomSolution();
            startOfNextPopulation.add(toBeAdded);
            population.remove(toBeAdded);
        }
        population = startOfNextPopulation;
    }

    public String toString(){
        StringBuilder stringBuilder = new StringBuilder("--- The Population --- \n");
        stringBuilder.append("Size: " + population.size() + "\n");
        stringBuilder.append("Best Score: " + getBestScore());
        for (Chromosome chromosome : population) {
            stringBuilder.append(chromosome.toString() + "\n");
        }
        return stringBuilder.toString();
    }
}
