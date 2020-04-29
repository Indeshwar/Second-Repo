class Complex1{
   private double real;
   private double imag;
   
   public Complex1(){}
   
   public Complex1(double real, double imag){
      this.real = real;
      this.imag = imag;
   }
   
   public double getReal(){
      return real;
   } 
   
   public void setReal(double real){
      this.real = real;
   }
   
   public double getImag(){
      return imag;
   }
   
   public void setImag(double real){
      this.real =real;
   }
   
    public double getAngle(){
     return Math.atan(this.imag/this.real);
     
   }
   
   public double getAngle2(){
      return this.getAngle()*180.0/Math.PI;     
   }
   
   public double pow(){
      return real*real + imag*imag;
      
   }
   
   public Complex1 conjugate(){
      return new Complex1(this.real, this.imag * -1);
      
   }
   
   public Complex1 add(Complex1 in){
      return new Complex1(this.real + in.real, this.imag + in.imag);
   }
   
   public Complex1 subtract(Complex1 in){
      return new Complex1(this.real - in.real, this.imag - in.imag);
   }
   
   public Complex1 multiply(Complex1 in){
      return new Complex1(this.real * in.real - this.imag * in. imag, this.real * in.imag + this.imag * this.real);
   }
   
   public Complex1 divide(Complex1 in){
      double dominator = in.pow();
      return new Complex1(this.multiply(in.conjugate()).real/dominator, this.multiply(in.conjugate()).imag/dominator);   
   }

   public void printComplex(){
      System.out.println("(" + this.real + ", " + this.imag + ")");
   }
   
   public static Complex1 nextComplex(){
      return new Complex1(Math.random()*2, Math.random()*2);
   }
   
   @Override
   public boolean equals(Object o){
      boolean b = false;
      if(o == null){
         b = false;
         
      }else if(this.getClass() != o.getClass()){
         b = false;
      }else{
         Complex1 c = (Complex1)o; //Down Casting
         if(this.real == c.real && this.imag == c.imag){
            b = true;
         }
      }
      return b;
   }
   
   @Override
   public String toString(){
      return "(R: " + real + ", I: " + imag + ")   ";
   }

}

class Matrix{
   private Complex1[][] value;// refrence variable of 2DArray of object from class Complex1;
   
   //This Constructor creates 3/3 Complex1 2DArray
   public Matrix(){
      value = new Complex1[3][3];
      int len = value.length;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            value[i][j] = new Complex1(); 
         }
      } 
   }
   
   //This constructor creates n/n complex1 2DArray
   public Matrix(int n){
      this.value = new Complex1[n][n];
      int len = value.length;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            value[i][j] = new Complex1(); 
         }
      } 

   }
   
   //This method return the numbers of rows in the array refrence by variable value
   public int getDim(){
      return this.value.length;
      
   }
   
   //This method return real part of this object as 2DArray of type double
   public double[][] getReal(){
      int len = value.length;
      double[][] twoDReal = new double[len][len];
      int k = 0;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            twoDReal[i][k] = value[i][j].getReal();
            k++;
         }
         k = 0;
      }
      return twoDReal;
   }
   
   //This method return imaginary part of this object as 2DArray of type double
   public double[][] getImag(){
      int len = value.length;
      double[][] twoDImag = new double[len][len];
      int k = 0;
      for(int i = 0; i < len; i++){
         for(int j = 1; j < value[i].length; j++ ){
            twoDImag[i][k] = value[i][j].getImag();
            k++;
         }
         k = 0;
      }
      return twoDImag;
   }
   
   //This metnod returns the column of 2DArray if the given index k is not out of bound
   public Complex1[] getCol(int k) throws IllegalArgumentException{
      int len = value.length;
      Complex1[] complex = new Complex1[len];
      if(k < 0 || k >= len){
         throw new IllegalArgumentException(k + " is invalid index");   
      }else{
          for(int i = 0; i < len; i++){
            complex[i] = new Complex1(value[i][k].getReal(), value[i][k].getImag());
         }

      }
      return complex;
   }
   
   //This metnod returns the row of 2DArray if the given index k is not out of bound
   public Complex1[] getRow(int k) throws IllegalArgumentException{
      int len = this.value.length;
      Complex1[] complex = new Complex1[len];
      if(k < 0 || k >= len){
         throw new IllegalArgumentException(k +" is invalid index");
         
      }else{
         for(int i = 0; i < len; i++){
            complex[i] = new Complex1(this.value[k][i].getReal(), this.value[k][i].getImag());;
         }
        
      }
      return complex;
   }
   
   //This method returns the diagnol elements of 2DArray refrenced by variable value
   public Complex1[] getDiag(){
      int len = value.length;
      Complex1[] complex = new Complex1[len]; 
      for(int i = 0; i < len; i++){
         complex[i] = value[i][i];
      }
      return complex;
   }
   
   //This method returns the power sum of elements of Complex1 2DArray refrence by the variable value
   public double pow(){
      int len = value.length;
      double powSum = 0;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            powSum += value[i][j].pow();//invoking the pow method from class Complex1
         }
      }
      return powSum;
   }
   
   //This method returns the refrence variable m of Matrix class
   //The returned refrence variable refers to two dimension array of object from class Complex1 that contains randomly generated real and imaginary numbers 
   public static Matrix randomMatrix(int size){
      Matrix m = new Matrix(size);
      int len = m.value.length;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
         
            //invoking nextComplex method to generate objects from class Complex1 whose real and imaginary numbers are generated randomly
            //and assign the generated objects in two dimension array refrence by the variable value 
            m.value[i][j] = Complex1.nextComplex();
         }
      }
      return m;  
   }
   
   //This method returs a refrence variable from class Matrix.
   //The returned refrence variable refers to the two dimension array resulting from adding this object to the taken object
   public Matrix add(Matrix m){
      int len = value.length;
      Matrix matrix = new Matrix(len);
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            matrix.value[i][j] = this.value[i][j].add(m.value[i][j]);//invoking add method from class Complex1 
         }
      }
      return matrix;
   }
   
   //This method display the Matrix Object
   public void display(){
      int len = this.value.length;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            System.out.print(value[i][j].toString());
         }
         System.out.println();
      }
      System.out.println();
      
   }
   
   public static void display(double[][] real){
      int len = real.length;
      for(int i = 0; i < len; i++){
         for(int j = 0; j < len; j++){
            System.out.print((real[i][j] + "  "));
         }
         System.out.println();
      }
      System.out.println();
      
   }
   
}

public class TestMatrix{
   public static void main(String[] args){
      System.out.println("--------1--------");
      Matrix m0 = new Matrix();
      m0.display();
      
      System.out.println("--------2--------");
      Matrix m1 = new Matrix(4);
      m1.display();
      
      System.out.println("--------3--------");
      Matrix m2 = Matrix.randomMatrix(5);
      Matrix m3 = Matrix.randomMatrix(5);
      m2.display();
      m3.display();
      
      System.out.println("--------4--------");
      double[][] real = m2.getReal();
      Matrix.display(real);
      double[][] imag = m2.getImag();
      Matrix.display(imag);
      
      System.out.println("--------5--------");
      int k = 1;
      try{
         Complex1[] col = m2.getCol(k);
         for(Complex1 a: col){
            a.printComplex();
            System.out.println();
         }
      }catch(IllegalArgumentException ex){
         System.out.println("Invalid column index");
      }
      System.out.println();
      
      System.out.println("--------6--------");
      int k2 = 6;
      try{
         Complex1[] row = m2.getRow(k2);
         for(Complex1 a: row){
            a.printComplex();
            System.out.println();
         }
         
      }catch(IllegalArgumentException ex){
         System.out.println("Invalid row index");

      }
      System.out.println();

      
      System.out.println("--------7--------");
      Complex1[] diag = m3.getDiag();
      for(Complex1 d: diag){
         d.printComplex();
         System.out.println();
      }
      System.out.println();
      
      System.out.println("--------8--------");
      System.out.println("The power of m2 is " + m2.pow() + "\n");
      
      System.out.println("--------9--------");
      Matrix m4 = m2.add(m3);
      m4.display();

   }
}