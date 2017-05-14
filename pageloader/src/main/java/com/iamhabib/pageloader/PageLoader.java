package com.iamhabib.pageloader;

/**
 * Created by HABIB on 5/2/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class PageLoader extends RelativeLayout {

    public static final String ROTATE_MODE = "rotate";
    public static final String FLIP_MODE = "flip";
    private RelativeLayout progressView;
    private LinearLayout progressViewStart;
    private LinearLayout progressViewFailed;
    private LinearLayout progressViewNoConnection;
    private ImageView imageLoading;
    private TextView textLoading;
    private ImageView imageError;
    private TextView textError;
    private ImageView imageNoConnection;
    private TextView textNoConnection;
    private TypedArray array;
    private Context mContext;

    public PageLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.pageloader, this, true);
        mContext = context;
        array = context.obtainStyledAttributes(attrs, R.styleable.PageLoader, 0, 0);
        initView();
    }

    private void initView() {
        progressView = (RelativeLayout) findViewById(R.id.progressPage);
        progressViewStart = (LinearLayout) findViewById(R.id.progressPageStart);
        progressViewFailed = (LinearLayout) findViewById(R.id.progressPageFailed);
        progressViewFailed.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoading();
            }
        });
        progressViewNoConnection = (LinearLayout) findViewById(R.id.progressPageNoConnection);
        progressViewNoConnection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startLoading();
            }
        });
        imageLoading = (ImageView) findViewById(R.id.mImageLoading);
        textLoading = (TextView) findViewById(R.id.mTextLoading);
        imageError = (ImageView) findViewById(R.id.mImageError);
        textError = (TextView) findViewById(R.id.mTextError);
        imageNoConnection = (ImageView) findViewById(R.id.mImageNoConnection);
        textNoConnection = (TextView) findViewById(R.id.mTextNoConnection);
        setData();
    }

    private void setData() {
        try {
            int textSize = array.getDimensionPixelSize(R.styleable.PageLoader_setTextSize, 0);
            setTextSize(textSize);
            int textColor = array.getColor(R.styleable.PageLoader_setTextColor, 0);
            setTextColor(textColor);
            int errorTextColor = array.getColor(R.styleable.PageLoader_setErrorTextColor, 0);
            setErrorTextColor(errorTextColor);
            int loadingTextColor = array.getColor(R.styleable.PageLoader_setLoadingTextColor, 0);
            setLoadingTextColor(loadingTextColor);
            int noConnectionTextColor = array.getColor(R.styleable.PageLoader_setNoConnectionTextColor, 0);
            setNoConnectionTextColor(noConnectionTextColor);
            String loadingText = array.getString(R.styleable.PageLoader_setLoadingText);
            setTextLoading(loadingText);
            String errorText = array.getString(R.styleable.PageLoader_setErrorText);
            setTextError(errorText);
            int backgroundColor = array.getColor(R.styleable.PageLoader_setPageLoaderBackground, 0);
            setPageLoaderBackground(backgroundColor);
            int loadingImage = array.getResourceId(R.styleable.PageLoader_setLoadingImage, 0);
            setImageLoading(loadingImage);
            String animationMode = array.getString(R.styleable.PageLoader_setLoadingAnimationMode);
            setLoadingAnimationMode(animationMode);
            int errorImage = array.getResourceId(R.styleable.PageLoader_setErrorImage, 0);
            setImageError(errorImage);
            int errorImageHeight = array.getDimensionPixelSize(R.styleable.PageLoader_setErrorImageHeight, 0);
            setErrorImageHeight(errorImageHeight);
            int errorImageWidth = array.getDimensionPixelSize(R.styleable.PageLoader_setErrorImageWidth, 0);
            setErrorImageWidth(errorImageWidth);
            int loadingImageHeight = array.getDimensionPixelSize(R.styleable.PageLoader_setLoadingImageHeight, 0);
            setLoadingImageHeight(loadingImageHeight);
            int loadingImageWidth = array.getDimensionPixelSize(R.styleable.PageLoader_setLoadingImageWidth, 0);
            setLoadingImageWidth(loadingImageWidth);
            String noConnectionText = array.getString(R.styleable.PageLoader_setNoConnectionText);
            setTextNoConnection(noConnectionText);
            int noConnectionImage = array.getResourceId(R.styleable.PageLoader_setNoConnectionImage, 0);
            setImageNoConnection(noConnectionImage);
            int noConnectionImageHeight = array.getDimensionPixelSize(R.styleable.PageLoader_setNoConnectionImageHeight, 0);
            setNoConnectionImageHeight(noConnectionImageHeight);
            int noConnectionImageWidth = array.getDimensionPixelSize(R.styleable.PageLoader_setNoConnectionImageWidth, 0);
            setNoConnectionImageWidth(noConnectionImageWidth);
        } finally {
            array.recycle();
        }
    }

    public void setPageLoaderBackground(int i) {
        if (i != 0) {
            progressView.setBackgroundColor(i);
        } else {
            progressView.setBackgroundResource(R.color.white);
        }
    }

    public void setTextSize(int i) {
        if (i != 0) {
            textError.setTextSize(TypedValue.COMPLEX_UNIT_SP, i);
            textLoading.setTextSize(TypedValue.COMPLEX_UNIT_SP, i);
            textNoConnection.setTextSize(TypedValue.COMPLEX_UNIT_SP, i);
        } else {
            textError.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textLoading.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            textNoConnection.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        }
    }

    public void setTextColor(int c) {
        if (c != 0) {
            textError.setTextColor(c);
            textLoading.setTextColor(c);
            textNoConnection.setTextColor(c);
        } else {
            textError.setTextColor(ContextCompat.getColor(mContext, R.color.colorDefault));
            textLoading.setTextColor(ContextCompat.getColor(mContext, R.color.colorDefault));
            textNoConnection.setTextColor(ContextCompat.getColor(mContext, R.color.colorDefault));
        }
    }

    public void setErrorTextColor(int c) {
        if (c != 0) {
            textError.setTextColor(c);
        } else {
            textError.setTextColor(ContextCompat.getColor(mContext, R.color.colorDefault));
        }
    }

    public void setNoConnectionTextColor(int c) {
        if (c != 0) {
            textNoConnection.setTextColor(c);
        } else {
            textNoConnection.setTextColor(ContextCompat.getColor(mContext, R.color.colorDefault));
        }
    }

    public void setLoadingTextColor(int c) {
        if (c != 0) {
            textLoading.setTextColor(c);
        } else {
            textLoading.setTextColor(ContextCompat.getColor(mContext, R.color.colorDefault));
        }
    }

    public void setTextLoading(String s) {
        if (!TextUtils.isEmpty(s)) {
            textLoading.setText(s);
        } else {
            textLoading.setText(getResources().getString(R.string.loading_text));
        }
    }

    public void setImageLoading(int i) {
        if (i != 0) {
            imageLoading.setImageResource(i);
        } else {
            imageLoading.setImageResource(R.drawable.ic_loading);
        }
    }

    public void setLoadingImageHeight(int i) {
        if (i != 0) {
            imageLoading.getLayoutParams().height = i;
        } else {
            imageLoading.getLayoutParams().height = 80;
        }
    }

    public void setLoadingImageWidth(int i) {
        if (i != 0) {
            imageLoading.getLayoutParams().width = i;
        } else {
            imageLoading.getLayoutParams().width = 80;
        }
    }

    public void setLoadingAnimationMode(String s) {

        if (!TextUtils.isEmpty(s)) {
            switch (s.toLowerCase()) {
                case ROTATE_MODE:
                    imageLoading.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim));
                    break;
                case FLIP_MODE:
                    imageLoading.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.flip_anim));
                    break;
                default:
                    imageLoading.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim));
                    break;
            }
        } else {
            imageLoading.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim));
        }
    }

    public void setTextError(String s) {
        if (!TextUtils.isEmpty(s)) {
            textError.setText(s);
        } else {
            textError.setText(getResources().getString(R.string.error_text));
        }
    }

    public void setImageError(int i) {
        if (i != 0) {
            imageError.setImageResource(i);
        } else {
            imageError.setImageResource(R.drawable.ic_error);
        }
    }

    public void setImageNoConnection(int i) {
        if (i != 0) {
            imageNoConnection.setImageResource(i);
        } else {
            imageNoConnection.setImageResource(R.drawable.ic_error);
        }
    }

    public void setErrorImageHeight(int i) {
        if (i != 0) {
            imageError.getLayoutParams().height = i;
        } else {
            imageError.getLayoutParams().height = 80;
        }
    }

    public void setErrorImageWidth(int i) {
        if (i != 0) {
            imageError.getLayoutParams().width = i;
        } else {
            imageError.getLayoutParams().width = 80;
        }
    }

    public void setTextNoConnection(String s) {
        if (!TextUtils.isEmpty(s)) {
            textNoConnection.setText(s);
        } else {
            textNoConnection.setText(getResources().getString(R.string.connection_failed_text));
        }
    }

    public void setNoConnectionImageHeight(int i) {
        if (i != 0) {
            imageNoConnection.getLayoutParams().height = i;
        } else {
            imageNoConnection.getLayoutParams().height = 80;
        }
    }

    public void setNoConnectionImageWidth(int i) {
        if (i != 0) {
            imageNoConnection.getLayoutParams().width = i;
        } else {
            imageNoConnection.getLayoutParams().width = 80;
        }
    }

    public void setCustomAnimation(Animation a) {
        if (a != null) {
            imageLoading.startAnimation(a);
        } else {
            imageLoading.startAnimation(AnimationUtils.loadAnimation(mContext, R.anim.rotate_anim));
        }
    }

    public void setCustomFont(Typeface t) {
        if (t != null) {
            textError.setTypeface(t);
            textLoading.setTypeface(t);
        }
    }

    public void setOnRetry(View.OnClickListener clickListener) {
        progressViewFailed.setOnClickListener(clickListener);
        progressViewNoConnection.setOnClickListener(clickListener);
    }

    public void startLoading() {
        progressView.setVisibility(View.VISIBLE);
        progressViewStart.setVisibility(View.VISIBLE);
        progressViewFailed.setVisibility(View.GONE);
        progressViewNoConnection.setVisibility(View.GONE);
    }

    public void stopLoading() {
        progressView.setVisibility(View.GONE);
        progressViewStart.setVisibility(View.GONE);
        progressViewFailed.setVisibility(View.GONE);
        progressViewNoConnection.setVisibility(View.GONE);
    }

    public void setFailed() {
        progressView.setVisibility(View.VISIBLE);
        progressViewStart.setVisibility(View.GONE);
        progressViewFailed.setVisibility(View.VISIBLE);
        progressViewNoConnection.setVisibility(View.GONE);
    }

    public void setNoConnection() {
        progressView.setVisibility(View.VISIBLE);
        progressViewStart.setVisibility(View.GONE);
        progressViewFailed.setVisibility(View.GONE);
        progressViewNoConnection.setVisibility(View.VISIBLE);
    }
}

