package GUI;

import Evolution_Algorithm.EvoltuionAlgorithm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    ///////////////////////
    //  FXML
    ///////////////////////
    @FXML
    Slider sldPopulation;
    @FXML
    Label lblPopulation;
    @FXML
    Slider sldReselection;
    @FXML
    Label lblReselection;
    @FXML
    LineChart<Number,Number> lineChart;
    @FXML
    private CheckBox cbBest;
    @FXML
    private CheckBox cbAverage;
    ///////////////////////
    //  Constants
    ///////////////////////
    private static int INIT_RESELECTION = 30;
    private static int INIT_POPULATION = 30;

    ///////////////////////
    //  Objects
    ///////////////////////

    private EvoltuionAlgorithm evoltuionAlgorithm;

    ///////////////////////
    //  Methods
    ///////////////////////

    /**
     * this gets the data from the sliders and uses it for input for EvolutionaryAlgorithm parameters
     *
     * @param actionEvent
     */
    public void startAlgorithm(ActionEvent actionEvent){
        Double startingPopulation = Double.parseDouble(lblPopulation.getText());
        Double startingReselection = Double.parseDouble(lblReselection.getText());
        evoltuionAlgorithm = new EvoltuionAlgorithm(startingPopulation,startingReselection,cbAverage.isSelected(),cbBest.isSelected());

        ArrayList<XYChart.Series> result = evoltuionAlgorithm.getLinceChart();
        lineChart.getData().clear();
        for (XYChart.Series series: result){
            lineChart.getData().add(series);
        }
    }

    /**
     * This is used to set up Bi-direction for the Sliders with Init_Population
     * (This sets the slider so that it changes the text)
     * @param location
     * @param resources
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
