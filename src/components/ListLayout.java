package components;

import java.awt.*;

import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class ListLayout implements LayoutManager {

	private int vgap;
	
	private int minWidth = 0, minHeight = 0;
	private int preferredWidth = 0, preferredHeight = 0;

	// Constructor
	public ListLayout(int v) {
		vgap = v;
	}
	
	// Main Layout Function
	public void layoutContainer(Container parent) {
		int maxWidth = parent.getWidth();
		int nComps   = parent.getComponentCount();

		int previousHeight = 0;
		int x = 0, y = 0;   

		// For every child
		for (int i = 0 ; i < nComps ; i++) {
			Component c = parent.getComponent(i);		// Gets the child
			
			if (c.isVisible()) {						// If it is visible
				Dimension d = c.getPreferredSize();		

				y += previousHeight + vgap;				// The height before placing it = the heights of everything before + the vertical gap
				c.setBounds(x, y, maxWidth, d.height);	// Sets x = left border, y = the height calculated before, maximum width and its preferred height

				previousHeight = d.height;				// The height of everything before is incremented with the height of this child
			}
		}
	}


	private void setSizes(Container parent) {
		int nComps = parent.getComponentCount();
		Dimension d = null;

		preferredWidth = 0;
		preferredHeight = 0;
		minWidth = 0;
		minHeight = 0;

		// For every child in the layout
		for (int i = 0; i < nComps; i++) {
			Component c = parent.getComponent(i);
			
			if (c.isVisible()) {
				d = c.getPreferredSize();

				if (i > 0) {
					preferredHeight += vgap;
				}
				preferredHeight += d.height;

				minWidth = Math.max(c.getMinimumSize().width, minWidth);
				minHeight = preferredHeight;
			}
		}
	}


	public Dimension preferredLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
		parent.getComponentCount();

		setSizes(parent);

		Insets insets = parent.getInsets();
		dim.width = preferredWidth + insets.left + insets.right;
		dim.height = preferredHeight + insets.top + insets.bottom;

		return dim;
	}


	public Dimension minimumLayoutSize(Container parent) {
		Dimension dim = new Dimension(0, 0);
		parent.getComponentCount();

		Insets insets = parent.getInsets();
		dim.width = minWidth + insets.left + insets.right;
		dim.height = minHeight + insets.top + insets.bottom;

		return dim;
	}


	public void addLayoutComponent(String name, Component comp) {}
	public void removeLayoutComponent(Component comp) {}

	@Override
	public String toString() {
		return getClass().getName() + "[vgap = " + vgap + "]";
	}
}
