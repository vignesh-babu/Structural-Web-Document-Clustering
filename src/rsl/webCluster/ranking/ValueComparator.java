package rsl.webCluster.ranking;

/**
 * Created by vignesh on 27/10/14.
 */

import rsl.webCluster.webDocument.WebDocument;

import java.util.Comparator;
import java.util.Map;

class ValueComparator implements Comparator<WebDocument> {
    Map<WebDocument, Double> fBase;

    public ValueComparator(Map<WebDocument, Double> base) {
        fBase = base;
    }

    public int compare(WebDocument a, WebDocument b) {
        if (fBase.get(a) >= fBase.get(b)) {
            return -1;
        } else {
            return 1;
        }
    }
}