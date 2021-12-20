package assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;

import static java.lang.System.exit;

    public class Files extends Thread {
        static String GLOBAL_PATH ="src/InputFiles/";
        Vector<String> Word_Tokens_In_File;
        private String file_name;
        int totalTokens;


        public Files(String file_name) throws Exception {
            File fileName = new File(GLOBAL_PATH + file_name);
            if (fileName.exists())
            {
                this.file_name=file_name;
                super.setName(file_name);
            }
            else
                throw new FileNotFoundException(file_name+" does not exist in the directory "+GLOBAL_PATH);
        }

        public String getFileName()
        {
            return this.file_name;
        }


        public Vector<String> getWord_Tokens_In_File() {
            return this.Word_Tokens_In_File;
        }


        private Vector<String> makeTokens() throws FileException {
            Vector<String> wordTokens=new Vector<>();
            try
            {
                FileReader readFile=new FileReader(GLOBAL_PATH+file_name);
                Scanner line =new Scanner(readFile);

                if(line.hasNext() ==false)
                {
                    throw new FileException("\n" +
                            "\n" +
                            "The Logic.Vocabulary.txt File is Empty ... \n" +
                            " Cannot Proceed With Empty File ..\n" +
                            " Please Check Logic.Vocabulary File ");
                }

                while (line.hasNext())
                {
                    String file_line= line.nextLine();
                    StringTokenizer line_tokens=new StringTokenizer(file_line," .//?");
                    while (line_tokens.hasMoreTokens())
                    {
                        wordTokens.add(line_tokens.nextToken());
                        ++totalTokens;
                    }
                }
                return wordTokens;
            }
            catch (FileNotFoundException e) {
                System.out.println("\nThe File : "+this.file_name+" don't exist in the Directory :Files\\ ");
                System.out.println("\nKindly Check That Your File "+this.file_name+" exist in the Files Directory");
                e.printStackTrace();
            }
            catch (Exception e)
            {
                System.out.println("\nThere is An Issue With Opening/Reading The File.. Kindly Check Your File "+this.file_name);
            }
            return null;
        }

        public void printWordTokens()
        {
            int x=1;
            for (String tokens :
                    this.Word_Tokens_In_File) {
                System.out.println(x+"\t"+tokens);
                x++;
            }
        }

        @Override
        public void run() {
            try {
                Word_Tokens_In_File =this.makeTokens();
            } catch (FileException e) {
                System.out.println(e);
            }

            if (Word_Tokens_In_File ==null)
            {
                System.out.println("\nThe Vector of Tokens is Null - Kindly Check Your Input File");
                exit(-1);
            }
        }
    }



