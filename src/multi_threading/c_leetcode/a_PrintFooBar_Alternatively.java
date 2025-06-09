package multi_threading.c_leetcode;

import java.util.concurrent.Semaphore;

public class a_PrintFooBar_Alternatively {
    public static void main(String[] args) {

    }
}

class FooBar {
private int n;
Semaphore fooSemaphore = new Semaphore(1);
Semaphore barSemaphore = new Semaphore(0);

public FooBar(int n) {
    this.n = n;
}

public void foo(Runnable printFoo) throws InterruptedException {

    for (int i = 0; i < n; i++) {
        fooSemaphore.acquire();
        // printFoo.run() outputs "foo". Do not change or remove this line.
        printFoo.run();
        barSemaphore.release();
    }
}

public void bar(Runnable printBar) throws InterruptedException {

    for (int i = 0; i < n; i++) {
        barSemaphore.acquire();
        // printBar.run() outputs "bar". Do not change or remove this line.
        printBar.run();
        fooSemaphore.release();
    }
}
}
