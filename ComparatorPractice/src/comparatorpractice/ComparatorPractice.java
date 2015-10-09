/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package comparatorpractice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author dev64
 */
public class ComparatorPractice {

    public static<T> void printList(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
        System.out.println();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Random rand = new Random();
        int maxRad = 200;
        int maxX = 500;
        int maxY = 500;
        
        List<Circle> list = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            Circle c = new Circle(maxX * rand.nextDouble(), 
                maxY * rand.nextDouble(),
                maxRad * rand.nextDouble());
            list.add(c);
        }
        printList(list);
        
        Collections.sort(list, Circle.xComparator);
        printList(list);
        
        Collections.sort(list, Circle.yComparator);
        printList(list);
     
        Collections.sort(list, Circle.radComparator);
        printList(list);   
    }
    
}
