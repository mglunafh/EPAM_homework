/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mylistwithlastreference;

/**
 *
 * @author dev64
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        MyList<String> l = new MyList<>();
        
        l.add("Hey");
        l.add("there");
        l.add("how are you doing?");
        
        for (String str : l) {
            System.out.println(str);
        }
    }
    
}
