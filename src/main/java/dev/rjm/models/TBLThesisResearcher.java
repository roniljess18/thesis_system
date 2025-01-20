package dev.rjm.models;

import dev.rjm.enums.Role;
import dev.sol.core.application.FXModel;
import dev.sol.core.properties.beans.FXObjectProperty;

public class TBLThesisResearcher extends FXModel{

    private FXObjectProperty<TBLThesis> tid;
    private FXObjectProperty<TBLStudent> rid;
    private FXObjectProperty<Role> role;


    public TBLThesisResearcher(TBLThesis tid, TBLStudent rid, Role role){
        this.tid = new FXObjectProperty<>(tid);
        this.rid = new FXObjectProperty<>(rid);
        this.role = new FXObjectProperty<>(role);

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
    public FXObjectProperty<Role> roleProperty(){
        return role;
    }
    public Role getRole(){
        return roleProperty().get();
    }
    public void setRole(Role role){
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
