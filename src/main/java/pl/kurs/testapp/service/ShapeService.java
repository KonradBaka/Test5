package pl.kurs.testapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import pl.kurs.testapp.model.Shape;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ShapeService {

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

        List<Shape> shapes = new ArrayList<>();
        JsonNode rootNode = objectMapper.readTree(new File(filePath));

        for (JsonNode node : rootNode) {
            Shape shape = objectMapper.treeToValue(node, Shape.class);
            shapes.add(shape);
        }

        return shapes;
    }
}

