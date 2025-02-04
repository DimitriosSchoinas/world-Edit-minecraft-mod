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

package com.sk89q.worldedit.command;

import java.util.LinkedList;
import java.util.Random;
import com.sk89q.worldedit.*;
import com.sk89q.worldedit.command.util.CommandPermissions;
import com.sk89q.worldedit.command.util.CommandPermissionsConditionGenerator;
import com.sk89q.worldedit.command.util.Logging;
import com.sk89q.worldedit.entity.Player;
import com.sk89q.worldedit.extension.input.InputParseException;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.function.mask.BlockTypeMask;
import com.sk89q.worldedit.function.mask.Masks;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.internal.annotation.Radii;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.internal.expression.ExpressionException;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.regions.EllipsoidRegion;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.util.TreeGenerator.TreeType;
import com.sk89q.worldedit.util.formatting.text.TextComponent;
import com.sk89q.worldedit.util.formatting.text.TranslatableComponent;
import com.sk89q.worldedit.world.biome.BiomeType;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.generation.ConfiguredFeatureType;
import com.sk89q.worldedit.world.generation.StructureType;
import org.enginehub.piston.annotation.Command;
import org.enginehub.piston.annotation.CommandContainer;
import org.enginehub.piston.annotation.param.Arg;
import org.enginehub.piston.annotation.param.Switch;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.sk89q.worldedit.command.util.Logging.LogMode.ALL;
import static com.sk89q.worldedit.command.util.Logging.LogMode.PLACEMENT;
import static com.sk89q.worldedit.command.util.Logging.LogMode.POSITION;
import static com.sk89q.worldedit.internal.command.CommandUtil.checkCommandArgument;

/**
 * Commands for the generation of shapes and other objects.
 */
@CommandContainer(superTypes = CommandPermissionsConditionGenerator.Registration.class)
public class GenerationCommands {

    private final WorldEdit worldEdit;

    /**
     * Create a new instance.
     *
     * @param worldEdit reference to WorldEdit
     */
    public GenerationCommands(WorldEdit worldEdit) {
        checkNotNull(worldEdit);
        this.worldEdit = worldEdit;
    }

    @Command(
            name = "/hcyl",
            desc = "Generates a hollow cylinder."
    )
    @CommandPermissions("worldedit.generation.cylinder")
    @Logging(PLACEMENT)
    public int hcyl(Actor actor, LocalSession session, EditSession editSession,
                    @Arg(desc = "The pattern of blocks to generate")
                    Pattern pattern,
                    @Arg(desc = "The radii of the cylinder. 1st is N/S, 2nd is E/W")
                    @Radii(2)
                    List<Double> radii,
                    @Arg(desc = "The height of the cylinder", def = "1")
                    int height) throws WorldEditException {
        return cyl(actor, session, editSession, pattern, radii, height, true);
    }

    @Command(
            name = "/cyl",
            desc = "Generates a cylinder."
    )
    @CommandPermissions("worldedit.generation.cylinder")
    @Logging(PLACEMENT)
    public int cyl(Actor actor, LocalSession session, EditSession editSession,
                   @Arg(desc = "The pattern of blocks to generate")
                   Pattern pattern,
                   @Arg(desc = "The radii of the cylinder. 1st is N/S, 2nd is E/W")
                   @Radii(2)
                   List<Double> radii,
                   @Arg(desc = "The height of the cylinder", def = "1")
                   int height,
                   @Switch(name = 'h', desc = "Make a hollow cylinder")
                   boolean hollow) throws WorldEditException {
        double radiusX;
        double radiusZ;
        switch (radii.size()) {
            case 1:
                radiusX = radiusZ = Math.max(1, radii.get(0));
                break;

            case 2:
                radiusX = Math.max(1, radii.get(0));
                radiusZ = Math.max(1, radii.get(1));
                break;

            default:
                actor.printError(TranslatableComponent.of("worldedit.cyl.invalid-radius"));
                return 0;
        }

        worldEdit.checkMaxRadius(radiusX);
        worldEdit.checkMaxRadius(radiusZ);
        worldEdit.checkMaxRadius(height);

        BlockVector3 pos = session.getPlacementPosition(actor);
        int affected = editSession.makeCylinder(pos, pattern, radiusX, radiusZ, height, !hollow);
        actor.printInfo(TranslatableComponent.of("worldedit.cyl.created", TextComponent.of(affected)));
        return affected;
    }

    @Command(
            name = "/cone",
            desc = "Generates a cone."
    )
    @CommandPermissions("worldedit.generation.cone")
    @Logging(PLACEMENT)
    public int cone(Actor actor, LocalSession session, EditSession editSession,
                    @Arg(desc = "The pattern of blocks to generate")
                    Pattern pattern,
                    @Arg(desc = "The radii of the cone. 1st is N/S, 2nd is E/W")
                    @Radii(2)
                    List<Double> radii,
                    @Arg(desc = "The height of the cone", def = "1")
                    int height,
                    @Switch(name = 'h', desc = "Make a hollow cone")
                    boolean hollow,
                    @Arg(desc = "Thickness of the hollow cone", def = "1")
                    double thickness
    ) throws WorldEditException {
        double radiusX;
        double radiusZ;
        switch (radii.size()) {
            case 1 -> radiusX = radiusZ = Math.max(1, radii.get(0));
            case 2 -> {
                radiusX = Math.max(1, radii.get(0));
                radiusZ = Math.max(1, radii.get(1));
            }
            default -> {
                actor.printError(TranslatableComponent.of("worldedit.cone.invalid-radius"));
                return 0;
            }
        }

        worldEdit.checkMaxRadius(radiusX);
        worldEdit.checkMaxRadius(radiusZ);
        worldEdit.checkMaxRadius(height);

        BlockVector3 pos = session.getPlacementPosition(actor);
        int affected = editSession.makeCone(pos, pattern, radiusX, radiusZ, height, !hollow, thickness);
        actor.printInfo(TranslatableComponent.of("worldedit.cone.created", TextComponent.of(affected)));
        return affected;
    }

    @Command(
            name = "/hsphere",
            desc = "Generates a hollow sphere."
    )
    @CommandPermissions("worldedit.generation.sphere")
    @Logging(PLACEMENT)
    public int hsphere(Actor actor, LocalSession session, EditSession editSession,
                       @Arg(desc = "The pattern of blocks to generate")
                       Pattern pattern,
                       @Arg(desc = "The radii of the sphere. Order is N/S, U/D, E/W")
                       @Radii(3)
                       List<Double> radii,
                       @Switch(name = 'r', desc = "Raise the bottom of the sphere to the placement position")
                       boolean raised) throws WorldEditException {
        return sphere(actor, session, editSession, pattern, radii, raised, true);
    }

    @Command(
            name = "/sphere",
            desc = "Generates a filled sphere."
    )
    @CommandPermissions("worldedit.generation.sphere")
    @Logging(PLACEMENT)
    public int sphere(Actor actor, LocalSession session, EditSession editSession,
                      @Arg(desc = "The pattern of blocks to generate")
                      Pattern pattern,
                      @Arg(desc = "The radii of the sphere. Order is N/S, U/D, E/W")
                      @Radii(3)
                      List<Double> radii,
                      @Switch(name = 'r', desc = "Raise the bottom of the sphere to the placement position")
                      boolean raised,
                      @Switch(name = 'h', desc = "Make a hollow sphere")
                      boolean hollow) throws WorldEditException {
        double radiusX;
        double radiusY;
        double radiusZ;
        switch (radii.size()) {
            case 1:
                radiusX = radiusY = radiusZ = Math.max(0, radii.get(0));
                break;

            case 3:
                radiusX = Math.max(0, radii.get(0));
                radiusY = Math.max(0, radii.get(1));
                radiusZ = Math.max(0, radii.get(2));
                break;

            default:
                actor.printError(TranslatableComponent.of("worldedit.sphere.invalid-radius"));
                return 0;
        }

        worldEdit.checkMaxRadius(radiusX);
        worldEdit.checkMaxRadius(radiusY);
        worldEdit.checkMaxRadius(radiusZ);

        BlockVector3 pos = session.getPlacementPosition(actor);
        if (raised) {
            pos = pos.add(0, (int) radiusY, 0);
        }

        int affected = editSession.makeSphere(pos, pattern, radiusX, radiusY, radiusZ, !hollow);
        if (actor instanceof Player) {
            ((Player) actor).findFreePosition();
        }
        actor.printInfo(TranslatableComponent.of("worldedit.sphere.created", TextComponent.of(affected)));
        return affected;
    }

    @Command(
            name = "forestgen",
            desc = "Generate a forest"
    )
    @CommandPermissions("worldedit.generation.forest")
    @Logging(POSITION)
    public int forestGen(Actor actor, LocalSession session, EditSession editSession,
                         @Arg(desc = "The size of the forest, in blocks", def = "10")
                         int size,
                         @Arg(desc = "The type of forest", def = "tree")
                         TreeType type,
                         @Arg(desc = "The density of the forest, between 0 and 100", def = "5")
                         double density) throws WorldEditException {
        checkCommandArgument(0 <= density && density <= 100, "Density must be between 0 and 100");
        worldEdit.checkMaxRadius(size);
        density /= 100;
        int affected = editSession.makeForest(session.getPlacementPosition(actor), size, density, type);
        actor.printInfo(TranslatableComponent.of("worldedit.forestgen.created", TextComponent.of(affected)));
        return affected;
    }

    @Command(
            name = "pumpkins",
            desc = "Generate pumpkin patches"
    )
    @CommandPermissions("worldedit.generation.pumpkins")
    @Logging(POSITION)
    public int pumpkins(Actor actor, LocalSession session, EditSession editSession,
                        @Arg(desc = "The size of the patch", def = "10")
                        int size) throws WorldEditException {
        worldEdit.checkMaxRadius(size);
        int affected = editSession.makePumpkinPatches(session.getPlacementPosition(actor), size);
        actor.printInfo(TranslatableComponent.of("worldedit.pumpkins.created", TextComponent.of(affected)));
        return affected;
    }

    @Command(
            name = "/feature",
            desc = "Generate Minecraft features"
    )
    @CommandPermissions("worldedit.generation.feature")
    @Logging(POSITION)
    public int feature(Actor actor, LocalSession session, EditSession editSession,
                       @Arg(desc = "The feature")
                       ConfiguredFeatureType feature) throws WorldEditException {
        if (editSession.getWorld().generateFeature(feature, editSession, session.getPlacementPosition(actor))) {
            actor.printInfo(TranslatableComponent.of("worldedit.feature.created"));
        } else {
            actor.printError(TranslatableComponent.of("worldedit.feature.failed"));
        }
        return 0;
    }

    @Command(
            name = "/structure",
            desc = "Generate Minecraft structures"
    )
    @CommandPermissions("worldedit.generation.structure")
    @Logging(POSITION)
    public int structure(Actor actor, LocalSession session, EditSession editSession,
                         @Arg(desc = "The structure")
                         StructureType feature) throws WorldEditException {
        if (editSession.getWorld().generateStructure(feature, editSession, session.getPlacementPosition(actor))) {
            actor.printInfo(TranslatableComponent.of("worldedit.structure.created"));
        } else {
            actor.printError(TranslatableComponent.of("worldedit.structure.failed"));
        }
        return 0;
    }

    @Command(
            name = "/hpyramid",
            desc = "Generate a hollow pyramid"
    )
    @CommandPermissions("worldedit.generation.pyramid")
    @Logging(PLACEMENT)
    public int hollowPyramid(Actor actor, LocalSession session, EditSession editSession,
                             @Arg(desc = "The pattern of blocks to set")
                             Pattern pattern,
                             @Arg(desc = "The size of the pyramid")
                             int size) throws WorldEditException {
        return pyramid(actor, session, editSession, pattern, size, true);
    }

    @Command(
            name = "/pyramid",
            desc = "Generate a filled pyramid"
    )
    @CommandPermissions("worldedit.generation.pyramid")
    @Logging(PLACEMENT)
    public int pyramid(Actor actor, LocalSession session, EditSession editSession,
                       @Arg(desc = "The pattern of blocks to set")
                       Pattern pattern,
                       @Arg(desc = "The size of the pyramid")
                       int size,
                       @Switch(name = 'h', desc = "Make a hollow pyramid")
                       boolean hollow) throws WorldEditException {
        worldEdit.checkMaxRadius(size);
        BlockVector3 pos = session.getPlacementPosition(actor);
        int affected = editSession.makePyramid(pos, pattern, size, !hollow);
        if (actor instanceof Player) {
            ((Player) actor).findFreePosition();
        }
        actor.printInfo(TranslatableComponent.of("worldedit.pyramid.created", TextComponent.of(affected)));
        return affected;
    }

    @Command(
            name = "/generate",
            aliases = {"/gen", "/g"},
            desc = "Generates a shape according to a formula.",
            descFooter = "For details, see https://ehub.to/we/expr"
    )
    @CommandPermissions("worldedit.generation.shape")
    @Logging(ALL)
    public int generate(Actor actor, LocalSession session, EditSession editSession,
                        @Selection Region region,
                        @Arg(desc = "The pattern of blocks to set")
                        Pattern pattern,
                        @Arg(desc = "Expression to test block placement locations and set block type", variable = true)
                        List<String> expression,
                        @Switch(name = 'h', desc = "Generate a hollow shape")
                        boolean hollow,
                        @Switch(name = 'r', desc = "Use the game's coordinate origin")
                        boolean useRawCoords,
                        @Switch(name = 'o', desc = "Use the placement's coordinate origin")
                        boolean offset,
                        @Switch(name = 'c', desc = "Use the selection's center as origin")
                        boolean offsetCenter) throws WorldEditException {

        final Vector3 zero;
        Vector3 unit;

        if (useRawCoords) {
            zero = Vector3.ZERO;
            unit = Vector3.ONE;
        } else if (offset) {
            zero = session.getPlacementPosition(actor).toVector3();
            unit = Vector3.ONE;
        } else if (offsetCenter) {
            final Vector3 min = region.getMinimumPoint().toVector3();
            final Vector3 max = region.getMaximumPoint().toVector3();

            zero = max.add(min).multiply(0.5);
            unit = Vector3.ONE;
        } else {
            final Vector3 min = region.getMinimumPoint().toVector3();
            final Vector3 max = region.getMaximumPoint().toVector3();

            zero = max.add(min).multiply(0.5);
            unit = max.subtract(zero);

            if (unit.x() == 0) {
                unit = unit.withX(1.0);
            }
            if (unit.y() == 0) {
                unit = unit.withY(1.0);
            }
            if (unit.z() == 0) {
                unit = unit.withZ(1.0);
            }
        }

        try {
            final int affected = editSession.makeShape(region, zero, unit, pattern, String.join(" ", expression), hollow, session.getTimeout());
            if (actor instanceof Player) {
                ((Player) actor).findFreePosition();
            }
            actor.printInfo(TranslatableComponent.of("worldedit.generate.created", TextComponent.of(affected)));
            return affected;
        } catch (ExpressionException e) {
            actor.printError(TextComponent.of(e.getMessage()));
            return 0;
        }
    }

    @Command(
            name = "/generatebiome",
            aliases = {"/genbiome", "/gb"},
            desc = "Sets biome according to a formula.",
            descFooter = "For details, see https://ehub.to/we/expr"
    )
    @CommandPermissions("worldedit.generation.shape.biome")
    @Logging(ALL)
    public int generateBiome(Actor actor, LocalSession session, EditSession editSession,
                             @Selection Region region,
                             @Arg(desc = "The biome type to set")
                             BiomeType target,
                             @Arg(desc = "Expression to test block placement locations and set biome type", variable = true)
                             List<String> expression,
                             @Switch(name = 'h', desc = "Generate a hollow shape")
                             boolean hollow,
                             @Switch(name = 'r', desc = "Use the game's coordinate origin")
                             boolean useRawCoords,
                             @Switch(name = 'o', desc = "Use the placement's coordinate origin")
                             boolean offset,
                             @Switch(name = 'c', desc = "Use the selection's center as origin")
                             boolean offsetCenter) throws WorldEditException {
        final Vector3 zero;
        Vector3 unit;

        if (useRawCoords) {
            zero = Vector3.ZERO;
            unit = Vector3.ONE;
        } else if (offset) {
            zero = session.getPlacementPosition(actor).toVector3();
            unit = Vector3.ONE;
        } else if (offsetCenter) {
            final Vector3 min = region.getMinimumPoint().toVector3();
            final Vector3 max = region.getMaximumPoint().toVector3();

            zero = max.add(min).multiply(0.5);
            unit = Vector3.ONE;
        } else {
            final Vector3 min = region.getMinimumPoint().toVector3();
            final Vector3 max = region.getMaximumPoint().toVector3();

            zero = max.add(min).multiply(0.5);
            unit = max.subtract(zero);

            if (unit.x() == 0) {
                unit = unit.withX(1.0);
            }
            if (unit.y() == 0) {
                unit = unit.withY(1.0);
            }
            if (unit.z() == 0) {
                unit = unit.withZ(1.0);
            }
        }

        try {
            final int affected = editSession.makeBiomeShape(region, zero, unit, target, String.join(" ", expression), hollow, session.getTimeout());
            actor.printInfo(TranslatableComponent.of("worldedit.generatebiome.changed", TextComponent.of(affected)));
            return affected;
        } catch (ExpressionException e) {
            actor.printError(TextComponent.of(e.getMessage()));
            return 0;
        }
    }


    @Command(
            name = "/lake",
            desc = "Generates a lake."
    )
    @CommandPermissions("worldedit.generation.lake")
    @Logging(PLACEMENT)
    public int lake(Actor actor, LocalSession session, EditSession editSession,
                    @Arg(desc = "The lake dimension")
                    double radii,
                    @Arg(desc = "Type of lake (e.g: oasis, lava, pond, water)")
                    String type) throws WorldEditException {
        double radiusX;
        double radiusY;
        double radiusZ;
        LakeType lakeType;

        if (type == null) {
            type = "water";
        }

        radiusX = radiusY = radiusZ = Math.max(0, radii);

        BlockVector3 pos = session.getPlacementPosition(actor);
        BlockVector3 posBelow = pos.add(0, -1, 0);
        if(editSession.getBlock(posBelow).getBlockType().id().equals("minecraft:air")){
            actor.printInfo(TranslatableComponent.of("worldedit.lake.invalidStartPos"));
            return 0;
        }

        ParserContext context = new ParserContext();
        context.setActor(actor);
        context.setSession(session);
        Pattern airPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:air", context);

        if(radii < 5){
            actor.printInfo(TranslatableComponent.of("worldedit.lake.invalidRadii"));
            return 0;
        }

        try {
            lakeType = LakeType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            actor.printInfo(TranslatableComponent.of("worldedit.lake.invalidArgument"));
            return 0;
        }


        switch (lakeType) {
            case WATER, LAVA -> {
                return createHole(actor, session, editSession, pos, airPattern, radiusX, radiusY, radiusZ, lakeType);
            }
            case POND, OASIS -> {
                return createPondOasisHole(actor, session, editSession, pos, airPattern, radiusX, radiusY, radiusZ, lakeType);
            }
            case INFINITE -> {
                return createInfiniteWaterHole(actor, session, editSession, pos);
            }
            default -> throw new IllegalStateException("Unexpected value: " + lakeType);
        }

    }

    private int createInfiniteWaterHole(Actor actor, LocalSession session, EditSession editSession, BlockVector3 pos) throws MaxChangedBlocksException, InputParseException {
        ParserContext context = new ParserContext();
        context.setActor(actor);
        context.setSession(session);
        Region lakeReg = new CuboidRegion(pos.add(0, -1, 0), pos.add(-1,-1,-1));
        Pattern waterPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:water[level=0]", context);
        actor.printInfo(TranslatableComponent.of("worldedit.lake.infinite"));
        return  editSession.replaceBlocks(lakeReg, Masks.alwaysTrue(), waterPattern);
    }

    private enum LakeType {
        WATER,
        LAVA,
        POND,
        OASIS,
        INFINITE,
    }

    private int createPondOasisHole(Actor actor, LocalSession session, EditSession editSession, BlockVector3 pos, Pattern pattern, Double radiusX, Double radiusY, Double radiusZ, LakeType lakeType)
            throws MaxChangedBlocksException, InputParseException, MaxRadiusException {

        int prevAffected = 0;
        Random rand = new Random();
        ParserContext context = new ParserContext();
        context.setActor(actor);
        context.setSession(session);

        int affected = 0;

        double randomRadiusX = radiusX + (rand.nextDouble() * 10.0 - 5.0);
        double randomRadiusY = radiusY + (rand.nextDouble() * 10.0 - 5.0);
        double randomRadiusZ = radiusZ + (rand.nextDouble() * 10.0 - 5.0);

        Vector3 radius = new Vector3(randomRadiusX + 3, randomRadiusY, randomRadiusZ + 3);

        Region sphere = new EllipsoidRegion(editSession.getWorld(), pos, radius);

        affected = editSession.makeSphere(pos, pattern, randomRadiusX, randomRadiusY, randomRadiusZ, true);
        prevAffected += affected;
        editSession.close();

        BlockVector3 waterPos = pos.add(0, -1, 0);
        double rad = radiusX * 10.0;
        worldEdit.checkMaxRadius(rad);
        Pattern waterPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:water[level=0]", context);
        affected = editSession.fillXZ(waterPos, waterPattern, rad, 1, false);

        prevAffected += affected;

        if (lakeType == LakeType.OASIS) {
            Pattern sandPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:sand", context);
            List<BlockType> airWaterType = new LinkedList<BlockType>();
            airWaterType.addLast(new BlockType("minecraft:water[level=0]"));
            airWaterType.addLast(new BlockType("minecraft:air"));

            Region surroundingRegion = generateSurroundingRegion(sphere, waterPos);

            affected = editSession.replaceBlocks(surroundingRegion, Masks.negate(new BlockTypeMask(editSession, airWaterType)), sandPattern);
            prevAffected += affected;
            editSession.close();

            affected = generateTreeOasis(surroundingRegion, editSession, waterPos, context);
            prevAffected += affected;

            actor.printInfo(TranslatableComponent.of("worldedit.lake.oasis"));
        }

        if (lakeType == LakeType.POND) {
            Pattern mudPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:podzol", context);

            List<BlockType> airWaterType = new LinkedList<BlockType>();
            airWaterType.addLast(new BlockType("minecraft:water[level=0]"));
            airWaterType.addLast(new BlockType("minecraft:air"));
            Region surroundingRegion = generateSurroundingRegion(sphere, waterPos);

            affected = editSession.replaceBlocks(surroundingRegion, Masks.negate(new BlockTypeMask(editSession, airWaterType)), mudPattern);
            prevAffected += affected;
            editSession.close();

            this.generateLilyPads(randomRadiusX, rand, waterPos, randomRadiusZ, editSession, context);

            actor.printInfo(TranslatableComponent.of("worldedit.lake.pond"));
        }
        return prevAffected;
    }

    private int generateTreeOasis(Region surroundingRegion, EditSession editSession, BlockVector3 waterPos, ParserContext context) throws MaxChangedBlocksException, InputParseException {
        BlockVector3 randomPointForTree = getRandomPointInRegion(surroundingRegion, editSession, waterPos);
        randomPointForTree = randomPointForTree.withY(waterPos.y() + 1);

        Pattern grassPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:grass_block", context);
        int affected = editSession.replaceBlocks(new CuboidRegion(randomPointForTree, randomPointForTree), Masks.alwaysTrue(), grassPattern);
        TreeType treeType = TreeType.ACACIA;
        treeType.generate(editSession, randomPointForTree);
        return affected;
    }

    private void generateLilyPads(double randomRadiusX, Random rand, BlockVector3 waterPos, double randomRadiusZ, EditSession editSession, ParserContext context) throws MaxChangedBlocksException, InputParseException {
        int nrLilyPads = rand.nextInt(0, (int) (randomRadiusX * 0.8));
        for(int i = 0; i <= nrLilyPads; i++){
            BlockVector3 lilyPadPos = waterPos;
            lilyPadPos = lilyPadPos.add(rand.nextInt(0,(int)(randomRadiusX)), 1, rand.nextInt(0,(int)(randomRadiusZ)));
            Pattern lilyPadPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:lily_pad", context);
            BlockVector3 posBelow = lilyPadPos.add(0,-1,0);
            if(editSession.getBlock(posBelow).toString().equals("minecraft:water[level=0]")) {
                editSession.setBlock(lilyPadPos, lilyPadPattern);
            }
        }
    }

    private static @NotNull Region generateSurroundingRegion(Region sphere, BlockVector3 waterPos) {
        BlockVector3 min = sphere.getMinimumPoint();
        BlockVector3 max = sphere.getMaximumPoint();

        max = max.withY(waterPos.y());
        return new CuboidRegion(min, max);
    }


    private BlockVector3 getRandomPointInRegion(Region surroundingRegion, EditSession es, BlockVector3 waterPos) {
        Random random = new Random();

        BlockVector3 min = surroundingRegion.getMinimumPoint();
        BlockVector3 max = surroundingRegion.getMaximumPoint();


        int x = random.nextInt(max.x() - min.x() + 1) + min.x();
        int y = waterPos.y();
        int z = random.nextInt(max.z() - min.z() + 1) + min.z();

        BlockVector3 result = BlockVector3.at(x,y,z);
        while(es.getBlock(result).getBlockType().id().equals("minecraft:water")){
            result = result.add(1,0,0);
        }
        return result;
    }

    private int createHole(Actor actor, LocalSession session, EditSession editSession, BlockVector3 pos, Pattern pattern, Double radiusX, Double radiusY, Double radiusZ, LakeType lakeType)
            throws MaxChangedBlocksException, InputParseException, MaxRadiusException {

        int prevAffected = 0;
        Random rand = new Random();
        ParserContext context = new ParserContext();
        context.setActor(actor);
        context.setSession(session);
        Pattern waterPattern;
        switch (lakeType) {
            case WATER -> waterPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:water[level=0]", context);
            case LAVA -> {
                waterPattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:lava[level=0]", context);
            }
            default -> throw new IllegalStateException("Unexpected value: " + lakeType);
        }

        final double adjustmentFactor = 1.10;
        int[][] displacements = {
                {(int) (radiusX * adjustmentFactor), 0, 0},
                {-(int) (radiusX * adjustmentFactor), 0, 0},
                {0, 0, (int) (radiusZ * adjustmentFactor)}
        };

        Region[] spheres = new Region[3];
        int i = 0;
        for (int[] displacement : displacements) {
            int affected = 0;
            BlockVector3 newPos = pos.add(displacement[0], displacement[1], displacement[2]);

            double randomRadiusX = radiusX + (rand.nextDouble() * 10.0 - 5.0);
            double randomRadiusY = radiusY + (rand.nextDouble() * 10.0 - 5.0);
            double randomRadiusZ = radiusZ + (rand.nextDouble() * 10.0 - 5.0);

            Vector3 radius = new Vector3(randomRadiusX + 7, randomRadiusY + 7, randomRadiusZ + 7);

            spheres[i] = new EllipsoidRegion(editSession.getWorld(), newPos, radius);

            affected = editSession.makeSphere(newPos, pattern, randomRadiusX, randomRadiusY, randomRadiusZ, true);
            prevAffected += affected;
            i++;
        }
        editSession.close();

        BlockVector3 waterPos = pos.add(0, -1, 0);
        double rad = radiusX * 10.0;
        worldEdit.checkMaxRadius(rad);
        int affected = editSession.fillXZ(waterPos, waterPattern, rad, 1, false);

        prevAffected += affected;
        if(lakeType == LakeType.WATER)
            actor.printInfo(TranslatableComponent.of("worldedit.lake.water"));
        if (lakeType == LakeType.LAVA) {
            Pattern stonePattern = WorldEdit.getInstance().getPatternFactory().parseFromInput("minecraft:stone", context);
            List<BlockType> airLavaType = new LinkedList<BlockType>();
            airLavaType.addLast(new BlockType("minecraft:lava[level=0]"));
            airLavaType.addLast(new BlockType("minecraft:air"));
            for(int j= 0; j < 3; j++) {
                Region surroundingRegion = generateSurroundingRegion(spheres[j], waterPos);

                affected = editSession.replaceBlocks(surroundingRegion, Masks.negate(new BlockTypeMask(editSession, airLavaType)), stonePattern);
                prevAffected += affected;
            }
            actor.printInfo(TranslatableComponent.of("worldedit.lake.lava"));
        }
        return prevAffected;
    }
}
