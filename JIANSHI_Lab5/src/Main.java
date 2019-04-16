import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;


public class Main {
    public static void main(String[] args){

        System.out.println(max(2,1,3));
        System.out.println(max(1,1,1));
        System.out.println(max(3,2,1));
        System.out.println(max(1,2,3));


        Integer[] a = {1,2,3};

        GenType<Integer> g1 = new GenType<>(6);
        GenType<Integer> g2 = new GenType<>(8);
        System.out.println("arrays of generic classes are not allowed in Java");
        //GenType<Integer>[] b = {g1,g2};

        String[] s = {"Hello","Bye"};

        System.out.println(ArrayToArrayList(a));
        //System.out.println(ArrayToArrayList(b));
        System.out.println(ArrayToArrayList(s));
        System.out.println(ArrayToArrayList(null));

        Stack<String> stack = new Stack<>(2);
        testPush("String Push", stack, s);
        testPop("String Pop", stack);


    }

    private static <T extends Comparable> T max(T a, T b, T c){
        if(a==null || b == null || c == null){
            throw new IllegalArgumentException("Null parameter not allowed");
        }
        T max = (a.compareTo(b) > 0) ? a : b;
        max = (max.compareTo(c) > 0)? max : c;
        return max;
    }

    private static <T> ArrayList<T> ArrayToArrayList(T[] array){
        if(array == null){
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(array));
    }

    private static <T> void testPush(String name, Stack<T> stack, T[] elements){
        System.out.println("Test Push "+name);
        for(T t : elements){
            stack.push(t);
        }
        try{
            stack.push(null);
            System.out.println("\nPush extra element");
        } catch (FullStackException e){
            System.out.println("Here it is! FullStackException");
        }

    }

    private static <T> void testPop(String name, Stack<T> stack){
        while(!stack.isEmpty()){
            stack.pop();
        }
        try{
            System.out.println("\nPop once more");
            stack.pop();
        } catch (EmptyStackException e){
            System.out.println("Here it is! EmptyStackException");
        }
    }
}
