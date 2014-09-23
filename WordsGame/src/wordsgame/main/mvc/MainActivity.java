package wordsgame.main.mvc;

import java.util.HashMap;
import java.util.Map;

import smallgameengine.help.FIXVALUE;

import com.example.wordsgame.R;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity  implements FIXVALUE{
	private OpenGlRenderer myrenderer = null;
	private GLListener listener;
	public MediaPlayer mp;
	public SoundPool sp;
	public Map<Integer, Integer> map;
	private GestureDetector mGestureDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//////
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		myrenderer = new OpenGlRenderer(this);
		mGestureDetector = new GestureDetector(this,
				new GLListener(myrenderer,
						(Vibrator) getSystemService(Service.VIBRATOR_SERVICE),
						this, mp));

		GLSurfaceView view = new GLSurfaceView(this);
		view.setRenderer(myrenderer);
		setContentView(view);
	}
	
	@Override
    public boolean onTouchEvent(MotionEvent event) {
    
		if(event.getAction()==MotionEvent.ACTION_UP){
			if(myrenderer.game!=null)
			myrenderer.game.touchUp(event);
		}

        if (mGestureDetector.onTouchEvent(event))
            return true;
        else
            return false;
    }

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO �Զ����ɷ������
		
		if (keyCode == KeyEvent.KEYCODE_MENU) {

		}
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// finish();
			switch (myrenderer.scene) {
			case HOMEPAGE:
				new AlertDialog.Builder(this)
						.setTitle("ȷ���˳���Ϸ��")
						// ���ñ���
						// .setCustomTitle(View) // ��һ�� View ��Ϊ����
						.setIcon(R.drawable.ic_launcher)
						// ���ñ���ͼƬ
						// .setMessage("��Ϣ") // ��Ҫ��ʾ�ĵ�������
						.setPositiveButton("ȷ��", new OnClickListener() { // ���õ����ȷ�ϰ�ť����ʾ���ı����Լ�������ť�����Ӧ��Ϊ
									@Override
									public void onClick(DialogInterface a0,
											int a1) {
										finish();
									}
								})
						.setNegativeButton("ȡ��", new OnClickListener() { // ���õ����ȷ�ϰ�ť����ʾ���ı����Լ�������ť�����Ӧ��Ϊ
									@Override
									public void onClick(DialogInterface a0,
											int a1) {

									}
								}).show();

				break;
			case GAME:
				myrenderer.scene=HOMEPAGE;
				break;
			}
		}
		return true;
	}

	public void initSoundPool() {
		sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		map = new HashMap<Integer, Integer>();
		// mp = MediaPlayer.create( this, R.raw.bgm );
		mp.setLooping(true);
		mp.start();
	}

	public void playSound(int id) {
		AudioManager am = (AudioManager) getSystemService(MainActivity.AUDIO_SERVICE);
		float audioMaxVolume = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float audioCurrentVolume = am
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float audioRatio = audioCurrentVolume / audioMaxVolume;
		sp.play(map.get(id), audioRatio, audioRatio, 1, 0, 1);
	}

}
