package dev.rjm.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;

public class Degree extends FXModel{
    public static class LIST_CELL extends ListCell<Degree> {
        @Override
        protected void updateItem(Degree degree, boolean empty) {
            super.updateItem(degree, empty);

            if (degree == null || empty) {
                setText(null);
                setGraphic(null);
                return;
            }

            setGraphic(new Label(degree.getDegree()));
        }

    }


    private FXIntegerProperty degreeID;
    private FXStringProperty degree;

    public Degree(Integer degreeID, String degree){
        this.degreeID = new FXIntegerProperty(degreeID);
        this.degree = new FXStringProperty(degree);

        track_properties(this.degreeID, this.degree);
    }

    // degreeID
    public FXIntegerProperty degreeIDProperty(){
        return degreeID;
    }
    public Integer getDegreeID(){
        return degreeIDProperty().get();
    }
    public void setDegreeID(Integer degreeID){
        degreeIDProperty().set(degreeID);
    }

    // degree
    public FXStringProperty degreeProperty(){
        return degree;
    }
    public String getDegree(){
        return degreeProperty().get();
    }
    public void setDegree(String degree){
        degreeProperty().set(degree);
    }

    @Override
    public FXModel clone() {
        Degree degree = new Degree(getDegreeID(), getDegree());

        return degree;
        
    }

    @Override
    public void copy(FXModel arg0) {
        Degree c = (Degree) arg0;

        setDegreeID(c.getDegreeID());
        setDegree(c.getDegree());
    }

    // public static Degree valueOf(String degree2) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'valueOf'");
    // }
    
}
