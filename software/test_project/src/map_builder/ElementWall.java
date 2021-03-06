package map_builder;

import java.awt.*;
import functionality.Constants;

/**
 * map element of type: Wall
 * 
 * Any custom behavior should be implemented here
 * 
 * @author Patrick
 *
 */
public class ElementWall extends MapElement{


	public ElementWall(int gridX, int gridY, Color elementColor){
		super(gridX, gridY, MapType.WALL, elementColor);
		
	}
	

	@Override
	public void draw(Graphics graphics) {
		int x = (getGridX() * Constants.MAP_ELEMENT_SIZE) + Constants.WINDOW_MAP_MARGIN;
		int y = (getGridY()*Constants.MAP_ELEMENT_SIZE) + Constants.WINDOW_MAP_MARGIN+Constants.WINDOW_HEADER_HEIGHT;

		Graphics2D g2d = (Graphics2D)graphics;

		g2d.setColor(getColor());
		g2d.fillRect(x, y, getWidth(), getHeight());


		//Border color is just the main color but with an alpha value
		Color color = new Color(getColor().getRed(),getColor().getGreen(),getColor().getBlue(),85);
		g2d.setColor(color);
		g2d.setStroke(new BasicStroke(4));
		g2d.drawRect(x, y, getWidth(), getHeight());
		g2d.setStroke(new BasicStroke(1));
	}



}
