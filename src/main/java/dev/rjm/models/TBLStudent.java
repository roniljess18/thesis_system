package dev.rjm.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;

public class TBLStudent extends FXModel {

    private FXIntegerProperty student_id;
    private FXStringProperty surname;
    private FXStringProperty firstname;
    private FXStringProperty Ml;
    

    public TBLStudent(Integer student_id, String surname, String firstname, String Ml) {
        this.student_id = new FXIntegerProperty(student_id);
        this.surname = new FXStringProperty(surname);
        this.firstname = new FXStringProperty(firstname);
        this.Ml = new FXStringProperty(Ml);

        track_properties(this.student_id, this.surname, this.firstname, this.Ml);
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

   public String getfullname(){
        return getSurname() + ", " + getFirstname() + ", " + getMl();
   }
   
   public String fullnameProperty() {
       return fullnameProperty();
   }

    @Override
    public FXModel clone() {
        TBLStudent tblStudent = new TBLStudent(getStudentId(), getSurname(), getFirstname(), getMl());

        return tblStudent;
    }


    @Override
    public void copy(FXModel arg0) {
        TBLStudent c = (TBLStudent) arg0;

        setStudentId(c.getStudentId());
        setSurname(c.getSurname());
        setFirstname(c.getFirstname());
        setMl(c.getMl());
    }
}
