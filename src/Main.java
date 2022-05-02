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

        System.out.println(gestorBD.getDeptAmbEmp(codigo));

        gestorBD.insertDept("d1881","PolDept","Barcelona");
        System.out.println(gestorBD.getDeptSenseEmp("d1881"));

        gestorBD.deleteDept("d1881");
        System.out.println(gestorBD.getDeptSenseEmp("d1881"));
        gestorBD.tancarSessió();
    }
}