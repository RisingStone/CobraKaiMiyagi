package cobrakai.com.miyagi.ui;

import android.animation.StateListAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.text.method.MovementMethod;
import android.text.style.URLSpan;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Display;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewOverlay;
import android.view.ViewParent;
import android.view.ViewPropertyAnimator;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.WindowId;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.animation.Animation;
import android.view.inputmethod.CompletionInfo;
import android.view.inputmethod.CorrectionInfo;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.ExtractedText;
import android.view.inputmethod.ExtractedTextRequest;
import android.view.inputmethod.InputConnection;
import android.widget.Scroller;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import cobrakai.com.miyagi.R;

/**
 * Created by Gareoke on 2/27/16.
 */
public class TypefaceTextView extends TextView {
    public TypefaceTextView(Context context) {
        super(context);
    }

    public TypefaceTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TypefaceTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray resource = context.obtainStyledAttributes(attrs, R.styleable.TypefaceTextView, defStyleAttr, 0);
        Typeface face=Typeface.createFromAsset(context.getAssets(),
                resource.getString(R.styleable.TypefaceTextView_font));
        setTypeface(face);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public int getVerticalFadingEdgeLength() {
        return super.getVerticalFadingEdgeLength();
    }

    @Override
    public void setFadingEdgeLength(int length) {
        super.setFadingEdgeLength(length);
    }

    @Override
    public int getHorizontalFadingEdgeLength() {
        return super.getHorizontalFadingEdgeLength();
    }

    @Override
    public int getVerticalScrollbarWidth() {
        return super.getVerticalScrollbarWidth();
    }

    @Override
    protected int getHorizontalScrollbarHeight() {
        return super.getHorizontalScrollbarHeight();
    }

    @Override
    public void setVerticalScrollbarPosition(int position) {
        super.setVerticalScrollbarPosition(position);
    }

    @Override
    public int getVerticalScrollbarPosition() {
        return super.getVerticalScrollbarPosition();
    }

    @Override
    public void setScrollIndicators(int indicators) {
        super.setScrollIndicators(indicators);
    }

    @Override
    public void setScrollIndicators(int indicators, int mask) {
        super.setScrollIndicators(indicators, mask);
    }

    @Override
    public int getScrollIndicators() {
        return super.getScrollIndicators();
    }

    @Override
    public void setOnScrollChangeListener(OnScrollChangeListener l) {
        super.setOnScrollChangeListener(l);
    }

    @Override
    public void setOnFocusChangeListener(OnFocusChangeListener l) {
        super.setOnFocusChangeListener(l);
    }

    @Override
    public void addOnLayoutChangeListener(OnLayoutChangeListener listener) {
        super.addOnLayoutChangeListener(listener);
    }

    @Override
    public void removeOnLayoutChangeListener(OnLayoutChangeListener listener) {
        super.removeOnLayoutChangeListener(listener);
    }

    @Override
    public void addOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        super.addOnAttachStateChangeListener(listener);
    }

    @Override
    public void removeOnAttachStateChangeListener(OnAttachStateChangeListener listener) {
        super.removeOnAttachStateChangeListener(listener);
    }

    @Override
    public OnFocusChangeListener getOnFocusChangeListener() {
        return super.getOnFocusChangeListener();
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

    @Override
    public boolean hasOnClickListeners() {
        return super.hasOnClickListeners();
    }

    @Override
    public void setOnLongClickListener(OnLongClickListener l) {
        super.setOnLongClickListener(l);
    }

    @Override
    public void setOnContextClickListener(OnContextClickListener l) {
        super.setOnContextClickListener(l);
    }

    @Override
    public void setOnCreateContextMenuListener(OnCreateContextMenuListener l) {
        super.setOnCreateContextMenuListener(l);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean callOnClick() {
        return super.callOnClick();
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
    }

    @Override
    public void setFocusable(boolean focusable) {
        super.setFocusable(focusable);
    }

    @Override
    public void setFocusableInTouchMode(boolean focusableInTouchMode) {
        super.setFocusableInTouchMode(focusableInTouchMode);
    }

    @Override
    public void setSoundEffectsEnabled(boolean soundEffectsEnabled) {
        super.setSoundEffectsEnabled(soundEffectsEnabled);
    }

    @Override
    public boolean isSoundEffectsEnabled() {
        return super.isSoundEffectsEnabled();
    }

    @Override
    public void setHapticFeedbackEnabled(boolean hapticFeedbackEnabled) {
        super.setHapticFeedbackEnabled(hapticFeedbackEnabled);
    }

    @Override
    public boolean isHapticFeedbackEnabled() {
        return super.isHapticFeedbackEnabled();
    }

    @Override
    public void setLayoutDirection(int layoutDirection) {
        super.setLayoutDirection(layoutDirection);
    }

    @Override
    public int getLayoutDirection() {
        return super.getLayoutDirection();
    }

    @Override
    public boolean hasTransientState() {
        return super.hasTransientState();
    }

    @Override
    public void setHasTransientState(boolean hasTransientState) {
        super.setHasTransientState(hasTransientState);
    }

    @Override
    public boolean isAttachedToWindow() {
        return super.isAttachedToWindow();
    }

    @Override
    public boolean isLaidOut() {
        return super.isLaidOut();
    }

    @Override
    public void setWillNotDraw(boolean willNotDraw) {
        super.setWillNotDraw(willNotDraw);
    }

    @Override
    public boolean willNotDraw() {
        return super.willNotDraw();
    }

    @Override
    public void setWillNotCacheDrawing(boolean willNotCacheDrawing) {
        super.setWillNotCacheDrawing(willNotCacheDrawing);
    }

    @Override
    public boolean willNotCacheDrawing() {
        return super.willNotCacheDrawing();
    }

    @Override
    public boolean isClickable() {
        return super.isClickable();
    }

    @Override
    public void setClickable(boolean clickable) {
        super.setClickable(clickable);
    }

    @Override
    public boolean isLongClickable() {
        return super.isLongClickable();
    }

    @Override
    public void setLongClickable(boolean longClickable) {
        super.setLongClickable(longClickable);
    }

    @Override
    public boolean isContextClickable() {
        return super.isContextClickable();
    }

    @Override
    public void setContextClickable(boolean contextClickable) {
        super.setContextClickable(contextClickable);
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
    }

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        super.dispatchSetPressed(pressed);
    }

    @Override
    public boolean isPressed() {
        return super.isPressed();
    }

    @Override
    public boolean isSaveEnabled() {
        return super.isSaveEnabled();
    }

    @Override
    public void setSaveEnabled(boolean enabled) {
        super.setSaveEnabled(enabled);
    }

    @Override
    public boolean getFilterTouchesWhenObscured() {
        return super.getFilterTouchesWhenObscured();
    }

    @Override
    public void setFilterTouchesWhenObscured(boolean enabled) {
        super.setFilterTouchesWhenObscured(enabled);
    }

    @Override
    public boolean isSaveFromParentEnabled() {
        return super.isSaveFromParentEnabled();
    }

    @Override
    public void setSaveFromParentEnabled(boolean enabled) {
        super.setSaveFromParentEnabled(enabled);
    }

    @Override
    public View focusSearch(int direction) {
        return super.focusSearch(direction);
    }

    @Override
    public boolean dispatchUnhandledMove(View focused, int direction) {
        return super.dispatchUnhandledMove(focused, direction);
    }

    @Override
    public ArrayList<View> getFocusables(int direction) {
        return super.getFocusables(direction);
    }

    @Override
    public void addFocusables(ArrayList<View> views, int direction) {
        super.addFocusables(views, direction);
    }

    @Override
    public void addFocusables(ArrayList<View> views, int direction, int focusableMode) {
        super.addFocusables(views, direction, focusableMode);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);
    }

    @Override
    protected boolean getDefaultEditable() {
        return super.getDefaultEditable();
    }

    @Override
    protected MovementMethod getDefaultMovementMethod() {
        return super.getDefaultMovementMethod();
    }

    @Override
    public CharSequence getText() {
        return super.getText();
    }

    @Override
    public int length() {
        return super.length();
    }

    @Override
    public Editable getEditableText() {
        return super.getEditableText();
    }

    @Override
    public int getLineHeight() {
        return super.getLineHeight();
    }

    @Override
    public void setKeyListener(KeyListener input) {
        super.setKeyListener(input);
    }

    @Override
    public int getCompoundPaddingTop() {
        return super.getCompoundPaddingTop();
    }

    @Override
    public int getCompoundPaddingBottom() {
        return super.getCompoundPaddingBottom();
    }

    @Override
    public int getCompoundPaddingLeft() {
        return super.getCompoundPaddingLeft();
    }

    @Override
    public int getCompoundPaddingRight() {
        return super.getCompoundPaddingRight();
    }

    @Override
    public int getCompoundPaddingStart() {
        return super.getCompoundPaddingStart();
    }

    @Override
    public int getCompoundPaddingEnd() {
        return super.getCompoundPaddingEnd();
    }

    @Override
    public int getExtendedPaddingTop() {
        return super.getExtendedPaddingTop();
    }

    @Override
    public int getExtendedPaddingBottom() {
        return super.getExtendedPaddingBottom();
    }

    @Override
    public int getTotalPaddingLeft() {
        return super.getTotalPaddingLeft();
    }

    @Override
    public int getTotalPaddingRight() {
        return super.getTotalPaddingRight();
    }

    @Override
    public int getTotalPaddingStart() {
        return super.getTotalPaddingStart();
    }

    @Override
    public int getTotalPaddingEnd() {
        return super.getTotalPaddingEnd();
    }

    @Override
    public int getTotalPaddingTop() {
        return super.getTotalPaddingTop();
    }

    @Override
    public int getTotalPaddingBottom() {
        return super.getTotalPaddingBottom();
    }

    @Override
    public void setCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawables(left, top, right, bottom);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(int left, int top, int right, int bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    @Override
    public void setCompoundDrawablesWithIntrinsicBounds(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        super.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
    }

    @Override
    public void setCompoundDrawablesRelative(Drawable start, Drawable top, Drawable end, Drawable bottom) {
        super.setCompoundDrawablesRelative(start, top, end, bottom);
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int start, int top, int end, int bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    @Override
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable start, Drawable top, Drawable end, Drawable bottom) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(start, top, end, bottom);
    }

    @NonNull
    @Override
    public Drawable[] getCompoundDrawables() {
        return super.getCompoundDrawables();
    }

    @NonNull
    @Override
    public Drawable[] getCompoundDrawablesRelative() {
        return super.getCompoundDrawablesRelative();
    }

    @Override
    public void setCompoundDrawablePadding(int pad) {
        super.setCompoundDrawablePadding(pad);
    }

    @Override
    public int getCompoundDrawablePadding() {
        return super.getCompoundDrawablePadding();
    }

    @Override
    public void setCompoundDrawableTintList(ColorStateList tint) {
        super.setCompoundDrawableTintList(tint);
    }

    @Override
    public ColorStateList getCompoundDrawableTintList() {
        return super.getCompoundDrawableTintList();
    }

    @Override
    public void setCompoundDrawableTintMode(PorterDuff.Mode tintMode) {
        super.setCompoundDrawableTintMode(tintMode);
    }

    @Override
    public PorterDuff.Mode getCompoundDrawableTintMode() {
        return super.getCompoundDrawableTintMode();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
    }

    @Override
    public void setPaddingRelative(int start, int top, int end, int bottom) {
        super.setPaddingRelative(start, top, end, bottom);
    }

    @Override
    public int getPaddingTop() {
        return super.getPaddingTop();
    }

    @Override
    public int getPaddingBottom() {
        return super.getPaddingBottom();
    }

    @Override
    public int getPaddingLeft() {
        return super.getPaddingLeft();
    }

    @Override
    public int getPaddingStart() {
        return super.getPaddingStart();
    }

    @Override
    public int getPaddingRight() {
        return super.getPaddingRight();
    }

    @Override
    public int getPaddingEnd() {
        return super.getPaddingEnd();
    }

    @Override
    public boolean isPaddingRelative() {
        return super.isPaddingRelative();
    }

    @Override
    public void setTextAppearance(int resId) {
        super.setTextAppearance(resId);
    }

    @Override
    public void setTextAppearance(Context context, int resId) {
        super.setTextAppearance(context, resId);
    }

    @NonNull
    @Override
    public Locale getTextLocale() {
        return super.getTextLocale();
    }

    @Override
    public void setTextLocale(Locale locale) {
        super.setTextLocale(locale);
    }

    @Override
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean isInTouchMode() {
        return super.isInTouchMode();
    }

    @Override
    public float getTextSize() {
        return super.getTextSize();
    }

    @Override
    public void setTextSize(float size) {
        super.setTextSize(size);
    }

    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
    }

    @Override
    public float getTextScaleX() {
        return super.getTextScaleX();
    }

    @Override
    public void setTextScaleX(float size) {
        super.setTextScaleX(size);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }

    @Override
    public Typeface getTypeface() {
        return super.getTypeface();
    }

    @Override
    public void setElegantTextHeight(boolean elegant) {
        super.setElegantTextHeight(elegant);
    }

    @Override
    public float getLetterSpacing() {
        return super.getLetterSpacing();
    }

    @Override
    public void setLetterSpacing(float letterSpacing) {
        super.setLetterSpacing(letterSpacing);
    }

    @Nullable
    @Override
    public String getFontFeatureSettings() {
        return super.getFontFeatureSettings();
    }

    @Override
    public void setBreakStrategy(int breakStrategy) {
        super.setBreakStrategy(breakStrategy);
    }

    @Override
    public int getBreakStrategy() {
        return super.getBreakStrategy();
    }

    @Override
    public void setHyphenationFrequency(int hyphenationFrequency) {
        super.setHyphenationFrequency(hyphenationFrequency);
    }

    @Override
    public int getHyphenationFrequency() {
        return super.getHyphenationFrequency();
    }

    @Override
    public void setFontFeatureSettings(String fontFeatureSettings) {
        super.setFontFeatureSettings(fontFeatureSettings);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        super.setTextColor(colors);
    }

    @Override
    public void setHighlightColor(int color) {
        super.setHighlightColor(color);
    }

    @Override
    public int getHighlightColor() {
        return super.getHighlightColor();
    }

    @Override
    public void setShadowLayer(float radius, float dx, float dy, int color) {
        super.setShadowLayer(radius, dx, dy, color);
    }

    @Override
    public float getShadowRadius() {
        return super.getShadowRadius();
    }

    @Override
    public float getShadowDx() {
        return super.getShadowDx();
    }

    @Override
    public float getShadowDy() {
        return super.getShadowDy();
    }

    @Override
    public int getShadowColor() {
        return super.getShadowColor();
    }

    @Override
    public TextPaint getPaint() {
        return super.getPaint();
    }

    @Override
    public URLSpan[] getUrls() {
        return super.getUrls();
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(gravity);
    }

    @Override
    public int getGravity() {
        return super.getGravity();
    }

    @Override
    public int getPaintFlags() {
        return super.getPaintFlags();
    }

    @Override
    public void setPaintFlags(int flags) {
        super.setPaintFlags(flags);
    }

    @Override
    public void setHorizontallyScrolling(boolean whether) {
        super.setHorizontallyScrolling(whether);
    }

    @Override
    public void setMinLines(int minlines) {
        super.setMinLines(minlines);
    }

    @Override
    public int getMinLines() {
        return super.getMinLines();
    }

    @Override
    public void setMinHeight(int minHeight) {
        super.setMinHeight(minHeight);
    }

    @Override
    public int getMinHeight() {
        return super.getMinHeight();
    }

    @Override
    public void setMaxLines(int maxlines) {
        super.setMaxLines(maxlines);
    }

    @Override
    public int getMaxLines() {
        return super.getMaxLines();
    }

    @Override
    public void setMaxHeight(int maxHeight) {
        super.setMaxHeight(maxHeight);
    }

    @Override
    public int getMaxHeight() {
        return super.getMaxHeight();
    }

    @Override
    public void setLines(int lines) {
        super.setLines(lines);
    }

    @Override
    public void setHeight(int pixels) {
        super.setHeight(pixels);
    }

    @Override
    public void setMinEms(int minems) {
        super.setMinEms(minems);
    }

    @Override
    public int getMinEms() {
        return super.getMinEms();
    }

    @Override
    public void setMinWidth(int minpixels) {
        super.setMinWidth(minpixels);
    }

    @Override
    public int getMinWidth() {
        return super.getMinWidth();
    }

    @Override
    public void setMaxEms(int maxems) {
        super.setMaxEms(maxems);
    }

    @Override
    public int getMaxEms() {
        return super.getMaxEms();
    }

    @Override
    public void setMaxWidth(int maxpixels) {
        super.setMaxWidth(maxpixels);
    }

    @Override
    public int getMaxWidth() {
        return super.getMaxWidth();
    }

    @Override
    public void setEms(int ems) {
        super.setEms(ems);
    }

    @Override
    public void setWidth(int pixels) {
        super.setWidth(pixels);
    }

    @Override
    public void setLineSpacing(float add, float mult) {
        super.setLineSpacing(add, mult);
    }

    @Override
    public float getLineSpacingMultiplier() {
        return super.getLineSpacingMultiplier();
    }

    @Override
    public float getLineSpacingExtra() {
        return super.getLineSpacingExtra();
    }

    @Override
    public void append(CharSequence text, int start, int end) {
        super.append(text, start, end);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
    }

    @Override
    public void drawableHotspotChanged(float x, float y) {
        super.drawableHotspotChanged(x, y);
    }

    @Override
    public void dispatchDrawableHotspotChanged(float x, float y) {
        super.dispatchDrawableHotspotChanged(x, y);
    }

    @Override
    public void refreshDrawableState() {
        super.refreshDrawableState();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        return super.onSaveInstanceState();
    }

    @Override
    public void restoreHierarchyState(SparseArray<Parcelable> container) {
        super.restoreHierarchyState(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchRestoreInstanceState(container);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
    }

    @Override
    public long getDrawingTime() {
        return super.getDrawingTime();
    }

    @Override
    public void setDuplicateParentStateEnabled(boolean enabled) {
        super.setDuplicateParentStateEnabled(enabled);
    }

    @Override
    public boolean isDuplicateParentStateEnabled() {
        return super.isDuplicateParentStateEnabled();
    }

    @Override
    public void setLayerType(int layerType, Paint paint) {
        super.setLayerType(layerType, paint);
    }

    @Override
    public void setLayerPaint(Paint paint) {
        super.setLayerPaint(paint);
    }

    @Override
    public int getLayerType() {
        return super.getLayerType();
    }

    @Override
    public void buildLayer() {
        super.buildLayer();
    }

    @Override
    public void setDrawingCacheEnabled(boolean enabled) {
        super.setDrawingCacheEnabled(enabled);
    }

    @Override
    public boolean isDrawingCacheEnabled() {
        return super.isDrawingCacheEnabled();
    }

    @Override
    public Bitmap getDrawingCache() {
        return super.getDrawingCache();
    }

    @Override
    public Bitmap getDrawingCache(boolean autoScale) {
        return super.getDrawingCache(autoScale);
    }

    @Override
    public void destroyDrawingCache() {
        super.destroyDrawingCache();
    }

    @Override
    public void setDrawingCacheBackgroundColor(int color) {
        super.setDrawingCacheBackgroundColor(color);
    }

    @Override
    public int getDrawingCacheBackgroundColor() {
        return super.getDrawingCacheBackgroundColor();
    }

    @Override
    public void buildDrawingCache() {
        super.buildDrawingCache();
    }

    @Override
    public void buildDrawingCache(boolean autoScale) {
        super.buildDrawingCache(autoScale);
    }

    @Override
    public boolean isInEditMode() {
        return super.isInEditMode();
    }

    @Override
    public void setFreezesText(boolean freezesText) {
        super.setFreezesText(freezesText);
    }

    @Override
    public boolean getFreezesText() {
        return super.getFreezesText();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
    }

    @Override
    public CharSequence getHint() {
        return super.getHint();
    }

    @Override
    public void setInputType(int type) {
        super.setInputType(type);
    }

    @Override
    public void setRawInputType(int type) {
        super.setRawInputType(type);
    }

    @Override
    public int getInputType() {
        return super.getInputType();
    }

    @Override
    public void setImeOptions(int imeOptions) {
        super.setImeOptions(imeOptions);
    }

    @Override
    public int getImeOptions() {
        return super.getImeOptions();
    }

    @Override
    public void setImeActionLabel(CharSequence label, int actionId) {
        super.setImeActionLabel(label, actionId);
    }

    @Override
    public CharSequence getImeActionLabel() {
        return super.getImeActionLabel();
    }

    @Override
    public int getImeActionId() {
        return super.getImeActionId();
    }

    @Override
    public void setOnEditorActionListener(OnEditorActionListener l) {
        super.setOnEditorActionListener(l);
    }

    @Override
    public void onEditorAction(int actionCode) {
        super.onEditorAction(actionCode);
    }

    @Override
    public void setPrivateImeOptions(String type) {
        super.setPrivateImeOptions(type);
    }

    @Override
    public String getPrivateImeOptions() {
        return super.getPrivateImeOptions();
    }

    @Override
    public void setInputExtras(int xmlResId) throws XmlPullParserException, IOException {
        super.setInputExtras(xmlResId);
    }

    @Override
    public Bundle getInputExtras(boolean create) {
        return super.getInputExtras(create);
    }

    @Override
    public CharSequence getError() {
        return super.getError();
    }

    @Override
    public void setError(CharSequence error) {
        super.setError(error);
    }

    @Override
    public void setError(CharSequence error, Drawable icon) {
        super.setError(error, icon);
    }

    @Override
    protected boolean setFrame(int l, int t, int r, int b) {
        return super.setFrame(l, t, r, b);
    }

    @Override
    public void setFilters(InputFilter[] filters) {
        super.setFilters(filters);
    }

    @Override
    public InputFilter[] getFilters() {
        return super.getFilters();
    }

    @Override
    public boolean onPreDraw() {
        return super.onPreDraw();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    public void onScreenStateChanged(int screenState) {
        super.onScreenStateChanged(screenState);
    }

    @Override
    protected boolean isPaddingOffsetRequired() {
        return super.isPaddingOffsetRequired();
    }

    @Override
    protected int getLeftPaddingOffset() {
        return super.getLeftPaddingOffset();
    }

    @Override
    protected int getTopPaddingOffset() {
        return super.getTopPaddingOffset();
    }

    @Override
    protected int getBottomPaddingOffset() {
        return super.getBottomPaddingOffset();
    }

    @Override
    public boolean isHardwareAccelerated() {
        return super.isHardwareAccelerated();
    }

    @Override
    public void setClipBounds(Rect clipBounds) {
        super.setClipBounds(clipBounds);
    }

    @Override
    public Rect getClipBounds() {
        return super.getClipBounds();
    }

    @Override
    public boolean getClipBounds(Rect outRect) {
        return super.getClipBounds(outRect);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public ViewOverlay getOverlay() {
        return super.getOverlay();
    }

    @Override
    public int getSolidColor() {
        return super.getSolidColor();
    }

    @Override
    public boolean isLayoutRequested() {
        return super.isLayoutRequested();
    }

    @Override
    public void layout(int l, int t, int r, int b) {
        super.layout(l, t, r, b);
    }

    @Override
    protected int getRightPaddingOffset() {
        return super.getRightPaddingOffset();
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who);
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
    }

    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }

    @Override
    public void setBackground(Drawable background) {
        super.setBackground(background);
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        super.setBackgroundDrawable(background);
    }

    @Override
    public Drawable getBackground() {
        return super.getBackground();
    }

    @Override
    public void setBackgroundTintList(ColorStateList tint) {
        super.setBackgroundTintList(tint);
    }

    @Nullable
    @Override
    public ColorStateList getBackgroundTintList() {
        return super.getBackgroundTintList();
    }

    @Override
    public void setBackgroundTintMode(PorterDuff.Mode tintMode) {
        super.setBackgroundTintMode(tintMode);
    }

    @Nullable
    @Override
    public PorterDuff.Mode getBackgroundTintMode() {
        return super.getBackgroundTintMode();
    }

    @Override
    public Drawable getForeground() {
        return super.getForeground();
    }

    @Override
    public void setForeground(Drawable foreground) {
        super.setForeground(foreground);
    }

    @Override
    public int getForegroundGravity() {
        return super.getForegroundGravity();
    }

    @Override
    public void setForegroundGravity(int gravity) {
        super.setForegroundGravity(gravity);
    }

    @Override
    public void setForegroundTintList(ColorStateList tint) {
        super.setForegroundTintList(tint);
    }

    @Nullable
    @Override
    public ColorStateList getForegroundTintList() {
        return super.getForegroundTintList();
    }

    @Override
    public void setForegroundTintMode(PorterDuff.Mode tintMode) {
        super.setForegroundTintMode(tintMode);
    }

    @Nullable
    @Override
    public PorterDuff.Mode getForegroundTintMode() {
        return super.getForegroundTintMode();
    }

    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
    }

    @Override
    public void invalidateDrawable(Drawable drawable) {
        super.invalidateDrawable(drawable);
    }

    @Override
    public void scheduleDrawable(Drawable who, Runnable what, long when) {
        super.scheduleDrawable(who, what, when);
    }

    @Override
    public void unscheduleDrawable(Drawable who, Runnable what) {
        super.unscheduleDrawable(who, what);
    }

    @Override
    public void unscheduleDrawable(Drawable who) {
        super.unscheduleDrawable(who);
    }

    @Override
    public boolean hasOverlappingRendering() {
        return super.hasOverlappingRendering();
    }

    @Override
    public void setAlpha(float alpha) {
        super.setAlpha(alpha);
    }

    @Override
    public boolean isDirty() {
        return super.isDirty();
    }

    @Override
    public float getX() {
        return super.getX();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
    }

    @Override
    public float getY() {
        return super.getY();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
    }

    @Override
    public float getZ() {
        return super.getZ();
    }

    @Override
    public void setZ(float z) {
        super.setZ(z);
    }

    @Override
    public float getElevation() {
        return super.getElevation();
    }

    @Override
    public void setElevation(float elevation) {
        super.setElevation(elevation);
    }

    @Override
    public float getTranslationX() {
        return super.getTranslationX();
    }

    @Override
    public void setTranslationX(float translationX) {
        super.setTranslationX(translationX);
    }

    @Override
    public float getTranslationY() {
        return super.getTranslationY();
    }

    @Override
    public void setTranslationY(float translationY) {
        super.setTranslationY(translationY);
    }

    @Override
    public float getTranslationZ() {
        return super.getTranslationZ();
    }

    @Override
    public void setTranslationZ(float translationZ) {
        super.setTranslationZ(translationZ);
    }

    @Override
    public StateListAnimator getStateListAnimator() {
        return super.getStateListAnimator();
    }

    @Override
    public void setStateListAnimator(StateListAnimator stateListAnimator) {
        super.setStateListAnimator(stateListAnimator);
    }

    @Override
    public void setClipToOutline(boolean clipToOutline) {
        super.setClipToOutline(clipToOutline);
    }

    @Override
    public void setOutlineProvider(ViewOutlineProvider provider) {
        super.setOutlineProvider(provider);
    }

    @Override
    public ViewOutlineProvider getOutlineProvider() {
        return super.getOutlineProvider();
    }

    @Override
    public void invalidateOutline() {
        super.invalidateOutline();
    }

    @Override
    public void getHitRect(Rect outRect) {
        super.getHitRect(outRect);
    }

    @Override
    public boolean isTextSelectable() {
        return super.isTextSelectable();
    }

    @Override
    public void setTextIsSelectable(boolean selectable) {
        super.setTextIsSelectable(selectable);
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        return super.onCreateDrawableState(extraSpace);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public void getFocusedRect(Rect r) {
        super.getFocusedRect(r);
    }

    @Override
    public boolean getGlobalVisibleRect(Rect r, Point globalOffset) {
        return super.getGlobalVisibleRect(r, globalOffset);
    }

    @Override
    public void offsetTopAndBottom(int offset) {
        super.offsetTopAndBottom(offset);
    }

    @Override
    public void offsetLeftAndRight(int offset) {
        super.offsetLeftAndRight(offset);
    }

    @Override
    public ViewGroup.LayoutParams getLayoutParams() {
        return super.getLayoutParams();
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    @Override
    protected boolean awakenScrollBars() {
        return super.awakenScrollBars();
    }

    @Override
    protected boolean awakenScrollBars(int startDelay) {
        return super.awakenScrollBars(startDelay);
    }

    @Override
    protected boolean awakenScrollBars(int startDelay, boolean invalidate) {
        return super.awakenScrollBars(startDelay, invalidate);
    }

    @Override
    public void invalidate(Rect dirty) {
        super.invalidate(dirty);
    }

    @Override
    public void invalidate(int l, int t, int r, int b) {
        super.invalidate(l, t, r, b);
    }

    @Override
    public void invalidate() {
        super.invalidate();
    }

    @Override
    public boolean isOpaque() {
        return super.isOpaque();
    }

    @Override
    public Handler getHandler() {
        return super.getHandler();
    }

    @Override
    public boolean post(Runnable action) {
        return super.post(action);
    }

    @Override
    public boolean postDelayed(Runnable action, long delayMillis) {
        return super.postDelayed(action, delayMillis);
    }

    @Override
    public void postOnAnimation(Runnable action) {
        super.postOnAnimation(action);
    }

    @Override
    public void postOnAnimationDelayed(Runnable action, long delayMillis) {
        super.postOnAnimationDelayed(action, delayMillis);
    }

    @Override
    public boolean removeCallbacks(Runnable action) {
        return super.removeCallbacks(action);
    }

    @Override
    public void postInvalidate() {
        super.postInvalidate();
    }

    @Override
    public void postInvalidate(int left, int top, int right, int bottom) {
        super.postInvalidate(left, top, right, bottom);
    }

    @Override
    public void postInvalidateDelayed(long delayMilliseconds) {
        super.postInvalidateDelayed(delayMilliseconds);
    }

    @Override
    public void postInvalidateDelayed(long delayMilliseconds, int left, int top, int right, int bottom) {
        super.postInvalidateDelayed(delayMilliseconds, left, top, right, bottom);
    }

    @Override
    public void postInvalidateOnAnimation() {
        super.postInvalidateOnAnimation();
    }

    @Override
    public void postInvalidateOnAnimation(int left, int top, int right, int bottom) {
        super.postInvalidateOnAnimation(left, top, right, bottom);
    }

    @Override
    public int getLineCount() {
        return super.getLineCount();
    }

    @Override
    public int getLineBounds(int line, Rect bounds) {
        return super.getLineBounds(line, bounds);
    }

    @Override
    public int getBaseline() {
        return super.getBaseline();
    }

    @Override
    public boolean isInLayout() {
        return super.isInLayout();
    }

    @Override
    public void requestLayout() {
        super.requestLayout();
    }

    @Override
    public void forceLayout() {
        super.forceLayout();
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        return super.onKeyPreIme(keyCode, event);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        return super.onKeyLongPress(keyCode, event);
    }

    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        return super.onKeyMultiple(keyCode, repeatCount, event);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public boolean onCheckIsTextEditor() {
        return super.onCheckIsTextEditor();
    }

    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return super.onCreateInputConnection(outAttrs);
    }

    @Override
    public boolean checkInputConnectionProxy(View view) {
        return super.checkInputConnectionProxy(view);
    }

    @Override
    public void createContextMenu(ContextMenu menu) {
        super.createContextMenu(menu);
    }

    @Override
    protected ContextMenu.ContextMenuInfo getContextMenuInfo() {
        return super.getContextMenuInfo();
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
    }

    @Override
    public boolean extractText(ExtractedTextRequest request, ExtractedText outText) {
        return super.extractText(request, outText);
    }

    @Override
    public void setExtractedText(ExtractedText text) {
        super.setExtractedText(text);
    }

    @Override
    public void onCommitCompletion(CompletionInfo text) {
        super.onCommitCompletion(text);
    }

    @Override
    public void onCommitCorrection(CorrectionInfo info) {
        super.onCommitCorrection(info);
    }

    @Override
    public void beginBatchEdit() {
        super.beginBatchEdit();
    }

    @Override
    public void endBatchEdit() {
        super.endBatchEdit();
    }

    @Override
    public void onBeginBatchEdit() {
        super.onBeginBatchEdit();
    }

    @Override
    public void onEndBatchEdit() {
        super.onEndBatchEdit();
    }

    @Override
    public boolean onPrivateIMECommand(String action, Bundle data) {
        return super.onPrivateIMECommand(action, data);
    }

    @Override
    public void setIncludeFontPadding(boolean includepad) {
        super.setIncludeFontPadding(includepad);
    }

    @Override
    public boolean getIncludeFontPadding() {
        return super.getIncludeFontPadding();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return super.getSuggestedMinimumHeight();
    }

    @Override
    protected int getSuggestedMinimumWidth() {
        return super.getSuggestedMinimumWidth();
    }

    @Override
    public int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override
    public void setMinimumHeight(int minHeight) {
        super.setMinimumHeight(minHeight);
    }

    @Override
    public int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override
    public void setMinimumWidth(int minWidth) {
        super.setMinimumWidth(minWidth);
    }

    @Override
    public Animation getAnimation() {
        return super.getAnimation();
    }

    @Override
    public void startAnimation(Animation animation) {
        super.startAnimation(animation);
    }

    @Override
    public void clearAnimation() {
        super.clearAnimation();
    }

    @Override
    public void setAnimation(Animation animation) {
        super.setAnimation(animation);
    }

    @Override
    protected void onAnimationStart() {
        super.onAnimationStart();
    }

    @Override
    protected void onAnimationEnd() {
        super.onAnimationEnd();
    }

    @Override
    protected boolean onSetAlpha(int alpha) {
        return super.onSetAlpha(alpha);
    }

    @Override
    public void playSoundEffect(int soundConstant) {
        super.playSoundEffect(soundConstant);
    }

    @Override
    public boolean performHapticFeedback(int feedbackConstant) {
        return super.performHapticFeedback(feedbackConstant);
    }

    @Override
    public boolean performHapticFeedback(int feedbackConstant, int flags) {
        return super.performHapticFeedback(feedbackConstant, flags);
    }

    @Override
    public void setSystemUiVisibility(int visibility) {
        super.setSystemUiVisibility(visibility);
    }

    @Override
    public int getSystemUiVisibility() {
        return super.getSystemUiVisibility();
    }

    @Override
    public int getWindowSystemUiVisibility() {
        return super.getWindowSystemUiVisibility();
    }

    @Override
    public void onWindowSystemUiVisibilityChanged(int visible) {
        super.onWindowSystemUiVisibilityChanged(visible);
    }

    @Override
    public void dispatchWindowSystemUiVisiblityChanged(int visible) {
        super.dispatchWindowSystemUiVisiblityChanged(visible);
    }

    @Override
    public void setOnSystemUiVisibilityChangeListener(OnSystemUiVisibilityChangeListener l) {
        super.setOnSystemUiVisibilityChangeListener(l);
    }

    @Override
    public void dispatchSystemUiVisibilityChanged(int visibility) {
        super.dispatchSystemUiVisibilityChanged(visibility);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    public Resources getResources() {
        return super.getResources();
    }

    @Override
    public boolean bringPointIntoView(int offset) {
        return super.bringPointIntoView(offset);
    }

    @Override
    public boolean moveCursorToVisibleOffset() {
        return super.moveCursorToVisibleOffset();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
    }

    @Override
    public boolean isHorizontalFadingEdgeEnabled() {
        return super.isHorizontalFadingEdgeEnabled();
    }

    @Override
    public void setHorizontalFadingEdgeEnabled(boolean horizontalFadingEdgeEnabled) {
        super.setHorizontalFadingEdgeEnabled(horizontalFadingEdgeEnabled);
    }

    @Override
    public boolean isVerticalFadingEdgeEnabled() {
        return super.isVerticalFadingEdgeEnabled();
    }

    @Override
    public void setVerticalFadingEdgeEnabled(boolean verticalFadingEdgeEnabled) {
        super.setVerticalFadingEdgeEnabled(verticalFadingEdgeEnabled);
    }

    @Override
    protected float getTopFadingEdgeStrength() {
        return super.getTopFadingEdgeStrength();
    }

    @Override
    protected float getBottomFadingEdgeStrength() {
        return super.getBottomFadingEdgeStrength();
    }

    @Override
    public void debug(int depth) {
        super.debug(depth);
    }

    @Override
    public int getSelectionStart() {
        return super.getSelectionStart();
    }

    @Override
    public int getSelectionEnd() {
        return super.getSelectionEnd();
    }

    @Override
    public boolean hasSelection() {
        return super.hasSelection();
    }

    @Override
    public void setSingleLine() {
        super.setSingleLine();
    }

    @Override
    public void setAllCaps(boolean allCaps) {
        super.setAllCaps(allCaps);
    }

    @Override
    public void setSingleLine(boolean singleLine) {
        super.setSingleLine(singleLine);
    }

    @Override
    public void setEllipsize(TextUtils.TruncateAt where) {
        super.setEllipsize(where);
    }

    @Override
    public void setMarqueeRepeatLimit(int marqueeLimit) {
        super.setMarqueeRepeatLimit(marqueeLimit);
    }

    @Override
    public int getMarqueeRepeatLimit() {
        return super.getMarqueeRepeatLimit();
    }

    @Override
    public TextUtils.TruncateAt getEllipsize() {
        return super.getEllipsize();
    }

    @Override
    public void setSelectAllOnFocus(boolean selectAllOnFocus) {
        super.setSelectAllOnFocus(selectAllOnFocus);
    }

    @Override
    public void setCursorVisible(boolean visible) {
        super.setCursorVisible(visible);
    }

    @Override
    public boolean isCursorVisible() {
        return super.isCursorVisible();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
    }

    @Override
    public void addTextChangedListener(TextWatcher watcher) {
        super.addTextChangedListener(watcher);
    }

    @Override
    public void removeTextChangedListener(TextWatcher watcher) {
        super.removeTextChangedListener(watcher);
    }

    @Override
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
    }

    @Override
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
    }

    @Override
    public KeyEvent.DispatcherState getKeyDispatcherState() {
        return super.getKeyDispatcherState();
    }

    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        return super.dispatchKeyEventPreIme(event);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }

    @Override
    public boolean dispatchKeyShortcutEvent(KeyEvent event) {
        return super.dispatchKeyShortcutEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onFilterTouchEventForSecurity(MotionEvent event) {
        return super.onFilterTouchEventForSecurity(event);
    }

    @Override
    public boolean dispatchTrackballEvent(MotionEvent event) {
        return super.dispatchTrackballEvent(event);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent event) {
        return super.dispatchGenericMotionEvent(event);
    }

    @Override
    protected boolean dispatchHoverEvent(MotionEvent event) {
        return super.dispatchHoverEvent(event);
    }

    @Override
    protected boolean dispatchGenericPointerEvent(MotionEvent event) {
        return super.dispatchGenericPointerEvent(event);
    }

    @Override
    protected boolean dispatchGenericFocusedEvent(MotionEvent event) {
        return super.dispatchGenericFocusedEvent(event);
    }

    @Override
    public void dispatchWindowFocusChanged(boolean hasFocus) {
        super.dispatchWindowFocusChanged(hasFocus);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }

    @Override
    public void sendAccessibilityEvent(int eventType) {
        super.sendAccessibilityEvent(eventType);
    }

    @Override
    public void announceForAccessibility(CharSequence text) {
        super.announceForAccessibility(text);
    }

    @Override
    public void sendAccessibilityEventUnchecked(AccessibilityEvent event) {
        super.sendAccessibilityEventUnchecked(event);
    }

    @Override
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event) {
        return super.dispatchPopulateAccessibilityEvent(event);
    }

    @Override
    public void onPopulateAccessibilityEvent(AccessibilityEvent event) {
        super.onPopulateAccessibilityEvent(event);
    }

    @Override
    public void onInitializeAccessibilityEvent(AccessibilityEvent event) {
        super.onInitializeAccessibilityEvent(event);
    }

    @Override
    public AccessibilityNodeInfo createAccessibilityNodeInfo() {
        return super.createAccessibilityNodeInfo();
    }

    @Override
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo info) {
        super.onInitializeAccessibilityNodeInfo(info);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    public boolean hasWindowFocus() {
        return super.hasWindowFocus();
    }

    @Override
    protected void dispatchVisibilityChanged(View changedView, int visibility) {
        super.dispatchVisibilityChanged(changedView, visibility);
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
    }

    @Override
    public void dispatchDisplayHint(int hint) {
        super.dispatchDisplayHint(hint);
    }

    @Override
    protected void onDisplayHint(int hint) {
        super.onDisplayHint(hint);
    }

    @Override
    public void dispatchWindowVisibilityChanged(int visibility) {
        super.dispatchWindowVisibilityChanged(visibility);
    }

    @Override
    protected void onWindowVisibilityChanged(int visibility) {
        super.onWindowVisibilityChanged(visibility);
    }

    @Override
    public int getWindowVisibility() {
        return super.getWindowVisibility();
    }

    @Override
    public void getWindowVisibleDisplayFrame(Rect outRect) {
        super.getWindowVisibleDisplayFrame(outRect);
    }

    @Override
    public void dispatchConfigurationChanged(Configuration newConfig) {
        super.dispatchConfigurationChanged(newConfig);
    }

    @Override
    public void clearComposingText() {
        super.clearComposingText();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
    }

    @Override
    protected void dispatchSetSelected(boolean selected) {
        super.dispatchSetSelected(selected);
    }

    @Override
    public boolean isSelected() {
        return super.isSelected();
    }

    @Override
    public void setActivated(boolean activated) {
        super.setActivated(activated);
    }

    @Override
    protected void dispatchSetActivated(boolean activated) {
        super.dispatchSetActivated(activated);
    }

    @Override
    public boolean isActivated() {
        return super.isActivated();
    }

    @Override
    public ViewTreeObserver getViewTreeObserver() {
        return super.getViewTreeObserver();
    }

    @Override
    public View getRootView() {
        return super.getRootView();
    }

    @Override
    public void getLocationOnScreen(int[] location) {
        super.getLocationOnScreen(location);
    }

    @Override
    public void getLocationInWindow(int[] location) {
        super.getLocationInWindow(location);
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public Object getTag() {
        return super.getTag();
    }

    @Override
    public void setTag(Object tag) {
        super.setTag(tag);
    }

    @Override
    public Object getTag(int key) {
        return super.getTag(key);
    }

    @Override
    public void setTag(int key, Object tag) {
        super.setTag(key, tag);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        return super.onGenericMotionEvent(event);
    }

    @Override
    public boolean onHoverEvent(MotionEvent event) {
        return super.onHoverEvent(event);
    }

    @Override
    public boolean isHovered() {
        return super.isHovered();
    }

    @Override
    public void setHovered(boolean hovered) {
        super.setHovered(hovered);
    }

    @Override
    public void onHoverChanged(boolean hovered) {
        super.onHoverChanged(hovered);
    }

    @Override
    public boolean didTouchFocusSelect() {
        return super.didTouchFocusSelect();
    }

    @Override
    public void cancelLongPress() {
        super.cancelLongPress();
    }

    @Override
    public void setTouchDelegate(TouchDelegate delegate) {
        super.setTouchDelegate(delegate);
    }

    @Override
    public TouchDelegate getTouchDelegate() {
        return super.getTouchDelegate();
    }

    @Override
    public void bringToFront() {
        super.bringToFront();
    }

    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        return super.onTrackballEvent(event);
    }

    @Override
    public void setScroller(Scroller s) {
        super.setScroller(s);
    }

    @Override
    protected float getLeftFadingEdgeStrength() {
        return super.getLeftFadingEdgeStrength();
    }

    @Override
    protected float getRightFadingEdgeStrength() {
        return super.getRightFadingEdgeStrength();
    }

    @Override
    public boolean isHorizontalScrollBarEnabled() {
        return super.isHorizontalScrollBarEnabled();
    }

    @Override
    public void setHorizontalScrollBarEnabled(boolean horizontalScrollBarEnabled) {
        super.setHorizontalScrollBarEnabled(horizontalScrollBarEnabled);
    }

    @Override
    public boolean isVerticalScrollBarEnabled() {
        return super.isVerticalScrollBarEnabled();
    }

    @Override
    public void setVerticalScrollBarEnabled(boolean verticalScrollBarEnabled) {
        super.setVerticalScrollBarEnabled(verticalScrollBarEnabled);
    }

    @Override
    public void setScrollbarFadingEnabled(boolean fadeScrollbars) {
        super.setScrollbarFadingEnabled(fadeScrollbars);
    }

    @Override
    public boolean isScrollbarFadingEnabled() {
        return super.isScrollbarFadingEnabled();
    }

    @Override
    public int getScrollBarDefaultDelayBeforeFade() {
        return super.getScrollBarDefaultDelayBeforeFade();
    }

    @Override
    public void setScrollBarDefaultDelayBeforeFade(int scrollBarDefaultDelayBeforeFade) {
        super.setScrollBarDefaultDelayBeforeFade(scrollBarDefaultDelayBeforeFade);
    }

    @Override
    public int getScrollBarFadeDuration() {
        return super.getScrollBarFadeDuration();
    }

    @Override
    public void setScrollBarFadeDuration(int scrollBarFadeDuration) {
        super.setScrollBarFadeDuration(scrollBarFadeDuration);
    }

    @Override
    public int getScrollBarSize() {
        return super.getScrollBarSize();
    }

    @Override
    public void setScrollBarSize(int scrollBarSize) {
        super.setScrollBarSize(scrollBarSize);
    }

    @Override
    public void setScrollBarStyle(int style) {
        super.setScrollBarStyle(style);
    }

    @Override
    public int getScrollBarStyle() {
        return super.getScrollBarStyle();
    }

    @Override
    protected int computeHorizontalScrollRange() {
        return super.computeHorizontalScrollRange();
    }

    @Override
    protected int computeHorizontalScrollOffset() {
        return super.computeHorizontalScrollOffset();
    }

    @Override
    protected int computeHorizontalScrollExtent() {
        return super.computeHorizontalScrollExtent();
    }

    @Override
    protected int computeVerticalScrollRange() {
        return super.computeVerticalScrollRange();
    }

    @Override
    protected int computeVerticalScrollOffset() {
        return super.computeVerticalScrollOffset();
    }

    @Override
    protected int computeVerticalScrollExtent() {
        return super.computeVerticalScrollExtent();
    }

    @Override
    public boolean canScrollHorizontally(int direction) {
        return super.canScrollHorizontally(direction);
    }

    @Override
    public boolean canScrollVertically(int direction) {
        return super.canScrollVertically(direction);
    }

    @Override
    public void findViewsWithText(ArrayList<View> outViews, CharSequence searched, int flags) {
        super.findViewsWithText(outViews, searched, flags);
    }

    @Override
    public ArrayList<View> getTouchables() {
        return super.getTouchables();
    }

    @Override
    public void addTouchables(ArrayList<View> views) {
        super.addTouchables(views);
    }

    @Override
    public boolean isAccessibilityFocused() {
        return super.isAccessibilityFocused();
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        return super.requestFocus(direction, previouslyFocusedRect);
    }

    @Override
    public int getImportantForAccessibility() {
        return super.getImportantForAccessibility();
    }

    @Override
    public void setAccessibilityLiveRegion(int mode) {
        super.setAccessibilityLiveRegion(mode);
    }

    @Override
    public int getAccessibilityLiveRegion() {
        return super.getAccessibilityLiveRegion();
    }

    @Override
    public void setImportantForAccessibility(int mode) {
        super.setImportantForAccessibility(mode);
    }

    @Override
    public boolean isImportantForAccessibility() {
        return super.isImportantForAccessibility();
    }

    @Override
    public ViewParent getParentForAccessibility() {
        return super.getParentForAccessibility();
    }

    @Override
    public void addChildrenForAccessibility(ArrayList<View> outChildren) {
        super.addChildrenForAccessibility(outChildren);
    }

    @Override
    public boolean dispatchNestedPrePerformAccessibilityAction(int action, Bundle arguments) {
        return super.dispatchNestedPrePerformAccessibilityAction(action, arguments);
    }

    @Override
    public boolean performAccessibilityAction(int action, Bundle arguments) {
        return super.performAccessibilityAction(action, arguments);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return super.getAccessibilityClassName();
    }

    @Override
    public void onProvideStructure(ViewStructure structure) {
        super.onProvideStructure(structure);
    }

    @Override
    public void onProvideVirtualStructure(ViewStructure structure) {
        super.onProvideVirtualStructure(structure);
    }

    @Override
    public void dispatchProvideStructure(ViewStructure structure) {
        super.dispatchProvideStructure(structure);
    }

    @Override
    public void setAccessibilityDelegate(AccessibilityDelegate delegate) {
        super.setAccessibilityDelegate(delegate);
    }

    @Override
    public AccessibilityNodeProvider getAccessibilityNodeProvider() {
        return super.getAccessibilityNodeProvider();
    }

    @Override
    public CharSequence getContentDescription() {
        return super.getContentDescription();
    }

    @Override
    public void setContentDescription(CharSequence contentDescription) {
        super.setContentDescription(contentDescription);
    }

    @Override
    public void setAccessibilityTraversalBefore(int beforeId) {
        super.setAccessibilityTraversalBefore(beforeId);
    }

    @Override
    public int getAccessibilityTraversalBefore() {
        return super.getAccessibilityTraversalBefore();
    }

    @Override
    public void setAccessibilityTraversalAfter(int afterId) {
        super.setAccessibilityTraversalAfter(afterId);
    }

    @Override
    public int getAccessibilityTraversalAfter() {
        return super.getAccessibilityTraversalAfter();
    }

    @Override
    public int getLabelFor() {
        return super.getLabelFor();
    }

    @Override
    public void setLabelFor(int id) {
        super.setLabelFor(id);
    }

    @Override
    public boolean isFocused() {
        return super.isFocused();
    }

    @Override
    public View findFocus() {
        return super.findFocus();
    }

    @Override
    public boolean isScrollContainer() {
        return super.isScrollContainer();
    }

    @Override
    public void setScrollContainer(boolean isScrollContainer) {
        super.setScrollContainer(isScrollContainer);
    }

    @Override
    public int getDrawingCacheQuality() {
        return super.getDrawingCacheQuality();
    }

    @Override
    public void setDrawingCacheQuality(int quality) {
        super.setDrawingCacheQuality(quality);
    }

    @Override
    public boolean getKeepScreenOn() {
        return super.getKeepScreenOn();
    }

    @Override
    public void setKeepScreenOn(boolean keepScreenOn) {
        super.setKeepScreenOn(keepScreenOn);
    }

    @Override
    public int getNextFocusLeftId() {
        return super.getNextFocusLeftId();
    }

    @Override
    public void setNextFocusLeftId(int nextFocusLeftId) {
        super.setNextFocusLeftId(nextFocusLeftId);
    }

    @Override
    public int getNextFocusRightId() {
        return super.getNextFocusRightId();
    }

    @Override
    public void setNextFocusRightId(int nextFocusRightId) {
        super.setNextFocusRightId(nextFocusRightId);
    }

    @Override
    public int getNextFocusUpId() {
        return super.getNextFocusUpId();
    }

    @Override
    public void setNextFocusUpId(int nextFocusUpId) {
        super.setNextFocusUpId(nextFocusUpId);
    }

    @Override
    public int getNextFocusDownId() {
        return super.getNextFocusDownId();
    }

    @Override
    public void setNextFocusDownId(int nextFocusDownId) {
        super.setNextFocusDownId(nextFocusDownId);
    }

    @Override
    public int getNextFocusForwardId() {
        return super.getNextFocusForwardId();
    }

    @Override
    public void setNextFocusForwardId(int nextFocusForwardId) {
        super.setNextFocusForwardId(nextFocusForwardId);
    }

    @Override
    public boolean isShown() {
        return super.isShown();
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        return super.fitSystemWindows(insets);
    }

    @Override
    public WindowInsets onApplyWindowInsets(WindowInsets insets) {
        return super.onApplyWindowInsets(insets);
    }

    @Override
    public void setOnApplyWindowInsetsListener(OnApplyWindowInsetsListener listener) {
        super.setOnApplyWindowInsetsListener(listener);
    }

    @Override
    public WindowInsets dispatchApplyWindowInsets(WindowInsets insets) {
        return super.dispatchApplyWindowInsets(insets);
    }

    @Override
    public WindowInsets getRootWindowInsets() {
        return super.getRootWindowInsets();
    }

    @Override
    public WindowInsets computeSystemWindowInsets(WindowInsets in, Rect outLocalInsets) {
        return super.computeSystemWindowInsets(in, outLocalInsets);
    }

    @Override
    public void setFitsSystemWindows(boolean fitSystemWindows) {
        super.setFitsSystemWindows(fitSystemWindows);
    }

    @Override
    public boolean getFitsSystemWindows() {
        return super.getFitsSystemWindows();
    }

    @Override
    public void requestFitSystemWindows() {
        super.requestFitSystemWindows();
    }

    @Override
    public void requestApplyInsets() {
        super.requestApplyInsets();
    }

    @Override
    public int getVisibility() {
        return super.getVisibility();
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }

    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }

    @Override
    public boolean isInputMethodTarget() {
        return super.isInputMethodTarget();
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        return super.onTextContextMenuItem(id);
    }

    @Override
    public boolean performLongClick() {
        return super.performLongClick();
    }

    @Override
    public boolean performContextClick() {
        return super.performContextClick();
    }

    @Override
    public boolean showContextMenu() {
        return super.showContextMenu();
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback) {
        return super.startActionMode(callback);
    }

    @Override
    public ActionMode startActionMode(ActionMode.Callback callback, int type) {
        return super.startActionMode(callback, type);
    }

    @Override
    public void setOnKeyListener(OnKeyListener l) {
        super.setOnKeyListener(l);
    }

    @Override
    public void setOnTouchListener(OnTouchListener l) {
        super.setOnTouchListener(l);
    }

    @Override
    public void setOnGenericMotionListener(OnGenericMotionListener l) {
        super.setOnGenericMotionListener(l);
    }

    @Override
    public void setOnHoverListener(OnHoverListener l) {
        super.setOnHoverListener(l);
    }

    @Override
    public void setOnDragListener(OnDragListener l) {
        super.setOnDragListener(l);
    }

    @Override
    public boolean requestRectangleOnScreen(Rect rectangle) {
        return super.requestRectangleOnScreen(rectangle);
    }

    @Override
    public boolean requestRectangleOnScreen(Rect rectangle, boolean immediate) {
        return super.requestRectangleOnScreen(rectangle, immediate);
    }

    @Override
    public void clearFocus() {
        super.clearFocus();
    }

    @Override
    public boolean hasFocus() {
        return super.hasFocus();
    }

    @Override
    public boolean hasFocusable() {
        return super.hasFocusable();
    }

    @Override
    protected void onScrollChanged(int horiz, int vert, int oldHoriz, int oldVert) {
        super.onScrollChanged(horiz, vert, oldHoriz, oldVert);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    @Override
    public void setScrollX(int value) {
        super.setScrollX(value);
    }

    @Override
    public void setScrollY(int value) {
        super.setScrollY(value);
    }

    @Override
    public void getDrawingRect(Rect outRect) {
        super.getDrawingRect(outRect);
    }

    @Override
    public Matrix getMatrix() {
        return super.getMatrix();
    }

    @Override
    public float getCameraDistance() {
        return super.getCameraDistance();
    }

    @Override
    public void setCameraDistance(float distance) {
        super.setCameraDistance(distance);
    }

    @Override
    public float getRotation() {
        return super.getRotation();
    }

    @Override
    public void setRotation(float rotation) {
        super.setRotation(rotation);
    }

    @Override
    public float getRotationY() {
        return super.getRotationY();
    }

    @Override
    public void setRotationY(float rotationY) {
        super.setRotationY(rotationY);
    }

    @Override
    public float getRotationX() {
        return super.getRotationX();
    }

    @Override
    public void setRotationX(float rotationX) {
        super.setRotationX(rotationX);
    }

    @Override
    public float getScaleX() {
        return super.getScaleX();
    }

    @Override
    public void setScaleX(float scaleX) {
        super.setScaleX(scaleX);
    }

    @Override
    public float getScaleY() {
        return super.getScaleY();
    }

    @Override
    public void setScaleY(float scaleY) {
        super.setScaleY(scaleY);
    }

    @Override
    public float getPivotX() {
        return super.getPivotX();
    }

    @Override
    public void setPivotX(float pivotX) {
        super.setPivotX(pivotX);
    }

    @Override
    public float getPivotY() {
        return super.getPivotY();
    }

    @Override
    public void setPivotY(float pivotY) {
        super.setPivotY(pivotY);
    }

    @Override
    public float getAlpha() {
        return super.getAlpha();
    }

    @Override
    public boolean isSuggestionsEnabled() {
        return super.isSuggestionsEnabled();
    }

    @Override
    public void setCustomSelectionActionModeCallback(ActionMode.Callback actionModeCallback) {
        super.setCustomSelectionActionModeCallback(actionModeCallback);
    }

    @Override
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return super.getCustomSelectionActionModeCallback();
    }

    @Override
    public void setCustomInsertionActionModeCallback(ActionMode.Callback actionModeCallback) {
        super.setCustomInsertionActionModeCallback(actionModeCallback);
    }

    @Override
    public ActionMode.Callback getCustomInsertionActionModeCallback() {
        return super.getCustomInsertionActionModeCallback();
    }

    @Override
    public int getOffsetForPosition(float x, float y) {
        return super.getOffsetForPosition(x, y);
    }

    @Override
    public boolean onDragEvent(DragEvent event) {
        return super.onDragEvent(event);
    }

    @Override
    public boolean dispatchDragEvent(DragEvent event) {
        return super.dispatchDragEvent(event);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    public int getOverScrollMode() {
        return super.getOverScrollMode();
    }

    @Override
    public void setOverScrollMode(int overScrollMode) {
        super.setOverScrollMode(overScrollMode);
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        super.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return super.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return super.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        super.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return super.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int[] offsetInWindow) {
        return super.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return super.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return super.dispatchNestedPreFling(velocityX, velocityY);
    }

    @Override
    public void setTextDirection(int textDirection) {
        super.setTextDirection(textDirection);
    }

    @Override
    public int getTextDirection() {
        return super.getTextDirection();
    }

    @Override
    public boolean canResolveTextDirection() {
        return super.canResolveTextDirection();
    }

    @Override
    public boolean isTextDirectionResolved() {
        return super.isTextDirectionResolved();
    }

    @Override
    public void setTextAlignment(int textAlignment) {
        super.setTextAlignment(textAlignment);
    }

    @Override
    public int getTextAlignment() {
        return super.getTextAlignment();
    }

    @Override
    public boolean canResolveTextAlignment() {
        return super.canResolveTextAlignment();
    }

    @Override
    public boolean isTextAlignmentResolved() {
        return super.isTextAlignmentResolved();
    }

    @Override
    public ViewPropertyAnimator animate() {
        return super.animate();
    }

    @Override
    public String getTransitionName() {
        return super.getTransitionName();
    }

    @Override
    public void onRtlPropertiesChanged(int layoutDirection) {
        super.onRtlPropertiesChanged(layoutDirection);
    }

    @Override
    public boolean canResolveLayoutDirection() {
        return super.canResolveLayoutDirection();
    }

    @Override
    public boolean isLayoutDirectionResolved() {
        return super.isLayoutDirectionResolved();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected int getWindowAttachCount() {
        return super.getWindowAttachCount();
    }

    @Override
    public IBinder getWindowToken() {
        return super.getWindowToken();
    }

    @Override
    public WindowId getWindowId() {
        return super.getWindowId();
    }

    @Override
    public IBinder getApplicationWindowToken() {
        return super.getApplicationWindowToken();
    }

    @Override
    public Display getDisplay() {
        return super.getDisplay();
    }

    @Override
    public void onCancelPendingInputEvents() {
        super.onCancelPendingInputEvents();
    }

    @Override
    public void saveHierarchyState(SparseArray<Parcelable> container) {
        super.saveHierarchyState(container);
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        super.dispatchSaveInstanceState(container);
    }
}
