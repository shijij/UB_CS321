public class Main {
    /**
     * The main() method used to test this class.
     *
     * THIS TESTING main() method
     * ASSUMES THAT THE static plus() and allEquals()
     * methods are MEMBERS OF CLASS COMPLEX
     *
     * YOU ARE NOT TO MODIFY THIS METHOD
     *
     * @param args command line arguments (not used).
     *
     */
    public static void main(String[] args) throws Exception {

        String testOK;

        Complex c0 = 	new Complex( );
        Complex c1 = 	new Complex( 5.0);
        Complex c2 = 	new Complex( 1.0, 3.0);
        Complex c3 = 	new Complex( c2);

        testOK = c0.getRel() == 0.0 ? "OK" : "FAIL";
        System.out.println(" Test-01: Default Constructor  [" + testOK + "]");

        testOK = c1.getRel() == 5.0 && c1.getImg() == 0.0     ? "OK" : "FAIL";
        System.out.println(" Test-02: One-arg Constructor  [" + testOK + "]");

        testOK = c2.getRel() == 1.0 && c2.getImg() == 3.0     ? "OK" : "FAIL";
        System.out.println(" Test-03: two-arg Constructor  [" + testOK + "]");

        testOK = c3.getRel() == 1.0 && c3.getImg() == 3.0     ? "OK" : "FAIL";
        System.out.println(" Test-04: copy    Constructor  [" + testOK + "]");

        testOK = "[1.0,3.0]".equals( c2.toString())   ? "OK" : "FAIL";
        System.out.println(" Test-05: toStringt            [" + testOK + "]");

        Complex c4 =    new Complex( );
        c4.setRel( 13.0);
        c4.setImg(17.0);;

        testOK =  c4.getRel() == 13.0 && c4.getImg()==17.0  ? "OK" : "FAIL";
        System.out.println(" Test-06: Setters              [" + testOK + "]");

        Complex c5 = new Complex( 7.0, 3.0);
        c5.plus(c4);
        testOK =  c5.getRel() == 20.0 && c5.getImg()==20.0  ? "OK" : "FAIL";
        System.out.println(" Test-07: non-static plus      [" + testOK + "]");


        Complex c6 = Complex.plus( c4, new Complex(-13, -17) );

        testOK =  c6.getRel() == 0.0 && c6.getImg()==0.0  ? "OK" : "FAIL";
        System.out.println(" Test-08: static plus          [" + testOK + "]");

        Object ob1 = new Complex( 9.0, 9.0);
        Object ob2 = new Complex( 9.0, 9.0);
        testOK =  ob1.equals(ob2)  ? "OK" : "FAIL";
        System.out.println(" Test-09: equals               [" + testOK + "]");


        Object [] A = { new Complex( 1.0, 3.0),
                new Complex( 1.0, 3.0),
                new Complex( 1.0, 3.0),
                new Complex( 1.0, 3.0)
        };

        testOK =  Complex.allEquals(A)  ? "OK" : "FAIL";
        System.out.println(" Test-10: AllEquals            [" + testOK + "]");


        Complex c7 = new Complex( 4.0, 7.0);
        c7.mult( new Complex( 2.0, 3.0));
        testOK =  c7.getRel() == -13.0  && c7.getImg() == 26.0 ? "OK" : "FAIL";
        System.out.println(" Test-11: mult                 [" + testOK + "]");

    }

}
