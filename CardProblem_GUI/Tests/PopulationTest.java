import Evolution_Algorithm.Population;
import org.junit.jupiter.api.Test;

public class PopulationTest {

    @Test
    public void constructor(){
        Population thePopulation = new Population(new Double(30),new Double(50));
        System.out.println(thePopulation.toString());
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        thePopulation.nextGeneration();
        System.out.println(thePopulation.toString());
    }

    @Test
    public void nextGeneration(){

    }
}
