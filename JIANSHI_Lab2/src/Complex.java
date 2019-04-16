/**
 * @auther Shiji Jiang
 */
public class Complex {

    private double rel;
    private double img;


    /**
     * Default Empty Constructor
     */
    public Complex() {
        this(0.0, 0.0);

    }

    /**
     * Contructor with real part only
     * @param rel
     */
    public Complex(double rel){
        this(rel, 0.0);
    }

    /**
     * Constructor with both real part and imaginary part
     * @param rel
     * @param img
     */
    public Complex(double rel, double img){
        this.rel = rel;
        this.img = img;
    }

    /**
     * Contructor based on a copy of another Complex Object
     * @param target Another Complex Object
     */
    public Complex(Complex target){
        this(target.rel, target.img);
    }

    /**
     * Return a String [rel, img]
     * @return
     */
    @Override
    public String toString(){
        return "[" + rel + ","+img+"]";
    }

    /**
     * Get Real Part
     * @return
     */
    public double getRel(){
        return rel;
    }

    /**
     * Get Imaginary Part
     * @return
     */
    public double getImg(){
        return img;
    }

    /**
     * Set Real Part
     * @return
     */
    public void setRel(double rel){
        this.rel = rel;
    }

    /**
     * Set Imaginary Part
     * @return
     */
    public void setImg(double img){
        this.img = img;
    }

    /**
     * Add another complex number to this complex number
     * @param target another complex object
     * @return
     */
    public void plus(Complex target){
        rel += target.getRel();
        img += target.getImg();
    }

    /**
     * Multiply another complex number to this complex number
     * @param target
     */
    public void mult(Complex target){
        double rel_new = rel * target.rel - img * target.img;
        img = rel * target.img + target.rel * img;
        rel = rel_new;
    }

    /**
     * Add two complex numbers and return the result as a new complex number
     * @param a
     * @param b
     * @return
     */
    public static Complex plus(Complex a, Complex b){
        return new Complex(a.rel + b.rel, a.img + b.img);
    }

    /**
     *
     * @param target
     * @return
     */
    @Override
    public boolean equals(Object target){
        if(target == null) {
            return false;
        }
        if(target == this ){
            return true;
        }
        if(!(target instanceof Complex)){
            return false;
        }
        Complex c = (Complex) target;
        if(c.img == img && c.rel == rel){
            return true;
        }
        return false;
    }

    /**
     *
     * @param objects
     * @return
     */
    public static boolean allEquals(Object[] objects){

        for (int i = 0 ; i < objects.length;i ++){

            if(objects[i] == null) {
                return false;
            }
            if(!(objects[i] instanceof  Complex)){
                return false;
            }
            Complex c = (Complex) objects[i];
            Complex first = (Complex) objects[0];
            if(c.img != first.img || c.rel != first.rel){
                return false;
            }
        }
        return true;
    }
}
