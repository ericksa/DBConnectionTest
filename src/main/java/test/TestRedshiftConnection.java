/*
package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class TestRedshiftConnection {
    public static void main(String[] args) {
        String defaultPort = "5439"; // default port for Oracle
        String defaultHost = "dhadaluatpxy.health.mil"; // default host
        String defaultServiceName = "edwu"; // default service name
        String defaultUser = "mip_srv_bcs_stage";
        String defaultPassword = "d9*UyhphhhbD7+48";
        System.out.println("");
        System.out.println("Creating Connection String....");
        System.out.println("Create Connection (press enter accept defaults):");
        System.out.println("");
        try (Scanner scanner = new Scanner(System.in)) {
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


            String sslOptions = useSSL ? "?ssl=true&sslfactory=com.amazon.redshift.ssl.NonValidatingFactory&sslProtocol=TLSv1.2" : "";
            String url = "jdbc:redshift://" + host + ":" + port + "/" + dbServiceName + sslOptions;
            System.out.println("Trying to connect to " + url);
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println("Connection successful.");

                // Execute a SQL query
                try (Statement stmt = conn.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT current_date")) {
                    if (rs.next()) {
                        System.out.println("Current date: " + rs.getString(1));
                    }
                } catch (SQLException e) {
                    System.out.println("Query execution failed.");
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                System.out.println("Connection failed.");
                e.printStackTrace();
            }
        }
    }


}
*/
