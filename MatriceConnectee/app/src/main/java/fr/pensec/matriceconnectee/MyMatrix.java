package fr.pensec.matriceconnectee;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class MyMatrix extends View {

    private Paint paintMatice ;
    private Paint paintDisque1 ;
    private Paint paintDisque2 ;
    private int n = 8 ;
    private int x = 0 ;
    private int y = 0 ;
    private boolean afficherBalle = false;

    public MyMatrix(Context context) {
        super(context);
        initialize();
    }

    public MyMatrix(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public MyMatrix(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    public MyMatrix(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initialize();
    }

    private void initialize(){
        paintMatice = new Paint();
        paintMatice.setColor(Color.GRAY);

        paintDisque1 = new Paint();
        paintDisque1.setAntiAlias(true);
        paintDisque1.setStyle(Paint.Style.FILL);
        paintDisque1.setColor(Color.rgb(250,200,0));

        paintDisque2 = new Paint();
        paintDisque2.setAntiAlias(true);
        paintDisque2.setStrokeWidth(2);
        paintDisque2.setStyle(Paint.Style.STROKE);
        paintDisque2.setColor(Color.rgb(80,80,60));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getWidth() - getWidth() % n;
        int h = getHeight() - getHeight() % n;

        int pasW = w / n;
        int pasH = h / n;

        for(int i = 0; i<=w ; i+=pasW){
            canvas.drawLine(i, 0, i, h, paintMatice);
        }

        for(int j = 0; j<=h ; j+=pasH){
            canvas.drawLine(0, j, w, j, paintMatice);
        }

        if(afficherBalle) {
            canvas.drawOval(pasW*x+4, pasH*y+4, pasW*(x+1)-4, pasH*(y+1)-4, paintDisque1);
            canvas.drawOval(pasW*x+4, pasH*y+4, pasW*(x+1)-4, pasH*(y+1)-4, paintDisque2);
        }
    }

    public void incX() {
        x++;
        x = (x % n);
        invalidate();
    }

    public void decX() {
        x--;
        if(x < 0) x = n-1;
        invalidate();
    }

    public void incY() {
        y++;
        y = (y % n);
        invalidate();
    }

    public void decY() {
        y--;
        if(y < 0) y = n-1;
        invalidate();
    }

    public void init() {
        x = y = n/2+n%2-1;
        invalidate();
    }

    public void afficher() {
        afficherBalle = !afficherBalle ;
        invalidate();
    }

    public void cacher() {
        afficherBalle = false;
        invalidate();
    }

    public void exit(){
        System.exit(0);
    }

    public void changeCoordonnees(int posX, int posY){
        x = posX;
        y = posY;
        invalidate();
    }

}