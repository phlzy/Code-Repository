// ConsoleApplication1.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
// use ctrl K + ctrl D to format the code


#include "head.h"

#include <algorithm>
#include <utility>
#include <stack>
using std::stack;
using std::pair;
using std::make_pair;

typedef pair<int, int> pii;

box chessboard;

const int dx[4] = { 1,1,-1,-1 };
const int dy[4] = { 1,-1,1,-1 };

bool vis[10][10];

inline bool inBorder(const int& x, const int& y) // check 
{
	if (x < 1 || x > 10 || y < 1 || y > 10)
		return false;
	return true;
}

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

int search_king(int x, int y,int col)
{
	point& cur = chessboard.field[x][y];
	if (!cur.valid)
		return 0;
	// int col = chessboard.field[x][y].ChessType;
	int cnt = 0,p,q;
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
	//GameOver();
	
	
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
	/*
	while (1) {

	}
	*/
	system("pause");

	return 0;
}




// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门使用技巧: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件
