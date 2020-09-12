#pragma once
#include <iostream>
#include <cstdlib>
#include <cstdio>
#include <ctime>
#include <easyx.h>
#include <graphics.h>
#include <conio.h>
#include <windows.h>
#include <algorithm>
using std::swap;

struct point
{
	int x = 0, y = 0, cnt = 0;
	int ChessType = 0; // 2:black 1:white 0:none
	int mod = 0;
	int id = 0;
	bool isnew = false;
	bool valid = false;
	bool isKing = false;
	//COLORREF color = WHITE;
	point() {}
};

struct box
{
	point field[12][12];
	COLORREF bgcolor = RGB(255, 205, 150); // background
	box();
	void draw();            // ����

};

inline bool inBorder(const int& x, const int& y);

bool GameOver(box& b);

void draw(box& b);

bool MoveCheck(box& b);