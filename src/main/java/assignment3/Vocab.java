package assignment3;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;


    public class Vocab extends Thread {

        static String GLOBAL_PATH ="src/InputFiles/";
        String file_name;
        TreeSet<String> vocabulary;

        //------------------------------------------------------- Constructor- ---------------------------------------------------------------------------------------------------
        public Vocab(String file_name) throws Exception {
            File fileName=new File(GLOBAL_PATH+file_name);
            if(fileName.exists())
            {
                this.file_name=file_name;
                super.setName(file_name);
            }
            else
                throw new FileNotFoundException(file_name+" does not exist in the directory "+GLOBAL_PATH);
        }

        synchronized  public TreeSet<String> getTree()
        {
            return this.vocabulary;
        }

        //Storing the content of Logic.Vocabulary.txt in a Tree
        synchronized public void create_Tree() throws Exception {
            Vector<String> Word_List =readFile();
            if(Word_List ==null)
            {
                throw new Exception("\t\t====>Tree Cannot Be Made. vocabulary.txt is empty");
            }
            if(Word_List !=null)
            {
                vocabulary =new TreeSet<>();
                for (String word:
                        Word_List) {

                    vocabulary.add(word);
                }
            }
        }

        private Vector<String> readFile() throws FileException
        {
            Vector<String> wordList=new Vector<>();
            FileReader read_vocabulary;
            try
            {
                read_vocabulary =new FileReader(GLOBAL_PATH+this.file_name);
                Scanner line =new Scanner(read_vocabulary);
                if(line.hasNext() ==false)
                {
                    throw new FileException("\n\n*The Logic.Vocabulary.txt File is Empty\n** Cannot Proceed With Empty File\n*** Please Check Logic.Vocabulary File ");
                }

                while (line.hasNext())
                {
                    String word=line.nextLine();
                    if(word !=" ")
                    {
                        wordList.add(word);
//                    System.out.println(word);
                    }
                }
                read_vocabulary.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("File Not Found!!");
                System.out.println("Kindly Check Your Logic.Vocabulary.txt");
                System.out.println("Logic.Vocabulary.txt is not Found");
                e.printStackTrace();
            }
            catch (IOException e)
            {
                System.out.println("IO Exception Thrown!!");
                System.out.println("Kindly Check Your Logic.Vocabulary.txt");
                System.out.println("Logic.Vocabulary.txt is not Found");
                e.printStackTrace();
            }
            catch (Exception E)
            {
                System.out.println(E);
                return null;
            }
            return wordList;
        }
        @Override
        public void run() {
            try {
//            System.out.println("Vocabulary : Thread is Started");
//            System.out.println(this.getState());
                create_Tree();
//            System.out.println(this.getState());
//            System.out.println("Vocabulary : Tree is Created");

//            System.out.println(vocabulary);
            } catch (Exception e) {
                System.out.println("Exception Thrown by Create Tree !! Possible Issue with the Vocabulary.txt");
                System.out.println(e);
            }
        }
    }



