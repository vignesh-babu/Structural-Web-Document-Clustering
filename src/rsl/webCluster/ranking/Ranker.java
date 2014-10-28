package rsl.webCluster.ranking;

import rsl.webCluster.webDocument.WebDocument;

import java.util.*;

/**
 * Created by vignesh on 27/10/14.
 */
public class Ranker {

    public static SortedMap<WebDocument, Double> GetOrdered(ArrayList<WebDocument> docList, WebDocument query) {

        HashMap<WebDocument, Double> map = new HashMap<WebDocument, Double>();
        SortedMap<WebDocument, Double> result = new TreeMap<WebDocument, Double>(new ValueComparator(map));

        for (WebDocument d : docList) {
            calcTfidf(d, docList, query);
        }
        calcTfidf(query, docList, query);
        for (WebDocument d : docList) {
            map.put(d, cosineSim(d, query));
        }
        result.putAll(map);
        return result;
    }

    public static void calcTfidf(WebDocument d, ArrayList<WebDocument> docList, WebDocument query) {

        for (String term : query.getTerms()) {
            int n = 0;
            for (WebDocument doc : docList) {
                if (doc.getFrequency(term) ==query.getFrequency(term) || Math.abs(doc.getFrequency(term) - query.getFrequency(term)) < 4 ) {
                    n++;
                }
            }
            double idf = 0;
            if (n != 0)
                idf = Math.log(docList.size() / n);
            double tfIdf = ((double) d.getFrequency(term) / (double) d.getfMax());

            tfIdf *= idf;
            d.setWeight(term, tfIdf * d.getFrequency(term));
        }

    }

    public static double cosineSim(WebDocument d1, WebDocument d2) {
        double n = 0, de1 = 0, de2 = 0;
        for (String t : d2.getTerms()) {
            n += d1.getWeight(t) * d2.getWeight(t);
            de1 += Math.pow(d1.getWeight(t), 2);
            de2 += Math.pow(d2.getWeight(t), 2);
        }

        if(n == 0)
            return 0;
        return n / (Math.sqrt(de1) * Math.sqrt(de2));
    }
}
