package smallgameengine.game;

import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;



import android.view.MotionEvent;
import smallgameengine.help.FileAccess;
import smallgameengine.help.Font;
import smallgameengine.help.Texture;
import smallgameengine.help.TxtReader;
import smallgameengine.image.Button;
import smallgameengine.image.Image;
import wordsgame.main.mvc.OpenGlRenderer;

public class Game {
	private OpenGlRenderer renderer;

	//��Ϸ����ֳ�����     
	//��Ļ     grid  ��ť
	
	/* ���ƣ�
	 * 1���ֵ����ȥ��֮ǰ����͸������isDrawQV[lastDrawQV]=isDrawQH[lastDrawQH]=false; 
	 * 2���ѵ�ǰ��isDrawQV[i]=isDrawQH[j]=true;
	 * 3�������ʱ��i��j����canBeDrawQV[i]==true&&canBeDrawQH[j]==true   ��ô��һ���ƶ���1040 +72������1040
	 * */
	//��Ļ
	//Ŀǰ������   ������ʱ�������ס
	private int lastDrawQV;
	private int lastDrawQH;
	//��ʾ�Ƿ���һ���л�����
	private boolean canBeDrawQV[] = new boolean[10]; 
	private boolean canBeDrawQH[]  = new boolean[10];
	//����draw��
	private boolean isDrawQV[] = new boolean[10]; 
	private boolean isDrawQH[]  = new boolean[10];
	//Fontָ��
	private Font fontQuestionVertival[] = new Font[10];
	private Font fontQuestionHorizontal[] = new Font[10];
		
	/**��ϷͼƬ
	 * ��Ϸ�ֳ�
	 * 1�������㣨����ͼƬ��
	 * 2��Ч���㣨���磺���㡢��Ҫ��ʵ�λ�ã���ɫ����
	 * 3������㣨��һ����ȥָ����ĸ������ȷ�𰸣�
	 */
	/*
	 * 0���� �ո� 1���� ��� 2���� ���� 3���� �� 4���� 5���� 6����
	 * 100����A 101����B ������ ������ 125����Z
	 */
	//i����   j����   [��][��]   [x][y]
	/*1�������㲻�����������renderer*/
	
	/*2��Ч����*/
	//Ч����ť
	private Button bntmean[] = new Button[3];
	//���е�ʱ���grid����˼  ����չʾ��Ч��
	public  int runMap[][] = new int[10][10];
	//��д�Ĵ�
	public  int myAnswerMap[][] = new int[10][10];
	//�����Ĵ�
	public  int answerMap[][] = new int[10][10];

	/*3�������*/
	//�洢26����ĸ������
	private Font fontALP[] = new Font[26];
	//�洢������
	private Font fontStrAnswer[][] = new Font[10][10];
	//draw��ʱ�򻭵�Font
	private Font fontAnswer[][] = new Font[10][10];
	//����Ƿ���Ҫ������
	private boolean isDranFont[][] = new boolean[10][10];
	
	
	
	//��ť
	private boolean isDrawALP[] = new boolean[26];
	private Button bntALP[] = new Button[26];
	private Rect rectALP[] = new Rect[26];
	
	FileAccess file;
	public Game(Button[] bnt, Button[] bntalp, OpenGlRenderer r) {
		this.bntmean = bnt;
		this.renderer = r;
		this.bntALP = bntalp;
		
		file = new FileAccess(r.context);
	      
        String string0 = file.readFileSdcard("/mnt/sdcard/a.txt");
        if(string0.equals("")){
        	InputStream inputStream = r.context.getResources().openRawResource(com.example.wordsgame.R.raw.a);
     		String string1 = TxtReader.getString(inputStream);
     		file.writeFileSdcard("/mnt/sdcard/a.txt", string1);
        }
        String string = file.readFileSdcard("/mnt/sdcard/a.txt");

		int Line=0;
		int strStart=0;
		int strEnd=string.indexOf('\n');
		String str="";
		for(;Line<10;Line++)
		{
			str=string.substring(strStart, strEnd);
			strStart=strEnd+1;
			strEnd=string.indexOf('\n',strEnd+1);
			for(int j=0;j<10;j++)
			{
				char charTemp=str.charAt(j);
				if(charTemp=='��')continue;
				else if(Line<10)
				fontStrAnswer[j][Line]=new Font(renderer.mainGl, ""+charTemp ,
						36 + 0 * 72, 1004 - 0 * 72, 0.09f, 72.0f);
			}
		}
		++Line;
		str=string.substring(strStart, strEnd);
		strStart=strEnd+1;
		strEnd=string.indexOf('\n',strEnd+1);
		for(;Line<21;Line++)
		{
			str=string.substring(strStart, strEnd);
			System.out.println(str);
			strStart=strEnd+1;
			strEnd=string.indexOf('\n',strEnd+1);
			
			for(int j=0;j<10;j++)
			{
				char charTemp=str.charAt(j);
				if(charTemp=='��')continue;
				else if(Line-11<10){ 
					runMap[j][Line-11] = 1;
					answerMap[j][Line-11] = charTemp-'a'+100;
				}
			}
		}
		++Line;
		str=string.substring(strStart, strEnd);
		strStart=strEnd+1;
		strEnd=string.indexOf('\n',strEnd+1);
		str=string.substring(strStart, strEnd);
		strStart=strEnd+1;
		strEnd=string.indexOf('\n',strEnd+1);
		while(!str.equals((String)"####")){
			int index=str.charAt(0)-'0';
			fontQuestionHorizontal[index]=new Font(renderer.mainGl, "��"+index+str.substring(2),
					0, 1040, 0.09f, 72.0f);
			canBeDrawQH[index]=true;
			str=string.substring(strStart, strEnd);
			strStart=strEnd+1;
			strEnd=string.indexOf('\n',strEnd+1);
		}
		++Line;
		str=string.substring(strStart, strEnd);
		strStart=strEnd+1;
		strEnd=string.indexOf('\n',strEnd+1);
		while(!str.equals((String)"####")){
			int index=str.charAt(0)-'0';
			fontQuestionVertival[index]=new Font(renderer.mainGl, "��"+index+str.substring(2),
					0, 1040, 0.09f, 72.0f);
			canBeDrawQV[index]=true;
			str=string.substring(strStart, strEnd);
			strStart=strEnd+1;
			strEnd=string.indexOf('\n',strEnd+1);
		}

		for (int i = 0; i < 10; i++) {
			rectALP[i] = new Rect((36 + i * 72) * 1.0f, 1280 - 1014 * 1.0f,
					72 * 1.0f, 108 * 1.0f);
		}
		for (int i = 10; i < 20; i++) {
			rectALP[i] = new Rect((36 + (i - 10) * 72) * 1.0f,
					1280 - 108 - 1014 * 1.0f, 72 * 1.0f, 108 * 1.0f);
		}
		for (int i = 20; i < 26; i++) {
			rectALP[i] = new Rect((144 + 36 + (i - 20) * 72) * 1.0f, 1280 - 108
					- 108 - 1014 * 1.0f, 72 * 1.0f, 108 * 1.0f);
		}
		for (int i = 0; i < 26; i++) {
			fontALP[i] = new Font(renderer.mainGl, "" + (char) ('A' + i),
					36 + 0 * 72, 1004 - 0 * 72, 0.09f, 72.0f);
		}
	}

	public void draw() {
		//��grid
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int temp = runMap[i][j];
				if (temp < 3 && temp > 0) {
					bntmean[temp].x = 36 + i * 72;
					bntmean[temp].y = 1004 - j * 72;
					bntmean[temp].draw();
				}
			}
		}

		//����ť
		for (int i = 0; i < 26; i++) {
			if (isDrawALP[i])
				bntALP[i].draw();
		}

		//�������
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (isDranFont[i][j]&&fontAnswer[i][j]!=null) {
					fontAnswer[i][j].x = i * 72;
					fontAnswer[i][j].y = 968 - j * 72;
					fontAnswer[i][j].draw();
				}
			}
		}
		
		
		//����Ļ
		for(int i=1;i<10;i++)
		{
			if(isDrawQH[i]&&fontQuestionHorizontal[i]!=null)
			{
				fontQuestionHorizontal[i].draw();
			}
			if(isDrawQV[i]&&fontQuestionVertival[i]!=null)
			{
				
				fontQuestionVertival[i].draw();
			}
		}
		
	}

	public int lastI = -1, lastJ = -1;
	private int lastALP = -1;

	public void touchDown(float x, float y) {
		
		//ת��Ϊ����
		int i, j;
		if (x >= 0 && x <= 720.0f && y >= 320.f && y <= 1040.0f) {
			i = (int) (x / 72);
			j = 9 - (int) ((y - 320) / 72);
		} else {
			i = j = -1;
		}

		// System.out.println("x:" + x + ";;;y:" + y + ";;;   " + "i:" + i
		// + ";;;j:" + j + ";;;   ");

		//�ı佹��     ���ij�ڵ�ͼ����  �ҿ��Դ���
		if (i >= 0 && i <= 9 && j >= 0 && j <= 9 && runMap[i][j] != 0) {
			if (lastI != -1 && lastJ != -1 && !(i == lastI && j == lastJ)) {
				if(answerMap[lastI][lastJ]>=100)
					runMap[lastI][lastJ] = 1;
				else runMap[lastI][lastJ] = answerMap[lastI][lastJ];
				isDrawQH[lastJ]=false;
				isDrawQV[lastI]=false;
			}
			
			if(canBeDrawQH[j])
				isDrawQH[j]=true;
			if(canBeDrawQV[i]){
				isDrawQV[i]=true;
				if(canBeDrawQH[j])
				{
					fontQuestionVertival[i].y=1040+72;
				}
				else fontQuestionVertival[i].y=1040;
			}
			
			runMap[i][j] = 2;
			lastI = i;
			lastJ = j;
		}

		
		//��鰴ť
		for (int tempi = 0; tempi < 26; tempi++) {
			if (rectALP[tempi].isTouch(x, y)) {
				bntALP[tempi].setw(90);
				bntALP[tempi].seth(120);
				isDrawALP[tempi] = true;
				lastALP = tempi;
				if (lastI != -1 && lastJ != -1) {
					fontAnswer[lastI][lastJ] = fontALP[tempi];
					isDranFont[lastI][lastJ] = true;
					myAnswerMap[lastI][lastJ] = tempi+100;
					if(canBeDrawQV[lastI]&&isVerticalRight(lastI))
					{
						for(int tj=0;tj<10;tj++)
						{
							if(answerMap[lastI][tj]>=100)
							{
								fontAnswer[lastI][tj]=fontStrAnswer[lastI][tj];
							}
						}
					}
					if(canBeDrawQH[lastJ]&&isHorizontalRight(lastJ)){
						for(int ti=0;ti<10;ti++)
						{
							if(answerMap[ti][lastJ]>=100)
							{
								fontAnswer[ti][lastJ]=fontStrAnswer[ti][lastJ];
							}
						}
					}
					for (int tempt = 0; tempt < 4; tempt++) {
						if (runMap[lastI + dir[tempt][0]][lastJ + dir[tempt][1]] == 1) {
							runMap[lastI + dir[tempt][0]][lastJ + dir[tempt][1]] = 2;
							if(answerMap[lastI][lastJ]>=100)
								runMap[lastI][lastJ] = 1;
							else runMap[lastI][lastJ] = answerMap[lastI][lastJ];
							lastI = lastI + dir[tempt][0];
							lastJ = lastJ + dir[tempt][1];
							break;
						}
					}
				}
			}
		}
	}

	private boolean isVerticalRight(int lastI2) {
		// TODO Auto-generated method stub
		for(int j=0;j<10;j++){
			if(myAnswerMap[lastI2][j]!=answerMap[lastI2][j])return false;
		}
		return true;
	}
	
	private boolean isHorizontalRight(int lastJ2) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			if(myAnswerMap[i][lastJ2]!=answerMap[i][lastJ2])return false;
		}
		return true;
	}

	public void touchUp(MotionEvent event) {
		if (lastALP != -1)
			isDrawALP[lastALP] = false;
		float x = event.getX();
		float y = renderer.screenH - event.getY();
		float gameX = ((x / renderer.screenW) * renderer.gameScreenW);
		float gameY = ((y / renderer.screenH) * renderer.gameScreenH);
	}

	public int dir[][] = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
}
