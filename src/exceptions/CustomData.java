package exceptions;

import model.PizzaConfig;

import java.util.List;

public class CustomData
{
    private String fileName;
    private List<PizzaConfig> configList;

    public CustomData()
    {
        this.fileName = "";
        this.configList = null;
    }

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public List<PizzaConfig> getConfigList()
    {
        return configList;
    }

    public void setConfigList(List<PizzaConfig> configList)
    {
        this.configList = configList;
    }
}
