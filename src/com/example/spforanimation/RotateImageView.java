package com.example.spforanimation;

import java.util.ArrayList;
import java.util.List;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class RotateImageView extends ImageView {
	private Rotate3dAnimation animation;

	public RotateImageView(Context context, AttributeSet attrs) {
		super(context, attrs);

	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		System.out.println("onLayout->hree");
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		System.out.println("onMeasure->hree");
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		System.out.println("onWindowFocusChanged->hree");
		super.onWindowFocusChanged(hasWindowFocus);
		this.startAnimation(animationSet);
	}

	private AnimationSet animationSet;
	private int width;

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		System.out.println("onSizeChanged->hree");
		if (animation == null) {
			animation = new Rotate3dAnimation(0, 360, w / 2, h / 2, 1, true);
			animation.setDuration(1000);
			animation.setFillAfter(true);
			animationSet = new AnimationSet(true);
			animationSet.addAnimation(animation);

			// animation=new Rotate3dAnimation(360, 0, w/2, h/2, 0, true);
			// animation.setDuration(3000);
			// animation.setFillAfter(true);
			// animationSet.addAnimation(animation);
			//
			width = h;

			animation.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
				}

				@Override
				public void onAnimationEnd(Animation animation) {
					startTrans();
				}
			});
		}
	}

	private void startTrans() {
		ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationX",
				0, width / 3);
		animator.setDuration(200);
		animator.setInterpolator(new DecelerateInterpolator());
		ObjectAnimator animator2 = ObjectAnimator.ofFloat(this, "translationX",
				width / 3, -width / 4);
		animator2.setDuration(200);
		animator2.setInterpolator(new DecelerateInterpolator());
		ObjectAnimator animator3 = ObjectAnimator.ofFloat(this, "translationX",
				-width / 4, width / 5);
		animator3.setDuration(200);
		animator3.setInterpolator(new DecelerateInterpolator());
		ObjectAnimator animator4 = ObjectAnimator.ofFloat(this, "translationX",
				width / 5, -width / 6);
		animator4.setDuration(200);
		animator4.setInterpolator(new DecelerateInterpolator());
		ObjectAnimator animator5 = ObjectAnimator.ofFloat(this, "translationX",
				-width / 6, 0);
		animator5.setDuration(200);
		animator5.setInterpolator(new DecelerateInterpolator());
		AnimatorSet set = new AnimatorSet();
		List<Animator> items = new ArrayList<Animator>();
		items.add(animator);
		items.add(animator2);
		items.add(animator3);
		items.add(animator4);
		items.add(animator5);
		set.playSequentially(items);
		set.start();
	}

}
