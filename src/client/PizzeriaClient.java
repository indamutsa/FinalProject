package client;

import model.PizzaConfig;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

public interface PizzeriaClient
{
    String uploadFile(String route);
    Object pizzeriaList();
    PizzaConfig retrievePizza(int n);
    String deleteApizzeria(int n);
    String updateBasePrice(int n,double money);
    String addAnOption(int pizzaName, int optionset,  String optionName, double price);
    Object streamTransporter(Object o);
    ArrayList<String> showOptionSets(int n);
    StringBuilder pizzaText(int n);

}
