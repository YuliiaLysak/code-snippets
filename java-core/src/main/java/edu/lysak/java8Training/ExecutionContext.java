package edu.lysak.java8Training;

public class ExecutionContext {

//	static Run r = new Run();

    void execute(Runnable r) {
        System.out.println("excecute Runnable");
        r.run();
    }

    public void execute(Action a) {
        System.out.println("excecute Action");
        a.act();
    }

    public static void main(String[] args) {
        ExecutionContext ec = new ExecutionContext();
//		ec.execute(new Run());
//		ec.executeAsAction(new Act());
//
//		ec.execute(() -> System.out.println("Run"));
//		ec.executeAsAction(() -> System.out.println("Act"));

        Runnable run = (Runnable) () -> System.out.println("Run");
        Action act = (Action) () -> System.out.println("Act");
        ec.execute(run);
        ec.execute(act);

//        ec.execute((Runnable) act); // java.lang.ClassCastException
    }
}

@FunctionalInterface
interface Action {
    static String s = "Action";

    void act();
}

class Run implements Runnable {

    public Run() {
        System.out.println("Runnable Constructor " + this);
    }

    @Override
    public void run() {
        System.out.println("Runnable " + this);
    }

}

class Act implements Action {

    public Act() {
        System.out.println("Action Constructor " + this);
    }

    @Override
    public void act() {
        System.out.println(s + " " + this);
    }

}
