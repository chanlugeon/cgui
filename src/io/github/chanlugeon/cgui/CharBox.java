package io.github.chanlugeon.cgui;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CharBox implements Cloneable {
    private static final char BRICK_X = '─';
    private static final char BRICK_Y = '│';
    private static final char BRICK_CORNER = '+';
    private static final char BRICK_UPPER_LEFT = '┌';
    private static final char BRICK_UPPER_RIGHT = '┐';
    private static final char BRICK_UNDER_RIGHT = '┘';
    private static final char BRICK_UNDER_LEFT = '└';
    private static final char VERTICAL_LEFT = '┤';
    private static final char VERTICAL_RIGHT = '├';
    private static final char HORIZONTAL_DOWN = '┬';
    private static final char ARROW_RIGHTWARD = '→';
    private static final char WHITESPACE = ' ';
    private static final char NEWLINE = '\n';

    private static final Format FORMAT = new DecimalFormat("###,###");

    private final String title;
    private int width = 50;
    private final Map<String, String> map = new LinkedHashMap<>();

    public CharBox(final String title, final int width) {
        this(title);
        this.width = width;
    }

    public CharBox(final String title) {
        this.title = title;
        if (title.length() > width + 6) title.substring(0, width - 6);
    }

    @Override
    public CharBox clone() {
        return new CharBox(this);
    }

    private CharBox(final CharBox inst) {
        this.title = inst.title;
        this.width = inst.width;
        map.putAll(inst.map);
    }

    public CharBox data(String key, int value) {
        return data(key, Integer.toString(value));
    }

    public CharBox data(String key, String value) {
        map.put(key, value);
        return this;
    }

    public CharBox newline() {
        return line("");
    }

    public CharBox borderLine() {
        return line(String.format("%" + (width - 4) + "s", BRICK_X).replace(WHITESPACE, BRICK_X));
    }

    public CharBox line(String str) {
        map.put(str, "");
        return this;
    }

    public CharBox println() {
        print();
        System.out.println();
        return this;
    }

    public CharBox print() {
        System.out.println(buildAsString());

        return this;
    }

    public CharBox printTail() {
        final StringBuilder builder =  build();
        final int start = builder.length() - width + 2;

        builder.replace(start, start + 1, String.valueOf(HORIZONTAL_DOWN)).append(NEWLINE)
        .append(WHITESPACE).append(WHITESPACE).append(BRICK_UNDER_LEFT).append(BRICK_X).append('>');
        System.out.println(builder);

        return this;
    }

    public String buildAsString() {
        return build().toString();
    }

    public StringBuilder build() {
        final StringBuilder builder = new StringBuilder();
        builder.append(renderTop());
        builder.append(renderCenter());
        builder.append(renderBottom());

        return builder;
    }

    private StringBuilder renderTop() {
        final String wrappedTitle = wrapTitle();

        final StringBuilder builder = new StringBuilder(width);
        final int halfWidth = (int) Math.ceil((width - 2) / 2.0);
        final int left =  halfWidth - (int) Math.ceil((double) wrappedTitle.length() / 2);
        int right = wrappedTitle.length() % 2 == 0 ? left : left + 1;
        if (width % 2 > 0) right--;

        builder.append(BRICK_UPPER_LEFT);
        for (int i = 0; i < left; i++) builder.append(BRICK_X);
        builder.append(wrappedTitle);
        for (int i = 0; i < right; i++) builder.append(BRICK_X);
        builder.append(BRICK_UPPER_RIGHT)
        .append(NEWLINE);

        return builder;
    }

    private String wrapTitle() {
        return new StringBuilder()
                .append(VERTICAL_LEFT).append(WHITESPACE)
                .append(title.length() > width + 6 ? title.substring(0, width - 6) : title)
                .append(WHITESPACE).append(VERTICAL_RIGHT)
                .toString();
    }

    private StringBuilder renderCenter() {
        final List<String> content = renderContent();
        final StringBuilder builder = new StringBuilder((width + 1) * content.toString().length() - 1);
        for (final String line : content)
            builder.append(BRICK_Y).append(WHITESPACE)
            .append(line)
            .append(WHITESPACE).append(BRICK_Y).append(NEWLINE);

        return builder;
    }

    private List<String> renderContent() {


        final List<String> content = new LinkedList<>();
        final int halfLeft = (int) Math.ceil((width - 4) / 2.0);
        final int halfRight = width % 2 > 0 ? halfLeft - 1 : halfLeft;
        map.forEach((key, value) -> {
            if (value.length() > halfRight) value = value.substring(0, halfRight - 1) + ".";

            if (key.compareTo("") == 0) content.add(String.format("%" + (width - 4) + "s", value));
            else if (value.compareTo("") == 0) content.add(String.format("%-" + (width - 4) + "s", key));
            else {
                content.add(String.format("%-" + halfLeft + "s%" + halfRight + "s", key, value));
            }
        });;

        return content;
    }

    private StringBuilder renderBottom() {
        return new StringBuilder().append(BRICK_UNDER_LEFT)
                .append(String.format("%" + (width - 2) + "s", "").replace(' ', BRICK_X))
                .append(BRICK_UNDER_RIGHT);
    }

    public String title() {
        return title();
    }

    public CharBox setWidth(final int width) {
        this.width = width;
        return this;
    }

    public int getWidth() {
        return width;
    }
}
