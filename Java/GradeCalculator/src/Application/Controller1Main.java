package Application;

import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller1Main implements Initializable {
    @FXML
    private Controller1Main controller1Main;
    @FXML
    private DataProcess data;
    @FXML
    private DecimalFormat f = new DecimalFormat("##.00");
    @FXML
    private boolean saveCheck = false, startQualityPointsHelperScene = true, disabled = false, startAddButton = true,
            startAcademicYear = true, startSemester = true, startClassSelectionBox = true;
    @FXML
    private String lastAcademicYearSelected, lastSemesterSelected, lastClassBoxSelected;
    @FXML
    public MenuItem newMenuItem, openMenuItem, saveMenuItem, saveAsMenuItem, quitMenuItem;
    @FXML
    private Button qualityPointsButton, addButton, goButton;
    @FXML
    private ComboBox<String> academicYear, semester, classSelectionBox;
    @FXML
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
    @FXML
    private Label academicStatus, academicGrade, classNameLabel, projectedGPA, creditHoursLabel,
            pointsEarned1, pointsEarned2, pointsEarned3, pointsEarned4, pointsEarned5,
            pointsEarned6, pointsEarned7, pointsEarned8, pointsEarned9, pointsEarned10;
    @FXML
    private ProgressIndicator gpaHoursProgress, qualityPointsProgress, currentGradeProgress;
    @FXML
    private Canvas canvas = new Canvas();
    @FXML
    private Image bearKat = new Image("sammyBearKat.png");
    @FXML
    private Stage stage2 = new Stage();

    private String musicFile = "C:\\Users\\nicho\\IdeaProjects\\GradeCalculator\\src\\wordUp.mp3";     // For example
    private Media sound = new Media(new File(musicFile).toURI().toString());
    private MediaPlayer mediaPlayer = new MediaPlayer(sound);



    @FXML
    public void newAction (Boolean check) {
        if(check) {
            clearAll();
            startUp();
        }
    }
    @FXML
    public void openAction(Boolean check) {
        if(check) {
            setData();
            data.handleOpenClick();
        }
    }
    @FXML
    public void saveAsAction() {
        setData();
        saveMenuItem.setDisable(false);
        saveCheck = true;
        data.handleSaveAsClick();
    }
    @FXML
    public void saveAction() {
        setData();
        data.handleSaveClick();
    }
    @FXML
    private void quitAction() {exitScreen();}
    @FXML
    private void setSemester() {}
    @FXML
    private void setGpaHours() {

        if(gpaHours.getText() == null || gpaHours.getText().isEmpty())
            return;

        int number = Integer.parseInt(gpaHours.getText());

        if (number >= 0 && number < 30)
            academicStatus.setText("Freshman");
        else if (number >= 30 && number < 60)
            academicStatus.setText("Sophomore");
        else if (number >= 60 && number < 90)
            academicStatus.setText("Junior");
        else if (number >= 90 && number <= 120)
            academicStatus.setText("Senior");
        else
            academicStatus.setText("Graduate");

        gpaHoursProgress.setProgress(number / 120.0);
        setProjectedGPA();

    }
    @FXML
    private void setQualityPoints () {

        if(gpaHours.getText() == null || qualityPoints.getText() == null || gpaHours.getText().isEmpty() ||
                qualityPoints.getText().isEmpty())
            return;

        double hours, points, gpa;

        hours = Double.parseDouble(gpaHours.getText());
        points = Double.parseDouble(qualityPoints.getText());

        if(hours != 0) {
            gpa = points / hours;
            currentGPA.setText(f.format(gpa));
            setCurrentGPA();
            setProjectedGPA();
        }
    }
    @FXML
    private void setQualityPointsHelperScene() {
        if(startQualityPointsHelperScene) {
            try {
                Platform.setImplicitExit(true);
                stage2.setOnCloseRequest(Event::consume);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Window2Helper.fxml"));
                Parent root = loader.load();
                Scene scene2 = new Scene(root, 450, 500);
                stage2.setScene(scene2);
                stage2.setTitle("Access Unofficial Transcript");
                stage2.showAndWait();

            }catch(IOException e){/*Test*/}
        }
        else
            stage2.show();

        startQualityPointsHelperScene = false;
    }
    @FXML
    private void setCurrentGPA() {
        if(currentGPA.getText() == null || gpaHours.getText() == null || qualityPoints.getText() == null ||
                currentGPA.getText().isEmpty() || gpaHours.getText().isEmpty() || qualityPoints.getText().isEmpty() ){
            currentGPA.setText("");
            return;
        }

        double number = ((Double.parseDouble(currentGPA.getText()) + 1) * 20);

        //Split it to reduce Complexity
        if (number >= 80 && number <= 100) {
            if (number >= 97 && number <= 100)
                academicGrade.setText("A+");
                else if (number >= 93 && number < 97)
                    academicGrade.setText("A");
                else if (number >= 90 && number < 93)
                    academicGrade.setText("A-");
                else if (number >= 87 && number < 90)
                    academicGrade.setText("B+");
                else if (number >= 83 && number < 87)
                    academicGrade.setText("B");
                else if (number >= 80 && number < 83)
                    academicGrade.setText("B-");

            } else if (number < 80) {
            if (number >= 77 && number < 80)
                academicGrade.setText("C+");
            else if (number >= 73 && number < 77)
                academicGrade.setText("C");
            else if (number >= 70 && number < 73)
                academicGrade.setText("C-");
            else if (number >= 67 && number < 70)
                academicGrade.setText("D+");
            else if (number >= 63 && number < 67)
                academicGrade.setText("D");
            else if (number >= 60 && number < 63)
                academicGrade.setText("D-");
            else
                academicGrade.setText("F");
        }
        else
            academicGrade.setText("!");

        if(Double.parseDouble(currentGPA.getText()) == 0)
            qualityPointsProgress.setProgress(0);
        else {
            qualityPointsProgress.setProgress(number / 100.0);

            //Checks if Either value is empty or 0 and updates if GPA was filled
            if(gpaHours.getText().isEmpty()) {
                gpaHours.setText("0");
            }
            if(qualityPoints.getText().isEmpty()) {
                qualityPoints.setText("0");
            }

            //Start here
            //If it contains a 0 value or doesn't contain
            if (!(qualityPoints.getText().matches("^[0]+$")) && (gpaHours.getText().matches("^[0]+$"))) {

                double points = (Double.parseDouble(qualityPoints.getText()));
                double gpa = (Double.parseDouble(currentGPA.getText()));
                double hours = points / gpa;

                //Rounds credit hours to nearest whole number
                if((hours % 1) >= 0.5 && hours % 1 != 0.0)
                    hours = hours + 1;
                if((hours % 1) < 0.5 && hours % 1 != 0.0)
                    hours = hours - 1;


                gpaHours.setText(Integer.toString((int)(hours)));
                setGpaHours();

                currentGPA.setText(f.format(points / hours));
            }
            else if ((qualityPoints.getText().matches("^[0]+$")) &&
                    !(gpaHours.getText().matches("^[0]+$"))) {

                double hours = (Double.parseDouble(gpaHours.getText()));
                double gpa = (Double.parseDouble(currentGPA.getText()));

                qualityPoints.setText((f.format(hours * gpa)));
            }
        }
            setProjectedGPA();
    }
    @FXML
    private void setClassName() { }
    @FXML
    private void setClassCreditHours() {
        setProjectedGPA();
    }
    @FXML
    private void setProjectedGPA() {

        if(creditHoursLabel.getText() == null || qualityPoints.getText() == null || gpaHours == null ||
                currentGradeProgress.getProgress() == 0 || creditHoursLabel.getText().isEmpty() ||
                qualityPoints.getText().isEmpty() || gpaHours.getText().isEmpty()) {
            projectedGPA.setText("0.0");
            System.out.println("null or empty from setProjectedGPA");
            return;
        }

        Double currentGrade, gradePoints = 0.0, classHours, totalGrade, credits, grade;

        currentGrade = currentGradeProgress.getProgress() * 100;
        classHours = Double.parseDouble(creditHoursLabel.getText());

        if (currentGrade >= 90)                      //A
            gradePoints = classHours * 4.0;
        if (currentGrade >= 80 && currentGrade < 90) //B
            gradePoints = classHours * 3.0;
        if (currentGrade >= 70 && currentGrade < 80) //C
            gradePoints = classHours * 2.0;
        if (currentGrade >= 60 && currentGrade < 70) //D
            gradePoints = classHours * 1.0;
        if (currentGrade < 60)
            gradePoints = 0.0;

        totalGrade = gradePoints + Double.parseDouble(qualityPoints.getText());
        credits = classHours + Double.parseDouble(gpaHours.getText());
        grade = totalGrade / credits;
        projectedGPA.setText(String.valueOf(f.format(grade)));
    }
    @FXML
    private void setClassSelectionBox() {
        String value;
        classNameLabel.setText("");
        value = classNameBox.getText();
        classNameLabel.setText(classNameLabel.getText() + value);
    }
    @FXML
    private void pointsEarned1() {

        Double count = 0.0;
        if(criteriaItem11.getText() != null)
            if ((criteriaItem11.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem11.getText());
        if(criteriaItem21.getText() != null)
            if ((criteriaItem21.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem21.getText());
        if(criteriaItem21.getText() != null)
            if ((criteriaItem31.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem31.getText());
        if(criteriaItem41.getText() != null)
            if ((criteriaItem41.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem41.getText());
        if(criteriaItem51.getText() != null)
            if ((criteriaItem51.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem51.getText());
        if(criteriaItem61.getText() != null)
            if ((criteriaItem61.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem61.getText());
        if(criteriaItem71.getText() != null)
            if ((criteriaItem71.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem71.getText());
        if(criteriaItem81.getText() != null)
            if ((criteriaItem81.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem81.getText());
        if(criteriaItem91.getText() != null)
            if ((criteriaItem91.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem91.getText());
        if(criteriaItem101.getText() != null)
            if ((criteriaItem101.getText()).matches("^[0-9]+\\.?[0-9]*$"))
                count += Double.parseDouble(criteriaItem101.getText());

        pointsEarned1.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned2() {

        Double count = 0.0;

        if ((criteriaItem12.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem12.getText());
        if ((criteriaItem22.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem22.getText());
        if ((criteriaItem32.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem32.getText());
        if ((criteriaItem42.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem42.getText());
        if ((criteriaItem52.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem52.getText());
        if ((criteriaItem62.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem62.getText());
        if ((criteriaItem72.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem72.getText());
        if ((criteriaItem82.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem82.getText());
        if ((criteriaItem92.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem92.getText());
        if ((criteriaItem102.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem102.getText());
        pointsEarned2.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned3() {

        Double count = 0.0;

        if ((criteriaItem13.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem13.getText());
        if ((criteriaItem23.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem23.getText());
        if ((criteriaItem33.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem33.getText());
        if ((criteriaItem43.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem43.getText());
        if ((criteriaItem53.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem53.getText());
        if ((criteriaItem63.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem63.getText());
        if ((criteriaItem73.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem73.getText());
        if ((criteriaItem83.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem83.getText());
        if ((criteriaItem93.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem93.getText());
        if ((criteriaItem103.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem103.getText());
        pointsEarned3.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned4() {

        Double count = 0.0;

        if ((criteriaItem14.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem14.getText());
        if ((criteriaItem24.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem24.getText());
        if ((criteriaItem34.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem34.getText());
        if ((criteriaItem44.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem44.getText());
        if ((criteriaItem54.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem54.getText());
        if ((criteriaItem64.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem64.getText());
        if ((criteriaItem74.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem74.getText());
        if ((criteriaItem84.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem84.getText());
        if ((criteriaItem94.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem94.getText());
        if ((criteriaItem104.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem104.getText());
        pointsEarned4.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned5() {

        Double count = 0.0;

        if ((criteriaItem15.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem15.getText());
        if ((criteriaItem25.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem25.getText());
        if ((criteriaItem35.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem35.getText());
        if ((criteriaItem45.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem45.getText());
        if ((criteriaItem55.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem55.getText());
        if ((criteriaItem65.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem65.getText());
        if ((criteriaItem75.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem75.getText());
        if ((criteriaItem85.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem85.getText());
        if ((criteriaItem95.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem95.getText());
        if ((criteriaItem105.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem105.getText());
        pointsEarned5.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned6() {

        Double count = 0.0;

        if ((criteriaItem16.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem16.getText());
        if ((criteriaItem26.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem26.getText());
        if ((criteriaItem36.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem36.getText());
        if ((criteriaItem46.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem46.getText());
        if ((criteriaItem56.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem56.getText());
        if ((criteriaItem66.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem66.getText());
        if ((criteriaItem76.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem76.getText());
        if ((criteriaItem86.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem86.getText());
        if ((criteriaItem96.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem96.getText());
        if ((criteriaItem106.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem106.getText());
        pointsEarned6.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned7() {

        Double count = 0.0;

        if ((criteriaItem17.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem17.getText());
        if ((criteriaItem27.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem27.getText());
        if ((criteriaItem37.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem37.getText());
        if ((criteriaItem47.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem47.getText());
        if ((criteriaItem57.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem57.getText());
        if ((criteriaItem67.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem67.getText());
        if ((criteriaItem77.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem77.getText());
        if ((criteriaItem87.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem87.getText());
        if ((criteriaItem97.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem97.getText());
        if ((criteriaItem107.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem107.getText());
        pointsEarned7.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned8() {

        Double count = 0.0;

        if ((criteriaItem18.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem18.getText());
        if ((criteriaItem28.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem28.getText());
        if ((criteriaItem38.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem38.getText());
        if ((criteriaItem48.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem48.getText());
        if ((criteriaItem58.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem58.getText());
        if ((criteriaItem68.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem68.getText());
        if ((criteriaItem78.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem78.getText());
        if ((criteriaItem88.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem88.getText());
        if ((criteriaItem98.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem98.getText());
        if ((criteriaItem108.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem108.getText());
        pointsEarned8.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned9() {

        Double count = 0.0;

        if ((criteriaItem19.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem19.getText());
        if ((criteriaItem29.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem29.getText());
        if ((criteriaItem39.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem39.getText());
        if ((criteriaItem49.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem49.getText());
        if ((criteriaItem59.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem59.getText());
        if ((criteriaItem69.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem69.getText());
        if ((criteriaItem79.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem79.getText());
        if ((criteriaItem89.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem89.getText());
        if ((criteriaItem99.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem99.getText());
        if ((criteriaItem109.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem109.getText());
        pointsEarned9.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void pointsEarned10() {

        Double count = 0.0;

        if ((criteriaItem110.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem110.getText());
        if ((criteriaItem210.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem210.getText());
        if ((criteriaItem310.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem310.getText());
        if ((criteriaItem410.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem410.getText());
        if ((criteriaItem510.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem510.getText());
        if ((criteriaItem610.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem610.getText());
        if ((criteriaItem710.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem710.getText());
        if ((criteriaItem810.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem810.getText());
        if ((criteriaItem910.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem910.getText());
        if ((criteriaItem1010.getText()).matches("^[0-9]+\\.?[0-9]*$"))
            count += Double.parseDouble(criteriaItem1010.getText());
        pointsEarned10.setText(count.toString());
        calculateGrade();
    }
    @FXML
    private void calculateGrade() {

        double totalWeight = 0.0, totalGrade = 0.0;
        double grade;

        //Start TotalWeight

            if(weight1.getText() != null)
                if (((weight1.getText()).matches("[0-9]+")) && (Integer.parseInt(weight1.getText()) >= 0) &&
                    (Integer.parseInt(weight1.getText()) <= 100))
                totalWeight += Double.parseDouble(weight1.getText());

            if(weight2.getText() != null)
                if (((weight2.getText()).matches("[0-9]+")) && (Integer.parseInt(weight2.getText()) >= 0) &&
                    (Integer.parseInt(weight2.getText()) <= 100))
                totalWeight += Double.parseDouble(weight2.getText());

            if(weight3.getText() != null)
                if (((weight3.getText()).matches("[0-9]+")) && (Integer.parseInt(weight3.getText()) >= 0) &&
                    (Integer.parseInt(weight3.getText()) <= 100))
                totalWeight += Double.parseDouble(weight3.getText());

            if(weight4.getText() != null)
                if (((weight4.getText()).matches("[0-9]+")) && (Integer.parseInt(weight4.getText()) >= 0) &&
                    (Integer.parseInt(weight4.getText()) <= 100))
                totalWeight += Double.parseDouble(weight4.getText());

            if(weight5.getText() != null)
                if (((weight5.getText()).matches("[0-9]+")) && (Integer.parseInt(weight5.getText()) >= 0) &&
                    (Integer.parseInt(weight5.getText()) <= 100))
                totalWeight += Double.parseDouble(weight5.getText());

            if(weight6.getText() != null)
                if (((weight6.getText()).matches("[0-9]+")) && (Integer.parseInt(weight6.getText()) >= 0) &&
                    (Integer.parseInt(weight6.getText()) <= 100))
                totalWeight += Double.parseDouble(weight6.getText());

            if(weight7.getText() != null)
                if (((weight7.getText()).matches("[0-9]+")) && (Integer.parseInt(weight7.getText()) >= 0) &&
                    (Integer.parseInt(weight7.getText()) <= 100))
                totalWeight += Double.parseDouble(weight7.getText());

            if(weight8.getText() != null)
                if (((weight8.getText()).matches("[0-9]+")) && (Integer.parseInt(weight8.getText()) >= 0) &&
                    (Integer.parseInt(weight8.getText()) <= 100))
                totalWeight += Double.parseDouble(weight8.getText());

            if(weight9.getText() != null)
                if (((weight9.getText()).matches("[0-9]+")) && (Integer.parseInt(weight9.getText()) >= 0) &&
                    (Integer.parseInt(weight9.getText()) <= 100))
                totalWeight += Double.parseDouble(weight9.getText());

            if(weight10.getText() != null)
                if (((weight10.getText()).matches("[0-9]+")) && (Integer.parseInt(weight10.getText()) >= 0) &&
                    (Integer.parseInt(weight10.getText()) <= 100))
                totalWeight += Double.parseDouble(weight10.getText());

            //Start TotalGrade
            if(totalPoints1.getText() != null)
                if (((totalPoints1.getText()).matches("[0-9]+") && (((weight1.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight1.getText()) >= 0) && (Integer.parseInt(weight1.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned1.getText()) * Double.parseDouble(weight1.getText())) /
                        Double.parseDouble(totalPoints1.getText());

            if(totalPoints2.getText() != null)
                if (((totalPoints2.getText()).matches("[0-9]+") && (((weight2.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight2.getText()) >= 0) && (Integer.parseInt(weight2.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned2.getText()) * Double.parseDouble(weight2.getText())) /
                        Double.parseDouble(totalPoints2.getText());

            if(totalPoints3.getText() != null)
                if (((totalPoints3.getText()).matches("[0-9]+") && (((weight3.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight3.getText()) >= 0) && (Integer.parseInt(weight3.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned3.getText()) * Double.parseDouble(weight3.getText())) /
                        Double.parseDouble(totalPoints3.getText());

            if(totalPoints4.getText() != null)
                if (((totalPoints4.getText()).matches("[0-9]+") && (((weight4.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight4.getText()) >= 0) && (Integer.parseInt(weight4.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned4.getText()) * Double.parseDouble(weight4.getText())) /
                        Double.parseDouble(totalPoints4.getText());

            if(totalPoints5.getText() != null)
                if (((totalPoints5.getText()).matches("[0-9]+") && (((weight5.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight5.getText()) >= 0) && (Integer.parseInt(weight5.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned5.getText()) * Double.parseDouble(weight5.getText())) /
                        Double.parseDouble(totalPoints5.getText());

            if(totalPoints6.getText() != null)
                if (((totalPoints6.getText()).matches("[0-9]+") && (((weight6.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight6.getText()) >= 0) && (Integer.parseInt(weight6.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned6.getText()) * Double.parseDouble(weight6.getText())) /
                        Double.parseDouble(totalPoints6.getText());

            if(totalPoints7.getText() != null)
                if (((totalPoints7.getText()).matches("[0-9]+") && (((weight7.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight7.getText()) >= 0) && (Integer.parseInt(weight7.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned7.getText()) * Double.parseDouble(weight7.getText())) /
                        Double.parseDouble(totalPoints7.getText());
            if(totalPoints8.getText() != null)
                if (((totalPoints8.getText()).matches("[0-9]+") && (((weight8.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight8.getText()) >= 0) && (Integer.parseInt(weight8.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned8.getText()) * Double.parseDouble(weight8.getText())) /
                        Double.parseDouble(totalPoints8.getText());

            if(totalPoints9.getText() != null)
                if (((totalPoints9.getText()).matches("[0-9]+") && (((weight9.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight9.getText()) >= 0) && (Integer.parseInt(weight9.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned9.getText()) * Double.parseDouble(weight1.getText())) /
                        Double.parseDouble(totalPoints9.getText());

            if(totalPoints10.getText() != null)
                if (((totalPoints10.getText()).matches("[0-9]+") && (((weight10.getText()).matches("[0-9]+")) &&
                    (Integer.parseInt(weight10.getText()) >= 0) && (Integer.parseInt(weight10.getText()) <= 100))))
                totalGrade += (Double.parseDouble(pointsEarned10.getText()) * Double.parseDouble(weight10.getText())) /
                        Double.parseDouble(totalPoints10.getText());

            grade = totalGrade / totalWeight;

            //Update progressIndicator
            currentGradeProgress.setProgress(grade);

            //Set Projected GPA
            setProjectedGPA();

    }
    @FXML
    private void disableAll() {
        disabled = true;
        newMenuItem.setDisable(true);
        openMenuItem.setDisable(true);
        saveMenuItem.setDisable(true);
        saveAsMenuItem.setDisable(true);
       // quitMenuItem.setDisable(true);
        qualityPointsButton.setDisable(true);
        addButton.setDisable(true);
        goButton.setDisable(true);
        academicYear.setDisable(true);
        semester.setDisable(true);
        classSelectionBox.setDisable(true);
        qualityPoints.setDisable(true);
        currentGPA.setDisable(true);
        gpaHours.setDisable(true);
        classNameBox.setDisable(true);
        classCreditHours.setDisable(true);
        creditHoursLabel.setDisable(true);
        criteria1.setDisable(true);
        criteria2.setDisable(true);
        criteria3.setDisable(true);
        criteria4.setDisable(true);
        criteria5.setDisable(true);
        criteria6.setDisable(true);
        criteria7.setDisable(true);
        criteria8 .setDisable(true);
        criteria9.setDisable(true);
        criteria10.setDisable(true);
        weight1.setDisable(true);
        weight2.setDisable(true);
        weight3.setDisable(true);
        weight4.setDisable(true);
        weight5.setDisable(true);
        weight6.setDisable(true);
        weight7.setDisable(true);
        weight8.setDisable(true);
        weight9.setDisable(true);
        weight10.setDisable(true);
        totalPoints1.setDisable(true);
        totalPoints2.setDisable(true);
        totalPoints3.setDisable(true);
        totalPoints4.setDisable(true);
        totalPoints5.setDisable(true);
        totalPoints6.setDisable(true);
        totalPoints7.setDisable(true);
        totalPoints8.setDisable(true);
        totalPoints9.setDisable(true);
        totalPoints10.setDisable(true);
        criteriaItem11.setDisable(true);
        criteriaItem12.setDisable(true);
        criteriaItem13.setDisable(true);
        criteriaItem14.setDisable(true);
        criteriaItem15.setDisable(true);
        criteriaItem16.setDisable(true);
        criteriaItem17.setDisable(true);
        criteriaItem18.setDisable(true);
        criteriaItem19.setDisable(true);
        criteriaItem110.setDisable(true);
        criteriaItem21.setDisable(true);
        criteriaItem22.setDisable(true);
        criteriaItem23.setDisable(true);
        criteriaItem24.setDisable(true);
        criteriaItem25.setDisable(true);
        criteriaItem26.setDisable(true);
        criteriaItem27.setDisable(true);
        criteriaItem28.setDisable(true);
        criteriaItem29.setDisable(true);
        criteriaItem210.setDisable(true);
        criteriaItem31.setDisable(true);
        criteriaItem32.setDisable(true);
        criteriaItem33.setDisable(true);
        criteriaItem34.setDisable(true);
        criteriaItem35.setDisable(true);
        criteriaItem36.setDisable(true);
        criteriaItem37.setDisable(true);
        criteriaItem38.setDisable(true);
        criteriaItem39.setDisable(true);
        criteriaItem310.setDisable(true);
        criteriaItem41.setDisable(true);
        criteriaItem42.setDisable(true);
        criteriaItem43.setDisable(true);
        criteriaItem44.setDisable(true);
        criteriaItem45.setDisable(true);
        criteriaItem46.setDisable(true);
        criteriaItem47.setDisable(true);
        criteriaItem48.setDisable(true);
        criteriaItem49.setDisable(true);
        criteriaItem410.setDisable(true);
        criteriaItem51.setDisable(true);
        criteriaItem52.setDisable(true);
        criteriaItem53.setDisable(true);
        criteriaItem54.setDisable(true);
        criteriaItem55.setDisable(true);
        criteriaItem56.setDisable(true);
        criteriaItem57.setDisable(true);
        criteriaItem58.setDisable(true);
        criteriaItem59.setDisable(true);
        criteriaItem510.setDisable(true);
        criteriaItem61.setDisable(true);
        criteriaItem62.setDisable(true);
        criteriaItem63.setDisable(true);
        criteriaItem64.setDisable(true);
        criteriaItem65.setDisable(true);
        criteriaItem66.setDisable(true);
        criteriaItem67.setDisable(true);
        criteriaItem68.setDisable(true);
        criteriaItem69.setDisable(true);
        criteriaItem610.setDisable(true);
        criteriaItem71.setDisable(true);
        criteriaItem72.setDisable(true);
        criteriaItem73.setDisable(true);
        criteriaItem74.setDisable(true);
        criteriaItem75.setDisable(true);
        criteriaItem76.setDisable(true);
        criteriaItem77.setDisable(true);
        criteriaItem78.setDisable(true);
        criteriaItem79.setDisable(true);
        criteriaItem710.setDisable(true);
        criteriaItem81.setDisable(true);
        criteriaItem82.setDisable(true);
        criteriaItem83.setDisable(true);
        criteriaItem84.setDisable(true);
        criteriaItem85.setDisable(true);
        criteriaItem86.setDisable(true);
        criteriaItem87.setDisable(true);
        criteriaItem88.setDisable(true);
        criteriaItem89.setDisable(true);
        criteriaItem810.setDisable(true);
        criteriaItem91.setDisable(true);
        criteriaItem92.setDisable(true);
        criteriaItem93.setDisable(true);
        criteriaItem94.setDisable(true);
        criteriaItem95.setDisable(true);
        criteriaItem96.setDisable(true);
        criteriaItem97.setDisable(true);
        criteriaItem98.setDisable(true);
        criteriaItem99.setDisable(true);
        criteriaItem910.setDisable(true);
        criteriaItem101.setDisable(true);
        criteriaItem102.setDisable(true);
        criteriaItem103.setDisable(true);
        criteriaItem104.setDisable(true);
        criteriaItem105.setDisable(true);
        criteriaItem106.setDisable(true);
        criteriaItem107.setDisable(true);
        criteriaItem108.setDisable(true);
        criteriaItem109.setDisable(true);
        criteriaItem1010.setDisable(true);
        academicStatus.setDisable(true);
        academicGrade.setDisable(true);
        classNameLabel.setDisable(true);
        projectedGPA.setDisable(true);
        pointsEarned1.setDisable(true);
        pointsEarned2.setDisable(true);
        pointsEarned3.setDisable(true);
        pointsEarned4.setDisable(true);
        pointsEarned5.setDisable(true);
        pointsEarned6.setDisable(true);
        pointsEarned7.setDisable(true);
        pointsEarned8.setDisable(true);
        pointsEarned9.setDisable(true);
        pointsEarned10.setDisable(true);
        gpaHoursProgress.setDisable(true);
        qualityPointsProgress.setDisable(true);
        currentGradeProgress.setDisable(true);
    }
    @FXML
    private void clearAll() {
        academicYear.getSelectionModel().clearSelection();
        semester.getSelectionModel().clearSelection();
        classSelectionBox.getSelectionModel().clearSelection();
        qualityPoints.setText("");
        currentGPA.setText("");
        gpaHours.setText("");
        classNameBox.setText("");
        classCreditHours.setText("");
        creditHoursLabel.setText("");
        criteria1.setText("");
        criteria2.setText("");
        criteria3.setText("");
        criteria4.setText("");
        criteria5.setText("");
        criteria6.setText("");
        criteria7.setText("");
        criteria8.setText("");
        criteria9.setText("");
        criteria10.setText("");
        weight1.setText("");
        weight2.setText("");
        weight3.setText("");
        weight4.setText("");
        weight5.setText("");
        weight6.setText("");
        weight7.setText("");
        weight8.setText("");
        weight9.setText("");
        weight10.setText("");
        totalPoints1.setText("");
        totalPoints2.setText("");
        totalPoints3.setText("");
        totalPoints4.setText("");
        totalPoints5.setText("");
        totalPoints6.setText("");
        totalPoints7.setText("");
        totalPoints8.setText("");
        totalPoints9.setText("");
        totalPoints10.setText("");
        criteriaItem11.setText("");
        criteriaItem12.setText("");
        criteriaItem13.setText("");
        criteriaItem14.setText("");
        criteriaItem15.setText("");
        criteriaItem16.setText("");
        criteriaItem17.setText("");
        criteriaItem18.setText("");
        criteriaItem19.setText("");
        criteriaItem110.setText("");
        criteriaItem21.setText("");
        criteriaItem22.setText("");
        criteriaItem23.setText("");
        criteriaItem24.setText("");
        criteriaItem25.setText("");
        criteriaItem26.setText("");
        criteriaItem27.setText("");
        criteriaItem28.setText("");
        criteriaItem29.setText("");
        criteriaItem210.setText("");
        criteriaItem31.setText("");
        criteriaItem32.setText("");
        criteriaItem33.setText("");
        criteriaItem34.setText("");
        criteriaItem35.setText("");
        criteriaItem36.setText("");
        criteriaItem37.setText("");
        criteriaItem38.setText("");
        criteriaItem39.setText("");
        criteriaItem310.setText("");
        criteriaItem41.setText("");
        criteriaItem42.setText("");
        criteriaItem43.setText("");
        criteriaItem44.setText("");
        criteriaItem45.setText("");
        criteriaItem46.setText("");
        criteriaItem47.setText("");
        criteriaItem48.setText("");
        criteriaItem49.setText("");
        criteriaItem410.setText("");
        criteriaItem51.setText("");
        criteriaItem52.setText("");
        criteriaItem53.setText("");
        criteriaItem54.setText("");
        criteriaItem55.setText("");
        criteriaItem56.setText("");
        criteriaItem57.setText("");
        criteriaItem58.setText("");
        criteriaItem59.setText("");
        criteriaItem510.setText("");
        criteriaItem61.setText("");
        criteriaItem62.setText("");
        criteriaItem63.setText("");
        criteriaItem64.setText("");
        criteriaItem65.setText("");
        criteriaItem66.setText("");
        criteriaItem67.setText("");
        criteriaItem68.setText("");
        criteriaItem69.setText("");
        criteriaItem71.setText("");
        criteriaItem72.setText("");
        criteriaItem73.setText("");
        criteriaItem74.setText("");
        criteriaItem75.setText("");
        criteriaItem76.setText("");
        criteriaItem77.setText("");
        criteriaItem78.setText("");
        criteriaItem79.setText("");
        criteriaItem710.setText("");
        criteriaItem81.setText("");
        criteriaItem82.setText("");
        criteriaItem83.setText("");
        criteriaItem84.setText("");
        criteriaItem85.setText("");
        criteriaItem86.setText("");
        criteriaItem87.setText("");
        criteriaItem88.setText("");
        criteriaItem89.setText("");
        criteriaItem810.setText("");
        criteriaItem91.setText("");
        criteriaItem92.setText("");
        criteriaItem93.setText("");
        criteriaItem94.setText("");
        criteriaItem95.setText("");
        criteriaItem96.setText("");
        criteriaItem97.setText("");
        criteriaItem98.setText("");
        criteriaItem99.setText("");
        criteriaItem910.setText("");
        criteriaItem101.setText("");
        criteriaItem102.setText("");
        criteriaItem103.setText("");
        criteriaItem104.setText("");
        criteriaItem105.setText("");
        criteriaItem106.setText("");
        criteriaItem107.setText("");
        criteriaItem108.setText("");
        criteriaItem109.setText("");
        criteriaItem1010.setText("");
        academicStatus.setText("");
        academicGrade.setText("");
        classNameLabel.setText("");
        projectedGPA.setText("");
        pointsEarned1.setText("");
        pointsEarned2.setText("");
        pointsEarned3.setText("");
        pointsEarned4.setText("");
        pointsEarned5.setText("");
        pointsEarned6.setText("");
        pointsEarned7.setText("");
        pointsEarned8.setText("");
        pointsEarned9.setText("");
        pointsEarned10.setText("");
        gpaHoursProgress.setProgress(0);
        qualityPointsProgress.setProgress(0);
        currentGradeProgress.setProgress(0);
    }
    @FXML
    public void passReferenceToController(Controller1Main controller1Main){
        this.controller1Main = controller1Main;
         data = new DataProcess(controller1Main);
    }
    @FXML
    public void exitScreen() {
        if (!disabled){
            try {
                FXMLLoader loader = new FXMLLoader();
                Parent root = loader.load(getClass().getResource("Window3Exit.fxml").openStream());
                Controller3Exit controller3Exit = loader.getController();
                controller3Exit.passReferenceToController(controller1Main);
                Stage stage = new Stage();
                Scene scene = new Scene(root, 337, 132);
                stage.setScene(scene);
                stage.setTitle("Exit Window");
                stage.show();
            } catch (IOException e) {/*Test*/}
        }
        else
            Platform.exit();
    }

    @FXML
    public void openCheckScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("Window4OpenSaveCheck.fxml").openStream());
            Controller4OpenSaveCheck controller4OpenSaveCheck = loader.getController();
            controller4OpenSaveCheck.passReferenceToController(controller1Main);
            Stage stage = new Stage();
            Scene scene = new Scene(root, 337, 132);
            stage.setScene(scene);
            stage.setTitle("Open Window");
            stage.show();

        }catch(IOException e){
            //Nothing
        }
    }
    @FXML
    public void newCheckScreen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.load(getClass().getResource("Window5NewSaveCheck.fxml").openStream());
            Controller5NewSaveCheck controller5NewSaveCheck = loader.getController();
            controller5NewSaveCheck.passReferenceToController(controller1Main);
            Stage stage = new Stage();
            Scene scene = new Scene(root, 337, 132);
            stage.setScene(scene);
            stage.setTitle("New Window");
            stage.show();

        }catch(IOException e){
            //Nothing
        }
    }
    @FXML
    public boolean getSaveStatus(){return saveCheck;}
    @FXML
    private void startUp() {

        disableAll();
        openMenuItem.setDisable(false);
        //if openMenuItem.
        academicYear.setDisable(false);
        academicYear.setOnAction(a -> {
            newMenuItem.setDisable(false);
            semester.setDisable(false);
            academicYear.setOnAction(null);
            lastAcademicYearSelected = academicYear.getSelectionModel().getSelectedItem();
            setData();
            data.saveAcademicYearStartUp();
        });
        semester.setOnAction(b -> {
            gpaHours.setDisable(false);
            qualityPoints.setDisable(false);
            qualityPointsButton.setDisable(false);
            academicStatus.setDisable(false);
            academicGrade.setDisable(false);
            gpaHoursProgress.setDisable(false);
            qualityPointsProgress.setDisable(false);
            semester.setOnAction(null);
            lastSemesterSelected = semester.getSelectionModel().getSelectedItem();
            setData();
            data.saveSemesterStartUp();
        });
        qualityPoints.setOnKeyTyped(c3 -> { //To Cancel tab or first type
            qualityPoints.setOnKeyTyped(c2 -> { //To Cancel 2nd type
                qualityPoints.setOnKeyTyped(c1 -> {
                    currentGPA.setDisable(false);
                    setQualityPoints();
                    //Once done then open class
                    classNameBox.setDisable(false);
                    classNameLabel.setDisable(true);
                    classCreditHours.setDisable(false);
                    creditHoursLabel.setDisable(false);
                    addButton.setDisable(false);
                    qualityPoints.setOnAction(null);
                });
            });
        });
        addButton.setOnAction(d -> {
            if(!startAddButton) {
                if (!(classSelectionBox.getItems().toString().contains(classNameBox.getText()))) {
                    creditHoursLabel.setText(classCreditHours.getText());
                    classNameLabel.setText(classNameBox.getText());
                    classSelectionBox.getItems().add(classNameLabel.getText());
                    classSelectionBox.getSelectionModel().select(classNameLabel.getText());
                }
            }
            else {
                startAddButton = false;
                creditHoursLabel.setText(classCreditHours.getText());
                classNameLabel.setText(classNameBox.getText());
                classSelectionBox.getItems().add(classNameLabel.getText());
                classSelectionBox.setDisable(false);
                classSelectionBox.getSelectionModel().selectFirst();
                goButton.setDisable(false);
                lastClassBoxSelected = classSelectionBox.getSelectionModel().getSelectedItem();
                setData();
                data.saveClassBoxStartUp();
            }
        });
        goButton.setOnAction(e -> {
            //Once done then open Grid
            criteria1.setDisable(false);
            criteria2.setDisable(false);
            criteria3.setDisable(false);
            criteria4.setDisable(false);
            criteria5.setDisable(false);
            criteria6.setDisable(false);
            criteria7.setDisable(false);
            criteria8.setDisable(false);
            criteria9.setDisable(false);
            criteria10.setDisable(false);
            weight1.setDisable(false);
            weight2.setDisable(false);
            weight3.setDisable(false);
            weight4.setDisable(false);
            weight5.setDisable(false);
            weight6.setDisable(false);
            weight7.setDisable(false);
            weight8.setDisable(false);
            weight9.setDisable(false);
            weight10.setDisable(false);
            totalPoints1.setDisable(false);
            totalPoints2.setDisable(false);
            totalPoints3.setDisable(false);
            totalPoints4.setDisable(false);
            totalPoints5.setDisable(false);
            totalPoints6.setDisable(false);
            totalPoints7.setDisable(false);
            totalPoints8.setDisable(false);
            totalPoints9.setDisable(false);
            totalPoints10.setDisable(false);
            pointsEarned1.setDisable(false);
            pointsEarned2.setDisable(false);
            pointsEarned3.setDisable(false);
            pointsEarned4.setDisable(false);
            pointsEarned5.setDisable(false);
            pointsEarned6.setDisable(false);
            pointsEarned7.setDisable(false);
            pointsEarned8.setDisable(false);
            pointsEarned9.setDisable(false);
            pointsEarned10.setDisable(false);
            criteriaItem11.setDisable(false);
            criteriaItem12.setDisable(false);
            criteriaItem13.setDisable(false);
            criteriaItem14.setDisable(false);
            criteriaItem15.setDisable(false);
            criteriaItem16.setDisable(false);
            criteriaItem17.setDisable(false);
            criteriaItem18.setDisable(false);
            criteriaItem19.setDisable(false);
            criteriaItem110.setDisable(false);
            criteriaItem21.setDisable(false);
            criteriaItem22.setDisable(false);
            criteriaItem23.setDisable(false);
            criteriaItem24.setDisable(false);
            criteriaItem25.setDisable(false);
            criteriaItem26.setDisable(false);
            criteriaItem27.setDisable(false);
            criteriaItem28.setDisable(false);
            criteriaItem29.setDisable(false);
            criteriaItem210.setDisable(false);
            criteriaItem31.setDisable(false);
            criteriaItem32.setDisable(false);
            criteriaItem33.setDisable(false);
            criteriaItem34.setDisable(false);
            criteriaItem35.setDisable(false);
            criteriaItem36.setDisable(false);
            criteriaItem37.setDisable(false);
            criteriaItem38.setDisable(false);
            criteriaItem39.setDisable(false);
            criteriaItem310.setDisable(false);
            criteriaItem41.setDisable(false);
            criteriaItem42.setDisable(false);
            criteriaItem43.setDisable(false);
            criteriaItem44.setDisable(false);
            criteriaItem45.setDisable(false);
            criteriaItem46.setDisable(false);
            criteriaItem47.setDisable(false);
            criteriaItem48.setDisable(false);
            criteriaItem49.setDisable(false);
            criteriaItem410.setDisable(false);
            criteriaItem51.setDisable(false);
            criteriaItem52.setDisable(false);
            criteriaItem53.setDisable(false);
            criteriaItem54.setDisable(false);
            criteriaItem55.setDisable(false);
            criteriaItem56.setDisable(false);
            criteriaItem57.setDisable(false);
            criteriaItem58.setDisable(false);
            criteriaItem59.setDisable(false);
            criteriaItem510.setDisable(false);
            criteriaItem61.setDisable(false);
            criteriaItem62.setDisable(false);
            criteriaItem63.setDisable(false);
            criteriaItem64.setDisable(false);
            criteriaItem65.setDisable(false);
            criteriaItem66.setDisable(false);
            criteriaItem67.setDisable(false);
            criteriaItem68.setDisable(false);
            criteriaItem69.setDisable(false);
            criteriaItem610.setDisable(false);
            criteriaItem71.setDisable(false);
            criteriaItem72.setDisable(false);
            criteriaItem73.setDisable(false);
            criteriaItem74.setDisable(false);
            criteriaItem75.setDisable(false);
            criteriaItem76.setDisable(false);
            criteriaItem77.setDisable(false);
            criteriaItem78.setDisable(false);
            criteriaItem79.setDisable(false);
            criteriaItem710.setDisable(false);
            criteriaItem81.setDisable(false);
            criteriaItem82.setDisable(false);
            criteriaItem83.setDisable(false);
            criteriaItem84.setDisable(false);
            criteriaItem85.setDisable(false);
            criteriaItem86.setDisable(false);
            criteriaItem87.setDisable(false);
            criteriaItem88.setDisable(false);
            criteriaItem89.setDisable(false);
            criteriaItem810.setDisable(false);
            criteriaItem91.setDisable(false);
            criteriaItem92.setDisable(false);
            criteriaItem93.setDisable(false);
            criteriaItem94.setDisable(false);
            criteriaItem95.setDisable(false);
            criteriaItem96.setDisable(false);
            criteriaItem97.setDisable(false);
            criteriaItem98.setDisable(false);
            criteriaItem99.setDisable(false);
            criteriaItem910.setDisable(false);
            criteriaItem101.setDisable(false);
            criteriaItem102.setDisable(false);
            criteriaItem103.setDisable(false);
            criteriaItem104.setDisable(false);
            criteriaItem105.setDisable(false);
            criteriaItem106.setDisable(false);
            criteriaItem107.setDisable(false);
            criteriaItem108.setDisable(false);
            criteriaItem109.setDisable(false);
            criteriaItem1010.setDisable(false);
            currentGradeProgress.setDisable(false);
            projectedGPA.setDisable(false);
            newMenuItem.setDisable(false);
            openMenuItem.setDisable(false);
            saveMenuItem.setDisable(false);
            saveAsMenuItem.setDisable(false);
            disabled = false;
            goButton.setOnAction(null);
            saveMenuItem.setDisable(true);
        });
    }
    @FXML
    public void setSaveStatus(boolean saveCheck){
        this.saveCheck = saveCheck;
        if(saveCheck)
            saveMenuItem.setDisable(false);
        else
            saveMenuItem.setDisable(true);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(bearKat, 0, 0);

        startUp();

        //mediaPlayer.play();

        academicYear.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!startAcademicYear) {
                        String selected = academicYear.getSelectionModel().getSelectedItem();

                        if (!(lastAcademicYearSelected.matches(selected))) {
                            setData();
                            data.saveAcademicYear();
                            if(!data.checkSemester())
                                return;
                            data.getAcademicYearBox();
                            getData();
                        }lastAcademicYearSelected = selected;
                    }startAcademicYear = false;
                });

        semester.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!startSemester) {
                        String selected = semester.getSelectionModel().getSelectedItem();

                        if (!(lastSemesterSelected.matches(selected))) {
                            setData();
                            data.saveSemester();
                            classSelectionBox.getItems().clear();
                            data.getSemesterBox();
                            getData();

                            //TODO Check this
                            setGpaHours();
                            setCurrentGPA();

                        }lastSemesterSelected = selected;
                    }startSemester = false;
                });

        classSelectionBox.getSelectionModel().selectedItemProperty().addListener(
                (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!startClassSelectionBox) {
                        String selected = classSelectionBox.getSelectionModel().getSelectedItem();
                        if(classSelectionBox.getItems().isEmpty())
                            return;

                            if(lastClassBoxSelected == null) {
                                System.out.println("null from lastClassBox Selected");
                                return;
                            }
                            if (!(lastClassBoxSelected.matches(selected))) {
                                setData();
                                data.saveClassBox();
                                data.getClassBox();
                                getData();

                                //TODO Check This
                                calculateGrade();
                                setProjectedGPA();

                            } lastClassBoxSelected = selected;
                        } startClassSelectionBox = false;

                });

        gpaHours.setTextFormatter(new TextFormatter<Integer>(this::checkForInteger));
        qualityPoints.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        currentGPA.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        classCreditHours.setTextFormatter(new TextFormatter<Integer>(this::checkForInteger));
        weight1.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight2.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight3.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight4.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight5.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight6.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight7.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight8.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight9.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        weight10.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints1.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints2.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints3.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints4.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints5.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints6.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints7.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints8.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints9.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        totalPoints10.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem11.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem12.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem13.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem14.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem15.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem16.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem17.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem18.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem19.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem110.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem21.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem22.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem23.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem24.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem25.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem26.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem27.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem28.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem29.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem210.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem31.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem32.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem33.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem34.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem35.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem36.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem37.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem38.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem39.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem310.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem41.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem42.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem43.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem44.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem45.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem46.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem47.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem48.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem49.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem410.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem51.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem52.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem53.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem54.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem55.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem56.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem57.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem58.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem59.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem510.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem61.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem62.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem63.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem64.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem65.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem66.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem67.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem68.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem69.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem71.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem72.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem73.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem74.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem75.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem76.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem77.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem78.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem79.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem710.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem81.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem82.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem83.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem84.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem85.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem86.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem87.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem88.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem89.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem810.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem91.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem92.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem93.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem94.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem95.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem96.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem97.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem98.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem99.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem910.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem101.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem102.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem103.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem104.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem105.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem106.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem107.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem108.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem109.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
        criteriaItem1010.setTextFormatter(new TextFormatter<Double>(this::checkForDouble));
    }

    private TextFormatter.Change checkForInteger(TextFormatter.Change c) {
        String text = c.getControlNewText();
        if (text.matches("[0-9]+")) {
            if (Integer.parseInt(text) < 1000)
                return c;
            else
                return null;
        } else if (c.isDeleted())
            return c;
        else {
            return null;
        }
    }
    private TextFormatter.Change checkForDouble(TextFormatter.Change c) {
        try {
            String text = c.getControlNewText();
            if (text.matches("^[0-9]+\\.?[0-9]*$+")) {
                if (Double.parseDouble(text) < 1000.001)
                    return c;
                else
                    return null;
            } else if (c.isDeleted())
                return c;
            else {
                return null;
            }
        }
        catch(IllegalArgumentException e){return null;}
    }

    @FXML
    private void setData() {
        data.setAcademicYear(academicYear);
        data.setSemester(semester);
        data.setClassSelectionBox(classSelectionBox);
        data.setQualityPoints(qualityPoints);
        data.setCurrentGPA(currentGPA);
        data.setGpaHours(gpaHours);
        data.setClassNameBox(classNameBox);
        data.setClassCreditHours(classCreditHours);
        data.setCreditHoursLabel(creditHoursLabel);
        data.setCriteria1(criteria1);
        data.setCriteria2(criteria2);
        data.setCriteria3(criteria3);
        data.setCriteria4(criteria4);
        data.setCriteria5(criteria5);
        data.setCriteria6(criteria6);
        data.setCriteria7(criteria7);
        data.setCriteria8(criteria8);
        data.setCriteria9(criteria9);
        data.setCriteria10(criteria10);
        data.setWeight1(weight1);
        data.setWeight2(weight2);
        data.setWeight3(weight3);
        data.setWeight4(weight4);
        data.setWeight5(weight5);
        data.setWeight6(weight6);
        data.setWeight7(weight7);
        data.setWeight8(weight8);
        data.setWeight9(weight9);
        data.setWeight10(weight10);
        data.setTotalPoints1(totalPoints1);
        data.setTotalPoints2(totalPoints2);
        data.setTotalPoints3(totalPoints3);
        data.setTotalPoints4(totalPoints4);
        data.setTotalPoints5(totalPoints5);
        data.setTotalPoints6(totalPoints6);
        data.setTotalPoints7(totalPoints7);
        data.setTotalPoints8(totalPoints8);
        data.setTotalPoints9(totalPoints9);
        data.setTotalPoints10(totalPoints10);
        data.setPointsEarned10(pointsEarned10);
        data.setCriteriaItem11(criteriaItem11);
        data.setCriteriaItem12(criteriaItem12);
        data.setCriteriaItem13(criteriaItem13);
        data.setCriteriaItem14(criteriaItem14);
        data.setCriteriaItem15(criteriaItem15);
        data.setCriteriaItem16(criteriaItem16);
        data.setCriteriaItem17(criteriaItem17);
        data.setCriteriaItem18(criteriaItem18);
        data.setCriteriaItem19(criteriaItem19);
        data.setCriteriaItem110(criteriaItem110);
        data.setCriteriaItem21(criteriaItem21);
        data.setCriteriaItem22(criteriaItem22);
        data.setCriteriaItem23(criteriaItem23);
        data.setCriteriaItem24(criteriaItem24);
        data.setCriteriaItem25(criteriaItem25);
        data.setCriteriaItem26(criteriaItem26);
        data.setCriteriaItem27(criteriaItem27);
        data.setCriteriaItem28(criteriaItem28);
        data.setCriteriaItem29(criteriaItem29);
        data.setCriteriaItem210(criteriaItem210);
        data.setCriteriaItem31(criteriaItem31);
        data.setCriteriaItem32(criteriaItem32);
        data.setCriteriaItem33(criteriaItem33);
        data.setCriteriaItem34(criteriaItem34);
        data.setCriteriaItem35(criteriaItem35);
        data.setCriteriaItem36(criteriaItem36);
        data.setCriteriaItem37(criteriaItem37);
        data.setCriteriaItem38(criteriaItem38);
        data.setCriteriaItem39(criteriaItem39);
        data.setCriteriaItem310(criteriaItem310);
        data.setCriteriaItem41(criteriaItem41);
        data.setCriteriaItem42(criteriaItem42);
        data.setCriteriaItem43(criteriaItem43);
        data.setCriteriaItem44(criteriaItem44);
        data.setCriteriaItem45(criteriaItem45);
        data.setCriteriaItem46(criteriaItem46);
        data.setCriteriaItem47(criteriaItem47);
        data.setCriteriaItem48(criteriaItem48);
        data.setCriteriaItem49(criteriaItem49);
        data.setCriteriaItem410(criteriaItem410);
        data.setCriteriaItem51(criteriaItem51);
        data.setCriteriaItem52(criteriaItem52);
        data.setCriteriaItem53(criteriaItem53);
        data.setCriteriaItem54(criteriaItem54);
        data.setCriteriaItem55(criteriaItem55);
        data.setCriteriaItem56(criteriaItem56);
        data.setCriteriaItem57(criteriaItem57);
        data.setCriteriaItem58(criteriaItem58);
        data.setCriteriaItem59(criteriaItem59);
        data.setCriteriaItem510(criteriaItem510);
        data.setCriteriaItem61(criteriaItem61);
        data.setCriteriaItem62(criteriaItem62);
        data.setCriteriaItem63(criteriaItem63);
        data.setCriteriaItem64(criteriaItem64);
        data.setCriteriaItem65(criteriaItem65);
        data.setCriteriaItem66(criteriaItem66);
        data.setCriteriaItem67(criteriaItem67);
        data.setCriteriaItem68(criteriaItem68);
        data.setCriteriaItem69(criteriaItem69);
        data.setCriteriaItem610(criteriaItem610);
        data.setCriteriaItem71(criteriaItem71);
        data.setCriteriaItem72(criteriaItem72);
        data.setCriteriaItem73(criteriaItem73);
        data.setCriteriaItem74(criteriaItem74);
        data.setCriteriaItem75(criteriaItem75);
        data.setCriteriaItem76(criteriaItem76);
        data.setCriteriaItem77(criteriaItem77);
        data.setCriteriaItem78(criteriaItem78);
        data.setCriteriaItem79(criteriaItem79);
        data.setCriteriaItem710(criteriaItem710);
        data.setCriteriaItem81(criteriaItem81);
        data.setCriteriaItem82(criteriaItem82);
        data.setCriteriaItem83(criteriaItem83);
        data.setCriteriaItem84(criteriaItem84);
        data.setCriteriaItem85(criteriaItem85);
        data.setCriteriaItem86(criteriaItem86);
        data.setCriteriaItem87(criteriaItem87);
        data.setCriteriaItem88(criteriaItem88);
        data.setCriteriaItem89(criteriaItem89);
        data.setCriteriaItem810(criteriaItem810);
        data.setCriteriaItem91(criteriaItem91);
        data.setCriteriaItem92(criteriaItem92);
        data.setCriteriaItem93(criteriaItem93);
        data.setCriteriaItem94(criteriaItem94);
        data.setCriteriaItem95(criteriaItem95);
        data.setCriteriaItem96(criteriaItem96);
        data.setCriteriaItem97(criteriaItem97);
        data.setCriteriaItem98(criteriaItem98);
        data.setCriteriaItem99(criteriaItem99);
        data.setCriteriaItem910(criteriaItem910);
        data.setCriteriaItem101(criteriaItem101);
        data.setCriteriaItem102(criteriaItem102);
        data.setCriteriaItem103(criteriaItem103);
        data.setCriteriaItem104(criteriaItem104);
        data.setCriteriaItem105(criteriaItem105);
        data.setCriteriaItem106(criteriaItem106);
        data.setCriteriaItem107(criteriaItem107);
        data.setCriteriaItem108(criteriaItem108);
        data.setCriteriaItem109(criteriaItem109);
        data.setCriteriaItem1010(criteriaItem1010);
        data.setAcademicStatus(academicStatus);
        data.setAcademicGrade(academicGrade);
        data.setClassNameLabel(classNameLabel);
        data.setProjectedGPA(projectedGPA);
        data.setPointsEarned1(pointsEarned1);
        data.setPointsEarned2(pointsEarned2);
        data.setPointsEarned3(pointsEarned3);
        data.setPointsEarned4(pointsEarned4);
        data.setPointsEarned5(pointsEarned5);
        data.setPointsEarned6(pointsEarned6);
        data.setPointsEarned7(pointsEarned7);
        data.setPointsEarned8(pointsEarned8);
        data.setPointsEarned9(pointsEarned9);
        data.setGpaHoursProgress(gpaHoursProgress);
        data.setQualityPointsProgress(qualityPointsProgress);
        data.setCurrentGradeProgress(currentGradeProgress);
    }
    @FXML
    private void getData(){
        academicYear = data.getAcademicYear();
        semester = data.getSemester();
        classSelectionBox = data.getClassSelectionBox();
        qualityPoints = data.getQualityPoints();
        currentGPA = data.getCurrentGPA();
        gpaHours = data.getGpaHours();
        classNameBox = data.getClassNameBox();
        classCreditHours = data.getClassCreditHours();
        creditHoursLabel = data.getCreditHoursLabel();
        criteria1 = data.getCriteria1();
        criteria2 = data.getCriteria2();
        criteria3 = data.getCriteria3();
        criteria4 = data.getCriteria4();
        criteria5 = data.getCriteria5();
        criteria6 = data.getCriteria6();
        criteria7 = data.getCriteria7();
        criteria8 = data.getCriteria8();
        criteria9 = data.getCriteria9();
        criteria10 = data.getCriteria10();
        weight1 = data.getWeight1();
        weight2 = data.getWeight2();
        weight3 = data.getWeight3();
        weight4 = data.getWeight4();
        weight5 = data.getWeight5();
        weight6 = data.getWeight6();
        weight7 = data.getWeight7();
        weight8 = data.getWeight8();
        weight9 = data.getWeight9();
        weight10 = data.getWeight10();
        totalPoints1 = data.getTotalPoints1();
        totalPoints2 = data.getTotalPoints2();
        totalPoints3 = data.getTotalPoints3();
        totalPoints4 = data.getTotalPoints4();
        totalPoints5 = data.getTotalPoints5();
        totalPoints6 = data.getTotalPoints6();
        totalPoints7 = data.getTotalPoints7();
        totalPoints8 = data.getTotalPoints8();
        totalPoints9 = data.getTotalPoints9();
        totalPoints10 = data.getTotalPoints10();
        criteriaItem11 = data.getCriteriaItem11();
        criteriaItem12 = data.getCriteriaItem12();
        criteriaItem13 = data.getCriteriaItem13();
        criteriaItem14 = data.getCriteriaItem14();
        criteriaItem15 = data.getCriteriaItem15();
        criteriaItem16 = data.getCriteriaItem16();
        criteriaItem17 = data.getCriteriaItem17();
        criteriaItem18 = data.getCriteriaItem18();
        criteriaItem19 = data.getCriteriaItem19();
        criteriaItem110 = data.getCriteriaItem110();
        criteriaItem21 = data.getCriteriaItem21();
        criteriaItem22 = data.getCriteriaItem22();
        criteriaItem23 = data.getCriteriaItem23();
        criteriaItem24 = data.getCriteriaItem24();
        criteriaItem25 = data.getCriteriaItem25();
        criteriaItem26 = data.getCriteriaItem26();
        criteriaItem27 = data.getCriteriaItem27();
        criteriaItem28 = data.getCriteriaItem28();
        criteriaItem29 = data.getCriteriaItem29();
        criteriaItem210 = data.getCriteriaItem210();
        criteriaItem31 = data.getCriteriaItem31();
        criteriaItem32 = data.getCriteriaItem32();
        criteriaItem33 = data.getCriteriaItem33();
        criteriaItem34 = data.getCriteriaItem34();
        criteriaItem35 = data.getCriteriaItem35();
        criteriaItem36 = data.getCriteriaItem36();
        criteriaItem37 = data.getCriteriaItem37();
        criteriaItem38 = data.getCriteriaItem38();
        criteriaItem39 = data.getCriteriaItem39();
        criteriaItem310 = data.getCriteriaItem310();
        criteriaItem41 = data.getCriteriaItem41();
        criteriaItem42 = data.getCriteriaItem42();
        criteriaItem43 = data.getCriteriaItem43();
        criteriaItem44 = data.getCriteriaItem44();
        criteriaItem45 = data.getCriteriaItem45();
        criteriaItem46 = data.getCriteriaItem46();
        criteriaItem47 = data.getCriteriaItem47();
        criteriaItem48 = data.getCriteriaItem48();
        criteriaItem49 = data.getCriteriaItem49();
        criteriaItem410 = data.getCriteriaItem410();
        criteriaItem51 = data.getCriteriaItem51();
        criteriaItem52 = data.getCriteriaItem52();
        criteriaItem53 = data.getCriteriaItem53();
        criteriaItem54 = data.getCriteriaItem54();
        criteriaItem55 = data.getCriteriaItem55();
        criteriaItem56 = data.getCriteriaItem56();
        criteriaItem57 = data.getCriteriaItem57();
        criteriaItem58 = data.getCriteriaItem58();
        criteriaItem59 = data.getCriteriaItem59();
        criteriaItem510 = data.getCriteriaItem510();
        criteriaItem61 = data.getCriteriaItem61();
        criteriaItem62 = data.getCriteriaItem62();
        criteriaItem63 = data.getCriteriaItem63();
        criteriaItem64 = data.getCriteriaItem64();
        criteriaItem65 = data.getCriteriaItem65();
        criteriaItem66 = data.getCriteriaItem66();
        criteriaItem67 = data.getCriteriaItem67();
        criteriaItem68 = data.getCriteriaItem68();
        criteriaItem69 = data.getCriteriaItem69();
        criteriaItem610 = data.getCriteriaItem610();
        criteriaItem71 = data.getCriteriaItem71();
        criteriaItem72 = data.getCriteriaItem72();
        criteriaItem73 = data.getCriteriaItem73();
        criteriaItem74 = data.getCriteriaItem74();
        criteriaItem75 = data.getCriteriaItem75();
        criteriaItem76 = data.getCriteriaItem76();
        criteriaItem77 = data.getCriteriaItem77();
        criteriaItem78 = data.getCriteriaItem78();
        criteriaItem79 = data.getCriteriaItem79();
        criteriaItem710 = data.getCriteriaItem710();
        criteriaItem81 = data.getCriteriaItem81();
        criteriaItem82 = data.getCriteriaItem82();
        criteriaItem83 = data.getCriteriaItem83();
        criteriaItem84 = data.getCriteriaItem84();
        criteriaItem85 = data.getCriteriaItem85();
        criteriaItem86 = data.getCriteriaItem86();
        criteriaItem87 = data.getCriteriaItem87();
        criteriaItem88 = data.getCriteriaItem88();
        criteriaItem89 = data.getCriteriaItem89();
        criteriaItem810 = data.getCriteriaItem810();
        criteriaItem91 = data.getCriteriaItem91();
        criteriaItem92 = data.getCriteriaItem92();
        criteriaItem93 = data.getCriteriaItem93();
        criteriaItem94 = data.getCriteriaItem94();
        criteriaItem95 = data.getCriteriaItem95();
        criteriaItem96 = data.getCriteriaItem96();
        criteriaItem97 = data.getCriteriaItem97();
        criteriaItem98 = data.getCriteriaItem98();
        criteriaItem99 = data.getCriteriaItem99();
        criteriaItem910 = data.getCriteriaItem910();
        criteriaItem101 = data.getCriteriaItem101();
        criteriaItem102 = data.getCriteriaItem102();
        criteriaItem103 = data.getCriteriaItem103();
        criteriaItem104 = data.getCriteriaItem104();
        criteriaItem105 = data.getCriteriaItem105();
        criteriaItem106 = data.getCriteriaItem106();
        criteriaItem107 = data.getCriteriaItem107();
        criteriaItem108 = data.getCriteriaItem108();
        criteriaItem109 = data.getCriteriaItem109();
        criteriaItem1010 = data.getCriteriaItem1010();
        academicStatus = data.getAcademicStatus();
        academicGrade = data.getAcademicGrade();
        classNameLabel = data.getClassNameLabel();
        projectedGPA = data.getProjectedGPA();
        pointsEarned1 = data.getPointsEarned1();
        pointsEarned2 = data.getPointsEarned2();
        pointsEarned3 = data.getPointsEarned3();
        pointsEarned4 = data.getPointsEarned4();
        pointsEarned5 = data.getPointsEarned5();
        pointsEarned6 = data.getPointsEarned6();
        pointsEarned7 = data.getPointsEarned7();
        pointsEarned8 = data.getPointsEarned8();
        pointsEarned9 = data.getPointsEarned9();
        pointsEarned10 = data.getPointsEarned10();
        //gpaHoursProgress = data.getGpaHoursProgress();
       // qualityPointsProgress = data.getQualityPointsProgress();
        currentGradeProgress = data.getCurrentGradeProgress();
    }
}