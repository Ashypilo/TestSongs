package com.ua.ashypilov.testsongs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.prush.bndrsntchtimer.BndrsntchTimer;


public class PlaySong {

    private LinearLayout layout;
    private TextView textViewGroup;
    private TextView textViewSong;
    private BndrsntchTimer progress;

    public PlaySong(BndrsntchTimer progress) {
        this.progress = progress;
    }

    public void show(MainActivity activity, Song songs) {
        layout = activity.findViewById(R.id.choiceLayout);
        textViewGroup = activity.findViewById(R.id.leftChoiceTextView);
        textViewSong = activity.findViewById(R.id.rightChoiceTextView);
        textViewGroup.setText(songs.getTextGroup());
        textViewSong.setText(songs.getTextSong());
        progress.reset(new BndrsntchTimer.OnTimerResetListener() {
            @Override
            public void onTimerResetCompleted() {
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(layout, "alpha", 0f, 1f);
                objectAnimator.setDuration(100);
                objectAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        progress.start(songs.getLengthSong());
                    }
                });
                objectAnimator.start();
            }
        });

        activity.getLifecycle().addObserver(progress.getLifecycleObserver());
    }
}
