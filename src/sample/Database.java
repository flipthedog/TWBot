package sample;

import net.bytebuddy.dynamic.scaffold.MethodGraph;
import org.apache.derby.vti.VTIEnvironment;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.sql.*;
import java.util.LinkedList;
import java.util.Vector;

public class Database {

    Connection c;

    public Database() {

        try {
            final String url = "jdbc:derby://localhost:1527/data";
            Connection con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createBarbarianTable() {

        try {
            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();

            s.execute("CREATE TABLE Barbarian (" +
                    "x INTEGER NOT NULL, " +
                    "y INTEGER, " +
                    "PRIMARY KEY (x)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return all the barbarian villages
     * @return LinkedList containing the point of a barbarian village
     */
    public static LinkedList<Point2D> getAllBarbarians() {
        LinkedList<Point2D> villages = new LinkedList<>();

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT  * from BARBARIAN");

            while(r.next()) {
                int x = r.getInt("x");
                int y = r.getInt("y");

                villages.add(new Point(x,y));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return villages;
    }

    /**
     * Get a village with a specific row count
     * @param n the row count of the village
     * @return Return the point of the village
     */
    public static Point2D getVillage(int n) {
        int rows = getRowCount();
        if(n > rows){
            return null;
        }
        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM BARBARIAN");
            for(int i = 0; i <= n; i++) {
                r.next();
                System.out.println("Cycled");
            }
            return new Point(r.getInt("x"), r.getInt("y"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        //return new Point(x,y);
        return null;
    }

    /**
     * Add a village to the database
     * @param x
     * @param y
     */
    public static void addVillage(int x, int y) {

        try {
            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            s.execute("INSERT INTO BARBARIAN(x,y) VALUES (" +
                    x + "," + y +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the number of rows currently in the database
     * @return
     */
    public static int getRowCount() {

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT COUNT(*) FROM BARBARIAN");
            r.next();
            return r.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // return an error value
    }

}
