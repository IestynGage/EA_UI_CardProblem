package Evolution_Algorithm;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.ArrayList;

public class EvoltuionAlgorithm {
    Population thePopulation;

    public EvoltuionAlgorithm(Double thePopulationSize, Double theReSelection){
        thePopulation = new Population(thePopulationSize,theReSelection);
    }

    /**
    *runs EvoltuionAlgorithm several times intill a solution with score of zero has been achieved. After each generation the
    *algorithm gets score of average and the best, and adds it to the result.
    *@return result
    */
    public ArrayList<XYChart.Series> getLinceChart(){
        XYChart.Series<String,Number> bestSeries = new XYChart.Series<>();
        XYChart.Series<String,Number> averageSeries = new XYChart.Series<>();

        bestSeries.getData().add(new XYChart.Data<String,Number>(Integer.toString(thePopulation.getGeneration()),thePopulation.getBestScore()));
        averageSeries.getData().add(new XYChart.Data<String,Number>(Integer.toString(thePopulation.getGeneration()),thePopulation.getAverageScore()));

        while(!thePopulation.hasConverged()){
            thePopulation.nextGeneration();
            bestSeries.getData().add(new XYChart.Data<String,Number>(Integer.toString(thePopulation.getGeneration()),thePopulation.getBestScore()));
            averageSeries.getData().add(new XYChart.Data<String,Number>(Integer.toString(thePopulation.getGeneration()),thePopulation.getAverageScore()));
        }

        bestSeries.setName("Best Case");
        averageSeries.setName("Average Case");
        ArrayList<XYChart.Series> result = new ArrayList<>();
        result.add(bestSeries);
        result.add(averageSeries);

        return result;
    }
}
