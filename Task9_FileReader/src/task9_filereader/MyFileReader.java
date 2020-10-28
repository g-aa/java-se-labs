package task9_filereader;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MyFileReader {

    static public void fileVerification(File path) throws IOException {
        try (FileReader fr = new FileReader(path)) {
            BufferedReader br = new BufferedReader(fr);
            String buf;
            String regex = "!@#$%*()=_~<>?\'\";:|\\{}[]\t";
            String separator = " ";

            Map<String, Integer> mapWord = new HashMap<String, Integer>();
            StringBuilder allNumeric = new StringBuilder();
            while ((buf = br.readLine()) != null) {
                String[] lineItems = buf.replace(regex,separator).split(separator);
                for (String item : lineItems) {
                    if(tryParsNumeric(item)) {
                        allNumeric.append(item).append(separator);
                    } else {
                        if ((mapWord.containsKey(item))) {
                            mapWord.put(item, mapWord.get(item) + 1);
                        } else {
                            mapWord.put(item, 1);
                        }
                    }
                }
            }
            br.close();

            String sName = path.getName();
            int pos = sName.lastIndexOf(".");
            String fName = sName.substring(0, pos);
            String fExt = sName.substring(pos, sName.length());

            // write words:
            try(FileWriter fw = new FileWriter(path.getPath() + fName + "_words" + fExt)) {
                StringBuilder tempBuf = new StringBuilder();
                for (Map.Entry<String, Integer> item : mapWord.entrySet()) {
                    if (item.getValue() == 1) {
                        tempBuf.append(item.getKey()).append(separator);
                    }
                }
                fw.write(tempBuf.toString());
            }

            // write numeric:
            try(FileWriter fw = new FileWriter(path.getPath() + fName + "_numeric" + fExt)) {
                fw.write(allNumeric.toString());
            }
        }
    }

    static private boolean tryParsNumeric(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException | NullPointerException ignore) {
            return false;
        }
        return true;
    }
}
