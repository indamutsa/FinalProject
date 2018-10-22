package testcases;

/*
 * This program will test our PizzaConfig class' getter methods
 *
 * @author Arsene INDAMUTSA
 *
 * Andrew ID: aindamut
 *
 * On my honor, as a Carnegie-Mellon Africa student, I have neither
 * given nor received unauthorized assistance on this work.
 */


import wrapper.PizzeriaConfigAPI;

public class PizzaTest5 extends PizzaTest
{
    @Override
    public void executeTest(PizzeriaConfigAPI pizzeriaConfigAPI)
    {
        pizzeriaConfigAPI.updateOptionSetName("Margeritta", "Meat choices","new Meatchoices");
        pizzeriaConfigAPI.optionUpdater("Margeritta","new Meatchoices","Pepperoni",
                "New pepperoni",1985);

        pizzeriaConfigAPI.updateOptionSetName("Quattro Stagioni", "Meat choices","new Meatchoices");
        pizzeriaConfigAPI.optionUpdater("Quattro Stagioni","new Meatchoices","Sausage",
                "New Sausage",2985);

        pizzeriaConfigAPI.updateOptionSetName("Napoletana", "Vegetable choices","new Vegetable choices");
        pizzeriaConfigAPI.optionUpdater("Napoletana","new Vegetable choices","oregano",
                "New oregano",2005);

        pizzeriaConfigAPI.updateOptionSetName("Carbonara", "Cheese choices","new Cheese choices");
        pizzeriaConfigAPI.optionUpdater("Carbonara","new Cheese choices","Cheddar",
                "New Cheddar",1325);
        pizzeriaConfigAPI.updateOptionPrice("Carbonara","new Cheese choices",
                "Regular",2000);

        pizzeriaConfigAPI.updateOptionSetName("Mozzarella", "Cheese choices","new Cheese choices");
        pizzeriaConfigAPI.optionUpdater("Mozzarella","new Cheese choices","Regular cheese",
                "Stracchino",1005);
   }
}

