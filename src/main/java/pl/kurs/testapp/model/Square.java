package pl.kurs.testapp.model;

public class Square implements Shape{
    private  double side;

    public Square() {
    }


    public Square(double side) {
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }

    @Override
    public String getType() {
        return "square";
    }

    public double getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "Square{" +
                "side=" + side +
                '}';
    }
}
