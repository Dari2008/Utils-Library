package javad.utils.files;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.bspfsystems.yamlconfiguration.configuration.Configuration;
import org.bspfsystems.yamlconfiguration.configuration.ConfigurationSection;
import org.bspfsystems.yamlconfiguration.configuration.InvalidConfigurationException;
import org.bspfsystems.yamlconfiguration.file.FileConfiguration;
import org.bspfsystems.yamlconfiguration.file.FileConfigurationOptions;
import org.bspfsystems.yamlconfiguration.file.YamlConfiguration;
import org.bspfsystems.yamlconfiguration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Config extends FileConfiguration{

	private YamlConfiguration conf;
	private Timer t;
	private boolean saveWehnChanges = true;
	private int millis = 10000;
	private File file = null;
	private TimerTask timerTask = new TimerTask() {
		
		@Override
		public void run() {
			if(file == null || conf == null)return;
			try {
				conf.save(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
	
	public Config(File file) {
		this.file = file;
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		conf = YamlConfiguration.loadConfiguration(file);
		t = new Timer();
	}
	
	public void saveWhenNewSet(boolean b) {
		saveWehnChanges = b;
		if(!saveWehnChanges) {
			t = new Timer();
			t.schedule(timerTask, millis, millis);
		}
	}
	
	public void setAutoSaveInterval(int millis) {
		this.millis = millis;
		t = new Timer();
		t.schedule(timerTask, millis, millis);
	}
	
	public void set(String path, Object obj) {
		save();
		conf.set(path, obj);
	}

	@Override
	public @NotNull String saveToString() {
		save();
		return conf.saveToString();
	}

	@Override
	public void loadFromString(@NotNull String data) throws InvalidConfigurationException {
		save();
		conf.loadFromString(data);
	}

	@Override
	public @NotNull FileConfigurationOptions getOptions() {
		save();
		return conf.getOptions();
	}

	@Override
	public @NotNull FileConfigurationOptions options() {
		save();
		return conf.options();
	}

	@Override
	public void addDefault(@NotNull String path, @Nullable Object value) {
		save();
		conf.addDefault(path, value);
	}

	@Override
	public void addDefaults(@NotNull Map<String, Object> defs) {
		save();
		conf.addDefaults(defs);
	}

	@Override
	public void addDefaults(@NotNull Configuration defs) {
		save();
		conf.addDefaults(defs);
	}

	@Override
	public void setDefaults(@NotNull Configuration defs) {
		save();
		conf.setDefaults(defs);
	}

	@Override
	public @Nullable Configuration getDefaults() {
		save();
		return conf.getDefaults();
	}

	@Override
	public @Nullable ConfigurationSection getParent() {
		save();
		return conf.getParent();
	}

	@Override
	public @NotNull Set<String> getKeys(boolean deep) {
		save();
		return conf.getKeys(deep);
	}

	@Override
	public @NotNull Map<String, Object> getValues(boolean deep) {
		save();
		return conf.getValues(deep);
	}

	@Override
	public @NotNull String getCurrentPath() {
		save();
		return conf.getCurrentPath();
	}

	@Override
	public @NotNull String getName() {
		save();
		return conf.getName();
	}

	@Override
	public @Nullable Configuration getRoot() {
		save();
		return conf.getRoot();
	}

	@Override
	public @Nullable ConfigurationSection getDefaultSection() {
		save();
		return conf.getDefaultSection();
	}

	@Override
	public @NotNull List<String> getComments(@NotNull String path) {
		save();
		return conf.getComments(path);
	}

	@Override
	public @NotNull List<String> getInLineComments(@NotNull String path) {
		save();
		return conf.getInLineComments(path);
	}

	@Override
	public void setComments(@NotNull String path, @Nullable List<String> comments) {
		save();
		conf.setComments(path, comments);
	}

	@Override
	public void setInLineComments(@NotNull String path, @Nullable List<String> inLineComments) {
		save();
		conf.setInLineComments(path, inLineComments);
	}

	@Override
	public @NotNull ConfigurationSection createSection(@NotNull String path) {
		save();
		return conf.createSection(path);
	}

	@Override
	public @NotNull ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
		save();
		return conf.createSection(path, map);
	}

	@Override
	public boolean isSet(@NotNull String path) {
		save();
		return conf.isSet(path);
	}

	@Override
	public boolean contains(@NotNull String path) {
		save();
		return conf.contains(path);
	}

	@Override
	public boolean contains(@NotNull String path, boolean ignoreDefault) {
		save();
		return conf.contains(path, ignoreDefault);
	}

	@Override
	public @Nullable Object get(@NotNull String path) {
		save();
		return conf.get(path);
	}

	@Override
	public @Nullable Object get(@NotNull String path, @Nullable Object def) {
		save();
		return conf.get(path, def);
	}

	@Override
	public boolean isBoolean(@NotNull String path) {
		save();
		return conf.isBoolean(path);
	}

	@Override
	public boolean getBoolean(@NotNull String path) {
		save();
		return conf.getBoolean(path);
	}

	@Override
	public boolean getBoolean(@NotNull String path, boolean def) {
		save();
		return conf.getBoolean(path, def);
	}

	@Override
	public boolean isByte(@NotNull String path) {
		save();
		return conf.isByte(path);
	}

	@Override
	public byte getByte(@NotNull String path) {
		save();
		return conf.getByte(path);
	}

	@Override
	public byte getByte(@NotNull String path, byte def) {
		save();
		return conf.getByte(path, def);
	}

	@Override
	public boolean isShort(@NotNull String path) {
		save();
		return conf.isShort(path);
	}

	@Override
	public short getShort(@NotNull String path) {
		save();
		return conf.getShort(path);
	}

	@Override
	public short getShort(@NotNull String path, short def) {
		save();
		return conf.getShort(path, def);
	}

	@Override
	public boolean isInt(@NotNull String path) {
		save();
		return conf.isInt(path);
	}

	@Override
	public int getInt(@NotNull String path) {
		save();
		return conf.getInt(path);
	}

	@Override
	public int getInt(@NotNull String path, int def) {
		save();
		return conf.getInt(path, def);
	}

	@Override
	public boolean isLong(@NotNull String path) {
		save();
		return conf.isLong(path);
	}

	@Override
	public long getLong(@NotNull String path) {
		save();
		return conf.getLong(path);
	}

	@Override
	public long getLong(@NotNull String path, long def) {
		save();
		return conf.getLong(path, def);
	}

	@Override
	public boolean isFloat(@NotNull String path) {
		save();
		return conf.isFloat(path);
	}

	@Override
	public float getFloat(@NotNull String path) {
		save();
		return conf.getFloat(path);
	}

	@Override
	public float getFloat(@NotNull String path, float def) {
		save();
		return conf.getFloat(path, def);
	}

	@Override
	public boolean isDouble(@NotNull String path) {
		save();
		return conf.isDouble(path);
	}

	@Override
	public double getDouble(@NotNull String path) {
		save();
		return conf.getDouble(path);
	}

	@Override
	public double getDouble(@NotNull String path, double def) {
		save();
		return conf.getDouble(path, def);
	}

	@Override
	public boolean isChar(@NotNull String path) {
		save();
		return conf.isChar(path);
	}

	@Override
	public char getChar(@NotNull String path) {
		save();
		return conf.getChar(path);
	}

	@Override
	public char getChar(@NotNull String path, char def) {
		save();
		return conf.getChar(path, def);
	}

	@Override
	public boolean isString(@NotNull String path) {
		save();
		return conf.isString(path);
	}

	@Override
	public @Nullable String getString(@NotNull String path) {
		save();
		return conf.getString(path);
	}

	@Override
	public @Nullable String getString(@NotNull String path, @Nullable String def) {
		save();
		return conf.getString(path, def);
	}

	@Override
	public boolean isList(@NotNull String path) {
		save();
		return conf.isList(path);
	}

	@Override
	public @Nullable List<?> getList(@NotNull String path) {
		save();
		return conf.getList(path);
	}

	@Override
	public @Nullable List<?> getList(@NotNull String path, @Nullable List<?> def) {
		save();
		return conf.getList(path, def);
	}

	@Override
	public boolean isConfigurationSection(@NotNull String path) {
		save();
		return conf.isConfigurationSection(path);
	}

	@Override
	public @Nullable ConfigurationSection getConfigurationSection(@NotNull String path) {
		save();
		return conf.getConfigurationSection(path);
	}

	@Override
	public <T> @Nullable T getObject(@NotNull String path, @NotNull Class<T> clazz) {
		save();
		return conf.getObject(path, clazz);
	}

	@Override
	public <T> @Nullable T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
		save();
		return conf.getObject(path, clazz, def);
	}

	@Override
	public <T extends ConfigurationSerializable> @Nullable T getSerializable(@NotNull String path,
			@NotNull Class<T> clazz) {
		save();
		return conf.getSerializable(path, clazz);
	}

	@Override
	public <T extends ConfigurationSerializable> @Nullable T getSerializable(@NotNull String path,
			@NotNull Class<T> clazz, @Nullable T def) {
		save();
		return conf.getSerializable(path, clazz, def);
	}

	@Override
	public @NotNull List<Boolean> getBooleanList(@NotNull String path) {
		save();
		return conf.getBooleanList(path);
	}

	@Override
	public @NotNull List<Byte> getByteList(@NotNull String path) {
		save();
		return conf.getByteList(path);
	}

	@Override
	public @NotNull List<Short> getShortList(@NotNull String path) {
		save();
		return conf.getShortList(path);
	}

	@Override
	public @NotNull List<Integer> getIntList(@NotNull String path) {
		save();
		return conf.getIntList(path);
	}

	@Override
	public @NotNull List<Long> getLongList(@NotNull String path) {
		save();
		return conf.getLongList(path);
	}

	@Override
	public @NotNull List<Float> getFloatList(@NotNull String path) {
		save();
		return conf.getFloatList(path);
	}

	@Override
	public @NotNull List<Double> getDoubleList(@NotNull String path) {
		save();
		return conf.getDoubleList(path);
	}

	@Override
	public @NotNull List<Character> getCharList(@NotNull String path) {
		save();
		return conf.getCharList(path);
	}

	@Override
	public @NotNull List<String> getStringList(@NotNull String path) {
		save();
		return conf.getStringList(path);
	}

	@Override
	public @NotNull List<Map<?, ?>> getMapList(@NotNull String path) {
		save();
		return conf.getMapList(path);
	}

	@Override
	public String toString() {
		return conf.toString();
	}
	
	public void save() {
		if(!saveWehnChanges)return;
		try {
			conf.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
