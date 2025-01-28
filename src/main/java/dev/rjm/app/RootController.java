package dev.rjm.app;

import dev.rjm.App;
import dev.rjm.data.TBLThesisDAO;
import dev.rjm.enums.Month;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
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
    private TableView<TBLThesisResearcher> authorTableView;
    @FXML
    private TableColumn<TBLThesisResearcher, Integer> ridColumn;
    @FXML
    private TableColumn<TBLThesisResearcher, TBLStudent> authornameColumn;
    @FXML
    private TableColumn<TBLThesisResearcher, String> roleColumn;
    @FXML
    private ComboBox<String> authorComboBox;
    @FXML
    private ComboBox<String> roleComboBox;
    @FXML
    private Button deleteauthor;
    @FXML
    private Button saveauthor;

    @FXML
    private void handleAuthorUpdate() {

    }
    @FXML
    private void handleDeteThesis() {
        TBLThesis selectedThesis = thesisTableView.getSelectionModel().getSelectedItem();
        if (selectedThesis == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Thesis Delete Error");
            alert.setHeaderText("Null selection Error!");
            alert.setContentText("No selected Thesis form the table");
            alert.initOwner(scene.getWindow());
            alert.show();
            return;
        }

        thesis_masterlist.remove(selectedThesis);
        TBLThesisDAO.delete(selectedThesis);
    }
    
    @FXML
    private void handleAdd() {

        TBLThesis selectedThesis = thesisTableView.getSelectionModel().getSelectedItem();
        TBLThesis newThesis = new TBLThesis(selectedThesis.getThesisId() + 1, "------> Click Here To Add New Thesis <------",
                2000,
                1, 1);

        selectedThesis.setTiltle(thesistitleArea.getText());
        selectedThesis.setDegId(degreeComboBox.getSelectionModel().getSelectedIndex() + 1);
        selectedThesis.setMonth(monthComboBox.getSelectionModel().getSelectedIndex() + 1);
        selectedThesis.setYear(yearField.getAnchor());

        thesis_masterlist.add(newThesis);
        TBLThesisDAO.update(selectedThesis);
        TBLThesisDAO.insert(newThesis);

    }
    
    @FXML
    private void handlEdit() {

        TBLThesis selectedThesis = thesisTableView.getSelectionModel().getSelectedItem();
        resetValue();
        deleteauthor.setVisible(true);
        saveauthor.setVisible(true);
        degreelist.clear();
        researcherList.clear();
        rolelist.clear();
        listContent();

        // Combobox Items
        yearField.getText();
        degreeComboBox.getItems();
        monthComboBox.setItems(months);
        authorComboBox.setItems(researcherList);
        authorComboBox.setValue(researcherList.get(0));
        roleComboBox.setItems(rolelist);
        roleComboBox.setValue(rolelist.get(0));

        if (thesisTableView.getSelectionModel().getSelectedIndex() == thesis_masterlist.size() - 1) {
            // default values for click to add
            yearField.getText();
            degreeComboBox.setItems(degree_masterlist);
            monthComboBox.setValue(months.get(0));
        } else {
            // For editing exisiting thesis
            yearField.setText(null);
            degreeComboBox.setItems(degree_masterlist);
            monthComboBox.setValue(months.get(selectedThesis.getMonth() - 1));
        }

    }

    

    private Scene scene;
    private ObservableList<TBLThesis> thesis_masterlist;
    private ObservableList<TBLStudent> student_masterlist;
    private ObservableList<TBLThesisResearcher> authormasterlist;
    private FilteredList<TBLThesisResearcher> authorFilteredlist;
    private FilteredList<TBLThesis> thesisfilteredList;
    private ObservableList<String> rolelist;
    private ObservableList<Degree> degree_masterlist;
    private ObservableList<Month> months;
    private ObservableList<Integer> years;
    private ObservableList<String> researcherList;
    private ObservableList<String> degreelist;
    
    @Override
    protected void load_bindings() {
        scene = (Scene) getParameter("SCENE");

        student_masterlist = App.COLLECTIONS_REGISTER.getList("STUDENT");
        authormasterlist = App.COLLECTIONS_REGISTER.getList("TBLTHESISRESEARCHER");
        thesis_masterlist = App.COLLECTIONS_REGISTER.getList("THESIS");
        degree_masterlist = App.COLLECTIONS_REGISTER.getList("DEGREE");

        //studentFilteredList = new FilteredList<>(student_masterlist, p -> true);
        thesisfilteredList = new FilteredList<>(thesis_masterlist);
        authorFilteredlist = new FilteredList<>(authormasterlist);
        rolelist = FXCollections.observableArrayList();
        researcherList = FXCollections.observableArrayList();       
        years = FXCollections.observableArrayList();
        months = FXCollections.observableArrayList();
        degreelist = FXCollections.observableArrayList();

        idColumn.setCellValueFactory(cell -> cell.getValue().thesis_idProperty().asObject());
        titleColumn.setCellValueFactory(cell -> cell.getValue().titleProperty());
        yearColumn.setCellValueFactory(cell -> cell.getValue().yearProperty().asObject());

        degreeComboBox.setItems(App.COLLECTIONS_REGISTER.getList("DEGREE"));
        degreeComboBox.setCellFactory(cell -> new Degree.LIST_CELL());
        degreeComboBox.setButtonCell(new Degree.LIST_CELL());

        // authorComboBox.setItems(App.COLLECTIONS_REGISTER.getList("TBLSTUDENTS"));
        // authorComboBox.setCellFactory(cell -> new TBLThesisResearcher.LIST_CELL());
        // authorComboBox.setButtonCell(new TBLThesisResearcher.LIST_CELL());

        ObservableList<Month> month = FXCollections.observableArrayList(Month.values());
        if (thesis_masterlist.stream().anyMatch(e -> e.getMonth().equals(Month.December))) {
            monthComboBox.setItems(FXCollections.observableArrayList(month.subList(0, 0)));
            month.size();
        } else
            monthComboBox.setItems(month);

        thesisTableView.setItems(thesisfilteredList);
       

        // roleColumn.setCellValueFactory(cell -> cell.getValue().roleProperty());

        thesisTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

            if (nv != null && thesisTableView.getSelectionModel().getSelectedIndex() < thesis_masterlist.size() - 1) {
                idField.setText(String.valueOf(nv.getThesisId()));
                thesistitleArea.setText(nv.getTitle());
                yearField.setText(String.valueOf(nv.getYear()));
                degreeComboBox.setValue(degree_masterlist.get(nv.getDegId() - 1));
                monthComboBox.setValue(Month.fromMonth(nv.getMonth()));
                ridColumn.setCellValueFactory(cell -> cell.getValue().getTid().thesis_idProperty().asObject());
                authornameColumn.setCellValueFactory(cell -> cell.getValue().ridProperty());
                roleColumn.setCellValueFactory(cell -> cell.getValue().roleProperty());
                authorFilteredlist.setPredicate(author -> author.getTid().getThesisId() == nv.getThesisId());
                authorTableView.setItems(authorFilteredlist);
                // _bind_labelProperties();
                

            } else {
                authorFilteredlist.setPredicate(author -> author.getTid().getThesisId() == 0);
                authorTableView.setItems(authorFilteredlist);
                idField.setText("");
                thesistitleArea.setText("");
                yearField.setText("");
            }
            // monthComboBox.setValue(Month.fromMonth(nv.getMonth()));
        });

        // authorTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
        //     if (nv != null) {
        //         authorComboBox.setPromptText(authormasterlist.get(nv.getRid()));

        //     } else {
        //         authorComboBox.setPromptText("");
        //     }

        // });
        
       

    }

    @Override
    protected void load_fields() {
         authornameColumn.setCellFactory(cell -> {
            TableCell<TBLThesisResearcher, TBLStudent> tableCell = new TableCell<>() {
                @Override
                protected void updateItem(TBLStudent item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item == null || empty) {
                        setText(null);
                        setGraphic(null);
                        return;
                    }

                    setGraphic(new Label(item.fullname()));
                }

            };
            return tableCell;
        });
         authorTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {

             if (authorTableView.getSelectionModel().isEmpty()) {
                 deleteauthor.setVisible(false);
                 saveauthor.setVisible(false);
                 authorComboBox.setValue(null);
                 roleComboBox.setValue(null);
             } else {
                 deleteauthor.setVisible(true);
                 saveauthor.setVisible(true);
                 roleComboBox.setValue(nv.getRole());
                 authorComboBox.setValue(nv.getRid().getFullname());

             }

         });
        
    }

    

    @Override
    protected void load_listeners() {
        deleteauthor.setVisible(false);
        saveauthor.setVisible(false);
        // reset_combobox();
        // thesisTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
        //     selectedthesis = nv;
        //     // _bind_labelProperties();

        // });
        // authorTableView.getSelectionModel().selectedItemProperty().addListener((o, ov, nv) -> {
        //     selectedauthor = nv;

        // });

        searchField.textProperty().addListener((o, ov, nv) -> {
            if (nv == null) {
                thesisfilteredList.setPredicate(p -> true);
            } else {
                thesisfilteredList.setPredicate(thesis -> {

                    String filter = searchField.getText().toUpperCase();

                    if (Integer.toString(thesis.getThesisId()).contains(filter))
                        return true;

                    if (thesis.getTitle().toUpperCase().contains(filter))
                        return true;

                    return Integer.toString(thesis.getYear()).contains(filter);

                });
            }
        });
    }

    

    // private void _bind_labelProperties() {

    // if (selectedthesis != null) {

    // degreeComboBox.valueProperty().bindBidirectional(selectedthesis.deg_idProperty());

    // }

    // }

    private void resetValue() {
        thesistitleArea.setPromptText(null);
        degreeComboBox.setValue(null);
        yearField.setText(null);
        monthComboBox.setValue(null);

        // degreeComboBox.getSelectionModel().selectFirst();
        // authorTableView.getSelectionModel().selectFirst();
    }
    
    public void listContent() {
        // Year
        for (int i = 0; i < 50; i++) {
            years.add(2000 + i);
        }
        // Degree
        for (int i = 0; i < degree_masterlist.size(); i++) {
            degreelist.add(degree_masterlist.get(i).getDegree());
        }
        // Months
        for (int i = 1; i <= 12; i++) {
            months.add(Month.fromMonth(i));
        }

        // Researcher
        for (int i = 0; i <= student_masterlist.size() - 1; i++) {
            researcherList.add(student_masterlist.get(i).getFullname());
        }

        // Role
        for (int i = 0; i <= 1; i++) {
            rolelist.add(authormasterlist.get(i).getRole());
        }

    }

}
