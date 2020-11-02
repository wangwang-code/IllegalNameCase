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
public class Config {
	public static String version = "3.7";
	private Configuration configuration;
	private me.tinkot.namecase.NameCase plugin;
	private String pluginFolder;


	/**
	 *  创建配置文件的新实例。
	 * @throws IOException
	 * @param plugin
	 */
	public Config(NameCase plugin) {
		try {
			this.plugin  = plugin;
			pluginFolder = plugin.getDataFolder().getAbsolutePath();
			folderParenting();
			configParenting();
			if(!getString("配置文件版本").equals(Config.version))
			{
				plugin.getLogger().warning("§4配置文件不匹配，请删除");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 *  不存在时创建文件夹
	 */
	private void folderParenting() {
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
	private void configParenting() throws IOException {
		File file = new File(pluginFolder, "config.yml");
		if (!file.exists()) {
			Files.copy(plugin.getResourceAsStream("config.yml"), file.toPath());
		}
		setTargetFile(file);
	}

	/**
	 *  为这个Config实例设置目标配置文件
	 *
	 * @param file
	 * @throws IOException
	 */
	private void setTargetFile(File file) throws IOException {
		configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
	}

	/**
	 *  获取插件文件夹的绝对路径
	 *
	 * @return
	 */
	public String getAbsolutePluginFolder() {
		return pluginFolder;
	}


	/**
	 * 从配置获取...
	 *
	 * @param key to data
	 * @return the string
	 */
	public String getString(String key) {
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
	public Integer getInt(String key) {
		return configuration.getInt(key);
	}

}
