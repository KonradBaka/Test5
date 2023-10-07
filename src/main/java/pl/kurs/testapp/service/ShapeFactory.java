package pl.kurs.testapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.kurs.testapp.model.Circle;
import pl.kurs.testapp.model.Rectangle;
import pl.kurs.testapp.model.Shape;
import pl.kurs.testapp.model.Square;
import pl.kurs.testapp.serializators.ShapeDeserializer;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ShapeFactory {


    public Map<String, Shape> shapeCache;

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

    public Rectangle createRectangle(double width, double height) {
        String key = "rectangle" + width + height;
        if (!shapeCache.containsKey(key)) {
            shapeCache.put(key, new Rectangle(width, height));
        }
        return (Rectangle) shapeCache.get(key);
    }

    public static Optional<Shape> findShapeWithLargestArea(List<Shape> shapes) {
        return shapes.stream()
                .max(Comparator.comparingDouble(Shape::calculateArea));
    }

    public static Optional<Shape> findShapeWithLargestPerimeter(List<Shape> shapes, String type) {
        return shapes.stream()
                .filter(shape -> shape.getType().equalsIgnoreCase(type))
                .max(Comparator.comparingDouble(Shape::calculatePerimeter));
    }

    public static void exportShapesToJson(List<Shape> shapes, String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.writeValue(new File(filePath), shapes);
    }

    public static List<Shape> importShapesFromJson(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        objectMapper.registerModule(new SimpleModule().addDeserializer(Shape.class, new ShapeDeserializer()));

        return objectMapper.readValue(new File(filePath), objectMapper.getTypeFactory().constructCollectionType(List.class, Shape.class));
    }
}
