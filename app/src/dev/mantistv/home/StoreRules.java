package dev.mantistv.home;
import java.util.*;
public final class StoreRules {
  private StoreRules() {}
  public static List<String> homeOrder(List<String> installed, List<String> favorites,
      List<String> preferred, boolean favoritesSaved) {
    LinkedHashSet<String> available = new LinkedHashSet<>(installed);
    LinkedHashSet<String> ordered = new LinkedHashSet<>();
    for (String pkg : favorites) if (available.contains(pkg)) ordered.add(pkg);
    if (!favoritesSaved)
      for (String pkg : preferred) if (available.contains(pkg)) ordered.add(pkg);
    ordered.addAll(available);
    return new ArrayList<>(ordered);
  }
  public static String profileId(String id) {
    return "1".equals(id) || "2".equals(id) || "3".equals(id) ? id : "0";
  }
  public static List<String> recent(List<String> previous, String id, int limit) {
    if (limit < 1 || id == null || id.trim().isEmpty())
      throw new IllegalArgumentException();
    LinkedHashSet<String> items = new LinkedHashSet<>();
    items.add(id);
    for (String item : previous)
      if (item != null && !item.isEmpty())
        items.add(item);
    List<String> result = new ArrayList<>();
    for (String item : items) {
      if (result.size() == limit)
        break;
      result.add(item);
    }
    return result;
  }
  public static boolean matches(String label, String pkg, String query) {
    String q = query.trim().toLowerCase(Locale.ROOT);
    return label.toLowerCase(Locale.ROOT).contains(q) || pkg.toLowerCase(Locale.ROOT).contains(q);
  }
}
