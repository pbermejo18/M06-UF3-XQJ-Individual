import net.xqj.exist.ExistXQDataSource;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.xquery.*;
import java.util.ArrayList;
import java.util.List;

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
        return new Departament(codi,nom,localitat,null);
    }

    public Departament getDeptAmbEmp(String codi) throws XQException, XMLStreamException {
        Departament departament = getDeptSenseEmp(codi);
        List<Empleat> empleatsList = new ArrayList<>();

        XQPreparedExpression xqpe = conn.prepareExpression("declare variable $x as xs:string external; $x");
        xqpe.bindString( new QName("x"), "DepartamentAmbEmpleats", null);
        XQResultSequence rs = xqpe.executeQuery();
        while(rs.next())
            System.out.println( rs.getItemAsString(null));

        XQExpression expr = conn.createExpression();

        XQResultSequence resultempleats = expr.executeQuery(
                "for $c in collection(\"/db/empresa\")/empresa/empleats/emp[@dept=\""+codi+"\"]\n" +
                        "let $codi:= $c/@codi/string()\n" +
                        "let $cognom:= $c/cognom/text()\n" +
                        "let $ofici:= $c/ofici/text()\n" +
                        "let $dataAlta:= $c/dataAlta/text()\n" +
                        "let $salari:= $c/salari/text()\n" +
                        "let $comissio:= $c/comissio/text()\n" +
                        "let $cjefe:= $c/@cap/string()\n" +
                        "return\n" +
                        "<ff>{$codi},{$cognom},{$ofici},{$dataAlta},{$salari},{$comissio},{$cjefe}</ff>");
        while (resultempleats.next()) {
            XMLStreamReader xmlStreamReader3 = resultempleats.getItemAsStream();
            Empleat empleat = new Empleat(null,null,null,null,0.0f,0.0f,null);

            for (; xmlStreamReader3.hasNext(); xmlStreamReader3.next())
                if (xmlStreamReader3.getEventType() == XMLStreamConstants.CHARACTERS) {
                    String[] textElements = xmlStreamReader3.getText().split(",");

                    if (textElements[0] != null) empleat.setCodi(textElements[0]);
                    else empleat.setCodi(null);
                    if (textElements[1] != null) empleat.setCognom(textElements[1]);
                    else empleat.setCognom(null);
                    if (textElements[2] != null) empleat.setOfici(textElements[2]);
                    else empleat.setOfici(null);
                    if (textElements[3] != null) empleat.setDataAlta(textElements[3]);
                    else empleat.setDataAlta(null);
                    if (textElements[4] != null) empleat.setSalari(Float.parseFloat(textElements[4]));
                    else empleat.setSalari(0.0f);
                    if (textElements[5] != null && !textElements[5].isEmpty()) empleat.setComissio(Float.parseFloat(textElements[5]));
                    else empleat.setComissio(0.0f);
                    if (textElements[6] != null) empleat.setCodiJefe(textElements[6]);
                    else empleat.setCodiJefe(null);
                    empleatsList.add(empleat);
                }
        }
        /*
        for $c in collection("/db/empresa")/empresa/empleats/emp[@dept="d20"]
                        let $cognom:= $c/cognom/text()
                        let $ofici:= $c/ofici/text()
                        return
                        <ff>{$cognom},{$ofici}</ff>
         */

        departament.setEmpleatsList(empleatsList);
        return departament;
    }
}