## 动画实现
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
        Pair pair = new Pair<>(view, PlayActivity.IMG_TRANSITION);
        ActivityOptionsCompat activityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity, pair);
        ActivityCompat.startActivity(activity, intent, activityOptions.toBundle());
    } else {
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
    }