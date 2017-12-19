package dom;
import java.io.File;



public class main {
    public static void main(String[] args) {

        File f= new File("StudentList.xml");
        dom d=new dom();
        d.createXMLByDOM(f);
    }
}

