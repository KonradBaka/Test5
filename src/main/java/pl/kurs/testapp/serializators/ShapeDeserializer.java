package pl.kurs.testapp.serializators;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import pl.kurs.testapp.model.Circle;
import pl.kurs.testapp.model.Rectangle;
import pl.kurs.testapp.model.Shape;
import pl.kurs.testapp.model.Square;

import java.io.IOException;

public class ShapeDeserializer extends JsonDeserializer<Shape> {


    @Override
    public Shape deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String type = node.get("type").asText();

        switch (type) {
            case "circle":
                double radius = node.get("radius").asDouble();
                return new Circle(radius);
            case "square":
                double side = node.get("side").asDouble();
                return new Square(side);
            case "rectangle":
                double width = node.get("width").asDouble();
                double height = node.get("height").asDouble();
                return new Rectangle(width, height);
            default:
                throw new IOException("Unsupported shape type: " + type);
        }
    }
}

