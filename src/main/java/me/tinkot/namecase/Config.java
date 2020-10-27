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
	private Configuration configuration;
	private me.tinkot.namecase.NameCase plugin;
	private String pluginFolder;

	/**
	 * Creating new instance of the config file.
	 * @throws IOException
	 * @param plugin
	 */
	Config(NameCase plugin) {
		try {
			this.plugin  = plugin;
			pluginFolder = plugin.getDataFolder().getAbsolutePath();
			folderParenting();
			configParenting();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Create folder when it does not exists
	 */
	private void folderParenting() {
		File folder = new File(pluginFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}

	}

	/**
	 * Create config when it does  exists
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
	 * Set the target configuration file for this Config instance
	 *
	 * @param file
	 * @throws IOException
	 */
	private void setTargetFile(File file) throws IOException {
		configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
	}

	/**
	 * Get the absolute path to the plugin's folder
	 *
	 * @return
	 */
	public String getAbsolutePluginFolder() {
		return pluginFolder;
	}


	/**
	 * Get string from the config
	 *
	 * @param key to data
	 * @return the string
	 */
	public String getString(String key) {
		return configuration.getString(key);
	}

	/**
	 * Get int from the config
	 *
	 * @param key to data
	 * @return the int
	 */
	public Integer getInt(String key) {
		return configuration.getInt(key);
	}

}
