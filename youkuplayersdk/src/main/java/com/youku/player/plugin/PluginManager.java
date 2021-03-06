package com.youku.player.plugin;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

import com.baseproject.utils.Logger;
import com.youku.player.base.GoplayException;
import com.youku.player.base.YoukuBasePlayerActivity;
import com.youku.player.ui.interf.IPluginManager;

/**
 * Class PluginManager
 */
public class PluginManager implements IPluginManager{

	YoukuBasePlayerActivity mActivity;

	public PluginManager(YoukuBasePlayerActivity youkuBasePlayerActivity) {
		this.mActivity = youkuBasePlayerActivity;
	}

	/**
	 * {@link #pluginOverlays}
	 * 
	 * @param plugin
	 */
	public void addMediaPlayerListener(PluginOverlay plugin) {
	}

	/**
	 * {@link #pluginOverlays}
	 * 
	 * @param plugin
	 */
	public void removeMediaPlayerListener(PluginOverlay plugin) {
	}

	public void onBufferingUpdateListener(int percent) {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onBufferingUpdateListener(percent);
		}
	}

	public void onCompletionListener() {
		Logger.d("PlayFlow", "onCompletionListener");
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onCompletionListener();
		}
	}

	public boolean onError(int what, int extra) {
		boolean result = false;
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			if (pluginOverlay.onErrorListener(what, extra))
				result = true;
		}
		return result;
	}

	public void onPrepared() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.OnPreparedListener();
		}
	}

	public void onSeekComplete() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.OnSeekCompleteListener();
		}
	}

	public void onVideoSizeChanged(int width, int height) {
		try{
			for (PluginOverlay pluginOverlay : pluginOverlays) {
				pluginOverlay.OnVideoSizeChangedListener(width, height);
			}
		}catch(Exception e){
			
		}
	}

	public void onTimeout() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.OnTimeoutListener();
		}
	}
	
	public void onNotifyChangeVideoQuality(){
		for (PluginOverlay pluginOverlay: pluginOverlays) {
			pluginOverlay.onNotifyChangeVideoQuality();
		}
	}

	public void onCurrentPositionChange(int currentPosition) {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.OnCurrentPositionChangeListener(currentPosition);
		}
	}

	public void onLoaded() {
		Logger.d("PlayFlow", "???????????????????????? onLoaded");
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onLoadedListener();
		}
	}

	public void onLoading() {
		Logger.d("PlayFlow", "?????????????????? onLoading");
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onLoadingListener();
		}
	}

	public void notifyChangeQuality() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onNotifyChangeVideoQuality();
		}
	}

	public void onLoginSucc() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.loginSucc();
		}
	}

	public void onLoginFail() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.loginFail();
		}
	}

	public void onUnFavor() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onUnFavor();
		}
	}
	
	public void onNetSpeedChange(int speed){
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onNetSpeedChange(speed);
		}
	}

	/* *
	 * ??????????????????????????????????????????????????????????????????????????????MediaPlayerObserver???LoginObserver?????????
	 */
	private Set<PluginOverlay> pluginOverlays = Collections
			.synchronizedSet(new HashSet<PluginOverlay>());

	/**
	 * @param plugin
	 * @param stub
	 */
	public void addPlugin(PluginOverlay plugin, ViewGroup stub) {
		try {
			for (PluginOverlay pluginOverlay : pluginOverlays) {
				pluginOverlay.setVisible(false);
			}

			// stub.addView(plugin, new
			// LinearLayout.LayoutParams(stub.getWidth(),
			// stub.getHeight()));
			if (!pluginOverlays.contains(plugin)) {
				stub.addView(plugin);
				pluginOverlays.add(plugin);
			}
			plugin.setVisible(true);
			plugin.onPluginAdded();
			mActivity.getWindow().setLayout(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ???????????????
	 * 
	 * @param plugin
	 * @param stub
	 */
	public void addPluginAbove(PluginOverlay plugin, ViewGroup stub) {
		try {
			if (!pluginOverlays.contains(plugin)) {
				pluginOverlays.add(plugin);
				stub.addView(plugin);
			}
			plugin.setVisible(true);
			plugin.onPluginAdded();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * ???????????????
	 * 
	 * @param plugin
	 */
	public void addPluginInvisibaleButReceiveMsg(PluginOverlay plugin) {
		try {
			if (!pluginOverlays.contains(plugin)) {
				pluginOverlays.add(plugin);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addYoukuPlayerView(PluginOverlay plugin) {
		if (!pluginOverlays.contains(plugin)) {
			pluginOverlays.add(plugin);
			plugin.onPluginAdded();
		}
	}

	public void addInvestigatePlugin(PluginOverlay plugin, ViewGroup stub) {
		try {
			if (!pluginOverlays.contains(plugin)) {
				pluginOverlays.add(plugin);
				stub.addView(plugin);
			}
			plugin.onPluginAdded();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void addPlugin(PluginOverlay plugin, FrameLayout stub, int width,
			int height) {
		try {
			for (PluginOverlay pluginOverlay : pluginOverlays) {
				pluginOverlay.setVisible(false);
			}
			if (!pluginOverlays.contains(plugin)) {
				stub.addView(plugin, width, height);
				pluginOverlays.add(plugin);
			}
			// if(pluginOverlays.contains(plugin)){
			// pluginOverlays.remove(plugin);
			// }
			// stub.addView(plugin, width, height);
			// pluginOverlays.add(plugin);

			plugin.setVisible(true);
			plugin.onPluginAdded();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// /**
	// * ???????????????????????????????????????plugin????????????????????????????????????????????????
	// *
	// * @param plugin
	// */
	// private void pluginRemoveFromParent(PluginOverlay plugin) {
	// if (plugin.getParent() != null)
	// ((FrameLayout) plugin.getParent()).removeView(plugin);
	// }

	public void removePlugin(PluginOverlay plugin, FrameLayout stub) {
		stub.removeView(plugin);
	}

	/**
	 * {@link #pluginOverlays}
	 */
	public void addLoginListener() {
	}

	/**
	 * {@link #pluginOverlays}
	 */
	public void removeLoginListener() {
	}

	public void setUp() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onUp();
		}
	}

	public void setDown() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onDown();
		}

	}

	public void setFav() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onFavor();
		}

	}

	public void clearUpDownFav() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onClearUpDownFav();
		}
	}

	public void onVolumnUp() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onVolumnUp();
		}
	}

	public void onVolumnDown() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onVolumnDown();
		}
	}

	public void onMute(boolean mute) {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onMute(mute);
		}
	}

	/**
	 * activity onStart?????????
	 */
	public void onStart() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onStart();
		}
	}

	/**
	 * activity onPause?????????
	 */
	public void onPause() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onPause();
		}
	}

	public void onChangeVideo() {
		try {
			for (PluginOverlay pluginOverlay : pluginOverlays) {
				pluginOverlay.onVideoChange();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onVideoInfoGetting() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onVideoInfoGetting();
		}
	}

	public void onVideoInfoGetted() {
		try {
			for (PluginOverlay pluginOverlay : pluginOverlays) {
				pluginOverlay.onVideoInfoGetted();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onVideoInfoGetFail(boolean retry) {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onVideoInfoGetFail(retry);
		}
	}

	public void onRealVideoStart() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onRealVideoStart();
		}
	}

	public void onPlayNoRightVideo(GoplayException e) {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onPlayNoRightVideo(e);
		}
	}

	public void onPlayReleateNoRightVideo() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onPlayReleateNoRightVideo();
		}
	}

	/**
	 * ?????????????????????????????????????????????
	 */
	public void newVideo() {

	}

	/**
	 * ????????????
	 */
	public void onDownloadSucc() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onDownloadSucc();
		}
	}

	public void OnDownloadFail(String msg) {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onDownloadFail(msg);
		}
	}
	
	public void onSubscribe() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onSubscribe();
		}
	}
	
	public void onUnSubscribe() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onUnSubscribe();
		}
	}
	
	public void onRelease(){
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onRelease();
		}
	}
	
	public void onReplay() {
		for (PluginOverlay pluginOverlay : pluginOverlays) {
			pluginOverlay.onReplay();
		}
	}
}
