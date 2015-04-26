package at.droelf.clippy.utils;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import at.droelf.clippy.model.gui.UiAnimation;
import at.droelf.clippy.model.gui.UiBranch;
import at.droelf.clippy.model.gui.UiFrame;

public class AnimationUtil {

    private final static int MAX_BRANCHES = 5;

    public static AnimationDrawableResult getAnimationDrawable(Context context, UiAnimation uiAnimation, int numberOverlays){
        final List<UiFrame> uiFrames = getUiFrames(uiAnimation.getUiFrames());

        final List<SoundMapping> sound = getSound(uiFrames);
        final List<AnimationDrawable> animationDrawables = getAnimationDrawAbles(context, uiFrames, numberOverlays);

        return new AnimationDrawableResult(animationDrawables, sound);
    }


    private static List<UiFrame> getUiFrames(List<UiFrame> frames){
        final Random random = new Random();

        final List<UiFrame> uiFrames = new ArrayList<>();

        int branches = 0;

        //for(UiFrame frame : frames){
        for(int i = 0; i < frames.size(); i++){
            UiFrame frame = frames.get(i);

            if(frame.getBranches() != null && branches < MAX_BRANCHES){
                int rnd = random.nextInt(100);

                for(UiBranch uiBranch : frame.getBranches()){
                    if(rnd <= uiBranch.getWeight()){
                        i = uiBranch.getFrameIndex();
                        frame = frames.get(i);
                        branches++;
                        break;
                    }
                    rnd -= uiBranch.getWeight();
                }
            }
            uiFrames.add(frame);
        }

        return uiFrames;
    }

    private static List<AnimationDrawable> getAnimationDrawAbles(Context context, List<UiFrame> frames, int numberOverlays){
        final List<AnimationDrawable> drawables = new ArrayList<>();
        for(int i = 0; i < numberOverlays; i++){
            final AnimationDrawable animationDrawable = new AnimationDrawable();
            animationDrawable.setOneShot(true);
            drawables.add(animationDrawable);
        }

        for(UiFrame uiFrame : frames){
            final List<Integer> imageIds = uiFrame.getImageIds();
            for(int j = 0; j < imageIds.size(); j++){
                final Drawable drawable = context.getResources().getDrawable(imageIds.get(j));
                drawables.get(j).addFrame(drawable, uiFrame.getDuration());
            }
        }

        return drawables;
    }

    private static List<SoundMapping> getSound(List<UiFrame> frames){
        final List<SoundMapping> soundMappings = new ArrayList<>();
        long time = 0;

        for(UiFrame frame : frames){
            if(frame.getSoundId() != null){
                soundMappings.add(new SoundMapping(time, frame.getSoundId()));
            }
            time += frame.getDuration();
        }

        return soundMappings;
    }


    public static class SoundMapping{
        private final long time;
        private final int soundId;

        SoundMapping(long time, int soundId) {
            this.time = time;
            this.soundId = soundId;
        }

        public long getTime() {
            return time;
        }

        public int getSoundId() {
            return soundId;
        }
    }

    public static class AnimationDrawableResult{
        private final List<AnimationDrawable> animationDrawables;
        private final List<SoundMapping> soundMappings;

        AnimationDrawableResult(List<AnimationDrawable> animationDrawables, List<SoundMapping> soundMappings) {
            this.animationDrawables = animationDrawables;
            this.soundMappings = soundMappings;
        }

        public List<AnimationDrawable> getAnimationDrawables() {
            return animationDrawables;
        }

        public List<SoundMapping> getSoundMappings() {
            return soundMappings;
        }
    }


}
