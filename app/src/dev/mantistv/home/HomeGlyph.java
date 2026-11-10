package dev.mantistv.home;

import android.content.Context;
import android.graphics.*;
import android.view.View;

/** Small original line icons; no Apple artwork or font assets. */
final class HomeGlyph extends View {
  private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
  private final Path path = new Path();
  private final int kind;
  HomeGlyph(Context context, int type) {
    super(context); kind = type;
    paint.setColor(Color.WHITE); paint.setStyle(Paint.Style.STROKE);
    paint.setStrokeWidth(2.5f); paint.setStrokeCap(Paint.Cap.ROUND);
    paint.setStrokeJoin(Paint.Join.ROUND);
    if (kind == 3) {
      path.moveTo(21, 17); path.lineTo(43, 17); path.lineTo(43, 49);
      path.lineTo(32, 42); path.lineTo(21, 49); path.close();
    } else if (kind == 5) {
      for (int i = 0; i < 24; i++) {
        double a = i * Math.PI / 12;
        float radius = i % 3 == 0 ? 21 : 17;
        float x = 32 + (float) Math.cos(a) * radius;
        float y = 32 + (float) Math.sin(a) * radius;
        if (i == 0) path.moveTo(x, y); else path.lineTo(x, y);
      }
      path.close();
    }
  }
  @Override protected void onDraw(Canvas c) {
    super.onDraw(c);
    int saved = c.save();
    float size = Math.min(getWidth() * .52f, getHeight() * .70f);
    c.translate((getWidth() - size) / 2, (getHeight() - size) / 2);
    c.scale(size / 64, size / 64);
    switch (kind) {
      case 0:
        for (int y = 0; y < 2; y++) for (int x = 0; x < 3; x++)
          c.drawRoundRect(9 + x * 17, 17 + y * 18, 21 + x * 17, 29 + y * 18, 2, 2, paint);
        break;
      case 1:
        c.drawCircle(28, 28, 15, paint); c.drawLine(39, 39, 53, 53, paint); break;
      case 2:
        c.drawCircle(32, 32, 21, paint); c.drawLine(32, 17, 32, 32, paint);
        c.drawLine(32, 32, 43, 38, paint); break;
      case 3: c.drawPath(path, paint); break;
      case 4:
        c.drawRoundRect(12, 13, 52, 28, 7, 7, paint);
        c.drawRoundRect(12, 36, 52, 51, 7, 7, paint);
        c.drawCircle(23, 20.5f, 3, paint); c.drawCircle(41, 43.5f, 3, paint); break;
      default: c.drawPath(path, paint); c.drawCircle(32, 32, 7, paint);
    }
    c.restoreToCount(saved);
  }
}
