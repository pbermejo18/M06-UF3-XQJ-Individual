import javax.xml.stream.XMLStreamException;
import javax.xml.xquery.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws XQException, XMLStreamException {
        Scanner scanner = new Scanner(System.in);
        GestorBD gestorBD = new GestorBD();

        System.out.println("****** Introdueix la id del departament: ******");
            String codigo = scanner.next();

        System.out.println("****** Departament sense empleats ******");
            System.out.println(gestorBD.getDeptSenseEmp(codigo));

        System.out.println("****** Departament amb empleats ******");
            System.out.println(gestorBD.getDeptAmbEmp(codigo));

        System.out.println("****** Insert departament d1881 ******");
            gestorBD.insertDept("d1881","PolDept","Barcelona");
            System.out.println(gestorBD.getDeptAmbEmp("d1881"));

        System.out.println("****** Remplazamos el departament d10 por el d20 ******");
            System.out.println("Antes: " + gestorBD.getDeptAmbEmp("d20"));
            gestorBD.replaceDept("d10","d20");
            System.out.println("Después: " + gestorBD.getDeptAmbEmp("d20"));

        System.out.println("****** Eliminem el departament d1881 ******");
            gestorBD.deleteDept("d1881");
            System.out.println(gestorBD.getDeptSenseEmp("d1881"));

        gestorBD.tancarSessió();
    }
}