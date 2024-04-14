package edu.lysak.patterns.state.earbuds;

interface State {
    void onTap(Earbud earbud);

    void onDiscontinue(Earbud earbud);
}
