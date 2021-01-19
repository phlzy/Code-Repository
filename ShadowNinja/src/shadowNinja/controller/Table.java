package shadowNinja.controller;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import shadowNinja.init.FailFrame;
import shadowNinja.init.LoginFrame;
import shadowNinja.init.SuccessFrame;
import shadowNinja.main.ui.MainFrame;
import shadowNinja.main.ui.MainPanel;
import shadowNinja.model.Arrow;
import shadowNinja.model.BackGround;
import shadowNinja.model.Bat;
import shadowNinja.model.Bullet;
import shadowNinja.model.Dart;
import shadowNinja.model.Enemy;
import shadowNinja.model.Fire;
import shadowNinja.model.Needle;
import shadowNinja.model.Ninja;
import shadowNinja.model.Obstacle;
import shadowNinja.model.Panther;
import shadowNinja.model.Person;
import shadowNinja.model.Rope;
import shadowNinja.model.Samurai;
import shadowNinja.model.Stickman;
import shadowNinja.model.Weapon;

/**
 * 游戏桌面核心类，内部管理所有的桌面元素，并负责操作它们（进行游戏）
 * 
 * @author 唐雷
 *
 */
public class Table {
	// 子弹的集合
	ArrayList<Bullet> bullets;
	// 需要被删除子弹的集合
	ArrayList<Bullet> removeBullets;
	// 火焰的集合
	public ArrayList<Fire> fire;
	// 需要被删除火焰的集合
	ArrayList<Fire> removeFire;
	// 障碍物的集合
	public ArrayList<Obstacle> obses;
	// 需要被删除障碍物的集合
	ArrayList<Obstacle> removeObses;
	// 敌人的集合
	ArrayList<Enemy> enemies;
	// 需要被删除敌人的集合
	ArrayList<Enemy> removeEnemies;
	// 人物
	private static Person person;
	// 绳子
	private Rope rope;
	// 绳子的高度
	private static int ROPE_POSITION_Y = 500;
	// 随机数，用于随机生成障碍物、敌人及它们的位置
	Random random;
	// 背景图
	private BackGround background;
	// 获取武器
	int weaponId;

	// 获取游戏窗口
	private MainFrame mainFrame;

	// 游戏是否结束
	private boolean endGame;

	// 是否绘制进度条
	private boolean ifBar;

	/**
	 * 构造函数，传入使用的人物
	 * 
	 * @param personId
	 * @throws IOException
	 */
	public Table(int personId, MainFrame mainFrame) throws IOException {
		this.mainFrame = mainFrame;
		random = new Random();
		bullets = new ArrayList<Bullet>();
		removeBullets = new ArrayList<Bullet>();

		fire = new ArrayList<Fire>();
		removeFire = new ArrayList<Fire>();

		obses = new ArrayList<Obstacle>();
		removeObses = new ArrayList<Obstacle>();
		enemies = new ArrayList<Enemy>();
		removeEnemies = new ArrayList<Enemy>();
		rope = new Rope();
		rope.setLeftUp(new Point(0, ROPE_POSITION_Y));// 设置绳子的高度

		background = new BackGround(0, 0, 0);

		endGame = false;// 初始化游戏状态
		ifBar = true;// 初始化绘制进度条的属性

		switch (personId) {
		case 1:
			person = new Stickman();
			person.startGame();
			// person.init();
			break;
		case 2:
			person = new Ninja();
			person.startGame();
			break;
		case 3:
			person = new Panther();
			person.startGame();
			break;

		}
	}

	public Table(int personId, shadowNinja.test.WeaponTest.MainFrame mainFrame2) throws IOException {
		this.mainFrame = mainFrame;
		random = new Random();
		bullets = new ArrayList<Bullet>();
		removeBullets = new ArrayList<Bullet>();

		fire = new ArrayList<Fire>();
		removeFire = new ArrayList<Fire>();

		obses = new ArrayList<Obstacle>();
		removeObses = new ArrayList<Obstacle>();
		enemies = new ArrayList<Enemy>();
		removeEnemies = new ArrayList<Enemy>();
		rope = new Rope();
		rope.setLeftUp(new Point(0, ROPE_POSITION_Y));// 设置绳子的高度

		background = new BackGround(0, 0, 0);

		endGame = false;// 初始化游戏状态
		ifBar = true;// 初始化绘制进度条的属性

		switch (personId) {
		case 1:
			person = new Stickman();
			person.startGame();
			// person.init();
			break;
		case 2:
			person = new Ninja();
			person.startGame();
			break;
		case 3:
			person = new Panther();
			person.startGame();
			break;

		}
	}

	/**
	 * 返回背景图
	 * 
	 * @return background
	 */
	public BackGround getBackground() {
		return background;
	}

	/**
	 * 返回人物的x坐标
	 * 
	 * @return x
	 */
	public int getPersonX() {
		// TODO Auto-generated method stub
		return person.getX();
	}

	/**
	 * 返回人物的y坐标
	 * 
	 * @return y
	 */
	public int getPersonY() {
		// TODO Auto-generated method stub
		return person.getY();
	}

	/**
	 * 返回人物
	 * 
	 * @return person
	 */
	public Person getPerson() {
		// TODO Auto-generated method stub
		return person;
	}

	/**
	 * 返回子弹的合集
	 * 
	 * @return ArrayList<Bullet>
	 */
	public synchronized ArrayList<Bullet> getBullets() {
		return bullets;
	}

	/**
	 * 返回火焰的合集
	 * 
	 * @return ArrayList<Fire>
	 */
	public synchronized ArrayList<Fire> getFire() {
		return fire;
	}

	/**
	 * 返回障碍物的合集
	 * 
	 * @return ArrayList<Obstacle>
	 */
	public synchronized ArrayList<Obstacle> getObses() {
		return obses;
	}

	/**
	 * 返回敌人的合集
	 * 
	 * @return ArrayList<Enemy>
	 */
	public synchronized ArrayList<Enemy> getEnemies() {
		return enemies;
	}

	/**
	 * 返回绳子
	 * 
	 * @return rope
	 */
	public Rope getRope() {
		return rope;
	}

	/**
	 * 对火焰操作，具体为移动火焰并判断是否应该被删除，如果应该则删除
	 */
	public void fireWork() {
		for (Fire each : fire) {
			if (each.shouldEnd()) {
				removeFire.add(each);
			} else {
				each.setX();
				each.setY();
				each.remainLifeTime(1.0f / MainPanel.FPS * 1000);
			}
		}
		for (Fire removeEach : removeFire) {
			fire.remove(removeEach);
		}
	}

	/**
	 * 添加子弹
	 */
	public void addBullet() {
		synchronized (this) {
			bullets.add(new Bullet((float) (person.getX() + Person.getWidth() / 2),
					(float) (person.getY() + Person.getHeight() / 2), this));
		}

	}

	/**
	 * 生成障碍物
	 */
	public void addObs() {

		synchronized (this) {
			int temp = random.nextInt();
			if (temp % 301 == 0)
				obses.add(new Arrow(this));
			else if (temp % 509 == 0)
				obses.add(new Needle(this));
			else if (temp % 703 == 0)
				obses.add(new Dart(this));
		}
	}

	/**
	 * 生成敌人
	 */
	public void addEnemy() {
		// TODO Auto-generated method stub
		synchronized (this) {
			int temp = random.nextInt();
			if (temp % 407 == 0)
				enemies.add(new Bat(this));
			else if (temp % 607 == 0)
				enemies.add(new Samurai(this));
		}

	}

	/**
	 * 令敌人移动并判断，如果越界就删除
	 */
	public synchronized void enemyMove() {
		for (Enemy enemy : enemies) {
			if (enemy.isOutOfBound() || enemy.isCollapse()) {
				removeEnemies.add(enemy);
			} else
				enemy.move();
		}
		for (Enemy removeEnemy : removeEnemies) {
			enemies.remove(removeEnemy);
		}
		removeEnemies = new ArrayList<Enemy>();

	}

	/**
	 * 令子弹移动并判断，如果越界就删除
	 */
	public synchronized void bulletFly() {
		for (Bullet bullet : bullets) {
			if (bullet.isOutOfBound()) {
				removeBullets.add(bullet);
			} else
				bullet.move();
		}
		for (Bullet removeBullet : removeBullets) {
			bullets.remove(removeBullet);
		}
		removeBullets = new ArrayList<Bullet>();

	}

	/**
	 * 令障碍物移动并判断，如果越界就删除
	 */
	public synchronized void obsMove() {
		for (Obstacle obs : obses) {
			if (obs.outofBounds() || obs.isCollapse()) {
				removeObses.add(obs);
			} else {
				obs.step();
			}
		}
		for (Obstacle removeObs : removeObses) {
			obses.remove(removeObs);
		}
		removeObses = new ArrayList<Obstacle>();

	}

	/**
	 * 从地上跳起
	 */
	public void personJump() {
		person.jumpup();
	}

	/**
	 * 从空中自由下落
	 */
	public void personDropdown() {
		person.dropup();
	}

	/**
	 * 在空中快速落地
	 */
	public void personDown() {
		person.jumpdown();
	}

	/**
	 * 从绳子下面翻到绳子上面
	 */
	public void personCrossUp() {
		person.overturnup();
	}

	/**
	 * 从绳子上翻到绳子下面
	 */
	public void personCrossDown() {
		person.overturndown();
	}

	/**
	 * 除了人物外全部移动一次
	 * 
	 * @throws LineUnavailableException
	 * @throws IOException
	 * @throws UnsupportedAudioFileException
	 */
	public void stepMovable() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
		synchronized ("check") {

			if (!ifBar) {
				if (person.isDead()) {
					// System.out.println("\033[1;32mdead frame output\033[0m");
					String score = Long.toString(person.getScore());
					endGame = true;
					person.reset();
					LoginFrame.user.updateNewCoinInformation(LoginFrame.user,
							(int) person.getScore() / 1000 + LoginFrame.user.getCoin());
					mainFrame.dispose();
					new SuccessFrame(score);

				}
			}

			// 有尽头模式
			if (ifBar) {
				if (person.getScore() >= 60000 && person.getScore() < 60040) {
					// System.out.println("\033[1;32msuccess frame output\033[0m");
					endGame = true;
					mainFrame.dispose();
					new SuccessFrame(Long.toString(person.getScore()));
					person.reset();
				} else if (person.isDead()) {
					endGame = true;
					LoginFrame.user.updateNewCoinInformation(LoginFrame.user,
							(int) person.getScore() / 1000 + LoginFrame.user.getCoin());
					mainFrame.dispose();
					new FailFrame(Long.toString(person.getScore()));
					person.reset();
				}
			}

			if (person.isUnderRope()) {
				person.stepdown();
			} else {
				person.stepup();
				person.dropdown();
			}
			person.restoreHealth();
			bulletFly();
			fireWork();
			obsMove();
			enemyMove();
			background.changePositionRelative(-2f, 0);
		}
	}

	/**
	 * 使用武器
	 * 
	 * @param weaponName
	 */

	public void useWeapon(Weapon weapon, Table table) {

		double cd = weapon.getCd();// 获取是否可使用
		if (cd == 0) {
			int index = weapon.getStageNum();// 获取使用次数
			if (weapon.getWeaponEffect().getIsBullet())// 判断是否为射出子弹的武器
			{
				table.bullets.add(new Bullet((float) (person.getX() + Person.getWidth() / 2),
						(float) (person.getY() + Person.getHeight() / 2), table));
			} else if (weapon.getWeaponEffect().getIsFire())// 判断是否为喷出火焰的武器
			{
				table.fire.add(new Fire(1 * 1000, table));
			} else {
				for (int i = 0; i < index; i++) {
					switch (weapon.getDirection(i))// 获取释放方向
					{
					case 'w':
						// 删除障碍物
						for (Obstacle obs : table.obses) {
							if (((person.getX() <= obs.getX()) && ((person.getX() + Person.WIDTH) >= obs.getX()))
									|| ((person.getX() <= (obs.getX() + obs.WIDTH))
											&& ((person.getX() + Person.WIDTH) >= (obs.getX() + obs.WIDTH))))// 判断是否可能发生碰撞
							{
								if (((person.getY() <= obs.getY())
										&& ((person.getY() + Person.HEIGHT + weapon.getDistance(i)) >= obs.getY()))
										|| ((person.getY() <= (obs.getY() + obs.HEIGHT)) && ((person.getY()
												+ Person.HEIGHT + weapon.getDistance(i)) >= (obs.getY() + obs.HEIGHT))))// 判断是否发生碰撞
								{
									removeObses.add(obs);// 删除发生碰撞的障碍物
								}
							}
						}
						// 删除敌人
						for (Enemy enemy : table.enemies) {
							if (((person.getX() <= enemy.getX()) && ((person.getX() + Person.WIDTH) >= enemy.getX()))
									|| ((person.getX() <= (enemy.getX() + Enemy.ENEMY_WIDTH))
											&& ((person.getX() + Person.WIDTH) >= (enemy.getX() + Enemy.ENEMY_WIDTH))))// 判断是否可能发生碰撞
							{
								if (((person.getY() <= enemy.getY())
										&& ((person.getY() + Person.HEIGHT + weapon.getDistance(i)) >= enemy.getY()))
										|| ((person.getY() <= (enemy.getY() + Enemy.ENEMY_HEIGHT)) && ((person.getY()
												+ Person.HEIGHT
												+ weapon.getDistance(i)) >= (enemy.getY() + Enemy.ENEMY_HEIGHT))))// 判断是否发生碰撞
								{
									removeEnemies.add(enemy);// 删除发生碰撞的敌人
								}
							}
						}
						break;
					case 'd':
						// 删除障碍物
						for (Obstacle obs : obses) {
							if (((person.getY() <= obs.getY()) && ((person.getY() + Person.HEIGHT) >= obs.getY()))
									|| ((person.getY() <= (obs.getY() + obs.HEIGHT))
											&& ((person.getY() + Person.HEIGHT) >= (obs.getY() + obs.HEIGHT))))// 判断是否可能发生碰撞
							{
								if (((person.getX() <= obs.getX())
										&& ((person.getX() + Person.WIDTH + weapon.getDistance(i)) >= obs.getX()))
										|| ((person.getX() <= (obs.getX() + obs.WIDTH)) && ((person.getX()
												+ Person.WIDTH + weapon.getDistance(i)) >= (obs.getX() + obs.WIDTH))))// 判断是否发生碰撞
								{
									removeObses.add(obs);// 删除发生碰撞的障碍物
								}
							}
						}
						// 删除敌人
						for (Enemy enemy : table.enemies) {
							if (((person.getY() <= enemy.getY()) && ((person.getY() + Person.HEIGHT) >= enemy.getY()))
									|| ((table.getPersonY() <= (enemy.getY() + Enemy.ENEMY_HEIGHT)) && ((person.getY()
											+ Person.HEIGHT) >= (enemy.getY() + Enemy.ENEMY_HEIGHT))))// 判断是否可能发生碰撞
							{
								if (((person.getX() <= enemy.getX())
										&& ((person.getX() + Person.WIDTH + weapon.getDistance(i)) >= enemy.getX()))
										|| ((person.getX() <= (enemy.getX() + Enemy.ENEMY_WIDTH)) && ((person.getX()
												+ Person.WIDTH
												+ weapon.getDistance(i)) >= (enemy.getX() + Enemy.ENEMY_WIDTH))))// 判断是否发生碰撞
								{
									removeEnemies.add(enemy);// 删除发生碰撞的敌人
								}
							}
						}
						break;
					case 's':
						for (Obstacle obs : table.obses) {
							if (!(((obs.getX() + Obstacle.WIDTH) < (person.getX() - weapon.getDistance(i)))
									|| ((obs.getX()) > (person.getX() + person.WIDTH + weapon.getDistance(i)))
									|| ((obs.getY() + obs.HEIGHT) < (person.getY() - weapon.getDistance(i)))
									|| ((obs.getY()) > (person.getY() + person.HEIGHT + weapon.getDistance(i)))))// 判断人周围weapon.getDistance范围内的方形区域是否有障碍物
							{
								removeObses.add(obs);// 删除发生碰撞的障碍物
							}
						}
						for (Enemy enemy : table.enemies) {
							if (!(((enemy.getX() + Enemy.ENEMY_WIDTH) < (person.getX() - weapon.getDistance(i)))
									|| ((enemy.getX()) > (person.getX() + person.WIDTH + weapon.getDistance(i)))
									|| ((enemy.getY() + Enemy.ENEMY_HEIGHT) < (person.getY() - weapon.getDistance(i)))
									|| ((enemy.getY()) > (person.getY() + person.HEIGHT + weapon.getDistance(i)))))// 判断人周围weapon.getDistance范围内的方形区域是否有敌人
							{
								removeEnemies.add(enemy);// 删除发生碰撞的敌人
							}
						}
						break;
					}
				}
			}
			weapon.updateCdStamp();// 等待Weapon类完成
		}
	}

	/**
	 * 控制人物上下翻
	 */
	public void personFlip() {
		person.flip();
	}

	public boolean getIfEndGame() {
		return endGame;
	}

	/**
	 * 专供Painter调用，true才会画进度条
	 * 
	 * @return
	 */
	public boolean getIfBar() {
		return ifBar;
	}

	/**
	 * 专供MainPanel调用
	 * 
	 * @param setIfBar
	 */
	public void setIfBar(boolean setIfBar) {
		ifBar = setIfBar;
	}

}
