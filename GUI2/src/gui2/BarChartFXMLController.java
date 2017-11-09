/*
 * Copyright (C) 2017 Szysz
 */
package gui2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;

/**
 * FXML Controller class
 *
 * @author Szysz
 */
public class BarChartFXMLController implements Initializable {

    private  ObservableList<XYChart.Data<String,Integer>> marks=FXCollections.observableArrayList();
    private XYChart.Series series1;
    @FXML BarChart barChart;
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        marks.addAll(
            new XYChart.Data<>(Double.toString(2.0),0),
            new XYChart.Data<>(Double.toString(3.0),0),
            new XYChart.Data<>(Double.toString(3.5),0),
            new XYChart.Data<>(Double.toString(4.0),0),
            new XYChart.Data<>(Double.toString(4.5),0),
            new XYChart.Data<>(Double.toString(5.0),0));
        
        
        series1=new XYChart.Series<>(marks);
        series1.setName("Marks");
        barChart.getData().add(series1);
    }

        public void addMark(double mark){
            
            for(XYChart.Data<String,Integer> d:marks){
		if (d.getXValue().equals(Double.toString(mark))){
			d.setYValue(d.getYValue()+1);
			break;
		}
            }
	}
	
	public void removeMark(double mark){
            
            for (XYChart.Data<String,Integer> d:marks){
		if (d.getXValue().equals(Double.toString(mark))){
			d.setYValue(d.getYValue()-1);
			break;
		}
            }
        }        
    
}
