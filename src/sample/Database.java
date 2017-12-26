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

    public static void initDatabase() {
        dropTables();
        createBarbarianTable();
        createTemplateTable();
    }

    public static void dropTables() {
        try {
            final String url = "jdbc:derby://localhost:1527/data";
            Connection con = DriverManager.getConnection(url);
            Statement s = con.createStatement();

            s.execute("DROP TABLE BARBARIAN");
            System.out.println("Dropped barbarian table");

            s.execute("DROP TABLE TEMPLATE");
            System.out.println("Dropped template table");

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
                    "PRIMARY KEY (x)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTemplateTable() {
        try {
            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();

            s.execute("CREATE TABLE Template (" +
                    "rowname CHAR(25) NOT NULL," +
                    "spear INTEGER," +
                    "sword INTEGER," +
                    "axe INTEGER," +
                    "scouts INTEGER," +
                    "LC INTEGER," +
                    "HC INTEGER," +
                    "Rams INTEGER," +
                    "Cats INTEGER," +
                    "Paladin INTEGER,"+
                    "PRIMARY KEY (rowname)" +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     *  CONTAINS ALL THE BARBARIAN TABLE FUNCTIONS
     *  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     * /

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
                String intStr = Integer.toString(x);
                villages.add(new Point(Integer.parseInt(intStr.substring(0,3)),Integer.parseInt(intStr.substring(3,6))));
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

        int rows = getBarbarianRowCount();

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
            }

            String total = Integer.toString(r.getInt("x"));

            return new Point(Integer.parseInt(total.substring(0,3)),Integer.parseInt(total.substring(3,6)));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Add a village to the database
     * @param x
     * @param y
     */
    public static void addVillage(int x, int y) {
        String xStr = Integer.toString(x);
        String yStr = Integer.toString(y);
        String total = xStr + yStr;
        try {
            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            s.execute("INSERT INTO BARBARIAN(x) VALUES (" +
                    total +
                    ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the number of rows currently in the database
     * @return
     */
    public static int getBarbarianRowCount() {

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

/** ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 *  CONTAINS ALL THE TEMPLATE TABLE FUNCTIONS
 *  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 */

    /**
     * Add a troop template to the database
     * @param troops Add a linked list of troops
     * @param templateName Key to the template, user defined name
     */
    public static void addTemplate(LinkedList<Integer> troops, String templateName) {

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            s.execute("INSERT INTO TEMPLATE (ROWNAME, SPEAR,SWORD,AXE,SCOUTS,LC,HC,RAMS,CATS,PALADIN) VALUES( '" +
                    templateName + "'," + troops.get(0) + "," + troops.get(1) + "," + troops.get(2) + "," + troops.get(3) + "," + troops.get(4) + "," + troops.get(5) + "," + troops.get(6) + "," + troops.get(7) + "," + troops.get(8) +
                    ")");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Return the row count of the template table
     * @return int representing the number of rows
     */
    public static int getTemplateRowCount() {

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT COUNT(*) FROM TEMPLATE");
            r.next();
            return r.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // return an error value
    }

    /**
     * Return the a linked list representing a specific troop template of a row number
     * @param rowNumber The row number of the template
     * @return LinkedList of the template
     */
    public static LinkedList<Integer> getTroopTemplate(String rowNumber) {

        LinkedList<Integer> returnTemplate = new LinkedList<>();
        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM TEMPLATE WHERE ROWNAME = '" + rowNumber + "'");
            returnTemplate.add(r.getInt("spear"));
            returnTemplate.add(r.getInt("sword"));
            returnTemplate.add(r.getInt("axe"));
            returnTemplate.add(r.getInt("LC"));
            returnTemplate.add(r.getInt("HC"));
            returnTemplate.add(r.getInt("Rams"));
            returnTemplate.add(r.getInt("Cats"));
            returnTemplate.add(r.getInt("Paladin"));
            returnTemplate.add(0);
            c.close();
            return returnTemplate;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Something went wrong when running getTroopTemplate, returned null");

        return null;

    }

    /**
     * Get all the troop templates
     * @return linked list of troops templates
     */
    public static LinkedList<LinkedList<Integer>> getAllTroopTemplates() {
        LinkedList<LinkedList<Integer>> returnList = new LinkedList<>();

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("SELECT * FROM TEMPLATE");
            while (r.next()){
                LinkedList<Integer> returnTemplate = new LinkedList<>();
                returnTemplate.add(r.getInt("spear"));
                returnTemplate.add(r.getInt("sword"));
                returnTemplate.add(r.getInt("axe"));
                returnTemplate.add(r.getInt("LC"));
                returnTemplate.add(r.getInt("HC"));
                returnTemplate.add(r.getInt("Rams"));
                returnTemplate.add(r.getInt("Cats"));
                returnTemplate.add(r.getInt("Paladin"));
                returnList.add(returnTemplate);
            }
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;

    }

    public static void removeTemplate(String templateName) {

        try {

            final String url = "jdbc:derby://localhost:1527/data";
            Connection c = DriverManager.getConnection(url);
            Statement s = c.createStatement();
            ResultSet r = s.executeQuery("DELETE FROM TEMPLATE WHERE ROWNAME = '" + templateName +"'");
            c.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
