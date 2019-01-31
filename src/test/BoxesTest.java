package test;

import org.junit.jupiter.api.Test;

import io.github.chanlugeon.cgui.Boxes;
import io.github.chanlugeon.cgui.CharBox;
import io.github.chanlugeon.cgui.init.Initializer;

public class BoxesTest {
    @Test
    void showOne() {
        Initializer.init();

        new CharBox("Example", 50)
        .data("Author", "Chan")
        .data("License", "MIT")
        .borderLine()
        .line("CGUI is a Java library.")
        .print();
    }

    @Test
    void showTwo() {
        Boxes boxes = new Boxes(60);

        CharBox first = new CharBox("First");
        first.data("Created by", "Chan");


        CharBox second = new CharBox("Second");
        second.data("Year", 2019)
        .data("Then", "They are happy.");
        boxes.add(second);
        boxes.add(first);

        boxes.print();
    }
}
