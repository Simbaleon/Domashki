package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.Arrays;

import task.Task;




public class CellsActivity extends Activity implements OnClickListener,
        OnLongClickListener {

    int WIDTH = 9;
    int HEIGHT = 14;
    int Max = WIDTH * HEIGHT;
    int mBombs = 0;
    int mCells = 0;
    int Goals = 0;
    int Pr = 0;

    Button[][] cells;
    int[][] Bombs = new int[HEIGHT][WIDTH];
    int[][] flags = new int[HEIGHT][WIDTH];
    int[][] Cells = new int[HEIGHT][WIDTH];
    int[][] isDrawn = new int [HEIGHT][WIDTH];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();
        generate();

    }

    void generate() {
        if (Pr == 0){
            Goals = 0;
        }
        mBombs = 0;
        mCells = 0;
        for (int  i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                Bombs[i][j] = 0;
                Cells[i][j] = 0;
                flags[i][j] = 0;
                cells[i][j].setBackgroundColor(Color.BLACK);
                if (Math.random() <= 0.05) {
                    Bombs[i][j] = 1;
                    isDrawn[i][j] = 0;
                    mBombs ++;
                }else {
                    isDrawn[i][j] = 1;
                    Bombs[i][j] = 0;
                }
            }
        }
    }

    @Override
    public boolean onLongClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);

        cells[tappedY][tappedX].setBackgroundColor(Color.RED);
        flags[tappedY][tappedX] = 1;

        Object[][] arr1 = {flags};
        Object[][] arr2 = {Bombs};
        if(Arrays.deepEquals(arr1, arr2)){
            Task.showMessage(this, "Вы победили :)   Ваш счёт: " + Goals + '\n'+  "Начать заново? ");
            makeCells();
            generate();
        }
        return true;
    }

    private void area(int i, int j){
        if(i < 0 || i >= HEIGHT || j < 0 || j >= WIDTH || Cells[i][j] == 1 || Bombs[i][j] == 1){
            return;
        }
        cells[i][j].setBackgroundColor(Color.WHITE);
        Cells[i][j] = 1;
        flags[i][j] = 0;
        int bCount = 0;
        for(int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (k >= 0 && k < HEIGHT && l >= 0 && l < WIDTH) {
                    bCount += Bombs[k][l];
                }
            }
        }
        if(bCount == 0){
            area(i - 1, j - 1);
            area(i - 1, j);
            area(i - 1, j + 1);
            area(i, j - 1);
            area(i, j + 1);
            area(i + 1, j - 1);
            area(i + 1, j);
            area(i + 1, j + 1);
        }else {
            cells[i][j].setText(bCount + "");
        }
        if (isDrawn[i][j] == 1){
            isDrawn[i][j] = 0;
            mCells ++;
            Goals = Goals + 5;
            if(mCells + mBombs == Max){
                Task.showMessage(this, "Вы победили :)  Ваш счёт: " + Goals + '\n'+  "Начать заново? ");
                makeCells();
                generate();
            }
        }
    }

    @Override
    public void onClick(View v) {
        Button tappedCell = (Button) v;

        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        flags[tappedY][tappedX] = 0;
        if(Bombs[tappedY][tappedX] == 1){
            cells[tappedY][tappedX].setBackgroundColor(Color.YELLOW);
            Task.showMessage(this, "Вы проиграли :(  Ваш счёт: " + Goals + '\n'+  "Начать заново? ");
            makeCells();
            generate();
        }else{
            cells[tappedY][tappedX].setBackgroundColor(Color.WHITE);
            Cells[tappedY][tappedX] = 1;
            int bCount = 0;
            for(int i = tappedY - 1; i <= tappedY + 1; i++){
                for(int j = tappedX - 1; j <= tappedX + 1; j++){
                    if(i >= 0 && i < HEIGHT && j >= 0 && j < WIDTH){
                        bCount += Bombs[i][j];
                    }
                }
            }
            if(bCount == 0){
                area(tappedY - 1, tappedX - 1);
                area(tappedY - 1, tappedX);
                area(tappedY - 1, tappedX + 1);
                area(tappedY, tappedX - 1);
                area(tappedY, tappedX + 1);
                area(tappedY + 1, tappedX - 1);
                area(tappedY + 1, tappedX);
                area(tappedY + 1, tappedX + 1);
            }else {
                cells[tappedY][tappedX].setText(bCount + "");
            }
        }
        if (isDrawn[tappedY][tappedX] == 1){
            isDrawn[tappedY][tappedX] = 0;
            mCells ++;
            Goals = Goals + 5;
            if(mCells + mBombs == Max){
                Task.showMessage(this, "Вы победили :)  Ваш счёт: " + Goals + '\n'+  "Начать заново? ");
                makeCells();
                generate();
            }
        }
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }

}