package GUI;

import Evolution_Algorithm.EvoltuionAlgorithm;
import Evolution_Algorithm.Population;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Slider sldPopulation;
    private static int INIT_POPULATION = 30;
    @FXML
    Label lblPopulation;

    @FXML
    Slider sldReselection;
    private static int INIT_RESELECTION = 30;
    @FXML
    Label lblReselection;

    @FXML
    LineChart<Number,Number> lineChart;

    EvoltuionAlgorithm evoltuionAlgorithm;

    /**
    * This Function runs the Genetic Algorithm when called upon, it collects
    * the data and then adds it to the graph
    *
    *@parameters actionEvent
    */
    public void startAlgorithm(ActionEvent actionEvent){
        Double startingPopulation = Double.parseDouble(lblPopulation.getText());
        Double startingReselection = Double.parseDouble(lblReselection.getText());
        evoltuionAlgorithm = new EvoltuionAlgorithm(startingPopulation,startingReselection);

        ArrayList<XYChart.Series> result = evoltuionAlgorithm.getLinceChart();
        lineChart.getData().clear();
        for (XYChart.Series series: result){
            lineChart.getData().add(series);
        }
    }

    /**
    *This function is used to create the sliders in the GUI.
    */
   @Override
    public void initialize(URL location, ResourceBundle resources) {
        sldPopulation.setValue(INIT_POPULATION);
        lblPopulation.setText(Integer.toString(INIT_POPULATION));
        lblPopulation.textProperty().bindBidirectional(sldPopulation.valueProperty(), NumberFormat.getNumberInstance());

        sldReselection.setValue(INIT_RESELECTION);
        lblReselection.setText(Integer.toString(INIT_RESELECTION));
        lblReselection.textProperty().bindBidirectional(sldReselection.valueProperty(), NumberFormat.getNumberInstance());
    }


}
