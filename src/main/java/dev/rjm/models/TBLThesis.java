package dev.rjm.models;

//import dev.rjm.enums.Month;
import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXIntegerProperty;
import dev.sol.core.properties.beans.FXObjectProperty;
import dev.sol.core.properties.beans.FXStringProperty;

public class TBLThesis extends FXModel {

    private FXIntegerProperty thesis_id;
    private FXStringProperty title;
    private FXIntegerProperty year;
    private FXIntegerProperty month;
    //private FXObjectProperty<Month> month;
    private FXObjectProperty<Degree> deg_id;

    public TBLThesis(Integer thesis_id, String title, Integer year, Integer month, Degree deg_id) {
        this.thesis_id = new FXIntegerProperty(thesis_id);
        this.title = new FXStringProperty(title);
        this.year = new FXIntegerProperty(year);
        this.month = new FXIntegerProperty(month);
        //this.month = new FXObjectProperty<>(month);
        this.deg_id = new FXObjectProperty<>(deg_id);

        track_properties(this.thesis_id, this.title, this.year, this.month, this.deg_id);
    }

    // thesis_id
    public FXIntegerProperty thesis_idProperty() {
        return thesis_id;
    }

    public Integer getThesisId() {
        return thesis_idProperty().get();
    }

    public void setThesisId(Integer thesis_id) {
        thesis_idProperty().set(thesis_id);
    }

    // title
    public FXStringProperty titleProperty() {
        return title;
    }

    public String getTitle() {
        return titleProperty().get();
    }

    public void setTiltle(String title) {
        titleProperty().set(title);
    }

    // year
    public FXIntegerProperty yearProperty() {
        return year;
    }

    public Integer getYear() {
        return yearProperty().get();
    }

    public void setYear(Integer year) {
        yearProperty().set(year);
    }

    //month
    public FXIntegerProperty monthProperty() {
        return month;
    }

    public Integer getMonth() {
        return monthProperty().get();
    }

    public void setMonth(int month) {
        monthProperty().set(month);
    }

    // public FXObjectProperty<Month> monthProperty() {
    // return month;
    // }

    // public Month getMonth() {
    // return monthProperty().get();
    // }

    // public void setMonth(Month month) {
    // monthProperty().set(month);
    // }

    // deg_id
    public FXObjectProperty<Degree> deg_idProperty() {
        return deg_id;
    }

    public Degree getDegId() {
        return deg_idProperty().get();
    }

    public void setDegId(Degree deg_id) {
        deg_idProperty().set(deg_id);
    }

    @Override
    public FXModel clone() {
        TBLThesis tblThesis = new TBLThesis(getThesisId(), getTitle(), getYear(), getMonth(), getDegId());

        return tblThesis;
    }

    @Override
    public void copy(FXModel arg0) {
        TBLThesis c = (TBLThesis) arg0;

        setThesisId(c.getThesisId());
        setTiltle(c.getTitle());
        setYear(c.getYear());
        setMonth(c.getMonth());
        setDegId(c.getDegId());
    }

}
