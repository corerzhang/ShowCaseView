package corer.me.showcase.listener;

import corer.me.showcase.ShowCaseView;

/**
 * Created by corer.zhang on 16/4/30.
 */
public interface ShowCaseListener {
    void onShowCaseDisplayed(ShowCaseView showCaseView);

    void onShowCaseDismissed(ShowCaseView showCaseView);
}
