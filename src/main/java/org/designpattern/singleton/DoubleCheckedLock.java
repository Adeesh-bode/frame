package org.designpattern.singleton;

class SingletonThreadSafe {

    private static volatile SingletonThreadSafe INSTANCE = null;

    private SingletonThreadSafe(){};

//    Normal Thread Safe -- we are doing DOUBLECHECKEDLOCK
//    private static SingletonThreadSafe INSTANCE;
//    public static synchronized SingletonThreadSafe getInstance(){
//        if(INSTANCE == null){
//            INSTANCE = new SingletonThreadSafe();
//        }
//        return INSTANCE;
//    }

    public static SingletonThreadSafe getInstance(){
        if(INSTANCE == null){
            // to make thread safe
            synchronized (SingletonThreadSafe.class){ //allows 1 thread at a time to enter this syn block
                // check again as multiple thread can reach above null check
                if(INSTANCE == null){
                    INSTANCE = new SingletonThreadSafe();
                }
            }
        }
        return INSTANCE;
    }
}

/*
Example scenario:

1️⃣ Thread A checks INSTANCE == null → true
2️⃣ Thread B checks INSTANCE == null → true

Both threads enter the outer check.

Now:

3️⃣ Thread A enters the synchronized block and creates the object. //allows 1 thread at a time to enter this syn block

4️⃣ Thread B was waiting. When it enters the synchronized block, INSTANCE is already created.

So we check again:

if (INSTANCE == null)
 */


public class DoubleCheckedLock {
    public static void main(String[] args) {
        SingletonThreadSafe instance = SingletonThreadSafe.getInstance();
        System.out.println(instance);
    }

}
