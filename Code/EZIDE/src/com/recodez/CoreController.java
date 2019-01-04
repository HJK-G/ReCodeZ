package com.recodez;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recodez.framework.CodeFile;
import com.recodez.framework.Executor;
import com.recodez.framework.Result;

@RestController
public class CoreController {
	@RequestMapping("/check")
	public String[] check(@RequestParam String code) {

		System.out.println(code);
		CodeFile file = new CodeFile(code);
		Executor executor = new Executor(file);

		Result result = executor.execute();

		return result.getDisplay();
	}
}
