public class Assert {
    public static void main(String[] args) {

        ClassLoader.getSystemClassLoader().
                setDefaultAssertionStatus(true);
        new Loaded().go();
    }
}
class Loaded {
    public void go() {
        assert false: "Loaded.go()";
    }
}
