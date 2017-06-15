package com.chhd.cniaoplay.inject.component;

import com.chhd.cniaoplay.inject.AppScope;
import com.chhd.cniaoplay.inject.module.DownloadManagerModule;
import com.chhd.cniaoplay.ui.base.SimpleDownloadManagerFragment;
import com.chhd.cniaoplay.ui.fragment.download_manager.DownloadedFragment;
import com.chhd.cniaoplay.ui.fragment.download_manager.DownloadingFragment;
import com.chhd.cniaoplay.view.DownloadManagerView;

import dagger.Component;

/**
 * Created by CWQ on 2017/6/10.
 */

@AppScope
@Component(modules = {DownloadManagerModule.class}, dependencies = {AppComponent.class})
public interface DownloadManagerComponent {

    void inject(SimpleDownloadManagerFragment fragment);

}
