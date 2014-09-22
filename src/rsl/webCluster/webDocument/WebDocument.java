package rsl.webCluster.webDocument;

import rsl.webCluster.compression.lzw;

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
    public WebDocument(String fileLocation,String name){
        splitLen = 10;
        fileName = name;
        prefix = "";
        suffix = "";
        tokens = DocumentParser.getTags(fileLocation);
        setFingerPrint(lzw.getFingerPrint(tokens));

    }

    public String getFileName() {
        return fileName;
    }

    public String[] getTokens() {
        return tokens;
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
        for( int i = 1 ; i < splitLen ; i++ ) {
            prefix = prefix.concat("," + fingerPrint[i].toString());
        }

    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix() {

        suffix = fingerPrint[splitLen+1].toString();
        for( int i = splitLen+2 ; i < fingerPrint.length ; i++ ) {
            suffix = suffix.concat("," + fingerPrint[i].toString());
        }

    }
}
