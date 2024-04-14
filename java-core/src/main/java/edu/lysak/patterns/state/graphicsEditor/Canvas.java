package edu.lysak.patterns.state.graphicsEditor;

class Canvas {
    private Tool tool;
    private String color = "black";

    void mouseDown() {
        tool.onMouseDown();
    }

    void mouseUp() {
        tool.onMouseUp();
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public Tool getTool() {
        return tool;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
