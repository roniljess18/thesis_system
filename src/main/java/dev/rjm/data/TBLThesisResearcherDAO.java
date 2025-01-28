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
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;
import javafx.collections.ObservableList;

public class TBLThesisResearcherDAO {
    public static final String TABLE = "tblthesisresearchers";
    public static final DBService DB = App.DB_THESIS;

    private static final ObservableList<TBLThesis> thesisList = App.COLLECTIONS_REGISTER.getList("THESIS");
    private static final ObservableList<TBLStudent> studentList = App.COLLECTIONS_REGISTER.getList("STUDENT");

    public static TBLThesisResearcher data(CachedRowSet crs) {
        try {

            TBLStudent studentID = studentList.stream()
                    .filter(student -> {
                        try {
                            return student.getStudentId() == (crs.getInt("RID"));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).findFirst().get();

            TBLThesis thesisID = thesisList.stream()
                    .filter(student -> {
                        try {
                            return student.getThesisId() == (crs.getInt("TID"));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return false;
                    }).findFirst().get();

            String role = crs.getString("Role");

            return new TBLThesisResearcher(thesisID, studentID, role);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    private static DBParam[] paramList(TBLThesisResearcher researcher) {
        List<DBParam> paramList = new LinkedList<>();

        paramList.add(new DBParam(DBType.NUMERIC, "RID", researcher.getRid().getStudentId()));

        paramList.add(new DBParam(DBType.NUMERIC, "TID", researcher.getTid().getThesisId()));

        paramList.add(new DBParam(DBType.TEXT, "Role", researcher.getRole()));

        return paramList.toArray(new DBParam[0]);
    }

    public static List<TBLThesisResearcher> getTBLThesisResearchers() {
        CachedRowSet crs = DB.select(TABLE);
        List<TBLThesisResearcher> list = new LinkedList<>();
        try {
            while (crs.next()) {
                TBLThesisResearcher researcher = data(crs);
                if (researcher != null) {
                    list.add(researcher);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public static void insert(TBLThesisResearcher researcher) {

        DB.insert(TABLE, paramList(researcher));
    }

    public static void delete(TBLThesisResearcher researcher) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "RID", researcher.getRid().getStudentId()));
    }

    public static void update(TBLThesisResearcher researcher) {

        DBParam[] params = paramList(researcher);

        for (int i = 0; i <= 2; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "RID",
                    researcher.getRid().getStudentId()), params[i]);
        }

    }

}
