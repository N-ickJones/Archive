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

public class Main extends Application {

    Stage window;
    TableView<Resource> table;
    TextField planetInput, nameInput, typeInput, quantityInput, ERInput, CRInput,
            CDInput, DRInput, FLInput, HRInput, MAInput, PEInput, OQInput, SRInput, UTInput;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
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

        //Quantity column
        TableColumn<Resource, String> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setMinWidth(100);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        //ER column
        TableColumn<Resource, String> ERColumn = new TableColumn<>("ER");
        ERColumn.setMinWidth(50);
        ERColumn.setCellValueFactory(new PropertyValueFactory<>("er"));

        //CR column
        TableColumn<Resource, String> CRColumn = new TableColumn<>("CR");
        CRColumn.setMinWidth(50);
        CRColumn.setCellValueFactory(new PropertyValueFactory<>("cr"));

        //CD column
        TableColumn<Resource, String> CDColumn = new TableColumn<>("CD");
        CDColumn.setMinWidth(50);
        CDColumn.setCellValueFactory(new PropertyValueFactory<>("cd"));

        //DR column
        TableColumn<Resource, String> DRColumn = new TableColumn<>("DR");
        DRColumn.setMinWidth(50);
        DRColumn.setCellValueFactory(new PropertyValueFactory<>("dr"));

        //FL column
        TableColumn<Resource, String> FLColumn = new TableColumn<>("FL");
        FLColumn.setMinWidth(50);
        FLColumn.setCellValueFactory(new PropertyValueFactory<>("fl"));

        //HR column
        TableColumn<Resource, String> HRColumn = new TableColumn<>("HR");
        HRColumn.setMinWidth(50);
        HRColumn.setCellValueFactory(new PropertyValueFactory<>("hr"));

        //MA column
        TableColumn<Resource, String> MAColumn = new TableColumn<>("MA");
        MAColumn.setMinWidth(50);
        MAColumn.setCellValueFactory(new PropertyValueFactory<>("ma"));

        //PE column
        TableColumn<Resource, String> PEColumn = new TableColumn<>("PE");
        PEColumn.setMinWidth(50);
        PEColumn.setCellValueFactory(new PropertyValueFactory<>("pe"));

        //OQ column
        TableColumn<Resource, String> OQColumn = new TableColumn<>("OQ");
        OQColumn.setMinWidth(50);
        OQColumn.setCellValueFactory(new PropertyValueFactory<>("oq"));

        //SR column
        TableColumn<Resource, String> SRColumn = new TableColumn<>("SR");
        SRColumn.setMinWidth(50);
        SRColumn.setCellValueFactory(new PropertyValueFactory<>("sr"));

        //UT column
        TableColumn<Resource, String> UTColumn = new TableColumn<>("UT");
        UTColumn.setMinWidth(50);
        UTColumn.setCellValueFactory(new PropertyValueFactory<>("ut"));

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

        //Quantity input
        quantityInput = new TextField();
        quantityInput.setPromptText("Quantity");
        quantityInput.setMinWidth(88);
        quantityInput.setMaxWidth(88);

        //ER input
        ERInput = new TextField();
        ERInput.setPromptText("ER");
        ERInput.setMinWidth(45);
        ERInput.setMaxWidth(45);

        //CR input
        CRInput = new TextField();
        CRInput.setPromptText("CR");
        CRInput.setMinWidth(45);
        CRInput.setMaxWidth(45);

        //CD input
        CDInput = new TextField();
        CDInput.setPromptText("CD");
        CDInput.setMinWidth(45);
        CDInput.setMaxWidth(45);

        //DR input
        DRInput= new TextField();
        DRInput.setPromptText("DR");
        DRInput.setMinWidth(45);
        DRInput.setMaxWidth(45);

        //FL input
        FLInput  = new TextField();
        FLInput.setPromptText("FL");
        FLInput.setMinWidth(45);
        FLInput.setMaxWidth(45);

        //HR input
        HRInput = new TextField();
        HRInput.setPromptText("HR");
        HRInput.setMinWidth(45);
        HRInput.setMaxWidth(45);

        //MA input
        MAInput = new TextField();
        MAInput.setPromptText("MA");
        MAInput.setMinWidth(45);
        MAInput.setMaxWidth(45);

        //PE input
        PEInput = new TextField();
        PEInput.setPromptText("PE");
        PEInput.setMinWidth(45);
        PEInput.setMaxWidth(45);

        //OQ input
        OQInput = new TextField();
        OQInput.setPromptText("OQ");
        OQInput.setMinWidth(45);
        OQInput.setMaxWidth(45);

        //SR input
        SRInput = new TextField();
        SRInput.setPromptText("SR");
        SRInput.setMinWidth(45);
        SRInput.setMaxWidth(45);

        //UT input
        UTInput = new TextField();
        UTInput.setPromptText("UT");
        UTInput.setMinWidth(45);
        UTInput.setMaxWidth(45);


        //Button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> addButtonClicked());
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> deleteButtonClicked());

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(planetInput, nameInput, typeInput, quantityInput, ERInput, CRInput, CDInput, DRInput, FLInput, HRInput, MAInput, PEInput, OQInput, SRInput, UTInput, addButton, deleteButton);

        table = new TableView<>();
        table.setItems(getResource());
        table.getColumns().addAll(planetColumn, nameColumn, typeColumn, quantityColumn, ERColumn, CRColumn, CDColumn, DRColumn, FLColumn, HRColumn, MAColumn, PEColumn, OQColumn, SRColumn, UTColumn);

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
        resource.setQuantity(Integer.parseInt(quantityInput.getText()));
        resource.setER(Integer.parseInt(ERInput.getText()));
        resource.setCR(Integer.parseInt(CRInput.getText()));
        resource.setCD(Integer.parseInt(CDInput.getText()));
        resource.setDR(Integer.parseInt(DRInput.getText()));
        resource.setFL(Integer.parseInt(FLInput.getText()));
        resource.setHR(Integer.parseInt(HRInput.getText()));
        resource.setMA(Integer.parseInt(MAInput.getText()));
        resource.setPE(Integer.parseInt(PEInput.getText()));
        resource.setOQ(Integer.parseInt(OQInput.getText()));
        resource.setSR(Integer.parseInt(SRInput.getText()));
        resource.setUT(Integer.parseInt(UTInput.getText()));


        table.getItems().add(resource);
        planetInput.clear();
        nameInput.clear();
        typeInput.clear();
        quantityInput.clear();
        ERInput.clear();
        CRInput.clear();
        CDInput.clear();
        DRInput.clear();
        FLInput.clear();
        HRInput.clear();
        MAInput.clear();
        PEInput.clear();
        OQInput.clear();
        SRInput.clear();
        UTInput.clear();
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
        resources.add(new Resource("Corellia", "Steel", "Ditanium", 5000, 0, 534, 438, 788, 0, 891, 253, 0, 62, 852, 762));
        resources.add(new Resource("Dantoonine","Iron", "Doonium", 22000,0, 593, 371, 820, 0, 856, 492, 0, 514, 851, 890));
        resources.add(new Resource("Dathomir","Aluminium", "Titanium", 50000,0, 8, 388, 46, 0, 314, 319, 0, 503, 397, 325));
        resources.add(new Resource("Naboo","Carbonate Ore", "Chromite", 100000,0, 556, 0, 154, 0, 638, 563, 0, 863, 221, 385));
        resources.add(new Resource("Tatooine","Copper", "Mythra", 200000,0, 537, 876, 544, 0, 494, 903, 0, 514, 627, 667));
        return resources;
    }


}