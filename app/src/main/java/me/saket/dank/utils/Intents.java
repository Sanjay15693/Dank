package me.saket.dank.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ShareCompat;

/**
 * Utility methods related to Intents.
 */
public class Intents {

  /**
   * Check if there are any installed app(s) that can handle <var>intent</var>.
   *
   * @return True if any app is present that can handle the intent. False otherwise.
   */
  public static boolean hasAppToHandleIntent(Context context, Intent intent) {
    return intent.resolveActivity(context.getPackageManager()) != null;
  }

  /**
   * For opening an Url in the browser.
   */
  public static Intent createForOpeningUrl(String url) {
    return new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
  }

  /**
   * For sharing an Url and its title. Not all apps support reading the title though.
   */
  public static Intent createForSharingUrl(@Nullable String title, String url) {
    Intent intent = new Intent(Intent.ACTION_SEND);
    intent.setType("text/plain");
    if (title != null && !title.isEmpty()) {
      intent.putExtra(Intent.EXTRA_SUBJECT, title);
    }
    intent.putExtra(Intent.EXTRA_TEXT, url);
    return intent;
  }

  public static Intent createForSharingImage(Activity activity, Uri imageContentUri) {
    return ShareCompat.IntentBuilder.from(activity)
        .addStream(imageContentUri)
        .setType("image/*")
        .getIntent()
        .addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
  }
}
