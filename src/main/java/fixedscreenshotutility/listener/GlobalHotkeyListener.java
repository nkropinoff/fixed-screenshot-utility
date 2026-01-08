package fixedscreenshotutility.listener;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import fixedscreenshotutility.service.ScreenshotService;

public class GlobalHotkeyListener implements NativeKeyListener {

    private final ScreenshotService screenshotService;

    public GlobalHotkeyListener(ScreenshotService screenshotService) {
        this.screenshotService = screenshotService;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        if (nativeEvent.getKeyCode() == NativeKeyEvent.VC_F) {

            int mods = nativeEvent.getModifiers();
            boolean shift = (mods & NativeKeyEvent.SHIFT_MASK) != 0;
            boolean win = (mods & NativeKeyEvent.META_MASK) != 0;

            if (shift && win) {
                screenshotService.captureToClipboard();
            }
        }
    }
}
