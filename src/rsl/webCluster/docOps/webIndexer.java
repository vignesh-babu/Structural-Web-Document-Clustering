package rsl.webCluster.docOps;

import rsl.webCluster.webDocument.WebDocument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by vignesh on 19/9/14.
 */
public class webIndexer {

    private static Map<String,ArrayList<WebDocument>> prefixTable;
    private static Map<String,ArrayList<WebDocument>> suffixTable;

    public static void prefixTableGenerator(WebDocument[] webDocuments){

        prefixTable = new HashMap<String,ArrayList<WebDocument>>();

        for(WebDocument webDocument:webDocuments){

            ArrayList<WebDocument> wdl;
            if(prefixTable.containsKey(webDocument.getPrefix())){
                prefixTable.get(webDocument.getPrefix()).add(webDocument);
            }else{
                wdl = new ArrayList<WebDocument>();
                wdl.add(webDocument);
                prefixTable.put(webDocument.getPrefix(),wdl);
            }
        }

    }

    public static void suffixTableGenerator(WebDocument[] webDocuments){

        suffixTable = new HashMap<String,ArrayList<WebDocument>>();

        for(WebDocument webDocument:webDocuments){

            ArrayList<WebDocument> wdl;
            if(suffixTable.containsKey(webDocument.getSuffix())){
                suffixTable.get(webDocument.getSuffix()).add(webDocument);
            }else{
                wdl = new ArrayList<WebDocument>();
                wdl.add(webDocument);
                suffixTable.put(webDocument.getSuffix(),wdl);
            }
        }
    }

    public static ArrayList<WebDocument> retrieveDocuments(WebDocument webDocument){

        ArrayList<WebDocument> result = new ArrayList<WebDocument>();
        ArrayList<WebDocument> list = prefixTable.get(webDocument.getPrefix());
        Iterator it;
        WebDocument wd ;

        if(list != null){
            it = list.iterator();
            while(it.hasNext()){
                wd = (WebDocument)it.next();
                if(sed(wd.getSuffix(),webDocument.getSuffix())){
                    result.add(wd);
                }
            }
        }

        list = suffixTable.get(webDocument.getSuffix());

        if(list != null){
            it = list.iterator();

            while(it.hasNext()){
                wd = (WebDocument)it.next();
                if(sed(wd.getPrefix(),webDocument.getPrefix())){
                    result.add(wd);
                }
            }
        }
        return result;
    }

    public static boolean sed(String fp1,String fp2){
        Integer flag = 0;
        String[] w1 = fp1.split(",");
        String[] w2 = fp2.split(",");
        for(int i=0 ; i < w1.length ; i++ ){
                flag += Math.abs(Integer.parseInt(w1[i]) - Integer.parseInt(w2[i]));
        }

        return flag <= 1;
    }
}
