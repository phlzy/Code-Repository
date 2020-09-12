// ConsoleApplication1.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
// use ctrl K + ctrl D to format the code


#include "head.h"

#include <algorithm>
#include <utility>
#include <stack>
#include <queue> 
using std::stack;
using std::pair;
using std::priority_queue;
using std::make_pair;
using std::swap;

typedef pair<int, int> pii;

box chessboard;

const int dx[4] = { 1,1,-1,-1 };
const int dy[4] = { 1,-1,1,-1 };

bool vis[10][10];

stack<pii> way_p;

int search(int x, int y, int col) // x conter DFS
{
	point& cur = chessboard.field[x][y];
	if (!cur.valid)
		return 0;
	// int col = chessboard.field[x][y].ChessType;
	int cnt = 0;
	// vis[x][y] = true;
	for (int s = 0; s < 4; ++s)
	{
		if (!inBorder(x + dx[s], y + dy[s]) || vis[x + dx[s]][y + dy[s]])
			continue;
		point& now = chessboard.field[x + dx[s]][y + dy[s]];
		switch (col)
		{
		case 1: // white, player
		{
			if (now.ChessType == 2 && /* enemy */
				inBorder(x + 2 * dx[s], y + 2 * dy[s]) && /* attackable */
				chessboard.field[x + 2 * dx[s]][y + 2 * dy[s]].ChessType == 0  /* attackable */
				)
			{
				vis[x + dx[s]][y + dy[s]] = true;
				int tmp = search(x + 2 * dx[s], y + 2 * dy[s], 1);
				if (tmp > cnt)
				{
					way_p.push(make_pair(x + 2 * dx[s], y + 2 * dy[s]));
					cnt = tmp;
				}
				//cnt = max(cnt, search(x + 2 * dx[s], y + 2 * dy[s], 1));
				vis[x + dx[s]][y + dy[s]] = false;
			}
			break;
		}
		case 2: // black, computer
		{
			if (now.ChessType == 1 && /* enemy */
				inBorder(x + 2 * dx[s], y + 2 * dy[s]) && /* attackable */
				chessboard.field[x + 2 * dx[s]][y + 2 * dy[s]].ChessType == 0  /* attackable */
				)
			{
				vis[x + dx[s]][y + dy[s]] = true;
				int tmp = max(cnt, search(x + 2 * dx[s], y + 2 * dy[s], 2));
				if (tmp > cnt)
				{
					way_p.push(make_pair(x + 2 * dx[s], y + 2 * dy[s]));
					cnt = tmp;
				}
				//cnt = max(cnt, search(x + 2 * dx[s], y + 2 * dy[s], 2));
				vis[x + dx[s]][y + dy[s]] = false;
			}
			break;
		}
		default:
			break;
		}
	}
	return cnt;
}

stack<pii> way_k;

int search_king(int x, int y, int col)
{
	point& cur = chessboard.field[x][y];
	if (!cur.valid)
		return 0;
	// int col = chessboard.field[x][y].ChessType;
	int cnt = 0, p, q;
	// vis[x][y] = true;
	for (int s = 0; s < 4; ++s)
	{
		for (int i = 1;; ++i)
		{
			p = x + i * dx[s], q = y + i * dy[s];
			if (!inBorder(p, q) || chessboard.field[p][q].ChessType == col || vis[p][q])
				break;

			point& now = chessboard.field[p][q];
			switch (col)
			{
			case 1: // white, player
			{
				if (now.ChessType == 2 && /* enemy */
					inBorder(p, q) && /* attackable */
					chessboard.field[p + dx[s]][q + dy[s]].ChessType == 0  /* attackable */
					)
				{
					vis[p][q] = true;
					memset(vis, 0, sizeof(vis));
					int tmp = search(p + dx[s], q + dy[s], 1);
					if (tmp > cnt)
					{
						way_p.push(make_pair(p + dx[s], q + dy[s]));
						cnt = tmp;
					}
					vis[p][q] = false;
					//cnt = max(cnt, search(p, q, 1));
				}
				break;
			}
			case 2: // black, computer
			{
				if (now.ChessType == 1 && /* enemy */
					inBorder(p, q) && /* attackable */
					chessboard.field[p][q].ChessType == 0 && /* attackable */
					!vis[p][q] /* not visited */
					)
				{
					vis[p][q] = true;
					memset(vis, 0, sizeof(vis));
					int tmp = max(cnt, search(p, q, 2));
					if (tmp > cnt)
					{
						way_p.push(make_pair(p, q));
						cnt = tmp;
					}
					//cnt = max(cnt, search(p, q, 2));
				}
				break;
			}
			default:
				break;
			}
		}

	}
	return cnt;
}

inline bool isBlack(const point& _p)
{
	return _p.ChessType == 2;
}


bool move()
{
	for (int i = 1; i <= 10; ++i)
	{
		for (int j = 10; j >= 1; --j)
		{
			point& cur = chessboard.field[i][j];
			if (!isBlack(cur))
				continue;
			// bool flag_move = true;
			if (inBorder(i - 1, j + 1))
			{
				if (chessboard.field[i - 1][j - 1].ChessType == 0)
				{
					swap(chessboard.field[i - 1][j - 1], chessboard.field[i][j]);
					return true;
				}
			}
			else if (inBorder(i + 1, j + 1))
			{
				if (chessboard.field[i - 1][j + 1].ChessType == 0)
				{
					swap(chessboard.field[i - 1][j + 1], chessboard.field[i][j]);
					return true;
				}
			}
		}
	}
	return false;
}

/*
	|- 1. over ? GameOver : continue (2)
	|- 2. can I attack (search) ? choose longest length (2-1) : continue (2-2)
		|- 1. attack
		|- 2. find a way
	|- 3. move and refresh the chessboard
	|- 4. over ? GameOver : continue (5)
	|- 5. player's turn
	|- loop (goto 1)
*/
void game()
{
	// bool player = MoveCheck(chessboard);
	//while (!MoveCheck(chessboard));// player's turn
	if (!MoveCheck(chessboard))
		return;
	int max_way = 0, pos_x = 0, pos_y = 0;
	for (int i = 1; i <= 10; ++i)
	{
		for (int j = 1; j <= 10; ++j)
		{
			point& cur = chessboard.field[i][j];
			if (cur.ChessType != 2)
				continue;
			memset(vis, 0, sizeof(vis));
			if (search(i, j, 2) > max_way)
			{
				while (!way_p.empty())
					way_p.pop();
				memset(vis, 0, sizeof(vis));
				max_way = search(i, j, 2);
			}
		}
	}
	for (int i = 1; i <= 10; ++i)
	{
		for (int j = 1; j <= 10; ++j)
		{
			point& cur = chessboard.field[i][j];
			if (cur.ChessType != 2)
				continue;
			memset(vis, 0, sizeof(vis));
			if (search(i, j, 2) == max_way)
			{
				while (!way_p.empty())
					way_p.pop();
				memset(vis, 0, sizeof(vis));
				max_way = search(i, j, 2);
				pos_x = i, pos_y = j;
				break;
			}
		}
	}
	if (way_p.empty())
	{
		move();
		return;
	}
	int res_x = 0, res_y = 0;
	chessboard.field[pos_x][pos_y].ChessType = 0;
	while (!way_p.empty())
	{
		int tmp_x = way_p.top().first;
		int tmp_y = way_p.top().second;
		way_p.pop();
		res_x = 2 * tmp_x - pos_x; 
		res_y = 2 * tmp_y - pos_y;
		pos_x = tmp_x;
		pos_y = tmp_y;
	}
	chessboard.field[res_x][res_y].ChessType = 2;
	return;
}


int main()
{
	srand(time(0));
	initgraph(700, 700); // 初始化绘图环境
	setbkcolor(WHITE);
	cleardevice();
	setbkmode(TRANSPARENT); // 设置透明文字输出背景
	setfillcolor(BLACK);
	setlinestyle(PS_SOLID, 1);
	// memset(vis, 0, sizeof(vis));
	draw(chessboard);

	while (1)
	{
		if (GameOver(chessboard))
			break;
		draw(chessboard);
		game();
		move();
		Sleep(10);
		cleardevice();
		
	}

	system("pause");

	return 0;
}


// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

