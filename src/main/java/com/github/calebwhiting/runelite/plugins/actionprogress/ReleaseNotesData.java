package com.github.calebwhiting.runelite.plugins.actionprogress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ReleaseNotesData {
    public static final String CONTENT;

    private static final class Release {
    final String version;
    final List<String> changes;

    Release(String version, List<String> changes) {
        this.version = version;
        this.changes = changes;
    }
    }

    private static final List<Release> RELEASES;

    static {
        List<Release> releases = new ArrayList<>();

    releases.add(new Release("1.28", Arrays.asList(
    "Fix smithing outfit fraction progress so item count matches the faster timer",
    "Add a simple progress bar mode")));

    releases.add(new Release("1.27", Arrays.asList(
    "Added support for butterfly mixes - Thanks @aclogar")));

    releases.add(new Release("1.26", Arrays.asList(
        "Added mastering mixology and sailing potions - Thanks @zedin27",
        "Added potato combinations - Thanks @aclogar",
        "Kandarin diary reward now considers Seers' spinning wheel - Thanks @aclogar",
        "Add support for Lunar tablet crafting - Thanks @aclogar")));

    releases.add(new Release("1.25", Arrays.asList(
        "Added Bryophyta's staff support - Thanks @BreakfastFood",
        "Add bottomless bucket support - Thanks @aclogar")));

    releases.add(new Release("1.24", Arrays.asList(
        "Add support for Grimstone Furnace - Thanks @BreakfastFood")));

    releases.add(new Release("1.23", Arrays.asList(
        "Add support for Camdozaal fishing - Thanks @aclogar")));

    releases.add(new Release("1.22", Arrays.asList(
        "Add support for new sailing items/actions - Thanks @aclogar")));

    releases.add(new Release("1.21", Arrays.asList(
        "Fix bow stringing detection that was broken in 1.19",
        "Fix Plank Make detection")));

    releases.add(new Release("1.20", Arrays.asList(
        "Update following sailing API changes",
        "Fix left clicking fire tending that was broken as part of the previous update")));

    releases.add(new Release("1.19", Arrays.asList(
        "Add support for wetting clay",
        "Add support for bow string spool",
        "Fix issue where action bar would show tending overlay when using sawmill")));

    releases.add(new Release("1.18", Arrays.asList(
        "Updating timing for fire tending",
        "Rework gold and silver casting")));

    releases.add(new Release("1.17", Arrays.asList(
        "Add support for tome of earth",
        "Add support for forgotten brew 4",
        "Add support for custom notifications",
        "Make guthix rest a 3 ticks action")));

    releases.add(new Release("1.16", Arrays.asList(
        "Add support for spinning bowstring")));

    releases.add(new Release("1.15", Arrays.asList(
        "Add support for jug of sunfire wine")));

    releases.add(new Release("1.14", Arrays.asList(
        "Fix plank make (hopefully for good) - Thanks @druyang",
        "Add support for dark essence fragment",
        "Add support for campfire tending")));

    releases.add(new Release("1.13", Arrays.asList(
        "Check for smith outfit for smithing timing",
        "Compact mode")));

    releases.add(new Release("1.12", Arrays.asList(
        "Add support for stringing items",
        "Fix gold crafting not showing progress bar",
        "Fix instance of the progress bar disappearing when it shouldn't",
        "Update lectern check to account for the new interface")));

    releases.add(new Release("1.11", Arrays.asList(
        "Add support for ultracompost",
        "Fix javelin timing",
        "Fix plank make detection")));

    releases.add(new Release("1.10", Arrays.asList(
        "Fix progress bar not showing when smithing")));

    releases.add(new Release("1.09", Arrays.asList(
        "Add support for javelins",
        "Add support to show ticks instead of seconds",
        "Add support for make-x darts")));

    releases.add(new Release("1.08", Arrays.asList(
        "Add vertical mode when resizing",
        "Add support for Plank make (Needs testing)")));

    releases.add(new Release("1.07", Arrays.asList(
        "Add support for fletching/crafting shields",
        "Add support for fletching crossbows",
        "Fixed progress bar display issues for several actions")));

    releases.add(new Release("1.06", Arrays.asList(
        "Add support for weaving",
        "Ability to resize overlay",
        "Detect when chemistry amulet breaks")));

    releases.add(new Release("1.05", Arrays.asList(
        "Add double ammo mould support",
        "Add settings for customizable colors for progress bar",
        "Updated for RuneLite version 1.9.13.3")));

    releases.add(new Release("1.04", Arrays.asList(
        "Added interruption when hit",
        "Added interruption when pest control portals drop",
        "Updated for RuneLite version 1.8.30")));

    releases.add(new Release("1.03.1", Arrays.asList(
        "Fixed various issues with 1.03",
        "IDQuery API moved to testing and replaced with constant ID arrays",
        "Fixed TemporossDetector",
        "Fixed ItemClickDetector")));

    releases.add(new Release("1.03", Arrays.asList(
        "Rewritten a lot of core components",
        "Many actions added",
        "Many ingredient checks added",
        "Many customizations added",
        "Option to show skill icons instead of product icons")));

    releases.add(new Release("1.02", Arrays.asList(
        "Track Tempoross activities (Cooking and filling crates)",
        "Interrupt action when wearing or removing equipment")));

    releases.add(new Release("1.01", Arrays.asList(
        "Respects the user-defined infobox background colour",
        "The \"Ignore single action\" property was mislabeled (was inverted)")));

        CONTENT = buildContent(releases);
        RELEASES = releases;
    }

    private static String buildContent(List<Release> releases) {
    StringBuilder sb = new StringBuilder();
    sb.append("## Release notes\n");
    for (Release r : releases) {
        sb.append("- `").append(r.version).append("`\n");
        for (String c : r.changes) {
        sb.append(c).append("\n");
        }
    }
    return sb.toString();
    }

    public static String getLatestRelease()
    {
        if (RELEASES == null || RELEASES.isEmpty()) {
            return "";
        }
        Release r = RELEASES.get(0);
        StringBuilder sb = new StringBuilder();
        for (String c : r.changes) {
            sb.append(c).append("\n");
        }
        return sb.toString().trim();
    }

    public static String getLatestVersion()
    {
        if (RELEASES == null || RELEASES.isEmpty()) {
            return "";
        }
        return RELEASES.get(0).version;
    }

    private ReleaseNotesData() {}
}
