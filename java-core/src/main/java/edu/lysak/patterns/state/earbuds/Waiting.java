package edu.lysak.patterns.state.earbuds;

class Waiting implements State {

    @Override
    public void onTap(Earbud earbud) {
        earbud.setState(new OneTap());
    }

    @Override
    public void onDiscontinue(Earbud earbud) {
        earbud.setState(new Idle());
    }
}
