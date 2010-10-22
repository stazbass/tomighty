/*
 * Copyright (c) 2010 C�lio Cidral Junior
 * 
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 * 
 *        http://www.apache.org/licenses/LICENSE-2.0
 * 
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.tomighty.ui.options;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import org.tomighty.config.Options;
import org.tomighty.ioc.Inject;
import org.tomighty.ui.util.FieldFactory;

@SuppressWarnings("serial")
public class Times extends OptionPanel implements OptionGroup {

	@Inject private Options options;
	
	private JFormattedTextField pomodoro;
	private JFormattedTextField shortBreak;
	private JFormattedTextField longBreak;

	public Times() {
		pomodoro = addField("Pomodoro");
		shortBreak = addField("Short break");
		longBreak = addField("Long break");
	}
	
	@Override
	protected LayoutManager createLayout() {
		return new GridLayout(3, 3, 6, 6);
	}
	
	@Override
	public String name() {
		return "Times";
	}

	@Override
	public Component asComponent() {
		return this;
	}

	@Override
	public void readConfiguration() {
		pomodoro.setValue(options.time().pomodoro());
		shortBreak.setValue(options.time().shortBreak());
		longBreak.setValue(options.time().longBreak());
	}

	@Override
	public void saveConfiguration() {
		options.time().pomodoro(valueOf(pomodoro));
		options.time().shortBreak(valueOf(shortBreak));
		options.time().longBreak(valueOf(longBreak));
	}

	private int valueOf(JFormattedTextField field) {
		String text = field.getText(); 
		return Integer.parseInt(text);
	}

	private JFormattedTextField addField(String label) {
		JFormattedTextField field = FieldFactory.createIntegerField(1, 2);
		add(new JLabel(label, JLabel.TRAILING));
		add(field);
		add(new JLabel("minutes"));
		return field;
	}

}
