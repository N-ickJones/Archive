package Parser;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import static Parser.Main.primaryStage;

public class Controller implements Initializable{

    private File file;

    private int count1, count2, count3, count4, count5,
            count6, count7, count8, count9, count10;

    private boolean check1, check2, check3, check4 , check5,
            check6, check7, check8, check9, check10,
            built = false, built2 = false;
    @FXML
    private TextField fileInputBox;
    @FXML
    private Button browse, startScan, scatterChartButton, pieChartButton,
             barChartButton, lineChartButton;
    @FXML
    private TextField input1, input2, input3, input4, input5,
                        input6, input7, input8, input9, input10;
    @FXML
    private Label output1, output2, output3, output4, output5,
                    output6, output7, output8, output9, output10;
    @FXML
    private Label showInput1, showInput2, showInput3, showInput4, showInput5,
                    showInput6, showInput7, showInput8, showInput9, showInput10;
    @FXML
    private Label chart;
    @FXML
    private LineChart<String, Number> lineChart;
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private ScatterChart<String, Number> scatterChart;
    @FXML
    private PieChart pieChart;


    private XYChart.Series<String, Number> series;

    private ObservableList<PieChart.Data> pieChartData;

    private void parseFile() {

        String line;
        Scanner fileParser;

        try {
            fileParser = new Scanner(file);
        }
        catch(FileNotFoundException e){
            System.out.println("File not Found");
            return;
        }

        resetCount();
        resetBoolean();
        checkEmpty();

        while(fileParser.hasNext()){

            line = fileParser.nextLine();

            if (check1 && line.contains(showInput1.getText()))
                count1++;

            if (check2 &&line.contains(showInput2.getText()))
                count2++;

            if (check3 &&line.contains(showInput3.getText()))
                count3++;

            if (check4 &&line.contains(showInput4.getText()))
                count4++;

            if (check5 &&line.contains(showInput5.getText()))
                count5++;

            if (check6 &&line.contains(showInput6.getText()))
                count6++;

            if (check7 &&line.contains(showInput7.getText()))
                count7++;

            if (check8 &&line.contains(showInput8.getText()))
                count8++;

            if (check9 &&line.contains(showInput9.getText()))
                count9++;

            if (check10 &&line.contains(showInput10.getText()))
                count10++;
        }
        setOutput();
        buildChart();
    }

    private void resetCount(){
         count1 = 0;
         count2 = 0;
         count3 = 0;
         count4 = 0;
         count5 = 0;
         count6 = 0;
         count7 = 0;
         count8 = 0;
         count9 = 0;
         count10 = 0;
    }

    private void resetBoolean(){
        check1 = true;
        check2 = true;
        check3 = true;
        check4 = true;
        check5 = true;
        check6 = true;
        check7 = true;
        check8 = true;
        check9 = true;
        check10 = true;
    }

    private void setOutput(){
        output1.setText(String.valueOf(count1));
        output2.setText(String.valueOf(count2));
        output3.setText(String.valueOf(count3));
        output4.setText(String.valueOf(count4));
        output5.setText(String.valueOf(count5));
        output6.setText(String.valueOf(count6));
        output7.setText(String.valueOf(count7));
        output8.setText(String.valueOf(count8));
        output9.setText(String.valueOf(count9));
        output10.setText(String.valueOf(count10));
    }

    private void buildChart(){

        if(chart.getText().matches("Chart")) {
            lineChart.getData().clear();
            setNotVisible();
            lineChart.setVisible(true);
            chart.setText("Line Chart");
            buildXYData();
            lineChart.getData().add(series);
        }
        else if(chart.getText().matches("Bar Chart")) {
            barChart.getData().clear();
            setNotVisible();
            barChart.setVisible(true);
            buildXYData();
            barChart.getData().add(series);
        }
        else if(chart.getText().matches("Line Chart")) {
            lineChart.getData().clear();
            setNotVisible();
            lineChart.setVisible(true);
            buildXYData();
            lineChart.getData().add(series);
        }
        else if(chart.getText().matches("Scatter Chart")) {
            scatterChart.getData().clear();
            setNotVisible();
            scatterChart.setVisible(true);
            buildXYData();
            scatterChart.getData().add(series);
        }
        else if(chart.getText().matches("Pie Chart")) {
            pieChart.getData().clear();
            setNotVisible();
            pieChart.setVisible(true);
            buildPieChart();
            pieChart.setData(pieChartData);
        }
    }

    private void buildXYData(){

        built = true;
        series = new XYChart.Series<>();
        if(check1)series.getData().add( new XYChart.Data<>(showInput1.getText(), count1));
        if(check2)series.getData().add( new XYChart.Data<>(showInput2.getText(), count2));
        if(check3)series.getData().add( new XYChart.Data<>(showInput3.getText(), count3));
        if(check4)series.getData().add( new XYChart.Data<>(showInput4.getText(), count4));
        if(check5)series.getData().add( new XYChart.Data<>(showInput5.getText(), count5));
        if(check6)series.getData().add( new XYChart.Data<>(showInput6.getText(), count6));
        if(check7)series.getData().add( new XYChart.Data<>(showInput7.getText(), count7));
        if(check8)series.getData().add( new XYChart.Data<>(showInput8.getText(), count8));
        if(check9)series.getData().add( new XYChart.Data<>(showInput9.getText(), count9));
        if(check10)series.getData().add( new XYChart.Data<>(showInput10.getText(), count10));
    }

    private void buildPieChart() {

        built2 = true;
        if (!check2)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1));

        else if (!check3)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2));

        else if (!check4)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3));

        else if (!check5)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4));

        else if (!check6)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4),
                    new PieChart.Data(showInput5.getText(), count5));

        else if (!check7)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4),
                    new PieChart.Data(showInput5.getText(), count5),
                    new PieChart.Data(showInput6.getText(), count6));

        else if (!check8)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4),
                    new PieChart.Data(showInput5.getText(), count5),
                    new PieChart.Data(showInput6.getText(), count6),
                    new PieChart.Data(showInput7.getText(), count7));

        else if (!check9)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4),
                    new PieChart.Data(showInput5.getText(), count5),
                    new PieChart.Data(showInput6.getText(), count6),
                    new PieChart.Data(showInput7.getText(), count7),
                    new PieChart.Data(showInput8.getText(), count8));

        else if (!check10)
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4),
                    new PieChart.Data(showInput5.getText(), count5),
                    new PieChart.Data(showInput6.getText(), count6),
                    new PieChart.Data(showInput7.getText(), count7),
                    new PieChart.Data(showInput8.getText(), count8),
                    new PieChart.Data(showInput9.getText(), count9));

        else
            pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(showInput1.getText(), count1),
                    new PieChart.Data(showInput2.getText(), count2),
                    new PieChart.Data(showInput3.getText(), count3),
                    new PieChart.Data(showInput4.getText(), count4),
                    new PieChart.Data(showInput5.getText(), count5),
                    new PieChart.Data(showInput6.getText(), count6),
                    new PieChart.Data(showInput7.getText(), count7),
                    new PieChart.Data(showInput8.getText(), count8),
                    new PieChart.Data(showInput9.getText(), count9),
                    new PieChart.Data(showInput10.getText(), count10));
    }

    private void checkEmpty() {
        if(input1.getText().isEmpty()) {
            check1 = false;
        }
        else
            showInput1.setText(input1.getText());

        if(input2.getText().isEmpty()) {
            check2 = false;
        }
        else
            showInput2.setText(input2.getText());

        if(input3.getText().isEmpty()) {
            check3 = false;
        }
        else
            showInput3.setText(input3.getText());

        if(input4.getText().isEmpty()) {
            check4 = false;
        }
        else
            showInput4.setText(input4.getText());

        if(input5.getText().isEmpty()) {
            check5 = false;
        }
        else
            showInput5.setText(input5.getText());

        if(input6.getText().isEmpty()) {
            check6 = false;
        }
        else
            showInput6.setText(input6.getText());

        if(input7.getText().isEmpty()) {
            check7 = false;
        }
        else
            showInput7.setText(input7.getText());

        if(input8.getText().isEmpty()) {
            check8 = false;
        }
        else
            showInput8.setText(input8.getText());

        if(input9.getText().isEmpty()) {
            check9 = false;
        }
        else
            showInput9.setText(input9.getText());

        if(input10.getText().isEmpty()) {
            check10 = false;
        }
        else
            showInput10.setText(input10.getText());
    }

    private void openFile() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Select a File");
        fc.getExtensionFilters().addAll(
                //new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        this.file = fc.showOpenDialog(primaryStage);

        fileInputBox.setText(this.file.toString());

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        browse.setOnAction(e -> openFile());
        startScan.setOnAction(e -> parseFile());
        barChartButton.setOnAction(e -> setBarChart());
        lineChartButton.setOnAction(e -> setLineChart());
        scatterChartButton.setOnAction(e -> setScatterChart());
        pieChartButton.setOnAction(e -> setPieChart());
    }

    private void setBarChart() {
        chart.setText("Bar Chart");
        if(built)
        buildChart();
    }

    private void setLineChart() {
        chart.setText("Line Chart");
        if(built)
        buildChart();
    }

    private void setScatterChart() {
        chart.setText("Scatter Chart");
        if(built)
        buildChart();
    }

    private void setPieChart() {
        chart.setText("Pie Chart");
        if(built2)
        buildChart();
    }


    private void setNotVisible(){
        barChart.setVisible(false);
        lineChart.setVisible(false);
        scatterChart.setVisible(false);
        pieChart.setVisible(false);
    }

}