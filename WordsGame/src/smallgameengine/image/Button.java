/*
 * �������� 2013-5-4
 *
 * TODO Ҫ���Ĵ����ɵ��ļ���ģ�壬��ת��
 * ���� �� ��ѡ�� �� Java �� ������ʽ �� ����ģ��
 */
package smallgameengine.image;

import javax.microedition.khronos.opengles.GL10;

import smallgameengine.game.Rect;

public class Button extends Image {

	public boolean Istouch=false;
	public Button(GL10 gl, int textureId, float x, float y, float z, float w,
			float h) {
		super(gl, textureId, x, y, z, w, h);
		// TODO �Զ����ɹ��캯�����
	}
	
	
	public boolean isTouch( float x, float y ){
		if( x < this.x - w * 0.5f || x > this.x + w * 0.5f ) return false;
		if( y < this.y - h * 0.5f || y > this.y + h * 0.5f ) return false;
		Istouch=true;
		
		return true;
	}


	public void setRect(Rect rect) {
		// TODO Auto-generated method stub
		setw(rect.getW());
		seth(rect.getH());
		x=rect.getX();
		y=rect.getY();
	}

}
