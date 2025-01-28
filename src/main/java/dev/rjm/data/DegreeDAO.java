package dev.rjm.data;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import dev.rjm.App;
import dev.rjm.models.Degree;
import dev.sol.db.DBParam;
import dev.sol.db.DBService;
import dev.sol.db.DBType;

public class DegreeDAO {

    public static String TABLE = "degree";
    public static final DBService DB = App.DB_THESIS;

    private static Degree data(CachedRowSet crs){
        try{
            Integer degreeId = crs.getInt("degreeID");
            String degree = crs.getString("Degree");

            return new Degree(degreeId, degree);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    private static DBParam[] paramlist(Degree degree){
        List<DBParam> paramlist = new LinkedList<>();
        paramlist.add(new DBParam(DBType.NUMERIC, "degreeID", degree.getDegreeID()));
        paramlist.add(new DBParam(DBType.TEXT, "Degree", degree.getDegree()));

        return paramlist.toArray(new DBParam[0]);
    }

     public static List<Degree> getDegreeList(){
        CachedRowSet crs = DB.select_all(TABLE);
        List<Degree> list = new LinkedList<>();

        try{
            while(crs.next()){
                Degree degree = data(crs);
                if(degree != null);
                list.add(degree);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return list;
    }
    
    public static void insert(Degree degree) {
        DB.insert(TABLE, paramlist(degree));
    }

    public static void delete(Degree degree) {
        DB.delete(TABLE, new DBParam(DBType.NUMERIC, "degreeID", degree.getDegreeID()));
    }

    public static void update(Degree degree) {

        DBParam[] params = paramlist(degree);

        for (int i = 0; i <= 17; i++) {
            DB.update(TABLE, new DBParam(DBType.NUMERIC, "degreeID",
                    degree.getDegreeID()), params[i]);
        }

    }
    
}
