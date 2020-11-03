package me.tinkot.namecase;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

/**
 * @author tjeerdvandijk
 */
final public class Config {
	public static String version = "3.8";
	private static Configuration configuration;
	private static String pluginFolder;


	/**
	 *  创建配置文件的新实例。
	 * @throws IOException
	 */
	private Config()
	{
	}
	public static void reload()
	{
		try
		{
			pluginFolder = NameCase.instance.getDataFolder().getAbsolutePath();
			folderParenting();
			configParenting();
			if(!getString("配置文件版本").equals(Config.version))
			{
				NameCase.instance.getLogger().warning("§4配置文件版本不匹配，请删除");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 *  不存在时创建文件夹
	 */
	private static void folderParenting() {
		File folder = new File(pluginFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}

	}

	/**
	 *  当它文件夹存在时创建配置
	 *
	 * @throws IOException
	 */
	private static void configParenting() throws IOException {
		File file = new File(pluginFolder, "config.yml");
		if (!file.exists()) {
			Files.copy(NameCase.instance.getResourceAsStream("config.yml"), file.toPath());
		}
		setTargetFile(file);
	}

	/**
	 *  为这个Config实例设置目标配置文件
	 *
	 * @param file
	 * @throws IOException
	 */
	private static void setTargetFile(File file) throws IOException {
		configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
	}

	/**
	 *  获取插件文件夹的绝对路径
	 *
	 * @return
	 */
	public static String getAbsolutePluginFolder() {
		return pluginFolder;
	}


	/**
	 * 从配置获取...
	 *
	 * @param key to data
	 * @return the string
	 */
	static public String getString(String key) {
		Object value=configuration.get(key);
		if(value!=null)
			return value.toString();
		else
			return null;
	}

	/**
	 *  从配置中获取int
	 *
	 * @param key to data
	 * @return the int
	 */
	public static Integer getInt(String key) {
		return configuration.getInt(key);
	}

}
