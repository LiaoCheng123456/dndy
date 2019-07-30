package com.dndy.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Properties;

public class PropertiesUtil {
	private volatile static Properties pps;

	public static Properties getProperties() {
		if (pps == null) {
			synchronized (PropertiesUtil.class) {
				if (pps == null) {
					pps = new Properties();
					return pps;
				}
			}
		}
		return pps;
	}

	/**
	 * 根据Key读取Value
	 * @param fileName 文件名字
	 * @param key 要读取的key
	 * @return
	 */
	public static String GetValueByKey(String fileName, String key) {
		Properties pps = PropertiesUtil.getProperties();
		try {
			pps.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
			return pps.getProperty(key);

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 读取Properties的全部信息
	 * 
	 * @param fileName
	 *            文件名字
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public static void GetAllProperties(String fileName) throws IOException {
		Properties pps = PropertiesUtil.getProperties();
		// String a = Object.class.getClassLoader().getResource("/").getPath();
		// String filePath =
		// Object.class.getClassLoader().getResource(fileName).getPath();
		// InputStream inputStream = new FileInputStream(filePath);
		// BufferedReader bf = new BufferedReader(new
		// InputStreamReader(inputStream, "UTF-8"));
		// pps.load(bf);
		// InputStream in = new BufferedInputStream(new
		// FileInputStream(fileName));
		// pps.load(in);
		pps.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		Enumeration en = pps.propertyNames(); // 得到配置文件的名字

		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			System.out.println(strKey + "=" + strValue);
		}

	}

	/**
	 * 写入Properties信息
	 * 
	 * @param fileName
	 *            文件名
	 * @param pKey
	 * @param pValue
	 * @throws IOException
	 */
	public static void WriteProperties(String fileName, String pKey, String pValue) throws IOException {
		Properties pps = new Properties();// PropertiesUtil.getJedisPoolInstance();

		// InputStream in = new FileInputStream(fileName);
		// // 从输入流中读取属性列表（键和元素对）
		// pps.load(in);
		pps.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName));
		// 调用 Hashtable 的方法 put。使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream out = new FileOutputStream(fileName);
		// pps.setProperty(pKey, pValue);
		// // 以适合使用 load 方法加载到 Properties 表中的格式，
		// // 将此 Properties 表中的属性列表（键和元素对）写入输出流
		// pps.store(out, "Update " + pKey + " name");

		pps.setProperty("phone", "10086");
		pps.store(out, "The New properties file");

	}

	public static void main(String[] args) throws IOException {
		// String value = GetValueByKey("Test.properties", "name");
		// System.out.println(value);

		GetAllProperties("qiniu.properties");

		// GetValueByKey("jdbc.properties", "dpsFail.notify");
		// File f = new
		// File(Object.class.getClass().getResource("/").getPath());
		// System.out.println(f);
		// String a = Object.class.getClassLoader().getResource("/").getPath();
		// GetAllProperties("../../patch/ios/demo.js");
		// wechatPay.notify=https://apiv2.saohuijia.com/pay/notify/wechatPay
		// alipay.notify=https://apiv2.saohuijia.com/pay/notify/alipay
		// dpsSuccess.notify=https://apiv2.saohuijia.com/pay/notify/dpsSuccess
		// dpsFail.notify=https://apiv2.saohuijia.com/pay/notify/dpsFail
		// GetAllProperties("/WEB-INF/config/jdbc.properties");
		// WriteProperties("log4j.properties", "long", "212");
	}

}
