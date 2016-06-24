/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testsoap;
import javax.xml.parsers.*;
import org.xml.sax.InputSource;
import org.w3c.dom.*;
import java.io.*;
/**
 *
 * @author Katawut
 */
public class TestSoap 
{
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        String get1;
        get1 = getOilPrice("T",(short)18, (short)6, (short)2016);
        //System.out.print(get1);
        try
        {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            InputSource is = new InputSource();
            is.setCharacterStream(new StringReader(get1));
            
            Document doc = db.parse(is);
            NodeList nodes = doc.getElementsByTagName("DataAccess");
            for (int i = 0; i < nodes.getLength(); i++)
            {
                Node nNode = nodes.item(i);
                
                Element eElement = (Element) nNode;
                String demo = eElement.getElementsByTagName("PRODUCT").item(0).getTextContent();
                if(demo.equals("NGV"))
                {
                    System.out.println("PRICE_DATE : " + eElement.getElementsByTagName("PRICE_DATE").item(0).getTextContent());
                    System.out.println("PRODUCT : " + eElement.getElementsByTagName("PRODUCT").item(0).getTextContent());
                    System.out.println("PRICE : " + eElement.getElementsByTagName("PRICE").item(0).getTextContent());
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String currentOilPrice(java.lang.String language) {
        testsoap.webservice.PTTInfo service = new testsoap.webservice.PTTInfo();
        testsoap.webservice.PTTInfoSoap port = service.getPTTInfoSoap();
        return port.currentOilPrice(language);
    }

    private static String getOilPrice(java.lang.String language, short dd, short mm, short yyyy) {
        testsoap.webservice.PTTInfo service = new testsoap.webservice.PTTInfo();
        testsoap.webservice.PTTInfoSoap port = service.getPTTInfoSoap();
        return port.getOilPrice(language, dd, mm, yyyy);
    }
    
}
