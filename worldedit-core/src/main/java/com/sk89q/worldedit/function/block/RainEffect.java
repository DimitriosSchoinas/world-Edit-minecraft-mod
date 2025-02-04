
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
        import com.sk89q.worldedit.function.pattern.Pattern;
        import com.sk89q.worldedit.function.pattern.RandomPattern;
        import com.sk89q.worldedit.math.BlockVector3;
        import com.sk89q.worldedit.world.biome.BiomeTypes;
        import com.sk89q.worldedit.world.block.*;
        import com.sk89q.worldedit.world.weather.WeatherTypes;


        import static com.google.common.base.Preconditions.checkNotNull;

        import java.util.Random;

        /**
         * Changes the blocks of the building to make the building have a drought effect
         */
        public class RainEffect implements LayerFunction {

            private static final double PUDDLE = 0.1;
            private static final double TALLFLOWER = 0.08;

            private final EditSession editSession;
            private final Mask mask;
            private int affected = 0;
            private final Random random;

            /**
             * Make a new DroughtEffect object.
             *
             * @param editSession an edit session
             */
            public RainEffect(EditSession editSession) {
                checkNotNull(editSession);
                this.editSession = editSession;
                this.mask =  new MaskUnion(new BlockTypeMask(editSession, BlockTypes.WATER, BlockTypes.OAK_LOG, BlockTypes.SPRUCE_LOG,
                        BlockTypes.SPRUCE_PLANKS), new BlockCategoryMask(editSession, BlockCategories.LEAVES),
                        new BlockCategoryMask(editSession, BlockCategories.LOGS), new BlockCategoryMask(editSession, BlockCategories.DIRT),
                        new BlockCategoryMask(editSession, BlockCategories.PLANKS));
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

            private Pattern growCrops() {
                RandomPattern pattern = new RandomPattern();
                BlockType grass = BlockTypes.SHORT_GRASS;
                if (grass == null) {
                    // Fallback for <1.20.3 compat
                    @SuppressWarnings("deprecation")
                    BlockType deprecatedGrass = BlockTypes.GRASS;
                    grass = deprecatedGrass;
                }

                pattern.add(grass.getDefaultState(), 300);
                pattern.add(BlockTypes.POPPY.getDefaultState(), 5);
                pattern.add(BlockTypes.DANDELION.getDefaultState(), 5);
                pattern.add(BlockTypes.ROSE_BUSH.getDefaultState(), 5);
                pattern.add(BlockTypes.ORANGE_TULIP.getDefaultState(), 5);
                pattern.add(BlockTypes.PINK_TULIP.getDefaultState(), 5);
                pattern.add(BlockTypes.CORNFLOWER.getDefaultState(), 5);
                pattern.add(BlockTypes.BLUE_ORCHID.getDefaultState(), 5);
                return pattern;
            }

            private BlockState getTargetBlock(BlockState currentBlock, BlockVector3 position) throws WorldEditException {

                if (currentBlock.getBlockType() == BlockTypes.GRASS_BLOCK) {
                    editSession.setBlock(position.add(0, 1, 0), growCrops().applyBlock(position));

                    if (random.nextDouble() < TALLFLOWER) {
                        editSession.setBlock(position.add(0, 1, 0), BlockTypes.ROSE_BUSH.getDefaultState()
                                .with(BlockTypes.ROSE_BUSH.getProperty("half"), "lower"));

                        editSession.setBlock(position.add(0, 2, 0), BlockTypes.ROSE_BUSH.getDefaultState()
                                .with(BlockTypes.ROSE_BUSH.getProperty("half"), "upper"));
                    }
                }

                if (random.nextDouble() < PUDDLE && currentBlock.getBlockType() == BlockTypes.GRASS_BLOCK) {
                        return BlockTypes.WATER.getDefaultState();
                }

                if (BlockCategories.PLANKS.contains(currentBlock.getBlockType())) {
                    return BlockTypes.SPRUCE_PLANKS.getDefaultState();
                } else if (BlockCategories.LOGS.contains(currentBlock.getBlockType())) {
                    return BlockTypes.SPRUCE_LOG.getDefaultState();
                } else{
                    return currentBlock;
                }
            }

            private boolean applyHellEffect(BlockVector3 position, int depth) throws WorldEditException {
                BlockState currentBlock  = editSession.getBlock(position);
                BlockState targetBlock = getTargetBlock(currentBlock, position);

                if (currentBlock.equalsFuzzy(targetBlock)) {
                    return false;
                }

                return editSession.setBlock(position, targetBlock);
            }

            @Override
            public boolean apply(BlockVector3 position, int depth) throws WorldEditException {
                if (mask.test(position)) {
                    if (applyHellEffect(position, depth)) {
                        ++affected;
                    }
                }
                editSession.setBiome(position, BiomeTypes.DARK_FOREST);
                editSession.getWorld().setWeather(WeatherTypes.RAIN);

                return true;
            }
        }
