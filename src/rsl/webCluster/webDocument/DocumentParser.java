package rsl.webCluster.webDocument;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

/**
 * Created by vignesh on 17/9/14.
 * {PACKAGE_NAME}
 */
public class DocumentParser {

    public static String[] getTags(String docLocation) {

        Document doc = null;
        try {
            doc = Jsoup.parse(new File(docLocation), "UTF-8", "");
        } catch (IOException e) {
            System.err.println("File not found : check the path");
            e.printStackTrace();
        }

        NodeVisitorModifier nv = new NodeVisitorModifier();
        doc.traverse(nv);

        return nv.token.toArray(new String[nv.token.size()]);

    }

}
