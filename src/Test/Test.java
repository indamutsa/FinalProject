package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Test implements Serializable
{
    public static void main(String[] a)
    {
//        String[] arra = {null,null,"Arsene", null,"Ange", null, "Diane", null, null, "Jane", null, null, "Felicien", null, "Rebecca"};
//        rearrangeArray(arra);
//
//         String [] arrays = serialize(arra);
//
//        System.out.println("--------------------------------");
//        for (String o : arrays)
//            System.out.println(o);

        ArrayList<String> arsene = new ArrayList();



        arsene.add("Hello");
        arsene.add("world");


        arsene.add("view");
        arsene.add("serena");
        arsene.add("hotel");
        arsene.add("kigali");

        if(duplicateChecker("xbvsm.bvs", arsene))
        {
            System.out.println("Yes");
        }

        else
            System.out.println("No");

        deleteDuplicate("kigali", arsene);

        for(String arr : arsene)
            System.out.println(arr);
//
//        if(validateName(""))
//            System.out.println("Yes");
//        else
//            System.out.println("NO");


    }

    public static void rearrangeArray(Object[] arr)
    {
        int j = 0;
        for (int i = 0; i < arr.length - 1; i++)
        {
            if (arr[i] == null && arr[i + 1] != null)
            {
                arr[j] = arr[i + 1];
                arr[i + 1] = null;
                j++;
            }

            while(arr[j] != null)
                j++;



        }
    }

    public static String[] serialize(String arr[])
    {
        String [] arrays = new String[56];
        try
        {

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("A.dat"));
            out.writeObject(arr);
            out.close();
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("A.dat"));
            String [] newStaff = (String[]) in.readObject(); // Note: what is this line doing??
            arrays = newStaff;
        }
        catch(Exception e)
        {
            System.out.print("Error: " + e);
            System.exit(1);
        }
        return arrays;
    }

    public static boolean duplicateChecker( String str, List<? extends Object> check)
    {
        for(int i = check.size(); i > 0; i--)
        {
            if(check.get(i-1).equals( str))
                return true;
        }
        return false;
    }

    public static boolean validatePrice(Object price)
    {
        if(price instanceof Double || price instanceof Integer && (Integer) price > 0)
            return true;
        return false;
    }

    public static boolean validateName(Object name)
    {
        if(Pattern.matches("[a-zA-Z0-9]+", (String)name))
                return true;


//        if(name instanceof String && !((String) name).isEmpty())
//            return true;
        return false;
    }

    public static void deleteDuplicate( String str, List<? extends Object> duplicate)
    {
        for(int i = 0; i < duplicate.size(); i++)
        {
            if(str.equals((String) duplicate.get(i)))
                duplicate.remove(i);
        }
    }
    /*
    public boolean duplicateChecker(Map<String, PizzaConfig> check) throws PizzaException
    {
        Iterator it = configs.entrySet().iterator();
        while (it.hasNext())
        {
            Map.Entry map = (Map.Entry) it.next();
            PizzaConfig pizzaConfig = (PizzaConfig) map.getValue();
            String pizzeriaName = (String) map.getKey();
            if ((pizzaConfig.getPizzaNameIndex().toLowerCase().equals(pizzeriaName.toLowerCase())))
            {
                it.remove(); // WE are avoiding ConcurrentModificationException
                return true;
            }
        }
        return false;
    }*/
}
