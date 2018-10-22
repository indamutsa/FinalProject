package util;
/**
 * This program searches the file
 *
 * @author Arsene INDAMUTSA
 * <p>
 * Andrew ID: aindamut
 * <p>
 * On my honor, as a Carnegie-Mellon Rwanda student, I have neither
 * given nor received unauthorized assistance on this work.
 */

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;

public class SearchFile
{
    private String theFile;

    public SearchFile()
    {
        this.theFile = null;
    }

    //retrieve the file of interest among other files
    public File[] getFileList(String path, String filename)
    {
        File dir = new File(path);

        //With a given directory, it can return files , or one file you are searching for
        File[] fileList = dir.listFiles(new FilenameFilter()
        {

            public boolean accept(File dir, String name)
            {
                return name.equals(filename);
            }
        });
        return fileList;
    }

    //With the above helb, this method recursively search the directories for a given file
    public String searchFile(Path currentPath, String filename)
    {
        //When it hasn't reached the root yet and the file hasn't been found yet, do the below
        while (currentPath != null || theFile != null)
        {
            String s = currentPath.toAbsolutePath().toString();
            File[] directories = new File(s).listFiles(File::isDirectory);

            if(directories == null)
                theFile = filename;
            //With a list of directories, it will search the given directories for a given file
            int numb = directories.length;
            for (int i = 0; i < numb; i++)
            {
                File[] fileList = getFileList(directories[i].toString(), filename);

                if (fileList != null)
                {
                    for (File file : fileList)
                    {
                        theFile = file.getPath();
//                        System.out.println("File found: "+theFile );
                        return theFile;
                    }
                }
            }
            //Go up one directory, and recursively the method calls itself
            currentPath = currentPath.getParent();
            searchFile(currentPath, filename);
            return theFile;
        }
        //If the file is found return it, otherwise return null
        return theFile;
    }
}
