 <style name="Theme.Dialog">
        <item name="windowFrame">@null</item>
        <item name="windowTitleStyle">@style/DialogWindowTitle</item>
        <item name="windowBackground">@drawable/panel_background</item>
        <item name="windowIsFloating">true</item>
        <item name="windowContentOverlay">@null</item>
        <item name="windowAnimationStyle">@style/Animation.Dialog</item>
        <item name="windowSoftInputMode">stateUnspecified|adjustPan</item>
        <item name="windowCloseOnTouchOutside">@bool/config_closeDialogWhenTouchOutside</item>
        <item name="windowActionModeOverlay">true</item>

        <item name="colorBackgroundCacheHint">@null</item>

        <item name="textAppearance">@style/TextAppearance</item>
        <item name="textAppearanceInverse">@style/TextAppearance.Inverse</item>

        <item name="textColorPrimary">@color/primary_text_dark</item>
        <item name="textColorSecondary">@color/secondary_text_dark</item>
        <item name="textColorTertiary">@color/tertiary_text_dark</item>
        <item name="textColorPrimaryInverse">@color/primary_text_light</item>
        <item name="textColorSecondaryInverse">@color/secondary_text_light</item>
        <item name="textColorTertiaryInverse">@color/tertiary_text_light</item>
        <item name="textColorPrimaryDisableOnly">@color/primary_text_dark_disable_only</item>
        <item name="textColorPrimaryInverseDisableOnly">@color/primary_text_light_disable_only</item>
        <item name="textColorPrimaryNoDisable">@color/primary_text_dark_nodisable</item>
        <item name="textColorSecondaryNoDisable">@color/secondary_text_dark_nodisable</item>
        <item name="textColorPrimaryInverseNoDisable">@color/primary_text_light_nodisable</item>
        <item name="textColorSecondaryInverseNoDisable">@color/secondary_text_light_nodisable</item>
        <item name="textColorHint">@color/hint_foreground_dark</item>
        <item name="textColorHintInverse">@color/hint_foreground_light</item>
        <item name="textColorSearchUrl">@color/search_url_text</item>

        <item name="textAppearanceLarge">@style/TextAppearance.Large</item>
        <item name="textAppearanceMedium">@style/TextAppearance.Medium</item>
        <item name="textAppearanceSmall">@style/TextAppearance.Small</item>
        <item name="textAppearanceLargeInverse">@style/TextAppearance.Large.Inverse</item>
        <item name="textAppearanceMediumInverse">@style/TextAppearance.Medium.Inverse</item>
        <item name="textAppearanceSmallInverse">@style/TextAppearance.Small.Inverse</item>

        <item name="listPreferredItemPaddingLeft">10dip</item>
        <item name="listPreferredItemPaddingRight">10dip</item>
        <item name="listPreferredItemPaddingStart">10dip</item>
        <item name="listPreferredItemPaddingEnd">10dip</item>

        <item name="preferencePanelStyle">@style/PreferencePanel.Dialog</item>
    </style>

    <!-- Variant of {@link Theme_Dialog} that does not include a frame (or background).
         The view hierarchy of the dialog is responsible for drawing all of
         its pixels. -->
    <style name="Theme.Dialog.NoFrame">
        <item name="windowBackground">@color/transparent</item>
        <item name="windowFrame">@null</item>
        <item name="windowContentOverlay">@null</item>
        <item name="windowAnimationStyle">@null</item>
        <item name="backgroundDimEnabled">false</item>
        <item name="windowIsTranslucent">true</item>
        <item name="windowNoTitle">true</item>
        <item name="windowCloseOnTouchOutside">false</item>
    </style>

    <!-- Default theme for alert dialog windows (on API level 10 and lower), which is used by the
         {@link android.app.AlertDialog} class.  This is basically a dialog
         but sets the background to empty so it can do two-tone backgrounds. -->
    <style name="Theme.Dialog.Alert">
        <item name="windowBackground">@color/transparent</item>
        <item name="windowTitleStyle">@style/DialogWindowTitle</item>
        <item name="windowContentOverlay">@null</item>
        <item name="itemTextAppearance">@style/TextAppearance.Large.Inverse</item>
        <item name="textAppearanceListItem">@style/TextAppearance.Large.Inverse</item>
        <item name="textAppearanceListItemSmall">@style/TextAppearance.Large.Inverse</item>
        <item name="textAppearanceListItemSecondary">@style/TextAppearance.Small.Inverse</item>
    </style>