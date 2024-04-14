package edu.lysak.patterns.state.earbuds;

class Earbud {
    private boolean playing;
    private State state = new Idle();

    public void tap() {
        state.onTap(this);
    }

    public void doNothing() {
        state.onDiscontinue(this);
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }

    public void setState(State state) {
        this.state = state;
    }
}
