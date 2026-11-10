package dev.mantistv.home;
import android.content.*;
import android.content.pm.*;
import java.util.*;
/** Public launch intents only. No package disabling, task control or privileged queries. */
public final class AppCatalog {
  private AppCatalog() {}
  public static final class AppItem {
    public final String pkg, label;
    public final Intent intent;
    AppItem(String p, String l, Intent i) {
      pkg = p;
      label = l;
      intent = i;
    }
  }
  public static List<AppItem> discover(Context context) {
    ArrayList<AppItem> result = new ArrayList<>();
    HashSet<String> seen = new HashSet<>();
    PackageManager pm = context.getPackageManager();
    for (String category :
        new String[] {Intent.CATEGORY_LEANBACK_LAUNCHER, Intent.CATEGORY_LAUNCHER}) {
      List<ResolveInfo> candidates;
      try {
        candidates =
            pm.queryIntentActivities(new Intent(Intent.ACTION_MAIN).addCategory(category), 0);
      } catch (SecurityException unavailable) {
        continue;
      }
      for (ResolveInfo r : candidates) {
        if (r.activityInfo == null || !r.activityInfo.exported || !r.activityInfo.enabled)
          continue;
        String pkg = r.activityInfo.packageName;
        if (pkg.equals(context.getPackageName()) || !seen.add(pkg))
          continue;
        CharSequence label;
        try {
          label = r.loadLabel(pm);
        } catch (RuntimeException unavailable) {
          label = pkg;
        }
        Intent launch = new Intent(Intent.ACTION_MAIN)
                            .addCategory(category)
                            .setComponent(new ComponentName(pkg, r.activityInfo.name))
                            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        result.add(new AppItem(pkg, label == null ? pkg : label.toString(), launch));
      }
    }
    Collections.sort(result, (a, b) -> a.label.compareToIgnoreCase(b.label));
    return result;
  }
}
