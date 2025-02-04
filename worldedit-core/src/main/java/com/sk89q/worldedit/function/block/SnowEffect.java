
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
        import com.sk89q.worldedit.world.block.BlockCategories;
        import com.sk89q.worldedit.world.block.BlockState;
        import com.sk89q.worldedit.world.block.BlockType;
        import com.sk89q.worldedit.world.block.BlockTypes;
        import com.sk89q.worldedit.world.weather.WeatherTypes;
        import org.mozilla.javascript.ast.Block;

        import java.util.Random;

        import static com.google.common.base.Preconditions.checkNotNull;

        /**
         * Changes the blocks of the building to make the building have a snowy effect
         */
        public class SnowEffect implements LayerFunction {

            private static final double ICE_PERCENTAGE = 0.2;

            private final EditSession editSession;
            private final Mask mask;
            private int affected = 0;
            private final Random random;

            /**
                 * Make a new SnowEffect object.
             *
             * @param editSession an edit session
             */
            public SnowEffect(EditSession editSession) {
                checkNotNull(editSession);
                this.editSession = editSession;
                this.mask = new MaskUnion(new BlockCategoryMask(editSession, BlockCategories.STAIRS),
                        new BlockCategoryMask(editSession, BlockCategories.LOGS),
                        new BlockTypeMask(editSession, BlockTypes.GRASS_BLOCK));
                this.random = new Random();
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


            /**
             * adds blocks on top of the ceiling and changes the wood type
             *
             * @return the current block to be returned
             */
            private BlockState getTargetBlock(BlockState currentBlock, BlockVector3 position) throws WorldEditException {

                if (BlockCategories.LOGS.contains(currentBlock.getBlockType())) {
                    return BlockTypes.STRIPPED_WARPED_STEM.getDefaultState();
                } else if (BlockCategories.STAIRS.contains(currentBlock.getBlockType())) {
                    addBlockAbove(position, BlockTypes.SNOW_BLOCK.getDefaultState());
                }
                return currentBlock;
            }

            /**
             * adds blocks 1 layer above other blocks
             *
             *
             */
            private void addBlockAbove(BlockVector3 position, BlockState choosenBlock)throws  WorldEditException {
                BlockVector3 abovePosition = position.add(0, 1, 0);
                BlockState aboveBlock = editSession.getBlock(abovePosition);

                if (aboveBlock.getBlockType() == BlockTypes.AIR ) {
                    editSession.setBlock(abovePosition, choosenBlock.getBlockType().getDefaultState());
                }
            }

            /**
             * applys the snow effect to the selected building
             *
             * @return the affected blocks
             */
            private boolean applySnowEffect(BlockVector3 position, int depth) throws WorldEditException {
                BlockState currentBlock  = editSession.getBlock(position);
                BlockState targetBlock = getTargetBlock(currentBlock, position );

                if (random.nextDouble() < ICE_PERCENTAGE)
                    addBlockAbove(position, BlockTypes.BLUE_ICE.getDefaultState());

                if (currentBlock.equalsFuzzy(targetBlock))
                    return false;

                return editSession.setBlock(position, targetBlock);
            }

            @Override
            public boolean apply(BlockVector3 position, int depth) throws WorldEditException {
                if (mask.test(position)) {
                    if (applySnowEffect(position, depth)) {
                        ++affected;
                    }
                }

                editSession.setBiome(position, BiomeTypes.SNOWY_BEACH);
                editSession.getWorld().setWeather(WeatherTypes.RAIN);

                return true;
            }
        }
