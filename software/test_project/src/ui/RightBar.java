package ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import functionality.Constants;
import functionality.Setup;
import map_builder.ElementBlackHole;
import map_builder.ElementEnemy;
import map_builder.ElementFinish;
import map_builder.ElementLaser;
import map_builder.ElementPlasmaBall;
import map_builder.ElementStart;
import map_builder.ElementWall;
import map_builder.MapElement;
import map_builder.MapType;

/**
 * This class is responsible for the right bar of the game.
 * 
 * Anything that should be on the right bar, should be added here
 * 
 * @author Patrick
 *
 */
public class RightBar extends UIElement {
	
	/*
	 * build mode:
	 * 		static mode:
	 * 			- wall
	 * 			- start
	 * 			- finish
	 * 			- black hole
	 * 
	 * 		dynamic mode:
	 * 			- laser
	 * 			- enemy
	 * 			- plasma ball
	 * 		
	 */
	
	// list of the static map elements that should be shown in map-building-mode
	private MapElement[] staticMapElements = new MapElement[]{
			new ElementStart(0, 0, Constants.COLOR_ACCENT),
			new ElementFinish(0, 0, Constants.COLOR_ACCENT),
			new ElementWall(0,0, Constants.COLOR_ACCENT),
			new ElementBlackHole(0, 0, Constants.COLOR_ACCENT)
	}; 
	// list of the dynamic map elements that should be shown in map-building-mode
	private MapElement[] dynamicMapElements = new MapElement[]{
			new ElementEnemy(0, 0, Constants.COLOR_MAP_START),
			new ElementLaser(0, 0, Constants.COLOR_ACCENT_2),
			new ElementPlasmaBall(0,0, Constants.COLOR_ACCENT_2)
	}; 
	
	// string representations of the map elements
	private String[] staticNames = new String[]{"Start", "Finish", "Wall", "Black Hole"};
	private String[] dynamicNames = new String[]{"Enemy", "Laser", "Plasma Ball"};
	
	final String[] headers = new String[]{"Static", "Dynamic"};
	
	// height of each list item & header (in pixels)
	final int listItemHeight = 50;
	
	// font for list items & headers text
	int fontSize = 16;
	Font font = new Font(Constants.DEFAULT_FONT, Font.PLAIN, fontSize);

	
	public RightBar(int x, int y, int width, int height, Color backgroundColor, Setup setup) {
		super(x, y, width, height, backgroundColor, setup);
	}
	
	/**
	 * overriding draw method for custom draw behavior:
	 * 
	 * either display the map-elements list or
	 * the game-play options
	 * 
	 */
	@Override
	public void draw(Graphics graphics) {
		drawBackground(graphics);
		
		//TODO: this boolean should be globally
		boolean mapBuildingMode = true;
		
		// decide whether to draw list with map-elements or configurations for AI game-play 
		if (mapBuildingMode){
			drawMapBuilderList(graphics);
		} else {
			drawGamePlayList(graphics);
		}
	}
	
	/**
	 * draws the basic background of the right bar
	 * @param graphics
	 */
	private void drawBackground(Graphics graphics){
		graphics.setColor(getBackgroundColor());
		graphics.fillRect(getX(), getY(), getWidth(), getHeight());
	}
	
	/**
	 * TODO: draw everything that is needed for the game-play mode
	 * @param graphics
	 */
	private void drawGamePlayList(Graphics graphics){
		
	}
	
	/**
	 * draws the map-builder-list, according to design specifications
	 * 
	 * @param graphics
	 */
	private void drawMapBuilderList(Graphics graphics){
		int counter = 0;
		// draw header 'static'
		drawMapElementListItem(graphics, getX(), getY(), listItemHeight, null, headers[0]);
		counter++;
		// draw static list
		for (int i=0; i<staticNames.length; i++){
			drawMapElementListItem(graphics, getX(), getY()+(counter*listItemHeight), 
					listItemHeight, staticMapElements[i], staticNames[i]);
			counter++;
		}
		// draw header 'dynamic'
		drawMapElementListItem(graphics, getX(), getY()+(counter*listItemHeight), 
				listItemHeight, null, headers[1]);
		counter++;
		// draw dynamic list
		for (int i=0; i<dynamicNames.length; i++){
			drawMapElementListItem(graphics, getX(), getY()+(counter*listItemHeight), 
					listItemHeight, dynamicMapElements[i], dynamicNames[i]);
			counter++;
		}
	}
	
	/**
	 * 
	 * draws a single list item for the map-builder-mode
	 * 
	 * @param graphics
	 * @param itemX			-	start x-coordinate of list item 
	 * @param itemY			-	start y-coordinate of list item
	 * @param itemHeight	-	height of list item
	 * @param element		-	the element that should be shown by the list item (if null, it's a header)
	 * @param name			-	the name that should be shown for the list item
	 */
	private void drawMapElementListItem(Graphics graphics, int itemX, int itemY, int itemHeight, 
			MapElement element, String name){
		
		// if null, then it's a header
		if (element == null){
			int textIndent = 15;
			
			//draw background
			graphics.setColor(Constants.COLOR_RIGHT_BAR_HEADER);
			graphics.fillRect(itemX, itemY, Constants.WINDOW_RIGHT_BAR_WIDTH, itemHeight);
			//draw text
			graphics.setColor(Constants.COLOR_AVATAR_WHITE);
			graphics.setFont(font);
			
			// Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
		    int theY = itemY + ((itemHeight - graphics.getFontMetrics(font).getHeight()) / 2) + graphics.getFontMetrics(font).getAscent();
		    graphics.drawString(name, itemX+textIndent, theY);
		    
		} else {
			//TODO: these variables should be added to Constants
			int elementIndent = 15;
			int elementWidth = 20;
			int textIndent = 50;
			
			//draw background
			graphics.setColor(Constants.COLOR_MAP_LAND);
			graphics.fillRect(itemX, itemY, Constants.WINDOW_RIGHT_BAR_WIDTH, itemHeight);
			//draw item
			graphics.setColor(element.getColor());
			graphics.fillRect(itemX+elementIndent, itemY + (itemHeight/2) - (elementWidth/2), elementWidth, elementWidth);
			//draw text
			graphics.setFont(font);
			int theY = itemY + ((itemHeight - graphics.getFontMetrics(font).getHeight()) / 2) + graphics.getFontMetrics(font).getAscent();
			graphics.drawString(name, itemX+textIndent, theY);
		}
		
		// draw separator line at bottom of item
		graphics.setColor(Constants.COLOR_RIGHT_BAR_HEADER);
		// -1 due to stroke width of line
		graphics.drawLine(itemX, itemY+itemHeight-1, itemX+Constants.WINDOW_RIGHT_BAR_WIDTH, itemY+itemHeight-1);
	}

}
