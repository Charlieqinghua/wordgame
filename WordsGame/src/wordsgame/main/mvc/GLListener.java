/*
 * �������� 2013-5-3
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package wordsgame.main.mvc;

import smallgameengine.help.CONFIG;
import smallgameengine.help.FIXVALUE;
import smallgameengine.image.Black;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

public class GLListener extends SimpleOnGestureListener implements FIXVALUE,
		CONFIG {
	private Context context;
	private OpenGlRenderer renderer;
	private Vibrator vib;
	private MediaPlayer mp;

	public GLListener(OpenGlRenderer renderer, Vibrator vib, Context context,
			MediaPlayer mp) {
		this.renderer = renderer;
		this.vib = vib;
		this.context = context;
		this.mp = mp;
	}

	@Override
	// ˫��������˫���ĵڶ���Touch downʱ��
	public boolean onDoubleTap(MotionEvent e) {
		// TODO �Զ����ɷ������
		// System.out.println("onDoubleTap Test");
		return super.onDoubleTap(e);
	}
	
	

	@Override
	// ˫���ĵڶ���Touch down��up���ᴥ��
	public boolean onDoubleTapEvent(MotionEvent e) {
		// TODO �Զ����ɷ������
		// System.out.println("onDoubleTapEvent Test");
		return super.onDoubleTapEvent(e);
	}
	
	@Override
	// ����ȥ
	public boolean onDown(MotionEvent event) {
		// TODO �Զ����ɷ������
		//System.out.println("onDown Test");

		float x = event.getX();
		float y = renderer.screenH - event.getY();
		float gameX = ((x / renderer.screenW) * renderer.gameScreenW);
		float gameY = ((y / renderer.screenH) * renderer.gameScreenH);
		// Log.v("www",""+gameX+":"+gameY);
		switch (renderer.scene) {
		/********************* �������� *********************/
		case START_TO_STARTPAGE:

			break;
		case STARTPAGE:

			break;
		case STARTPAGE_TO_HOMEPAGE:

			break;

		/********************* ��ҳ���� *********************/
		case TO_HOMEPAGE:

			break;
		case HOMEPAGE:
			if(renderer.single.isTouch(gameX, gameY)){
				renderer.black.isToBlack = true;
				renderer.scene = HOMEPAGE_TO_GAME;
			}
			if(renderer.setting.isTouch(gameX, gameY))
			{
				Intent intent=new Intent();
				intent.setClass(context, SetGame.class);
				context.startActivity(intent);
			}
			break;

		/********************* �������� *********************/
		case HOMEPAGE_TO_HELP:
			break;
		case HELP:
			break;
		case HELP_BACK_HOMEPAGE:
			break;

		/********************* ���ڽ��� *********************/
		case HOMEPAGE_TO_ABOUT:
			break;
		case ABOUT:
			break;
		case ABOUT_BACK_HOMEPAGE:
			break;

		/********************* ���ý��� *********************/
		case HOMEPAGE_TO_SETTING:
			break;
		case SETTING:
			break;
		case SETTING_BACK_HOMEPAGE:
			break;

		/********************* ���˽��� *********************/
		case HOMEPAGE_TO_SINGLEPLAY:
			break;
		case SINGLEPLAY:
			break;

		/********************* ������� *********************/
		case SINGLEPLAY_TO_CLASS:
			break;
		case CLASS:
			break;
		case CLASS_TO_MISSION:
			break;

		/********************* �ؿ����� *********************/
		case MISSION:
			break;
		case MISSION_TO_GAME:
			break;

		/********************* ��Ϸ���� *********************/
		case GAME:
			renderer.game.touchDown(gameX, gameY);
			break;
		case GAME_BACK_MISSION:
			break;
		}
		return super.onDown(event);
	}

	@Override
	// Touch�˻���һ������upʱ��
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {

		return super.onFling(e1, e2, velocityX, velocityY);
	}

	@Override
	public void onLongPress(MotionEvent event) {
		// TODO �Զ����ɷ������
		float x = event.getX();
		float y = renderer.screenH - event.getY();
		float gameX = ((x / renderer.screenW) * renderer.gameScreenW);
		float gameY = ((y / renderer.screenH) * renderer.gameScreenH);
		// Log.v("www",""+gameX+":"+gameY);
		switch (renderer.scene) {
		/********************* �������� *********************/
		case START_TO_STARTPAGE:
			break;
		case STARTPAGE:
			break;
		case STARTPAGE_TO_HOMEPAGE:
			break;
		/********************* ��ҳ���� *********************/
		case TO_HOMEPAGE:
			break;
		case HOMEPAGE:
			break;
		/********************* �������� *********************/
		case HOMEPAGE_TO_HELP:
			break;
		case HELP:
			break;
		case HELP_BACK_HOMEPAGE:
			break;
		/********************* ���ڽ��� *********************/
		case HOMEPAGE_TO_ABOUT:
			break;
		case ABOUT:
			break;
		case ABOUT_BACK_HOMEPAGE:
			break;
		/********************* ���ý��� *********************/
		case HOMEPAGE_TO_SETTING:
			break;
		case SETTING:
			break;
		case SETTING_BACK_HOMEPAGE:
			break;
		/********************* ���˽��� *********************/
		case HOMEPAGE_TO_SINGLEPLAY:
			break;
		case SINGLEPLAY:
			break;
		/********************* ������� *********************/
		case SINGLEPLAY_TO_CLASS:
			break;
		case CLASS:
			break;
		case CLASS_TO_MISSION:
			break;
		/********************* �ؿ����� *********************/
		case MISSION:
			break;
		case MISSION_TO_GAME:
			break;
		/********************* ��Ϸ���� *********************/
		case GAME:
			break;
		case GAME_BACK_MISSION:
			break;
		}
		super.onLongPress(event);
	}
	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO �Զ����ɷ������

		return super.onScroll(e1, e2, distanceX, distanceY);
	}

	@Override
	// û�й���֮ǰ��
	public void onShowPress(MotionEvent e) {
		// TODO �Զ����ɷ������
		//System.out.println("onShowPress");
		super.onShowPress(e);
	}

	@Override
	public boolean onSingleTapConfirmed(MotionEvent e) {
		// TODO �Զ����ɷ������
		//System.out.println("onSingleTapConfirmed");
		return super.onSingleTapConfirmed(e);
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event) {
		// TODO �Զ����ɷ������
		float x = event.getX();
		float y = renderer.screenH - event.getY();
		float gameX = ((x / renderer.screenW) * renderer.gameScreenW);
		float gameY = ((y / renderer.screenH) * renderer.gameScreenH);
		// Log.v("www",""+gameX+":"+gameY);
		switch (renderer.scene) {
		/********************* �������� *********************/
		case START_TO_STARTPAGE:
			break;
		case STARTPAGE:
			break;
		case STARTPAGE_TO_HOMEPAGE:
			break;
		/********************* ��ҳ���� *********************/
		case TO_HOMEPAGE:
			break;
		case HOMEPAGE:
			break;
		/********************* �������� *********************/
		case HOMEPAGE_TO_HELP:
			break;
		case HELP:
			break;
		case HELP_BACK_HOMEPAGE:
			break;
		/********************* ���ڽ��� *********************/
		case HOMEPAGE_TO_ABOUT:
			break;
		case ABOUT:
			break;
		case ABOUT_BACK_HOMEPAGE:
			break;
		/********************* ���ý��� *********************/
		case HOMEPAGE_TO_SETTING:
			break;
		case SETTING:
			break;
		case SETTING_BACK_HOMEPAGE:
			break;
		/********************* ���˽��� *********************/
		case HOMEPAGE_TO_SINGLEPLAY:
			break;
		case SINGLEPLAY:
			break;
		/********************* ������� *********************/
		case SINGLEPLAY_TO_CLASS:
			break;
		case CLASS:
			break;
		case CLASS_TO_MISSION:
			break;
		/********************* �ؿ����� *********************/
		case MISSION:
			break;
		case MISSION_TO_GAME:
			break;
		/********************* ��Ϸ���� *********************/
		case GAME:
			break;
		case GAME_BACK_MISSION:
			break;
		}
		return super.onSingleTapUp(event);
	}

}
