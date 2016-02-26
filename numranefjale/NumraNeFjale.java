/*
 *
 */
package numranefjale;

import java.util.Scanner;

/**
 *
 * @author Husejn
 */
public class NumraNeFjale {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        long i = 0;
        NumraFjale nf = new NumraFjale();
        while ( true ){
            Scanner s = new Scanner(System.in);
            i = s.nextLong();
            System.err.println(nf.showWord(i));
        }
    }
    
}
