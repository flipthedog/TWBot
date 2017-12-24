package sample;

import net.bytebuddy.dynamic.scaffold.MethodGraph;

import java.awt.*;
import java.awt.geom.Point2D;
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

    public static void createTable() {

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

    public static Point2D getVillage(int n) {
        int x = 0;
        int y = 0;

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT  * FROM (" +
                    "SELECT " +
                    "ROW_NUMBER() OVER () AS R, " +
                    "C " +
                    "FROM BARBARIAN" +
                    ") AS foo" +
                    "WHERE R <= 2;");
            x = r.getInt("x");
            y = r.getInt("y");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Point(x,y);
    }

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
}
