package javad.utils.ki.algorithm.astar.utils;

import java.util.Comparator;

import javad.utils.ki.algorithm.astar.map.Tile;

public class TileScoreComparator implements Comparator<Tile> {
    @Override
    public int compare(Tile o1, Tile o2) {
        return o1.getScore() - o2.getScore();
    }
}
