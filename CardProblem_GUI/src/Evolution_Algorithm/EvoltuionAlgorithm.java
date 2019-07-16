package Evolution_Algorithm;


import javafx.scene.chart.XYChart;
import java.util.ArrayList;

public class EvoltuionAlgorithm {

    ///////////////////////
    //  Objects
    ///////////////////////

    Population thePopulation;

    ///////////////////////
    //  Variables
    ///////////////////////
    private boolean getAverage;
    private boolean getBest;

    ///////////////////////
    //  Constructors
    ///////////////////////

    /**
     * Conctructor for the EvolutionAlgorithm, creates the population and then sets if the Evolutionary Algorithm will show average and best result
     *
     * @param thePopulationSize
     * @param theReSelection
     * @param getAverage
     * @param getBest
     */
    public EvoltuionAlgorithm(Double thePopulationSize, Double theReSelection,boolean getAverage, boolean getBest, String fitnessFunction){
        thePopulation = new Population(thePopulationSize,theReSelection,fitnessFunction);
        this.getAverage = getAverage;
        this.getBest = getBest;
    }

    ///////////////////////
    //  Methods
    ///////////////////////

    /**
     * This runs the population to gets the results, method then adds the result onto the Chart, This Chart will need to
     * be given to JavaFX graph for it to be displayed.
     *
     * @return ArrayList of Charts, being best results and average results
     */
    public ArrayList<XYChart.Series> getLinceChart(){
        XYChart.Series<String,Number> bestSeries = new XYChart.Series<>();
        XYChart.Series<String,Number> averageSeries = new XYChart.Series<>();

        do{
            thePopulation.nextGeneration();
            if(getBest){
                bestSeries.getData().add(new XYChart.Data<String,Number>(Integer.toString(thePopulation.getGeneration()),thePopulation.getBestScore()));
            }
            if(getAverage){
                averageSeries.getData().add(new XYChart.Data<String,Number>(Integer.toString(thePopulation.getGeneration()),thePopulation.getAverageScore()));
            }
        }while(!thePopulation.hasConverged());


        bestSeries.setName("Best Case");
        averageSeries.setName("Average Case");
        ArrayList<XYChart.Series> result = new ArrayList<>();
        if(getBest){
            result.add(bestSeries);
        }
        if(getAverage){
            result.add(averageSeries);
        }

        return result;
    }
}
