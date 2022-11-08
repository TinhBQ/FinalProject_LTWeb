package Servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectJDBC {

    private final String serverName = "DESKTOP-VKIRTNN";
    private final String dbName = "LT_web";
    private final String portNumber = "1433";
    private final String instance = "";
    // MSSQLSERVER LEAVE THIS ONE EMPTY IF YOUR SQL IS A SINGLE INSTANCE
    private final String userID = "sa";
    private final String password = "1234567890";

    public static void main(String[] args) {
        try {
            Connection con = new ConnectJDBC().getConnectionW();

            String querry = "UPDATE Category SET categoryName = N'category 1000'\r\n"
                            + "WHERE categoryId = 10";

            PreparedStatement ps = con.prepareStatement(querry);

            ps.executeUpdate();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\"
                + instance + ";databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;

        System.out.print(url + "\n");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }

    public Connection getConnectionW() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\"
                + instance + ";integratedSecurity=true;databaseName=" + dbName;
        if (instance == null || instance.trim().isEmpty())
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";integratedSecurity=true;databaseName="
                    + dbName;
        DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
}