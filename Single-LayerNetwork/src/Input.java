import org.w3c.dom.ls.LSInput;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Input {

    public static List<DataRecord> getTrainList(String dirAddress){
        List<DataRecord> trainList = new ArrayList<>();

        try(DirectoryStream<Path> streamDirectories = Files.newDirectoryStream(Paths.get(dirAddress))){
            String lang = "";
            for (Path dir : streamDirectories){
                StringBuilder builder =new StringBuilder();
                if (Files.isDirectory(dir)){
                    lang = dir.getFileName().toString();
                    Stream<Path> files = Files.walk(dir);
                    for (Path file : files.filter(Files::isRegularFile).toList()){
                        BufferedReader reader = new BufferedReader(new FileReader(file.toFile()));
                        String line ="";

                        while ((line = reader.readLine()) != null){
                            builder.append(line.toLowerCase().replaceAll("[^a-zA-Z]",""));
                        }
                    }
                }

                List<Double> attributes = new ArrayList<>();
                for (int i='a'; i<='z'; i++){
                    char currChar = (char) i;
                    double count = builder.chars().filter(ch -> ch==currChar).count();
                    attributes.add(count/builder.length());
                }
                trainList.add(new DataRecord(attributes,lang));
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return trainList;
    }

    public static List<Double> getAttributesList(String text){
        text = text.toLowerCase();
        text = text.replaceAll("[^a-zA-Z]","");
        StringBuilder builder = new StringBuilder(text);
        List<Double> attributesList = new ArrayList<>();

        for (int i = 'a'; i < 'z'; i++) {
            char currChar = (char) i;
            double count = builder.chars().filter(ch -> (ch == currChar)).count();
            attributesList.add(count/builder.length());

        }
        return attributesList;
    }

    public static List<String> getLanguages(String address){
        return Arrays.asList(new File(address).list());
    }


}
