package data.scripts.plugins;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.plugins.LevelupPlugin;

public class LevelupPluginImpl implements LevelupPlugin {

	public static int XP_AFTER_MAX_LEVEL = 450000;

	public static long [] XP_PER_LEVEL = new long [] {
		0,
		2500,
		5000,
		7500,
		10000,  // level 5, 2500 each
		15000,
		20000,
		25000,
		30000,
		35000,  // level 10, 5000 each
		42500,
		50000,
		57500,
		65000,
		72500,  // level 15, 7500 each
		82500,
		92500,
		102500,
		112500,
		122500, // level 20, 10000 each
		135000,
		147500,
		160000,
		172500,
		185000, // level 25, 12500 each
		200000,
		215000,
		230000,
		245000,
		260000, // level 30, 15000 each
		277500,
		295000,
		312500,
		330000,
		347500, // level 35, 17500 each
		367500,
		387500,
		407500,
		427500,
		447500, // level 40, 20000 each
	};

	public static long [] TOTAL_XP_PER_LEVEL = new long [XP_PER_LEVEL.length];

	static {
		long total = 0;
		for (int i = 0; i < XP_PER_LEVEL.length; i++) {
			total += XP_PER_LEVEL[i];
			TOTAL_XP_PER_LEVEL[i] = total;
		}
	}

	public int getPointsAtLevel(int level) {
		return (int) Global.getSettings().getFloat("skillPointsPerLevel");
	}

	public int getMaxLevel() {
		return (int) Global.getSettings().getFloat("playerMaxLevel");
	}

	public int getStoryPointsPerLevel() {
		return (int) Global.getSettings().getFloat("storyPointsPerLevel");
	}

	public int getBonusXPUseMultAtMaxLevel() {
		return (int) Global.getSettings().getFloat("bonusXPUseMultAtMaxLevel");
	}

	public long getXPForNextLevel(int level) {
		if (level < XP_PER_LEVEL.length) {
			return XP_PER_LEVEL[level];
		}

		return (long) (XP_PER_LEVEL[XP_PER_LEVEL.length - 1]);
	}

	public long getXPForLevel(int level) {
		if (level <= 1) return 0;

		if (level - 1 < TOTAL_XP_PER_LEVEL.length) {
			return TOTAL_XP_PER_LEVEL[level - 1];
		}

		return TOTAL_XP_PER_LEVEL[TOTAL_XP_PER_LEVEL.length - 1] +
			XP_AFTER_MAX_LEVEL * (level - TOTAL_XP_PER_LEVEL.length);
	}

	public static void main(String[] args) {
		LevelupPluginImpl plugin = new LevelupPluginImpl();
		// System.out.println(plugin.getXPForLevel(16) - plugin.getXPForLevel(15));

		for (int i = 1; i < 41; i++) {
			//System.out.println(i + ": " + (plugin.getXPForLevel(i) - plugin.getXPForLevel(i - 1)));
			System.out.println(i + ": " + (plugin.getXPForLevel(i)));
		}
	}
}
