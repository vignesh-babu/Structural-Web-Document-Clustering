package rsl.webCluster;

import rsl.webCluster.docOps.webIndexer;
import rsl.webCluster.webDocument.WebDocument;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vignesh on 17/9/14.
 */
public class Main {
    public static void main(String[] args){

        //training doc
        File folder = new File("data/train");
        File[] listOfFiles = folder.listFiles();

        WebDocument[] webDocuments = new WebDocument[0];
        if (listOfFiles != null) {
            webDocuments = new WebDocument[listOfFiles.length];
        }

        for (int i = 0; i < listOfFiles.length; i++)
            webDocuments[i] = new WebDocument("data/train/" + listOfFiles[i].getName(),listOfFiles[i].getName());

        webIndexer.prefixTableGenerator(webDocuments);
        webIndexer.suffixTableGenerator(webDocuments);

        //test doc
        folder = new File("data/test");
        listOfFiles = folder.listFiles();

        webDocuments = new WebDocument[listOfFiles.length];
        ArrayList<WebDocument> results;
        for (int i = 0; i < listOfFiles.length; i++){

            webDocuments[i] = new WebDocument("data/test/" + listOfFiles[i].getName(),listOfFiles[i].getName());
            results = webIndexer.retrieveDocuments(webDocuments[i]);
            Iterator<WebDocument> it = results.iterator();
            System.out.println("test file : "+listOfFiles[i].getName());

            while (it.hasNext()){
                System.out.println(it.next().getFileName());
            }

        }
    }
}
