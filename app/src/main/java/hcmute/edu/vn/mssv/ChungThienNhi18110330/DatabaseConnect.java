package hcmute.edu.vn.mssv.ChungThienNhi18110330;

import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;

public class DatabaseConnect {
    Connection con;
    String uname, pass, ip, port, database;

    public  Connection connectionclass(){
        ip="";
        database="AndroidProject";
        uname="sa";
        pass="";
        port="";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection=null;
        String ConnectionURL = null;

        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+uname+";password="+pass+";";
        }
        catch (Exception ex){
            Log.e("Error", ex.getMessage());
        }
        return connection;
    }
}
