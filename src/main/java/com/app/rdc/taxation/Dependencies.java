package com.app.rdc.taxation;

import edu.mit.jwi.Dictionary;
import edu.mit.jwi.IDictionary;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Dependencies {

    private final static String WORDNET_LOCATION = File.separator + "resources" + File.separator + "wordnet" + File.separator + "dict";

    public static IDictionary loadDictionary() throws IOException {
        Path currentRelativePath = Paths.get("");
        String wordNetUrl = currentRelativePath.toAbsolutePath().toString() + WORDNET_LOCATION;

        URL url;
        url = new URL("file", null, wordNetUrl);

        IDictionary dictionary = new Dictionary(url);

        if(dictionary.open() == false)
            throw new IOException("Unable to open wordnet dictionary, check resource installation");

        return dictionary;
    }
}
