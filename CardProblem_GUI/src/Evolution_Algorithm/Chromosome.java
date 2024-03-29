package Evolution_Algorithm;

import java.util.Random;

public class Chromosome {

    ///////////////////////
    //  Constants
    ///////////////////////

    final static private int SOLUTION_SIZE = 10;

    ///////////////////////
    //  Array
    ///////////////////////

    private PileType[] solution = new PileType[SOLUTION_SIZE];

    ///////////////////////
    //  Constructors
    ///////////////////////

    public Chromosome(Chromosome parent1,Chromosome parent2){
        setHelixPart(0,2,parent1.getSolution());
        setHelixPart(3,6,parent2.getSolution());
        setHelixPart(7,9,parent1.getSolution());
        mutate();
    }

    public Chromosome(PileType[] theSolution){
        solution = theSolution;
    }

    public Chromosome(){
        Random random = new Random();
        for(int i = 0; i < SOLUTION_SIZE;i++){
            if( random.nextInt(2)==1){
                solution[i] = PileType.Addition;
            } else {
                solution[i] = PileType.Multilpication;
            }
        }
    }

    ///////////////////////
    //  Methods
    ///////////////////////

    private void setHelixPart(int startPositiion, int endPosition, PileType[] parent){
        for(int arrayPosition = startPositiion; arrayPosition<=endPosition; arrayPosition++){
            solution[arrayPosition] = parent[arrayPosition];
        }
    }

    /**
     * This gets the total multiplication number from multplication pile
     * @return integer value from all multplication pile cards multilpied togeather
     */
    public int getMultiplication(){
        int output = 1;
        for (int i = 0;i< SOLUTION_SIZE;i++) {
            if(solution[i] == PileType.Multilpication){
                output = output * (i+1);
            }
        }
        return output;
    }

    /**
     * This gets the total Addition number from Addition pile
     * @return integer value from all Addition pile cards added togeather
     */
    public int getAddition(){
        int output = 0;
        for (int i = 0;i< SOLUTION_SIZE;i++) {
            if(solution[i] == PileType.Addition){
                output += (i+1);
            }
        }
        return output;
    }

    /**
     * This gets the solution of the chromosome
     * @return solution
     */
    public PileType[] getSolution(){
        return solution;
    }

    /**
     * Random amount of array are swapped at random array positions
     */
    public void mutate(){
        Random random = new Random();

        int amountRandomise = random.nextInt(SOLUTION_SIZE-1);
        for(int i=0;i<=amountRandomise;i++){
            int randomInt = random.nextInt(SOLUTION_SIZE-1);

            if(solution[randomInt]==PileType.Addition){
                solution[randomInt]=PileType.Multilpication;
            }else {
                solution[randomInt]=PileType.Addition;
            }
        }

    }

    /**
     *  This sets the solution for the chromosome
     * @param theSolution
     */
    public void setSolution(PileType[] theSolution){
        solution = theSolution;
    }

    /**
     * This calculates the score from the chromosome.
     * @return the Score of chromosome
     */
    public int fitnessFunctionEqual(){
        int output;
        int multiplicationPile = (360 - getMultiplication())/10;
        if (multiplicationPile<0){
            multiplicationPile = multiplicationPile*-1;
        }
        int additionPile = 36 - getAddition();
        if(additionPile<0){
            additionPile = additionPile*-1;
        }

        output =multiplicationPile+additionPile;
        return output;
    }

    public int fitnessFunctionPure(){
        int output = 396 - (getMultiplication()) + getAddition();
        return output;
    }
    /**
     * This returns all information in the chromosome
     *
     * @return String of all the information in the Chromosome
     */
    public String toString(){
        StringBuilder  stringBuilder = new StringBuilder("-- Chromosome Date -- \n");
        for (PileType pileType : solution) {
            stringBuilder.append(pileType + ", ");
        }

        return stringBuilder.toString();
    }
}