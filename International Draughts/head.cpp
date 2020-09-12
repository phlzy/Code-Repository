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
			COLORREF thefillcolor = getfillcolor(); // 备份填充颜色
			setlinestyle(PS_SOLID, 2);              // 线样式设置
			if (cur.valid)
			{
				setfillcolor(bgcolor);// 填充颜色设置
			}
			else
			{
				setfillcolor(YELLOW);
			}
			solidrectangle(50 * i + 50, 50 * j + 50, 50 * i + 100, 50 * j + 100);   // 绘制无边框的正方形
			setlinecolor(BLACK);
			line(50 * i + 50, 50 * j + 50, 50 * i + 50, 50 * j + 100);
			line(50 * i + 50, 50 * j + 50, 50 * i + 100, 50 * j + 50);
			line(50 * i + 100, 50 * j + 50, 50 * i + 100, 50 * j + 100);
			line(50 * i + 50, 50 * j + 100, 50 * i + 100, 50 * j + 100);
			//setfillcolor(field[i][j].color);		// 填充颜色设置
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



bool MoveCheck(box& b)
{
	int x, y;
	MOUSEMSG ms = GetMouseMsg();
	x = ms.x, y = ms.y;
	if (x < 100 || x >= 600 || y < 100 || y >= 600)
		return false;
	x = x / 50 - 1, y = y / 50 - 1;
	point& cur = b.field[x][y];
	if (!cur.valid || cur.ChessType == 2)
		return false;
	setlinecolor(LIGHTGRAY);
	setlinestyle(PS_SOLID, 2);
	line(50 * x + 55, 50 * y + 55, 50 * x + 55, 50 * y + 95);
	line(50 * x + 55, 50 * y + 55, 50 * x + 95, 50 * y + 55);
	line(50 * x + 95, 50 * y + 55, 50 * x + 95, 50 * y + 95);
	line(50 * x + 55, 50 * y + 95, 50 * x + 95, 50 * y + 95);
	if (cur.ChessType == 0 && ms.mkLButton)
	{
		if (b.field[x - 1][y + 1].ChessType == 1)
		{
			swap(b.field[x - 1][y + 1], b.field[x][y]);
			return true;
		}
		else if (b.field[x + 1][y + 1].ChessType == 1)
		{
			swap(b.field[x + 1][y + 1], b.field[x][y]);
			return true;
		}
		else
			return false;
	}
	return false;
}

void draw(box& b)
{
	b.draw();
	settextstyle(30, 15, 0, 0, 0, 1000, false, false, false);
	settextcolor(BLACK);
	outtextxy(150, 650, _T("玩家执白先行，电脑执黑后行"));
	outtextxy(150, 20, _T("跳棋游戏"));

}

inline bool inBorder(const int& x, const int& y) // check 
{
	if (x < 1 || x > 10 || y < 1 || y > 10)
		return false;
	return true;
}

bool GameOver(box& b)
{
	int cnt_w = 0, cnt_b = 0;
	for (int i = 1; i <= 10; ++i)
	{
		for (int j = 1; j <= 10; ++j)
		{
			point& cur = b.field[i][j];
			switch (cur.ChessType)
			{
			case 1:
			{
				if (inBorder(i - 1, j - 1))
				{
					if (b.field[i - 1][j - 1].ChessType == 0)
						cnt_w++;
				}
				if (inBorder(i + 1, j - 1))
				{
					if (b.field[i + 1][j - 1].ChessType == 0)
						cnt_w++;
				}
				break;
			}
			case 2:
			{
				if (inBorder(i - 1, j + 1))
				{
					if (b.field[i - 1][j + 1].ChessType == 0)
						cnt_b++;
				}
				if (inBorder(i + 1, j + 1))
				{
					if (b.field[i + 1][j + 1].ChessType == 0)
						cnt_b++;
				}
				break;
			}
			default:
				break;
			}
		}
	}
	if (cnt_w > 0 && cnt_b > 0)
		return false;
	bool flag = cnt_b == 0 ? true : false;
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
	//system("pause");
	return true;
}