package Test;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Search
{
    private static String theFile = null;

    public static void main(String[] args) throws Exception
    {
        Path currentPath = Paths.get("");
        Path path = currentPath.toAbsolutePath();
//        System.out.println("---##--" + path.toString());
        String str = path.toString();
        System.out.println("---##--" + str);

        //========================================================================================
        System.out.println("========================================================================================");
        System.out.println("----------========>>>"+ Search.searchFile(path,"Pizzeria.txt", 0));
    }

    private static File[] getFileList(String path, String filename)
    {
        File dir = new File(path);

        File[] fileList = dir.listFiles(new FilenameFilter()
        {

            public boolean accept(File dir, String name)
            {
                return name.equals(filename);
            }
        });
        return fileList;
    }

    public static String searchFile(Path currentPath, String filename, int k)
    {
        while (currentPath.getParent() != null || theFile == null)
        {
            String s = currentPath.toAbsolutePath().toString();
            File[] directories = new File(s).listFiles(File::isDirectory);

            int numb = directories.length;
            for (int i = 0; i < numb; i++)
            {
                  File[] fileList = getFileList(directories[i].toString(), filename);

                if (fileList != null)
                {
                    for (File file : fileList)
                    {
                        theFile = file.getPath();
                        System.out.println(theFile);
                        return theFile;
                    }
                }
            }

            currentPath = currentPath.getParent();
            searchFile(currentPath, filename,k + 1);
            return theFile;
        }
        return theFile;
    }
}
