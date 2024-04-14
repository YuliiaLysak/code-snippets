package edu.lysak.patterns.state.graphicsEditor;

class Draw implements Tool {
    private final Canvas canvas;

    Draw(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public void onMouseUp() {
        System.out.printf("Finished drawing a %s line%n", canvas.getColor());
    }

    @Override
    public void onMouseDown() {
        System.out.printf("Drawing a %s line%n", canvas.getColor());
    }
}
