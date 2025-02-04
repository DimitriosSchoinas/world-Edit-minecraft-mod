
        /*
         * WorldEdit, a Minecraft world manipulation toolkit
         * Copyright (C) sk89q <http://www.sk89q.com>
         * Copyright (C) WorldEdit team and contributors
         *
         * This program is free software: you can redistribute it and/or modify
         * it under the terms of the GNU General Public License as published by
         * the Free Software Foundation, either version 3 of the License, or
         * (at your option) any later version.
         *
         * This program is distributed in the hope that it will be useful,
         * but WITHOUT ANY WARRANTY; without even the implied warranty of
         * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
         * GNU General Public License for more details.
         *
         * You should have received a copy of the GNU General Public License
         * along with this program.  If not, see <https://www.gnu.org/licenses/>.
         */
        package com.sk89q.worldedit.function.block;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.function.LayerFunction;
import com.sk89q.worldedit.function.mask.*;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.world.biome.BiomeTypes;
import com.sk89q.worldedit.world.block.*;
import com.sk89q.worldedit.world.weather.WeatherTypes;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Changes the blocks of the building to make the building have a drought effect
 */
public class DroughtEffect implements LayerFunction {

    private final EditSession editSession;
    private final Mask mask;
    private int affected = 0;

    /**
     * Make a new DroughtEffect object.
     *
     * @param editSession an edit session
     */
    public DroughtEffect(EditSession editSession) {
        checkNotNull(editSession);
        this.editSession = editSession;
        this.mask = new MaskUnion(new BlockTypeMask(editSession, BlockTypes.SHORT_GRASS, BlockTypes.FARMLAND,BlockTypes.CLAY,BlockTypes.STONE,BlockTypes.COBBLESTONE,BlockTypes.WATER) ,
            new BlockCategoryMask(editSession, BlockCategories.LEAVES), new BlockCategoryMask(editSession, BlockCategories.FLOWERS),
                new BlockCategoryMask(editSession, BlockCategories.DIRT), new BlockCategoryMask(editSession, BlockCategories.STONE_BRICKS),
                new BlockCategoryMask(editSession, BlockCategories.SLABS),new BlockCategoryMask(editSession, BlockCategories.ICE),new BlockCategoryMask(editSession, BlockCategories.SNOW));
    }

    /**
     * Get the number of affected objects.
     *
     * @return the number of affected
     */
    public int getAffected() {
        return affected;
    }

    @Override
    public boolean isGround(BlockVector3 position) {
        return mask.test(position);
    }

    private BlockState getTargetBlock(BlockState currentBlock) {


        if (BlockCategories.DIRT.contains(currentBlock.getBlockType())) {
            return BlockTypes.COARSE_DIRT.getDefaultState();
        } else if (currentBlock.getBlockType() == BlockTypes.SHORT_GRASS) {
            return BlockTypes.DEAD_BUSH.getDefaultState();
        } else if (BlockCategories.FLOWERS.contains(currentBlock.getBlockType()) && currentBlock.getBlockType() != BlockTypes.CHERRY_LEAVES && currentBlock.getBlockType() != BlockTypes.FLOWERING_AZALEA_LEAVES) {
            return BlockTypes.WITHER_ROSE.getDefaultState();
        } else if (currentBlock.getBlockType() == BlockTypes.FARMLAND) {
            return BlockTypes.DIRT_PATH.getDefaultState();
        } else if (currentBlock.getBlockType() == BlockTypes.CLAY) {
            return BlockTypes.TERRACOTTA.getDefaultState();
        }  else if (BlockCategories.STONE_BRICKS.contains(currentBlock.getBlockType()) || currentBlock.getBlockType() == BlockTypes.STONE || currentBlock.getBlockType() == BlockTypes.COBBLESTONE) {
            return BlockTypes.END_STONE_BRICKS.getDefaultState();
        } else if (BlockCategories.SLABS.contains(currentBlock.getBlockType())) {
            return BlockTypes.SANDSTONE_SLAB.getDefaultState();
        }else{
            return BlockTypes.AIR.getDefaultState();
        }

    }

    /**
     * applys the drought effect to the selected building
     *
     * @return the affected blocks
     */
    private boolean applyDryEffect(BlockVector3 position, int depth) throws WorldEditException {
        BlockState currentBlock  = editSession.getBlock(position);
        BlockState targetBlock = getTargetBlock(currentBlock );

        if (currentBlock.equalsFuzzy(targetBlock)) {
            return false;
        }

        return editSession.setBlock(position, targetBlock);
    }

    @Override
    public boolean apply(BlockVector3 position, int depth) throws WorldEditException {
        if (mask.test(position)) {
            if (applyDryEffect(position, depth)) {
                ++affected;
            }
        }
        editSession.setBiome(position, BiomeTypes.DESERT);
        editSession.getWorld().setWeather(WeatherTypes.CLEAR);

        return true;
    }
}
