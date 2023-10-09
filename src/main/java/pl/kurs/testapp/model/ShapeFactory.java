package pl.kurs.testapp.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeFactory {


    private Map<String, Shape> shapeCache;

    public ShapeFactory() {
        this.shapeCache = new HashMap<>();
    }

    public Circle createCircle(double radius) {
        String key = "circle" + radius;
        if (!shapeCache.containsKey(key)) {
            shapeCache.put(key, new Circle(radius));
        }
        return (Circle) shapeCache.get(key);
    }

    public Square createSquare(double side) {
        String key = "square" + side;
        if (!shapeCache.containsKey(key)) {
            shapeCache.put(key, new Square(side));
        }
        return (Square) shapeCache.get(key);
    }

    public Rectangle createRectangle(double width, double height) { //  10, 122 = 10122 //101, 22 = 10122 - rozumiem
        String key = "rectangle" + width + " " + height;
        if (!shapeCache.containsKey(key)) {
            shapeCache.put(key, new Rectangle(width, height));
        }
        return (Rectangle) shapeCache.get(key);
    }
}


