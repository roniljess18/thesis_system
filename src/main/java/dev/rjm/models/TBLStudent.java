package dev.rjm.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class TBLStudent extends FXModel {

    // public static class LIST_CELL extends ListCell<TBLStudent>{
    //     @Override
    //     protected void updateItem(TBLStudent tblStudent, boolean empty){
    //         super.updateItem(tblStudent, empty);

    //         if(tblStudent == null || empty){
    //             setText(null);
    //             setGraphic(null);
    //             return;
    //         }
    //         setGraphic(new Label(tblStudent.getfullname()));

    //     }
    // }

    private FXIntegerProperty student_id;
    private FXStringProperty surname;
    private FXStringProperty firstname;
    private FXStringProperty Ml;
    private FXStringProperty fullname;
    

    public TBLStudent(Integer student_id, String surname, String firstname, String Ml) {
        this.student_id = new FXIntegerProperty(student_id);
        this.surname = new FXStringProperty(surname);
        this.firstname = new FXStringProperty(firstname);
        this.Ml = new FXStringProperty(Ml);
        this.fullname = new FXStringProperty(surname + ", " + firstname + ", " + Ml); 

        track_properties(this.student_id, this.surname, this.firstname, this.Ml, this.fullname);
    }

    // student_id
    public FXIntegerProperty student_idProperty() {
        return student_id;
    }

    public Integer getStudentId() {
        return student_idProperty().get();
    }

    public void setStudentId(Integer student_id) {
        student_idProperty().set(student_id);
    }

    // surname
    public FXStringProperty surnameProperty() {
        return surname;
    }

    public String getSurname() {
        return surnameProperty().get();
    }

    public void setSurname(String surname) {
        surnameProperty().set(surname);
    }

    // fisrtname
    public FXStringProperty firstnameProperty() {
        return firstname;
    }

    public String getFirstname() {
        return firstnameProperty().get();
    }

    public void setFirstname(String firstname) {
        firstnameProperty().set(firstname);
    }

    // MI
    public FXStringProperty MlProperty() {
        return Ml;
    }

    public String getMl() {
        return MlProperty().get();
    }

    public void setMl(String Ml) {
        MlProperty().set(Ml);
    }
    //fullname
   

    public FXStringProperty fullnameProperty(){
        return this.fullname;
    }
    public String getFullname(){
        return fullnameProperty().get();
    }
    public String fullname(){
        return surnameProperty().get() + ", " + firstnameProperty().get() + ", " + MlProperty().get();
    }

    @Override
    public FXModel clone() {
        TBLStudent tblStudent =new TBLStudent(getStudentId(), getSurname(), getFirstname(), getMl());

        return tblStudent;
    }


    @Override
    public void copy(FXModel arg0) {
        if(arg0 instanceof TBLStudent){
        TBLStudent c = (TBLStudent) arg0;
    
        setStudentId(c.getStudentId());
        setSurname(c.getSurname());
        setFirstname(c.getFirstname());
        setMl(c.getMl());
        // setFullname(c.getfullname());
        }else {
            throw new IllegalArgumentException("Argument must be an instance of Student");
        }
    }
}
