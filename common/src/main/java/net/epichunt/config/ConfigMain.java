package net.epichunt.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.CollapsibleObject;
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart;


@Config(name = "epichunt")
public class ConfigMain implements ConfigData {
    @CollapsibleObject
    public DeerConfig deer = new DeerConfig();
    @CollapsibleObject
    public RoeDeerConfig roe_deer = new RoeDeerConfig();
    @CollapsibleObject
    public DoeConfig doe = new DoeConfig();
    @CollapsibleObject
    public CaribouConfig caribou = new CaribouConfig();
    @CollapsibleObject
    public MooseConfig moose = new MooseConfig();

    @CollapsibleObject
    public DuckConfig duck = new DuckConfig();
    @CollapsibleObject
    public DrakeConfig drake = new DrakeConfig();
    @CollapsibleObject
    public GooseConfig goose = new GooseConfig();
    @CollapsibleObject
    public PheasantConfig pheasant = new PheasantConfig();
    @CollapsibleObject
    public QuailConfig quail = new QuailConfig();

    @CollapsibleObject
    public YakConfig yak = new YakConfig();
    @CollapsibleObject
    public HighlandConfig highland_cow = new HighlandConfig();
    @CollapsibleObject
    public WisentConfig wisent = new WisentConfig();

    @CollapsibleObject
    public GoatConfig short_haired_goat = new GoatConfig();
    @CollapsibleObject
    public BearConfig brown_bear = new BearConfig();
    @CollapsibleObject
    public BoarConfig boar = new BoarConfig();

    @CollapsibleObject
    public LynxConfig lynx = new LynxConfig();
    @CollapsibleObject
    public BadgerConfig badger = new BadgerConfig();
    @CollapsibleObject
    public WolfConfig wild_wolf = new WolfConfig();

    @CollapsibleObject
    public BeaverConfig beaver = new BeaverConfig();
    @CollapsibleObject
    public HareConfig hare = new HareConfig();

    @CollapsibleObject
    public EelConfig eel = new EelConfig();
    @CollapsibleObject
    public SturgeonConfig sturgeon = new SturgeonConfig();
    @CollapsibleObject
    public PikefishConfig pike_fish = new PikefishConfig();

    @CollapsibleObject
    public PollockConfig pollock = new PollockConfig();
    @CollapsibleObject
    public HerringConfig herring = new HerringConfig();
    @CollapsibleObject
    public TroutConfig trout = new TroutConfig();

    @CollapsibleObject
    public CarpConfig carp = new CarpConfig();
    @CollapsibleObject
    public BassConfig bass = new BassConfig();
    @CollapsibleObject
    public MackerelConfig mackerel = new MackerelConfig();

    @CollapsibleObject
    public SardineConfig sardine = new SardineConfig();
    @CollapsibleObject
    public PerchesConfig perches = new PerchesConfig();
    @CollapsibleObject
    public ZanderConfig zander = new ZanderConfig();

    @CollapsibleObject
    public RoachConfig roach = new RoachConfig();
    @CollapsibleObject
    public HalibutConfig halibut = new HalibutConfig();
    @CollapsibleObject
    public CatfishConfig catfish = new CatfishConfig();


    public static class DeerConfig {
        @RequiresRestart
        public int weight = 12;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public DeerConfig() {
        }
    }
    public static class RoeDeerConfig {
        @RequiresRestart
        public int weight = 12;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public RoeDeerConfig() {
        }
    }
    public static class DoeConfig {
        @RequiresRestart
        public int weight = 12;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public DoeConfig() {
        }
    }
    public static class CaribouConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public CaribouConfig() {
        }
    }
    public static class MooseConfig {
        @RequiresRestart
        public int weight = 4;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public MooseConfig() {
        }
    }


    public static class DuckConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public DuckConfig() {
        }
    }
    public static class DrakeConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public DrakeConfig() {
        }
    }
    public static class GooseConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 2;
        @RequiresRestart
        public int maxAmount = 4;
        public GooseConfig() {
        }
    }
    public static class PheasantConfig {
        @RequiresRestart
        public int weight = 15;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public PheasantConfig() {
        }
    }
    public static class QuailConfig {
        @RequiresRestart
        public int weight = 15;
        @RequiresRestart
        public int minAmount = 2;
        @RequiresRestart
        public int maxAmount = 4;
        public QuailConfig() {
        }
    }


    public static class YakConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public YakConfig() {
        }
    }
    public static class HighlandConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public HighlandConfig() {
        }
    }
    public static class WisentConfig {
        @RequiresRestart
        public int weight = 4;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public WisentConfig() {
        }
    }


    public static class GoatConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public GoatConfig() {
        }
    }
    public static class BearConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public BearConfig() {
        }
    }
    public static class BoarConfig {
        @RequiresRestart
        public int weight = 12;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public BoarConfig() {
        }
    }


    public static class BadgerConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 1;
        public BadgerConfig() {
        }
    }
    public static class LynxConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public LynxConfig() {
        }
    }
    public static class WolfConfig {
        @RequiresRestart
        public int weight = 8;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public WolfConfig() {
        }
    }

    public static class BeaverConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public BeaverConfig() {
        }
    }
    public static class HareConfig {
        @RequiresRestart
        public int weight = 12;
        @RequiresRestart
        public int minAmount = 2;
        @RequiresRestart
        public int maxAmount = 4;
        public HareConfig() {
        }
    }

    public static class EelConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 3;
        public EelConfig() {
        }
    }
    public static class SturgeonConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 1;
        public SturgeonConfig() {
        }
    }
    public static class PikefishConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public PikefishConfig() {
        }
    }
    public static class PollockConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public PollockConfig() {
        }
    }
    public static class HerringConfig {
        @RequiresRestart
        public int weight = 30;
        @RequiresRestart
        public int minAmount = 4;
        @RequiresRestart
        public int maxAmount = 7;
        public HerringConfig() {
        }
    }
    public static class TroutConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public TroutConfig() {
        }
    }
    public static class CarpConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public CarpConfig() {
        }
    }
    public static class BassConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public BassConfig() {
        }
    }
    public static class MackerelConfig {
        @RequiresRestart
        public int weight = 30;
        @RequiresRestart
        public int minAmount = 4;
        @RequiresRestart
        public int maxAmount = 7;
        public MackerelConfig() {
        }
    }
    public static class SardineConfig {
        @RequiresRestart
        public int weight = 30;
        @RequiresRestart
        public int minAmount = 4;
        @RequiresRestart
        public int maxAmount = 7;
        public SardineConfig() {
        }
    }
    public static class PerchesConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public PerchesConfig() {
        }
    }
    public static class ZanderConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public ZanderConfig() {
        }
    }
    public static class RoachConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public RoachConfig() {
        }
    }
    public static class HalibutConfig {
        @RequiresRestart
        public int weight = 20;
        @RequiresRestart
        public int minAmount = 3;
        @RequiresRestart
        public int maxAmount = 5;
        public HalibutConfig() {
        }
    }
    public static class CatfishConfig {
        @RequiresRestart
        public int weight = 10;
        @RequiresRestart
        public int minAmount = 1;
        @RequiresRestart
        public int maxAmount = 1;
        public CatfishConfig() {
        }
    }
    public ConfigMain() {
    }
}
