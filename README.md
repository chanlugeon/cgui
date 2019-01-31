# CGUI

<div align="center">
    <font size=10><b>CGUI</b></font>
</div>

CGUI is a Java library for printing prettier.

## Download
[cgui-1.jar][jar]

## Getting started
Call `Initializer.init()` once to prevent word breaks.  
CGUI uses special characters such as `┌`.

## Example
### Source
```
import io.github.chanlugeon.cgui.CharBox;
import io.github.chanlugeon.cgui.init.Initializer;

public class Example {
    public static void main(final String[] args) {
        Initializer.init();

        new CharBox("Example", 50)
        .data("Author", "Chan")
        .data("License", "MIT")
        .borderLine()
        .line("CGUI is a Java library.")
        .print();
    }
}
```

### Output
```
┌──────────────────┤ Example ├───────────────────┐
│ Author                                    Chan │
│ License                                    MIT │
│ ────────────────────────────────────────────── │
│ CGUI is a Java library.                        │
└────────────────────────────────────────────────┘
```

## What's new
*  **31/01/2019:** CGUI launched.



[jar]: https://raw.githubusercontent.com/chanlugeon/cgui/master/jar/cgui-1.jar