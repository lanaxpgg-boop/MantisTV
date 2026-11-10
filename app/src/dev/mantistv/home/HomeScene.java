package dev.mantistv.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.*;
import android.widget.*;
import dev.mantistv.home.AppCatalog.AppItem;
import java.util.*;

/** Foreground Home composition. Installed artwork only; no invented content/provider state. */
public final class HomeScene extends FrameLayout {
  public interface Actions {
    void open(AppItem app);
    void page(String name);
    void profile();
    void focused(String tag);
    void appActions(AppItem app);
    void icon(String pkg, ImageView image);
    void banner(String pkg, ImageView image);
  }
  private final Actions actions;
  private final boolean reduced;
  private final ImageView artwork;
  private final LinearLayout hero;
  private final TextView title, detail;
  private final Button open;
  private final ScrollView scroll;
  private final View spacer;
  private final GridLayout grid;
  private final ArrayList<View> tiles = new ArrayList<>();
  private Runnable pendingArtwork;
  private AppItem selected;
  private String selectedPage;
  private View lastTile;
  private int direction = KeyEvent.KEYCODE_DPAD_RIGHT;

  public HomeScene(Context context, boolean motionReduced, boolean warm, String profile,
      List<AppItem> apps, Actions listener) {
    super(context);
    actions = listener;
    reduced = motionReduced;
    setClipChildren(false);
    setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,
        warm ? new int[] {0xff503447, 0xff211623, 0xff09060e}
             : new int[] {0xff26394b, 0xff10131f, 0xff05070c}));
    artwork = new ImageView(context);
    artwork.setScaleType(ImageView.ScaleType.CENTER_CROP);
    artwork.setAlpha(.60f);
    addView(artwork, new LayoutParams(-1, -1));
    View shade = new View(context);
    shade.setBackground(new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,
        new int[] {0xee080b12, 0x99080b12, 0x11080b12}));
    addView(shade, new LayoutParams(-1, -1));
    View bottom = new View(context);
    bottom.setBackground(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM,
        new int[] {0x00070a11, 0x11070a11, 0xee070a11}));
    addView(bottom, new LayoutParams(-1, -1));

    hero = new LinearLayout(context);
    hero.setOrientation(LinearLayout.VERTICAL);
    TextView eyebrow = label("YOUR APPS", 12);
    eyebrow.setLetterSpacing(.14f);
    eyebrow.setTextColor(0xffc1c6d2);
    hero.addView(eyebrow);
    title = label("Apps", 43);
    title.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
    title.setMaxLines(2);
    title.setEllipsize(TextUtils.TruncateAt.END);
    hero.addView(title, new LinearLayout.LayoutParams(-1, -2));
    detail = label("Choose an app below", 15);
    detail.setTextColor(0xffc8ccd6);
    LinearLayout.LayoutParams detailLayout = new LinearLayout.LayoutParams(-1, -2);
    detailLayout.setMargins(0, dp(9), 0, dp(14));
    hero.addView(detail, detailLayout);
    open = new Button(context);
    open.setId(View.generateViewId());
    open.setTag("home:hero");
    open.setText("Open");
    open.setAllCaps(false);
    open.setTextSize(15);
    open.setTextColor(Color.WHITE);
    open.setBackground(surface(false));
    open.setPadding(dp(22), 0, dp(22), 0);
    open.setOnClickListener(v -> {
      if (selected != null) actions.open(selected);
      else if (selectedPage != null) actions.page(selectedPage);
    });
    open.setOnFocusChangeListener((v, focused) -> {
      open.setBackground(surface(focused));
      if (focused) {
        actions.focused(lastTile == null ? "home:hero" : (String) lastTile.getTag());
        scrollToTop();
      }
    });
    open.setOnKeyListener((v, key, event) -> {
      if (event.getAction() == KeyEvent.ACTION_DOWN && key == KeyEvent.KEYCODE_DPAD_DOWN
          && lastTile != null) { lastTile.requestFocus(); return true; }
      return false;
    });
    hero.addView(open, new LinearLayout.LayoutParams(-2, dp(42)));
    LayoutParams heroLayout = new LayoutParams(dp(420), -2, Gravity.TOP | Gravity.START);
    heroLayout.leftMargin = dp(52);
    heroLayout.topMargin = dp(86);
    addView(hero, heroLayout);

    scroll = new ScrollView(context);
    scroll.setFillViewport(false);
    scroll.setVerticalScrollBarEnabled(false);
    scroll.setClipToPadding(false);
    scroll.setClipChildren(false);
    scroll.setPadding(0, 0, 0, dp(28));
    LinearLayout content = new LinearLayout(context);
    content.setOrientation(LinearLayout.VERTICAL);
    content.setClipChildren(false);
    spacer = new View(context);
    spacer.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
    content.addView(spacer, new LinearLayout.LayoutParams(-1, dp(388)));
    grid = new GridLayout(context);
    grid.setColumnCount(6);
    grid.setClipChildren(false);
    grid.setClipToPadding(false);
    grid.setPadding(dp(34), dp(8), dp(34), dp(36));
    int top = Math.min(6, apps.size());
    for (int i = 0; i < top; i++) addApp(apps.get(i));
    // Utility tiles remain reachable even when no other launchable apps are installed.
    String[] tools = {"App Library", "Search", "Recent", "Watchlist", "Controls", "Settings"};
    String[] pages = {"Library", "Search", "Recent", "Watchlist", "Controls", "Settings"};
    for (int i = 0; i < tools.length; i++) addTool(tools[i], pages[i], i);
    for (int i = top; i < apps.size(); i++) addApp(apps.get(i));
    content.addView(grid, new LinearLayout.LayoutParams(-1, -2));
    scroll.addView(content, new ScrollView.LayoutParams(-1, -2));
    scroll.setOnScrollChangeListener((v, x, y, oldx, oldy) -> {
      hero.setAlpha(Math.max(0f, 1f - y / Math.max(1f, getHeight() * .35f)));
      hero.setImportantForAccessibility(y > getHeight() * .3f
          ? View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
          : View.IMPORTANT_FOR_ACCESSIBILITY_AUTO);
    });
    addView(scroll, new LayoutParams(-1, -1));

    LinearLayout status = new LinearLayout(context);
    status.setGravity(Gravity.CENTER_VERTICAL);
    status.setPadding(dp(12), dp(2), dp(4), dp(2));
    status.setBackground(surface(false));
    TextClock clock = new TextClock(context);
    clock.setFormat12Hour("HH:mm");
    clock.setFormat24Hour("HH:mm");
    clock.setTextColor(Color.WHITE);
    clock.setTextSize(15);
    clock.setGravity(Gravity.CENTER_VERTICAL);
    status.addView(clock, new LinearLayout.LayoutParams(-2, dp(32)));
    Button account = new Button(context);
    account.setId(View.generateViewId());
    account.setTag("home:profile");
    account.setText(profile.startsWith("Profile ") ? profile.substring(8)
        : profile.isEmpty() ? "P" : profile.substring(0, 1).toUpperCase(Locale.ROOT));
    account.setTextColor(Color.WHITE);
    account.setTextSize(14);
    account.setAllCaps(false);
    account.setPadding(0, 0, 0, 0);
    account.setMinWidth(0); account.setMinimumWidth(0);
    account.setContentDescription("Switch profile, " + profile);
    account.setBackground(surface(false));
    account.setOnClickListener(v -> actions.profile());
    account.setOnFocusChangeListener((v, focused) -> {
      account.setBackground(surface(focused));
      if (focused) actions.focused("home:profile");
    });
    LinearLayout.LayoutParams accountLayout = new LinearLayout.LayoutParams(dp(32), dp(32));
    accountLayout.leftMargin = dp(9);
    status.addView(account, accountLayout);
    open.setNextFocusUpId(account.getId());
    account.setNextFocusDownId(open.getId());
    LayoutParams statusLayout = new LayoutParams(-2, -2, Gravity.TOP | Gravity.END);
    statusLayout.topMargin = dp(22); statusLayout.rightMargin = dp(42);
    addView(status, statusLayout);
    for (int i = 0; i < tiles.size(); i++) {
      View tile = tiles.get(i);
      if (i < 6) tile.setNextFocusUpId(open.getId());
      else tile.setNextFocusUpId(tiles.get(i - 6).getId());
      tile.setNextFocusLeftId(i % 6 == 0 ? tile.getId() : tiles.get(i - 1).getId());
      tile.setNextFocusRightId(i % 6 == 5 || i + 1 == tiles.size()
          ? tile.getId() : tiles.get(i + 1).getId());
      tile.setNextFocusDownId(i + 6 < tiles.size() ? tiles.get(i + 6).getId() : tile.getId());
    }
  }
  private int dp(float value) {
    return (int) (value * getResources().getDisplayMetrics().density + .5f);
  }
  private TextView label(String value, int size) {
    TextView text = new TextView(getContext());
    text.setText(value); text.setTextColor(Color.WHITE); text.setTextSize(size);
    text.setIncludeFontPadding(false);
    return text;
  }
  private GradientDrawable surface(boolean focused) {
    GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.TL_BR,
        focused ? new int[] {0xdd536174, 0xd52a3342} : new int[] {0xbb303846, 0xbb1b202c});
    drawable.setCornerRadius(dp(11));
    drawable.setStroke(dp(focused ? 2 : 1), focused ? 0xeeffffff : 0x226e7b90);
    return drawable;
  }
  private void addApp(AppItem app) {
    ImageView image = new ImageView(getContext());
    image.setScaleType(ImageView.ScaleType.FIT_CENTER);
    addTile(app.label, "home:app:" + app.pkg, image, app, null);
    image.post(() -> actions.icon(app.pkg, image));
  }
  private void addTool(String name, String page, int glyph) {
    addTile(name, "home:page:" + page, new HomeGlyph(getContext(), glyph), null, page);
  }
  private void addTile(String name, String tag, View image, AppItem app, String page) {
    LinearLayout item = new LinearLayout(getContext());
    item.setId(View.generateViewId()); item.setTag(tag);
    item.setFocusable(true); item.setClickable(true);
    item.setContentDescription(name);
    item.setOrientation(LinearLayout.VERTICAL);
    item.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
    item.setClipChildren(false);
    FrameLayout face = new FrameLayout(getContext());
    face.setClipToOutline(true);
    face.setBackground(surface(false));
    face.addView(image, new FrameLayout.LayoutParams(-1, -1));
    item.addView(face, new LinearLayout.LayoutParams(-1, dp(74)));
    TextView caption = label(name, 15);
    caption.setGravity(Gravity.CENTER);
    caption.setSingleLine(true); caption.setEllipsize(TextUtils.TruncateAt.END);
    caption.setAlpha(0f);
    LinearLayout.LayoutParams captionLayout = new LinearLayout.LayoutParams(-1, dp(28));
    captionLayout.topMargin = dp(8);
    item.addView(caption, captionLayout);
    item.setOnClickListener(v -> {
      if (app != null) actions.open(app); else actions.page(page);
    });
    if (app != null) item.setOnLongClickListener(v -> { actions.appActions(app); return true; });
    item.setOnFocusChangeListener((v, focused) -> {
      face.setBackground(surface(focused));
      caption.setAlpha(focused ? 1f : 0f);
      item.setElevation(focused ? dp(12) : 0);
      float scale = focused && !reduced ? 1.10f : 1f;
      face.animate().cancel(); image.animate().cancel();
      if (reduced) { face.setScaleX(1); face.setScaleY(1); }
      else {
        if (focused) {
          face.setRotationY(direction == KeyEvent.KEYCODE_DPAD_LEFT ? -2f : 2f);
          image.setTranslationX(dp(direction == KeyEvent.KEYCODE_DPAD_LEFT ? -3 : 3));
        }
        face.animate().scaleX(scale).scaleY(scale).rotationY(0).setDuration(180).start();
        image.animate().translationX(0).setDuration(180).start();
      }
      if (focused) {
        actions.focused(tag); lastTile = item;
        selected = app; selectedPage = page;
        title.setText(name);
        detail.setText(app != null ? "Installed application" : toolDescription(page));
        open.setText("Open " + name);
        if (pendingArtwork != null) removeCallbacks(pendingArtwork);
        artwork.setImageDrawable(null);
        artwork.setTag(null);
        if (app != null) {
          pendingArtwork = () -> actions.banner(app.pkg, artwork);
          postDelayed(pendingArtwork, reduced ? 0 : 160);
        }
      }
    });
    GridLayout.LayoutParams layout = new GridLayout.LayoutParams();
    layout.width = 0; layout.height = dp(112);
    layout.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, 1f);
    layout.setMargins(dp(9), dp(8), dp(9), dp(4));
    grid.addView(item, layout); tiles.add(item);
  }
  private String toolDescription(String page) {
    switch (page) {
      case "Library": return "Browse the apps installed on your Fire TV";
      case "Search": return "Find an installed application";
      case "Recent": return "Apps opened with MantisTV";
      case "Watchlist": return "Your saved titles";
      case "Controls": return "Network, display and screensaver controls";
      default: return "Profiles, appearance and device settings";
    }
  }
  private void scrollToTop() {
    if (reduced) scroll.scrollTo(0, 0); else scroll.smoothScrollTo(0, 0);
  }
  public void restoreFocus(String tag) {
    post(() -> {
      View target = tag == null || tag.isEmpty() ? null : findViewWithTag(tag);
      if (target == null || target == open) target = tiles.isEmpty() ? open : tiles.get(0);
      target.requestFocus();
    });
  }
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    ViewGroup.LayoutParams layout = spacer.getLayoutParams();
    int height = Math.round(h * .72f);
    if (layout.height != height) { layout.height = height; spacer.setLayoutParams(layout); }
  }
  @Override
  public boolean dispatchKeyEvent(KeyEvent event) {
    if (event.getAction() == KeyEvent.ACTION_DOWN) direction = event.getKeyCode();
    return super.dispatchKeyEvent(event);
  }
  @Override
  protected void onDetachedFromWindow() {
    if (pendingArtwork != null) removeCallbacks(pendingArtwork);
    super.onDetachedFromWindow();
  }
}
