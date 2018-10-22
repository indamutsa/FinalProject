package db;

import exceptions.PizzaException;
import io.BuildPizzaConfig;
import model.PizzaConfig;
import util.StructExcp;
import util.StructOption;

import java.sql.*;
import java.util.*;

/*
 * This program implements the proxy design pattern for the database
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


public class DatabaseHandler
{
    private BuildPizzaConfig buildPizza = new BuildPizzaConfig();
    private DbConnector db = new DbConnector();


    /*This method shall configure the pizzeria through a file*/
    public synchronized boolean configurePizzeria(Properties properties)
    {
        PizzaConfig pizzaConfig = buildPizza.buildPizzaProperty(properties);

        if (pizzaConfig != null)
        {
            String pizzaname = pizzaConfig.getPizzaName();
            Double bPrice = pizzaConfig.getBasePrice();
            ArrayList<String> listSet = (ArrayList) pizzaConfig.getOptionSets();

            /*Inserting pizza name in the database*/
            if(!insertPizza(pizzaname, bPrice))
                return false;

            for (String optset : listSet)
            {
                /*Inserting the optionset in the database*/
                addAnOptionset(pizzaConfig.getPizzaName(), optset);
                ArrayList<StructOption> optionList = pizzaConfig.getListOption(optset);

                for (StructOption structOption : optionList)
                {
                    /*Inserting the options in the database*/
                    addAnOption(pizzaConfig.getPizzaName(), optset, structOption.getName(), structOption.getPrice());
                }
            }
            return true;
        }
        return false;
    }


    /*This method insert the pizza in the database*/
    public boolean insertPizza(String name, double basePrice)
    {
        String sql = "INSERT INTO PizzaConfig(pizzaName,basePrice) VALUES(?,?)";

        try (Connection conn = db.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, basePrice);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e)
        {
        }
        return false;
    }

    /*This method inserts optionset in the database*/
    public void addAnOptionset(String pizzaname, String optionset)
    {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        int foreignKey = 0;

        /*My queries which will help me to insert in the database*/
        String sql = "INSERT INTO Optionset(optionsetName, fgnKeyPizza) VALUES(?,?)";
        String query = "SELECT pizzaID FROM PizzaConfig WHERE pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of optionsets*/
        try (Connection conn = db.connect())
        {

            preparedStatement1 = conn.prepareStatement(query);
            preparedStatement1.setString(1, pizzaname);

            ResultSet rows = preparedStatement1.executeQuery();

            if (rows.next())
                foreignKey = rows.getInt(1);

            /*Insertion per se happens here*/
            if (foreignKey != 0)
            {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, optionset);
                preparedStatement.setInt(2, foreignKey);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e)
        {
        }
    }

    /*This method inserts options in the database*/
    public void addAnOption(String pizzaname, String optionset, String optionName, double optionPrice)
    {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        int foreignKey = 0;

        /*My queries which will help me to insert in the database*/
        String sql = "INSERT INTO OptionsPizza(optionName, optionPrice, opsetID) VALUES(?,?,?)";

        String query = "SELECT * FROM Optionset\n" +
                "INNER JOIN PizzaConfig\n" +
                "ON PizzaConfig.pizzaID = Optionset.fgnKeyPizza\n" +
                "WHERE Optionset.optionsetName = ? AND PizzaConfig.pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement1 = conn.prepareStatement(query);
            preparedStatement1.setString(1, optionset);
            preparedStatement1.setString(2, pizzaname);


            ResultSet rows = preparedStatement1.executeQuery();

            if (rows.next())
                foreignKey = rows.getInt(1);

            /*Insertion per se happens here*/
            if (foreignKey != 0)
            {
                preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, optionName);
                preparedStatement.setDouble(2, optionPrice);
                preparedStatement.setInt(3, foreignKey);
                preparedStatement.executeUpdate();
            }

        } catch (SQLException e)
        {
        }
    }

    /*This method print the pizzeria*/
    public void printPizzeria(String pizzeriaName)
    {
        PizzaConfig pizzaConfig = retrivePizza(pizzeriaName);
        pizzaConfig.printConfig();
    }


    /* This method update optionset name*/
    public synchronized void updateOptionSetName(String pizzeriaName, String optionSetName, String newOptionSetName)
    {
        PizzaConfig pizzaConfig = new PizzaConfig();
        List<String> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        int foreignKeyPizza = 0;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "SELECT * FROM PizzaConfig  WHERE pizzaName = ? ";
        String sqlOptionset = "UPDATE Optionset SET optionSetName = ? WHERE fgnKeyPizza = ? AND optionsetName = ?";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setString(1, pizzeriaName);

            ResultSet rows = preparedStatement.executeQuery();

            if (rows.next())
            {
                foreignKeyPizza = rows.getInt(1);
            }

            /*Retrieving the optionset name*/
            preparedStatement = conn.prepareStatement(sqlOptionset);
            preparedStatement.setString(1, newOptionSetName);
            preparedStatement.setInt(2, foreignKeyPizza);
            preparedStatement.setString(3, optionSetName);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /*This method update the base price*/
    public synchronized void updateBasePrice(String pizzeriaName, double newBasePrice)
    {
        PreparedStatement preparedStatement = null;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "UPDATE PizzaConfig SET basePrice = ? WHERE pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setDouble(1, newBasePrice);
            preparedStatement.setString(2, pizzeriaName);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
        }
    }

    /*This method updates the options*/
    public synchronized void optionUpdater(String pizzeriaName, String optionSetName, String optionName,
                                           String newoptionName, double newOptionPrice)
    {
        PizzaConfig pizzaConfig = new PizzaConfig();
        List<String> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        int optsetKey = 0;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "UPDATE OptionsPizza SET optionName = ?, optionPrice = ?  WHERE opsetID = ?  AND optionName = ? ";

        String query = "SELECT * FROM Optionset\n" +
                "INNER JOIN PizzaConfig\n" +
                "ON PizzaConfig.pizzaID = Optionset.fgnKeyPizza\n" +
                "WHERE Optionset.optionsetName = ? AND PizzaConfig.pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1, optionSetName);
            preparedStatement.setString(2, pizzeriaName);

            ResultSet rows = preparedStatement.executeQuery();

            if (rows.next())
            {
                optsetKey = rows.getInt(1);
                String str = rows.getString(2);

                System.out.println(optsetKey + "           " + str);
            }

            /*Retrieving the optionset name*/
            preparedStatement1 = conn.prepareStatement(sqlPizza);
            preparedStatement1.setString(1, newoptionName);
            preparedStatement1.setDouble(2, newOptionPrice);
            preparedStatement1.setInt(3, optsetKey);
            preparedStatement1.setString(4, optionName);


            preparedStatement1.executeUpdate();

        } catch (SQLException e)
        {
        }
    }

    /* This method updates the pizzeria name*/
    public synchronized void updatePizzeriaName(String pizzeriaName, String newPizzeriaName)
    {
        PreparedStatement preparedStatement = null;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "UPDATE PizzaConfig SET pizzaName = ? WHERE pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setString(1, newPizzeriaName);
            preparedStatement.setString(2, pizzeriaName);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }


    /*This method checks for duplicates in the linkedhashmap of a given pizzeria*/
    public synchronized void printPizzerias()
    {
        String pizzeriaName = "";
        PreparedStatement preparedStatement = null;
        String sqlPizza = "SELECT * FROM PizzaConfig";

        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            ResultSet rows = preparedStatement.executeQuery();

            while (rows.next())
            {
                pizzeriaName = rows.getString(2);
                printPizzeria(pizzeriaName);
            }
        } catch (SQLException e)
        {
        }
    }

    /*Removes a configuration from a pizzeria*/
    public synchronized void deleteOptionSet(String pizzeriaName, String optionset)
    {
        PizzaConfig pizzaConfig = new PizzaConfig();
        List<String> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;

        int foreignKeyPizza = 0;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "SELECT * FROM PizzaConfig  WHERE pizzaName = ? ";
        String sqlOptionset = "DELETE FROM Optionset WHERE fgnKeyPizza = ? AND optionsetName = ?";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setString(1, pizzeriaName);

            ResultSet rows = preparedStatement.executeQuery();

            if (rows.next())
            {
                foreignKeyPizza = rows.getInt(1);
            }

            /*Retrieving the optionset name*/
            preparedStatement = conn.prepareStatement(sqlOptionset);
            preparedStatement.setInt(1, foreignKeyPizza);
            preparedStatement.setString(2, optionset);

            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
        }
    }


    /*Return the list of Pizza config*/
    public List<String> pizzaList()
    {
        List<String> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        String pizzaName = null;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "SELECT * FROM PizzaConfig";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            ResultSet rows = preparedStatement.executeQuery();

            while (rows.next())
            {
                pizzaName = rows.getString(2);
                list.add(pizzaName);
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        return list;
    }

    public PizzaConfig retrivePizza(String pizzeriaName)
    {
        PizzaConfig pizzaConfig = new PizzaConfig();

        PreparedStatement preparedStatement = null;
        String pizzaName = null;
        String optionSet;
        double bPrice;
        int foreignKeyPizza = 0;
        int foreignKeyOptSet = 0;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "SELECT * FROM PizzaConfig  WHERE pizzaName = ? ";
        String sqlOptionset = "SELECT * FROM Optionset WHERE fgnKeyPizza = ? ";
        String sqlOptionsPizza = "SELECT * FROM OptionsPizza WHERE opsetID = ? ";



        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setString(1, pizzeriaName);

            ResultSet rows = preparedStatement.executeQuery();

            if (rows.next())
            {
                foreignKeyPizza = rows.getInt(1);
                pizzaName = rows.getString(2);
                bPrice = rows.getDouble(3);

                pizzaConfig.setPizzaName(pizzaName);
                pizzaConfig.setBasePrice(bPrice);
            }

            /*Retrieving the optionset name*/
            preparedStatement = conn.prepareStatement(sqlOptionset);
            preparedStatement.setInt(1, foreignKeyPizza);

            ResultSet rows1 = preparedStatement.executeQuery();

            while (rows1.next())
            {
                foreignKeyOptSet = rows1.getInt(1);
                optionSet = rows1.getString(2);
                pizzaConfig.addOptionSets(optionSet);

                preparedStatement = conn.prepareStatement(sqlOptionsPizza);
                preparedStatement.setInt(1, foreignKeyOptSet);

                ResultSet rows2 = preparedStatement.executeQuery();

                while (rows2.next())
                {
                    String optionName = rows2.getString(2);
                    double optionPrice = rows2.getDouble(3);
                    pizzaConfig.addOptionSetOption(optionSet, optionName, optionPrice);
                }
            }
        } catch (SQLException e)
        {
        } catch (PizzaException e)
        {
        }
        return pizzaConfig;
    }

    /*Removes a configuration from a pizzeria*/
    public synchronized void deletePizzeria(String pizzeriaName)
    {

        PreparedStatement preparedStatement = null;
        String pizzaName = null;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "DELETE FROM PizzaConfig WHERE pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setString(1, pizzeriaName);
            preparedStatement.executeUpdate();

        } catch (SQLException e)
        {
        }
    }

    /*Return option set list*/
    public List<String> optionSetList(String pizzeriaName)
    {
        PizzaConfig pizzaConfig = new PizzaConfig();
        List<String> list = new ArrayList<>();

        PreparedStatement preparedStatement = null;
        String optionSet;

        int foreignKeyPizza = 0;

        /*My queries which will help me to insert in the database*/
        String sqlPizza = "SELECT * FROM PizzaConfig  WHERE pizzaName = ? ";
        String sqlOptionset = "SELECT * FROM Optionset WHERE fgnKeyPizza = ? ";

        /*Connecting with the database and procceeding with insertion of options*/
        try (Connection conn = db.connect())
        {
            preparedStatement = conn.prepareStatement(sqlPizza);
            preparedStatement.setString(1, pizzeriaName);

            ResultSet rows = preparedStatement.executeQuery();

            if (rows.next())
            {
                foreignKeyPizza = rows.getInt(1);
            }

            /*Retrieving the optionset name*/
            preparedStatement = conn.prepareStatement(sqlOptionset);
            preparedStatement.setInt(1, foreignKeyPizza);

            ResultSet rows1 = preparedStatement.executeQuery();

            while (rows1.next())
            {
                optionSet = rows1.getString(2);
                list.add(optionSet);
            }
        } catch (SQLException e)
        {
        }
        return list;
    }
}
