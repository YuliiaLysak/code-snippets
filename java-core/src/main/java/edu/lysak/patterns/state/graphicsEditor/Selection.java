package edu.lysak.patterns.state.graphicsEditor;

class Selection implements Tool {

    @Override
    public void onMouseUp() {
        System.out.println("Finished selection");
    }

    @Override
    public void onMouseDown() {
        System.out.println("Started selection");
    }
}
