package db;

import exceptions.PizzaException;
import model.PizzaConfig;

import java.sql.*;

public class PizzeriaDatabase
{
    /*This function helps me connect with the database*/
    private Connection connect()
    {
        /*Mysql connection*/
        Connection conn = null;

        try
        {
            Class.forName("com.mysql.jdbc.Driver");

            String db = "jdbc:mysql://localhost:3306/HwFinal";
            conn = DriverManager.getConnection(db, "root", "Jehovah@1");

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /*This method insert the pizza in the database*/
    public void insertPizza(String name, double basePrice)
    {
        String sql = "INSERT INTO PizzaConfig(pizzaName,basePrice) VALUES(?,?)";

        try (Connection conn = this.connect();
             PreparedStatement preparedStatement = conn.prepareStatement(sql))
        {
            preparedStatement.setString(1, name);
            preparedStatement.setDouble(2, basePrice);
            preparedStatement.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

    /*This method inserts optionset in the database*/
    public void dbAddAnOptionset(String pizzaname, String optionset)
    {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        int foreignKey = 0;

        /*My queries which will help me to insert in the database*/
        String sql = "INSERT INTO Optionset(optionsetName, fgnKeyPizza) VALUES(?,?)";
        String query = "SELECT pizzaID FROM PizzaConfig WHERE pizzaName = ? ";

        /*Connecting with the database and procceeding with insertion of optionsets*/
        try (Connection conn = this.connect())
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
            System.out.println(e.getMessage());
        }
    }

    /*This method inserts options in the database*/
    public void dbAddAnOption(String pizzaname, String optionset, String optionName, double optionPrice)
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
        try (Connection conn = this.connect())
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
            System.out.println(e.getMessage());
        }
    }

    /*This method print the pizzeria*/
    public void dbPrintPizzeria(String pizzeriaName)
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
        try (Connection conn = this.connect())
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
                    pizzaConfig.addOptionSetOption(optionSet, optionName, optionPrice );
                }
            }
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch (PizzaException e)
        {
            System.out.println("PizzaException");
        }
        pizzaConfig.printConfig();
    }


    public static void main(String[] args)
    {
        PizzeriaDatabase database = new PizzeriaDatabase();
        database.dbPrintPizzeria("Napoletana");
    }

}

//       BuildPizzaConfig buildPizza = new BuildPizzaConfig();
//
//        /*Here we just uploading the file*/
//Properties props = new Properties();
//    SearchFile searchFile = new SearchFile();
//    Path currentPath = Paths.get("").toAbsolutePath();
//
//
//    String route = "Napoletana.properties";
//
//
//        if (route.matches("[\\d]+") || searchFile.searchFile(currentPath, route) == null)
//                {
//                System.out.println("File not found");
//                return;
//                }
//
//                String str = searchFile.searchFile(currentPath, route);
//
//                /*Reading the file route given*/
//                try
//                {
//                FileInputStream in = new FileInputStream(str);
//                props.load(in);
//                } catch (IOException e)
//                {
//                System.out.println(e.getMessage());
//                }
//
//                PizzaConfig pizzaConfig = buildPizza.buildPizzaProperty(props);
//                ArrayList<String> listSet = (ArrayList) pizzaConfig.getOptionSets();
//
//        System.out.println("\n");
//
//        String pizzaname = pizzaConfig.getPizzaName();
//        Double bPrice = pizzaConfig.getBasePrice();
//
//
//        PizzeriaDatabase app = new PizzeriaDatabase();
//
//        app.insertPizza(pizzaname, bPrice);
//
//        for (String optset : listSet)
//        {
//        app.dbAddAnOptionset(pizzaConfig.getPizzaName(), optset);
//
//        ArrayList<StructOption> optionList = pizzaConfig.getListOption(optset);
//
//        for (StructOption structOption : optionList)
//        {
//        app.dbAddAnOption(pizzaConfig.getPizzaName(), optset, structOption.getName(), structOption.getPrice());
//        }
//        }*/