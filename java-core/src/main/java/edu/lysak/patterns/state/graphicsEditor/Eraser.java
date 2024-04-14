package edu.lysak.patterns.state.graphicsEditor;

class Eraser implements Tool {

    @Override
    public void onMouseUp() {
        System.out.println("Erased selected figures");
    }

    @Override
    public void onMouseDown() {
        System.out.println("Selecting figures to delete");
    }
}
