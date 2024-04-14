package edu.lysak.patterns.state.earbuds;

class Idle implements State {

    @Override
    public void onTap(Earbud earbud) {
        earbud.setState(new Waiting());
    }

    @Override
    public void onDiscontinue(Earbud earbud) {
        System.out.println(earbud.isPlaying() ? "Playing" : "Paused");
    }
}
