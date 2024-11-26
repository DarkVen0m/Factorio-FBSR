package com.demod.fbsr;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public abstract class Renderer {
	protected final Rectangle2D.Double bounds;
	protected final Layer layer;

	public Renderer(Layer layer, Point2D.Double position) {
		this(layer, new Rectangle2D.Double(position.x, position.y, 0, 0));
	}

	public Renderer(Layer layer, Rectangle2D.Double bounds) {
		this.layer = layer;
		this.bounds = bounds;
	}

	public Rectangle2D.Double getBounds() {
		return bounds;
	}

	public Layer getLayer() {
		return layer;
	}

	public abstract void render(Graphics2D g) throws Exception;
}
