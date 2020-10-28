package task9_filereader;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        File file = new File("D:\\MyRepository\\Java\\JavaSE\\Labs\\NetBeansProjects\\Data\\text_file_for_task9.txt");
        try {
            MyFileReader.fileVerification(file);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}