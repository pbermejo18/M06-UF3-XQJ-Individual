import net.xqj.exist.ExistXQDataSource;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.*;

public class GestorBD {
    public XQConnection conn;

    public GestorBD() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "192.168.249.137");
        xqs.setProperty("port", "8080");
        conn = xqs.getConnection();
    }

    public void tancarSessió() throws XQException {
        conn.close();
    }

    public Departament getDeptSenseEmp(String codi) throws XQException, XMLStreamException {
        String nom = null;
        String localitat = null;

        XQPreparedExpression xqpe = conn.prepareExpression("declare variable $x as xs:string external; $x");
        xqpe.bindString( new QName("x"), "DepartamentSenseEmpleats", null);
        XQResultSequence rs = xqpe.executeQuery();
        while(rs.next())
            System.out.println( rs.getItemAsString(null));

        XQExpression expr = conn.createExpression();
        XQResultSequence resultNom = expr.executeQuery(
                "for $c in collection(\"/db/empresa\") " +
                        "let $name:= $c/empresa/departaments/dept[@codi="+"\""+codi+"\""+"]/nom/text()" +
                        "return" +
                        "<nom>{$name}</nom>");
        while (resultNom.next()) {
            XMLStreamReader xmlStreamReader = resultNom.getItemAsStream();
            for (; xmlStreamReader.hasNext(); xmlStreamReader.next())
                if (xmlStreamReader.getEventType() == XMLStreamConstants.CHARACTERS) nom = xmlStreamReader.getText();
        }

        XQResultSequence resultLocalitat = expr.executeQuery(
                "for $c in collection(\"/db/empresa\") " +
                        "let $localitat:= $c/empresa/departaments/dept[@codi="+"\""+codi+"\""+"]/localitat/text()" +
                        "return" +
                        "<localitat>{$localitat}</localitat>");
        while (resultLocalitat.next()) {
            XMLStreamReader xmlStreamReader2 = resultLocalitat.getItemAsStream();
            for (; xmlStreamReader2.hasNext(); xmlStreamReader2.next())
                if (xmlStreamReader2.getEventType() == XMLStreamConstants.CHARACTERS) localitat = xmlStreamReader2.getText();
        }
        return new Departament(codi,nom,localitat);
    }
}