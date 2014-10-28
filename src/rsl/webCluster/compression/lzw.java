package rsl.webCluster.compression;

import java.util.HashMap;

/**
 * Created by vignesh on 17/9/14.
 */
public class lzw {

    public static Integer[] getFingerPrint(String[] tokenSeq) {

        HashMap<String, Integer> dict = new HashMap<String, Integer>();
        int token_cnt = 0, dictEntryId = 1;
        String token;
        String buffer = "\0";
        Integer refPrefixId;
        Integer[] fingerPrint = new Integer[25];
        int cnt = 0;

        while (dict.size() < 25 && token_cnt < tokenSeq.length) {
            token = tokenSeq[token_cnt];
            if (dict.containsKey(token.concat(buffer))) {
                buffer = token.concat(buffer);
            } else {
                refPrefixId = dict.get(buffer);

                if (refPrefixId != null) {
                    fingerPrint[cnt] = refPrefixId;
                    cnt++;
                } else {
                    fingerPrint[cnt] = 0;
                    cnt++;
                }

                dict.put(token.concat(buffer), dictEntryId);
                dictEntryId = dictEntryId + 1;
                buffer = "\0";
            }
            token_cnt++;
        }

        return fingerPrint;
    }

}
