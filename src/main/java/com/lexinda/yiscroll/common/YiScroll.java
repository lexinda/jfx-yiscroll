package com.lexinda.yiscroll.common;

import java.util.concurrent.Executors;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class YiScroll extends ScrollPane {
	private Label label;
	private ScrollScheduledService scheduledService;
	private Duration delay = Duration.millis(0);
	private Duration period = Duration.millis(25);
	private Double step = 0.5;
	/**
	 * 1,右进左出循环 
	 * 2.从右滚动到左从左滚动到右循环
	 */
	private int scrollType = 2;

	public YiScroll(String text,Font font,Paint color) {
		super();
		this.label = new Label(text);
		this.label.setFont(font);
		this.label.setTextFill(color);
		this.setContent(this.label);
	}

	public void start() {
		Text theText = new Text(this.label.getText());
		theText.setFont(this.label.getFont());
		double labelWidth = theText.getBoundsInLocal().getWidth();
		if (this.scheduledService == null) {
			intScrollScheduledService(delay, period, step);
			this.scheduledService.setLabel(this.label);
			this.scheduledService.setScrollWidth(this.getWidth());
			this.scheduledService.setLabelWidth(labelWidth);
			this.scheduledService.setLabelHValue(this.getWidth());
			if(scrollType==2) {
				this.scheduledService.setLabelHValue(this.getWidth()-labelWidth);
			}
			
		}
		this.scheduledService.start();
	}

	public void cancel() {
		this.scheduledService.cancel();
	}

	public void restart() {
		this.scheduledService.restart();
	}

	private void intScrollScheduledService(Duration delay, Duration period, double step) {
		this.scheduledService = new ScrollScheduledService();
		this.scheduledService.setScrollPane(this);
		this.scheduledService.sethValue(0);
		this.scheduledService.setStep(step);
		this.scheduledService.setScrollType(scrollType);
		// 设置线程池，restart会尝试重用线程
		this.scheduledService.setExecutor(Executors.newFixedThreadPool(1));
		// 延时2s开始
		this.scheduledService.setDelay(this.delay);
		// 间隔1s执行
		this.scheduledService.setPeriod(this.period);
	}

	public Duration getDelay() {
		return delay;
	}

	public void setDelay(Duration delay) {
		this.delay = delay;
	}

	public Duration getPeriod() {
		return period;
	}

	public void setPeriod(Duration period) {
		this.period = period;
	}

	public Double getStep() {
		return step;
	}

	public void setStep(Double step) {
		this.step = step;
	}

	public int getScrollType() {
		return scrollType;
	}

	public void setScrollType(int scrollType) {
		this.scrollType = scrollType;
	}

}
