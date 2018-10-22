package Test;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class SearchFileTest
{
    public static void main(String[] args) throws Exception
    {
        Path currentPath = Paths.get("");
        String s = currentPath.toAbsolutePath().toString();

        System.out.println("Current relative path is: " + s);
        System.out.println("Current relative path is: " + currentPath.toAbsolutePath().getParent().toString());


        Path path = currentPath.toAbsolutePath();
        System.out.println("-----" + path.toString());
        String p;

        File[] directories = new File(s).listFiles(File::isDirectory);
        System.out.println("--------Directories------------------" + Arrays.toString(directories));

        for (int i = 0; i < 3; i++)
        {
            File[] fileList = getFileList(s);
            if (fileList != null)
            {
                for (File file : fileList)
                {
                    System.out.println("#### File name: "+file.getName());
                }
            }
            path = currentPath.toAbsolutePath().getParent();
            s = path.toString();
        }
    }

    private static File[] getFileList(String path)
    {
        File dir = new File(path);
        System.out.println("---------Files---------$$$$$$--------" + Arrays.toString(dir.listFiles(File::isFile)));

        File[] fileList = dir.listFiles(new FilenameFilter()
        {
            public boolean accept(File dir, String name)
            {
                return name.equals("logs.txt");
            }
        });
        return fileList;
    }
}

