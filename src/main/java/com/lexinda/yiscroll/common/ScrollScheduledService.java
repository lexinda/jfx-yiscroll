package com.lexinda.yiscroll.common;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;

public class ScrollScheduledService extends ScheduledService<Void> {

	private ScrollPane scrollPane;

	private double hValue;

	private double step;

	private Label label;

	private double labelWidth;

	private double scrollWidth;

	private double labelHValue;
	/**
	 * 1,右进左出循环 
	 * 2.从右滚动到左从左滚动到右循环
	 */
	private int scrollType = 1;
	// 1左2右
	private int arrow = 1;

	@Override
	protected Task<Void> createTask() {
		// TODO Auto-generated method stub
		return new Task<Void>() {
			@Override
			protected Void call() {
				if (scrollType == 1) {
					if (labelWidth < scrollWidth) {
						double minHValue = -labelWidth;
						if (labelHValue <= minHValue) {
							labelHValue = scrollWidth;
						}
						labelHValue = labelHValue - step;
						getLabel().setTranslateX(labelHValue);
					} else {
						if (labelHValue <= scrollWidth + labelWidth && labelHValue > 0) {
							labelHValue = labelHValue - step;
							if (labelHValue < 0) {
								getLabel().setTranslateX(0);
							} else {
								getLabel().setTranslateX(labelHValue);
							}
						} else {
							if (labelHValue <= -scrollWidth) {
								hValue = 0;
								scrollPane.setHvalue(hValue);
								labelHValue = scrollWidth;
								getLabel().setTranslateX(labelHValue);
							} else {
								if (hValue >= 1) {
									labelHValue = labelHValue - step;
									getLabel().setTranslateX(labelHValue);
								} else {
									hValue = hValue + 1 / ((labelWidth - scrollWidth) / step);
									scrollPane.setHvalue(hValue);
								}
							}
						}
					}
				} else {
					if(labelWidth < scrollWidth) {
						if (arrow == 1) {
							labelHValue = labelHValue - step;
							if (labelHValue <= 0) {
								labelHValue = 0;
								arrow = 2;
							}
						}
						if (arrow == 2) {
							labelHValue = labelHValue + step;
							if(labelHValue >= (scrollWidth - labelWidth)) {
								labelHValue = (scrollWidth - labelWidth);
								arrow = 1;
							}
						}
						getLabel().setTranslateX(labelHValue);
					}else {
						if (hValue > 1) {
							hValue = 1;
						}
						if (hValue < 0) {
							hValue = 0;
						}
						if (arrow == 1) {
							if (hValue >= 1) {
								arrow = 2;
							} else {
								hValue = hValue + 1 / ((labelWidth - scrollWidth) / step);
							}
						}
						if (arrow == 2) {
							if (hValue <= 0) {
								arrow = 1;
							} else {
								hValue = hValue - 1 / ((labelWidth - scrollWidth) / step);
							}
						}
						scrollPane.setHvalue(hValue);
					}
					
				}
				return null;
			}
		};
	}

	public ScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(ScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public double gethValue() {
		return hValue;
	}

	public void sethValue(double hValue) {
		this.hValue = hValue;
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		this.step = step;
	}

	public Label getLabel() {
		return label;
	}

	public void setLabel(Label label) {
		this.label = label;
	}

	public double getScrollWidth() {
		return scrollWidth;
	}

	public void setScrollWidth(double scrollWidth) {
		this.scrollWidth = scrollWidth;
	}

	public double getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(double labelWidth) {
		this.labelWidth = labelWidth;
	}

	public double getLabelHValue() {
		return labelHValue;
	}

	public void setLabelHValue(double labelHValue) {
		this.labelHValue = labelHValue;
	}

	public int getScrollType() {
		return scrollType;
	}

	public void setScrollType(int scrollType) {
		this.scrollType = scrollType;
	}

}