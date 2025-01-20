package dev.rjm.app;

import dev.rjm.App;
import dev.rjm.enums.Month;
import dev.rjm.enums.Role;
import dev.rjm.models.Degree;
import dev.rjm.models.TBLStudent;
import dev.rjm.models.TBLThesis;
import dev.rjm.models.TBLThesisResearcher;
import dev.sol.core.application.FXController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class RootController extends FXController {

    @FXML
    private TextField searchField;
    @FXML
    private Button goButton;
    @FXML
    private TableView<TBLThesis> thesisTableView;
    @FXML
    private TableColumn<TBLThesis, Integer> idColumn;
    @FXML
    private TableColumn<TBLThesis, String> titleColumn;
    @FXML
    private TableColumn<TBLThesis, Integer> yearColumn;
    @FXML
    private TextField idField;
    @FXML
    private TextArea thesistitleArea;
    @FXML
    private ComboBox<Degree> degreeComboBox;
    @FXML
    private ComboBox<Month> monthComboBox;
    @FXML
    private TextField yearField;
    @FXML
    private Button deleteButton;
    @FXML
    private Button saveButton;
    @FXML
    private TableView<TBLStudent> authorTableView;
    @FXML
    private TableColumn<TBLStudent, Integer> ridColumn;
    @FXML
    private TableColumn<TBLStudent, String> authorColumn;
    @FXML
    private TableColumn<Role, TBLThesisResearcher> roleColumn;
    @FXML
    private ComboBox<TBLStudent> authorComboBox;
    @FXML
    private ComboBox<Role> roleComboBox;

    @FXML
    private void handleAuthorUpdate(){
    
    }

    private Scene scene;
    private ObservableList<TBLThesis> thesis_masterlist;
    private TBLThesis selectedthesis;
    private ObservableList<TBLStudent> student_masterlist;
    private TBLStudent selectedstudent;
    private FilteredList<TBLStudent> jdhsFilteredList;
    private FilteredList<TBLStudent> rolelist;
    

    @Override
    protected void load_fields() {
        scene = (Scene) getParameter("SCENE");
        //student_masterlist = App.COLLECTIONS_REGISTER.getList("TBLTHESISRESEARCHER");
        student_masterlist = App.COLLECTIONS_REGISTER.getList("TBLSTUDENTS");
        thesis_masterlist = App.COLLECTIONS_REGISTER.getList("TBLTHESIS");
        jdhsFilteredList = new FilteredList<>(student_masterlist, p -> true);

        //  rolelist = new FilteredList<>(student_masterlist, employee -> {
        //     return employee.getStudentId() == Role.ADVISER || employee.getStudentId() == Role.RESEARCHER;
        // });

        idColumn.setCellValueFactory(cell -> cell.getValue().thesis_idProperty().asObject());
        titleColumn.setCellValueFactory(cell -> cell.getValue().titleProperty());
        yearColumn.setCellValueFactory(cell -> cell.getValue().yearProperty().asObject());

        degreeComboBox.setItems(App.COLLECTIONS_REGISTER.getList("DEGREE"));
        degreeComboBox.setCellFactory(cell -> new Degree.LIST_CELL());
        degreeComboBox.setButtonCell(new Degree.LIST_CELL());

        ObservableList<Month> joblist =FXCollections.observableArrayList(Month.values());
            if (thesis_masterlist.stream().anyMatch(e -> e.getMonth().equals(Month.December))) {
                monthComboBox.setItems(FXCollections.observableArrayList(joblist.subList(0,0)));
                joblist.size();
            } else
            monthComboBox.setItems(joblist);

        ridColumn.setCellValueFactory(cell -> cell.getValue().student_idProperty().asObject());
        authorColumn.setCellValueFactory(cell -> cell.getValue().surnameProperty());
        //roleColumn.setCellValueFactory(cell -> cell.getValue().roleProperty().asObject());

        

        thesisTableView.setItems(thesis_masterlist);
        authorTableView.setItems(student_masterlist);

        thesisTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            
            if (nv != null){
            idField.setText(String.valueOf(nv.getThesisId()));
            thesistitleArea.setText(nv.getTitle());
            yearField.setText(String.valueOf(nv.getYear()));
            //degreeComboBox.setValue(Degree.valueOf(nv.getDegId()));
            monthComboBox.setValue(Month.fromMonth(nv.getMonth()));
            _bind_labelProperties();
            } else {
                idField.setText("");
                thesistitleArea.setText("");
                yearField.setText("");
            }
           monthComboBox.setValue(Month.fromMonth(nv.getMonth()));
        });

        authorTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            if(nv != null){
                authorComboBox.setPromptText(nv.getfullname());
            }
            

            
        });

    }

    @Override
    protected void load_bindings() {
        _bind_labelProperties();

    }

    @Override
    protected void load_listeners() {
        reset_combobox();
        thesisTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldvalue, newValue) -> {
            selectedthesis = newValue;
            _bind_labelProperties();
        });
        authorTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
            selectedstudent = nv;
        });

    }

    private void _bind_labelProperties() {
        if (selectedthesis != null) {
            //idField.textPropertvay().bind(selectedthesis.thesis_idProperty());
            degreeComboBox.valueProperty().bindBidirectional(selectedthesis.deg_idProperty());
            //monthComboBox.valueProperty().bindBidirectional(selectedthesis.monthProperty());
        }

    }

    private void reset_combobox() {

        degreeComboBox.getSelectionModel().selectFirst();
        
    }

}
