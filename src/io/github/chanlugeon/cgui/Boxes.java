package io.github.chanlugeon.cgui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Boxes {

    private int width;
    private final List<CharBox> items = new LinkedList<>();

    public Boxes(final int eachWidth) {
        this.width = eachWidth;
    }

    public Boxes println() {
        print();
        System.out.println();
        return this;
    }

    public Boxes print() {
        System.out.println(buildAsString());
        return this;
    }

    private String buildAsString() {
        return build().toString();
    }

    public StringBuilder build() {
        final List<List<String>> open = new ArrayList<>(items.size());

        int height = 0;
        items.forEach(box -> {
            List<String> list = new ArrayList<>();
            list.addAll(Arrays.asList(box.setWidth(width).buildAsString().split("\n")));
            open.add(list);
        });

        for (final List<String> o : open)
            if (height < o.size()) height = o.size();
        for (List<String> o : open)
            for (int i = o.size(); i < height; i++)
                o.add(String.format("%" + width + "s", ""));

        final String[] content = new String[height];
        Arrays.fill(content, "");

        for (int i = 0; i < items.size(); i++) {
            for (int j = 0; j < open.get(i).size(); j++) {
                content[j] += open.get(i).get(j) + (i < items.size() - 1 ? " " :
                    j < open.get(i).size() - 1 ? "\n" : "");
            }
        }

        final StringBuilder builder = new StringBuilder();
        for (final String line : content)
            builder.append(line);

        return builder;
    }

    public Boxes add(final CharBox box) {
        items.add(box);
        return this;
    }

    public Boxes setWidth(final int width) {
        this.width = width;
        return this;
    }

    public int getWidth() {
        return width;
    }
}
