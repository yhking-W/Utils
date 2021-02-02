JDBCUtils工具类
public class JDBAUtils {
	private static String url;
	private static String username;
	private static String password;
	private static String driver;
	//静态代码块，只执行一次
	static {
		//读取资源文件获取值
		try {
			//1.创建Properties集合类
			Properties pro = new Properties();
			
			//获取src路径下的文件方式-->ClassLoader 类加载器
			ClassLoader classLoader = JDBCUtils.class.getClassLoader();
			URL res = classLoader.getResource("jdbc.properties");
			String path = res.getPath();
			//2.加载文件
			pro.load(new FileRader(path));
			//3.获取数据，赋值
			url = pro.getProperty("url");
			username = pro.getProperty("username");
			password = pro.getProperty("password");
			driver = pro.getProperty("driver");
			//4.注册驱动
			Class.forName(driver);
		} catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			throw new IllegalArgumentException("非法异常");
		}
	}
	//获得数据库连接
	public static Connection getConn()throws SQLException{
		return DriverManager.getConnection(url,usename,password);
	}
	//数据关流
	public static void close(Connection conn,PreparedStatement ps,ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null){
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//数据关流重载
	public static void close(Connection conn,PreparedStatement ps){
		close(conn,ps,null);
	}
}