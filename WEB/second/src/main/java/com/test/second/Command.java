package com.test.second;

import org.springframework.ui.Model;

public interface Command {
	public boolean excute(Model model);
}