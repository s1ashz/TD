package com.talkdesk.exercise.prefixes;

import com.talkdesk.exercise.configuration.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PrefixesFileReader {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final String DEFAULT_FILE_NAME = "prefixes.txt";

    private InputStream defaultFileInputStream;

    private StoredPhoneNumberPrefixes phoneNumberPrefixes;

    @Autowired
    public PrefixesFileReader(StoredPhoneNumberPrefixes phoneNumberPrefixes) {
        this.phoneNumberPrefixes = phoneNumberPrefixes;
        loadDefaultFile();
    }

    private void loadDefaultFile() {
        ClassPathResource resource = new ClassPathResource(DEFAULT_FILE_NAME);
        try {
            defaultFileInputStream = resource.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromPrefixesFile() {
        LOGGER.info("Start reading prefixes.txt file...");
        Set<String> prefixes;

        try {
            prefixes = readFromPropertiesFile();
        } catch (FileNotFoundException e) {
            LOGGER.error( e.getMessage() );
            LOGGER.error(">File 'prefixes.txt' not provided, using default FILE on resources" );
            prefixes = readFromDefaultResourceFile();
        }
        phoneNumberPrefixes.setPrefixes(prefixes);
        LOGGER.info(">Finished reading prefixes file. Prefixes Found: " + phoneNumberPrefixes.getPrefixesSize() );
    }

    private Set<String> readFromPropertiesFile() throws FileNotFoundException {
        Set<String> prefixes = new HashSet<>();

        File f = new File(Constants.FileName.getValue());
        FileInputStream inputStream = new FileInputStream(f);
        readFromFileAndAddToSet(prefixes, inputStream);

        return prefixes;
    }

    private Set<String> readFromDefaultResourceFile() {
        LOGGER.info(">loading default prefix file");
        String fileName = "prefixes.txt";
        Set<String> prefixes = new HashSet<>();
        try {
            ClassPathResource resource = new ClassPathResource(fileName);
            InputStream inputStream = resource.getInputStream();
            readFromFileAndAddToSet(prefixes, inputStream);
            LOGGER.info("Default Prefix file Loaded....");
        } catch ( Exception e ) {
            LOGGER.info(">Default prefix file on resource has a problem" );
            e.printStackTrace();
        }
        return prefixes;
    }

    private void readFromFileAndAddToSet(Set<String> prefixes, InputStream inputStream) {
        Scanner sc = new Scanner(inputStream, "UTF-8");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            prefixes.add(line);
        }
    }

}
