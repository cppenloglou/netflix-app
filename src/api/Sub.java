package api;

import java.io.Serializable;
import java.util.Scanner;
public class Sub extends User implements Serializable {
    public Sub(String Name, String Sname, String Uname, String Pass) {
        super(Name, Sname, Uname, Pass);
        this.isAdmin = false;
        scan = new Scanner(System.in);
    }

}