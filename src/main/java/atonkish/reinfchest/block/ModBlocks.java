package atonkish.reinfchest.block;

import java.util.HashMap;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import atonkish.reinfcore.util.ReinforcingMaterial;
import atonkish.reinfchest.ReinforcedChestsMod;
import atonkish.reinfchest.block.entity.ModBlockEntityType;

public class ModBlocks {
    public static final HashMap<ReinforcingMaterial, Block> REINFORCED_CHEST_MAP;

    public static void init() {
    }

    private static Block register(String id, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(ReinforcedChestsMod.MOD_ID, id), block);
    }

    private static FabricBlockSettings createMaterialSettings(ReinforcingMaterial material) {
        FabricBlockSettings settings;
        switch (material) {
            case COPPER:
                settings = FabricBlockSettings.of(Material.METAL, MapColor.ORANGE).strength(2.5F, 6.0F)
                        .sounds(BlockSoundGroup.COPPER);
                break;
            case IRON:
                settings = FabricBlockSettings.of(Material.METAL, MapColor.IRON_GRAY).strength(2.5F, 6.0F)
                        .sounds(BlockSoundGroup.METAL);
                break;
            case GOLD:
                settings = FabricBlockSettings.of(Material.METAL, MapColor.GOLD).strength(2.5F, 6.0F)
                        .sounds(BlockSoundGroup.METAL);
                break;
            case DIAMOND:
                settings = FabricBlockSettings.of(Material.METAL, MapColor.DIAMOND_BLUE).strength(2.5F, 6.0F)
                        .sounds(BlockSoundGroup.METAL);
                break;
            case NETHERITE:
                settings = FabricBlockSettings.of(Material.METAL, MapColor.BLACK).strength(2.5F, 1200.0F)
                        .sounds(BlockSoundGroup.NETHERITE);
                break;
            default:
                settings = FabricBlockSettings.of(Material.WOOD).strength(2.5F).sounds(BlockSoundGroup.WOOD);
        }
        settings = settings.breakByTool(FabricToolTags.AXES);
        return settings;
    }

    static {
        REINFORCED_CHEST_MAP = new HashMap<>();
        for (ReinforcingMaterial material : ReinforcingMaterial.values()) {
            Block block = register(material.getName() + "_chest",
                    new ReinforcedChestBlock(material, createMaterialSettings(material), () -> {
                        return ModBlockEntityType.REINFORCED_CHEST_MAP.get(material);
                    }));
            REINFORCED_CHEST_MAP.put(material, block);
        }
    }
}