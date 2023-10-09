package pl.kurs.testapp.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.kurs.testapp.model.Circle;
import pl.kurs.testapp.model.Rectangle;
import pl.kurs.testapp.model.Shape;
import pl.kurs.testapp.model.Square;
import pl.kurs.testapp.model.ShapeFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ShapeFactoryTest {

    private ShapeFactory shapeFactory;

    @BeforeEach
    public void setUp() {
        shapeFactory = new ShapeFactory();
    }

    @Test
    public void shouldReturnCircleWithRadius5() {
        Circle circle1 = shapeFactory.createCircle(5);
        Circle circle2 = shapeFactory.createCircle(5);

        assertSame(circle1, circle2);
        assertEquals(5.0, circle1.getRadius());
    }

    @Test
    public void shouldReturnSquareWithSide6() {
        Square square1 = shapeFactory.createSquare(6);
        Square square2 = shapeFactory.createSquare(6);

        assertSame(square1, square2);
        assertEquals(6.0, square1.getSide());
    }

    @Test
    public void shouldReturnRectangleWithWidth7AndHeight8() {
        Rectangle rectangle1 = shapeFactory.createRectangle(7, 8);
        Rectangle rectangle2 = shapeFactory.createRectangle(7, 8);

        assertSame(rectangle1, rectangle2);
        assertEquals(7.0, rectangle1.getWidth());
        assertEquals(8.0, rectangle1.getHeight());
    }

    @Test
    public void testCreateRectangleWithCache() {
        Rectangle rectangle1 = shapeFactory.createRectangle(7, 8);
        Rectangle rectangle2 = shapeFactory.createRectangle(7, 8);

        assertSame(rectangle1, rectangle2);
    }

    @Test
    public void testFindShapeWithLargestArea() {
        List<Shape> shapes = List.of(
                shapeFactory.createCircle(3),
                shapeFactory.createSquare(4),
                shapeFactory.createRectangle(5, 6)
        );

        Optional<Shape> largestAreaShape = ShapeService.findShapeWithLargestArea(shapes);

        assertTrue(largestAreaShape.isPresent());
        assertSame(shapes.get(2), largestAreaShape.get());
    }

    @Test
    public void testFindShapeWithLargestPerimeter() {
        List<Shape> shapes = List.of(
                shapeFactory.createCircle(3),
                shapeFactory.createSquare(4),
                shapeFactory.createRectangle(5, 6)
        );

        Optional<Shape> largestPerimeterShape = ShapeService.findShapeWithLargestPerimeter(shapes, "rectangle");

        assertTrue(largestPerimeterShape.isPresent());
        assertSame(shapes.get(2), largestPerimeterShape.get());
    }

    @Test
    public void testExportAndImportShapes() throws IOException {
        List<Shape> shapes = List.of(
                shapeFactory.createCircle(3),
                shapeFactory.createSquare(4),
                shapeFactory.createRectangle(5, 6)
        );

        String filePath = "shapes.json";

        ShapeService.exportShapesToJson(shapes, filePath);

        List<Shape> importedShapes = ShapeService.importShapesFromJson(filePath);

        assertEquals(shapes.size(), importedShapes.size());

        for (int i = 0; i < shapes.size(); i++) {
            assertEquals(shapes.get(i).getType(), importedShapes.get(i).getType());
        }
    }
}