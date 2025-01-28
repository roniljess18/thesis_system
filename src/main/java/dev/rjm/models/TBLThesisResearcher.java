package dev.rjm.models;

import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXObjectProperty;
import dev.sol.core.properties.beans.FXStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TableCell;

public class TBLThesisResearcher extends FXModel{
     public static class STUDENT_TABLECELL extends TableCell<TBLThesisResearcher, TBLStudent> {
        @Override
        protected void updateItem(TBLStudent item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                return;
            }
            setGraphic(new Label(String.valueOf(item.getStudentId())));
        }

    }

    public static class THESIS_TABLECELL extends TableCell<TBLThesisResearcher, TBLThesis> {
        @Override
        protected void updateItem(TBLThesis item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
                return;
            }
            setGraphic(new Label(String.valueOf(item.getThesisId())));
        }

    }
    


    private FXObjectProperty<TBLThesis> tid;
    private FXObjectProperty<TBLStudent> rid;
    private FXStringProperty role;


    public TBLThesisResearcher(TBLThesis tid, TBLStudent rid, String role){
        this.tid = new FXObjectProperty<>(tid);
        this.rid = new FXObjectProperty<>(rid);
        this.role = new FXStringProperty(role);

        track_properties(this.tid, this.rid, this.role);
    }

    // tid
    public FXObjectProperty<TBLThesis>  tidProperty(){
        return tid;
    }
    public TBLThesis getTid(){
        return tidProperty().get();
    }
    public void setTid(TBLThesis tid){
        tidProperty().set(tid);
    }

    // rid
    public FXObjectProperty<TBLStudent> ridProperty(){
        return rid;
    }
    public TBLStudent getRid(){
        return ridProperty().get();
    }
    public void setRid(TBLStudent rid){
        ridProperty().set(rid);
    }

    // role
    public FXStringProperty roleProperty(){
        return role;
    }
    public String getRole(){
        return roleProperty().get();
    }
    public void setRole(String role){
        roleProperty().set(role);
    }


    @Override
    public FXModel clone() {
        TBLThesisResearcher tblThesisResearcher = new TBLThesisResearcher(getTid(), getRid(), getRole());

        return tblThesisResearcher;
    }

    @Override
    public void copy(FXModel arg0) {
        TBLThesisResearcher c = (TBLThesisResearcher) arg0;

        setTid(c.getTid());
        setRid(c.getRid());
        setRole(c.getRole());
    }
    
}
