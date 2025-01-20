package dev.rjm.data;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.rjm.App;
import dev.rjm.enums.Role;
import dev.rjm.models.Degree;
import dev.rjm.models.TBLStudent;
import dev.rjm.models.TBLThesis;
import dev.rjm.models.TBLThesisResearcher;
import dev.sol.db.DBService;
import javafx.collections.ObservableList;

public class TBLThesisResearcherDAO {
    public static String TABLE = "tblthesisresearchers";
    public static final DBService DB = App.DB_THESIS; 
    public static ObservableList<TBLThesis> THESIS_LIST = App.COLLECTIONS_REGISTER.getList("TBLTHESIS");
    private static final ObservableList<TBLStudent> studentlist = App.COLLECTIONS_REGISTER.getList("TBLSTUDEN");
    

    private static TBLThesisResearcher data(CachedRowSet crs){
        try {

            Role role = Role.valueOf(crs.getString("ROLE").toUpperCase().trim());
            //TBLThesis tid = TBLThesis.valueOf(crs.getString("TID").toUpperCase().trim());
            // TBLThesis tid = THESIS_LIST.stream().filter(o -> {
            //     try {
            //         return o.getThesisId().equals(crs.getObject("TID"));
            //     } catch (SQLException e) {
            //         e.printStackTrace();
            //         return false;
            //     }
            // }).findFirst().get();
           return new TBLThesisResearcher(null, null, role);
            //return new TBLThesisResearcher(tid, null, role);

        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }
    public static List<TBLThesisResearcher> getTBLThesisResearcherList() {
        CachedRowSet crs = DB.select_all(TABLE);
        List<TBLThesisResearcher> list = new LinkedList<>();

        try {
            while (crs.next()) {
                TBLThesisResearcher tblThesisResearcher = data(crs);
                                if (tblThesisResearcher != null)
                                    
                                list.add(tblThesisResearcher);
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return list;
                    }
                
                   
}
