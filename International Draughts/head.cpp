#include "head.h"

box::box()
{
	int cnt = 0;
	bgcolor = RGB(255, 205, 150);
	for (int i = 1; i <= 10; ++i)
	{
		for (int j = 1; j <= 10; ++j)
		{
			point& cur = field[i][j];
			cur.isKing = false;
			if ((i & 1) != (j & 1))
			{
				cur.valid = true;
				cur.id = ++cnt;
				if (j <= 4) // black
				{
					cur.ChessType = 2;
				}
				else if (j <= 6) // empty
				{
					cur.ChessType = 0;
				}
				else // white
				{
					cur.ChessType = 1;
				}
			}
			else
			{
				cur.valid = false;
				cur.id = 0;
				cur.ChessType = -1; // invalid
			}
		}
	}
}

//TCHAR strnum[51] = {_T("0"),};

void box::draw()
{
	for (int i = 1; i <= 10; ++i)
	{
		for (int j = 1; j <= 10; ++j)
		{
			//Sleep(100);
			point& cur = field[i][j];
			COLORREF thefillcolor = getfillcolor(); // ���������ɫ
			setlinestyle(PS_SOLID, 2);              // ����ʽ����
			if (cur.id > 0)
			{
				setfillcolor(bgcolor);// �����ɫ����
				//settextstyle(30, 15, 0, 0, 0, 1000, false, false, false);
				//settextcolor(DARKGRAY);
				//outtextxy(150, 650, _T("���ִ�����У�����ִ�ں���"));

			}
			else
			{
				setfillcolor(YELLOW);
			}
			solidrectangle(50 * i + 50, 50 * j + 50, 50 * i + 100, 50 * j + 100);   // �����ޱ߿��������
			setlinecolor(BLACK);
			line(50 * i + 50, 50 * j + 50, 50 * i + 50, 50 * j + 100);
			line(50 * i + 50, 50 * j + 50, 50 * i + 100, 50 * j + 50);
			line(50 * i + 100, 50 * j + 50, 50 * i + 100, 50 * j + 100);
			line(50 * i + 50, 50 * j + 100, 50 * i + 100, 50 * j + 100);
			//setfillcolor(field[i][j].color);		// �����ɫ����
			int rx = 50 * i + 75, ry = 50 * j + 75;
			switch (cur.ChessType) // draw chess
			{
			case 0:
				break;
			case 1: // white
			{
				setfillcolor(WHITE);
				fillcircle(rx, ry, 15);
				if (cur.isKing) {
					setfillcolor(BLACK);
					fillcircle(rx, ry, 7);
				}
				break;
			}
			case 2: // black
			{
				setfillcolor(BLACK);
				fillcircle(rx, ry, 15);
				if (cur.isKing) {
					setfillcolor(WHITE);
					fillcircle(rx, ry, 7);
				}
				break;
			}
			default:
				break;
			}

		}
	}
}



void MouseCheck() {
	int x, y;
	MOUSEMSG ms = GetMouseMsg();

}

void draw(box& b)
{
	b.draw();
	settextstyle(30, 15, 0, 0, 0, 1000, false, false, false);
	settextcolor(BLACK);
	outtextxy(150, 650, _T("���ִ�����У�����ִ�ں���"));
	outtextxy(150, 20, _T("draghts"));

}

void GameOver(bool flag)
{
	if (flag) // player win
	{
		settextstyle(50, 25, 0, 0, 0, 1000, false, false, false);
		settextcolor(RED);
		outtextxy(250, 300, _T("YOU WIN!"));
	}
	else // player lose
	{
		settextstyle(50, 25, 0, 0, 0, 1000, false, false, false);
		settextcolor(GREEN);
		outtextxy(250, 300, _T("YOU LOSE!"));
	}
	settextstyle(30, 15, 0, 0, 0, 1000, false, false, false);
	settextcolor(BLACK);
	outtextxy(150, 650, _T("press any botton to close"));
	system("pause");
}