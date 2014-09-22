package rsl.webCluster.webDocument;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeVisitor;

import java.util.ArrayList;

/**
 * Created by vignesh on 19/9/14.
 */
public class NodeVisitorModifier implements NodeVisitor {

    ArrayList<String> token = new ArrayList<String>();

    @Override
    public void head(Node node, int depth) {
        if (node instanceof Element && !(node instanceof org.jsoup.nodes.Document)) {
            token.add(node.nodeName());
        }
    }

    @Override
    public void tail(Node node, int depth) {
        if (node instanceof Element && !(node instanceof org.jsoup.nodes.Document)) {
            token.add(node.nodeName());
        }
    }
}