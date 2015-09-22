package com.example.cl.crzayandroiddemo11;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
//����һ������ͼƬ������
    int [] images = new int[]{
        R.drawable.lijiang,
        R.drawable.qiao,
        R.drawable.shuangta,
        R.drawable.shui,
        R.drawable.xiangbi,
};
//    ����Ĭ����ʾ��ͼƬ
    int currentImg = 2;
//    ����ͼƬ�ĳ�ʼ͸����
    private  int alpha = 255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button plus = (Button) findViewById(R.id.button_1);
        final Button minus = (Button) findViewById(R.id.button_2);
        final ImageView image1 = (ImageView) findViewById(R.id.image1);
        final ImageView image2 = (ImageView) findViewById(R.id.image2);
        final Button next = (Button) findViewById(R.id.button_3);
//        ����鿴��һ��ͼƬ�ļ�����

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //����ImageView��ʾһ��ͼƬ
                image1.setImageResource(images[++currentImg % images.length]);
            }
        });
        //����ı�ͼƬ͸���ȵķ���
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v == plus){
                    alpha += 20;
                }
                if (v == minus){
                    alpha -=20;
                }
                if (alpha >= 255){
                    alpha = 255;
                }
                if (alpha <= 0){
                    alpha = 0;
                }
                //�ı�ͼƬ��͸����
                image1.setAlpha(alpha);
            }
        };
        //Ϊ������ť���ü���
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);
        image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image1.getDrawable();
                //��ȡ��һ��ͼƬ��ʾ�е�λ��
                Bitmap bitmap = bitmapDrawable.getBitmap();
                //bitmapͼƬʵ�ʴ�С���һ��ImageView�����ű���
                double scale = bitmap.getWidth() / 320.0;
                //��ȡ��Ҫ��ʾ��ͼƬ�Ŀ�ʼ��
                int x = (int) (event.getX() * scale);
                int y = (int) (event.getY() * scale);
                if(x + 120 > bitmap.getWidth()){
                    x = bitmap.getWidth() - 120;
                }
                if (y + 120 > bitmap.getHeight()){
                    y = bitmap.getHeight() - 120;
                }
                //��ʾͼƬ��ָ������
                //CreateBitmap()��̬�������Խ�ȡλͼ��ָ�����֣��������ؽ�ȡ�������ɵ���λͼ
                image2.setImageBitmap(Bitmap.createBitmap(bitmap,x,y,120,120));
                image2.setAlpha(alpha);
                return false;
            }
        });
    }

}
