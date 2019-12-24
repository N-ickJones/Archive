/*



             @FXML
             private Label gradedCriteriaLabel1, gradedCriteriaLabel2, gradedCriteriaLabel3, gradedCriteriaLabel4,
             gradedCriteriaLabel5, gradedCriteriaLabel6, gradedCriteriaLabel7, gradedCriteriaLabel8,
             gradedCriteriaLabel9, gradedCriteriaLabel10;

             private MenuItem openMenuItem, saveAsMenuItem, quitMenuItem;

             private Label academicYearLabel, semesterLabel, currentGPALabel, creditHoursCompletedLabel, academicStatusLabel, totalGradePoints, creditHoursAttempted;

             @FXML
             private Button addButton;

             @FXML
             private Label nameofCriteriaLabel, weightLabel, totalPointsLabel, pointsEarnedLabel, bonusLabel;

             @FXML
             private Label currentGrade;

             @FXML
             private TextArea infoBox;
             @FXML
             private Button changeScene;
             @FXML
             private Label label;


*/


/*      [Setting Actions Through Controller "in initialize"]

        try {


            academicYear.setOnAction(event -> setAcademicYear());
            semester.setOnAction(event -> setSemester());
            totalGradePoints.setOnAction(event -> setTotalGradePoints());
            totalGradePointsHelper.setOnAction(event -> setTotalGradePointsHelperScene());
            currentGPA.setOnAction(event -> setCurrentGPA());

            creditHoursCompleted.setOnMouseClicked(event -> creditHoursCompleted.setText(""));
            creditHoursCompleted.setOnAction(event -> setCreditHoursCompleted());
            creditHoursCompleted.setOnKeyPressed(event -> setCreditHoursCompleted());
            creditHoursCompleted.setOnMouseExited(event -> setCreditHoursCompleted());

        } catch (NullPointerException e) {//Test}

/*

/*     [Setup for Selection Boxes]

      //Out
        academicYear.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldYear, String newYear) {
                if (newYear != null) {
                    switch(newYear) {
                        case "2018": academicStatus.setText("2018"); break;
                        case "2019": academicStatus.setText("2019"); break;
                        case "2020": academicStatus.setText("2020"); break;
                    }
                }
            }
        });

        semester.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue<? extends String> selected, String oldSemester, String newSemester) {
                if (newSemester != null) {
                    switch(newSemester) {
                        case "Spring": academicStatus.setText("Spring"); break;
                        case "Fall": academicStatus.setText("Fall"); break;
                        case "SummerI": academicStatus.setText("SummerI"); break;
                        case "SummerII": academicStatus.setText("SummerII"); break;
                        case "Winter": academicStatus.setText("Winter"); break;
                    }
                }
            }
        });
*/


/*
        //changeScene2
        Stage change = (Stage) ((Node)event.getSource()).getScene().getWindow();
        //Stage stageTheLabelBelongs = (Stage) label.getScene().getWindow();
        Parent root2 = FXMLLoader.load(getClass().getResource("Window2.fxml"));
        change.setScene(new Scene(root2, 1024, 768));

*/

//stage2.setOnCloseRequest((WindowEvent event) -> event.consume());
/*
        academicYear
        semester
        classSelectionBox
        qualityPoints
        currentGPA
        gpaHours
        classNameBox
        classCreditHours
        criteria1
        criteria2
        criteria3
        criteria4
        criteria5
        criteria6
        criteria7
        criteria8
        criteria9
        criteria10
        weight1
        weight2
        weight3
        weight4
        weight5
        weight6
        weight7
        weight8
        weight9
        weight10
        totalPoints1
        totalPoints2
        totalPoints3
        totalPoints4
        totalPoints5
        totalPoints6
        totalPoints7
        totalPoints8
        totalPoints9
        totalPoints10
        criteriaItem11
        criteriaItem12
        criteriaItem13
        criteriaItem14
        criteriaItem15
        criteriaItem16
        criteriaItem17
        criteriaItem18
        criteriaItem19
        criteriaItem110
        criteriaItem21
        criteriaItem22
        criteriaItem23
        criteriaItem24
        criteriaItem25
        criteriaItem26
        criteriaItem27
        criteriaItem28
        criteriaItem29
        criteriaItem210
        criteriaItem31
        criteriaItem32
        criteriaItem33
        criteriaItem34
        criteriaItem35
        criteriaItem36
        criteriaItem37
        criteriaItem38
        criteriaItem39
        criteriaItem310
        criteriaItem41
        criteriaItem42
        criteriaItem43
        criteriaItem44
        criteriaItem45
        criteriaItem46
        criteriaItem47
        criteriaItem48
        criteriaItem49
        criteriaItem410
        criteriaItem51
        criteriaItem52
        criteriaItem53
        criteriaItem54
        criteriaItem55
        criteriaItem56
        criteriaItem57
        criteriaItem58
        criteriaItem59
        criteriaItem510
        criteriaItem61
        criteriaItem62
        criteriaItem63
        criteriaItem64
        criteriaItem65
        criteriaItem66
        criteriaItem67
        criteriaItem68
        criteriaItem69
        criteriaItem71
        criteriaItem72
        criteriaItem73
        criteriaItem74
        criteriaItem75
        criteriaItem76
        criteriaItem77
        criteriaItem78
        criteriaItem79
        criteriaItem710
        criteriaItem81
        criteriaItem82
        criteriaItem83
        criteriaItem84
        criteriaItem85
        criteriaItem86
        criteriaItem87
        criteriaItem88
        criteriaItem89
        criteriaItem810
        criteriaItem91
        criteriaItem92
        criteriaItem93
        criteriaItem94
        criteriaItem95
        criteriaItem96
        criteriaItem97
        criteriaItem98
        criteriaItem99
        criteriaItem910
        criteriaItem101
        criteriaItem102
        criteriaItem103
        criteriaItem104
        criteriaItem105
        criteriaItem106
        criteriaItem107
        criteriaItem108
        criteriaItem109
        criteriaItem1010
        academicStatus
        academicGrade
        classNameLabel
        projectedGPA
        pointsEarned1
        pointsEarned2
        pointsEarned3
        pointsEarned4
        pointsEarned5
        pointsEarned6
        pointsEarned7
        pointsEarned8
        pointsEarned9
        pointsEarned10
        currentGPAProgress
        creditHoursProgress
        currentGradeIndicator

            criteria1.setDisable(false);
            criteria2.setDisable(false);
            criteria3.setDisable(false);
            criteria4.setDisable(false);
            criteria5.setDisable(false);
            criteria6.setDisable(false);
            criteria7.setDisable(false);
            criteria8 .setDisable(false);
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
            currentGradeIndicator.setDisable(false);
            projectedGPA.setDisable(false);

            arrayProcessor[1] = academicYear.getSelectionModel().getSelectedItem());
            arrayProcessor[1] = semester.getSelectionModel().getSelectedItem());
            arrayProcessor[1] = qualityPoints.getText());
            arrayProcessor[1] = currentGPA.getText());
            arrayProcessor[1] = creditHours.getText());
            arrayProcessor[1] = academicStatus.getText());
            arrayProcessor[1] = academicGrade.getText());

            arrayProcessor[1] = classNameBox.getText());
            arrayProcessor[1] = classCreditHours.getText());
            arrayProcessor[1] = classSelectionBox.getSelectionModel().getSelectedItem());
            arrayProcessor[1] = currentGPAProgress.getProgress());
            arrayProcessor[1] = creditHoursProgress.getProgress());

            arrayProcessor[1] = criteria1.getText());
            criteria2.getText());
            criteria3.getText());
            criteria4.getText());
            criteria5.getText());
            criteria6.getText());
            criteria7.getText());
            criteria8.getText());
            criteria9.getText());
            criteria10.getText());

            weight1.getText());
            weight2.getText());
            weight3.getText());
            weight4.getText());
            weight5.getText());
            weight6.getText());
            weight7.getText());
            weight8.getText());
            weight9.getText());
            weight10.getText());

            totalPoints1.getText());
            totalPoints1.getText());
            totalPoints2.getText());
            totalPoints3.getText());
            totalPoints4.getText());
            totalPoints5.getText());
            totalPoints6.getText());
            totalPoints7.getText());
            totalPoints8.getText());
            totalPoints9.getText());
            totalPoints10.getText());

            pointsEarned1.getText());
            pointsEarned2.getText());
            pointsEarned3.getText());
            pointsEarned4.getText());
            pointsEarned5.getText());
            pointsEarned6.getText());
            pointsEarned7.getText());
            pointsEarned8.getText());
            pointsEarned9.getText());
            pointsEarned10.getText());

            criteriaItem11.getText());
            criteriaItem12.getText());
            criteriaItem13.getText());
            criteriaItem14.getText());
            criteriaItem15.getText());
            criteriaItem16.getText());
            criteriaItem17.getText());
            criteriaItem18.getText());
            criteriaItem19.getText());
            criteriaItem110.getText());

            criteriaItem21.getText());
            criteriaItem22.getText());
            criteriaItem23.getText());
            criteriaItem24.getText());
            criteriaItem25.getText());
            criteriaItem26.getText());
            criteriaItem27.getText());
            criteriaItem28.getText());
            criteriaItem29.getText());
            criteriaItem210.getText());

            criteriaItem31.getText());
            criteriaItem32.getText());
            criteriaItem33.getText());
            criteriaItem34.getText());
            criteriaItem35.getText());
            criteriaItem36.getText());
            criteriaItem37.getText());
            criteriaItem38.getText());
            criteriaItem39.getText());
            criteriaItem310.getText());

            criteriaItem41.getText());
            criteriaItem42.getText());
            criteriaItem43.getText());
            criteriaItem44.getText());
            criteriaItem45.getText());
            criteriaItem46.getText());
            criteriaItem47.getText());
            criteriaItem48.getText());
            criteriaItem49.getText());
            criteriaItem410.getText());

            criteriaItem51.getText());
            criteriaItem52.getText());
            criteriaItem53.getText());
            criteriaItem54.getText());
            criteriaItem55.getText());
            criteriaItem56.getText());
            criteriaItem57.getText());
            criteriaItem58.getText());
            criteriaItem59.getText());
            criteriaItem510.getText());

            criteriaItem61.getText());
            criteriaItem62.getText());
            criteriaItem63.getText());
            criteriaItem64.getText());
            criteriaItem65.getText());
            criteriaItem66.getText());
            criteriaItem67.getText());
            criteriaItem68.getText());
            criteriaItem69.getText());
            criteriaItem610.getText());

            criteriaItem71.getText());
            criteriaItem72.getText());
            criteriaItem73.getText());
            criteriaItem74.getText());
            criteriaItem75.getText());
            criteriaItem76.getText());
            criteriaItem77.getText());
            criteriaItem78.getText());
            criteriaItem79.getText());
            criteriaItem710.getText());

            criteriaItem81.getText());
            criteriaItem82.getText());
            criteriaItem83.getText());
            criteriaItem84.getText());
            criteriaItem85.getText());
            criteriaItem86.getText());
            criteriaItem87.getText());
            criteriaItem88.getText());
            criteriaItem89.getText());
            criteriaItem810.getText());

            criteriaItem91.getText());
            criteriaItem92.getText());
            criteriaItem93.getText());
            criteriaItem94.getText());
            criteriaItem95.getText());
            criteriaItem96.getText());
            criteriaItem97.getText());
            criteriaItem98.getText());
            criteriaItem99.getText());
            criteriaItem910.getText());

            criteriaItem101.getText());
            criteriaItem102.getText());
            criteriaItem103.getText());
            criteriaItem104.getText());
            criteriaItem105.getText());
            criteriaItem106.getText());
            criteriaItem107.getText());
            criteriaItem108.getText());
            criteriaItem109.getText());
            criteriaItem1010.getText());

            classNameLabel.getText());
            currentGradeIndicator.getProgress());
            projectedGPA.getText());


                qualityPoints.setText("");
                currentGPA.setText("");
                creditHours.setText("");
                academicStatus.setText("");
                academicGrade.setText("");
                classNameBox.setText("");
                classCreditHours.setText("");

                classSelectionBox.getSelectionModel().select(text);

                currentGPAProgress.setProgress(Double.parseDouble(""));
                creditHoursProgress.setProgress(Double.parseDouble(""));

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
                criteriaItem610.setText("");

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

                //Bottom of Page
                classNameLabel.setText("");
                currentGradeIndicator.setProgress(Double.parseDouble(""));
                projectedGPA.setText("");

                data.setData(academicYear, semester, classSelectionBox, qualityPoints, currentGPA,
                creditHours, classNameBox, classCreditHours,
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
                criteriaItem106, criteriaItem107, criteriaItem108, criteriaItem109, criteriaItem1010,
                academicStatus, academicGrade, classNameLabel, projectedGPA,
                pointsEarned1, pointsEarned2, pointsEarned3, pointsEarned4, pointsEarned5,
                pointsEarned6, pointsEarned7, pointsEarned8, pointsEarned9, pointsEarned10,
                currentGPAProgress, creditHoursProgress, currentGradeIndicator);

                public void setData(ComboBox<String> academicYear, ComboBox<String> semester, ComboBox<String> classSelectionBox,
                        TextField qualityPoints, TextField currentGPA, TextField creditHours,
                        TextField classNameBox, TextField classCreditHours, TextField criteria1, TextField criteria2,
                        TextField criteria3, TextField criteria4, TextField criteria5, TextField criteria6,
                        TextField criteria7, TextField criteria8, TextField criteria9, TextField criteria10,
                        TextField weight1, TextField weight2, TextField weight3, TextField weight4, TextField weight5,
                        TextField weight6, TextField weight7, TextField weight8, TextField weight9, TextField weight10,
                        TextField totalPoints1, TextField totalPoints2, TextField totalPoints3, TextField totalPoints4,
                        TextField totalPoints5, TextField totalPoints6, TextField totalPoints7, TextField totalPoints8,
                        TextField totalPoints9, TextField totalPoints10, TextField criteriaItem11,
                        TextField criteriaItem12, TextField criteriaItem13, TextField criteriaItem14,
                        TextField criteriaItem15, TextField criteriaItem16, TextField criteriaItem17,
                        TextField criteriaItem18, TextField criteriaItem19, TextField criteriaItem110,
                        TextField criteriaItem21, TextField criteriaItem22, TextField criteriaItem23,
                        TextField criteriaItem24, TextField criteriaItem25, TextField criteriaItem26,
                        TextField criteriaItem27, TextField criteriaItem28, TextField criteriaItem29,
                        TextField criteriaItem210, TextField criteriaItem31, TextField criteriaItem32,
                        TextField criteriaItem33, TextField criteriaItem34, TextField criteriaItem35,
                        TextField criteriaItem36, TextField criteriaItem37, TextField criteriaItem38,
                        TextField criteriaItem39, TextField criteriaItem310, TextField criteriaItem41,
                        TextField criteriaItem42, TextField criteriaItem43, TextField criteriaItem44,
                        TextField criteriaItem45, TextField criteriaItem46, TextField criteriaItem47,
                        TextField criteriaItem48, TextField criteriaItem49, TextField criteriaItem410,
                        TextField criteriaItem51, TextField criteriaItem52, TextField criteriaItem53,
                        TextField criteriaItem54, TextField criteriaItem55, TextField criteriaItem56,
                        TextField criteriaItem57, TextField criteriaItem58, TextField criteriaItem59,
                        TextField criteriaItem510, TextField criteriaItem61, TextField criteriaItem62,
                        TextField criteriaItem63, TextField criteriaItem64, TextField criteriaItem65,
                        TextField criteriaItem66, TextField criteriaItem67, TextField criteriaItem68,
                        TextField criteriaItem69, TextField criteriaItem610, TextField criteriaItem71,
                        TextField criteriaItem72, TextField criteriaItem73, TextField criteriaItem74,
                        TextField criteriaItem75, TextField criteriaItem76, TextField criteriaItem77,
                        TextField criteriaItem78, TextField criteriaItem79, TextField criteriaItem710,
                        TextField criteriaItem81, TextField criteriaItem82, TextField criteriaItem83,
                        TextField criteriaItem84, TextField criteriaItem85, TextField criteriaItem86,
                        TextField criteriaItem87, TextField criteriaItem88, TextField criteriaItem89,
                        TextField criteriaItem810, TextField criteriaItem91, TextField criteriaItem92,
                        TextField criteriaItem93, TextField criteriaItem94, TextField criteriaItem95,
                        TextField criteriaItem96, TextField criteriaItem97, TextField criteriaItem98,
                        TextField criteriaItem99, TextField criteriaItem910, TextField criteriaItem101,
                        TextField criteriaItem102, TextField criteriaItem103, TextField criteriaItem104,
                        TextField criteriaItem105, TextField criteriaItem106, TextField criteriaItem107,
                        TextField criteriaItem108, TextField criteriaItem109, TextField criteriaItem1010,
                        Label academicStatus, Label academicGrade, Label classNameLabel, Label projectedGPA,
                        Label pointsEarned1, Label pointsEarned2, Label pointsEarned3, Label pointsEarned4,
                        Label pointsEarned5, Label pointsEarned6, Label pointsEarned7, Label pointsEarned8,
                        Label pointsEarned9, Label pointsEarned10, ProgressIndicator currentGPAProgress,
                        ProgressIndicator creditHoursProgress, ProgressIndicator currentGradeIndicator) {

        this.academicYear = academicYear;
        this.semester = semester;
        this.classSelectionBox = classSelectionBox;
        this.qualityPoints = qualityPoints;
        this.currentGPA = currentGPA;
        this.creditHours = creditHours;
        this.classNameBox = classNameBox;
        this.classCreditHours = classCreditHours;
        this.criteria1 = criteria1;
        this.criteria2 = criteria2;
        this.criteria3 = criteria3;
        this.criteria4 = criteria4;
        this.criteria5 = criteria5;
        this.criteria6 = criteria6;
        this.criteria7 = criteria7;
        this.criteria8 = criteria8;
        this.criteria9 = criteria9;
        this.criteria10 = criteria10;
        this.weight1 = weight1;
        this.weight2 = weight2;
        this.weight3 = weight3;
        this.weight4 = weight4;
        this.weight5 = weight5;
        this.weight6 = weight6;
        this.weight7 = weight7;
        this.weight8 = weight8;
        this.weight9 = weight9;
        this.weight10 = weight10;
        this.totalPoints1 = totalPoints1;
        this.totalPoints2 = totalPoints2;
        this.totalPoints3 = totalPoints3;
        this.totalPoints4 = totalPoints4;
        this.totalPoints5 = totalPoints5;
        this.totalPoints6 = totalPoints6;
        this.totalPoints7 = totalPoints7;
        this.totalPoints8 = totalPoints8;
        this.totalPoints9 = totalPoints9;
        this.totalPoints10 = totalPoints10;
        this.criteriaItem11 = criteriaItem11;
        this.criteriaItem12 = criteriaItem12;
        this.criteriaItem13 = criteriaItem13;
        this.criteriaItem14 = criteriaItem14;
        this.criteriaItem15 = criteriaItem15;
        this.criteriaItem16 = criteriaItem16;
        this.criteriaItem17 = criteriaItem17;
        this.criteriaItem18 = criteriaItem18;
        this.criteriaItem19 = criteriaItem19;
        this.criteriaItem110 = criteriaItem110;
        this.criteriaItem21 = criteriaItem21;
        this.criteriaItem22 = criteriaItem22;
        this.criteriaItem23 = criteriaItem23;
        this.criteriaItem24 = criteriaItem24;
        this.criteriaItem25 = criteriaItem25;
        this.criteriaItem26 = criteriaItem26;
        this.criteriaItem27 = criteriaItem27;
        this.criteriaItem28 = criteriaItem28;
        this.criteriaItem29 = criteriaItem29;
        this.criteriaItem210 = criteriaItem210;
        this.criteriaItem31 = criteriaItem31;
        this.criteriaItem32 = criteriaItem32;
        this.criteriaItem33 = criteriaItem33;
        this.criteriaItem34 = criteriaItem34;
        this.criteriaItem35 = criteriaItem35;
        this.criteriaItem36 = criteriaItem36;
        this.criteriaItem37 = criteriaItem37;
        this.criteriaItem38 = criteriaItem38;
        this.criteriaItem39 = criteriaItem39;
        this.criteriaItem310 = criteriaItem310;
        this.criteriaItem41 = criteriaItem41;
        this.criteriaItem42 = criteriaItem42;
        this.criteriaItem43 = criteriaItem43;
        this.criteriaItem44 = criteriaItem44;
        this.criteriaItem45 = criteriaItem45;
        this.criteriaItem46 = criteriaItem46;
        this.criteriaItem47 = criteriaItem47;
        this.criteriaItem48 = criteriaItem48;
        this.criteriaItem49 = criteriaItem49;
        this.criteriaItem410 = criteriaItem410;
        this.criteriaItem51 = criteriaItem51;
        this.criteriaItem52 = criteriaItem52;
        this.criteriaItem53 = criteriaItem53;
        this.criteriaItem54 = criteriaItem54;
        this.criteriaItem55 = criteriaItem55;
        this.criteriaItem56 = criteriaItem56;
        this.criteriaItem57 = criteriaItem57;
        this.criteriaItem58 = criteriaItem58;
        this.criteriaItem59 = criteriaItem59;
        this.criteriaItem510 = criteriaItem510;
        this.criteriaItem61 = criteriaItem61;
        this.criteriaItem62 = criteriaItem62;
        this.criteriaItem63 = criteriaItem63;
        this.criteriaItem64 = criteriaItem64;
        this.criteriaItem65 = criteriaItem65;
        this.criteriaItem66 = criteriaItem66;
        this.criteriaItem67 = criteriaItem67;
        this.criteriaItem68 = criteriaItem68;
        this.criteriaItem69 = criteriaItem69;
        this.criteriaItem610 = criteriaItem610;
        this.criteriaItem71 = criteriaItem71;
        this.criteriaItem72 = criteriaItem72;
        this.criteriaItem73 = criteriaItem73;
        this.criteriaItem74 = criteriaItem74;
        this.criteriaItem75 = criteriaItem75;
        this.criteriaItem76 = criteriaItem76;
        this.criteriaItem77 = criteriaItem77;
        this.criteriaItem78 = criteriaItem78;
        this.criteriaItem79 = criteriaItem79;
        this.criteriaItem710 = criteriaItem710;
        this.criteriaItem81 = criteriaItem81;
        this.criteriaItem82 = criteriaItem82;
        this.criteriaItem83 = criteriaItem83;
        this.criteriaItem84 = criteriaItem84;
        this.criteriaItem85 = criteriaItem85;
        this.criteriaItem86 = criteriaItem86;
        this.criteriaItem87 = criteriaItem87;
        this.criteriaItem88 = criteriaItem88;
        this.criteriaItem89 = criteriaItem89;
        this.criteriaItem810 = criteriaItem810;
        this.criteriaItem91 = criteriaItem91;
        this.criteriaItem92 = criteriaItem92;
        this.criteriaItem93 = criteriaItem93;
        this.criteriaItem94 = criteriaItem94;
        this.criteriaItem95 = criteriaItem95;
        this.criteriaItem96 = criteriaItem96;
        this.criteriaItem97 = criteriaItem97;
        this.criteriaItem98 = criteriaItem98;
        this.criteriaItem99 = criteriaItem99;
        this.criteriaItem910 = criteriaItem910;
        this.criteriaItem101 = criteriaItem101;
        this.criteriaItem102 = criteriaItem102;
        this.criteriaItem103 = criteriaItem103;
        this.criteriaItem104 = criteriaItem104;
        this.criteriaItem105 = criteriaItem105;
        this.criteriaItem106 = criteriaItem106;
        this.criteriaItem107 = criteriaItem107;
        this.criteriaItem108 = criteriaItem108;
        this.criteriaItem109 = criteriaItem109;
        this.criteriaItem1010 = criteriaItem1010;
        this.academicStatus = academicStatus;
        this.academicGrade = academicGrade;
        this.classNameLabel = classNameLabel;
        this.projectedGPA = projectedGPA;
        this.pointsEarned1 = pointsEarned1;
        this.pointsEarned2 = pointsEarned2;
        this.pointsEarned3 = pointsEarned3;
        this.pointsEarned4 = pointsEarned4;
        this.pointsEarned5 = pointsEarned5;
        this.pointsEarned6 = pointsEarned6;
        this.pointsEarned7 = pointsEarned7;
        this.pointsEarned8 = pointsEarned8;
        this.pointsEarned9 = pointsEarned9;
        this.pointsEarned10 = pointsEarned10;
        this.currentGPAProgress = currentGPAProgress;
        this.creditHoursProgress = creditHoursProgress;
        this.currentGradeIndicator = currentGradeIndicator;


    /*
    //Start AcademicYear
    private boolean check2018 = false, check2019 = false, check2020 = false;
    private final int allItems = 156;
    private String[] arrayProcessor = new String[allItems];
    private String[] array2018 = new String[allItems];
    private String[] array2019 = new String[allItems];
    private String[] array2020 = new String[allItems];
    //End of academicYear
    //Start AcademicYear
    public void saveYear(String year){

        //arrayProcessor[0] = academicYear.getSelectionModel().getSelectedItem();
        //arrayProcessor[1] = semester.getSelectionModel().getSelectedItem();
        arrayProcessor[2] = qualityPoints.getText();
        arrayProcessor[3] = currentGPA.getText();
        arrayProcessor[4] = gpaHours.getText();
        arrayProcessor[5] = academicStatus.getText();
        arrayProcessor[6] = academicGrade.getText();

        arrayProcessor[7] = classNameBox.getText();
        arrayProcessor[8] = classCreditHours.getText();
        //arrayProcessor[9] = classSelectionBox.getSelectionModel().getSelectedItem();
        arrayProcessor[11] = String.valueOf(gpaHoursProgress.getProgress());
        arrayProcessor[10] = String.valueOf(qualityPointsProgress.getProgress());


        //Here Down needs to be multiplied

        arrayProcessor[12] = criteria1.getText();
        arrayProcessor[13] = criteria2.getText();
        arrayProcessor[14] = criteria3.getText();
        arrayProcessor[15] = criteria4.getText();
        arrayProcessor[16] = criteria5.getText();
        arrayProcessor[17] = criteria6.getText();
        arrayProcessor[18] = criteria7.getText();
        arrayProcessor[19] = criteria8.getText();
        arrayProcessor[20] = criteria9.getText();
        arrayProcessor[21] = criteria10.getText();

        arrayProcessor[22] = weight1.getText();
        arrayProcessor[23] = weight2.getText();
        arrayProcessor[24] = weight3.getText();
        arrayProcessor[25] = weight4.getText();
        arrayProcessor[26] = weight5.getText();
        arrayProcessor[27] = weight6.getText();
        arrayProcessor[28] = weight7.getText();
        arrayProcessor[29] = weight8.getText();
        arrayProcessor[30] = weight9.getText();
        arrayProcessor[31] = weight10.getText();

        arrayProcessor[32] = totalPoints1.getText();
        arrayProcessor[33] = totalPoints1.getText();
        arrayProcessor[34] = totalPoints2.getText();
        arrayProcessor[35] = totalPoints3.getText();
        arrayProcessor[36] = totalPoints4.getText();
        arrayProcessor[37] = totalPoints5.getText();
        arrayProcessor[38] = totalPoints6.getText();
        arrayProcessor[39] = totalPoints7.getText();
        arrayProcessor[40] = totalPoints8.getText();
        arrayProcessor[41] = totalPoints9.getText();
        arrayProcessor[42] = totalPoints10.getText();

        arrayProcessor[43] = pointsEarned1.getText();
        arrayProcessor[44] = pointsEarned2.getText();
        arrayProcessor[45] = pointsEarned3.getText();
        arrayProcessor[46] = pointsEarned4.getText();
        arrayProcessor[47] = pointsEarned5.getText();
        arrayProcessor[48] = pointsEarned6.getText();
        arrayProcessor[49] = pointsEarned7.getText();
        arrayProcessor[50] = pointsEarned8.getText();
        arrayProcessor[51] = pointsEarned9.getText();
        arrayProcessor[52] = pointsEarned10.getText();

        arrayProcessor[53] = criteriaItem11.getText();
        arrayProcessor[54] = criteriaItem12.getText();
        arrayProcessor[55] = criteriaItem13.getText();
        arrayProcessor[56] = criteriaItem14.getText();
        arrayProcessor[57] = criteriaItem15.getText();
        arrayProcessor[58] = criteriaItem16.getText();
        arrayProcessor[59] = criteriaItem17.getText();
        arrayProcessor[60] = criteriaItem18.getText();
        arrayProcessor[61] = criteriaItem19.getText();
        arrayProcessor[62] = criteriaItem110.getText();

        arrayProcessor[63] = criteriaItem21.getText();
        arrayProcessor[64] = criteriaItem22.getText();
        arrayProcessor[65] = criteriaItem23.getText();
        arrayProcessor[66] = criteriaItem24.getText();
        arrayProcessor[67] = criteriaItem25.getText();
        arrayProcessor[68] = criteriaItem26.getText();
        arrayProcessor[69] = criteriaItem27.getText();
        arrayProcessor[70] = criteriaItem28.getText();
        arrayProcessor[71] = criteriaItem29.getText();
        arrayProcessor[72] = criteriaItem210.getText();

        arrayProcessor[73] = criteriaItem31.getText();
        arrayProcessor[74] = criteriaItem32.getText();
        arrayProcessor[75] = criteriaItem33.getText();
        arrayProcessor[76] = criteriaItem34.getText();
        arrayProcessor[77] = criteriaItem35.getText();
        arrayProcessor[78] = criteriaItem36.getText();
        arrayProcessor[79] = criteriaItem37.getText();
        arrayProcessor[80] = criteriaItem38.getText();
        arrayProcessor[81] = criteriaItem39.getText();
        arrayProcessor[82] = criteriaItem310.getText();

        arrayProcessor[83] = criteriaItem41.getText();
        arrayProcessor[84] = criteriaItem42.getText();
        arrayProcessor[85] = criteriaItem43.getText();
        arrayProcessor[86] = criteriaItem44.getText();
        arrayProcessor[87] = criteriaItem45.getText();
        arrayProcessor[88] = criteriaItem46.getText();
        arrayProcessor[89] = criteriaItem47.getText();
        arrayProcessor[90] = criteriaItem48.getText();
        arrayProcessor[91] = criteriaItem49.getText();
        arrayProcessor[92] = criteriaItem410.getText();

        arrayProcessor[93] = criteriaItem51.getText();
        arrayProcessor[94] = criteriaItem52.getText();
        arrayProcessor[95] = criteriaItem53.getText();
        arrayProcessor[96] = criteriaItem54.getText();
        arrayProcessor[97] = criteriaItem55.getText();
        arrayProcessor[98] = criteriaItem56.getText();
        arrayProcessor[99] = criteriaItem57.getText();
        arrayProcessor[100] = criteriaItem58.getText();
        arrayProcessor[101] = criteriaItem59.getText();
        arrayProcessor[102] = criteriaItem510.getText();

        arrayProcessor[103] = criteriaItem61.getText();
        arrayProcessor[104] = criteriaItem62.getText();
        arrayProcessor[105] = criteriaItem63.getText();
        arrayProcessor[106] = criteriaItem64.getText();
        arrayProcessor[107] = criteriaItem65.getText();
        arrayProcessor[108] = criteriaItem66.getText();
        arrayProcessor[109] = criteriaItem67.getText();
        arrayProcessor[110] = criteriaItem68.getText();
        arrayProcessor[111] = criteriaItem69.getText();
        arrayProcessor[112] = criteriaItem610.getText();

        arrayProcessor[113] = criteriaItem71.getText();
        arrayProcessor[114] = criteriaItem72.getText();
        arrayProcessor[115] = criteriaItem73.getText();
        arrayProcessor[116] = criteriaItem74.getText();
        arrayProcessor[117] = criteriaItem75.getText();
        arrayProcessor[118] = criteriaItem76.getText();
        arrayProcessor[119] = criteriaItem77.getText();
        arrayProcessor[120] = criteriaItem78.getText();
        arrayProcessor[121] = criteriaItem79.getText();
        arrayProcessor[122] = criteriaItem710.getText();

        arrayProcessor[123] = criteriaItem81.getText();
        arrayProcessor[124] = criteriaItem82.getText();
        arrayProcessor[125] = criteriaItem83.getText();
        arrayProcessor[126] = criteriaItem84.getText();
        arrayProcessor[127] = criteriaItem85.getText();
        arrayProcessor[128] = criteriaItem86.getText();
        arrayProcessor[129] = criteriaItem87.getText();
        arrayProcessor[130] = criteriaItem88.getText();
        arrayProcessor[131] = criteriaItem89.getText();
        arrayProcessor[132] = criteriaItem810.getText();

        arrayProcessor[133] = criteriaItem91.getText();
        arrayProcessor[134] = criteriaItem92.getText();
        arrayProcessor[135] = criteriaItem93.getText();
        arrayProcessor[136] = criteriaItem94.getText();
        arrayProcessor[137] = criteriaItem95.getText();
        arrayProcessor[138] = criteriaItem96.getText();
        arrayProcessor[139] = criteriaItem97.getText();
        arrayProcessor[140] = criteriaItem98.getText();
        arrayProcessor[141] = criteriaItem99.getText();
        arrayProcessor[142] = criteriaItem910.getText();

        arrayProcessor[143] = criteriaItem101.getText();
        arrayProcessor[144] = criteriaItem102.getText();
        arrayProcessor[145] = criteriaItem103.getText();
        arrayProcessor[146] = criteriaItem104.getText();
        arrayProcessor[147] = criteriaItem105.getText();
        arrayProcessor[148] = criteriaItem106.getText();
        arrayProcessor[149] = criteriaItem107.getText();
        arrayProcessor[150] = criteriaItem108.getText();
        arrayProcessor[151] = criteriaItem109.getText();
        arrayProcessor[152] = criteriaItem1010.getText();

        arrayProcessor[153] = classNameLabel.getText();
        arrayProcessor[154] = String.valueOf(currentGradeProgress.getProgress());
        arrayProcessor[155] = String.valueOf(projectedGPA.getText());

        if(year.matches("2018")) {
            System.arraycopy(arrayProcessor, 0, array2018, 0, allItems);
            check2018 = true;
        }

        if(year.matches("2019")) {
            System.arraycopy(arrayProcessor, 0, array2019, 0, allItems);
            check2019 = true;
        }

        if(year.matches("2020")) {
            System.arraycopy(arrayProcessor, 0, array2020, 0, allItems);
            check2020 = true;
        }
    }
    public void getYear(String year){

        if(year.matches("2018") && check2018) {
            System.arraycopy(array2018, 0, arrayProcessor, 0, allItems);
            saveData();
        }
        else if(year.matches("2019") && check2019) {
            System.arraycopy(array2019, 0, arrayProcessor, 0, allItems);
            saveData();
        }
        else if(year.matches("2020") && check2020) {
            System.arraycopy(array2020, 0, arrayProcessor, 0, allItems);
            saveData();
        }
        else
            saveDataDefault();
    }
    private void saveDataDefault(){

        //academicYear.getSelectionModel().select(null);
        //semester.getSelectionModel().select(null);

        qualityPoints.setText("");
        currentGPA.setText("");
        gpaHours.setText("");
        academicStatus.setText("");
        academicGrade.setText("");
        classNameBox.setText("");
        classCreditHours.setText("");

        //classSelectionBox.getSelectionModel().select(null);

        gpaHoursProgress.setProgress(Double.parseDouble("0"));
        qualityPointsProgress.setProgress(Double.parseDouble("0"));


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
        criteriaItem610.setText("");

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

        //Bottom of Page
        classNameLabel.setText("");
        currentGradeProgress.setProgress(Double.parseDouble("0"));
        projectedGPA.setText("");
    }
    private void saveData(){

        //academicYear.getSelectionModel().select(arrayProcessor[0]);
        //semester.getSelectionModel().select(arrayProcessor[1]);

        qualityPoints.setText(arrayProcessor[2]);
        currentGPA.setText(arrayProcessor[3]);
        gpaHours.setText(arrayProcessor[4]);
        academicStatus.setText(arrayProcessor[5]);
        academicGrade.setText(arrayProcessor[6]);
        classNameBox.setText(arrayProcessor[7]);
        classCreditHours.setText(arrayProcessor[8]);

        //classSelectionBox.getSelectionModel().select(arrayProcessor[9]);
        gpaHoursProgress.setProgress(Double.parseDouble(arrayProcessor[11]));
        qualityPointsProgress.setProgress(Double.parseDouble(arrayProcessor[10]));


        criteria1.setText(arrayProcessor[12]);
        criteria2.setText(arrayProcessor[13]);
        criteria3.setText(arrayProcessor[14]);
        criteria4.setText(arrayProcessor[15]);
        criteria5.setText(arrayProcessor[16]);
        criteria6.setText(arrayProcessor[17]);
        criteria7.setText(arrayProcessor[18]);
        criteria8.setText(arrayProcessor[19]);
        criteria9.setText(arrayProcessor[20]);
        criteria10.setText(arrayProcessor[21]);

        weight1.setText(arrayProcessor[22]);
        weight2.setText(arrayProcessor[23]);
        weight3.setText(arrayProcessor[24]);
        weight4.setText(arrayProcessor[25]);
        weight5.setText(arrayProcessor[26]);
        weight6.setText(arrayProcessor[27]);
        weight7.setText(arrayProcessor[28]);
        weight8.setText(arrayProcessor[29]);
        weight9.setText(arrayProcessor[30]);
        weight10.setText(arrayProcessor[31]);

        totalPoints1.setText(arrayProcessor[32]);
        totalPoints1.setText(arrayProcessor[33]);
        totalPoints2.setText(arrayProcessor[34]);
        totalPoints3.setText(arrayProcessor[35]);
        totalPoints4.setText(arrayProcessor[36]);
        totalPoints5.setText(arrayProcessor[37]);
        totalPoints6.setText(arrayProcessor[38]);
        totalPoints7.setText(arrayProcessor[39]);
        totalPoints8.setText(arrayProcessor[40]);
        totalPoints9.setText(arrayProcessor[41]);
        totalPoints10.setText(arrayProcessor[42]);

        pointsEarned1.setText(arrayProcessor[43]);
        pointsEarned2.setText(arrayProcessor[44]);
        pointsEarned3.setText(arrayProcessor[45]);
        pointsEarned4.setText(arrayProcessor[46]);
        pointsEarned5.setText(arrayProcessor[47]);
        pointsEarned6.setText(arrayProcessor[48]);
        pointsEarned7.setText(arrayProcessor[49]);
        pointsEarned8.setText(arrayProcessor[50]);
        pointsEarned9.setText(arrayProcessor[51]);
        pointsEarned10.setText(arrayProcessor[52]);

        criteriaItem11.setText(arrayProcessor[53]);
        criteriaItem12.setText(arrayProcessor[54]);
        criteriaItem13.setText(arrayProcessor[55]);
        criteriaItem14.setText(arrayProcessor[56]);
        criteriaItem15.setText(arrayProcessor[57]);
        criteriaItem16.setText(arrayProcessor[58]);
        criteriaItem17.setText(arrayProcessor[59]);
        criteriaItem18.setText(arrayProcessor[60]);
        criteriaItem19.setText(arrayProcessor[61]);
        criteriaItem110.setText(arrayProcessor[62]);

        criteriaItem21.setText(arrayProcessor[63]);
        criteriaItem22.setText(arrayProcessor[64]);
        criteriaItem23.setText(arrayProcessor[65]);
        criteriaItem24.setText(arrayProcessor[66]);
        criteriaItem25.setText(arrayProcessor[67]);
        criteriaItem26.setText(arrayProcessor[68]);
        criteriaItem27.setText(arrayProcessor[69]);
        criteriaItem28.setText(arrayProcessor[70]);
        criteriaItem29.setText(arrayProcessor[71]);
        criteriaItem210.setText(arrayProcessor[72]);

        criteriaItem31.setText(arrayProcessor[73]);
        criteriaItem32.setText(arrayProcessor[74]);
        criteriaItem33.setText(arrayProcessor[75]);
        criteriaItem34.setText(arrayProcessor[76]);
        criteriaItem35.setText(arrayProcessor[77]);
        criteriaItem36.setText(arrayProcessor[78]);
        criteriaItem37.setText(arrayProcessor[79]);
        criteriaItem38.setText(arrayProcessor[80]);
        criteriaItem39.setText(arrayProcessor[81]);
        criteriaItem310.setText(arrayProcessor[82]);

        criteriaItem41.setText(arrayProcessor[83]);
        criteriaItem42.setText(arrayProcessor[84]);
        criteriaItem43.setText(arrayProcessor[85]);
        criteriaItem44.setText(arrayProcessor[86]);
        criteriaItem45.setText(arrayProcessor[87]);
        criteriaItem46.setText(arrayProcessor[88]);
        criteriaItem47.setText(arrayProcessor[89]);
        criteriaItem48.setText(arrayProcessor[90]);
        criteriaItem49.setText(arrayProcessor[91]);
        criteriaItem410.setText(arrayProcessor[92]);

        criteriaItem51.setText(arrayProcessor[93]);
        criteriaItem52.setText(arrayProcessor[94]);
        criteriaItem53.setText(arrayProcessor[95]);
        criteriaItem54.setText(arrayProcessor[96]);
        criteriaItem55.setText(arrayProcessor[97]);
        criteriaItem56.setText(arrayProcessor[98]);
        criteriaItem57.setText(arrayProcessor[99]);
        criteriaItem58.setText(arrayProcessor[100]);
        criteriaItem59.setText(arrayProcessor[101]);
        criteriaItem510.setText(arrayProcessor[102]);

        criteriaItem61.setText(arrayProcessor[103]);
        criteriaItem62.setText(arrayProcessor[104]);
        criteriaItem63.setText(arrayProcessor[105]);
        criteriaItem64.setText(arrayProcessor[106]);
        criteriaItem65.setText(arrayProcessor[107]);
        criteriaItem66.setText(arrayProcessor[108]);
        criteriaItem67.setText(arrayProcessor[109]);
        criteriaItem68.setText(arrayProcessor[110]);
        criteriaItem69.setText(arrayProcessor[111]);
        criteriaItem610.setText(arrayProcessor[112]);

        criteriaItem71.setText(arrayProcessor[113]);
        criteriaItem72.setText(arrayProcessor[114]);
        criteriaItem73.setText(arrayProcessor[115]);
        criteriaItem74.setText(arrayProcessor[116]);
        criteriaItem75.setText(arrayProcessor[117]);
        criteriaItem76.setText(arrayProcessor[118]);
        criteriaItem77.setText(arrayProcessor[119]);
        criteriaItem78.setText(arrayProcessor[120]);
        criteriaItem79.setText(arrayProcessor[121]);
        criteriaItem710.setText(arrayProcessor[122]);

        criteriaItem81.setText(arrayProcessor[123]);
        criteriaItem82.setText(arrayProcessor[124]);
        criteriaItem83.setText(arrayProcessor[125]);
        criteriaItem84.setText(arrayProcessor[126]);
        criteriaItem85.setText(arrayProcessor[127]);
        criteriaItem86.setText(arrayProcessor[128]);
        criteriaItem87.setText(arrayProcessor[129]);
        criteriaItem88.setText(arrayProcessor[130]);
        criteriaItem89.setText(arrayProcessor[131]);
        criteriaItem810.setText(arrayProcessor[132]);

        criteriaItem91.setText(arrayProcessor[133]);
        criteriaItem92.setText(arrayProcessor[134]);
        criteriaItem93.setText(arrayProcessor[135]);
        criteriaItem94.setText(arrayProcessor[136]);
        criteriaItem95.setText(arrayProcessor[137]);
        criteriaItem96.setText(arrayProcessor[138]);
        criteriaItem97.setText(arrayProcessor[139]);
        criteriaItem98.setText(arrayProcessor[140]);
        criteriaItem99.setText(arrayProcessor[141]);
        criteriaItem910.setText(arrayProcessor[142]);

        criteriaItem101.setText(arrayProcessor[143]);
        criteriaItem102.setText(arrayProcessor[144]);
        criteriaItem103.setText(arrayProcessor[145]);
        criteriaItem104.setText(arrayProcessor[146]);
        criteriaItem105.setText(arrayProcessor[147]);
        criteriaItem106.setText(arrayProcessor[148]);
        criteriaItem107.setText(arrayProcessor[149]);
        criteriaItem108.setText(arrayProcessor[150]);
        criteriaItem109.setText(arrayProcessor[151]);
        criteriaItem1010.setText(arrayProcessor[152]);

        //Bottom of Page
        classNameLabel.setText(arrayProcessor[153]);
        currentGradeProgress.setProgress(Double.parseDouble(arrayProcessor[154]));
        projectedGPA.setText(arrayProcessor[155]);

    }
    //End AcademicYear
*/
//Start of classBox
    /*
    private final int classBoxItems = 144;
    private int classBoxNumber = 10;

    private String[][] classBoxArray;
    private List<String> classBoxList = new ArrayList<>();
    private String previousClassBox;
    private Boolean classBoxFirstSave = true;
    private int classIndex;
    //End of classBox

    //Start of Semester
    private int totalClasses = 0;
    private String[][][] semesterArray;
    private List<String> semesterList = new ArrayList<>();
    private String previousSemester;
    private Boolean semesterFirstSave = true;
    private int semesterIndex,sem0, sem1, sem2, sem3, sem4;
    //End of Semester

    //Start Semester
    private void startSemester(){
        System.out.println("start semester");
        final int semesterItems = 5;
        semesterArray = new String[classBoxItems + 10][classBoxNumber][semesterItems];}
    public void saveSemesterBox(){

        //Start semester Array
        if(semesterFirstSave)
            startSemester();

        String current = semester.getSelectionModel().getSelectedItem();
        boolean check = true;

        for (String s : semesterList) {
            if (s.contains(current))
                check = false;
        }

        if(check) {
            semesterList.add(current);
            semesterIndex = semesterList.indexOf(current);
            saveSemester(semesterIndex);
        }

        if(!semesterFirstSave) {
            semesterIndex = semesterList.indexOf(previousSemester);
            saveSemester(semesterIndex);
        }
        else
            semesterFirstSave = false;

        previousSemester = current;

        //to be able to load last classIndex
        switch(semesterList.indexOf(current)){
            case 0: sem0  = classIndex;
            case 1: sem1  = classIndex;
            case 2: sem2  = classIndex;
            case 3: sem3  = classIndex;
            case 4: sem4  = classIndex;
        }


    }
    public void getSemesterBox(String selected) {

        boolean check = true;


        for (String s : semesterList) {
            if (s.contains(selected))
                check = false;
        }

        switch(semesterList.indexOf(selected)){
            case 0: classIndex = sem0;
            case 1: classIndex = sem1;
            case 2: classIndex = sem2;
            case 3: classIndex = sem3;
            case 4: classIndex = sem4;
        }

        if (!check) {

            semesterIndex = semesterList.indexOf(selected);

            criteria1.setText(semesterArray[0][classIndex][semesterIndex]);
            criteria2.setText(semesterArray[1][classIndex][semesterIndex]);
            criteria3.setText(semesterArray[2][classIndex][semesterIndex]);
            criteria4.setText(semesterArray[3][classIndex][semesterIndex]);
            criteria5.setText(semesterArray[4][classIndex][semesterIndex]);
            criteria6.setText(semesterArray[5][classIndex][semesterIndex]);
            criteria7.setText(semesterArray[6][classIndex][semesterIndex]);
            criteria8.setText(semesterArray[7][classIndex][semesterIndex]);
            criteria9.setText(semesterArray[8][classIndex][semesterIndex]);
            criteria10.setText(semesterArray[9][classIndex][semesterIndex]);
            weight1.setText(semesterArray[10][classIndex][semesterIndex]);
            weight2.setText(semesterArray[11][classIndex][semesterIndex]);
            weight3.setText(semesterArray[12][classIndex][semesterIndex]);
            weight4.setText(semesterArray[13][classIndex][semesterIndex]);
            weight5.setText(semesterArray[14][classIndex][semesterIndex]);
            weight6.setText(semesterArray[15][classIndex][semesterIndex]);
            weight7.setText(semesterArray[16][classIndex][semesterIndex]);
            weight8.setText(semesterArray[17][classIndex][semesterIndex]);
            weight9.setText(semesterArray[18][classIndex][semesterIndex]);
            weight10.setText(semesterArray[19][classIndex][semesterIndex]);
            totalPoints1.setText(semesterArray[20][classIndex][semesterIndex]);
            totalPoints1.setText(semesterArray[21][classIndex][semesterIndex]);
            totalPoints2.setText(semesterArray[22][classIndex][semesterIndex]);
            totalPoints3.setText(semesterArray[23][classIndex][semesterIndex]);
            totalPoints4.setText(semesterArray[24][classIndex][semesterIndex]);
            totalPoints5.setText(semesterArray[25][classIndex][semesterIndex]);
            totalPoints6.setText(semesterArray[26][classIndex][semesterIndex]);
            totalPoints7.setText(semesterArray[27][classIndex][semesterIndex]);
            totalPoints8.setText(semesterArray[28][classIndex][semesterIndex]);
            totalPoints9.setText(semesterArray[29][classIndex][semesterIndex]);
            totalPoints10.setText(semesterArray[30][classIndex][semesterIndex]);
            pointsEarned1.setText(semesterArray[31][classIndex][semesterIndex]);
            pointsEarned2.setText(semesterArray[32][classIndex][semesterIndex]);
            pointsEarned3.setText(semesterArray[33][classIndex][semesterIndex]);
            pointsEarned4.setText(semesterArray[34][classIndex][semesterIndex]);
            pointsEarned5.setText(semesterArray[35][classIndex][semesterIndex]);
            pointsEarned6.setText(semesterArray[36][classIndex][semesterIndex]);
            pointsEarned7.setText(semesterArray[37][classIndex][semesterIndex]);
            pointsEarned8.setText(semesterArray[38][classIndex][semesterIndex]);
            pointsEarned9.setText(semesterArray[39][classIndex][semesterIndex]);
            pointsEarned10.setText(semesterArray[40][classIndex][semesterIndex]);
            criteriaItem11.setText(semesterArray[41][classIndex][semesterIndex]);
            criteriaItem12.setText(semesterArray[42][classIndex][semesterIndex]);
            criteriaItem13.setText(semesterArray[43][classIndex][semesterIndex]);
            criteriaItem14.setText(semesterArray[44][classIndex][semesterIndex]);
            criteriaItem15.setText(semesterArray[45][classIndex][semesterIndex]);
            criteriaItem16.setText(semesterArray[46][classIndex][semesterIndex]);
            criteriaItem17.setText(semesterArray[47][classIndex][semesterIndex]);
            criteriaItem18.setText(semesterArray[48][classIndex][semesterIndex]);
            criteriaItem19.setText(semesterArray[49][classIndex][semesterIndex]);
            criteriaItem110.setText(semesterArray[50][classIndex][semesterIndex]);
            criteriaItem21.setText(semesterArray[51][classIndex][semesterIndex]);
            criteriaItem22.setText(semesterArray[52][classIndex][semesterIndex]);
            criteriaItem23.setText(semesterArray[53][classIndex][semesterIndex]);
            criteriaItem24.setText(semesterArray[54][classIndex][semesterIndex]);
            criteriaItem25.setText(semesterArray[55][classIndex][semesterIndex]);
            criteriaItem26.setText(semesterArray[56][classIndex][semesterIndex]);
            criteriaItem27.setText(semesterArray[57][classIndex][semesterIndex]);
            criteriaItem28.setText(semesterArray[58][classIndex][semesterIndex]);
            criteriaItem29.setText(semesterArray[59][classIndex][semesterIndex]);
            criteriaItem210.setText(semesterArray[60][classIndex][semesterIndex]);
            criteriaItem31.setText(semesterArray[61][classIndex][semesterIndex]);
            criteriaItem32.setText(semesterArray[62][classIndex][semesterIndex]);
            criteriaItem33.setText(semesterArray[63][classIndex][semesterIndex]);
            criteriaItem34.setText(semesterArray[64][classIndex][semesterIndex]);
            criteriaItem35.setText(semesterArray[65][classIndex][semesterIndex]);
            criteriaItem36.setText(semesterArray[66][classIndex][semesterIndex]);
            criteriaItem37.setText(semesterArray[67][classIndex][semesterIndex]);
            criteriaItem38.setText(semesterArray[68][classIndex][semesterIndex]);
            criteriaItem39.setText(semesterArray[69][classIndex][semesterIndex]);
            criteriaItem310.setText(semesterArray[70][classIndex][semesterIndex]);
            criteriaItem41.setText(semesterArray[71][classIndex][semesterIndex]);
            criteriaItem42.setText(semesterArray[72][classIndex][semesterIndex]);
            criteriaItem43.setText(semesterArray[73][classIndex][semesterIndex]);
            criteriaItem44.setText(semesterArray[74][classIndex][semesterIndex]);
            criteriaItem45.setText(semesterArray[75][classIndex][semesterIndex]);
            criteriaItem46.setText(semesterArray[76][classIndex][semesterIndex]);
            criteriaItem47.setText(semesterArray[77][classIndex][semesterIndex]);
            criteriaItem48.setText(semesterArray[78][classIndex][semesterIndex]);
            criteriaItem49.setText(semesterArray[79][classIndex][semesterIndex]);
            criteriaItem410.setText(semesterArray[80][classIndex][semesterIndex]);
            criteriaItem51.setText(semesterArray[81][classIndex][semesterIndex]);
            criteriaItem52.setText(semesterArray[82][classIndex][semesterIndex]);
            criteriaItem53.setText(semesterArray[83][classIndex][semesterIndex]);
            criteriaItem54.setText(semesterArray[84][classIndex][semesterIndex]);
            criteriaItem55.setText(semesterArray[85][classIndex][semesterIndex]);
            criteriaItem56.setText(semesterArray[86][classIndex][semesterIndex]);
            criteriaItem57.setText(semesterArray[87][classIndex][semesterIndex]);
            criteriaItem58.setText(semesterArray[88][classIndex][semesterIndex]);
            criteriaItem59.setText(semesterArray[89][classIndex][semesterIndex]);
            criteriaItem510.setText(semesterArray[90][classIndex][semesterIndex]);
            criteriaItem61.setText(semesterArray[91][classIndex][semesterIndex]);
            criteriaItem62.setText(semesterArray[92][classIndex][semesterIndex]);
            criteriaItem63.setText(semesterArray[93][classIndex][semesterIndex]);
            criteriaItem64.setText(semesterArray[94][classIndex][semesterIndex]);
            criteriaItem65.setText(semesterArray[95][classIndex][semesterIndex]);
            criteriaItem66.setText(semesterArray[96][classIndex][semesterIndex]);
            criteriaItem67.setText(semesterArray[97][classIndex][semesterIndex]);
            criteriaItem68.setText(semesterArray[98][classIndex][semesterIndex]);
            criteriaItem69.setText(semesterArray[99][classIndex][semesterIndex]);
            criteriaItem610.setText(semesterArray[100][classIndex][semesterIndex]);
            criteriaItem71.setText(semesterArray[101][classIndex][semesterIndex]);
            criteriaItem72.setText(semesterArray[102][classIndex][semesterIndex]);
            criteriaItem73.setText(semesterArray[103][classIndex][semesterIndex]);
            criteriaItem74.setText(semesterArray[104][classIndex][semesterIndex]);
            criteriaItem75.setText(semesterArray[105][classIndex][semesterIndex]);
            criteriaItem76.setText(semesterArray[106][classIndex][semesterIndex]);
            criteriaItem77.setText(semesterArray[107][classIndex][semesterIndex]);
            criteriaItem78.setText(semesterArray[108][classIndex][semesterIndex]);
            criteriaItem79.setText(semesterArray[109][classIndex][semesterIndex]);
            criteriaItem710.setText(semesterArray[110][classIndex][semesterIndex]);
            criteriaItem81.setText(semesterArray[111][classIndex][semesterIndex]);
            criteriaItem82.setText(semesterArray[112][classIndex][semesterIndex]);
            criteriaItem83.setText(semesterArray[113][classIndex][semesterIndex]);
            criteriaItem84.setText(semesterArray[114][classIndex][semesterIndex]);
            criteriaItem85.setText(semesterArray[115][classIndex][semesterIndex]);
            criteriaItem86.setText(semesterArray[116][classIndex][semesterIndex]);
            criteriaItem87.setText(semesterArray[117][classIndex][semesterIndex]);
            criteriaItem88.setText(semesterArray[118][classIndex][semesterIndex]);
            criteriaItem89.setText(semesterArray[119][classIndex][semesterIndex]);
            criteriaItem810.setText(semesterArray[120][classIndex][semesterIndex]);
            criteriaItem91.setText(semesterArray[121][classIndex][semesterIndex]);
            criteriaItem92.setText(semesterArray[122][classIndex][semesterIndex]);
            criteriaItem93.setText(semesterArray[123][classIndex][semesterIndex]);
            criteriaItem94.setText(semesterArray[124][classIndex][semesterIndex]);
            criteriaItem95.setText(semesterArray[125][classIndex][semesterIndex]);
            criteriaItem96.setText(semesterArray[126][classIndex][semesterIndex]);
            criteriaItem97.setText(semesterArray[127][classIndex][semesterIndex]);
            criteriaItem98.setText(semesterArray[128][classIndex][semesterIndex]);
            criteriaItem99.setText(semesterArray[129][classIndex][semesterIndex]);
            criteriaItem910.setText(semesterArray[130][classIndex][semesterIndex]);
            criteriaItem101.setText(semesterArray[131][classIndex][semesterIndex]);
            criteriaItem102.setText(semesterArray[132][classIndex][semesterIndex]);
            criteriaItem103.setText(semesterArray[133][classIndex][semesterIndex]);
            criteriaItem104.setText(semesterArray[134][classIndex][semesterIndex]);
            criteriaItem105.setText(semesterArray[135][classIndex][semesterIndex]);
            criteriaItem106.setText(semesterArray[136][classIndex][semesterIndex]);
            criteriaItem107.setText(semesterArray[137][classIndex][semesterIndex]);
            criteriaItem108.setText(semesterArray[138][classIndex][semesterIndex]);
            criteriaItem109.setText(semesterArray[139][classIndex][semesterIndex]);
            criteriaItem1010.setText(semesterArray[140][classIndex][semesterIndex]);
            classNameLabel.setText(semesterArray[141][classIndex][semesterIndex]);
//            currentGradeProgress.setProgress(Double.parseDouble(semesterArray[142][classIndex][semesterIndex]));
            projectedGPA.setText(semesterArray[143][classIndex][semesterIndex]);



            for(int i = 0; i == semesterIndex; i++)
                for(int j = 0; j < totalClasses; j++)
                    for(int k = 0; k < classBoxItems; k++)
                        classBoxArray[k][j] = semesterArray[k][j][i];
        }
    }
    private void saveSemester(int semesterIndex){
        for(int i = 0; i == semesterIndex; i++)
            for(int j = 0; j < totalClasses; j++)
                for(int k = 0; k < classBoxItems; k++)
                    semesterArray[k][j][i] = classBoxArray[k][j];

        semesterArray[144][0][semesterIndex] = qualityPoints.getText();
        semesterArray[145][0][semesterIndex] = currentGPA.getText();
        semesterArray[146][0][semesterIndex] = gpaHours.getText();
        semesterArray[147][0][semesterIndex] = academicStatus.getText();
        semesterArray[148][0][semesterIndex] = academicGrade.getText();
        semesterArray[149][0][semesterIndex] = classNameBox.getText();
        semesterArray[150][0][semesterIndex] = classCreditHours.getText();
        semesterArray[151][0][semesterIndex] = classSelectionBox.getSelectionModel().getSelectedItem();
        semesterArray[152][0][semesterIndex] = String.valueOf(gpaHoursProgress.getProgress());
        semesterArray[153][0][semesterIndex] = String.valueOf(qualityPointsProgress.getProgress());

        //Save all arrays
    }
    //End of Semester

//TODO Add a delete feature for Classes
    //Start of classBox
    private void startClassBox(){
        classBoxArray = new String[classBoxItems][classBoxNumber];}
    public void saveClassBox() {

        saveSemesterBox();

        if(classBoxFirstSave)
            startClassBox();

        String current = classSelectionBox.getSelectionModel().getSelectedItem();
        boolean check = true;
        int classIndex = 0;

        for (String s : classBoxList) {
            if (s.contains(current))
                check = false;
        }

        if(check) {
            classBoxList.add(current);
            classIndex = classBoxList.indexOf(current);
            saveNewBox(classIndex);
        }

        if(!classBoxFirstSave) {
            classIndex = classBoxList.indexOf(previousClassBox);
            saveCurrentBox(classIndex);
        }
        else
            classBoxFirstSave = false;

        previousClassBox = current;
        if(classIndex > totalClasses)
            totalClasses = classIndex;

        if((classBoxList.size() >= 10) && classBoxList.size() % 5 == 0)
            classBoxExpand(classBoxList.size());
    }
    private void saveNewBox(int classIndex){
        for(int i = 0; i < classBoxItems; i++)
            classBoxArray[i][classIndex] = "";

        classBoxArray[31][classIndex] = "0.0";
        classBoxArray[32][classIndex] = "0.0";
        classBoxArray[33][classIndex] = "0.0";
        classBoxArray[34][classIndex] = "0.0";
        classBoxArray[35][classIndex] = "0.0";
        classBoxArray[36][classIndex] = "0.0";
        classBoxArray[37][classIndex] = "0.0";
        classBoxArray[38][classIndex] = "0.0";
        classBoxArray[39][classIndex] = "0.0";
        classBoxArray[40][classIndex] = "0.0";
        classBoxArray[142][classIndex] = "0";

    }
    private void saveCurrentBox(int classIndex){
        classBoxArray[0][classIndex] = criteria1.getText();
        classBoxArray[1][classIndex] = criteria2.getText();
        classBoxArray[2][classIndex] = criteria3.getText();
        classBoxArray[3][classIndex] = criteria4.getText();
        classBoxArray[4][classIndex] = criteria5.getText();
        classBoxArray[5][classIndex] = criteria6.getText();
        classBoxArray[6][classIndex] = criteria7.getText();
        classBoxArray[7][classIndex] = criteria8.getText();
        classBoxArray[8][classIndex] = criteria9.getText();
        classBoxArray[9][classIndex] = criteria10.getText();
        classBoxArray[10][classIndex] = weight1.getText();
        classBoxArray[11][classIndex] = weight2.getText();
        classBoxArray[12][classIndex] = weight3.getText();
        classBoxArray[13][classIndex] = weight4.getText();
        classBoxArray[14][classIndex] = weight5.getText();
        classBoxArray[15][classIndex] = weight6.getText();
        classBoxArray[16][classIndex] = weight7.getText();
        classBoxArray[17][classIndex] = weight8.getText();
        classBoxArray[18][classIndex] = weight9.getText();
        classBoxArray[19][classIndex] = weight10.getText();
        classBoxArray[20][classIndex] = totalPoints1.getText();
        classBoxArray[21][classIndex] = totalPoints1.getText();
        classBoxArray[22][classIndex] = totalPoints2.getText();
        classBoxArray[23][classIndex] = totalPoints3.getText();
        classBoxArray[24][classIndex] = totalPoints4.getText();
        classBoxArray[25][classIndex] = totalPoints5.getText();
        classBoxArray[26][classIndex] = totalPoints6.getText();
        classBoxArray[27][classIndex] = totalPoints7.getText();
        classBoxArray[28][classIndex] = totalPoints8.getText();
        classBoxArray[29][classIndex] = totalPoints9.getText();
        classBoxArray[30][classIndex] = totalPoints10.getText();
        classBoxArray[31][classIndex] = pointsEarned1.getText();
        classBoxArray[32][classIndex] = pointsEarned2.getText();
        classBoxArray[33][classIndex] = pointsEarned3.getText();
        classBoxArray[34][classIndex] = pointsEarned4.getText();
        classBoxArray[35][classIndex] = pointsEarned5.getText();
        classBoxArray[36][classIndex] = pointsEarned6.getText();
        classBoxArray[37][classIndex] = pointsEarned7.getText();
        classBoxArray[38][classIndex] = pointsEarned8.getText();
        classBoxArray[39][classIndex] = pointsEarned9.getText();
        classBoxArray[40][classIndex] = pointsEarned10.getText();
        classBoxArray[41][classIndex] = criteriaItem11.getText();
        classBoxArray[42][classIndex] = criteriaItem12.getText();
        classBoxArray[43][classIndex] = criteriaItem13.getText();
        classBoxArray[44][classIndex] = criteriaItem14.getText();
        classBoxArray[45][classIndex] = criteriaItem15.getText();
        classBoxArray[46][classIndex] = criteriaItem16.getText();
        classBoxArray[47][classIndex] = criteriaItem17.getText();
        classBoxArray[48][classIndex] = criteriaItem18.getText();
        classBoxArray[49][classIndex] = criteriaItem19.getText();
        classBoxArray[50][classIndex] = criteriaItem110.getText();
        classBoxArray[51][classIndex] = criteriaItem21.getText();
        classBoxArray[52][classIndex] = criteriaItem22.getText();
        classBoxArray[53][classIndex] = criteriaItem23.getText();
        classBoxArray[54][classIndex] = criteriaItem24.getText();
        classBoxArray[55][classIndex] = criteriaItem25.getText();
        classBoxArray[56][classIndex] = criteriaItem26.getText();
        classBoxArray[57][classIndex] = criteriaItem27.getText();
        classBoxArray[58][classIndex] = criteriaItem28.getText();
        classBoxArray[59][classIndex] = criteriaItem29.getText();
        classBoxArray[60][classIndex] = criteriaItem210.getText();
        classBoxArray[61][classIndex] = criteriaItem31.getText();
        classBoxArray[62][classIndex] = criteriaItem32.getText();
        classBoxArray[63][classIndex] = criteriaItem33.getText();
        classBoxArray[64][classIndex] = criteriaItem34.getText();
        classBoxArray[65][classIndex] = criteriaItem35.getText();
        classBoxArray[66][classIndex] = criteriaItem36.getText();
        classBoxArray[67][classIndex] = criteriaItem37.getText();
        classBoxArray[68][classIndex] = criteriaItem38.getText();
        classBoxArray[69][classIndex] = criteriaItem39.getText();
        classBoxArray[70][classIndex] = criteriaItem310.getText();
        classBoxArray[71][classIndex] = criteriaItem41.getText();
        classBoxArray[72][classIndex] = criteriaItem42.getText();
        classBoxArray[73][classIndex] = criteriaItem43.getText();
        classBoxArray[74][classIndex] = criteriaItem44.getText();
        classBoxArray[75][classIndex] = criteriaItem45.getText();
        classBoxArray[76][classIndex] = criteriaItem46.getText();
        classBoxArray[77][classIndex] = criteriaItem47.getText();
        classBoxArray[78][classIndex] = criteriaItem48.getText();
        classBoxArray[79][classIndex] = criteriaItem49.getText();
        classBoxArray[80][classIndex] = criteriaItem410.getText();
        classBoxArray[81][classIndex] = criteriaItem51.getText();
        classBoxArray[82][classIndex] = criteriaItem52.getText();
        classBoxArray[83][classIndex] = criteriaItem53.getText();
        classBoxArray[84][classIndex] = criteriaItem54.getText();
        classBoxArray[85][classIndex] = criteriaItem55.getText();
        classBoxArray[86][classIndex] = criteriaItem56.getText();
        classBoxArray[87][classIndex] = criteriaItem57.getText();
        classBoxArray[88][classIndex] = criteriaItem58.getText();
        classBoxArray[89][classIndex] = criteriaItem59.getText();
        classBoxArray[90][classIndex] = criteriaItem510.getText();
        classBoxArray[91][classIndex] = criteriaItem61.getText();
        classBoxArray[92][classIndex] = criteriaItem62.getText();
        classBoxArray[93][classIndex] = criteriaItem63.getText();
        classBoxArray[94][classIndex] = criteriaItem64.getText();
        classBoxArray[95][classIndex] = criteriaItem65.getText();
        classBoxArray[96][classIndex] = criteriaItem66.getText();
        classBoxArray[97][classIndex] = criteriaItem67.getText();
        classBoxArray[98][classIndex] = criteriaItem68.getText();
        classBoxArray[99][classIndex] = criteriaItem69.getText();
        classBoxArray[100][classIndex] = criteriaItem610.getText();
        classBoxArray[101][classIndex] = criteriaItem71.getText();
        classBoxArray[102][classIndex] = criteriaItem72.getText();
        classBoxArray[103][classIndex] = criteriaItem73.getText();
        classBoxArray[104][classIndex] = criteriaItem74.getText();
        classBoxArray[105][classIndex] = criteriaItem75.getText();
        classBoxArray[106][classIndex] = criteriaItem76.getText();
        classBoxArray[107][classIndex] = criteriaItem77.getText();
        classBoxArray[108][classIndex] = criteriaItem78.getText();
        classBoxArray[109][classIndex] = criteriaItem79.getText();
        classBoxArray[110][classIndex] = criteriaItem710.getText();
        classBoxArray[111][classIndex] = criteriaItem81.getText();
        classBoxArray[112][classIndex] = criteriaItem82.getText();
        classBoxArray[113][classIndex] = criteriaItem83.getText();
        classBoxArray[114][classIndex] = criteriaItem84.getText();
        classBoxArray[115][classIndex] = criteriaItem85.getText();
        classBoxArray[116][classIndex] = criteriaItem86.getText();
        classBoxArray[117][classIndex] = criteriaItem87.getText();
        classBoxArray[118][classIndex] = criteriaItem88.getText();
        classBoxArray[119][classIndex] = criteriaItem89.getText();
        classBoxArray[120][classIndex] = criteriaItem810.getText();
        classBoxArray[121][classIndex] = criteriaItem91.getText();
        classBoxArray[122][classIndex] = criteriaItem92.getText();
        classBoxArray[123][classIndex] = criteriaItem93.getText();
        classBoxArray[124][classIndex] = criteriaItem94.getText();
        classBoxArray[125][classIndex] = criteriaItem95.getText();
        classBoxArray[126][classIndex] = criteriaItem96.getText();
        classBoxArray[127][classIndex] = criteriaItem97.getText();
        classBoxArray[128][classIndex] = criteriaItem98.getText();
        classBoxArray[129][classIndex] = criteriaItem99.getText();
        classBoxArray[130][classIndex] = criteriaItem910.getText();
        classBoxArray[131][classIndex] = criteriaItem101.getText();
        classBoxArray[132][classIndex] = criteriaItem102.getText();
        classBoxArray[133][classIndex] = criteriaItem103.getText();
        classBoxArray[134][classIndex] = criteriaItem104.getText();
        classBoxArray[135][classIndex] = criteriaItem105.getText();
        classBoxArray[136][classIndex] = criteriaItem106.getText();
        classBoxArray[137][classIndex] = criteriaItem107.getText();
        classBoxArray[138][classIndex] = criteriaItem108.getText();
        classBoxArray[139][classIndex] = criteriaItem109.getText();
        classBoxArray[140][classIndex] = criteriaItem1010.getText();
        classBoxArray[141][classIndex] = classNameLabel.getText();
        classBoxArray[142][classIndex] = String.valueOf(currentGradeProgress.getProgress());
        classBoxArray[143][classIndex] = String.valueOf(projectedGPA.getText());
    }
    public void getClassBox(String selected) {

        boolean check = true;
        int classIndex;

        for (String s : classBoxList) {
            if (s.contains(selected))
                check = false;
        }

        if(!check) {

            classIndex = classBoxList.indexOf(selected);

            criteria1.setText(classBoxArray[0][classIndex]);
            criteria2.setText(classBoxArray[1][classIndex]);
            criteria3.setText(classBoxArray[2][classIndex]);
            criteria4.setText(classBoxArray[3][classIndex]);
            criteria5.setText(classBoxArray[4][classIndex]);
            criteria6.setText(classBoxArray[5][classIndex]);
            criteria7.setText(classBoxArray[6][classIndex]);
            criteria8.setText(classBoxArray[7][classIndex]);
            criteria9.setText(classBoxArray[8][classIndex]);
            criteria10.setText(classBoxArray[9][classIndex]);
            weight1.setText(classBoxArray[10][classIndex]);
            weight2.setText(classBoxArray[11][classIndex]);
            weight3.setText(classBoxArray[12][classIndex]);
            weight4.setText(classBoxArray[13][classIndex]);
            weight5.setText(classBoxArray[14][classIndex]);
            weight6.setText(classBoxArray[15][classIndex]);
            weight7.setText(classBoxArray[16][classIndex]);
            weight8.setText(classBoxArray[17][classIndex]);
            weight9.setText(classBoxArray[18][classIndex]);
            weight10.setText(classBoxArray[19][classIndex]);
            totalPoints1.setText(classBoxArray[20][classIndex]);
            totalPoints1.setText(classBoxArray[21][classIndex]);
            totalPoints2.setText(classBoxArray[22][classIndex]);
            totalPoints3.setText(classBoxArray[23][classIndex]);
            totalPoints4.setText(classBoxArray[24][classIndex]);
            totalPoints5.setText(classBoxArray[25][classIndex]);
            totalPoints6.setText(classBoxArray[26][classIndex]);
            totalPoints7.setText(classBoxArray[27][classIndex]);
            totalPoints8.setText(classBoxArray[28][classIndex]);
            totalPoints9.setText(classBoxArray[29][classIndex]);
            totalPoints10.setText(classBoxArray[30][classIndex]);
            pointsEarned1.setText(classBoxArray[31][classIndex]);
            pointsEarned2.setText(classBoxArray[32][classIndex]);
            pointsEarned3.setText(classBoxArray[33][classIndex]);
            pointsEarned4.setText(classBoxArray[34][classIndex]);
            pointsEarned5.setText(classBoxArray[35][classIndex]);
            pointsEarned6.setText(classBoxArray[36][classIndex]);
            pointsEarned7.setText(classBoxArray[37][classIndex]);
            pointsEarned8.setText(classBoxArray[38][classIndex]);
            pointsEarned9.setText(classBoxArray[39][classIndex]);
            pointsEarned10.setText(classBoxArray[40][classIndex]);
            criteriaItem11.setText(classBoxArray[41][classIndex]);
            criteriaItem12.setText(classBoxArray[42][classIndex]);
            criteriaItem13.setText(classBoxArray[43][classIndex]);
            criteriaItem14.setText(classBoxArray[44][classIndex]);
            criteriaItem15.setText(classBoxArray[45][classIndex]);
            criteriaItem16.setText(classBoxArray[46][classIndex]);
            criteriaItem17.setText(classBoxArray[47][classIndex]);
            criteriaItem18.setText(classBoxArray[48][classIndex]);
            criteriaItem19.setText(classBoxArray[49][classIndex]);
            criteriaItem110.setText(classBoxArray[50][classIndex]);
            criteriaItem21.setText(classBoxArray[51][classIndex]);
            criteriaItem22.setText(classBoxArray[52][classIndex]);
            criteriaItem23.setText(classBoxArray[53][classIndex]);
            criteriaItem24.setText(classBoxArray[54][classIndex]);
            criteriaItem25.setText(classBoxArray[55][classIndex]);
            criteriaItem26.setText(classBoxArray[56][classIndex]);
            criteriaItem27.setText(classBoxArray[57][classIndex]);
            criteriaItem28.setText(classBoxArray[58][classIndex]);
            criteriaItem29.setText(classBoxArray[59][classIndex]);
            criteriaItem210.setText(classBoxArray[60][classIndex]);
            criteriaItem31.setText(classBoxArray[61][classIndex]);
            criteriaItem32.setText(classBoxArray[62][classIndex]);
            criteriaItem33.setText(classBoxArray[63][classIndex]);
            criteriaItem34.setText(classBoxArray[64][classIndex]);
            criteriaItem35.setText(classBoxArray[65][classIndex]);
            criteriaItem36.setText(classBoxArray[66][classIndex]);
            criteriaItem37.setText(classBoxArray[67][classIndex]);
            criteriaItem38.setText(classBoxArray[68][classIndex]);
            criteriaItem39.setText(classBoxArray[69][classIndex]);
            criteriaItem310.setText(classBoxArray[70][classIndex]);
            criteriaItem41.setText(classBoxArray[71][classIndex]);
            criteriaItem42.setText(classBoxArray[72][classIndex]);
            criteriaItem43.setText(classBoxArray[73][classIndex]);
            criteriaItem44.setText(classBoxArray[74][classIndex]);
            criteriaItem45.setText(classBoxArray[75][classIndex]);
            criteriaItem46.setText(classBoxArray[76][classIndex]);
            criteriaItem47.setText(classBoxArray[77][classIndex]);
            criteriaItem48.setText(classBoxArray[78][classIndex]);
            criteriaItem49.setText(classBoxArray[79][classIndex]);
            criteriaItem410.setText(classBoxArray[80][classIndex]);
            criteriaItem51.setText(classBoxArray[81][classIndex]);
            criteriaItem52.setText(classBoxArray[82][classIndex]);
            criteriaItem53.setText(classBoxArray[83][classIndex]);
            criteriaItem54.setText(classBoxArray[84][classIndex]);
            criteriaItem55.setText(classBoxArray[85][classIndex]);
            criteriaItem56.setText(classBoxArray[86][classIndex]);
            criteriaItem57.setText(classBoxArray[87][classIndex]);
            criteriaItem58.setText(classBoxArray[88][classIndex]);
            criteriaItem59.setText(classBoxArray[89][classIndex]);
            criteriaItem510.setText(classBoxArray[90][classIndex]);
            criteriaItem61.setText(classBoxArray[91][classIndex]);
            criteriaItem62.setText(classBoxArray[92][classIndex]);
            criteriaItem63.setText(classBoxArray[93][classIndex]);
            criteriaItem64.setText(classBoxArray[94][classIndex]);
            criteriaItem65.setText(classBoxArray[95][classIndex]);
            criteriaItem66.setText(classBoxArray[96][classIndex]);
            criteriaItem67.setText(classBoxArray[97][classIndex]);
            criteriaItem68.setText(classBoxArray[98][classIndex]);
            criteriaItem69.setText(classBoxArray[99][classIndex]);
            criteriaItem610.setText(classBoxArray[100][classIndex]);
            criteriaItem71.setText(classBoxArray[101][classIndex]);
            criteriaItem72.setText(classBoxArray[102][classIndex]);
            criteriaItem73.setText(classBoxArray[103][classIndex]);
            criteriaItem74.setText(classBoxArray[104][classIndex]);
            criteriaItem75.setText(classBoxArray[105][classIndex]);
            criteriaItem76.setText(classBoxArray[106][classIndex]);
            criteriaItem77.setText(classBoxArray[107][classIndex]);
            criteriaItem78.setText(classBoxArray[108][classIndex]);
            criteriaItem79.setText(classBoxArray[109][classIndex]);
            criteriaItem710.setText(classBoxArray[110][classIndex]);
            criteriaItem81.setText(classBoxArray[111][classIndex]);
            criteriaItem82.setText(classBoxArray[112][classIndex]);
            criteriaItem83.setText(classBoxArray[113][classIndex]);
            criteriaItem84.setText(classBoxArray[114][classIndex]);
            criteriaItem85.setText(classBoxArray[115][classIndex]);
            criteriaItem86.setText(classBoxArray[116][classIndex]);
            criteriaItem87.setText(classBoxArray[117][classIndex]);
            criteriaItem88.setText(classBoxArray[118][classIndex]);
            criteriaItem89.setText(classBoxArray[119][classIndex]);
            criteriaItem810.setText(classBoxArray[120][classIndex]);
            criteriaItem91.setText(classBoxArray[121][classIndex]);
            criteriaItem92.setText(classBoxArray[122][classIndex]);
            criteriaItem93.setText(classBoxArray[123][classIndex]);
            criteriaItem94.setText(classBoxArray[124][classIndex]);
            criteriaItem95.setText(classBoxArray[125][classIndex]);
            criteriaItem96.setText(classBoxArray[126][classIndex]);
            criteriaItem97.setText(classBoxArray[127][classIndex]);
            criteriaItem98.setText(classBoxArray[128][classIndex]);
            criteriaItem99.setText(classBoxArray[129][classIndex]);
            criteriaItem910.setText(classBoxArray[130][classIndex]);
            criteriaItem101.setText(classBoxArray[131][classIndex]);
            criteriaItem102.setText(classBoxArray[132][classIndex]);
            criteriaItem103.setText(classBoxArray[133][classIndex]);
            criteriaItem104.setText(classBoxArray[134][classIndex]);
            criteriaItem105.setText(classBoxArray[135][classIndex]);
            criteriaItem106.setText(classBoxArray[136][classIndex]);
            criteriaItem107.setText(classBoxArray[137][classIndex]);
            criteriaItem108.setText(classBoxArray[138][classIndex]);
            criteriaItem109.setText(classBoxArray[139][classIndex]);
            criteriaItem1010.setText(classBoxArray[140][classIndex]);
            classNameLabel.setText(classBoxArray[141][classIndex]);
            currentGradeProgress.setProgress(Double.parseDouble(classBoxArray[142][classIndex]));
            projectedGPA.setText(classBoxArray[143][classIndex]);
        }
    }

    private void classBoxExpand(int size){

        classBoxNumber = classBoxNumber + 5;



        String[][] holdArray = new String[classBoxItems][classBoxNumber];

        for(int i = 0; i < classBoxItems; i++)
            for(int j = 0; j < size; j++)
                holdArray[i][j] = classBoxArray[i][j];

        classBoxArray = new String[classBoxItems][classBoxNumber];

        for(int i = 0; i < classBoxItems; i++)
            for(int j = 0; j < size; j++)
                classBoxArray[i][j] = holdArray[i][j];
    }
    //End of classBox
               /*
                else  {
                    if(number != numberTest) {
                        qualityPoints.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                        creditHours.setStyle("-fx-border-color: red ; -fx-border-width: 1px ;");
                    }
                     else{
                        qualityPoints.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");
                        creditHours.setStyle("-fx-border-color: black ; -fx-border-width: 1px ;");

                    }


                    numberTest = number;
*/



