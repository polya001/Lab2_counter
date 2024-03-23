import java.io.*;

/**

 * A class that organizes reading the file name, reading and writing to files, as well as counting the number of characters in the input file
 */
public class Functions {



     //Array of the number of Latin letters, including 26 uppercase letters and 26 lowercase letters
    private final int[] arr= new int[52];



    /**

     * Read the file name from the keyboard
     * @return filename if data input is correct
     */
    public static String GetFilename(){

        String FileName = null;
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
        try {
            FileName = bufferedReader1.readLine();


        }
        catch(IOException e) {
            System.out.println("Ошибка при вводе данных");
            System.out.println(e.getMessage());
        }
        return FileName;
    }

    /**

     * Reading an input file and counting the number of capital and small letters of the Latin alphabet
     *
     * @param fileName  input filename
     * @return <b>true</b> if the file is opened successfully
     */
    public boolean ReadFile(String fileName){

        try (FileReader file = new FileReader(fileName);
             BufferedReader buff = new BufferedReader(file)) {
            int el;
            while ((el = buff.read()) != -1) {

                //Counting capital letters of the Latin alphabet
                if (el>=65 && el<=90){
                    arr[el-65]+=1;
                }

                //Counting lowercase letters of the Latin alphabet
                else if (el>=97 && el<=122){
                    arr[el-97+26]+=1;
                }

            }
            return true;
        }
        catch (FileNotFoundException e){
            System.out.println("File not found "  + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("Error reading file " + e.getMessage());
        }
        return false;

    }

    /**

     * Recording in the output file the number of capital and lower case letters of the Latin alphabet and the total number of Latin letters from the input file
     * @param filename  output filename
     */
    public void WriteToFile(String filename){
        try(FileWriter file = new FileWriter(filename, false))
        {
            String text = "Number of Latin symbols\n";
            file.write(text);
            int n = arr.length;

            //Writing the number of capital letters of the Latin alphabet
            for (int i=0;i<26;i++){
                file.write((char)(i+65) + " - " + arr[i] + "\n");
            }

            //Writing the number of lowercase letters of the Latin alphabet
            for (int i=26;i<n;i++){
                file.write((char)(i-26+97) + " - " + arr[i]+ "\n");
            }

            //Counting the total number of letters of the Latin alphabet
            int sum = 0;
            for (int i = 0; i < n; i++) {
                sum += arr[i];
            }


            String NumberOfLetters = "\nTotal number of Latin letters in the input file:\n";
            file.write(NumberOfLetters);
            file.append(String.valueOf(sum));

        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

}
