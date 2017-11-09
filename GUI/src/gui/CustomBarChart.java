/*
 * Copyright (C) 2017 Szysz
*/
package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.chart.XYChart.*;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Szysz
 */
public class CustomBarChart extends AnchorPane {
    
        private final ObservableList<Data<String,Integer>> marks;
	private final CategoryAxis xAxis;
	private final NumberAxis yAxis;
	private final BarChart barChart;
	private final Series series1;
	
	
	CustomBarChart(){
            marks=FXCollections.observableArrayList();
            marks.addAll(
                new Data<>(Double.toString(2.0),0),
                new Data<>(Double.toString(3.0),0),
                new Data<>(Double.toString(3.5),0),
                new Data<>(Double.toString(4.0),0),
                new Data<>(Double.toString(4.5),0),
                new Data<>(Double.toString(5.0),0));
                
            xAxis=new CategoryAxis();
            xAxis.setLabel("Mark");
            yAxis=new NumberAxis();
            yAxis.setLabel("Count");
            yAxis.setTickLabelFormatter(new CustomStringConverter());
            series1=new XYChart.Series<>(marks);
            series1.setName("Marks");
            barChart=new BarChart(xAxis, yAxis);
            barChart.setTitle("Distribution of marks");
            barChart.getData().add(series1);
            barChart.setLegendSide(Side.RIGHT);
            
            AnchorPane.setRightAnchor(barChart, 0.0);
            AnchorPane.setLeftAnchor(barChart, 0.0);
            AnchorPane.setTopAnchor(barChart, 0.0);
            AnchorPane.setBottomAnchor(barChart, 0.0);
				
            this.getChildren().add(barChart);
	}
	
	public void addMark(double mark){
            
            for(Data<String,Integer> d:marks){
		if (d.getXValue().equals(Double.toString(mark))){
			d.setYValue(d.getYValue()+1);
			break;
		}
            }
	}
	
	public void removeMark(double mark){
            
            for (Data<String,Integer> d:marks){
		if (d.getXValue().equals(Double.toString(mark))){
			d.setYValue(d.getYValue()-1);
			break;
		}
            }
        }       
    
}
