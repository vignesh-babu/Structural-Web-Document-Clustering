package rsl.webCluster;

import rsl.webCluster.docOps.webIndexer;
import rsl.webCluster.ranking.Ranker;
import rsl.webCluster.webDocument.WebDocument;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by vignesh on 17/9/14.
 */
public class Main {
    public static void main(String[] args) {

        //training doc
        File folder = new File("data/train");
        File[] listOfFiles = folder.listFiles();

        WebDocument[] webDocuments = new WebDocument[0];
        if (listOfFiles != null) {
            webDocuments = new WebDocument[listOfFiles.length];
        }

        for (int i = 0; i < listOfFiles.length; i++)
            webDocuments[i] = new WebDocument("data/train/" + listOfFiles[i].getName(), listOfFiles[i].getName());

        webIndexer.prefixTableGenerator(webDocuments);
        webIndexer.suffixTableGenerator(webDocuments);

        //test doc
        folder = new File("data/test");
        listOfFiles = folder.listFiles();

        webDocuments = new WebDocument[listOfFiles.length];
        ArrayList<WebDocument> results;
        for (int i = 0; i < listOfFiles.length; i++) {

            webDocuments[i] = new WebDocument("data/test/" + listOfFiles[i].getName(), listOfFiles[i].getName());
            results = webIndexer.retrieveDocuments(webDocuments[i]);
            SortedMap<WebDocument, Double> result = Ranker.GetOrdered(results, webDocuments[i]);

            System.out.println("test file : " + listOfFiles[i].getName());

            for (Map.Entry<WebDocument, Double> e : result.entrySet()) {
                System.out.println(e.getKey().getFileName() + "\t:\t" + e.getValue().toString());
            }

        }
    }
}
