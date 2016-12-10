package defualt;

/**
 * Created by dmthlb on 19/6/16.
 */
public class ITest {

  interface Test {
    void say();
  }

  public static void main(String[] args) {
    Test t = new Test() {
      public void say() {
        System.out.println("Instance of Interface.");
      }
    };

    t.say();
  }
}
