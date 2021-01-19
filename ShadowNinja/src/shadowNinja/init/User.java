package shadowNinja.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * 用户数据存储类
 * @author cyx
 * @version 1.0.0
 * @date 2020-12-28
 *
 */
public class User {

	//换行
	private final static String NEW_LINE = System.getProperty("line.separator");

	//用户账号
	private String accountNumber;

	//用户密码/
	private String password;

	//用户拥有的武器
	private String weapon;

	//用户拥有的人物
	private String person;

	//用户金币数
	private int coin;

	//用户无尽模式最高分
	private int bestScore;

	//用户闯关模式通过关数
	private int numberOfPasses;

	/**
	 * 读取文件信息，新建用户对象.
	 * 
	 * @param initalaccountNumber  the account number of the user.
	 * @param initalpassword       the password of the user.
	 * @param initalweapon         the weapon of the user.
	 * @param initalperson         the person of the user.
	 * @param initalcoin           the coin of the user.
	 * @param initalbestScore      the best score of the user.
	 * @param initalnumberOfPasses the number of passes of the user.
	 */
	public User(String initalAccountNumber, String initalPassword, String initalWeapon, String initalPerson,
			int initalCoin, int initalBestScore, int initalNumberOfPasses) {
		super();
		this.accountNumber = initalAccountNumber;
		this.password = initalPassword;
		this.weapon = initalWeapon;
		this.person = initalPerson;
		this.coin = initalCoin;
		this.bestScore = initalBestScore;
		this.numberOfPasses = initalNumberOfPasses;
	}

	/**
	 * 初始化一个对象.
	 * 
	 * @param initalaccountNumber the account number of the user.
	 * @param initalpassword      the password of the user.
	 */
	public User(String initalAccountNumber, String initalPassword) {
		super();
		this.accountNumber = initalAccountNumber;
		this.password = initalPassword;
		this.weapon = "";
		this.person = "";
		this.coin = 0;
		this.bestScore = 0;
		this.numberOfPasses = 0;
	}

	/**
	 * 新用户注册,新建一个用户信息文件
	 * 
	 * @param user
	 * @throws IOException
	 */
	public void registeredAccount(User user) throws IOException {

		FileWriter userInfomatoin = new FileWriter(user.getAccountNumber() + ".txt", true); // 如果为 true，则将数据写入文件末尾处，
		// 而不是写入文件开始处。

		userInfomatoin.write(user.getAccountNumber() + NEW_LINE + user.getPassword() + NEW_LINE + user.weapon + NEW_LINE
				+ user.person + NEW_LINE + user.coin + NEW_LINE + user.bestScore + NEW_LINE + user.numberOfPasses);

		userInfomatoin.close();
	}

	/**
	 * 登录，通过对应信息文件读取用户数据
	 * 
	 * @param initalAccountNumber
	 * @return 已读取文件中用户信息的用户对象
	 * @throws IOException
	 */
	public static User readUserInfomation(String initalAccountNumber) throws IOException {

		BufferedReader userInfomatoin = new BufferedReader(new FileReader(initalAccountNumber + ".txt"));// 构造一个BufferedReader类来读取文件

		String accountNumber = userInfomatoin.readLine();
		String password = userInfomatoin.readLine();
		String weapon = userInfomatoin.readLine();
		String person = userInfomatoin.readLine();
		int coin = Integer.parseInt(userInfomatoin.readLine());
		int bestScore = Integer.parseInt(userInfomatoin.readLine());
		int numberOfPasses = Integer.parseInt(userInfomatoin.readLine());

		User user = new User(accountNumber, password, weapon, person, coin, bestScore, numberOfPasses);
		userInfomatoin.close();
		return user;
	}

	/**
	 * 清空文件
	 * 
	 * @param fileName
	 */
	public static void clearInfoForFile(String fileName) {
		File file = new File(fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write("");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新武器数据
	 * 
	 * @param user
	 * @param newWeapon
	 * @throws IOException
	 */
	public boolean updateWeaponInformation(User user, String newWeapon) throws IOException {

		BufferedReader userInfomatoin = new BufferedReader(new FileReader(user.getAccountNumber() + ".txt"));
		StringBuffer buf = new StringBuffer();
		String line;
		int i = 0;
		boolean flag = true;

		while ((line = userInfomatoin.readLine()) != null) {
			
			i++;
			if (i == 3) {
				if (line == "") {
					buf.append(newWeapon);
				} else {
					StringTokenizer st = new StringTokenizer(line, " ");
					while (st.hasMoreTokens()) {
						if (st.nextToken().equals(newWeapon)) {
							flag = false;
							buf.append(line);
							break;
						}
					}
					if (flag) {
						buf.append(line).append(" " + newWeapon);
						
					}

				}
			} else {
				buf.append(line);
			}
			buf.append(System.getProperty("line.separator"));
		}
		userInfomatoin.close();
		clearInfoForFile(user.getAccountNumber() + ".txt");
		FileWriter userInfomatoin1 = new FileWriter(user.getAccountNumber() + ".txt", true);
		userInfomatoin1.write(buf.toString());
		userInfomatoin1.close();
		if(user.getWeapon().equals("")) {
			user.setWeapon(user.getWeapon() + newWeapon);
		}else {
			user.setWeapon(user.getWeapon() +  " " + newWeapon);
		}
		return flag;
	}

	/**
	 * 更新人物数据
	 * 
	 * @param user
	 * @param newPerson
	 * @throws IOException
	 */
	public boolean updatePersonInformation(User user, String newPerson) throws IOException {

		BufferedReader userInfomatoin = new BufferedReader(new FileReader(user.getAccountNumber() + ".txt"));
		StringBuffer buf = new StringBuffer();
		String line;
		int i = 0;
		boolean flag = true;

		while ((line = userInfomatoin.readLine()) != null) {
			i++;
			if (i == 4) {
				if (line == "") {
					buf.append(newPerson);
				} else {
					StringTokenizer st = new StringTokenizer(line, " ");
					while (st.hasMoreTokens()) {
						if (st.nextToken().equals(newPerson)) {
							flag = false;
							buf.append(line);
							break;
						}
					}
					if (flag) {
						buf.append(line).append(" " + newPerson);
					}
				}
			} else {
				buf.append(line);
			}
			buf.append(System.getProperty("line.separator"));
		}
		userInfomatoin.close();
		clearInfoForFile(user.getAccountNumber() + ".txt");
		FileWriter userInfomatoin1 = new FileWriter(user.getAccountNumber() + ".txt", true);
		userInfomatoin1.write(buf.toString());
		userInfomatoin1.close();
		if(user.getPerson().equals("")) {
			user.setWeapon(user.getWeapon() + newPerson);
		}else {
			user.setWeapon(user.getWeapon() +  " " + newPerson);
		}
		return flag;
	}

	/**
	 * 更新金钱数据
	 * 
	 * @param user
	 * @param newCoin
	 * @throws IOException
	 */
	public void updateNewCoinInformation(User user, int newCoin) throws IOException {

		BufferedReader userInfomatoin = new BufferedReader(new FileReader(user.getAccountNumber() + ".txt"));
		StringBuffer buf = new StringBuffer();
		String line;
		int i = 0;

		while ((line = userInfomatoin.readLine()) != null) {
			i++;
			if (i == 5) {
				buf.append(newCoin);
			} else {
				buf.append(line);
			}
			buf.append(System.getProperty("line.separator"));
		}
		userInfomatoin.close();
		clearInfoForFile(user.getAccountNumber() + ".txt");
		FileWriter userInfomatoin1 = new FileWriter(user.getAccountNumber() + ".txt", true);
		userInfomatoin1.write(buf.toString());
		userInfomatoin1.close();
		user.setCoin(newCoin);
	}

	/**
	 * 更新无尽模式最高分数据
	 * 
	 * @param user
	 * @param newBestScore
	 * @throws IOException
	 */
	public void updateNewBestScoreInformation(User user, int newBestScore) throws IOException {

		BufferedReader userInfomatoin = new BufferedReader(new FileReader(user.getAccountNumber() + ".txt"));
		StringBuffer buf = new StringBuffer();
		String line;
		int i = 0;

		while ((line = userInfomatoin.readLine()) != null) {
			i++;
			if (i == 6) {
				buf.append(newBestScore);
			} else {
				buf.append(line);
			}
			buf.append(System.getProperty("line.separator"));
		}
		userInfomatoin.close();
		clearInfoForFile(user.getAccountNumber() + ".txt");
		FileWriter userInfomatoin1 = new FileWriter(user.getAccountNumber() + ".txt", true);
		userInfomatoin1.write(buf.toString());
		userInfomatoin1.close();
		user.setBestScore(newBestScore);
	}

	/**
	 * 更新闯关模式通过关数数据
	 * 
	 * @param user
	 * @param newNumberOfPasses
	 * @throws IOException
	 */
	public void updateNewNumberOfPassesInformation(User user, int newNumberOfPasses) throws IOException {

		BufferedReader userInfomatoin = new BufferedReader(new FileReader(user.getAccountNumber() + ".txt"));
		StringBuffer buf = new StringBuffer();
		String line;
		int i = 0;

		while ((line = userInfomatoin.readLine()) != null) {
			i++;
			if (i == 7) {
				buf.append(newNumberOfPasses);
			} else {
				buf.append(line);
			}
			buf.append(System.getProperty("line.separator"));
		}
		userInfomatoin.close();
		clearInfoForFile(user.getAccountNumber() + ".txt");
		FileWriter userInfomatoin1 = new FileWriter(user.getAccountNumber() + ".txt", true);
		userInfomatoin1.write(buf.toString());
		userInfomatoin1.close();
		user.setNumberOfPasses(newNumberOfPasses);
	}

	/**
	 * 返回拥有武器个数
	 * @param user
	 * @return
	 */
	public int getWeaponNumber(User user) {
		
		String weapon = user.getWeapon();
		StringTokenizer st = new StringTokenizer(weapon, " ");
		return st.countTokens();
		
	}
	
	/**
	 * 返回拥有人物数量
	 * @param user
	 * @return
	 */
    public int getPersonNumber(User user) {
		
		String person = user.getPerson();
		StringTokenizer st = new StringTokenizer(person, " ");
		return st.countTokens();
		
	}
	
	/**
	 * 测试函数
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		User user = readUserInfomation("zhanghao");
//		System.out.println(user.getAccountNumber() + NEW_LINE + user.getPassword() + NEW_LINE + user.weapon + NEW_LINE
//				+ user.person + NEW_LINE + user.coin + NEW_LINE + user.bestScore + NEW_LINE + user.numberOfPasses);
//		user.updateWeaponInformation(user, "Dao");
		user.updatePersonInformation(user, "ren1");
//		user.updateNewCoinInformation(user, 200);
//		user.updateNewBestScoreInformation(user, 2300);
//		user.updateNewNumberOfPassesInformation(user, 2);
		System.out.println(user.getPersonNumber(user));
	}

	// 属性对应的get()以及set() 函数
	/**
	 * 获取用户名称
	 * @return
	 */
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * 设置用户名称
	 * @param accountNumber
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * 获取用户密码
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 设置用户密码
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 获取武器信息
	 * @return
	 */
	public String getWeapon() {
		return weapon;
	}

	/**
	 * 设置武器信息
	 * @param weapon
	 */
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	/**
	 * 获取人物信息
	 * @return
	 */
	public String getPerson() {
		return person;
	}

	/**
	 * 设置人物信息
	 * @param person
	 */
	public void setPerson(String person) {
		this.person = person;
	}

	/**
	 * 获取金币信息
	 * @return
	 */
	public int getCoin() {
		return coin;
	}

	/**
	 * 设置金币信息
	 * @param coin
	 */
	public void setCoin(int coin) {
		this.coin = coin;
	}

	/**
	 * 获取无尽模式最高分
	 * @return
	 */
	public int getBestScore() {
		return bestScore;
	}

	/**
	 * 设置无尽模式最高分
	 * @param bestScore
	 */
	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	/**
	 * 获取闯关模式闯关数
	 * @return
	 */
	public int getNumberOfPasses() {
		return numberOfPasses;
	}

	/**
	 * 设置闯关模式闯关数
	 * @param numberOfPasses
	 */
	public void setNumberOfPasses(int numberOfPasses) {
		this.numberOfPasses = numberOfPasses;
	}

}
