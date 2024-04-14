package edu.lysak.patterns.state.earbuds;

class TwoTaps implements State {

    @Override
    public void onTap(Earbud earbud) {
        earbud.setState(new Idle());
    }

    @Override
    public void onDiscontinue(Earbud earbud) {
        System.out.println("Fast forwarding");
        earbud.setState(new Idle());
    }
}
