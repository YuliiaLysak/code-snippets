package edu.lysak.patterns.state.earbuds;

class OneTap implements State {

    @Override
    public void onTap(Earbud earbud) {
        earbud.setState(new TwoTaps());
    }

    @Override
    public void onDiscontinue(Earbud earbud) {
        if (earbud.isPlaying()) {
            System.out.println("Pausing");
            earbud.setPlaying(false);
        } else {
            System.out.println("Resuming");
            earbud.setPlaying(true);
        }
        earbud.setState(new Idle());
    }
}
