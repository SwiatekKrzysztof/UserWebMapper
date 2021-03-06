package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static Logger logger = LogManager.getLogger(FileService.class);
    public List<String> parseTextFile(String path) {
        List<String> linesList = new ArrayList<>();

        try(BufferedReader fileReader = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)){
            fileReader.lines().distinct().forEach(linesList::add);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return linesList;
    }
    public boolean isPathValid(String path){
        if(!new File(path).exists()){
            return false;
        }
        if(!new File(path).isFile()){
            return false;
        }
        return true;
    }
}
