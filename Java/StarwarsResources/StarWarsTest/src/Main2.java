

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import java.util.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;
import javax.xml.transform.dom.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;

public class Main2 extends Application {

    Stage window;
    TableView<Resource> table;
    TextField planetInput, nameInput, typeInput;

    public static void main(String argv[]) {



        launch(argv);


    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Imports the SWG XML File



        //Starts Building the JAVA GUI

        window = primaryStage;
        window.setTitle("SWG Resource Bag");

        //Planet column
        TableColumn<Resource, String> planetColumn = new TableColumn<>("Planet");
        planetColumn.setMinWidth(100);
        planetColumn.setCellValueFactory(new PropertyValueFactory<>("planet"));

        //Name column
        TableColumn<Resource, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(100);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //Type column
        TableColumn<Resource, String> typeColumn = new TableColumn<>("Type");
        typeColumn.setMinWidth(100);
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));


        //Planet input
        planetInput = new TextField();
        planetInput.setPromptText("Planet");
        planetInput.setMinWidth(88);
        planetInput.setMaxWidth(88);

        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Name");
        nameInput.setMinWidth(88);
        nameInput.setMaxWidth(88);

        //Type input
        typeInput = new TextField();
        typeInput.setPromptText("Type");
        typeInput.setMinWidth(88);
        typeInput.setMaxWidth(88);


        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(planetInput, nameInput, typeInput, addButton, deleteButton);

        table = new TableView<>();
        table.setItems(getResource());
        table.getColumns().addAll(planetColumn, nameColumn, typeColumn);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table, hBox);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }

    //Add button clicked
    public void addButtonClicked(){
        Resource resource = new Resource();
        resource.setPlanet(planetInput.getText());
        resource.setName(nameInput.getText());
        resource.setType(typeInput.getText());

        table.getItems().add(resource);
        planetInput.clear();
        nameInput.clear();
        typeInput.clear();

    }

    //Delete button clicked
    public void deleteButtonClicked(){
        ObservableList<Resource> resourceSelected, allResources;
        allResources = table.getItems();
        resourceSelected = table.getSelectionModel().getSelectedItems();

        resourceSelected.forEach(allResources::remove);
    }

    //Get all of the resources
    public ObservableList<Resource> getResource(){
        ObservableList<Resource> resources = FXCollections.observableArrayList();

        try {

            File fXmlFile = new File("/Users/armyj/IdeaProjects/StarWarsTest/src/current68.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("resource");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    resources.add(new Resource((eElement.getElementsByTagName("planet").item(0).getTextContent()),
                                    (eElement.getElementsByTagName("CR").item(0).getTextContent())));


                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resources;
    }


}

/*

        resources.add(new Resource("Corellia", "Steel", "Ditanium", 5000, 0, 534, 438, 788, 0, 891, 253, 0, 62, 852, 762));
        resources.add(new Resource("Dantoonine","Iron", "Doonium", 22000,0, 593, 371, 820, 0, 856, 492, 0, 514, 851, 890));
        resources.add(new Resource("Dathomir","Aluminium", "Titanium", 50000,0, 8, 388, 46, 0, 314, 319, 0, 503, 397, 325));
        resources.add(new Resource("Naboo","Carbonate Ore", "Chromite", 100000,0, 556, 0, 154, 0, 638, 563, 0, 863, 221, 385));
        resources.add(new Resource("Tatooine","Copper", "Mythra", 200000,0, 537, 876, 544, 0, 494, 903, 0, 514, 627, 667));
        return resources;
 */