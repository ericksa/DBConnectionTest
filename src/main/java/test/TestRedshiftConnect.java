package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestRedshiftConnect {


    public static void main(String[] args) {
   //Redshift driver: "jdbc:redshift://x.y.us-west-.redshift.amazonaws.com:5439/dev";
// or "jdbc:postgresql://x.y.us-west-2.redshift.amazonaws.com:5439/dev";
        String defaultPort = "5439"; // default port for Oracle
        String defaultHost = "dhadaluatpxy.health.mil"; // default host
        String defaultServiceName = "edwu"; // default service name
        String defaultUser = "mip_srv_bcs_stage";
        String defaultPassword = "d9*UyhphhhbD7+48";
        System.out.println("");
        System.out.println("Creating Connection String....");
        System.out.println("Create Connection (press enter accept defaults):");
        System.out.println("");

        Scanner scanner = new Scanner(System.in); {
            System.out.print("Enter host[" + defaultHost + "]: ");
            String host = scanner.nextLine();
            if (host.isEmpty()) {
                host = defaultHost;
            }
            System.out.println("");
            System.out.println("---Host set to: " + host);
            System.out.println("");

            System.out.print("Enter Port[" + defaultPort + "]: ");
            String port = scanner.nextLine();
            if (port.isEmpty()) {
                port = defaultPort;
            }
            System.out.println("");
            System.out.println("---Port set to:" + port);
            System.out.println("");

            System.out.print("Enter Service Name[" + defaultServiceName + "]: ");
            String dbServiceName = scanner.nextLine();
            if (dbServiceName.isEmpty()) {
                dbServiceName = defaultServiceName;
            }
            System.out.println("");
            System.out.println("---Service Name set to: " + dbServiceName);
            System.out.println("");

            System.out.print("Enter Username[Redshift UAT user]: ");
            String user = scanner.nextLine();
            if (user.isEmpty()) {
                user = defaultUser;
            }

            System.out.println("");
            System.out.println("---Username set to:" + user);
            System.out.println("");
            String password = null;

            char[] passwordArray = System.console().readPassword("Enter user password:[Redshift UAT password] ");
            if (passwordArray != null && passwordArray.length > 0) {
                password = new String(passwordArray);

            } else {
                password = defaultPassword;
            }
            System.out.println("");
            System.out.println("--- Password set and not null.");
            System.out.println("");

            // SSL
            System.out.print("Use SSL (yes/no) [no]: ");
            String sslInput = scanner.nextLine();
            boolean useSSL = "yes".equalsIgnoreCase(sslInput);

        Connection conn = null;
        Statement stmt = null;
        try{
            //Dynamically load driver at runtime.
            //Redshift JDBC 4.1 driver: com.amazon.redshift.jdbc41.Driver
            //Redshift JDBC 4 driver: com.amazon.redshift.jdbc4.Driver
            Class.forName("com.amazon.redshift.jdbc.Driver");

            //Open a connection and define properties.
            System.out.println("Connecting to database...");
            Properties props = new Properties();

            //Uncomment the following line if using a keystore.
            if (useSSL){
            props.setProperty("ssl", "true");
            }
            props.setProperty("user", user);
            props.setProperty("password", password);
            // jdbc:redshift://endpoint:port/database
            String dbURL = "jdbc:redshift://"  + host + ":"+port + "/" + dbServiceName;
            conn = DriverManager.getConnection(dbURL, props);

            //Try a simple query.
            System.out.println("Listing system tables...");
            stmt = conn.createStatement();
            String sql;
            sql = "select * from information_schema.tables;";
            ResultSet rs = stmt.executeQuery(sql);

            //Get the data from the result set.
            while(rs.next()){
                //Retrieve two columns.
                String catalog = rs.getString("table_catalog");
                String name = rs.getString("table_name");

                //Display values.
                System.out.print("Catalog: " + catalog);
                System.out.println(", Name: " + name);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception ex){
            //For convenience, handle all errors here.
            ex.printStackTrace();
        }finally{
            //Finally block to close resources.
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(Exception ex){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }
        System.out.println("Finished connectivity test.");
    }

        }

}