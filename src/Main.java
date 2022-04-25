import javax.xml.stream.XMLStreamException;
import javax.xml.xquery.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws XQException, XMLStreamException {
        Scanner scanner = new Scanner(System.in);
        GestorBD gestorBD = new GestorBD();

        System.out.println("****** Introdueix la id del departament: ******");
        String codigo = scanner.next();
        System.out.println(gestorBD.getDeptSenseEmp(codigo));

        gestorBD.tancarSessió();
/*
        XQDataSource xqs = new ExistXQDataSource();
        xqs.setProperty("serverName", "192.168.249.137");
        xqs.setProperty("port", "8080");

        XQConnection conn = xqs.getConnection();

        XQPreparedExpression xqpe = conn.prepareExpression("declare variable $x as xs:string external; $x");
        xqpe.bindString( new QName("x"), "HelloWorld!", null);
        XQResultSequence rs = xqpe.executeQuery();
        while(rs.next())
            System.out.println( rs.getItemAsString(null));

        XQExpression expr = conn.createExpression();
        XQResultSequence result = expr.executeQuery("declare namespace foaf = 'http://xmlns.com/foaf/0.1/';"
                + "declare namespace rdf = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#';"
                + "for $c in /rdf:RDF/foaf:Person[@rdf:nodeID]" + "let $name:= $c/foaf:givenname/text()"
                + "let $surname:= $c/foaf:surname/text()" + "return" + "<alumne>{$name, ' ' , $surname}</alumne>");

        while (result.next()) {

            System.out.println("Resultat consulta: " + result.getItemAsString(null));
        }

        rs.close();
        xqpe.close();
        rs.close();
        expr.close();
        result.close();
        conn.close();
 */
    }
}