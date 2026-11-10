import dev.mantistv.home.StoreRules;
import java.util.*;
public class StoreRulesTest {
  static void check(boolean ok) {
    if (!ok)
      throw new AssertionError();
  }
  public static void main(String[] args) {
    check(StoreRules.recent(Arrays.asList("a", "b", "a", "c"), "b", 3)
              .equals(Arrays.asList("b", "a", "c")));
    check(StoreRules.recent(Arrays.asList("a", "b"), "c", 1).equals(Arrays.asList("c")));
    List<String> values = Arrays.asList("a", "b");
    StoreRules.recent(values, "c", 3);
    check(values.equals(Arrays.asList("a", "b")));
    check(StoreRules.matches("Apple TV", "com.apple.atve", " APPLE "));
    check(StoreRules.matches("TV", "com.example.player", "PLAYER"));
    check(!StoreRules.matches("TV", "com.example", "music"));
    try {
      StoreRules.recent(values, "x", 0);
      throw new AssertionError();
    } catch (IllegalArgumentException expected) {
    }
    check(StoreRules.profileId(null).equals("0"));
    check(StoreRules.profileId("999").equals("0"));
    check(StoreRules.profileId("3").equals("3"));
    System.out.println("PASS: profile bounds, history ordering, deduplication, bounds, "
        + "immutability and search normalization");
  }
}
