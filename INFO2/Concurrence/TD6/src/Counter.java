public class Counter {
    int count = 0;
    PermitSync<Integer> sync = new PermitSync<>(1);

    public void increment() throws InterruptedException {
        sync.safe(() -> count++);
    }

    public static void main(String[] args) {
        var counter = new Counter();

        new Thread(() -> {
            for (;;) {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(counter.count);
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                try {
                    counter.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(counter.count);
            }
        }).start();
    }
}
