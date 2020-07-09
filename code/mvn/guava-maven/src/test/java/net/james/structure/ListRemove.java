package net.james.structure;

import com.sun.xml.internal.messaging.saaj.soap.impl.TextImpl;
import sun.net.www.protocol.http.logging.HttpLogFormatter;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.*;

public class ListRemove {

    private  static Logger logger = Logger.getLogger(ListRemove.class.getName());

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format","%4$s: %5$s [%1$tc]%n %N %n ");
//        System.setProperty("java.util.logging.SimpleFormatter.format","%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n");
        logger.info(System.getProperty("java.util.logging.SimpleFormatter.format"));
    }
    public static void main(String[] args){
       List<Node> nodes = new ArrayList<>();
        Node node1 = new TextImpl(null,"aa");
        Node node2 = new TextImpl(null,"bb");
        Node node3 = new TextImpl(null,"cc");
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        Iterator it = nodes.iterator();
        if(it.hasNext()){
            Node no = (Node) it.next();
            logger.info(no.toString());
            it.remove();
        }
        logger.info(nodes.toString());

        for(Node no : nodes){
            logger.info(no.toString());
            if("cc".equals(no.getValue())){
                nodes.remove(no);
            }
        }

    }
}
