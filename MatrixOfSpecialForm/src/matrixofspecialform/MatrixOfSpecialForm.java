/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package matrixofspecialform;

/**
 *
 * @author dev64
 */
public class MatrixOfSpecialForm {
    
    public static void specialMatrix(int rows, int columns) {
        
        int[][] a = new int[rows][columns];
        for (int i = 0; i < columns; i++) {
            if (i >= rows)
                break;
            a[i][i] = 1;
            a[i][columns - i - 1] = 1;
        }
        
        for (int[] row : a) {
            for (int i : row) {
                System.out.printf("%d ", i);
            }
            System.out.println();
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
        specialMatrix(4, 7);
        
    }
    
}
