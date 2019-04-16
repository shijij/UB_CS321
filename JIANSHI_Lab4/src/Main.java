import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author DV
 *
 * IMPORTANT  -  READ ME
 *
 * To use the following testing main() method do the following:
 *
 * A)   Set all instance variables of your Sheep and Flock classes
 *      to package visibility.   i.e. NO visibility modifier
 *
 * B)   comment out your Main class and paste this Main class in its place
 *      INCLUDING the two Comparator classes
 *
 * C)   Run the main() method you see below
 *
 */

class reverseIDComparator implements Comparator<Sheep> {

    public int compare(Sheep ob1, Sheep ob2) {
        return ob2.id - ob1.id;
    }

}



class  NameComparator implements Comparator<Sheep> {

    public int compare(Sheep ob1, Sheep ob2) {
        return ob1.name.compareTo( ob2.name);
    }

}

public class Main {

    public static void main(String [] args) {

        String testOK = "OK";

        System.out.println( "----------------   Testing class Sheep");

        // test-01 non-default constructor

        Sheep[] stock = { new Sheep("s-1", 99),
                new Sheep("s-2", 100),
                new Sheep("s-2", 101)
        };

        boolean test_01 = "s-2".equals(stock[1].name) && stock[1].id == 100;

        testOK = test_01 ? "OK":"FAIL";
        System.out.println( " TEST-01:  non-default Constructor       [" + testOK + "]");

        if( ! test_01) System.out.println("   *** bad non-default Constructor");

        // test-02 non-default constructor test ------------------------------------------

        for(int k = 1; k < 300; k++)
            new Sheep();

        Sheep s_300 = new Sheep();


        boolean test_02 = s_300.name.equals("noname-300") &&
                (s_300.id == 300);
        //System.out.println(s_300.name);

        System.out.println(  " TEST-02:  default Constructor           ["
                + (test_02 ? "OK":"FAIL") + "]");
        if( ! test_02) System.out.println("             *** bad default Constructor");


        // test-03 toString() method test    ---------------------------------------

        String s_300_String = s_300.toString();

        boolean test_03 = s_300_String.contains("-300") &&
                s_300.toString().contains("noname");


        System.out.println(  " TEST-03:  toString() method             ["
                + (test_03 ? "OK":"FAIL") + "]");
        if( ! test_03) System.out.println("            *** bad toString() method");


        // test-03A equals() method test    ---------------------------------------

        Sheep a  = new Sheep("Adam",777);
        Sheep b  = new Sheep("Adam",777);

        boolean test_03A = a.equals(b) ;



        System.out.println(  " TEST-03A: equals()  method              ["
                + (test_03A ? "OK":"FAIL") + "]");
        if( ! test_03A) System.out.println("            *** bad equals()() method");




        // test-04 Flock class test    ---------------------------------------

        Flock flo = new Flock("Fluffy Flock");

        for( int k = 0; k < 5; k++ ) {
            flo.insert(new Sheep()) ;
        }


        boolean test_04 = (flo.num == 5) &&
                (flo.name.contains("Fluffy"));


        System.out.println(  " TEST-04:  Flock constructor & insert()  ["
                + (test_04 ? "OK":"FAIL") + "]");
        if( ! test_04) System.out.println("      *** bad Flock insert or contains()");



        // test-05 Flock change-pen test    ---------------------------------------

        flo.insert(new Sheep("Thomas", 888));
        flo.change_pen( new LinkedList());

        boolean test_05 = flo.contains(new Sheep("Thomas",888)) ;

        System.out.println(  " test-05:  Flock change-pen              ["
                + (test_05 ? "OK":"FAIL") + "]");

        if( ! test_05) System.out.println("            *** bad pen change. LOST SHEEP");


        // test-06 Flock class test    ---------------------------------------

        boolean test_06 = flo.toString().contains("Thomas") ;
        System.out.println(  " test-06:  Flock toString()              ["
                + (test_06 ? "OK":"FAIL") + "]");

        if( ! test_06) System.out.println("            *** bad flock.toString()");

        System.out.println("\nThe result of the Flock toString() :\n"+flo+"\n");


        // test-07 Flock class test    ---------------------------------------

        Iterator itr = flo.iterator();

        boolean test_07 = ( itr != null ) &&
                ( itr instanceof Iterator);
        System.out.println(  " test-07:  Flock iterator() method       ["
                + (test_07 ? "OK":"FAIL") + "]");

        if( ! test_07) System.out.println("            *** bad iterator() method()");


        // test-08 Flock iterator test    ---------------------------------------

        Iterator  itr2 = flo.iterator();
        // remove all the Sheep with even id
        while( itr2.hasNext()) {
            Object ob = itr2.next();
            if (  ((Sheep)ob).id % 2 == 0 ) itr2.remove();
        }

        boolean test_08 = flo.contains(new Sheep( "Thomas", 888));
        // it should not contain a Sheep with even ID
        System.out.println(  " test-08:  using Flock iterator()        ["
                + ( ! test_08 ? "OK":"FAIL") + "]");

        if(  test_08) System.out.println("            *** bad iterator() method()");
        System.out.println("\n the Flock after removal of even-numbered Sheep\n"
                + flo);

        // test-09 Flock sorting test    ---------------------------------------

        flo.insert( new Sheep("Graham", 543));
        flo.insert( new Sheep("Carol",  501));
        flo.insert( new Sheep("Adele",  985));
        flo.insert( new Sheep("Zeta",   72));

        // now sort them with a REVERSE ID comparator
        flo.sort( new reverseIDComparator());

        Object max = null;
        Object ob;
        // check if the Sheep are in descending order

        boolean test_09 = true;

        System.out.println("\n the Flock after SORTING in descending ID order\n"
                + flo);

        Iterator  it1 = flo.iterator();

        if ( it1.hasNext())  max = it1.next();

        // compare all the rest to the first one 
        // if even one is out of place, tet fails
        while(it1.hasNext()) {
            ob  = it1.next();
            if (  ((Sheep)ob).id  >  ((Sheep)max).id ) test_09 = false;
        }

        System.out.println(  "\n test-09:  testing reverse sort        ["
                + (  test_09 ? "OK":"FAIL") + "]");


        // test-10 Flock second sort test    ---------------------------------------

        //sort according to ascending NAME

        flo.sort( new NameComparator());

        System.out.println("\n the Flock after sorted in ascending NAME\n"
                + flo);

        Iterator it3 = flo.iterator();

        if( it3.hasNext()) max = it3.next();
        boolean test_10 = true;

        while(it1.hasNext()) {
            ob  = it1.next();
            if (  ((Sheep)ob).name.compareTo (((Sheep)max).name )  < 0 )
                test_09 = false;
        }
        System.out.println(  "\n test-10:  ascending name sort         ["
                + (test_10 ? "OK":"FAIL") + "]");

        if( ! test_10) System.out.println("            *** bad ascending name sort");


    }

}