package com.test.second;

import org.springframework.ui.*;

import com.test.second.parser.*;

public class ClassRoomParseCommand implements Command {

	@Override
	public boolean excute(Model model) {
		LecturePlan lecturePlan = new LecturePlan();
		lecturePlan.TotalParse();
		
		return true;
	}

}
