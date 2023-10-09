package pl.kurs.testapp.app;

import pl.kurs.testapp.model.Circle;
import pl.kurs.testapp.model.Rectangle;
import pl.kurs.testapp.model.Shape;
import pl.kurs.testapp.model.Square;
import pl.kurs.testapp.model.ShapeFactory;
import pl.kurs.testapp.service.ShapeService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println();
        System.out.println();
        System.out.println("Zdałem test !");
        System.out.println();
        System.out.println();



        ShapeFactory shapeFactory = new ShapeFactory();

        List<Shape> shapes = new ArrayList<>();

        Square s1 = shapeFactory.createSquare(10.0);
        Square s2 = shapeFactory.createSquare(150.0);
        Rectangle r1 = shapeFactory.createRectangle(15.0, 16.0);
        Rectangle r2 = shapeFactory.createRectangle(25.0, 60.0);
        Circle c1 = shapeFactory.createCircle(16.0);
        Circle c2 = shapeFactory.createCircle(22.0);

        shapes.add(s1);
        shapes.add(s2);
        shapes.add(r1);
        shapes.add(r2);
        shapes.add(c1);
        shapes.add(c2);


        System.out.println("Figura z największym poelm: " + ShapeService.findShapeWithLargestArea(shapes));
        System.out.println("---------------------------------");
        System.out.println("Figura z najwikszym obwoedem: " + ShapeService.findShapeWithLargestPerimeter(shapes,
                "rectangle"));
        System.out.println("---------------------------------");


        try {
//            ShapeService.exportShapesToJson(shapes, "src/main/resources/newshapes.json");

            List<Shape> shapes2 = ShapeService.importShapesFromJson("src/main/resources/shapestomake.json");
            System.out.println(shapes2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}