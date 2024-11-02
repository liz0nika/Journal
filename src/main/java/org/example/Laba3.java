package org.example;

import java.io.Console;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Laba3 {
    interface Drawable{
        void draw();
    }

    abstract static class Shape implements Drawable{
        String shapeColor;

        public Shape(String shapeColor)
        {
            this.shapeColor = shapeColor;
        }
        public String getShapeColor(){
            return shapeColor;
        }
        abstract public double calcArea();
        @Override
        public String toString(){
            return "Shape color:" + shapeColor;
        }

    }

    static class Rectangle extends Shape{
        double width;
        double height;
        public Rectangle(String shapeColor, double width, double height){
            super(shapeColor);
            this.width = width;
            this.height = height;
        }
        @Override
        public double calcArea() {
            return width * height;
        }

        @Override
        public void draw() {
            System.out.println("Drawing rectangle with color: " + shapeColor);
        }
        @Override
        public String toString() {
            return "Rectangle with color: " + shapeColor + ". Width: " + width + ". Height: " + height;
        }
    }
    static class Triangle extends Shape {
        double base, height;

        public Triangle(String shapeColor, double base, double height) {
            super(shapeColor);
            this.base = base;
            this.height = height;
        }

        @Override
        public double calcArea() {
            return (base * height) / 2;
        }

        @Override
        public void draw() {
            System.out.println("Drawing triangle with color: " + shapeColor);
        }

        @Override
        public String toString() {
            return "Triangle with color: " + shapeColor + ". Base: " + base + ". Height: " + height;
        }
    }
    static class Circle extends Shape{
        double radius;
        public Circle(String shapeColor, double radius){
            super(shapeColor);
            this.radius = radius;
        }
        @Override
        public double calcArea() {
            return Math.PI * radius * radius;
        }
        @Override
        public void draw() {
            System.out.println("Drawing circle with color: " + shapeColor);
        }
        @Override
        public String toString() {
            return "Circle with color: " + shapeColor + ". Radius: " + radius;
        }

    }
    class ShapeCollection{
        Shape[] shapes;
        public ShapeCollection(Shape[] shapes){
            this.shapes = shapes;
        }

//        public boolean size_more_10(int n){
//            if (n >= 10){
//                return true;
//            }
//            else{
//                System.out.println("Size less than 10");
//                return false;
//            }
//        }
        @Override
        public String toString(){
            double totalArea = 0;
            double totalRectangle = 0;
            double totalTriangle = 0;
            double totalCircle = 0;
            for (Shape shape : shapes){
                totalArea += shape.calcArea();
                if (shape instanceof Rectangle){
                    totalRectangle += shape.calcArea();
                }
                if (shape instanceof Triangle){
                    totalTriangle += shape.calcArea();
                }
                if (shape instanceof Circle){
                    totalCircle += shape.calcArea();
                }
            }
            return "Total area of all shapes: " +totalArea + ". " +
                    "\nTotal area of rectangles: " + totalRectangle + ". " +
                    "\nTotal area of triangles: " + totalTriangle + ". "  +
                    "\nTotal area of circles: " + totalCircle;
        }

        public String compareArea() {
            Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
            StringBuilder result = new StringBuilder("Shapes sorted by area:\n");
            for (Shape shape : shapes) {
                result.append(shape.toString()).append("\n");
            }
            return result.toString();
        }
        public String compareShapeColor() {
            Arrays.sort(shapes, Comparator.comparing(Shape::getShapeColor));
            StringBuilder result = new StringBuilder("Shapes sorted by color:\n");
            for (Shape shape : shapes) {
                result.append(shape.toString()).append("\n");
            }
            return result.toString();
        }
    }
    public static void main(String[] args) {
        Shape[] shapes = new Shape[0];
        String shapeColor = null;
        Laba3 laba3 = new Laba3();
        ShapeCollection shapeCollection = laba3.new ShapeCollection(shapes);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of shapes (not less than 10): ");
        int n = scanner.nextInt();
        scanner.nextLine();
        //if(shapeCollection.size_more_10(n)) {
        if (n>0){
            shapes = new Shape[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter shape type (rectangle, triangle, circle): ");
                String shapeType = scanner.nextLine();
                switch (shapeType) {
                    case "rectangle":
                        System.out.print("Enter width: ");
                        double width = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.print("Enter height: ");
                        double height = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.print("Enter shape color: ");
                        shapeColor = scanner.nextLine().toUpperCase();
                        //scanner.nextLine();
                        shapes[i] = new Rectangle(shapeColor, width, height);
                        break;
                    case "triangle":
                        System.out.print("Enter base: ");
                        double base = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.print("Enter height: ");
                        double height_triangle = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.print("Enter shape color: ");
                        shapeColor = scanner.nextLine().toUpperCase();
                        //scanner.nextLine();
                        shapes[i] = new Triangle(shapeColor, base, height_triangle);
                        break;
                    case "circle":
                        System.out.print("Enter radius: ");
                        double radius = scanner.nextDouble();
                        //scanner.nextLine();
                        System.out.print("Enter shape color: ");
                        shapeColor = scanner.nextLine().toUpperCase();
                        //scanner.nextLine();
                        shapes[i] = new Circle(shapeColor, radius);
                        break;
                    default:
                        System.out.println("Invalid shape type");
                        i--;
                        break;
                }
            }
            System.out.println(shapeCollection);

            System.out.println(shapeCollection.compareArea());

            System.out.println(shapeCollection.compareShapeColor());
        }
    }
}
