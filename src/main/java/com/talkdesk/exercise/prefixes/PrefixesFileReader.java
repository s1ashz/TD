package com.talkdesk.exercise.prefixes;

import com.talkdesk.exercise.configuration.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class PrefixesFileReader {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private StoredPhoneNumberPrefixes phoneNumberPrefixes;

    @Autowired
    public PrefixesFileReader(StoredPhoneNumberPrefixes phoneNumberPrefixes) {
        this.phoneNumberPrefixes = phoneNumberPrefixes;
    }

    public void readFromPrefixesFile() {
        LOGGER.info("Start reading prefixes.txt file...");
        File f = new File(Constants.FileName.getValue());
        Set<String> prefixes = new HashSet<>();

        try {
            FileInputStream inputStream = new FileInputStream(f);
            Scanner sc = new Scanner(inputStream, "UTF-8");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                prefixes.add(line);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }

        phoneNumberPrefixes.setPrefixes(prefixes);
        LOGGER.info(">Finished reading prefixes file. Prefixes Found: " + phoneNumberPrefixes.getPrefixesSize() );
    }

}
