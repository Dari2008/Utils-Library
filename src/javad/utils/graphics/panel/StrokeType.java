package javad.utils.graphics.panel;

import java.awt.BasicStroke;
import java.awt.Stroke;

public enum StrokeType {
	LINE, DASHED, DOTTED;
	
    public static Stroke getStroke(StrokeType strokeType, float lineWidth) {
        switch (strokeType) {
            case LINE:
                return new BasicStroke(lineWidth);
            case DOTTED:
                return new BasicStroke(lineWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1.0f, 2.0f}, 0);
            case DASHED:
                return new BasicStroke(lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5.0f, 5.0f}, 0);
            default:
                return new BasicStroke(lineWidth);
        }
    }
}
