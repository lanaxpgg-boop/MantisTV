package dev.mantistv.home;
import android.content.Context;
import android.graphics.*;
import android.os.SystemClock;
import android.view.View;
import java.text.SimpleDateFormat;
import java.util.*;
public final class LandscapeView extends View {
  final Paint paint = new Paint(3);
  final boolean fullscreen;
  final boolean reduced;
  public LandscapeView(Context c, boolean full, boolean motionReduced) {
    super(c);
    fullscreen = full;
    reduced = motionReduced;
  }
  @Override
  protected void onDraw(Canvas c) {
    super.onDraw(c);
    float w = getWidth(), h = getHeight();
    paint.setShader(new LinearGradient(
        0, 0, w, h, new int[] {0xff163f50, 0xff54747e, 0xff141e36}, null, Shader.TileMode.CLAMP));
    c.drawRect(0, 0, w, h, paint);
    paint.setShader(null);
    for (int i = 0; i < 4; i++) {
      Path p = new Path();
      float offset =
          reduced ? 0 : (float) Math.sin(SystemClock.uptimeMillis() / 18000d + i) * w * .008f;
      p.moveTo(-w * .1f, h);
      p.lineTo(-w * .1f, h * (.62f + i * .09f));
      for (int x = 0; x < 9; x++)
        p.lineTo(
            x * w / 7f + offset, h * (.36f + i * .12f) + (float) Math.sin(x * 1.8 + i) * h * .1f);
      p.lineTo(w * 1.2f, h);
      p.close();
      paint.setColor(new int[] {0xff4c7479, 0xff305565, 0xff203b50, 0xff16283b}[i]);
      c.drawPath(p, paint);
    }
    if (fullscreen) {
      paint.setColor(0xddffffff);
      paint.setTextSize(dp(40));
      c.drawText(new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()), dp(50),
          h - dp(70), paint);
      paint.setTextSize(dp(16));
      c.drawText("MANTIS  /  Quiet horizons", dp(52), h - dp(40), paint);
      if (!reduced)
        postInvalidateDelayed(100);
    }
  }
  private int dp(float n) {
    return (int) (n * getResources().getDisplayMetrics().density + .5f);
  }
}
