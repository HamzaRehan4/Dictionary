package assignment3;

public class Main {
    static String GLOBAL_PATH ="src/InputFiles/";

    public static void main(String[] args) {
        System.out.println("\n**********************************************Welcome To The Dictionary**************************");

        System.out.println("Total Number of Files Are : "+args.length);
        System.out.print("\nThe Files Entered Are : \n");

        for (String fileName :
                args) {
            System.out.print(fileName+" ");
        }
        System.out.println();

        int total_threads= args.length;
        Vocab file_vocabulary=null;


        if ("vocabulary.txt".equals(args[0])) {
            try {
                file_vocabulary = new Vocab(args[0]);
                System.out.println("Thread Name is : " + file_vocabulary.getName());
                file_vocabulary.start();
//                System.out.println(file_vocabulary.getState());
                file_vocabulary.join();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        System.out.println("\n************************************************************");
        System.out.println("BST CONTAINS: ");
        System.out.print(file_vocabulary.getTree());
        System.out.println("\n************************************************************");
        //------------------------------------------------ Making Threads of Input Files Now -----------------------------------------------------------------------------------
        int no_input_threads=args.length-1;
        Files input_files[] =new Files[no_input_threads];
        for (int i = 0; i < no_input_threads; i++) {
            try
            {
                input_files[i] =new Files(args[i+1]);
            } catch (Exception e)
            {
                System.out.println("\nIssue Reading Input Files Call From main:Reading Input Files ");
            }
        }

//-------------------------- Starting Input Files Threads -----
        for (int i = 0; i < no_input_threads; i++) {
            input_files[i].start();
//            System.out.println("Thread Name : "+input_files[i].getName()+" Thread State : "+input_files[i].getState());
            try {
                input_files[i].join();
            } catch (Exception e) {
                System.out.println(e);
            }

        }


        System.out.println("Printing Content of All The Input Files");
        for (int i = 0; i < no_input_threads; i++) {
            System.out.println("Content of Input File : "+input_files[i].getName()+" is as follows:");
            int no_of_tokens=1;
            for (String token :
                    input_files[i].getWord_Tokens_In_File()) {
                System.out.println(no_of_tokens+" \t "+token);
                ++no_of_tokens;

            }
            System.out.println("\n************************************************************");
        }


    }
}

