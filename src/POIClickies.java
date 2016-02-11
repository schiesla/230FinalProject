import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class POIClickies implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getLocationOnScreen();
		System.out.println(point.x + "");
		System.out.println(point.y + "");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub.
		
	}

}
