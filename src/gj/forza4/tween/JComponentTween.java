package gj.forza4.tween;

import javax.swing.JComponent;

import aurelienribon.tweenengine.TweenAccessor;

public class JComponentTween implements TweenAccessor<JComponent> {

	public static final int POSITION_X = 1;
	public static final int POSITION_Y = 2;

	@Override
	public int getValues(JComponent d, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_X:
			returnValues[0] = d.getX();
			return 1;
		case POSITION_Y:
			returnValues[0] = d.getY();
			return 1;
		default:
			assert false;
			return -1;
		}

	}

	@Override
	public void setValues(JComponent d, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POSITION_X:
			d.setBounds((int) newValues[0], d.getY(), d.getWidth(), d.getHeight());
			break;

		case POSITION_Y:
			d.setBounds(d.getX(), (int) newValues[0], d.getWidth(), d.getHeight());
			break;
		default:
			assert false;
			break;
		}

	}
}
