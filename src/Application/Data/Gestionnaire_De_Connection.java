package Application.Data;

import java.sql.Connection;
import java.sql.DriverManager;

public class Gestionnaire_De_Connection {

    private Connection connection;
    public static int personnel_connecte;
    public static String user_connecte = "h2";

    public Connection getConnection() {

        String nom_BD = "system_scolarite";
        String userName = "sa";
        String password = "@*9292";

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

//            jdbc:sqlserver://localhost:1433;databaseName=system_scolarite;
//            jdbc:sqlserver://localhost;user=MyUserName;password=*****;
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=" + nom_BD, userName, password);


        } catch (Exception e) {
            e.printStackTrace();
        }


        return connection;
    }
}
