package dev.rjm.data;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.rjm.App;
import dev.rjm.models.TBLStudent;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class TBLStudentDAO {
    public static String TABLE = "tblstudents";
    public static final DBService DB = App.DB_THESIS;

    private static TBLStudent data(CachedRowSet crs){
        try {
            Integer student_id = crs.getInt("ID");
            String surname = crs.getString("Surname");
            String firstname = crs.getString("FirstName");
            String Ml = crs.getString("MI");

            return new TBLStudent(student_id, surname, firstname, Ml);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private static DBParam[] paramlist(TBLStudent tblStudent){
        List<DBParam> paramlist = new LinkedList<>();
        paramlist.add(new DBParam(DBType.NUMERIC, "ID", tblStudent.getStudentId()));
        paramlist.add(new DBParam(DBType.TEXT, "Surname", tblStudent.getSurname()));
        paramlist.add(new DBParam(DBType.TEXT, "FirstName", tblStudent.getFirstname()));
        paramlist.add(new DBParam(DBType.TEXT, "MI", tblStudent.getMl()));

        return paramlist.toArray(new DBParam[0]);

    }
    public static List<TBLStudent> getTBLStudentList(){
        CachedRowSet crs = DB.select_all(TABLE);
        List<TBLStudent> list = new LinkedList<>();

        try{
            while(crs.next()){
                TBLStudent tblStudent = data(crs);
                if(tblStudent != null);
                list.add(tblStudent);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
}
