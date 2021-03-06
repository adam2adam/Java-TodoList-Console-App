import java.io.*;

/**
 * All of the file operations are here
 */

    public class FileOps {
    private File file;
    private String filePath = "ToDoListApp_File.txt";
    //public String strFileContent = "";

    /**
     * Create new file
     */
    public void CreateFile(){
        System.out.println("FileOps.CreateFile() method");

        try {

            file = new File(filePath);

            if (file.createNewFile()){
                System.out.println("File is created to: " + filePath);
            }else{
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check if file already exists or not
     * @return
     */
    public boolean CheckFileExists(){
        File f = new File(filePath);
        if(f.exists() && !f.isDirectory())
            return true;
        else
            return false;
    }
/*
    public String ReadFileNNNN(){
        //System.out.println("FileOps.ReadFile() method");
        String strFileContent = "";
        String file = "";
        FileReader fr = null;
        try {
            fr = new FileReader(filePath);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        BufferedReader bf = new BufferedReader(fr);


        try {
            while ((file = bf.readLine()) != null) {
                strFileContent = strFileContent + file + "\n";
            }
            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strFileContent;
    }
*/

    /**
     * Read file
     * @return
     */
    public String ReadFile(){
        //System.out.println("FileOps.ReadFile() method");
        String strFileContent = "";
        String file = "";
        FileReader fr = null;
        BufferedReader bf = null;
        try {
            fr = new FileReader(filePath);
            bf = new BufferedReader(fr);

            while ((file = bf.readLine()) != null) {
                strFileContent = strFileContent + file + "\n";
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally{
            if (fr != null){
                try{
                    fr.close();
                } catch (IOException e) {}
            }
            if (bf != null){
                try{
                    bf.close();
                } catch (IOException e) {}
            }
        }
        return strFileContent;
    }

    /**
     * Write to file
     * @param message
     */
    public void WriteFile(String message){
        //System.out.println("FileOps.WriteFile() method");
        BufferedWriter bw = null;

        try {
            // Append mode: false
            bw = new BufferedWriter(new FileWriter(filePath, false));
            bw.write(message);
            //bw.newLine();
            bw.flush();
            System.out.println("File saved");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {                       // always close the file
            if (bw != null) try {
                bw.close();
            } catch (IOException e2) {
                // just ignore it
            }
        } // end try/catch/finally
    }
/*
    public void SaveFile(){
        System.out.println("FileOps.SaveFile() method");
    }

    public void QuitFile(){
        System.out.println("FileOps.QuitFile() method");
    }
    */
}
