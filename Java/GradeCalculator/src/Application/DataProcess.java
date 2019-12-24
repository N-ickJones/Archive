package Application;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Application.Main.primaryStage;

public class DataProcess {

    private Controller1Main controller1Main;
    public DataProcess(Controller1Main controller1Main) {
        this.controller1Main = controller1Main;
    }

    private File dataFile = null;

    private ComboBox<String> academicYear, semester, classSelectionBox;
    private TextField gpaHours, qualityPoints, currentGPA, classNameBox, classCreditHours,
            criteria1, criteria2, criteria3, criteria4, criteria5,
            criteria6, criteria7, criteria8, criteria9, criteria10,
            weight1, weight2, weight3, weight4, weight5, weight6, weight7, weight8, weight9, weight10,
            totalPoints1, totalPoints2, totalPoints3, totalPoints4, totalPoints5,
            totalPoints6, totalPoints7, totalPoints8, totalPoints9, totalPoints10,
            criteriaItem11, criteriaItem12, criteriaItem13, criteriaItem14, criteriaItem15,
            criteriaItem16, criteriaItem17, criteriaItem18, criteriaItem19, criteriaItem110,
            criteriaItem21, criteriaItem22, criteriaItem23, criteriaItem24, criteriaItem25,
            criteriaItem26, criteriaItem27, criteriaItem28, criteriaItem29, criteriaItem210,
            criteriaItem31, criteriaItem32, criteriaItem33, criteriaItem34, criteriaItem35,
            criteriaItem36, criteriaItem37, criteriaItem38, criteriaItem39, criteriaItem310,
            criteriaItem41, criteriaItem42, criteriaItem43, criteriaItem44, criteriaItem45,
            criteriaItem46, criteriaItem47, criteriaItem48, criteriaItem49, criteriaItem410,
            criteriaItem51, criteriaItem52, criteriaItem53, criteriaItem54, criteriaItem55,
            criteriaItem56, criteriaItem57, criteriaItem58, criteriaItem59, criteriaItem510,
            criteriaItem61, criteriaItem62, criteriaItem63, criteriaItem64, criteriaItem65,
            criteriaItem66, criteriaItem67, criteriaItem68, criteriaItem69, criteriaItem610,
            criteriaItem71, criteriaItem72, criteriaItem73, criteriaItem74, criteriaItem75,
            criteriaItem76, criteriaItem77, criteriaItem78, criteriaItem79, criteriaItem710,
            criteriaItem81, criteriaItem82, criteriaItem83, criteriaItem84, criteriaItem85,
            criteriaItem86, criteriaItem87, criteriaItem88, criteriaItem89, criteriaItem810,
            criteriaItem91, criteriaItem92, criteriaItem93, criteriaItem94, criteriaItem95,
            criteriaItem96, criteriaItem97, criteriaItem98, criteriaItem99, criteriaItem910,
            criteriaItem101, criteriaItem102, criteriaItem103, criteriaItem104, criteriaItem105,
            criteriaItem106, criteriaItem107, criteriaItem108, criteriaItem109, criteriaItem1010;

    private Label academicStatus, academicGrade, classNameLabel, projectedGPA,  creditHoursLabel,
            pointsEarned1, pointsEarned2, pointsEarned3, pointsEarned4, pointsEarned5,
            pointsEarned6, pointsEarned7, pointsEarned8, pointsEarned9, pointsEarned10;
    private ProgressIndicator  gpaHoursProgress, qualityPointsProgress, currentGradeProgress;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final int classItems = 145, semesterItems = 10, numberSemesters = 5, numberYears = 3;
    private int numberClasses = 10;
    private int items = classItems + semesterItems;

    private String[][][][] dataArray;
    private String[][] classBoxItems = new String[10][5];
    private String[][] semesterBoxItems = new String[6][3];
    private List<String> academicYearList = new ArrayList<>();
    private List<String> semesterList = new ArrayList<>();
    private List<String> classBoxList = new ArrayList<>();
    private int academicYearIndex = 0, semesterIndex = 0, classBoxIndex = 0;
    private String classNameHolder, creditHourHolder;
    private boolean checkSave = true, firstSemester = true, semesterStatus;

    public void saveAcademicYearStartUp() {

        dataArray = new String[items][numberClasses][numberSemesters][numberYears];
        for(int i = 0; i < 3; i++)
            academicYearList.add("");
        for(int i = 0; i < 5; i++)
            semesterList.add("");

        String current = academicYear.getSelectionModel().getSelectedItem();
        int index = 0;

        switch(current){
            case "2018" : index = 0;
                break;
            case "2019" : index = 1;
                break;
            case "2020" : index = 2;
                break;
        }
        academicYearList.add(index, current);
        academicYearIndex = index;
    }
    public void saveAcademicYear() {

        String currentSemester = semester.getSelectionModel().getSelectedItem();
        if(currentSemester == null || currentSemester.isEmpty()) {
            semesterStatus = false;
            return;
        }
        else
            semesterStatus = true;
        //User can change this after StartUP so all indexes assigned
        saveSemester();

        String current = academicYear.getSelectionModel().getSelectedItem();
        int index = 0;

        switch(current){
            case "2018" : index = 0;
                break;
            case "2019" : index = 1;
                break;
            case "2020" : index = 2;
                break;
        }

        boolean check = true;

        for (String s : academicYearList) {
            if (s.contains(current))
                check = false;
        }

        if(check) {
            saveCurrentAcademicYear();
            academicYearList.add(index, current);
            academicYearIndex = index;
            saveNewAcademicYear();
        }
        else{
            saveCurrentAcademicYear();
            academicYearIndex = index;
        }
    }
    public void getAcademicYearBox(){

        if(semesterBoxItems[5][academicYearIndex] != null) {
            for (int i = 0; i < Integer.parseInt(semesterBoxItems[5][academicYearIndex]); i++) {
                semesterList.add(semesterBoxItems[i][academicYearIndex]);
                //semester.getItems().add(semesterBoxItems[i][academicYearIndex]);
            }

        }
        getSemesterBox();}
    private void saveNewAcademicYear(){

        saveNewSemester();
    }
    private void saveCurrentAcademicYear(){

        saveCurrentSemester();
        int count = 0;
        for(int i = 0; i < semesterList.size(); i++){
            semesterBoxItems[i][academicYearIndex] = semesterList.get(i);
            count++;
        }
        semesterBoxItems[5][academicYearIndex] = String.valueOf(count);
        semesterList.clear();
    }
    public void saveSemesterStartUp() {

        String current = semester.getSelectionModel().getSelectedItem();
        int index = 0;

        switch(current){
            case "Spring" : index = 0;
                break;
            case "Fall" : index = 1;
                break;
            case "SummerI" : index = 2;
                break;
            case "SummerII" : index = 3;
                break;
            case "Winter" : index = 4;
        }

        semesterList.add(index, current);
        semesterIndex = index;
    }
    public void saveSemester() {


        if(firstSemester) {
            dataArray[145][classBoxIndex][semesterIndex][academicYearIndex] = gpaHours.getText();
            dataArray[146][classBoxIndex][semesterIndex][academicYearIndex] = qualityPoints.getText();
            dataArray[147][classBoxIndex][semesterIndex][academicYearIndex] = currentGPA.getText();
            dataArray[148][classBoxIndex][semesterIndex][academicYearIndex] = academicStatus.getText();
            dataArray[149][classBoxIndex][semesterIndex][academicYearIndex] = academicGrade.getText();
            dataArray[150][classBoxIndex][semesterIndex][academicYearIndex] = classSelectionBox.getSelectionModel().getSelectedItem();



            //TODO
            dataArray[151][classBoxIndex][semesterIndex][academicYearIndex] = String.valueOf(gpaHoursProgress.getProgress());
            dataArray[152][classBoxIndex][semesterIndex][academicYearIndex] = String.valueOf(qualityPointsProgress.getProgress());




        }
        firstSemester = false;

        String current = semester.getSelectionModel().getSelectedItem();

        int index = 0;

        switch(current){
            case "Spring" : index = 0;
                break;
            case "Fall" : index = 1;
                break;
            case "SummerI" : index = 2;
                break;
            case "SummerII" : index = 3;
                break;
            case "Winter" : index = 4;
        }

        boolean check = true;
        for (String s: semesterList){
            if(s.contains(current))
                check = false;
        }
        if(check) {
            saveCurrentSemester();
            semesterList.add(index, current);
            semesterIndex = index;
            saveNewSemester();
        }
        else {
            saveCurrentSemester();
            semesterIndex = index;
        }
    }
    public void getSemesterBox() {


        if(classBoxItems[9][semesterIndex] != null) {
            for (int i = 0; i < Integer.parseInt(classBoxItems[9][semesterIndex]); i++) {
                classBoxList.add(classBoxItems[i][semesterIndex]);
                classSelectionBox.getItems().add(classBoxItems[i][semesterIndex]);
            }
        }
        getClassBox();
        gpaHours.setText(dataArray[145][classBoxIndex][semesterIndex][academicYearIndex]);
        qualityPoints.setText(dataArray[146][classBoxIndex][semesterIndex][academicYearIndex]);
        currentGPA.setText(dataArray[147][classBoxIndex][semesterIndex][academicYearIndex]);
        academicStatus.setText(dataArray[148][classBoxIndex][semesterIndex][academicYearIndex]);
        academicGrade.setText(dataArray[149][classBoxIndex][semesterIndex][academicYearIndex]);
        classSelectionBox.getSelectionModel().select(dataArray[150][classBoxIndex][semesterIndex][academicYearIndex]);


        //TODO Put an if to check if empty String
        if(dataArray[151][classBoxIndex][semesterIndex][academicYearIndex] != null)
            if(!(dataArray[151][classBoxIndex][semesterIndex][academicYearIndex].isEmpty()))
                gpaHoursProgress.setProgress(Double.parseDouble(dataArray[151][classBoxIndex][semesterIndex][academicYearIndex]));

        if(dataArray[152][classBoxIndex][semesterIndex][academicYearIndex] != null)
            if(!(dataArray[152][classBoxIndex][semesterIndex][academicYearIndex].isEmpty()))
                qualityPointsProgress.setProgress(Double.parseDouble(dataArray[152][classBoxIndex][semesterIndex][academicYearIndex]));

        checkForNull();



    }
    private void saveNewSemester(){


        saveNewClassBox();
        dataArray[145][classBoxIndex][semesterIndex][academicYearIndex] = "";//gpaHours
        dataArray[146][classBoxIndex][semesterIndex][academicYearIndex] = "";//quality Points
        dataArray[147][classBoxIndex][semesterIndex][academicYearIndex] = "";//currentGpa
        dataArray[148][classBoxIndex][semesterIndex][academicYearIndex] = "";//academicStatus
        dataArray[149][classBoxIndex][semesterIndex][academicYearIndex] = "";//academicGrade
        dataArray[151][classBoxIndex][semesterIndex][academicYearIndex] = "";//classSelectionBox



        //TODO
        dataArray[152][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";//gpaHoursProgress
        dataArray[153][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";//qualityPointsProgress




    }
    private void saveCurrentSemester(){


        saveCurrentClassBox();
        int count = 0;
        for(int i = 0; i < classBoxList.size(); i++){
            classBoxItems[i][semesterIndex] = classBoxList.get(i);
            count++;
        }
        classBoxItems[9][semesterIndex] = String.valueOf(count);
        classBoxList.clear();

        dataArray[145][classBoxIndex][semesterIndex][academicYearIndex] = gpaHours.getText();
        dataArray[146][classBoxIndex][semesterIndex][academicYearIndex] = qualityPoints.getText();
        dataArray[147][classBoxIndex][semesterIndex][academicYearIndex] = currentGPA.getText();
        dataArray[148][classBoxIndex][semesterIndex][academicYearIndex] = academicStatus.getText();
        dataArray[149][classBoxIndex][semesterIndex][academicYearIndex] = academicGrade.getText();
        dataArray[150][classBoxIndex][semesterIndex][academicYearIndex] = classSelectionBox.getSelectionModel().getSelectedItem();



        //TODO
        dataArray[151][classBoxIndex][semesterIndex][academicYearIndex] = String.valueOf(gpaHoursProgress.getProgress());
        dataArray[152][classBoxIndex][semesterIndex][academicYearIndex] = String.valueOf(qualityPointsProgress.getProgress());




    }
    public void saveClassBoxStartUp() {


        String current = classSelectionBox.getSelectionModel().getSelectedItem();
        classBoxList.add(current);
        classBoxIndex = classBoxList.indexOf(current);



        //TODO
        classNameBox.clear();
        classCreditHours.clear();
        classNameHolder = classNameLabel.getText();
        creditHourHolder = creditHoursLabel.getText();




    }
    public void saveClassBox() {



        String current = classSelectionBox.getSelectionModel().getSelectedItem();
        boolean check = true;

        for (String s: classBoxList){
            if(s.contains(current))
                check = false;
        }

        if(check) {
            saveCurrentClassBox();
            classBoxList.add(current);
            classBoxIndex = classBoxList.indexOf(current);
            saveNewClassBox();
        }
        else{
            checkSave = false;
            saveCurrentClassBox();
            classBoxIndex = classBoxList.indexOf(current);
        }




        //TODO
        classNameBox.clear();
        classCreditHours.clear();
        classNameHolder = classNameLabel.getText();
        creditHourHolder = creditHoursLabel.getText();



    }
    public void getClassBox() {


        criteria1.setText(dataArray[0][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria2.setText(dataArray[1][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria3.setText(dataArray[2][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria4.setText(dataArray[3][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria5.setText(dataArray[4][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria6.setText(dataArray[5][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria7.setText(dataArray[6][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria8.setText(dataArray[7][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria9.setText(dataArray[8][classBoxIndex][semesterIndex][academicYearIndex]);
        criteria10.setText(dataArray[9][classBoxIndex][semesterIndex][academicYearIndex]);
        weight1.setText(dataArray[10][classBoxIndex][semesterIndex][academicYearIndex]);
        weight2.setText(dataArray[11][classBoxIndex][semesterIndex][academicYearIndex]);
        weight3.setText(dataArray[12][classBoxIndex][semesterIndex][academicYearIndex]);
        weight4.setText(dataArray[13][classBoxIndex][semesterIndex][academicYearIndex]);
        weight5.setText(dataArray[14][classBoxIndex][semesterIndex][academicYearIndex]);
        weight6.setText(dataArray[15][classBoxIndex][semesterIndex][academicYearIndex]);
        weight7.setText(dataArray[16][classBoxIndex][semesterIndex][academicYearIndex]);
        weight8.setText(dataArray[17][classBoxIndex][semesterIndex][academicYearIndex]);
        weight9.setText(dataArray[18][classBoxIndex][semesterIndex][academicYearIndex]);
        weight10.setText(dataArray[19][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints1.setText(dataArray[20][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints1.setText(dataArray[21][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints2.setText(dataArray[22][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints3.setText(dataArray[23][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints4.setText(dataArray[24][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints5.setText(dataArray[25][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints6.setText(dataArray[26][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints7.setText(dataArray[27][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints8.setText(dataArray[28][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints9.setText(dataArray[29][classBoxIndex][semesterIndex][academicYearIndex]);
        totalPoints10.setText(dataArray[30][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned1.setText(dataArray[31][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned2.setText(dataArray[32][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned3.setText(dataArray[33][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned4.setText(dataArray[34][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned5.setText(dataArray[35][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned6.setText(dataArray[36][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned7.setText(dataArray[37][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned8.setText(dataArray[38][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned9.setText(dataArray[39][classBoxIndex][semesterIndex][academicYearIndex]);
        pointsEarned10.setText(dataArray[40][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem11.setText(dataArray[41][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem12.setText(dataArray[42][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem13.setText(dataArray[43][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem14.setText(dataArray[44][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem15.setText(dataArray[45][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem16.setText(dataArray[46][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem17.setText(dataArray[47][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem18.setText(dataArray[48][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem19.setText(dataArray[49][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem110.setText(dataArray[50][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem21.setText(dataArray[51][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem22.setText(dataArray[52][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem23.setText(dataArray[53][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem24.setText(dataArray[54][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem25.setText(dataArray[55][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem26.setText(dataArray[56][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem27.setText(dataArray[57][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem28.setText(dataArray[58][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem29.setText(dataArray[59][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem210.setText(dataArray[60][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem31.setText(dataArray[61][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem32.setText(dataArray[62][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem33.setText(dataArray[63][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem34.setText(dataArray[64][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem35.setText(dataArray[65][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem36.setText(dataArray[66][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem37.setText(dataArray[67][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem38.setText(dataArray[68][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem39.setText(dataArray[69][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem310.setText(dataArray[70][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem41.setText(dataArray[71][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem42.setText(dataArray[72][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem43.setText(dataArray[73][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem44.setText(dataArray[74][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem45.setText(dataArray[75][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem46.setText(dataArray[76][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem47.setText(dataArray[77][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem48.setText(dataArray[78][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem49.setText(dataArray[79][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem410.setText(dataArray[80][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem51.setText(dataArray[81][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem52.setText(dataArray[82][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem53.setText(dataArray[83][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem54.setText(dataArray[84][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem55.setText(dataArray[85][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem56.setText(dataArray[86][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem57.setText(dataArray[87][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem58.setText(dataArray[88][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem59.setText(dataArray[89][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem510.setText(dataArray[90][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem61.setText(dataArray[91][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem62.setText(dataArray[92][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem63.setText(dataArray[93][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem64.setText(dataArray[94][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem65.setText(dataArray[95][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem66.setText(dataArray[96][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem67.setText(dataArray[97][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem68.setText(dataArray[98][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem69.setText(dataArray[99][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem610.setText(dataArray[100][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem71.setText(dataArray[101][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem72.setText(dataArray[102][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem73.setText(dataArray[103][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem74.setText(dataArray[104][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem75.setText(dataArray[105][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem76.setText(dataArray[106][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem77.setText(dataArray[107][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem78.setText(dataArray[108][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem79.setText(dataArray[109][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem710.setText(dataArray[110][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem81.setText(dataArray[111][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem82.setText(dataArray[112][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem83.setText(dataArray[113][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem84.setText(dataArray[114][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem85.setText(dataArray[115][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem86.setText(dataArray[116][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem87.setText(dataArray[117][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem88.setText(dataArray[118][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem89.setText(dataArray[119][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem810.setText(dataArray[120][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem91.setText(dataArray[121][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem92.setText(dataArray[122][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem93.setText(dataArray[123][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem94.setText(dataArray[124][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem95.setText(dataArray[125][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem96.setText(dataArray[126][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem97.setText(dataArray[127][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem98.setText(dataArray[128][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem99.setText(dataArray[129][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem910.setText(dataArray[130][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem101.setText(dataArray[131][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem102.setText(dataArray[132][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem103.setText(dataArray[133][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem104.setText(dataArray[134][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem105.setText(dataArray[135][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem106.setText(dataArray[136][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem107.setText(dataArray[137][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem108.setText(dataArray[138][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem109.setText(dataArray[139][classBoxIndex][semesterIndex][academicYearIndex]);
        criteriaItem1010.setText(dataArray[140][classBoxIndex][semesterIndex][academicYearIndex]);

        //TODO
        classNameLabel.setText(dataArray[141][classBoxIndex][semesterIndex][academicYearIndex]);
        creditHoursLabel.setText(dataArray[142][classBoxIndex][semesterIndex][academicYearIndex]);
        if(dataArray[143][classBoxIndex][semesterIndex][academicYearIndex] != null)
            currentGradeProgress.setProgress(Double.parseDouble(dataArray[143][classBoxIndex][semesterIndex][academicYearIndex]));
        projectedGPA.setText(dataArray[144][classBoxIndex][semesterIndex][academicYearIndex]);
        checkForNull();

    }
    private void saveNewClassBox() {


        for(int i = 0; i < classItems; i++)
            dataArray[i][classBoxIndex][semesterIndex][academicYearIndex] = "";

        //Points Earned
        dataArray[31][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[32][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[33][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[34][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[35][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[36][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[37][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[38][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[39][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";
        dataArray[40][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";




        //TODO
        dataArray[141][classBoxIndex][semesterIndex][academicYearIndex] = classNameLabel.getText();
        dataArray[142][classBoxIndex][semesterIndex][academicYearIndex] = creditHoursLabel.getText();

        dataArray[143][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";//current Grade Progress[Disabled]
        dataArray[144][classBoxIndex][semesterIndex][academicYearIndex] = "0.0";//Projected GPa





    }
    private void saveCurrentClassBox() {



        //TODO
        if(checkSave) {
            dataArray[141][classBoxIndex][semesterIndex][academicYearIndex] = classNameHolder;
            dataArray[142][classBoxIndex][semesterIndex][academicYearIndex] = creditHourHolder;
        }

        dataArray[0][classBoxIndex][semesterIndex][academicYearIndex] = criteria1.getText();
        dataArray[1][classBoxIndex][semesterIndex][academicYearIndex] = criteria2.getText();
        dataArray[2][classBoxIndex][semesterIndex][academicYearIndex] = criteria3.getText();
        dataArray[3][classBoxIndex][semesterIndex][academicYearIndex] = criteria4.getText();
        dataArray[4][classBoxIndex][semesterIndex][academicYearIndex] = criteria5.getText();
        dataArray[5][classBoxIndex][semesterIndex][academicYearIndex] = criteria6.getText();
        dataArray[6][classBoxIndex][semesterIndex][academicYearIndex] = criteria7.getText();
        dataArray[7][classBoxIndex][semesterIndex][academicYearIndex] = criteria8.getText();
        dataArray[8][classBoxIndex][semesterIndex][academicYearIndex] = criteria9.getText();
        dataArray[9][classBoxIndex][semesterIndex][academicYearIndex] = criteria10.getText();
        dataArray[10][classBoxIndex][semesterIndex][academicYearIndex] = weight1.getText();
        dataArray[11][classBoxIndex][semesterIndex][academicYearIndex] = weight2.getText();
        dataArray[12][classBoxIndex][semesterIndex][academicYearIndex] = weight3.getText();
        dataArray[13][classBoxIndex][semesterIndex][academicYearIndex] = weight4.getText();
        dataArray[14][classBoxIndex][semesterIndex][academicYearIndex] = weight5.getText();
        dataArray[15][classBoxIndex][semesterIndex][academicYearIndex] = weight6.getText();
        dataArray[16][classBoxIndex][semesterIndex][academicYearIndex] = weight7.getText();
        dataArray[17][classBoxIndex][semesterIndex][academicYearIndex] = weight8.getText();
        dataArray[18][classBoxIndex][semesterIndex][academicYearIndex] = weight9.getText();
        dataArray[19][classBoxIndex][semesterIndex][academicYearIndex] = weight10.getText();
        dataArray[20][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints1.getText();
        dataArray[21][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints1.getText();
        dataArray[22][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints2.getText();
        dataArray[23][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints3.getText();
        dataArray[24][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints4.getText();
        dataArray[25][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints5.getText();
        dataArray[26][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints6.getText();
        dataArray[27][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints7.getText();
        dataArray[28][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints8.getText();
        dataArray[29][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints9.getText();
        dataArray[30][classBoxIndex][semesterIndex][academicYearIndex] = totalPoints10.getText();

        //Points earned
        dataArray[31][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned1.getText();
        dataArray[32][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned2.getText();
        dataArray[33][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned3.getText();
        dataArray[34][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned4.getText();
        dataArray[35][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned5.getText();
        dataArray[36][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned6.getText();
        dataArray[37][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned7.getText();
        dataArray[38][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned8.getText();
        dataArray[39][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned9.getText();
        dataArray[40][classBoxIndex][semesterIndex][academicYearIndex] = pointsEarned10.getText();

        dataArray[41][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem11.getText();
        dataArray[42][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem12.getText();
        dataArray[43][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem13.getText();
        dataArray[44][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem14.getText();
        dataArray[45][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem15.getText();
        dataArray[46][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem16.getText();
        dataArray[47][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem17.getText();
        dataArray[48][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem18.getText();
        dataArray[49][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem19.getText();
        dataArray[50][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem110.getText();
        dataArray[51][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem21.getText();
        dataArray[52][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem22.getText();
        dataArray[53][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem23.getText();
        dataArray[54][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem24.getText();
        dataArray[55][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem25.getText();
        dataArray[56][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem26.getText();
        dataArray[57][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem27.getText();
        dataArray[58][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem28.getText();
        dataArray[59][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem29.getText();
        dataArray[60][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem210.getText();
        dataArray[61][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem31.getText();
        dataArray[62][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem32.getText();
        dataArray[63][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem33.getText();
        dataArray[64][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem34.getText();
        dataArray[65][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem35.getText();
        dataArray[66][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem36.getText();
        dataArray[67][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem37.getText();
        dataArray[68][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem38.getText();
        dataArray[69][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem39.getText();
        dataArray[70][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem310.getText();
        dataArray[71][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem41.getText();
        dataArray[72][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem42.getText();
        dataArray[73][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem43.getText();
        dataArray[74][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem44.getText();
        dataArray[75][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem45.getText();
        dataArray[76][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem46.getText();
        dataArray[77][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem47.getText();
        dataArray[78][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem48.getText();
        dataArray[79][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem49.getText();
        dataArray[80][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem410.getText();
        dataArray[81][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem51.getText();
        dataArray[82][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem52.getText();
        dataArray[83][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem53.getText();
        dataArray[84][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem54.getText();
        dataArray[85][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem55.getText();
        dataArray[86][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem56.getText();
        dataArray[87][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem57.getText();
        dataArray[88][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem58.getText();
        dataArray[89][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem59.getText();
        dataArray[90][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem510.getText();
        dataArray[91][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem61.getText();
        dataArray[92][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem62.getText();
        dataArray[93][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem63.getText();
        dataArray[94][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem64.getText();
        dataArray[95][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem65.getText();
        dataArray[96][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem66.getText();
        dataArray[97][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem67.getText();
        dataArray[98][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem68.getText();
        dataArray[99][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem69.getText();
        dataArray[100][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem610.getText();
        dataArray[101][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem71.getText();
        dataArray[102][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem72.getText();
        dataArray[103][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem73.getText();
        dataArray[104][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem74.getText();
        dataArray[105][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem75.getText();
        dataArray[106][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem76.getText();
        dataArray[107][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem77.getText();
        dataArray[108][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem78.getText();
        dataArray[109][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem79.getText();
        dataArray[110][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem710.getText();
        dataArray[111][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem81.getText();
        dataArray[112][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem82.getText();
        dataArray[113][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem83.getText();
        dataArray[114][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem84.getText();
        dataArray[115][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem85.getText();
        dataArray[116][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem86.getText();
        dataArray[117][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem87.getText();
        dataArray[118][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem88.getText();
        dataArray[119][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem89.getText();
        dataArray[120][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem810.getText();
        dataArray[121][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem91.getText();
        dataArray[122][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem92.getText();
        dataArray[123][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem93.getText();
        dataArray[124][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem94.getText();
        dataArray[125][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem95.getText();
        dataArray[126][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem96.getText();
        dataArray[127][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem97.getText();
        dataArray[128][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem98.getText();
        dataArray[129][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem99.getText();
        dataArray[130][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem910.getText();
        dataArray[131][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem101.getText();
        dataArray[132][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem102.getText();
        dataArray[133][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem103.getText();
        dataArray[134][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem104.getText();
        dataArray[135][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem105.getText();
        dataArray[136][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem106.getText();
        dataArray[137][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem107.getText();
        dataArray[138][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem108.getText();
        dataArray[139][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem109.getText();
        dataArray[140][classBoxIndex][semesterIndex][academicYearIndex] = criteriaItem1010.getText();



        //TODO
        dataArray[143][classBoxIndex][semesterIndex][academicYearIndex] = String.valueOf(currentGradeProgress.getProgress());
        dataArray[144][classBoxIndex][semesterIndex][academicYearIndex] = String.valueOf(projectedGPA.getText());




    }
    private void checkForNull() {

        for(int i = 0; i < numberYears ; i++)
            for(int j = 0; j < numberSemesters ; j++)
                for(int k = 0; k < numberClasses ; k++)
                    for(int n = 0; n < items ; n++)
                       if(dataArray[n][k][j][i] == null)
                           dataArray[n][k][j][i] = "0";
    }
    public boolean checkSemester() {
        return semesterStatus;
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




    //Start Open Save Functions
    public void handleOpenClick() {

        FileChooser fc = new FileChooser();
        fc.setTitle("Select a File");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File file = fc.showOpenDialog(primaryStage);

        if (file != null) {

            try (Scanner scan = new Scanner(file)) {

                dataArray = new String[items][numberClasses][numberSemesters][numberYears];

                for(int i = 0; i < numberYears ; i++)
                    for(int j = 0; j < numberSemesters ; j++)
                        for(int k = 0; k < numberClasses ; k++)
                            for(int n = 0; n < items ; n++)
                                dataArray[n][k][j][i] = scan.useDelimiter("\\Z").nextLine();



                for(int i = 0; i < 3 ; i++)
                    for(int j = 0; j < 6 ; j++)
                        semesterBoxItems[j][i] = scan.useDelimiter("\\Z").nextLine();

                for(int i = 0; i < 5 ; i++)
                    for(int j = 0; j < 10 ; j++)
                        classBoxItems[j][i] = scan.useDelimiter("\\Z").nextLine();

                int size = Integer.parseInt(scan.useDelimiter("\\Z").nextLine());
                System.out.println(size);


                for (int i = 0; i < size; i++)
                    academicYearList.add(i, scan.useDelimiter("\\Z").nextLine());

                size = Integer.parseInt(scan.useDelimiter("\\Z").nextLine());
                System.out.println(size);

                for (int i = 0; i < size; i++)
                    semesterList.add(i, scan.useDelimiter("\\Z").nextLine());

                size = Integer.parseInt(scan.useDelimiter("\\Z").nextLine());
                System.out.println(size);

                for (int i = 0; i < size; i++)
                    classBoxList.add(i, scan.useDelimiter("\\Z").nextLine());

                            //load and save Arraylist


/*

                String text;

                //Test for null Academic Year Selection Box
                text = scan.useDelimiter("\\Z").nextLine();
                if (!(text.equals("null")))
                    academicYear.getSelectionModel().select(text);

                //Test for null Semester Selection Box
                text = scan.useDelimiter("\\Z").nextLine();
                if (!(text.equals("null")))
                    semester.getSelectionModel().select(text);

                qualityPoints.setText(scan.useDelimiter("\\Z").nextLine());
                currentGPA.setText(scan.useDelimiter("\\Z").nextLine());
                gpaHours.setText(scan.useDelimiter("\\Z").nextLine());
                academicStatus.setText(scan.useDelimiter("\\Z").nextLine());
                academicGrade.setText(scan.useDelimiter("\\Z").nextLine());
                classNameBox.setText(scan.useDelimiter("\\Z").nextLine());
                classCreditHours.setText(scan.useDelimiter("\\Z").nextLine());

                //Test for null Class Selection Box
                text = scan.useDelimiter("\\Z").nextLine();
                if (!(text.equals("null")))
                    classSelectionBox.getSelectionModel().select(text);

                gpaHoursProgress.setProgress(Double.parseDouble(scan.useDelimiter("\\Z").nextLine()));
                qualityPointsProgress.setProgress(Double.parseDouble(scan.useDelimiter("\\Z").nextLine()));


                criteria1.setText(scan.useDelimiter("\\Z").nextLine());
                criteria2.setText(scan.useDelimiter("\\Z").nextLine());
                criteria3.setText(scan.useDelimiter("\\Z").nextLine());
                criteria4.setText(scan.useDelimiter("\\Z").nextLine());
                criteria5.setText(scan.useDelimiter("\\Z").nextLine());
                criteria6.setText(scan.useDelimiter("\\Z").nextLine());
                criteria7.setText(scan.useDelimiter("\\Z").nextLine());
                criteria8.setText(scan.useDelimiter("\\Z").nextLine());
                criteria9.setText(scan.useDelimiter("\\Z").nextLine());
                criteria10.setText(scan.useDelimiter("\\Z").nextLine());

                weight1.setText(scan.useDelimiter("\\Z").nextLine());
                weight2.setText(scan.useDelimiter("\\Z").nextLine());
                weight3.setText(scan.useDelimiter("\\Z").nextLine());
                weight4.setText(scan.useDelimiter("\\Z").nextLine());
                weight5.setText(scan.useDelimiter("\\Z").nextLine());
                weight6.setText(scan.useDelimiter("\\Z").nextLine());
                weight7.setText(scan.useDelimiter("\\Z").nextLine());
                weight8.setText(scan.useDelimiter("\\Z").nextLine());
                weight9.setText(scan.useDelimiter("\\Z").nextLine());
                weight10.setText(scan.useDelimiter("\\Z").nextLine());

                totalPoints1.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints1.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints2.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints3.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints4.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints5.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints6.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints7.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints8.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints9.setText(scan.useDelimiter("\\Z").nextLine());
                totalPoints10.setText(scan.useDelimiter("\\Z").nextLine());

                pointsEarned1.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned2.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned3.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned4.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned5.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned6.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned7.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned8.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned9.setText(scan.useDelimiter("\\Z").nextLine());
                pointsEarned10.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem11.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem12.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem13.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem14.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem15.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem16.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem17.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem18.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem19.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem110.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem21.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem22.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem23.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem24.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem25.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem26.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem27.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem28.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem29.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem210.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem31.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem32.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem33.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem34.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem35.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem36.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem37.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem38.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem39.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem310.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem41.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem42.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem43.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem44.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem45.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem46.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem47.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem48.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem49.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem410.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem51.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem52.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem53.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem54.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem55.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem56.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem57.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem58.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem59.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem510.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem61.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem62.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem63.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem64.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem65.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem66.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem67.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem68.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem69.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem610.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem71.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem72.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem73.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem74.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem75.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem76.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem77.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem78.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem79.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem710.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem81.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem82.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem83.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem84.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem85.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem86.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem87.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem88.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem89.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem810.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem91.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem92.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem93.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem94.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem95.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem96.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem97.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem98.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem99.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem910.setText(scan.useDelimiter("\\Z").nextLine());

                criteriaItem101.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem102.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem103.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem104.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem105.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem106.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem107.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem108.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem109.setText(scan.useDelimiter("\\Z").nextLine());
                criteriaItem1010.setText(scan.useDelimiter("\\Z").nextLine());

                //Bottom of Page
                classNameLabel.setText(scan.useDelimiter("\\Z").nextLine());
                currentGradeProgress.setProgress(Double.parseDouble(scan.useDelimiter("\\Z").nextLine()));
                projectedGPA.setText(scan.useDelimiter("\\Z").nextLine());
*/

                dataFile = file;

                controller1Main.setSaveStatus(true);

            } catch (FileNotFoundException e) {
                //Nothing
            }
        }
        else { //If file location not set Do nothing and report false
            if(dataFile == null)
                controller1Main.setSaveStatus(false);
        }
    }


    public void handleSaveAsClick() {
        FileChooser fc = new FileChooser();
        fc.setTitle("Grade Data Save As Window");

        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*"));

        File file = fc.showSaveDialog(primaryStage);

        if (file != null)  {
            dataFile = file;
            handleSaveClick();
            controller1Main.setSaveStatus(true);
        }
        else { //If file location not set Do nothing and report false
            if(dataFile == null)
                controller1Main.setSaveStatus(false);
        }
    }


    public void handleSaveClick() {
        try (PrintStream ps = new PrintStream(dataFile)){

            if(dataFile != null) {

                for(int i = 0; i < numberYears ; i++)
                    for(int j = 0; j < numberSemesters ; j++)
                        for(int k = 0; k < numberClasses ; k++)
                            for(int n = 0; n < items ; n++)
                                ps.println(dataArray[n][k][j][i]);

                for(int i = 0; i < 3 ; i++)
                    for(int j = 0; j < 6 ; j++)
                        ps.println(semesterBoxItems[j][i]);

                for(int i = 0; i < 5 ; i++)
                    for(int j = 0; j < 10 ; j++)
                        ps.println(classBoxItems[j][i]);



                ps.println(academicYearList.size());


                for (String s: academicYearList){
                    ps.println(s);
                }

                ps.println(semesterList.size());

                for (String s: semesterList){
                    ps.println(s);
                }

                ps.println(classBoxList.size());

                for (String s: classBoxList){
                    ps.println(s);
                }




/*
                ps.println(academicYear.getSelectionModel().getSelectedItem());
                ps.println(semester.getSelectionModel().getSelectedItem());
                ps.println(qualityPoints.getText());
                ps.println(currentGPA.getText());
                ps.println(gpaHours.getText());
                ps.println(academicStatus.getText());
                ps.println(academicGrade.getText());

                ps.println(classNameBox.getText());
                ps.println(classCreditHours.getText());
                ps.println(classSelectionBox.getSelectionModel().getSelectedItem());
                ps.println(gpaHoursProgress.getProgress());
                ps.println(qualityPointsProgress.getProgress());


                ps.println(criteria1.getText());
                ps.println(criteria2.getText());
                ps.println(criteria3.getText());
                ps.println(criteria4.getText());
                ps.println(criteria5.getText());
                ps.println(criteria6.getText());
                ps.println(criteria7.getText());
                ps.println(criteria8.getText());
                ps.println(criteria9.getText());
                ps.println(criteria10.getText());

                ps.println(weight1.getText());
                ps.println(weight2.getText());
                ps.println(weight3.getText());
                ps.println(weight4.getText());
                ps.println(weight5.getText());
                ps.println(weight6.getText());
                ps.println(weight7.getText());
                ps.println(weight8.getText());
                ps.println(weight9.getText());
                ps.println(weight10.getText());

                ps.println(totalPoints1.getText());
                ps.println(totalPoints1.getText());
                ps.println(totalPoints2.getText());
                ps.println(totalPoints3.getText());
                ps.println(totalPoints4.getText());
                ps.println(totalPoints5.getText());
                ps.println(totalPoints6.getText());
                ps.println(totalPoints7.getText());
                ps.println(totalPoints8.getText());
                ps.println(totalPoints9.getText());
                ps.println(totalPoints10.getText());

                ps.println(pointsEarned1.getText());
                ps.println(pointsEarned2.getText());
                ps.println(pointsEarned3.getText());
                ps.println(pointsEarned4.getText());
                ps.println(pointsEarned5.getText());
                ps.println(pointsEarned6.getText());
                ps.println(pointsEarned7.getText());
                ps.println(pointsEarned8.getText());
                ps.println(pointsEarned9.getText());
                ps.println(pointsEarned10.getText());

                ps.println(criteriaItem11.getText());
                ps.println(criteriaItem12.getText());
                ps.println(criteriaItem13.getText());
                ps.println(criteriaItem14.getText());
                ps.println(criteriaItem15.getText());
                ps.println(criteriaItem16.getText());
                ps.println(criteriaItem17.getText());
                ps.println(criteriaItem18.getText());
                ps.println(criteriaItem19.getText());
                ps.println(criteriaItem110.getText());

                ps.println(criteriaItem21.getText());
                ps.println(criteriaItem22.getText());
                ps.println(criteriaItem23.getText());
                ps.println(criteriaItem24.getText());
                ps.println(criteriaItem25.getText());
                ps.println(criteriaItem26.getText());
                ps.println(criteriaItem27.getText());
                ps.println(criteriaItem28.getText());
                ps.println(criteriaItem29.getText());
                ps.println(criteriaItem210.getText());

                ps.println(criteriaItem31.getText());
                ps.println(criteriaItem32.getText());
                ps.println(criteriaItem33.getText());
                ps.println(criteriaItem34.getText());
                ps.println(criteriaItem35.getText());
                ps.println(criteriaItem36.getText());
                ps.println(criteriaItem37.getText());
                ps.println(criteriaItem38.getText());
                ps.println(criteriaItem39.getText());
                ps.println(criteriaItem310.getText());

                ps.println(criteriaItem41.getText());
                ps.println(criteriaItem42.getText());
                ps.println(criteriaItem43.getText());
                ps.println(criteriaItem44.getText());
                ps.println(criteriaItem45.getText());
                ps.println(criteriaItem46.getText());
                ps.println(criteriaItem47.getText());
                ps.println(criteriaItem48.getText());
                ps.println(criteriaItem49.getText());
                ps.println(criteriaItem410.getText());

                ps.println(criteriaItem51.getText());
                ps.println(criteriaItem52.getText());
                ps.println(criteriaItem53.getText());
                ps.println(criteriaItem54.getText());
                ps.println(criteriaItem55.getText());
                ps.println(criteriaItem56.getText());
                ps.println(criteriaItem57.getText());
                ps.println(criteriaItem58.getText());
                ps.println(criteriaItem59.getText());
                ps.println(criteriaItem510.getText());

                ps.println(criteriaItem61.getText());
                ps.println(criteriaItem62.getText());
                ps.println(criteriaItem63.getText());
                ps.println(criteriaItem64.getText());
                ps.println(criteriaItem65.getText());
                ps.println(criteriaItem66.getText());
                ps.println(criteriaItem67.getText());
                ps.println(criteriaItem68.getText());
                ps.println(criteriaItem69.getText());
                ps.println(criteriaItem610.getText());

                ps.println(criteriaItem71.getText());
                ps.println(criteriaItem72.getText());
                ps.println(criteriaItem73.getText());
                ps.println(criteriaItem74.getText());
                ps.println(criteriaItem75.getText());
                ps.println(criteriaItem76.getText());
                ps.println(criteriaItem77.getText());
                ps.println(criteriaItem78.getText());
                ps.println(criteriaItem79.getText());
                ps.println(criteriaItem710.getText());

                ps.println(criteriaItem81.getText());
                ps.println(criteriaItem82.getText());
                ps.println(criteriaItem83.getText());
                ps.println(criteriaItem84.getText());
                ps.println(criteriaItem85.getText());
                ps.println(criteriaItem86.getText());
                ps.println(criteriaItem87.getText());
                ps.println(criteriaItem88.getText());
                ps.println(criteriaItem89.getText());
                ps.println(criteriaItem810.getText());

                ps.println(criteriaItem91.getText());
                ps.println(criteriaItem92.getText());
                ps.println(criteriaItem93.getText());
                ps.println(criteriaItem94.getText());
                ps.println(criteriaItem95.getText());
                ps.println(criteriaItem96.getText());
                ps.println(criteriaItem97.getText());
                ps.println(criteriaItem98.getText());
                ps.println(criteriaItem99.getText());
                ps.println(criteriaItem910.getText());

                ps.println(criteriaItem101.getText());
                ps.println(criteriaItem102.getText());
                ps.println(criteriaItem103.getText());
                ps.println(criteriaItem104.getText());
                ps.println(criteriaItem105.getText());
                ps.println(criteriaItem106.getText());
                ps.println(criteriaItem107.getText());
                ps.println(criteriaItem108.getText());
                ps.println(criteriaItem109.getText());
                ps.println(criteriaItem1010.getText());

                ps.println(classNameLabel.getText());
                ps.println(currentGradeProgress.getProgress());
                ps.println(projectedGPA.getText());
*/

            }
            else {
                if(dataFile == null)
                    controller1Main.setSaveStatus(false);
            }

        }catch(FileNotFoundException e){
            controller1Main.setSaveStatus(false);
        }
    }





////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Start Setters and Getters
    public void setAcademicYear(ComboBox<String> academicYear) {
        this.academicYear = academicYear;
    }
    public void setSemester(ComboBox<String> semester) {
        this.semester = semester;
    }
    public void setClassSelectionBox(ComboBox<String> classSelectionBox) {
        this.classSelectionBox = classSelectionBox;
    }
    public void setQualityPoints(TextField qualityPoints) {
        this.qualityPoints = qualityPoints;
    }
    public void setCurrentGPA(TextField currentGPA) {
        this.currentGPA = currentGPA;
    }
    public void setGpaHours(TextField gpaHours) {
        this.gpaHours = gpaHours;
    }
    public void setClassNameBox(TextField classNameBox) {
        this.classNameBox = classNameBox;
    }
    public void setClassCreditHours(TextField classCreditHours) {
        this.classCreditHours = classCreditHours;
    }
    public void setCriteria1(TextField criteria1) {
        this.criteria1 = criteria1;
    }
    public void setCriteria2(TextField criteria2) {
        this.criteria2 = criteria2;
    }
    public void setCriteria3(TextField criteria3) {
        this.criteria3 = criteria3;
    }
    public void setCriteria4(TextField criteria4) {
        this.criteria4 = criteria4;
    }
    public void setCriteria5(TextField criteria5) {
        this.criteria5 = criteria5;
    }
    public void setCriteria6(TextField criteria6) {
        this.criteria6 = criteria6;
    }
    public void setCriteria7(TextField criteria7) {
        this.criteria7 = criteria7;
    }
    public void setCriteria8(TextField criteria8) {
        this.criteria8 = criteria8;
    }
    public void setCriteria9(TextField criteria9) {
        this.criteria9 = criteria9;
    }
    public void setCriteria10(TextField criteria10) {
        this.criteria10 = criteria10;
    }
    public void setWeight1(TextField weight1) {
        this.weight1 = weight1;
    }
    public void setWeight2(TextField weight2) {
        this.weight2 = weight2;
    }
    public void setWeight3(TextField weight3) {
        this.weight3 = weight3;
    }
    public void setWeight4(TextField weight4) {
        this.weight4 = weight4;
    }
    public void setWeight5(TextField weight5) {
        this.weight5 = weight5;
    }
    public void setWeight6(TextField weight6) {
        this.weight6 = weight6;
    }
    public void setWeight7(TextField weight7) {
        this.weight7 = weight7;
    }
    public void setWeight8(TextField weight8) {
        this.weight8 = weight8;
    }
    public void setWeight9(TextField weight9) {
        this.weight9 = weight9;
    }
    public void setWeight10(TextField weight10) {
        this.weight10 = weight10;
    }
    public void setTotalPoints1(TextField totalPoints1) {
        this.totalPoints1 = totalPoints1;
    }
    public void setTotalPoints2(TextField totalPoints2) {
        this.totalPoints2 = totalPoints2;
    }
    public void setTotalPoints3(TextField totalPoints3) {
        this.totalPoints3 = totalPoints3;
    }
    public void setTotalPoints4(TextField totalPoints4) {
        this.totalPoints4 = totalPoints4;
    }
    public void setTotalPoints5(TextField totalPoints5) {
        this.totalPoints5 = totalPoints5;
    }
    public void setTotalPoints6(TextField totalPoints6) {
        this.totalPoints6 = totalPoints6;
    }
    public void setTotalPoints7(TextField totalPoints7) {
        this.totalPoints7 = totalPoints7;
    }
    public void setTotalPoints8(TextField totalPoints8) {
        this.totalPoints8 = totalPoints8;
    }
    public void setTotalPoints9(TextField totalPoints9) {
        this.totalPoints9 = totalPoints9;
    }
    public void setTotalPoints10(TextField totalPoints10) {
        this.totalPoints10 = totalPoints10;
    }
    public void setPointsEarned1(Label pointsEarned1) {
        this.pointsEarned1 = pointsEarned1;
    }
    public void setPointsEarned2(Label pointsEarned2) {
        this.pointsEarned2 = pointsEarned2;
    }
    public void setPointsEarned3(Label pointsEarned3) {
        this.pointsEarned3 = pointsEarned3;
    }
    public void setPointsEarned4(Label pointsEarned4) {
        this.pointsEarned4 = pointsEarned4;
    }
    public void setPointsEarned5(Label pointsEarned5) {
        this.pointsEarned5 = pointsEarned5;
    }
    public void setPointsEarned6(Label pointsEarned6) {
        this.pointsEarned6 = pointsEarned6;
    }
    public void setPointsEarned7(Label pointsEarned7) {
        this.pointsEarned7 = pointsEarned7;
    }
    public void setPointsEarned8(Label pointsEarned8) {
        this.pointsEarned8 = pointsEarned8;
    }
    public void setPointsEarned9(Label pointsEarned9) {
        this.pointsEarned9 = pointsEarned9;
    }
    public void setPointsEarned10(Label pointsEarned10) {
        this.pointsEarned10 = pointsEarned10;
    }
    public void setCriteriaItem11(TextField criteriaItem11) {
        this.criteriaItem11 = criteriaItem11;
    }
    public void setCriteriaItem12(TextField criteriaItem12) {
        this.criteriaItem12 = criteriaItem12;
    }
    public void setCriteriaItem13(TextField criteriaItem13) {
        this.criteriaItem13 = criteriaItem13;
    }
    public void setCriteriaItem14(TextField criteriaItem14) {
        this.criteriaItem14 = criteriaItem14;
    }
    public void setCriteriaItem15(TextField criteriaItem15) {
        this.criteriaItem15 = criteriaItem15;
    }
    public void setCriteriaItem16(TextField criteriaItem16) {
        this.criteriaItem16 = criteriaItem16;
    }
    public void setCriteriaItem17(TextField criteriaItem17) {
        this.criteriaItem17 = criteriaItem17;
    }
    public void setCriteriaItem18(TextField criteriaItem18) {
        this.criteriaItem18 = criteriaItem18;
    }
    public void setCriteriaItem19(TextField criteriaItem19) {
        this.criteriaItem19 = criteriaItem19;
    }
    public void setCriteriaItem110(TextField criteriaItem110) {
        this.criteriaItem110 = criteriaItem110;
    }
    public void setCriteriaItem21(TextField criteriaItem21) {
        this.criteriaItem21 = criteriaItem21;
    }
    public void setCriteriaItem22(TextField criteriaItem22) {
        this.criteriaItem22 = criteriaItem22;
    }
    public void setCriteriaItem23(TextField criteriaItem23) {
        this.criteriaItem23 = criteriaItem23;
    }
    public void setCriteriaItem24(TextField criteriaItem24) {
        this.criteriaItem24 = criteriaItem24;
    }
    public void setCriteriaItem25(TextField criteriaItem25) {
        this.criteriaItem25 = criteriaItem25;
    }
    public void setCriteriaItem26(TextField criteriaItem26) {
        this.criteriaItem26 = criteriaItem26;
    }
    public void setCriteriaItem27(TextField criteriaItem27) {
        this.criteriaItem27 = criteriaItem27;
    }
    public void setCriteriaItem28(TextField criteriaItem28) {
        this.criteriaItem28 = criteriaItem28;
    }
    public void setCriteriaItem29(TextField criteriaItem29) {
        this.criteriaItem29 = criteriaItem29;
    }
    public void setCriteriaItem210(TextField criteriaItem210) {
        this.criteriaItem210 = criteriaItem210;
    }
    public void setCriteriaItem31(TextField criteriaItem31) {
        this.criteriaItem31 = criteriaItem31;
    }
    public void setCriteriaItem32(TextField criteriaItem32) {
        this.criteriaItem32 = criteriaItem32;
    }
    public void setCriteriaItem33(TextField criteriaItem33) {
        this.criteriaItem33 = criteriaItem33;
    }
    public void setCriteriaItem34(TextField criteriaItem34) {
        this.criteriaItem34 = criteriaItem34;
    }
    public void setCriteriaItem35(TextField criteriaItem35) {
        this.criteriaItem35 = criteriaItem35;
    }
    public void setCriteriaItem36(TextField criteriaItem36) {
        this.criteriaItem36 = criteriaItem36;
    }
    public void setCriteriaItem37(TextField criteriaItem37) {
        this.criteriaItem37 = criteriaItem37;
    }
    public void setCriteriaItem38(TextField criteriaItem38) {
        this.criteriaItem38 = criteriaItem38;
    }
    public void setCriteriaItem39(TextField criteriaItem39) {
        this.criteriaItem39 = criteriaItem39;
    }
    public void setCriteriaItem310(TextField criteriaItem310) {
        this.criteriaItem310 = criteriaItem310;
    }
    public void setCriteriaItem41(TextField criteriaItem41) {
        this.criteriaItem41 = criteriaItem41;
    }
    public void setCriteriaItem42(TextField criteriaItem42) {
        this.criteriaItem42 = criteriaItem42;
    }
    public void setCriteriaItem43(TextField criteriaItem43) {
        this.criteriaItem43 = criteriaItem43;
    }
    public void setCriteriaItem44(TextField criteriaItem44) {
        this.criteriaItem44 = criteriaItem44;
    }
    public void setCriteriaItem45(TextField criteriaItem45) {
        this.criteriaItem45 = criteriaItem45;
    }
    public void setCriteriaItem46(TextField criteriaItem46) {
        this.criteriaItem46 = criteriaItem46;
    }
    public void setCriteriaItem47(TextField criteriaItem47) {
        this.criteriaItem47 = criteriaItem47;
    }
    public void setCriteriaItem48(TextField criteriaItem48) {
        this.criteriaItem48 = criteriaItem48;
    }
    public void setCriteriaItem49(TextField criteriaItem49) {
        this.criteriaItem49 = criteriaItem49;
    }
    public void setCriteriaItem410(TextField criteriaItem410) {
        this.criteriaItem410 = criteriaItem410;
    }
    public void setCriteriaItem51(TextField criteriaItem51) {
        this.criteriaItem51 = criteriaItem51;
    }
    public void setCriteriaItem52(TextField criteriaItem52) {
        this.criteriaItem52 = criteriaItem52;
    }
    public void setCriteriaItem53(TextField criteriaItem53) {
        this.criteriaItem53 = criteriaItem53;
    }
    public void setCriteriaItem54(TextField criteriaItem54) {
        this.criteriaItem54 = criteriaItem54;
    }
    public void setCriteriaItem55(TextField criteriaItem55) {
        this.criteriaItem55 = criteriaItem55;
    }
    public void setCriteriaItem56(TextField criteriaItem56) {
        this.criteriaItem56 = criteriaItem56;
    }
    public void setCriteriaItem57(TextField criteriaItem57) {
        this.criteriaItem57 = criteriaItem57;
    }
    public void setCriteriaItem58(TextField criteriaItem58) {
        this.criteriaItem58 = criteriaItem58;
    }
    public void setCriteriaItem59(TextField criteriaItem59) {
        this.criteriaItem59 = criteriaItem59;
    }
    public void setCriteriaItem510(TextField criteriaItem510) {
        this.criteriaItem510 = criteriaItem510;
    }
    public void setCriteriaItem61(TextField criteriaItem61) {
        this.criteriaItem61 = criteriaItem61;
    }
    public void setCriteriaItem62(TextField criteriaItem62) {
        this.criteriaItem62 = criteriaItem62;
    }
    public void setCriteriaItem63(TextField criteriaItem63) {
        this.criteriaItem63 = criteriaItem63;
    }
    public void setCriteriaItem64(TextField criteriaItem64) {
        this.criteriaItem64 = criteriaItem64;
    }
    public void setCriteriaItem65(TextField criteriaItem65) {
        this.criteriaItem65 = criteriaItem65;
    }
    public void setCriteriaItem66(TextField criteriaItem66) {
        this.criteriaItem66 = criteriaItem66;
    }
    public void setCriteriaItem67(TextField criteriaItem67) {
        this.criteriaItem67 = criteriaItem67;
    }
    public void setCriteriaItem68(TextField criteriaItem68) {
        this.criteriaItem68 = criteriaItem68;
    }
    public void setCriteriaItem69(TextField criteriaItem69) {
        this.criteriaItem69 = criteriaItem69;
    }
    public void setCriteriaItem610(TextField criteriaItem610) {
        this.criteriaItem610 = criteriaItem610;
    }
    public void setCriteriaItem71(TextField criteriaItem71) {
        this.criteriaItem71 = criteriaItem71;
    }
    public void setCriteriaItem72(TextField criteriaItem72) {
        this.criteriaItem72 = criteriaItem72;
    }
    public void setCriteriaItem73(TextField criteriaItem73) {
        this.criteriaItem73 = criteriaItem73;
    }
    public void setCriteriaItem74(TextField criteriaItem74) {
        this.criteriaItem74 = criteriaItem74;
    }
    public void setCriteriaItem75(TextField criteriaItem75) {
        this.criteriaItem75 = criteriaItem75;
    }
    public void setCriteriaItem76(TextField criteriaItem76) {
        this.criteriaItem76 = criteriaItem76;
    }
    public void setCriteriaItem77(TextField criteriaItem77) {
        this.criteriaItem77 = criteriaItem77;
    }
    public void setCriteriaItem78(TextField criteriaItem78) {
        this.criteriaItem78 = criteriaItem78;
    }
    public void setCriteriaItem79(TextField criteriaItem79) {
        this.criteriaItem79 = criteriaItem79;
    }
    public void setCriteriaItem710(TextField criteriaItem710) {
        this.criteriaItem710 = criteriaItem710;
    }
    public void setCriteriaItem81(TextField criteriaItem81) {
        this.criteriaItem81 = criteriaItem81;
    }
    public void setCriteriaItem82(TextField criteriaItem82) {
        this.criteriaItem82 = criteriaItem82;
    }
    public void setCriteriaItem83(TextField criteriaItem83) {
        this.criteriaItem83 = criteriaItem83;
    }
    public void setCriteriaItem84(TextField criteriaItem84) {
        this.criteriaItem84 = criteriaItem84;
    }
    public void setCriteriaItem85(TextField criteriaItem85) {
        this.criteriaItem85 = criteriaItem85;
    }
    public void setCriteriaItem86(TextField criteriaItem86) {
        this.criteriaItem86 = criteriaItem86;
    }
    public void setCriteriaItem87(TextField criteriaItem87) {
        this.criteriaItem87 = criteriaItem87;
    }
    public void setCriteriaItem88(TextField criteriaItem88) {
        this.criteriaItem88 = criteriaItem88;
    }
    public void setCriteriaItem89(TextField criteriaItem89) {
        this.criteriaItem89 = criteriaItem89;
    }
    public void setCriteriaItem810(TextField criteriaItem810) {
        this.criteriaItem810 = criteriaItem810;
    }
    public void setCriteriaItem91(TextField criteriaItem91) {
        this.criteriaItem91 = criteriaItem91;
    }
    public void setCriteriaItem92(TextField criteriaItem92) {
        this.criteriaItem92 = criteriaItem92;
    }
    public void setCriteriaItem93(TextField criteriaItem93) {
        this.criteriaItem93 = criteriaItem93;
    }
    public void setCriteriaItem94(TextField criteriaItem94) {
        this.criteriaItem94 = criteriaItem94;
    }
    public void setCriteriaItem95(TextField criteriaItem95) {
        this.criteriaItem95 = criteriaItem95;
    }
    public void setCriteriaItem96(TextField criteriaItem96) {
        this.criteriaItem96 = criteriaItem96;
    }
    public void setCriteriaItem97(TextField criteriaItem97) {
        this.criteriaItem97 = criteriaItem97;
    }
    public void setCriteriaItem98(TextField criteriaItem98) {
        this.criteriaItem98 = criteriaItem98;
    }
    public void setCriteriaItem99(TextField criteriaItem99) {
        this.criteriaItem99 = criteriaItem99;
    }
    public void setCriteriaItem910(TextField criteriaItem910) {
        this.criteriaItem910 = criteriaItem910;
    }
    public void setCriteriaItem101(TextField criteriaItem101) {
        this.criteriaItem101 = criteriaItem101;
    }
    public void setCriteriaItem102(TextField criteriaItem102) {
        this.criteriaItem102 = criteriaItem102;
    }
    public void setCriteriaItem103(TextField criteriaItem103) {
        this.criteriaItem103 = criteriaItem103;
    }
    public void setCriteriaItem104(TextField criteriaItem104) {
        this.criteriaItem104 = criteriaItem104;
    }
    public void setCriteriaItem105(TextField criteriaItem105) {
        this.criteriaItem105 = criteriaItem105;
    }
    public void setCriteriaItem106(TextField criteriaItem106) {
        this.criteriaItem106 = criteriaItem106;
    }
    public void setCriteriaItem107(TextField criteriaItem107) {
        this.criteriaItem107 = criteriaItem107;
    }
    public void setCriteriaItem108(TextField criteriaItem108) {
        this.criteriaItem108 = criteriaItem108;
    }
    public void setCriteriaItem109(TextField criteriaItem109) {
        this.criteriaItem109 = criteriaItem109;
    }
    public void setCriteriaItem1010(TextField criteriaItem1010) {
        this.criteriaItem1010 = criteriaItem1010;
    }
    public void setAcademicStatus(Label academicStatus) {
        this.academicStatus = academicStatus;
    }
    public void setAcademicGrade(Label academicGrade) {
        this.academicGrade = academicGrade;
    }
    public void setClassNameLabel(Label classNameLabel) {
        this.classNameLabel = classNameLabel;
    }
    public void setProjectedGPA(Label projectedGPA) {
        this.projectedGPA = projectedGPA;
    }
    public void setGpaHoursProgress(ProgressIndicator gpaHoursProgress) {
        this.gpaHoursProgress = gpaHoursProgress;
    }
    public void setQualityPointsProgress(ProgressIndicator qualityPointsProgress) {
        this.qualityPointsProgress = qualityPointsProgress;
    }
    public void setCurrentGradeProgress(ProgressIndicator currentGradeProgress) {
        this.currentGradeProgress = currentGradeProgress;
    }
    public void setCreditHoursLabel(Label creditHoursLabel) {
        this.creditHoursLabel = creditHoursLabel;
    }
    public ComboBox<String> getAcademicYear() {
        return academicYear;
    }
    public ComboBox<String> getSemester() {
        return semester;
    }
    public ComboBox<String> getClassSelectionBox() {
        return classSelectionBox;
    }
    public TextField getQualityPoints() {
        return qualityPoints;
    }
    public TextField getCurrentGPA() {
        return currentGPA;
    }
    public TextField getGpaHours() {
        return gpaHours;
    }
    public TextField getClassNameBox() {
        return classNameBox;
    }
    public TextField getClassCreditHours() {
        return classCreditHours;
    }
    public TextField getCriteria1() {
        return criteria1;
    }
    public TextField getCriteria2() {
        return criteria2;
    }
    public TextField getCriteria3() {
        return criteria3;
    }
    public TextField getCriteria4() {
        return criteria4;
    }
    public TextField getCriteria5() {
        return criteria5;
    }
    public TextField getCriteria6() {
        return criteria6;
    }
    public TextField getCriteria7() {
        return criteria7;
    }
    public TextField getCriteria8() {
        return criteria8;
    }
    public TextField getCriteria9() {
        return criteria9;
    }
    public TextField getCriteria10() {
        return criteria10;
    }
    public TextField getWeight1() {
        return weight1;
    }
    public TextField getWeight2() {
        return weight2;
    }
    public TextField getWeight3() {
        return weight3;
    }
    public TextField getWeight4() {
        return weight4;
    }
    public TextField getWeight5() {
        return weight5;
    }
    public TextField getWeight6() {
        return weight6;
    }
    public TextField getWeight7() {
        return weight7;
    }
    public TextField getWeight8() {
        return weight8;
    }
    public TextField getWeight9() {
        return weight9;
    }
    public TextField getWeight10() {
        return weight10;
    }
    public TextField getTotalPoints1() {
        return totalPoints1;
    }
    public TextField getTotalPoints2() {
        return totalPoints2;
    }
    public TextField getTotalPoints3() {
        return totalPoints3;
    }
    public TextField getTotalPoints4() {
        return totalPoints4;
    }
    public TextField getTotalPoints5() {
        return totalPoints5;
    }
    public TextField getTotalPoints6() {
        return totalPoints6;
    }
    public TextField getTotalPoints7() {
        return totalPoints7;
    }
    public TextField getTotalPoints8() {
        return totalPoints8;
    }
    public TextField getTotalPoints9() {
        return totalPoints9;
    }
    public TextField getTotalPoints10() {
        return totalPoints10;
    }
    public TextField getCriteriaItem11() {
        return criteriaItem11;
    }
    public TextField getCriteriaItem12() {
        return criteriaItem12;
    }
    public TextField getCriteriaItem13() {
        return criteriaItem13;
    }
    public TextField getCriteriaItem14() {
        return criteriaItem14;
    }
    public TextField getCriteriaItem15() {
        return criteriaItem15;
    }
    public TextField getCriteriaItem16() {
        return criteriaItem16;
    }
    public TextField getCriteriaItem17() {
        return criteriaItem17;
    }
    public TextField getCriteriaItem18() {
        return criteriaItem18;
    }
    public TextField getCriteriaItem19() {
        return criteriaItem19;
    }
    public TextField getCriteriaItem110() {
        return criteriaItem110;
    }
    public TextField getCriteriaItem21() {
        return criteriaItem21;
    }
    public TextField getCriteriaItem22() {
        return criteriaItem22;
    }
    public TextField getCriteriaItem23() {
        return criteriaItem23;
    }
    public TextField getCriteriaItem24() {
        return criteriaItem24;
    }
    public TextField getCriteriaItem25() {
        return criteriaItem25;
    }
    public TextField getCriteriaItem26() {
        return criteriaItem26;
    }
    public TextField getCriteriaItem27() {
        return criteriaItem27;
    }
    public TextField getCriteriaItem28() {
        return criteriaItem28;
    }
    public TextField getCriteriaItem29() {
        return criteriaItem29;
    }
    public TextField getCriteriaItem210() {
        return criteriaItem210;
    }
    public TextField getCriteriaItem31() {
        return criteriaItem31;
    }
    public TextField getCriteriaItem32() {
        return criteriaItem32;
    }
    public TextField getCriteriaItem33() {
        return criteriaItem33;
    }
    public TextField getCriteriaItem34() {
        return criteriaItem34;
    }
    public TextField getCriteriaItem35() {
        return criteriaItem35;
    }
    public TextField getCriteriaItem36() {
        return criteriaItem36;
    }
    public TextField getCriteriaItem37() {
        return criteriaItem37;
    }
    public TextField getCriteriaItem38() {
        return criteriaItem38;
    }
    public TextField getCriteriaItem39() {
        return criteriaItem39;
    }
    public TextField getCriteriaItem310() {
        return criteriaItem310;
    }
    public TextField getCriteriaItem41() {
        return criteriaItem41;
    }
    public TextField getCriteriaItem42() {
        return criteriaItem42;
    }
    public TextField getCriteriaItem43() {
        return criteriaItem43;
    }
    public TextField getCriteriaItem44() {
        return criteriaItem44;
    }
    public TextField getCriteriaItem45() {
        return criteriaItem45;
    }
    public TextField getCriteriaItem46() {
        return criteriaItem46;
    }
    public TextField getCriteriaItem47() {
        return criteriaItem47;
    }
    public TextField getCriteriaItem48() {
        return criteriaItem48;
    }
    public TextField getCriteriaItem49() {
        return criteriaItem49;
    }
    public TextField getCriteriaItem410() {
        return criteriaItem410;
    }
    public TextField getCriteriaItem51() {
        return criteriaItem51;
    }
    public TextField getCriteriaItem52() {
        return criteriaItem52;
    }
    public TextField getCriteriaItem53() {
        return criteriaItem53;
    }
    public TextField getCriteriaItem54() {
        return criteriaItem54;
    }
    public TextField getCriteriaItem55() {
        return criteriaItem55;
    }
    public TextField getCriteriaItem56() {
        return criteriaItem56;
    }
    public TextField getCriteriaItem57() {
        return criteriaItem57;
    }
    public TextField getCriteriaItem58() {
        return criteriaItem58;
    }
    public TextField getCriteriaItem59() {
        return criteriaItem59;
    }
    public TextField getCriteriaItem510() {
        return criteriaItem510;
    }
    public TextField getCriteriaItem61() {
        return criteriaItem61;
    }
    public TextField getCriteriaItem62() {
        return criteriaItem62;
    }
    public TextField getCriteriaItem63() {
        return criteriaItem63;
    }
    public TextField getCriteriaItem64() {
        return criteriaItem64;
    }
    public TextField getCriteriaItem65() {
        return criteriaItem65;
    }
    public TextField getCriteriaItem66() {
        return criteriaItem66;
    }
    public TextField getCriteriaItem67() {
        return criteriaItem67;
    }
    public TextField getCriteriaItem68() {
        return criteriaItem68;
    }
    public TextField getCriteriaItem69() {
        return criteriaItem69;
    }
    public TextField getCriteriaItem610() {
        return criteriaItem610;
    }
    public TextField getCriteriaItem71() {
        return criteriaItem71;
    }
    public TextField getCriteriaItem72() {
        return criteriaItem72;
    }
    public TextField getCriteriaItem73() {
        return criteriaItem73;
    }
    public TextField getCriteriaItem74() {
        return criteriaItem74;
    }
    public TextField getCriteriaItem75() {
        return criteriaItem75;
    }
    public TextField getCriteriaItem76() {
        return criteriaItem76;
    }
    public TextField getCriteriaItem77() {
        return criteriaItem77;
    }
    public TextField getCriteriaItem78() {
        return criteriaItem78;
    }
    public TextField getCriteriaItem79() {
        return criteriaItem79;
    }
    public TextField getCriteriaItem710() {
        return criteriaItem710;
    }
    public TextField getCriteriaItem81() {
        return criteriaItem81;
    }
    public TextField getCriteriaItem82() {
        return criteriaItem82;
    }
    public TextField getCriteriaItem83() {
        return criteriaItem83;
    }
    public TextField getCriteriaItem84() {
        return criteriaItem84;
    }
    public TextField getCriteriaItem85() {
        return criteriaItem85;
    }
    public TextField getCriteriaItem86() {
        return criteriaItem86;
    }
    public TextField getCriteriaItem87() {
        return criteriaItem87;
    }
    public TextField getCriteriaItem88() {
        return criteriaItem88;
    }
    public TextField getCriteriaItem89() {
        return criteriaItem89;
    }
    public TextField getCriteriaItem810() {
        return criteriaItem810;
    }
    public TextField getCriteriaItem91() {
        return criteriaItem91;
    }
    public TextField getCriteriaItem92() {
        return criteriaItem92;
    }
    public TextField getCriteriaItem93() {
        return criteriaItem93;
    }
    public TextField getCriteriaItem94() {
        return criteriaItem94;
    }
    public TextField getCriteriaItem95() {
        return criteriaItem95;
    }
    public TextField getCriteriaItem96() {
        return criteriaItem96;
    }
    public TextField getCriteriaItem97() {
        return criteriaItem97;
    }
    public TextField getCriteriaItem98() {
        return criteriaItem98;
    }
    public TextField getCriteriaItem99() {
        return criteriaItem99;
    }
    public TextField getCriteriaItem910() {
        return criteriaItem910;
    }
    public TextField getCriteriaItem101() {
        return criteriaItem101;
    }
    public TextField getCriteriaItem102() {
        return criteriaItem102;
    }
    public TextField getCriteriaItem103() {
        return criteriaItem103;
    }
    public TextField getCriteriaItem104() {
        return criteriaItem104;
    }
    public TextField getCriteriaItem105() {
        return criteriaItem105;
    }
    public TextField getCriteriaItem106() {
        return criteriaItem106;
    }
    public TextField getCriteriaItem107() {
        return criteriaItem107;
    }
    public TextField getCriteriaItem108() {
        return criteriaItem108;
    }
    public TextField getCriteriaItem109() {
        return criteriaItem109;
    }
    public TextField getCriteriaItem1010() {
        return criteriaItem1010;
    }
    public Label getAcademicStatus() {
        return academicStatus;
    }
    public Label getAcademicGrade() {
        return academicGrade;
    }
    public Label getClassNameLabel() {
        return classNameLabel;
    }
    public Label getProjectedGPA() {
        return projectedGPA;
    }
    public Label getPointsEarned1() {
        return pointsEarned1;
    }
    public Label getPointsEarned2() {
        return pointsEarned2;
    }
    public Label getPointsEarned3() {
        return pointsEarned3;
    }
    public Label getPointsEarned4() {
        return pointsEarned4;
    }
    public Label getPointsEarned5() {
        return pointsEarned5;
    }
    public Label getPointsEarned6() {
        return pointsEarned6;
    }
    public Label getPointsEarned7() {
        return pointsEarned7;
    }
    public Label getPointsEarned8() {
        return pointsEarned8;
    }
    public Label getPointsEarned9() {
        return pointsEarned9;
    }
    public Label getPointsEarned10() {
        return pointsEarned10;
    }
    public ProgressIndicator getGpaHoursProgress() {
        return gpaHoursProgress;
    }
    public ProgressIndicator getQualityPointsProgress() {
        return qualityPointsProgress;
    }
    public ProgressIndicator getCurrentGradeProgress() {
        return currentGradeProgress;
    }
    public Label getCreditHoursLabel() {
        return creditHoursLabel;
    }
}