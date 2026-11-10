package dev.mantistv.home;
import android.content.Context;
import android.graphics.*;
import android.os.SystemClock;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.*;
/** Original landscape renderer. Paths and gradient are rebuilt only when size changes. */
public final class LandscapeView extends View {
  private static final int[] SKY = {0xff163f50, 0xff54747e, 0xff141e36};
  private static final int[] LAYERS = {0xff4c7479, 0xff305565, 0xff203b50, 0xff16283b};
  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private final Path[] mountains = {new Path(), new Path(), new Path(), new Path()};
  private final SimpleDateFormat clockFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
  private final Date clockDate = new Date();
  private final boolean fullscreen, reduced;
  private final float density;
  private Shader sky;
  private long minute = Long.MIN_VALUE;
  private String clock = "";
  public LandscapeView(Context context, boolean full, boolean motionReduced) {
    super(context);
    fullscreen = full;
    reduced = motionReduced;
    density = getResources().getDisplayMetrics().density;
  }
  private float dp(float n) {
    return n * density;
  }
  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    if (w <= 0 || h <= 0)
      return;
    sky = new LinearGradient(0, 0, w, h, SKY, null, Shader.TileMode.CLAMP);
    for (int i = 0; i < mountains.length; i++) {
      Path path = mountains[i];
      path.reset();
      path.moveTo(-w * .1f, h);
      path.lineTo(-w * .1f, h * (.62f + i * .09f));
      for (int x = 0; x < 9; x++)
        path.lineTo(x * w / 7f, h * (.36f + i * .12f) + (float) Math.sin(x * 1.8 + i) * h * .1f);
      path.lineTo(w * 1.2f, h);
      path.close();
    }
  }
  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    float w = getWidth(), h = getHeight();
    paint.setShader(sky);
    canvas.drawRect(0, 0, w, h, paint);
    paint.setShader(null);
    long uptime = SystemClock.uptimeMillis();
    for (int i = 0; i < mountains.length; i++) {
      float offset = reduced ? 0 : (float) Math.sin(uptime / 18000d + i) * w * .008f;
      int saved = canvas.save();
      canvas.translate(offset, 0);
      paint.setColor(LAYERS[i]);
      canvas.drawPath(mountains[i], paint);
      canvas.restoreToCount(saved);
    }
    if (fullscreen) {
      long now = System.currentTimeMillis();
      long current = now / 60000;
      if (current != minute) {
        minute = current;
        clockDate.setTime(now);
        clock = clockFormat.format(clockDate);
      }
      paint.setColor(0xddffffff);
      paint.setTextSize(dp(40));
      canvas.drawText(clock, dp(50), h - dp(70), paint);
      paint.setTextSize(dp(16));
      canvas.drawText("MANTIS  /  Quiet horizons", dp(52), h - dp(40), paint);
      if (!reduced)
        postInvalidateDelayed(100);
    }
  }
}
