package dev.rjm.data;

import java.sql.SQLException;
import dev.rjm.enums.Month;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.rjm.App;
import dev.rjm.models.Degree;
import dev.rjm.models.TBLThesis;
import dev.rjm.models.Degree.LIST_CELL;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;
import javafx.collections.ObservableList;

public class TBLThesisDAO {
    public static String TABLE = "tblthesis";
    public static final DBService DB = App.DB_THESIS;
    public static ObservableList<Degree> DEGREE_LIST = App.COLLECTIONS_REGISTER.getList("DEGREE");

    private static TBLThesis data(CachedRowSet crs) {
        try {
            Integer thesis_id = crs.getInt("ID");
            String title = crs.getString("Title");
            Integer year = crs.getInt("Year");

            Degree deg_id = DEGREE_LIST.stream().filter(o -> {
                try {
                    return o.getDegreeID().equals(crs.getObject("DegID"));
                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }).findFirst().get();
            Integer month = crs.getInt("Month");
            //Month month = Month.values()[crs.getInt("Month")];

            return new TBLThesis(thesis_id, title, year, month, deg_id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DBParam[] paramlist(TBLThesis tblThesis) {
        List<DBParam> paramlist = new LinkedList<>();
        paramlist.add(new DBParam(DBType.NUMERIC, "ID", tblThesis.getThesisId()));
        paramlist.add(new DBParam(DBType.TEXT, "Title", tblThesis.getTitle()));
        paramlist.add(new DBParam(DBType.NUMERIC, "Year", tblThesis.getYear()));
        paramlist.add(new DBParam(DBType.NUMERIC, "Month", tblThesis.getMonth()));
        paramlist.add(new DBParam(DBType.NUMERIC, "DegID", tblThesis.getDegId()));

        return paramlist.toArray(new DBParam[0]);

    }

    public static List<TBLThesis> getTBLThesisList() {
        CachedRowSet crs = DB.select_all(TABLE);
        List<TBLThesis> list = new LinkedList<>();

        try {
            while (crs.next()) {
                TBLThesis tblThesis = data(crs);
                if (tblThesis != null)
                    ;
                list.add(tblThesis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
