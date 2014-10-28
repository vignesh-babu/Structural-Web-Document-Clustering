package rsl.webCluster.webDocument;

import rsl.webCluster.compression.lzw;

import java.util.HashMap;

/**
 * Created by vignesh on 17/9/14.
 */
public class WebDocument {

    private String fileName;
    private String[] tokens;
    private Integer[] fingerPrint;
    private String prefix;
    private String suffix;
    private Integer splitLen;
    private int fMax, fLen;
    private HashMap<String, Integer> fFreq;
    private HashMap<String, Double> fWeight;

    public WebDocument(String fileLocation, String name) {
        splitLen = 10;
        fileName = name;
        prefix = "";
        suffix = "";
        setTokens(DocumentParser.getTags(fileLocation));
        setFingerPrint(lzw.getFingerPrint(tokens));

    }

    public int getfMax() {
        return fMax;
    }

    public void setfMax(int fMax) {
        this.fMax = fMax;
    }

    public int getfLen() {
        return fLen;
    }

    public void setfLen(int fLen) {
        this.fLen = fLen;
    }

    public void setWeight(String term, double weight) {
        if (fWeight == null)
            fWeight = new HashMap<String, Double>();
        fWeight.put(term, weight);
    }

    public double getWeight(String term) {
        if (fWeight.get(term) == null)
            fWeight.put(term, (double) 0);
        return fWeight.get(term);
    }

    public String[] getTerms() {
        return fFreq.keySet().toArray(new String[fFreq.keySet().size()]);
    }

    public Integer getFrequency(String term) {
        if (fFreq.get(term) == null)
            fFreq.put(term, 0);
        return fFreq.get(term);
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getTokens() {
        return tokens;
    }

    public void setTokens(String[] tokens) {

        this.tokens = tokens;
        fFreq = new HashMap<String, Integer>();
        setfLen(0);
        fMax = 0;

        for (String term : tokens) {
            fLen++;
            if (!fFreq.containsKey(term))
                fFreq.put(term, 0);
            int freq = fFreq.get(term) + 1;
            fFreq.put(term, freq);
            if (freq > fMax)
                setfMax(freq);
        }
    }

    public Integer[] getFingerPrint() {
        return fingerPrint;
    }

    public void setFingerPrint(Integer[] fingerPrint) {

        this.fingerPrint = fingerPrint;
        setPrefix();
        setSuffix();
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix() {
        prefix = fingerPrint[0].toString();
        for (int i = 1; i < splitLen; i++) {
            prefix = prefix.concat("," + fingerPrint[i].toString());
        }

    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix() {

        suffix = fingerPrint[splitLen + 1].toString();
        for (int i = splitLen + 2; i < fingerPrint.length; i++) {
            suffix = suffix.concat("," + fingerPrint[i].toString());
        }

    }
}
